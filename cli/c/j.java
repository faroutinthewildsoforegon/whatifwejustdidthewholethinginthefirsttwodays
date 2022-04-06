import java.io.*;
import java.net.*;

class Client{
	Socket socket;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	Client(String rename_this, int port){
		try{
			this.socket = new Socket(rename_this,port);
			System.out.println("Connection established.");
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch (IOException e){
			System.out.println("Error creating server.");
			e.printStackTrace();
			return;
			//System.exit(1);
			//closeEverything();
		}
	}
	void send(String in){
		try{
			bufferedWriter.write(in);
			bufferedWriter.newLine(); // Why do we need this? 
			bufferedWriter.flush(); // Think I know why this. 
			//System.out.println(bufferedReader.readLine());
		}
		catch (IOException e){
			System.out.println("Error in server sending message.");
			return;
		}
	}
	void recv(){
		try{
			String msg = bufferedReader.readLine();
			if (msg == null){
				System.out.println("Nyah.");
				return;
			}
			else
				System.out.println(msg);
			//System.out.println(bufferedReader.readLine()); // Fix while(true)print(null); error. 
			
		}
		catch (IOException e){
			System.out.println("Error in client receiving message.");
			return;
			//return;
		}
	}
}

class sendLoop extends Thread{
	//@Override
	Client lnt;
	public void run(){
		java.util.Scanner in = new java.util.Scanner(System.in);
		while (true){
			lnt.send(in.nextLine());
		}
	}
	sendLoop(Client lnt){
		this.lnt = lnt;
		this.start();
	}
}
class recvLoop extends Thread{
	Client lnt;
	//@Override
	public void run(){
		while (true){
			lnt.recv();
		}
	}
	recvLoop(Client lnt){
		this.lnt = lnt;
		this.start();
	}
}
public class j{
public static void main(String[] args){
	Client lnt = new Client("localhost",6969);
	System.out.println("Hello, world!");
	sendLoop hack1 = new sendLoop(lnt);
	recvLoop hack2 = new recvLoop(lnt);
	/*
	java.util.Scanner in = new java.util.Scanner(System.in);
	while (true){
		//lnt.recv();
		lnt.send(in.nextLine());
	}
	*/
}}
