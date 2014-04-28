/*
 * Hw0_problem2_Goyal.java
 * 
 * Version:
 * 		0.1
 * 
 * Revision:
 * 		0.1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This program prints the non-negative integers 
 * smaller than a given number.
 * 
 * @author 		Varun Goyal
 * @author		Anuj Panwar
 */

public class Hw0_problem2_Goyal 
{
	/**
	 * This is the main method.
	 * 
	 * @param 	args			command line argument (ignored)
	 * @throws 	IOException		to handle IO Exception.
	 */
	
	public static void main(String args[]) throws IOException
	{
		System.out.println("Program to print non-negative integers");
		
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));
		System.out.print("\nEnter the number: ");
		String str = br.readLine();
		int num = Integer.parseInt(str);
		
		for(int i=0; i<num; i=i+2)
		{
			System.out.print(i+" ");
		}
		System.out.print("\n");
	}
}
