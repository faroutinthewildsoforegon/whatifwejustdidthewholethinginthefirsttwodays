import java.util.Scanner;

public class j{
static void d(String in){
	// For debugging. 
	System.out.print("DEBUG:\t");
	System.out.println(in);
	return;
}
public static void main(String[] args){
	d("Hello, world!");
	int b = 0;
	showBoard(b);
	Scanner in = new Scanner(System.in);
	int t = 1;
	while (true)
	{
		t %= 2;
		if (t==0)
			t = 2;
		String tmp = in.nextLine();
		/*
		if (tmp.charAt(2) == 'o')
			++b;
		*/
		switch(tmp.charAt(0))
		{
			//case 't':b = 1;
			case 'b':t *= 27;
			case 'm':t *= 27;
		}
		switch(tmp.charAt(1))
		{
			case 'r':t *= 3;
			case 'c':t *= 3;
			//case 'l':b *= 1;
		}
		//d("Variable b was " + b);
		//d("Variable t is " + t);
		b += t;
		//d("Variable b is " + b);
		showBoard(b);
		++t;
	}
	/*
	for (int i=1;i<729*3*3+1;i*=3)
		showBoard(i);
	*/
}
static void showBoard(int b){
	d("Value of b is " + b);
	for (int i=1;i<=9;++i)
	{
		//char tmp = (char)((b/(i*3))%3);
		//byte tmp = (byte)(b/(i*3));
		byte tmp = (byte)(b%3);
		switch(tmp)
		{
			case 0:tmp = '#';break;
			case 1:tmp = 'X';break;
			case 2:tmp = 'O';break;
		}
		// Fix this. 
		System.out.print((char)tmp);
		if (i%3 == 0)
			System.out.println();
		// Fix this. 
		b /= 3;
	}
}
}

//tlx:1
//tcx:3
//trx:9
//mlx:27
//mlx:27
