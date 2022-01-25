import java.io.*;
import java.net.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.geometry.*;
	import javafx.scene.text.*;
import javafx.scene.*;

/*
class Bt extends Button {
	public int x;
	public int y;

}
*/
class myButton extends Button {
	 public  int i; // Index. 
	myButton(String tmp, ImageView lst,int i){
		super(tmp,lst);
		this.i = i;
	}
}
public class j extends Application {
	int b=0; // The state of the board. 
	public myButton imgButton(String in, int i){
		ImageView image = new ImageView(in);
		myButton bt = new myButton("",image,i);
		bt.setOnAction(e -> System.out.println("Clicked " + bt.i + "."));
		return bt;
	}
	@Override
	public void start(Stage primaryStage) {
		//Stage stage = new Stage();
		//stage.show();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		for (int i=0;i<9;++i)
		{
			pane.add(imgButton("blank.png",i),i%3,i/3);
		}
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
		new Thread(() -> {
		// Have to use new thread. 
			try {
				System.out.println("Hallo, moto!");
				int port = 8000;
				DataInputStream in;
				DataOutputStream out;
				ServerSocket server;
				Socket socket;
				server = new ServerSocket(port);
				socket = server.accept();
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				while (true)
				{
					System.out.println(in.readInt());
					out.writeInt(17);
				}
			}
			catch(IOException ex){
				System.out.println("Error.");
				System.out.println(ex);
			}
		}).start();
	}
	public static void main(String[] args){
		System.out.println("Hello, world!");
		Application.launch(args);
	}
}
