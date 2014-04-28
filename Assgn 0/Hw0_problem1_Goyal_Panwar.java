/*
 * Hw0_problem1_Goyal.java
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
 * This program computes the Factorial of a given number.
 * 
 * @author 		Varun Goyal
 * @author		Anuj Panwar
 */

public class Hw0_problem1_Goyal 
{
	
	/**
	 * This is the main method.
	 * 
	 * @param 	args			command line argument (ignored)
	 * @throws 	IOException		to handle IO Exception.
	 */
	
	public static void main(String args[]) throws IOException
	{
		System.out.println("Program to find the factorial of a number...");
		
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));
		System.out.print("\nEnter the number: ");
		String str = br.readLine();
		int num = Integer.parseInt(str);
		
		int fact=1;
		for(int i=1; i<=num; i++)
		{
			fact = fact * i;
		}
		
		System.out.println(fact);
	}
}
