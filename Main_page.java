import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.Scene; 
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button; 
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;
import javafx.scene.control.*; 
import javafx.scene.input.MouseEvent;
import java.io.*;
/**
*<h1>Main page </h1>
*<p> 
*This is the main page of the game where user gets to decide the grid of the game
*and the number of players that are going to play the game.
*The start button redirects to the game page where the game actually happens   
*and the settings button redirects to the setting page.
* @author Aman Roy (2016011), Suyash Singh(2016105)
* @version 1.0
* @since 2017-10-20
 */
public class Main_page extends Application implements Serializable {
	int row , col;
	Game_page2 game;
    Settings_page s;
    int num_players;
    static Main_page m ;
    /**
   * This method is creating the Main page and contains all the GUI components
   *including the functionality of each button and other components
   *@param primaryStage is the only parameter.
   *
   */ 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chain Reaction");	
        Text playernum = new Text("Number of Players : ");
        ChoiceBox playerchoiceBox = new ChoiceBox();
        playerchoiceBox.getItems().addAll(2,3,4,5,6,7,8);        
        playerchoiceBox.getSelectionModel().select(0);
        System.out.println(playerchoiceBox.getValue());
        Text Gridsize = new Text("Size of the grid: ");
        ToggleGroup groupGrid = new ToggleGroup();
        RadioButton _9X6 = new RadioButton("9X6");
        _9X6.setToggleGroup(groupGrid);
        RadioButton _15X10 = new RadioButton("15X10");
        _15X10.setToggleGroup(groupGrid);
        _9X6.setSelected(true);
        Text chainrxn = new Text("CHAIN REACTION");
        chainrxn.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        Button start=new Button("START");
        start.setOnMouseClicked(new EventHandler<MouseEvent>(){
        	public void handle (MouseEvent event)
        	{
        		int n = (int) playerchoiceBox.getValue();
        		RadioButton temp = (RadioButton) groupGrid.getSelectedToggle();
        		String s = temp.getText();
        		if(s.equals("9X6"))
        		{
        			row = 9;
        			col = 6;
        		}
        		else
        		{
        			row = 15;
        			col = 10;
        		}
        		System.out.println(row + " " + col + " " + n);
        		game = new Game_page2(row , col , n , m , m.s.color);
        		game.start(primaryStage);
        	}
        });
        Button resume=new Button("RESUME");
        resume.setOnMouseClicked(new EventHandler<MouseEvent>(){
        	public void handle (MouseEvent event)
        	{
        		System.out.println(-1);
        	}
        });
        Button settings=new Button("SETTINGS");
        settings.setOnMouseClicked(new EventHandler<MouseEvent>(){
        	public void handle (MouseEvent event)
        	{
        		m.s.start(primaryStage);
        	}
        });
        GridPane root = new GridPane(); 
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setVgap(5); 
        root.setHgap(5);     
        root.add(playernum,1,0);
        root.add(playerchoiceBox,2,0);
        root.add(Gridsize,1,1);
        root.add(_9X6,2,1);
        root.add(_15X10,3,1);
        root.add(chainrxn,1,2);
        root.add(start,1,3);
        root.add(resume,2,3);
        root.add(settings,1,4);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root,1000,1000);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    /**
   * This is the main method launching the start method
   *@param args Unused.
   *
   */ 
    public static void main(String[] args) {
    	m = new Main_page();
        m.s = new Settings_page(m);
        launch(args);
    }
    
}
