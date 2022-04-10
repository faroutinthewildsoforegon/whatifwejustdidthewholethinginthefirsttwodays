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
