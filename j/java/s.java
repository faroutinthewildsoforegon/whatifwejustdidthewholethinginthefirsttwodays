import java.io.*;
import java.net.*;
import java.util.Scanner;
public class s {
	public static void main(String[] args){
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
			Scanner inp = new Scanner(System.in);
			//while (true)
			{
				out.writeInt(inp.nextInt());
				System.out.println(in.readInt());
			}
		}
		catch(IOException ex){
			System.out.println("Error.");
			System.out.println(ex);
		}
	}
}
