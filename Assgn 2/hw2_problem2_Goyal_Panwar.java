


/*
 * hw2_problem2_Goyal_Panwar.java
 *
 * Version:
 *     0.1
 *
 * Revisions:
 *     0.1
 */ 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution for the problem set:
 * There are n stacks of DVD's forming a line, we number them from 
 * left to right: the leftmost stack is number 1, the rightmost 
 * stack is number n. A robot needs to rearrange the DVD's so 
 * that all stacks are of the same height (i.e., contain the same number 
 * of DVD's). The robot can perform only this move: go to the i-th stack 
 * where i belongs to {1, 2, ...., n}, pick any number of DVD's and move them 
 * to the stack immediately to the right (the (i+1)-st stack, if i < n) or 
 * immediately to the left (the (i - 1)-st stack, if i > 1). Design an O(n) 
 * algorithm that finds the minimum number of moves the robot needs to make in
 * order to get all stacks to contain the same number of DVD's. Your algorithm
 * should also list the moves the robot needs to make. If it is not possible 
 * to achieve equal heights, your algorithm should say so. Argue both the 
 * correctness and the time complexity of your algorithm.
 * 
 * For example, if we start with stacks of 2, 6, 3, and 1 DVD's, the robot 
 * needs to move 1 DVD from the 2nd to the 1st stack, 2 DVD's from the 2nd 
 * to the 3rd stack, and 2 DVD's from the 3rd to the 4th stack.
 * 
 * @author		Anuj Panwar
 * @author		Varun Goyal
 *
 */


public class hw2_problem2_Goyal_Panwar 
{

	/**
	 * Main program to create and call the method.
	 * 
	 * @param 	args			Command line argument (ignored)
	 * @throws 	IOException		To handle Input/Output Exception.
	 */

	public static void main(String args[]) throws IOException
	{
		new hw2_problem2_Goyal_Panwar().start();
	}


	/**
	 * The method which performs all the calculation.
	 * 
	 * @return				Returns true if it is possible to equally 
	 * 						stack the DVDs. else returns false.
	 * 
	 * @throws IOException	To handle input/output exception.
	 */

	boolean start() throws IOException
	{
		// To get the input....
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));
		String str = br.readLine();
		int len = Integer.parseInt(str);

		str = br.readLine(); // to get the number array...

		String str_arr[] = str.split(" "); //to split the input...

		int arr[] = new int[len];
		for (int i=0; i<len; i++)
		{
			arr[i] = Integer.parseInt(str_arr[i]);
		}


		// To calculate the minimum DvDs for each stack...
		int min=0;		// minimum number of DvDs required in each stack...
		for (int i=0; i<len; i++)
		{
			min = min + arr[i];
		}


		// To check if it is possible to stack the DvDs equally...
		if((min%len) != 0)
		{
			System.out.println("IMPOSSIBLE");
			return false;
		}

		else
		{
			System.out.print("\nPOSSIBLE");
			min = min / len;
		}


		// The main logic...
		int def = 0; 		   // Deficiency of DvDs in a stack... 
		int currDef = 0;	   // To get the deficiency in the current stack...
		
		int exc = 0;		   // Excess of DvDs in previous stacks... 
		int currExc = 0;	   // Excess DvDs in the present stack...
		
		String trans = "";     // To store the transfers...
		int steps = 0;	       // To store the number of steps...
		


		for(int i=0; i<len; i++)
		{
			if(arr[i] < min)
			{
				def = def + (min - arr[i]);
			}


			// If there is a deficiency at the left of the current stack...
			if (arr[i] > min && def != 0)
			{
				if (arr[i] >= (min + def - exc))
				{
					trans = trans + "\n"+(i+1);
					
					arr[i] = arr[i] - (def - exc);

					i--;	// To go back to previous stack...

					currDef = min - arr[i];
					if(currDef < 0)
						currDef = 0;

					currExc = arr[i] - min;
					if(currExc < 0)
						currExc = 0;

					trans = trans + " " + (i+1) + " " + (def - exc);
					arr[i] = arr[i] + (def - exc);

					def = def - currDef;
					exc = exc - currExc;

					steps++;
					i--; 	// To compensate for the for loop i++;
				}
				
				else
				{
					exc = exc + arr[i] - min;
				}
			}

			// If there is deficiency to the right of the current stack...
			if (i >= 0 && arr[i] > min && def == 0)
			{
				trans = trans + "\n"+(i+1);
				currExc = arr[i] - min;
				arr[i] = arr[i] - currExc;
				arr[i+1] = arr[i+1] + currExc;

				trans = trans + " " + (i+2) + " " + currExc;
				
				steps++;
			}

		}

		// To print the output to the standard output...
		System.out.print("\n"+steps);
		System.out.println(trans);

		return true;
	}
}