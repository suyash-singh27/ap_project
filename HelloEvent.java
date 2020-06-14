import javafx.scene.Node;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.application.Application;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.PhongMaterial;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Optional;
import javafx.scene.Group; 
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene; 
import javafx.scene.shape.CullFace; 
import javafx.scene.control.ButtonType;
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
/**
*<h1>HelloEvent</h1>
*<p> 
*This is the main page of the game where user gets to decide the grid of the game
*and the number of players that are going to play the game.
*The start button redirects to the game page where the game actually happens   
*and the settings button redirects to the setting page.
* @author Aman Roy (2016011), Suyash Singh(2016105)
* @version 1.0
* @since 2017-10-20
 */
public class HelloEvent implements EventHandler <MouseEvent> , Serializable {  
    GridPane[][] r;
    Game_page2 g;
    GridPane root;
    Stage primaryStage;
    /**
   * This is the constructor of the HelloEvent class which instantiate the HelloEvent 
   *@param g is the first parameter of the constructor of the type Game_page
   *@param st is the second parameter of the constructor of the type 2D array of Gridpane
   *@param root is the third parameter of constructor of the type Gridpane
   *@param primaryStage is the fourth parameter of the constructor of the type stage
   */ 
    public HelloEvent(Game_page2 g,GridPane[][] st,GridPane root, Stage primaryStage)
    {
        this.g = g;
        this.r=st;
        this.root=root;
        this.primaryStage=primaryStage;
    }

