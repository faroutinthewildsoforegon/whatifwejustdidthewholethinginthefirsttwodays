import java.io.*;
import java.net.*;
//import javafx.application.Application;
import java.util.Scanner;

public class c {
	static int B=0;
	static void showBoard(int b){
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
	public static void main(String[] args){
		try{
			System.out.println("Hello, world!");
			int port = 8000;
			String host = "localhost";
			DataInputStream in;
			DataOutputStream out;
			Socket socket;
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			Scanner inp = new Scanner(System.in);
			while (true)
			{
				//System.out.println(in.readInt());
				B = in.readInt();
				showBoard(B);
				out.writeInt(B);
			}
		}
		catch(IOException ex){
			System.out.println("Error.");
		}
	}
}
