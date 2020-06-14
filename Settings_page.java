import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.*;
/**
*<h1>Settings page </h1>
*<p> 
*page that changes the color of orb of each player
*it also has a back button which will return the user to the main page 
* @author Aman Roy (2016011), Suyash Singh(2016105)
* @version 1.0
* @since 2017-10-20
*/
public class Settings_page extends Application implements Serializable {
    
    boolean ar[];
    Main_page m;
    String color[];
    Settings_page(Main_page m)
    {
        this.m = m;
        this.color = new String[]{"RED","BLUE" , "GREEN" , "BLACK" ,"PINK" , "VIOLET" ,"ORANGE","YELLOW"};
    }
    /**
   * This method is creating the setting page and contains all the GUI components
   *including the functionality of each button and other components
   *@param primaryStage is the only parameter.
   *
   */ 
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Settings Page");	
        Text[] Player = new Text[8];
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        String [] c = new String[] {"RED","BLUE" , "GREEN" , "BLACK" ,"PINK" , "VIOLET" ,"ORANGE","YELLOW"};
        ChoiceBox [] choice = new ChoiceBox [8];
        for(int i=0;i<8;i++)
        {
            choice[i] = new ChoiceBox();
            choice[i].getItems().addAll("RED","BLUE" , "GREEN" , "BLACK" ,"PINK" , "VIOLET" ,"ORANGE","YELLOW");
            choice[i].getSelectionModel().select(i);
            Player[i] = new Text("Player "+(i+1));
            map.put(c[i],i);
        }
        Button mainpage=new Button("BACK");
        mainpage.setOnMouseClicked(new EventHandler<MouseEvent>(){
   
            public void handle (MouseEvent event)
   
            {
                m.start(primaryStage);
            }
        });
        Button save=new Button("SAVE");
        save.setOnMouseClicked(new EventHandler<MouseEvent>(){
     
            public void handle (MouseEvent event)
            {

                ar = new boolean[8];
                boolean temp = true;
                for(int i=0;i<8;i++)
                {
                    String s = (String) choice[i].getValue();
                    color[i] = s;
                    int j =(int) map.get(s);
                    System.out.println(j);
                    ar[j] = true;
                }
                for(int i=0;i<8;i++)
                {
                    if(!(temp && ar[i]))
                    {
                        temp = false;
                        break;
                    }
                }
                if(!temp)
                {
                    color = new String[]{"RED","BLUE" , "GREEN" , "BLACK" ,"PINK" , "VIOLET" ,"ORANGE","YELLOW"};
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setContentText("Invalid selection of choices");
                    alert.show();
                }
                else
                {
                    m.start(primaryStage);
                }
            }
        });
        GridPane root = new GridPane(); 
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setVgap(5); 
        root.setHgap(5);
        for(int i=0;i<8;i++)
        {
            root.add(Player[i],0,i);
            root.add(choice[i],1,i);
        }     
        root.add(mainpage,3,8);
        root.add(save,1,8);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   /**
   * This is the main method launching the start method
   *@param args Unused.
   *
   */ 
   public static void main(String[] args) {
        launch(args);
    }
    
}
