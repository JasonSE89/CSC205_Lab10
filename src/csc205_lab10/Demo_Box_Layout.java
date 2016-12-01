package csc205_lab10;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Random;

public class Demo_Box_Layout extends Application {

    static int windowWidth = 900;
    static int windowHeight = 500;

    Random randomGenerator = new Random( 314159 );

    Font fontBoldItalic = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
    Font fontBold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
    Font fontItalic = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20);
    Font fontNormal = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 20);

    Text message;
    CheckBox chkBold;
    CheckBox chkItalic;
    ComboBox<String> messageSelector;
    TextField tf;                         /** text field where user can enter any input they wish */

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        HBox box = new HBox (25);   /** items will be placed in the box, left-to-right, with no wrapping */

        //VBox box = new VBox (25);   /** items will be placed in the box, top-to-bottom, with no wrapping */

        box.setPadding( new Insets( 10, 20, 5, 50 ));  /** padding for top, left, bottom, right */
                               
        message = new Text("Hello World");
        message.setFont(fontNormal);
        box.getChildren().add( message );

        chkBold = new CheckBox("Bold");          /** checkboxes to control the appearance of the Message */
        chkItalic = new CheckBox("Italic");
        CheckBoxHandler checkHandler = new CheckBoxHandler();
        chkBold.setOnAction(checkHandler);
        chkItalic.setOnAction(checkHandler);
        box.getChildren().addAll(chkBold, chkItalic);

        Button colorButton = new Button("Change Color");
        colorButton.setStyle( "-fx-border-color: red" );
        box.getChildren().add( colorButton );

        colorButton.setOnAction( new ButtonHandler() );  // register the handling code to this Button object

        /** ComboBox allows a selection of one item from a collection of items */
        messageSelector = new ComboBox<String>(); 
        String[] countryChoices = { "USA", "Canada", "Mexico", "Cuba" };
        messageSelector.getItems().addAll( countryChoices );
        messageSelector.setValue( countryChoices[1] );    /** indicate which value to initially displayed */
        messageSelector.setOnAction( new ComboBoxHandler() );
        box.getChildren().add( messageSelector );

        /** Include a TextField so the user can display any Country he/she wants */
        tf = new TextField();
        tf.setOnAction( new TextFieldHandler() );

        Label lbl = new Label ( "Enter Country Name:", tf );
        lbl.setContentDisplay(ContentDisplay.BOTTOM);
        lbl.setTextFill(Color.BLUE);
        lbl.setStyle("-fx-border-color: gold; fx-border-width: 5");

        box.getChildren().add( lbl );

        // Create a scene and place it in the stage
        Scene scene = new Scene( box, windowWidth, windowHeight );  
        primaryStage.setTitle("HBox and VBox Demo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private class TextFieldHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            message.setText( "Hello " + tf.getText() );
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