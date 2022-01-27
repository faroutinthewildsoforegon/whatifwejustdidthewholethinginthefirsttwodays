public class s {
	public static void main(String[] args){
		new Thread(){
			@Override
			public void run(){
				String[] noArguments = new String[0];
				j.main(noArguments);
			}
		}.start();
		new Thread(){
			@Override
			public void run(){
				String[] noArguments = new String[0];
				c.main(noArguments);
			}
		}.start();
	}
}
