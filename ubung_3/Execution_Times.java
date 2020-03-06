package ubung_3;

public class Execution_Times {

	public static void main(String[] args) {

		int a = 10;
		int b = 100;
		int c = 1000;
		int d = 10000;
		int e = 100000;

		fragment_1 (a);
		fragment_1 (b);
		fragment_1 (c);
		fragment_1 (d);
		fragment_1 (e);
		}
	
	public static void fragment_1(int n) {
		long sum = 0;
		for ( int i = 0; i < n; i ++)
		    sum++;
		System.out.println(sum); }
	
	
	public static void fragment_2(int n) {
		long sum = 0;
		for ( int i = 0; i < n; i ++)
		    for ( int j = 0; j < n; j ++)
		        sum++;
		System.out.println(sum);}
	
	
	public static void fragment_3(int n) {
		long sum = 0;
		for ( int i = 0; i < n; i ++)
		    for ( int j = i; j < n; j ++)
		        sum++;
		System.out.println(sum); }
	
	
	public static void fragment_4(int n) {
		long sum = 0;
		for ( int i = 0; i < n; i ++)
		    sum ++;
		    for ( int j = 0; j < n; j ++)
		        sum ++;
			System.out.println(sum);}
	
	
	public static void fragment_5(int n) {
		long sum = 0;
		for ( int i = 0; i < n; i ++)
		    for ( int j = 0; j < n*n; j ++)
		    sum++;
		System.out.println(sum);}
	
	
	public static void fragment_6(int n) {
		long sum = 0;
		for ( int i = 0; i < n; i ++)
		    for ( int j = 0; j < i; j ++)
		        sum++;
		System.out.println(sum);}
	
	
	public static void fragment_7(int n) {
		long sum = 0;
		for ( int i = 1; i < n; i ++)
		    for ( int j = 0; j < n*n; j ++)
		        if (j % i == 0)
		           for (int k = 0; k < j; k++)
		               sum++;
		System.out.println(sum);}
	
	
	public static void fragment_8(int n) {
		long sum = 0;
		int i = n;
		while (i > 1) {
		    i = i / 2;
		    sum++;
		}
		System.out.println(sum);
	}
}





