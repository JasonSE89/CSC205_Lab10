package csc205_lab10;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Demo_GUI_Components extends Application {

    static int windowWidth = 400;
    static int windowHeight = 500;
    
    Font fontBoldItalic = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
    Font fontBold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
    Font fontItalic = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20);
    Font fontNormal = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 20);

    Text message;
    CheckBox chkBold;
    CheckBox chkItalic;
    
    ComboBox<String> messageSelector;
    
    TextField inputBox;   /** text field where user can enter any input they wish */

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Pane pane = new Pane();    /** Container for all the Window elements */
        
        message = new Text(50, 50, "Hello World");
        message.setFont(fontNormal);
        pane.getChildren().add( message );
        message.relocate( 25, 25 );

        chkBold = new CheckBox("Bold");          /** checkboxes to control the appearance of the Message */
        chkItalic = new CheckBox("Italic");
        chkBold.relocate ( 100, 100 );
        chkItalic.relocate ( 100, 150 ); 
        CheckBoxHandler checkHandler = new CheckBoxHandler();
        chkBold.setOnAction(checkHandler);
        chkItalic.setOnAction(checkHandler);
        pane.getChildren().addAll(chkBold, chkItalic);

        Button shiftRight = new Button("Shift Right");
        shiftRight.setStyle( "-fx-border-color: red" ); 
        shiftRight.relocate ( 100, 200 );    /** position the button in the Pane */
        pane.getChildren().add( shiftRight );

        shiftRight.setOnAction( new ButtonHandler() );  // register the handling code to this Button object

        /** ComboBox allows a selection of one item from a collection of items */
        messageSelector = new ComboBox<String>(); 
        messageSelector.relocate ( 100, 300 );    /** position the comboBox in the Pane */
        String[] countryChoices = { "USA", "Canada", "Mexico", "Cuba" };
        messageSelector.getItems().addAll( countryChoices );
        messageSelector.setValue( countryChoices[1] );    /** indicate which value to initially displayed */
        messageSelector.setOnAction( new ComboBoxHandler() );
        pane.getChildren().add( messageSelector );
        
        /** Include a TextField so the user can display any Country he/she wants */
        inputBox = new TextField();
        inputBox.setOnAction( new TextFieldHandler() );
        
        Label lbl = new Label ( "Enter Country Name:", inputBox );
        lbl.setContentDisplay( ContentDisplay.BOTTOM );   /** Textfield appears below the Labal */
        lbl.setTextFill( Color.BLUE );
        lbl.setStyle("-fx-border-color: gold; fx-border-width: 5");
        lbl.relocate ( 200, 300 );    /** position the Label in the Pane */
        
        pane.getChildren().add( lbl );
                
        // Create a scene and place it in the stage
        Scene scene = new Scene( pane, windowWidth, windowHeight );  
        primaryStage.setTitle("Label Demo"); // Set the stage title
        primaryStage.setScene(scene);        // Place the scene in the stage
        primaryStage.show();                 // Display the stage
    }
    
    
    private class TextFieldHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            
            message.setText( "Hello " + inputBox.getText() );
        }
    }
    
    private class ComboBoxHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            
            message.setText( "Hello " + messageSelector.getValue() );
        }
    }

    
    private class CheckBoxHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            if (chkBold.isSelected() && chkItalic.isSelected()) {
                message.setFont(fontBoldItalic); // Both check boxes checked
            }
            else if (chkBold.isSelected()) {
                message.setFont(fontBold); // The Bold check box checked
            }
            else if (chkItalic.isSelected()) {
                message.setFont(fontItalic); // The Italic check box checked
            }      
            else {
                message.setFont(fontNormal); // Both check boxes unchecked
            }

        }
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            
            message.setX( message.getX() + 25 ); /** move the Text to the right */
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