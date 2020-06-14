import javafx.scene.Node;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.application.Application;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.PhongMaterial;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.CullFace; 
import javafx.stage.Stage; 
import javafx.scene.shape.Sphere;
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.PerspectiveCamera; 
import javafx.scene.Scene;  
import javafx.scene.paint.*;
import javafx.scene.shape.Box; 
import javafx.scene.shape.DrawMode; 
import javafx.stage.Stage; 
import javafx.scene.Scene; 
import javafx.scene.paint.Material;
import javafx.scene.control.Button; 
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.*; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import java.io.Serializable;
import java.util.*;
    /**
*<h1>Game page </h1>
*<p> 
*This is the game page of the game where user is going to play the game
*this page has 3 functionality namely UNDO, BACK and RESTART
*Undo button takes the state of the game to the previous state   
*Back button takes the player back to the main page from the game page
*Restart starts the game from the start and clearing the orbs from the previous game
* @author Aman Roy (2016011), Suyash Singh(2016105)
* @version 1.0
* @since 2017-10-20
*/
  class Player
  {
    String color;
    int player_number;
    int index;
    Player(String color , int player_number , int index)
    {
        this.color = color;
        this.player_number = player_number;
        this.index = index;
    }
  }
public class Game_page2 extends Application implements Serializable {
    
    node ar[][];
    int count;
    int player_numbers;
    String color[];
    int row , col;
    int i_index , j_index;
    Main_page m;
    Game_page2 cur , prev;
    GridPane root;
    boolean visited[];
    ArrayList <Player> pl;
    int counter;  
    /**
   * This is the constructor of the game page which initializes the game page 
   *@param row This is the First parameter to the constructor of game page 
   *representing the number of rows in the grid
   *@param col This is the Second parameter to the constructor of game page 
   *representing the number of columns in the grid
   *@param p This is the third parameter to the constructor of game page 
   *representing the number of players in the game
   *@param m This is the fourth parameter to the constructor of game page 
   *which has an instance of the mainpage 
   *@param color This is the fifth parameter to the constructor of game page 
   *representing the color array of the players in the order set up in settings page
   */ 
    public Game_page2(int row , int col , int p , Main_page m , String[] color)
    {
        this.row = row;
        this.col = col;
        this.player_numbers = p;
        this.color = color;
        this.count=0;
        this.setup(this.row , this.col);
        this.m = m;
        visited = new boolean[player_numbers];
        pl = new ArrayList <Player>();
        initialise();
        counter = 0;
    }

    public void initialise()
    {
        for(int i=0;i<this.player_numbers;i++)
        {
            pl.add(new Player(color[i] , i+1 , i));
        }
    }
    /**
   * This method is setting up the grid of the size RowXCol
   *@param size1 is the number of rows required.
   *@param size2 is the number of columns required.
   *
   */
    public void setup(int size1 , int size2)
    {
        row = size1;
        col = size2;
        ar = new node [size1][size2];
        count=0;
        for(int i=0;i<size1;i++)
        {
            for(int j=0;j<size2;j++)
            {
                if(i==0 && j==0 || i==size1-1 && j==0 || i==0 && j==size2-1 || i==size1-1 && j==size2-1)
                {
                    helper(i,j, 2,"c");
                }
                else if(i==0 || i==size1-1 || j==0 || j==size2-1)
                {
                    helper(i,j,3,"f");
                }
                else
                {
                    helper(i,j,4,"m");
                }
            }
        }
        count=0;
    }

    /**
   * This method is initialising each node/cell of the grid
   *@param i is the row number.
   *@param j is the column number.
   *@param max is the number of maximum orbs that each cell can have.
   *@param t is the type of the orb.
   *
   */
    public void helper(int i , int j,int max , String t)
    {
        count+=1;
        if(count%player_numbers==0)
        {   
            ar[i][j] = new node(max , t ,"" ,0 );
        }
        else
        {
            ar[i][j] = new node(max , t ,"" ,0 );
        }
    }
    /**
   * This method is creating the Game page and contains all the GUI components
   *including the functionality of each button and other components
   *@param primaryStage is the only parameter.
   *
   */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Page");	
        Rectangle[][] box=new Rectangle[50][50];
        Sphere[] sphere =new Sphere[500];
        root = new GridPane(); 
       
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(0); 
        root.setHgap(0);
        GridPane[][] stack=new GridPane[50][50];
        int k=0;
        int t=0;
        TranslateTransition translateTransition[] = new TranslateTransition[500];
       
