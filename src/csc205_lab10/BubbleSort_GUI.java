package csc205_lab10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;

public class BubbleSort_GUI extends Application {

	static int windowWidth = 1000;
	static int windowHeight = 700;
	Text name;
	boolean setGet = false;
	boolean smallData = true;
	ComboBox<String> setGetorAddRemove;
	ComboBox<String> structureChoice;
	TextField userData;
	TextArea results;
	Label enterData;
	Label resultField;
	CheckBox dataSize;
	Button execute;
	Button clear;
	DataStructureChoice choice = DataStructureChoice.CSC205_LINKED_LIST;
	String result = "";
	int amount;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		BorderPane bubbleSortGUI = new BorderPane();

		name = new Text("CSC 205 Lab 10 - GUI for BubbleSort Experiment by Jason Epstein ");
		bubbleSortGUI.setTop(name);
		BorderPane.setAlignment(name, Pos.CENTER);

		VBox leftSide = new VBox(25);
		leftSide.setPadding(new Insets(10, 20, 5, 50));
		setGetorAddRemove = new ComboBox<String>();
		setGetorAddRemove.setPadding(new Insets(5, 5, 5, 5));
		setGetorAddRemove.getItems().addAll("Use Set and Get", "Use Add and Remove");

		structureChoice = new ComboBox<String>();
		structureChoice.setPadding(new Insets(5, 5, 5, 5));
		structureChoice.getItems().addAll("Java ArrayList", "Java LinkedList", "CSC 205 LinkedList");

		setGetorAddRemove.setValue("Use Add and Remove");
		setGetorAddRemove.setOnAction(new setGetHandler());
		structureChoice.setValue("Use CSC205 LinkedList");
		structureChoice.setOnAction(new structureChoiceHandler());
		leftSide.getChildren().addAll(setGetorAddRemove, structureChoice);
		leftSide.setAlignment(Pos.CENTER_LEFT);
		bubbleSortGUI.setLeft(leftSide);

		userData = new TextField();
		userData.setOnAction(new TextFieldHandler());

		VBox rightSide = new VBox(25);
		rightSide.setPadding(new Insets(10, 20, 5, 50));
		dataSize = new CheckBox("Use Large Data");
		dataSize.setOnAction(new dataSizeHandler());
		dataSize.setPadding(new Insets(5, 5, 5, 5));
		enterData = new Label("Enter Amount Of Data", userData);
		enterData.setContentDisplay(ContentDisplay.BOTTOM);
		enterData.setTextFill(Color.BLUE);
		enterData.setPadding(new Insets(5, 5, 5, 5));
		enterData.setStyle("-fx-border-color: gold; fx-border-width: 5");

		rightSide.getChildren().addAll(dataSize, enterData);
		rightSide.setAlignment(Pos.CENTER_RIGHT);
		bubbleSortGUI.setRight(rightSide);
		BorderPane.setMargin(rightSide, new Insets(5, 5, 5, 5));

		BorderPane.setMargin(setGetorAddRemove, new Insets(5, 5, 5, 5));

		HBox bottomSide = new HBox(25);
		bottomSide.setPadding(new Insets(10, 20, 5, 50));

		execute = new Button("Run an Experiment");
		execute.setOnAction(new executeButtonHandler());
		execute.setStyle("-fx-border-color: red; fx-border-width: 5");
		clear = new Button("Clear Results Area");
		clear.setOnAction(new clearButtonHandler());
		clear.setStyle("-fx-border-color: red; fx-border-width: 5");

		bottomSide.getChildren().addAll(execute, clear);
		bottomSide.setAlignment(Pos.BOTTOM_CENTER);
		bubbleSortGUI.setBottom(bottomSide);

		results = new TextArea("Waiting For you to run your Experiments");
		results.setPrefHeight(400);
		results.setPrefWidth(400);
		results.setWrapText(true);
		resultField = new Label("The results of the Experiments", results);
		resultField.setTextFill(Color.BLUE);
		resultField.setMaxWidth(400);
		resultField.setWrapText(true);
		results.setStyle("-fx-border-color: gold; fx-border-width: 5");
		resultField.setContentDisplay(ContentDisplay.BOTTOM);

		bubbleSortGUI.setCenter(resultField);
		// Create a scene and place it in the stage
		Scene scene = new Scene(bubbleSortGUI, windowWidth, windowHeight);
		primaryStage.setTitle("GUI Front end for Bubblesort Experiment"); // Set
																			// the
																			// stage
																			// title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	private class TextFieldHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {

			amount = Integer.parseInt(userData.getText());
		}
	}

	private class executeButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			result = result + new BubbleSort(choice, setGet, smallData, amount).runExperiment();
			results.setText(result);
		}
	}

	private class clearButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			result = "";
			results.setText(result);
		}
	}

	private class setGetHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			if (setGetorAddRemove.getValue().equals("Use Set and Get")) {
				setGet = true;
			} else {
				setGet = false;
			}
		}
	}

	private class structureChoiceHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			if (structureChoice.getValue().equals("Java ArrayList")) {
				choice = DataStructureChoice.JAVA_ARRAY_LIST;
			} else if (structureChoice.getValue().equals("Java LinkedList")) {
				choice = DataStructureChoice.JAVA_LINKED_LIST;
			} else {
				choice = DataStructureChoice.CSC205_LINKED_LIST;
			}
		}
	}

	private class dataSizeHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			if (dataSize.isSelected()) {
				smallData = false;

			} else
				smallData = true;
		}
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support.
	 * Not needed for running from the command line.
	 */

	public static void main(String[] args) {
		launch(args);
	}
}