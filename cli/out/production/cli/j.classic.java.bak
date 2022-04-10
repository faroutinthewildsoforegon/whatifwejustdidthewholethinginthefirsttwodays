import java.io.*;
import java.net.*;
import java.util.*;

class sendLoop extends Thread{
	//@Override
	Server srv;
	public void run(){
		java.util.Scanner in = new java.util.Scanner(System.in);
		while (true){
			srv.send(in.nextLine());
		}
	}
	sendLoop(Server srv){
		this.srv = srv;
		this.start();
	}
}
class recvLoop extends Thread{
	Server srv;
	//@Override
	public void run(){
		while (true){
			srv.recv();
		}
	}
	recvLoop(Server srv){
		this.srv = srv;
		this.start();
	}
}

class Server extends Thread{
	ServerSocket serverSocket;
	Socket socket;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	ArrayList<Server> lst;
	//Server(int port){
	@Override
	public void run(){
		while (true){
			try{
				//System.out.println(bufferedReader.readLine()); // Fix while(true)print(null); error. 
				String in = bufferedReader.readLine();
				this.sendToAll(in);
			}
			catch (IOException e){
				System.out.println("Error in server receiving message.");
			}
			System.out.println("SCREAMING.");
		}
	}
	Server(ServerSocket serverSocket,Socket socket,ArrayList<Server> lst){
			this.lst = lst; // Important line! 
			//this.serverSocket = new ServerSocket(port);
			//this.socket = serverSocket.accept();
			this.serverSocket = serverSocket;
			this.socket = socket;
			System.out.println("Connection established.");
		try{
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch (IOException e){
			System.out.println("Error spawning reader and writer.");
			return; // WATCH OUT. 
		}
		this.start(); // Should this be here?! 
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
			System.out.println(bufferedReader.readLine()); // Fix while(true)print(null); error. 
		}
		catch (IOException e){
			System.out.println("Error in client receiving message.");
		}
	}
	void sendToAll(String in){
		for (var srv : lst)
			srv.send(in);
	}
}

public class j{
public static void main(String[] args){
	ArrayList<Server> serverList = new ArrayList<>();
	while (true){
		try (ServerSocket serverSocket = new ServerSocket(6969)){
			Socket socket = serverSocket.accept();
			Server srv = new Server(serverSocket,socket,serverList);
			serverList.add(srv);
			System.out.println("Spawned a new server.");
		}
		catch (Exception e){
			System.out.println("Error in server spawning function.");
			System.out.println(e.getStackTrace()); // `e.getStackTrace` or just `e`? 
		}
	}
	/*
	Server srv = new Server(6969);
	System.out.println("Hello, world!");
	sendLoop hack1 = new sendLoop(srv);
	recvLoop hack2 = new recvLoop(srv);
	*/
}}
