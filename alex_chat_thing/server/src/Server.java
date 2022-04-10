import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

class Server extends Thread {
    AtomicBoolean RUNNING = new AtomicBoolean(true); // Should give up on this and find another way.
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    ArrayList<Server> lst; // The list of all servers.
    Server(ServerSocket serverSocket,Socket socket,ArrayList<Server> lst){
        this.serverSocket = serverSocket;
        this.socket = socket;
        this.lst = lst; // Important line!
        System.out.println("Connection established.");
        try{
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e){
            System.out.println("Error spawning reader and writer.");
            return; // WATCH OUT.  // Maybe it would be better to throw an error?
        }
        lst.add(this);
        this.start(); // Should this be _here_?!
    }
    @Override
    public void run(){
        while (RUNNING.get()){
            try{
                String in = bufferedReader.readLine();
                //this.sendToAll(in); // Unfolded.
                for (var srv : lst)
                    srv.send(in);
            }
            catch (/*IO*/Exception e){
                System.out.println("Error in server.run sending message.");
                this.close();
                return;
            }
            //System.out.println("SCREAMING.");
            System.out.println("A message was sent.");
        }
    }
    void send(String in){
        try{
            bufferedWriter.write(in);
            bufferedWriter.newLine(); // Why do we need this?
            bufferedWriter.flush(); // Think I know why this.
        }
        catch (Exception e){
            System.out.println("Error in server.send sending message.");
            this.close();
            return;
        }
    }
    void close(){
        System.out.println("Server.close() run.");
        System.out.println("Client probably closed- deleting this particular _Server_.");
        RUNNING.set(false);
    }
}