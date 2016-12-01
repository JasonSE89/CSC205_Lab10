package csc205_lab10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Random;

public class Demo_BorderPane extends Application {

    static int windowWidth = 700;
    static int windowHeight = 600;
    
    Random randomGenerator = new Random( 314159 );
    
    Font fontBoldItalic = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50);
    Font fontBold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 50);
    Font fontItalic = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 50);
    Font fontNormal = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 50);

    Text message;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

    	/** items will be placed in one of 5 regions */
        BorderPane borderpane = new BorderPane();  
        
        Text topMessage = new Text( "This is the Top Region of the Border Pane");
        borderpane.setTop( topMessage );     
        BorderPane.setAlignment( topMessage, Pos.CENTER );
        BorderPane.setMargin( topMessage, new Insets (5, 5, 5, 5) );
                              
        message = new Text("Hello World");
        message.setFont( fontNormal );
        borderpane.setCenter( message );    

        /** this button will change the color of the message displayed in the center */
        Button colorButton = new Button("Change Color");
        colorButton.setStyle( "-fx-border-color: red" );
        colorButton.setOnAction( new ButtonHandler() );  /** register the handling code to this Button object */
        BorderPane.setAlignment( colorButton, Pos.CENTER );
        colorButton.setPadding( new Insets( 5, 5, 5, 5 )); 
        borderpane.setRight( colorButton );      
        
        Text leftMessage = new Text( "This is the Left Region" );
        borderpane.setLeft( leftMessage );     
        BorderPane.setAlignment( leftMessage, Pos.CENTER );
        BorderPane.setMargin( leftMessage, new Insets (5, 5, 5, 5) );

        Text bottomMessage = new Text( "This is the Bottom Region" );
        borderpane.setBottom( bottomMessage );     
        BorderPane.setAlignment( bottomMessage, Pos.CENTER );
        BorderPane.setMargin( bottomMessage, new Insets (5, 5, 5, 5) );
          
        // Create a scene and place it in the stage
        Scene scene = new Scene( borderpane, windowWidth, windowHeight );  
        primaryStage.setTitle("Border Pane Demo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    
   
    /** change the message to be a new, random color */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            
            int red = randomGenerator.nextInt( 255 );
            int blue = randomGenerator.nextInt( 255 );
            int green = randomGenerator.nextInt( 255 );       
            Color randomColor = Color.rgb( red, blue, green, 1.0 );
            
            message.setFill ( randomColor );
        }
    }

    
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}