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