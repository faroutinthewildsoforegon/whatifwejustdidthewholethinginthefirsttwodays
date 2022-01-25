import java.io.*;
import java.net.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.geometry.*;
	import javafx.scene.text.*;

/*
class Bt extends Button {
	public int x;
	public int y;

}
*/
public class j extends Application {
	int b=0; // The state of the board. 
	@Override
	public void start(Stage primaryStage) {
		Stage stage = new Stage();
		try {
			System.out.println("Hello, world!");
			int port = 8000;
			DataInputStream in;
			DataOutputStream out;
			ServerSocket server;
			Socket socket;
			server = new ServerSocket(port);
			socket = server.accept();
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println(in.readInt());
			//out.writeInt(5);
			stage.show();
		}
		catch(IOException ex){
			System.out.println("Error.");
			System.out.println(ex);
		}
	}
	public static void main(String[] args){
		System.out.println("Hello, world!");
		Application.launch(args);
	}
}
