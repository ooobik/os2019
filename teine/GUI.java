package teine;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by alk_ on 10/25/16.
 * Cleaned also by alk on 10/30/19.
 * This code was written in 2016, back when I was young and foolish.
 */


public class GUI extends Application {
	//Default value is the 1st example, is set here
	static String sample = "A,2;B,3;A,-;C,4;D,5;B,-;E,15";


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
		grid.getColumnConstraints().add(new ColumnConstraints(500));

		vBox.getChildren().add(grid);

		Text t = new Text("Sisesta järjend");
		Text btnText1 = new Text("A,2;B,3;A,-;C,4;D,5;B,-;E,15");
		Text btnText2 = new Text("A,4;B,3;C,6;D,5;B,-;E,5;A,-;F,10");
		Text btnText3 = new Text("A,2;B,3;C,4;D,5;B,-;E,7;D,-;F,10;A,-;G,1;H,1;G,-;I,10;J,8;I,-");
		TextField btnText4 = new TextField("A,30;B,30");
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



		grid.getRowConstraints().add(new RowConstraints(30));
		grid.getRowConstraints().add(new RowConstraints(30));

		TextArea txtArea = new TextArea();
		txtArea.setMinSize(200,100);
		grid.add(txtArea,3,1);



		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		vBox.getChildren().add(hBox);

		Button algo1 = new Button("Kirjuta väljund");

		Button puhasta = new Button("Puhasta väljund");

		hBox.getChildren().addAll(algo1,puhasta);

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


		algo1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.generateDisplay(sample),txtArea),vBox,primaryStage);
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

		primaryStage.setTitle("Kodutöö2");
		primaryStage.setScene(scene);
		primaryStage.setHeight(600);
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
	 *Method takes some input to create the graphical output noted in the sample program pictures
	 *
	 * That input is formatted as an array like
	 * [[metadata, 'a','a','b'....],[metadata,"a"..]]
	 * metadata is the process description
	 */
	public static GridPane createNewPane(String[][] in,TextArea txtArea){

		GridPane grid = new GridPane();




		Text txt1 = new Text("Samm");
		txt1.setTextAlignment(TextAlignment.CENTER);
		grid.add(txt1,0,0);

		grid.getColumnConstraints().add(new ColumnConstraints(70));

		//Sets the column headers and width
		for (int i = 0; i < 50; i++) {
			Text tempText = new Text(Integer.toString(i));
			GridPane.setHalignment(tempText, HPos.CENTER);

			grid.add(tempText,i+1,0);
			grid.getColumnConstraints().add(new ColumnConstraints(20));

		}
		//The engine that creates individual rows, will also check if one of the functions threw a null
		//to indicate full memory
		int index = 1;
		for (String[] strings : in) {
			if(strings == null){
				grid.add(new Text("MÄLU SAI TÄIS, LÕPETAN TÖÖTAMISE"),0,index);
				return grid;
			}
			addRow(strings,grid, index++);


			int[] values = Functions.calculateFragmentation(strings);
			txtArea.setText("Allesjäänud failidest on fragmenteeritud " + values[0] + "% \nja need failid hõlmavad " + values[1] + "%");
		}



		return grid;
	}

	/**
	 * While the method above takes the whole stack of strings, this one takes just one line
	 * metadata format "data"
	 *
	 */

	public static GridPane addRow(String[] in,GridPane grid, int row){

		Color[] list = {Color.BLUE, Color.RED, Color.ORANGE, Color.CYAN, Color.PALEGREEN,
				Color.SALMON, Color.GREEN, Color.YELLOW, Color.LAVENDER, Color.PINK, Color.GRAY,Color.GRAY};
		List<String> chars = new ArrayList();

		String[] cs = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "-",null};
		chars = Arrays.asList(cs);


		Text txt1 = new Text("Samm: " + Integer.toString(row));



		txt1.setTextAlignment(TextAlignment.CENTER);

		grid.add(txt1,0,row);

		//Colors each rectangle as needed
		for (int i = 0; i < in.length; i++) {
			Rectangle rekt = new Rectangle(20,20,list[chars.indexOf(in[i])]);
			rekt.setStroke(Color.BLACK);
			Text text = new Text(in[i]);
			text.setTextAlignment(TextAlignment.CENTER);
			StackPane pane = new StackPane();
			pane.getChildren().addAll(rekt,text);

			grid.add(pane,i+1,row);
		}
		//Semi-legacy, as the functions i wrote automatically fill unused spaces with "-"
		if(in.length < 50){
			for (int i = 0; i < 50-in.length; i++) {
				Rectangle rekt = new Rectangle(20,20,Color.GREY);
				rekt.setStroke(Color.BLACK);
				Text text = new Text("-");
				StackPane pane = new StackPane();
				pane.getChildren().addAll(rekt,text);

				grid.add(pane,in.length+1+i,row);
			}
		}
		return grid;
	}
}
