public class main{
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
