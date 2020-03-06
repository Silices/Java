package ubung_3;

import java.util.*;

public class Prime_Numbers {

	public static void main(String[] args) {
		List<Integer> Twenty_Bits = new ArrayList<Integer>();
		List<Long> Fourty_Bits = new ArrayList<Long>();
		
		for (int i = 300000; i<300100; i++)
		{
			Twenty_Bits.add(i);
		}
		
		
		for (long i = 500_000_000_000L; i<500_000_000_100L; i++)
		{
			Fourty_Bits.add(i);
		}
		
		for (int i=0;i<100; i++) {
		isPrime(Twenty_Bits.get(i));
		}

		for (int i=0;i<100; i++) {
		isPrime(Fourty_Bits.get(i));
		}

	}
	
	
	public static boolean isPrime (long n) {
		long sum =0;
		if (n<0) {
			System.out.println("n is not a positive number");
			sum++;
			System.out.println(sum);
			return false;}
		
		for (long i =2; i<n; i++){
			sum++;
			if (n%i == 0)
			{
				System.out.println(sum);
				return false;
			}	
		}
		System.out.println(sum);
		System.out.println("is prime");
		return true;
	}
}


