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

public class j extends Application {
	//public void start(Stage primaryStage) {
	public BorderPane TEST() {
		Pane pane = new HBox(10);
		Button bt = new Button("Center",new ImageView("png.png"));
		pane.getChildren().addAll(bt);
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-border-color: green");

		BorderPane pain = new BorderPane();
		pain.setBottom(pane);

		Pane payne = new Pane();
		//pane.setCenter(payne);

		//bt.setOnAction(e -> text.setY(text.getY() + 10));
		bt.setOnAction(e -> System.out.println("It worked."));
		return pain;
	}
	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(TEST(),450,200);
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
