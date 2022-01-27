/*
//import javafx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class j extends Application {
	int b=0;
	//public void start(Stage primaryStage) {
	public BorderPane TEST() {
		Pane pane = new HBox(10);
		Button bt = new Button("Center",new ImageView("png.png"));
		pane.getChildren().addAll(bt);
		//pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-border-color: green");

		BorderPane pain = new BorderPane();
		pain.setBottom(pane);

		Pane payne = new Pane();
		//pane.setCenter(payne);

		//bt.setOnAction(e -> text.setY(text.getY() + 10));
		bt.setOnAction(e -> System.out.println("It worked."));
		return pain;
	}
	public Button imgButton(String in){
		//Button bt = new Button("",new ImageView(in));
		ImageView image = new ImageView(in);
		Button bt = new Button("",image);
		//bt = new Button("",new ImageView("flop.png"));
		//bt.setOnAction(e -> System.out.println("It worked."));
		//bt.setOnAction(e -> bt = new Button("",new ImageView("flop.png")));
		bt.setOnAction(e -> flip(image));
		return bt;
	}
	public final void flip(ImageView in){
		System.out.println("We tried.");
		//in = new Button("",new ImageView("flop.png"));
		if ((b++)%2==0)
			in.setImage(new Image("flop.png"));
		else
			in.setImage(new Image("flip.png"));
	}
	public final void flop(){
		System.out.println("Flop.");
	}
	@Override
	public void start(Stage primaryStage) {
		//Scene scene = new Scene(TEST(),450,200);
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		//pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		//pane.add(new Label("First Name:"), 0, 0);
		pane.add(imgButton("png.png"), 0, 0);
		pane.add(imgButton("png.png"), 0, 1);
		pane.add(imgButton("png.png"), 0, 2);
		pane.add(imgButton("png.png"), 1, 0);
		pane.add(imgButton("png.png"), 1, 1);
		pane.add(imgButton("png.png"), 1, 2);
		pane.add(imgButton("png.png"), 2, 0);
		pane.add(imgButton("png.png"), 2, 1);
		pane.add(imgButton("png.png"), 2, 2);
		/*
		pane.add(new Button("",new ImageView("png.png")), 0, 0);
		pane.add(new Button("",new ImageView("png.png")), 0, 1);
		pane.add(new Button("",new ImageView("png.png")), 0, 2);
		pane.add(new Button("",new ImageView("png.png")), 1, 0);
		pane.add(new Button("",new ImageView("png.png")), 1, 1);
		pane.add(new Button("",new ImageView("png.png")), 1, 2);
		pane.add(new Button("",new ImageView("png.png")), 2, 0);
		pane.add(new Button("",new ImageView("png.png")), 2, 1);
		pane.add(new Button("",new ImageView("png.png")), 2, 2);
		*/
		/*
		pane.add(new TextField(), 1, 0);
		pane.add(new Label("MI:"), 0, 1);
		pane.add(new TextField(), 1, 1);
		pane.add(new Label("Last Name:"), 0, 2);
		pane.add(new TextField(), 1, 2);
		Button btAdd = new Button("Add Name");
		pane.add(btAdd, 1, 3);
		GridPane.setHalignment(btAdd, HPos.RIGHT);
		*/

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Show image.");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
		/*
		Button btOK = new Button("OK");
		Scene scene = new Scene(btOK, 200, 250);
		primaryStage.setTitle("MyJavaFX");
		primaryStage.setScene(scene);
		primaryStage.show();

		Stage stage = new Stage();
		stage.setTitle("Second stage.");
		stage.setScene(new Scene(new Button("New Stage!"), 100, 100));
		stage.show();
		System.out.println("Hello, world!");
		*/

	public static void main(String[] args) {
		Application.launch(args);
	}
}