        root.setBlendMode(BlendMode.MULTIPLY);
        for (int i=0;i<this.row ;i++ ) {
            for (int j=0; j<this.col;j++ ) {
              
                box[i][j] = new Rectangle(i,j,40,40);     
                box[i][j].setStroke(Color.BLACK);
                box[i][j].setStrokeWidth(2);
                box[i][j].setFill(Color.TRANSPARENT);
                stack[i][j]=new GridPane();
                stack[i][j].setPadding(new Insets(10, 10, 10, 10));
                root.add(box[i][j],j,i);      
 }
        }
        System.out.println(ar[0][0].player);
         for (int i=0;i<this.row ;i++ ) {
            for (int j=0; j<this.col;j++ ) {
                System.out.println(-1);
                System.out.println(this.player_numbers);
                stack[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, new HelloEvent(this,stack,root,primaryStage)); 
                root.add(stack[i][j],j,i); 
                root.setRowSpan(stack[i][j],2);
                root.setColumnSpan(stack[i][j],2);
          }
        }
        
        Button mainpage=new Button("BACK");
        mainpage.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle (MouseEvent event)
            {
                m.start(primaryStage);
            }
        });
        Button redo=new Button("UNDO");
        redo.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle (MouseEvent event)
            {
                if(prev==null)
                {
                    prev = new Game_page2(row,col,player_numbers,m,color);
                    prev.count=0;
                    prev.setup(prev.row , prev.col);
                }
                else

                {
                    makecopy(prev);
                }
                for(int i=0;i<row;i++)
                {
                    for(int j=0;j<col;j++)
                    {
                        insertball(i,j,ar[i][j].color,stack);
                    }
                }
                counter = (prev.counter)%player_numbers; 
                count = prev.count;
                System.out.println(count);

            }
        });
        Button restart=new Button("RESTART");
        restart.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle (MouseEvent event)
            {
                Game_page2 g =  new Game_page2(row , col , player_numbers , m , m.s.color);
                g.start(primaryStage);
            }
        });

        root.add(mainpage,20,20);
        root.add(redo,21,20);
        root.add(restart,22,20);
        root.setAlignment(Pos.CENTER);
        
       // box[1][1].setFill();
        Scene scene = new Scene(root);
        PerspectiveCamera camera = new PerspectiveCamera(false); 
        camera.setTranslateX(3); 
        camera.setTranslateY(4); 
        camera.setTranslateZ(0); 
        scene.setCamera(camera);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
   * This method is making a copy of the gamepage type object
   *@param g is the object of the Game_page which is to be copied
   */ 
    public void makecopy(Game_page2 g)
    {
        System.out.println(g);
        for(int i=0;i<this.row;i++)
        {
            for(int j=0;j<this.col;j++)
            {
                this.ar[i][j].currentsize = g.ar[i][j].currentsize; 
                this.ar[i][j].maxsize = g.ar[i][j].maxsize;
                this.ar[i][j].type = g.ar[i][j].type;
                this.ar[i][j].color = g.ar[i][j].color;
                this.ar[i][j].player = g.ar[i][j].player;
                this.cur.ar[i][j].currentsize = g.ar[i][j].currentsize; 
                this.cur.ar[i][j].maxsize = g.ar[i][j].maxsize;
                this.cur.ar[i][j].type = g.ar[i][j].type;
                this.cur.ar[i][j].color = g.ar[i][j].color;
                this.cur.ar[i][j].player = g.ar[i][j].player;
            }
        }
        this.counter = this.prev.counter;
        this.cur.counter = this.counter;
        this.count = this.prev.count;
        this.cur.count = this.count;
        print();
        System.out.println(this.counter + "1 ");
        System.out.println(this.cur.counter + "2");
        System.out.println(this.prev.counter+ "3");
        
    }
    /**
   * This method is printing the game status on the console to check the backend 
   *side of the game.
   *
   */ 
    public void print()
    {
        for(int i=0;i<this.row;i++)
        {
            for(int j=0;j<this.col;j++)
            {
                System.out.print(this.ar[i][j].currentsize+"T"+this.ar[i][j].player + " ");
            }
            System.out.println();
        }
    }
     /**
   * This method is creating the ball in a node/cell at position i,j
   *@param i is the row number
   *@param j is the column number
   *@param col is the color of the ball
   *@param r is the gridpane in which the ball is to be blended
   *
   */
    public void insertball(int i,int j,String col,GridPane r[][]){
    PhongMaterial material = new PhongMaterial();
    if (col.equals("RED")) {
        material.setDiffuseColor(Color.RED);    
    }
    else if (col.equals("BLUE")) {
        material.setDiffuseColor(Color.BLUE);    
    }
    else if (col.equals("GREEN")) {
        material.setDiffuseColor(Color.GREEN);    
    }
    else if (col.equals("BLACK")) {
        material.setDiffuseColor(Color.BLACK);    
    }
    else if (col.equals("PINK")) {
        material.setDiffuseColor(Color.PINK);    
    }
    else if (col.equals("VIOLET")) {
      material.setDiffuseColor(Color.VIOLET);    
    }
    else if (col.equals("ORANGE")) {
    material.setDiffuseColor(Color.ORANGE);    
    }
    else{
        material.setDiffuseColor(Color.YELLOW);    
    }
   

     if (i==0 && j==0 || i==0 && j==this.col-1||i==this.row-1 && j==0||i==this.row-1 && j==this.col-1) {  
     if (this.ar[i][j].currentsize==1) {
        r[i][j].getChildren().clear();
        Sphere s1=new Sphere(7);
        s1.setMaterial(material);
        TranslateTransition translateTransition1=new TranslateTransition();
        translateTransition1.setDuration(Duration.millis(100)); 
        translateTransition1.setNode(s1);
        translateTransition1.setByX(5);
        translateTransition1.setCycleCount(Timeline.INDEFINITE);
        translateTransition1.setAutoReverse(true); 
        translateTransition1.play();   
        r[i][j].add(s1,0,0);
       //root.add(r,j,i); 
        
     }
     else if (this.ar[i][j].currentsize==2) {
        r[i][j].getChildren().clear();
        
     }

}
else if (i==0 || i==this.row-1 || j==0 || j==this.col-1) {
    TranslateTransition translateTransition1=new TranslateTransition();
    TranslateTransition translateTransition2=new TranslateTransition();
    Sphere s1=null;
    Sphere s2=null;
    
    if (this.ar[i][j].currentsize==1) {
        r[i][j].getChildren().clear();
        s1=new Sphere(7);
        s1.setMaterial(material);
        translateTransition1.setDuration(Duration.millis(500)); 
        translateTransition1.setNode(s1);
        translateTransition1.setByX(5);
        translateTransition1.setCycleCount(Timeline.INDEFINITE);
        translateTransition1.setAutoReverse(true); 
        translateTransition1.play();   
        r[i][j].add(s1,0,0);
       //root.add(r,j,i);
        
     }
     else if (this.ar[i][j].currentsize==2) {
        System.out.println(3000);
        r[i][j].getChildren().clear();
        s2=new Sphere(7);
        s1=new Sphere(7);
        s1.setMaterial(material);
        s2.setMaterial(material);
        translateTransition1.setDuration(Duration.millis(200)); 
        translateTransition2.setDuration(Duration.millis(200)); 
        translateTransition2.setNode(s2);
        translateTransition2.setByX(5);
        translateTransition2.setCycleCount(Timeline.INDEFINITE);
        translateTransition2.setAutoReverse(true); 
        translateTransition2.play();   
        r[i][j].add(s1,0,0);
        r[i][j].add(s2,0,0);
       //root.add(r,j,i);
     }
     else{
         r[i][j].getChildren().clear();
     }
}
else{
     TranslateTransition translateTransition1=new TranslateTransition();
    TranslateTransition translateTransition2=new TranslateTransition();
    Sphere s1=null;
    Sphere s2=null;
    Sphere s3=null;
    if (this.ar[i][j].currentsize==1) {
        r[i][j].getChildren().clear();
        s1=new Sphere(7);
        s1.setMaterial(material);
        r[i][j].add(s1,0,0);
       //root.add(r,j,i);
     }
     else if (this.ar[i][j].currentsize==2) {
        r[i][j].getChildren().clear();
        s2=new Sphere(7);
        s1=new Sphere(7);
        s1.setMaterial(material);
        s2.setMaterial(material);
        translateTransition2.setDuration(Duration.millis(500)); 
        translateTransition2.setNode(s1);
        translateTransition2.setByX(10);
        translateTransition2.setCycleCount(Timeline.INDEFINITE);
        translateTransition2.setAutoReverse(true); 
        translateTransition2.play();   
        translateTransition1.setDuration(Duration.millis(500)); 
        translateTransition1.setNode(s2);
        translateTransition1.setByX(-10);
        translateTransition1.setCycleCount(Timeline.INDEFINITE);
        translateTransition1.setAutoReverse(true); 
        translateTransition1.play();   
        r[i][j].add(s1,0,0);
        r[i][j].add(s2,1,0);
        //root.add(r,j,i);
     } 
     else if (this.ar[i][j].currentsize==3) {
            r[i][j].getChildren().clear();
            s2=new Sphere(7);
            s1=new Sphere(7);
            s3=new Sphere(7);
            s1.setMaterial(material);
            s2.setMaterial(material);
            s3.setMaterial(material);
            translateTransition1.setNode(s2);
            translateTransition1.setByX(-10);
            translateTransition1.setByY(10); 
            translateTransition1.setCycleCount(Timeline.INDEFINITE);
            translateTransition1.setAutoReverse(true); 
            translateTransition1.play();
            translateTransition2.setNode(s3);
            translateTransition2.setByX(10);
            translateTransition2.setByY(-10); 
            translateTransition2.setCycleCount(Timeline.INDEFINITE);
            translateTransition2.setAutoReverse(true); 
            translateTransition2.play();
            r[i][j].add(s1,0,0);
            r[i][j].add(s2,1,0);
            r[i][j].add(s3,0,1);
           // root.add(r,j,i);
       }
       else{
         r[i][j].getChildren().clear();
       } 
}
}    
} 
