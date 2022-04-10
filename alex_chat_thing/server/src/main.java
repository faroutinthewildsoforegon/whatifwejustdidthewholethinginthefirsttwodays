import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class main{
public static void main(String[] args) {
        ArrayList<Server> serverList = new ArrayList<>(); // We should probably do a map of threads rather than a list of threads.
        while (true){
            try (ServerSocket serverSocket = new ServerSocket(6969)){
                Socket socket = serverSocket.accept();
                Server srv = new Server(serverSocket,socket,serverList);
                // serverList.add(srv); // Moved inside constructor.
                System.out.println("Spawned a new server.");
        }
        catch (Exception e){
            System.out.println("Error in server spawning function.");
            System.out.println(e.getStackTrace()); // `e.getStackTrace` or just `e`?
            }
        }
    }
};
