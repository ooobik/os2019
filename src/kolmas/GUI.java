package kolmas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Arrays;


/**
 * Created by alk_ on 10/25/16.
 * Cleaned and modified by alk on 19/12/04
 * This code was partially written in 2016, back when I was young and foolish.
 * Enter at own risk
 */

public class GUI extends Application {
	//Default value is the 1st example, is set here
	static String sample = "2,5,13,29,7,1,18,40,49,4";

	//All the buttons and the neccesary hooks to make the UI work
	@Override
	public void start(Stage primaryStage) {

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(0,10,0,10));

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		grid.getColumnConstraints().add(new ColumnConstraints(110));

		vBox.getChildren().add(grid);

		Text t = new Text("Vali või sisesta järjend");
		Text btnText1 = new Text("2,5,13,29,7,1,18,40,49,4");
		Text btnText2 = new Text("1,10,44,2,12,3,13,20");
		Text btnText3 = new Text("45,6,16,9,33,28,11,37,49,25");
		TextField btnText4 = new TextField("1,10,44,2,12,3,13,20");
		btnText4.setMinWidth(300);
		btnText4.setPadding(new Insets(5,0,5,0));

		final ToggleGroup group = new ToggleGroup();

		RadioButton btn = new RadioButton();
		RadioButton btn2 = new RadioButton();
		RadioButton btn3 = new RadioButton();
		RadioButton btn4 = new RadioButton();

		btn.setToggleGroup(group);
		btn2.setToggleGroup(group);
		btn3.setToggleGroup(group);
		btn4.setToggleGroup(group);
		btn.setSelected(true);

		btn.setText("Esimene");
		btn2.setText("Teine");
		btn3.setText("Kolmas");
		btn4.setText("Enda oma");

		grid.add(t,0,1);
		grid.add(btn,0,2);
		grid.add(btn2,0,3);
		grid.add(btn3,0,4);
		grid.add(btn4,0,5);

		grid.add(btnText1,1,2);
		grid.add(btnText2,1,3);
		grid.add(btnText3,1,4);
		grid.add(btnText4,1,5);


		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		vBox.getChildren().add(hBox);

		Button algo1 = new Button("FCFS");
		Button algo2 = new Button("SSTF");
		Button algo3 = new Button("LOOK");
		Button algo4 = new Button("SCAN");
		Button puhasta = new Button("Puhasta väljund");

		hBox.getChildren().addAll(algo1,algo2,algo3,algo4,puhasta);

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText1.getText();
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText2.getText();
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText3.getText();
			}
		});
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText4.getText();
			}
		});

		//FCFS
		algo1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(new GridPane(),vBox,primaryStage);
				update(createNewPane(Functions.FCFS(sample)),vBox,primaryStage);

			}

		});
		//SSTF
		algo2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(new GridPane(),vBox,primaryStage);
				//update(createNewPane(Functions.SSTF(sample)),vBox,primaryStage);
			}
		});
		//LOOK
		algo3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {

                update(new GridPane(),vBox,primaryStage);
                update(createNewPane(Functions.LOOK(sample)),vBox,primaryStage);
			}
		});
		//CSCAN
		algo4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
                update(new GridPane(),vBox,primaryStage);
                update(createNewPane(Functions.SCAN(sample)),vBox,primaryStage);
			}
		});

		puhasta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(new GridPane(),vBox,primaryStage);
			}
		});

		vBox.getChildren().add(new GridPane());
		Scene scene = new Scene(vBox, 300, 100);

		primaryStage.setTitle("Kodutöö3");
		primaryStage.setScene(scene);
		primaryStage.setHeight(500);
		primaryStage.setWidth(1200);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}


	//Method updates the UI with a new Gridpane that is used as the output field
	public static void update(GridPane newPane, VBox vBox, Stage primaryStage){
		vBox.getChildren().remove(vBox.getChildren().size()-1);
		vBox.getChildren().add(newPane);
		primaryStage.show();
	}

	/**
	 * Main drawing function - takes input in the format of int[]
	 * The first value in the array will be displayed as the total length of the algorithm
	 * The rest of the values in the array will be where the line is drawn between
	 * @param in Array of points where the head stops, with in[0] delimiting total length
	 * @return Gridpane to pass to canvas
	 */
	public static GridPane createNewPane(int[] in){

		GridPane grid = new GridPane();

		//Sets the column headers and width
		for (int i = 0; i < 50; i++) {
			Text tempText = new Text(Integer.toString(i));
			GridPane.setHalignment(tempText, HPos.CENTER);
			grid.add(tempText,i,0);
			grid.getColumnConstraints().add(new ColumnConstraints(20));
		}

		for (int i = 0; i < 50; i++) {
			Rectangle rekt = new Rectangle(20,20, Color.WHITE);
			rekt.setStroke(Color.BLACK);

			Text text = null;
			for (int i1 : Arrays.copyOf(in,in.length-1)) {
				if(i == i1) {
					text = new Text("X");
				}
			}
			if(text == null){
				text = new Text();
			}
			text.setTextAlignment(TextAlignment.CENTER);
			StackPane pane = new StackPane();
			pane.getChildren().addAll(rekt,text);

			grid.add(pane,i,2);
		}
 		return createGraph(in,grid);
	}

	public static GridPane createGraph(int[] in,GridPane grid){
		int columnW = 20;
		int gridLast = 0;
		//What is this spagetthi???? - alk@04.12.19
		for (int i = 1; i < in.length-1; i++) {
			Line l = new Line(0,0,(in[i+1]-in[i])*columnW,columnW);

			if(in[i] <= in[i+1]){
				grid.add(l,in[i],i+3);
			}else{
				grid.add(l,in[i+1],i+3);
			}
		}
		grid.add(new Text("Sumaarne pikkus: " + in[0]),0,20);
		grid.setAlignment(Pos.CENTER);
		return grid;
	}
}