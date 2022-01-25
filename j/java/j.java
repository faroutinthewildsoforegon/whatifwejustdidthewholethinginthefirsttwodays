import java.io.*;
import java.net.*;
import javafx.application.*;
import javafx.stage.*;

public class j extends Application {
	@Override
	public void start(Stage primaryStage) {
		Stage stage = new Stage();
		stage.show();
	}
	public static void main(String[] args){
		Application.launch(args);
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
			out.writeInt(5);
		}
		catch(IOException ex){
			System.out.println("Error.");
		}
	}
}
