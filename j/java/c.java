import java.io.*;
import java.net.*;
//import javafx.application.Application;

public class c {
	public static void main(String[] args){
		try{
			System.out.println("Hello, world!");
			int port = 8000;
			String host = "localhost";
			DataInputStream in;
			DataOutputStream out;
			ServerSocket server;
			Socket socket;
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(7);
			System.out.println(in.readInt());
		}
		catch(IOException ex){
			System.out.println("Error.");
		}
	}
}
