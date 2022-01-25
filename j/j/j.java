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

class myButton extends Button {
	int i; // Index. 
	ImageView thisImage;
	void setImage(String in){
		thisImage.setImage(new Image(in));
	}
	myButton(String tmp, ImageView lst,int i){
		super(tmp,lst);
		this.i = i;
		thisImage = lst;
	}
	void onClick(){
		if (j.yourTurn)
			yeet(i);
	}
	void yeet(int i){
// I wonder if the compiler is smart enough to work this one out. 
		int tmp=1;
		for (int j=0;j<i;++j)
			tmp *= 3;
// I wonder if the compiler is smart enough to work this one out. 
		if (j.b/tmp%3==0)
			j.b += tmp;
		else
			System.out.println("Slot taken.");
		///////j.yourTurn = false;
	}
}
public class j extends Application {
	static boolean yourTurn = true;
	static int b=0; // The state of the board. 
	public myButton imgButton(String in, int i){
		ImageView image = new ImageView(in); // Move this within class? 
		myButton bt = new myButton("",image,i);
		bt.setOnAction(e -> bt.onClick());
		return bt;
	}

	myButton[] myButtons = new myButton[9];

	void refreshBoard(){
		for (int i=0;i<9;++i)
		{
			int I = i+1;
			int tmp = 1;
			for (int j=0;j<i;++j)
				tmp *= 3;
			if ((b/tmp)%3 == 1)
				myButtons[i].setImage("flip.png");
			else if ((b/tmp)%3 == 2)
				myButtons[i].setImage("flop.png");
			//else continue;
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		System.out.println("In start.");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		for (int i=0;i<9;++i)
		{
			myButtons[i] = imgButton("blank.png",i);
			pane.add(myButtons[i],i%3,i/3);
		}
		primaryStage.setScene(new Scene(pane));
		System.out.println("Executing show.");
		primaryStage.show();
		System.out.println("Executed show.");
		//new Thread(() -> {while(true)System.out.println("b: " + b);});
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
					int tmp = in.readInt();
					//b = (tmp > b)? tmp : b;
					if (tmp > b)
						b = tmp;
					refreshBoard();
					out.writeInt(b);
					System.out.println(b);
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