   /**
   * This method is keeping two copies of the game page to implement the UNDO function
   *@param g1 is the instance of Gamepage which is to be saved
   *@param k is a switch parameter which controls the copying in the previous object or the current object
   *
   */ 
    public void makecopy(Game_page2 g1 ,int k)
    {
        if(g1!=null)
        {
            Game_page2 temp = new Game_page2(g1.row , g1.col , g1.player_numbers , g1.m , g1.color);
            temp.counter = g1.counter;
            temp.setup(temp.row , temp.col);
            for(int i=0;i<temp.row;i++)
            {
                for(int j=0;j<temp.col;j++)
                {
                    temp.ar[i][j].currentsize = g1.ar[i][j].currentsize; 
                    temp.ar[i][j].maxsize = g1.ar[i][j].maxsize;
                    temp.ar[i][j].type = g1.ar[i][j].type;
                    temp.ar[i][j].color = g1.ar[i][j].color;
                    temp.ar[i][j].player = g1.ar[i][j].player;
                }
            }
            if(k==1)
            {
                g.prev = temp;
                g.prev.counter = g1.counter;
            }
            else
            {
                g.cur = temp;
                g.cur.counter = g1.counter;
            }
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
    public void insertball(int i,int j,String col){
    PhongMaterial material = new PhongMaterial();
   // System.out.println(1000);
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
   

     if (i==0 && j==0 || i==0 && j==g.col-1||i==g.row-1 && j==0||i==g.row-1 && j==g.col-1) {  
     if (g.ar[i][j].currentsize==1) {
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
     else if (g.ar[i][j].currentsize==2) {
        r[i][j].getChildren().clear();
        
     }

}
else if (i==0 || i==g.row-1 || j==0 || j==g.col-1) {
   // System.out.println("fuck" );
    TranslateTransition translateTransition1=new TranslateTransition();
    TranslateTransition translateTransition2=new TranslateTransition();
    Sphere s1=null;
    Sphere s2=null;
    
    if (g.ar[i][j].currentsize==1) {
       // System.out.println("fuckyou" );
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
     else if (g.ar[i][j].currentsize==2) {
       //  System.out.println(3000);
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
    if (g.ar[i][j].currentsize==1) {
        r[i][j].getChildren().clear();
        s1=new Sphere(7);
        s1.setMaterial(material);
        r[i][j].add(s1,0,0);
       //root.add(r,j,i);
     }
     else if (g.ar[i][j].currentsize==2) {
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
     else if (g.ar[i][j].currentsize==3) {
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
/**
   * This method is checking the node/cell number and accordingly inserting the ball
   *@param i is the row number
   *@param j is the column number
   *
   */
    public void insert(int i,int j)
    {
        Player p = g.pl.get(g.counter);
        if(g.ar[i][j].player==0)
        {
            System.out.println(-1);
            g.ar[i][j].player = p.player_number;
            g.ar[i][j].color = g.color[p.index];
            g.ar[i][j].currentsize+=1;
            g.counter = (g.counter+1)%g.player_numbers;
            g.count+=1;
            insertball(i,j,p.color);
        }
        else if(g.ar[i][j].player==p.player_number)
        {
            g.ar[i][j].currentsize+=1;
            g.count+=1;
            g.counter = (g.counter+1)%g.player_numbers;
            insertball(i,j,p.color);
            if(g.ar[i][j].currentsize >= g.ar[i][j].maxsize)
            {
                System.out.println(1000);
                func(i,j,p.color,p.player_number);
            }
        }
        makecopy(g,2);
        if(g.count>=g.player_numbers)
        {    
            checkwinner();
        }
        return;
    }
    /**
   * This method is checking the winner after each move
   *
   */
    public void checkwinner()
    {
        if(g.player_numbers==1)
        {
            System.out.println(g.pl.get(g.counter).player_number + " is winner");
            return;
        }
        else{
            int i=0;
        while(i<g.player_numbers)
        {
            System.out.println(i);
            System.out.println(g.counter);
            int temp=0;
            for(int j=0;j<g.row;j++)
            {
                for(int k=0;k<g.col;k++)
                {
                    if(g.ar[j][k].player== (g.pl.get(i).player_number))
                    {
                        temp=1;
                        break;
                    }
                }
                if(temp==1)
                {
                    break;
                }
            }
                if(temp==0)
                {
                    System.out.println("hi");
                    System.out.println(g.pl.get(i).player_number);
                    g.pl.remove(i);
                    g.player_numbers-=1;
                    if(g.counter > i)
                    {
                        g.counter-=1;
                    }
                    else if(g.counter >= g.player_numbers)
                    {
                        g.counter = 0;
                    }
                    i-=1;
                }
                i+=1;
                if(g.player_numbers==1)
                {
                    System.out.println(g.pl.get(g.counter).player_number + " is winner");
                    Alert winn = new Alert(AlertType.INFORMATION);
                    winn.setTitle("Winner");
                    winn.setHeaderText("THE WINNER IS : ");
                    winn.setContentText("Player number "+g.pl.get(g.counter).player_number);
                    ButtonType buttonTypeOne = new ButtonType("Go to Main page");
                    ButtonType buttonTypeTwo = new ButtonType("Close the game");
                    winn.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
                    Optional<ButtonType> result = winn.showAndWait();
                    if (result.get() == buttonTypeOne){
                      g.m.start(this.primaryStage);

                    }
                    else{
                    System.exit(0);
                }
                } 
                temp=0;      
        }
    }}
    /**
   * This method is recursively checking the insertion of the ball at each and every move
   *@param i is the row number
   *@param j is the column number
   *@param color is the color of the ball
   *@param player_no is the player number whose turn is going on
   *
   */
    public void func(int i,int j,String color , int player_no)
    {
        if(g.ar[i][j].currentsize < g.ar[i][j].maxsize)
        {
            return;
        }
        if(g.ar[i][j].currentsize >= g.ar[i][j].maxsize)
        {
            String s = g.ar[i][j].type;
            g.ar[i][j].currentsize=0;
            g.ar[i][j].player=0;
            g.ar[i][j].color="";
            if(s.equals("c"))
            {
                if(i==0 && j==0)
                {
                    g.ar[i+1][j].currentsize+=1;
                    g.ar[i+1][j].color=color;
                    g.ar[i+1][j].player = player_no;
                    insertball(i+1,j,g.ar[i+1][j].color);
                    g.ar[i][j+1].currentsize+=1;
                    g.ar[i][j+1].color=color;
                    g.ar[i][j+1].player = player_no;
                    insertball(i,j+1,g.ar[i][j+1].color);
                    func(i+1,j,color,player_no);
                    func(i,j+1,color,player_no);
                }
                else if(i==0 && j==g.col-1)
                {
                    g.ar[i+1][j].currentsize+=1;
                    g.ar[i+1][j].color=color;
                    g.ar[i+1][j].player = player_no;
                    insertball(i+1,j,g.ar[i+1][j].color);
                    g.ar[i][j-1].currentsize+=1;
                    g.ar[i][j-1].color=color;
                    g.ar[i][j-1].player = player_no;
                   
                    insertball(i,j-1,g.ar[i][j-1].color);
                    func(i+1,j,color,player_no);
                    func(i,j-1,color,player_no);
                }
                else if(i==g.row-1 && j==0)
                {
                    g.ar[i-1][j].currentsize+=1;
                    g.ar[i-1][j].color=color;
                    g.ar[i-1][j].player = player_no;
                   
                    insertball(i-1,j,g.ar[i-1][j].color);
                    g.ar[i][j+1].currentsize+=1;
                    g.ar[i][j+1].color=color;
                    g.ar[i][j+1].player = player_no;
                
                    insertball(i,j+1,g.ar[i][j+1].color);
                    func(i-1,j,color,player_no);
                    func(i,j+1,color,player_no);

                }
                else if(i==g.row-1 && j==g.col-1)
                {
                    g.ar[i-1][j].currentsize+=1;
                    g.ar[i-1][j].color=color;
                    g.ar[i-1][j].player = player_no;
                 
                    insertball(i-1,j,g.ar[i-1][j].color);
                    g.ar[i][j-1].currentsize+=1;
                    g.ar[i][j-1].color=color;
                    g.ar[i][j-1].player = player_no;
                     insertball(i,j-1,g.ar[i][j-1].color);
                    func(i-1,j,color,player_no);
                    func(i,j-1,color,player_no);
                }

            }
            else if(s.equals("f"))
            {
                if(i==0)
                {
                    g.ar[i+1][j].currentsize+=1;
                    g.ar[i+1][j].color=color;
                    g.ar[i+1][j].player = player_no;
              
                    insertball(i+1,j,g.ar[i+1][j].color);
                    g.ar[i][j-1].currentsize+=1;
                    g.ar[i][j-1].color=color;
                    g.ar[i][j-1].player = player_no;
               
                    insertball(i,j-1,g.ar[i][j-1].color);
                    g.ar[i][j+1].currentsize+=1;
                    g.ar[i][j+1].color=color;
                    g.ar[i][j+1].player = player_no;
                   
                    insertball(i,j+1,g.ar[i][j+1].color);
                    func(i+1,j,color,player_no);
                    func(i,j-1,color,player_no);
                    func(i,j+1,color,player_no);    
                }
                else if(i==g.row-1)
                {
                    g.ar[i-1][j].currentsize+=1;
                    g.ar[i-1][j].color=color;
                    g.ar[i-1][j].player = player_no;
            
                    insertball(i-1,j,g.ar[i-1][j].color);
                    g.ar[i][j-1].currentsize+=1;
                    g.ar[i][j-1].color=color;
                    g.ar[i][j-1].player = player_no;
                  
                    insertball(i,j-1,g.ar[i][j-1].color);
                    g.ar[i][j+1].currentsize+=1;
                    g.ar[i][j+1].color=color;
                    g.ar[i][j+1].player = player_no;
                 
                    insertball(i,j+1,g.ar[i][j+1].color);
                    func(i-1,j,color,player_no);
                    func(i,j-1,color,player_no);
                    func(i,j+1,color,player_no);    
                }
                else if( j==0 )
                {
                    g.ar[i+1][j].currentsize+=1;
                    g.ar[i+1][j].color=color;
                    g.ar[i+1][j].player = player_no;
              
                    insertball(i+1,j,g.ar[i+1][j].color);
                    g.ar[i-1][j].currentsize+=1;
                    g.ar[i-1][j].color=color;
                    g.ar[i-1][j].player = player_no;
                 
                    insertball(i-1,j,g.ar[i-1][j].color);
                    g.ar[i][j+1].currentsize+=1;
                    g.ar[i][j+1].color=color;
                    g.ar[i][j+1].player = player_no;
                
                    insertball(i,j+1,g.ar[i][j+1].color);
                    func(i+1,j,color,player_no);
                    func(i-1,j,color,player_no);
                    func(i,j+1,color,player_no);
                }
                else
                {
                    g.ar[i+1][j].currentsize+=1;
                    g.ar[i+1][j].color=color;
                    g.ar[i+1][j].player = player_no;
                 
                    insertball(i+1,j,g.ar[i+1][j].color);
                    g.ar[i-1][j].currentsize+=1;
                    g.ar[i-1][j].color=color;
                    g.ar[i-1][j].player = player_no;
                 
                    insertball(i-1,j,g.ar[i-1][j].color);
                    g.ar[i][j-1].currentsize+=1;
                    g.ar[i][j-1].color=color;
                    g.ar[i][j-1].player = player_no;
                
                    insertball(i,j-1,g.ar[i][j-1].color);
                    func(i+1,j,color,player_no);
                    func(i-1,j,color,player_no);
                    func(i,j-1,color,player_no);

                }
            }
            else
            {
                g.ar[i+1][j].currentsize+=1;
                g.ar[i+1][j].color=color;
                g.ar[i+1][j].player = player_no;
            
                insertball(i+1,j,g.ar[i+1][j].color);
                g.ar[i-1][j].currentsize+=1;
                g.ar[i-1][j].color=color;
                g.ar[i-1][j].player = player_no;
            
                insertball(i-1,j,g.ar[i-1][j].color);
                g.ar[i][j-1].currentsize+=1;
                g.ar[i][j-1].color=color;
                g.ar[i][j-1].player = player_no;
                insertball(i,j-1,g.ar[i][j-1].color);
                g.ar[i][j+1].currentsize+=1;
                g.ar[i][j+1].color=color;
                g.ar[i][j+1].player = player_no;
                insertball(i,j+1,g.ar[i][j+1].color);
                func(i+1,j,color,player_no);
                func(i-1,j,color,player_no);
                func(i,j-1,color,player_no);
                func(i,j+1,color,player_no);
            }
        }
    }
    /**
   * This method is event listener to the event of mouseclicking
   *@param event is just one parameter of handle function
   *
   */
    @Override 
    public void handle(MouseEvent event) { 
        Node source = (Node) event.getSource();
        int x = GridPane.getRowIndex(source);
        int y = GridPane.getColumnIndex(source);
        makecopy(g.cur,1);
        insert(x,y); 
        print();
        System.out.println(g.counter + " 21");
        System.out.println(g.cur.counter + " 31");
          
    }
 /**
   * This method is printing the game status on the console to check the backend 
   *side of the game.
   *
   */ 
    
    public void print()
    {
        for(int i=0;i<g.row;i++)
        {
            for(int j=0;j<g.col;j++)
            {
                System.out.print(g.ar[i][j].currentsize+"T"+g.ar[i][j].player + " ");
            }
            System.out.println();
        }
    }      
}
