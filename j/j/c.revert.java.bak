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
		if (c.yourTurn)
			yeet(i);
		  else return; // Is this even necessary? 
	}
	void yeet(int i){
		System.out.println("Yeet.");
// I wonder if the compiler is smart enough to work this one out. 
		int tmp=1;
		for (int j=0;j<i;++j)
			tmp *= 3;
// I wonder if the compiler is smart enough to work this one out. 
		if (c.b/tmp%3==0)
			c.b += tmp*2;
		else
			System.out.println("At that location it's-" );
		c.yourTurn = false;
		c.showBoard(c.b);
	}
}
public class c extends Application {
	static boolean yourTurn = true;
	static int b=0; // The state of the board. 
	public myButton imgButton(String in, int i){
		ImageView image = new ImageView(in); // Move this within class? 
		 image.setPreserveRatio(true);
		 image.setFitHeight(200);
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
		 primaryStage.setTitle("c.java");
		primaryStage.setScene(new Scene(pane));
		System.out.println("Executing show.");
		primaryStage.show();
		System.out.println("Executed show.");
		//new Thread(() -> {while(true)System.out.println("b: " + b);});
		//new Thread(() -> {while(true)System.out.println("b: " + b);});
		new Thread(() -> {
		// Have to use new thread. 
			try {
				System.out.println("Hallo, moto!");
				int port = 8000;
				String host = "localhost";
				DataInputStream in;
				DataOutputStream out;
				Socket socket;
				socket = new Socket(host, port);
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				while (true)
				{
					out.writeInt(b);
					int tmp = in.readInt();
					yourTurn = (yourTurn || tmp != b); // TMHWW. 
					if (b > tmp) // DON'T TOUCH THIS. 
						yourTurn = false; // DON'T TOUCH THIS. 
					refreshBoard();
					//b = (tmp > b)? tmp : b;
					if (tmp > b)
						b = tmp;
					//,showBoard(b);
					/*
					if (b == 13 *2|| b == 13*27 *2|| b == 13*27*27  *2||  b == 757 *2|| b == 757*3 *2|| b == 757*3*3  *2||  b == (1+81+(729*9)) *2|| b == (9+81+729)*2)
					{
						System.out.println("You won!");
						System.exit(1);
					}
					*/
					CheckIfWon(2);
				}
			}
			catch(IOException ex){
				System.out.println("Error.");
				System.out.println("You know, that probably means you lost!");
				System.out.println(ex);
				  System.exit(2);
			}
		}).start();
	}
	void CheckIfWon(int in){
		if (checkIfWon(in))
			{
				System.out.println("You won!");
				System.exit(1);
			}
	}
	boolean checkIfWon(int in){
		int B = b;
		for (int i=1;i<=3;++i)
		{
			if (B%27 == 13*in)
				return true;
			B /= 27;
		}
		/*
		if (B%3 == in && (B/27)%3 == (B/(27*27))%3)// && (B/27)%3 == in)
			return true;
		*/
		B = b;
		for (int i=1;i<=9;i*=3)
		{
			if ((B%(i*3) == (B/27)%(i*3)) && (B/(27*27)%(i*3) == in) && (in == B%(i*3))) // This might break. 
				return true;
		}
		//                                                          Right diagonal \/. 
		if ((b%3 == (b/81)%3 && (b/6561)%3 == in && in == b%3) || ((b/9)%3 == (b/81)%3 && (b/729)%3 == in && in == (b/9)%3))
		// Left diagonal ^. 
			return true;
		// I'd really love to see what the compiler made out of this one. 
		return false;
	}
	public static void main(String[] args){
		System.out.println("Hello, world!");
		Application.launch(args);
	}
	public static void showBoard(int b){
		for (int i=1;i<=9;++i)
		{
			//char tmp = (char)((b/(i*3))%3);
			//byte tmp = (byte)(b/(i*3));
			byte tmp = (byte)(b%3);
			switch(tmp)
			{
				case 0:tmp = '#';break;
				case 1:tmp = 'X';break;
				case 2:tmp = 'O';break;
			}
			// Fix this. 
			System.out.print((char)tmp);
			if (i%3 == 0)
				System.out.println();
			// Fix this. 
			b /= 3;
		}
	}
}

