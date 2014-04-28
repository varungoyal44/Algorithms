

/*
 * hw2_problem3_Goyal_Panwar.java
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
import java.util.Random;
import java.util.Vector;

/**
 * (a) We are given an array of integers A[1::n]. We would like to determine 
 * whether there exists an integer x that occurs in A more than n=2 times 
 * (i. e., whether A has a majority element). Design an algorithm that runs 
 * in time O(n) and argue its correctness and running time estimate.
 * 
 * Example: For A = [3; 1; 2] the answer is NO. For A = [3; 1; 3] the answer 
 * is YES.
 * 
 * (b) We are given an array of integers A[1::n]. We would like to determine 
 * whether there exists an integer x that occurs in A more than n=3 times. 
 * Design an algorithm that runs in time O(n) and argue its correctness and 
 * running time estimate.
 * 
 * @author 		Varun Goyal
 * @author 		Anuj Panwar
 *
 */

public class hw2_problem3_Goyal_Panwar 
{	
	
	/**
	 * Main method to get the input and print the output.
	 * To check if the number is indeed present more than n/2 & n/3 time.
	 * 
	 * @param 		args			Command line argument (ignored)
	 * @throws 		IOException		To handle IO exception.
	 */
	
	public static void main(String args[]) throws IOException
	{
		// To get the input...
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

		// For n/2.............................................................
		// To get the median of the array...
		int num = new hw2_problem3_Goyal_Panwar().aSelectRand(arr, (len/2));
		int cnt=0;
		
		// To count the number of presence of the number... 
		for(int i=0; i<len; i++)
			if(arr[i]==num)
				cnt++;
		
		// For Output....
		if (cnt > (len/2))
			System.out.println("YES");
		else
			System.out.println("NO");


		// For n/3.............................................................
		// To get the n/3rd smallest number...
		num = new hw2_problem3_Goyal_Panwar().aSelectRand(arr, (len/3));
		
		// To count the number of presence of the number...
		cnt=0;
		for(int i=0; i<len; i++)
		{
			if(arr[i]==num)
				cnt++;
		}

		// For Output....
		if (cnt > (len/3))
			System.out.println("YES");
		else
		{
			// To get the n/3rd largest number...
			num = new hw2_problem3_Goyal_Panwar().aSelectRand(arr, ((2*len)/3));
			
			// To count the number of presence of the number...
			cnt=0;
			for(int i=0; i<len; i++)
			{
				if(arr[i]==num)
					cnt++;
			}

			// For Output....
			if (cnt > (len/3))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

	
	/**
	 * Select Random function to get the k-th smallest number from an array.
	 * Using the SELECT-RAND (A, k) Algorithm from Professor Bezakova's
	 * slides.
	 * 
	 * @param 	arr		Provides the array to be worked upon.
	 * @param 	k		Provides the k-th position.
	 * @return			returns the k-th smallest number from the arr array.
	 */
	
	private int aSelectRand(int arr[], int k)
	{
		int n = arr.length;

		// Step 0.............................................................
		if (n<1)
			return 0;

		// Step 1.............................................................
		int x = arr[rand(n)];

		// Step 2.............................................................
		Vector <Integer> arrVect = new Vector<Integer>();;

		for (int i=0; i<n; i++)
			if(arr[i] < x)
				arrVect.add(arr[i]);

		for (int i=0; i<n; i++)
			if(arr[i] == x)
				arrVect.add(arr[i]);

		for (int i=(n-1); i>=0; i--)
			if(arr[i] > x)
				arrVect.add(arr[i]);

		for (int i=0; i<n; i++)
			arr[i] = arrVect.elementAt(i);


		// Step 3.............................................................
		int j1=-1; 
		int j2=-1;

		for(int i=0; i<n; i++)
		{
			if(j1 != -1 && arr[i] == x)
				j2 = i;

			if(j2 == -1 && arr[i] == x)
				j1 = i;
		}

		if(j2 == -1)
			j2 = j1;

		// Step 4.............................................................
		if (k < j1)
		{
			int temp[] = new int[j1];

			for(int i=0; i<j1; i++)
				temp[i] = arr[i];

			return aSelectRand(temp, k);
		}

		// Step 5.............................................................
		if (j1 <= k && k <= j2)
			return x;

		// Step 6.............................................................
		if (k > j2)
		{
			int temp[] = new int[(n-j2)];

			for(int i=j2, j=0; i<n; i++, j++)
				temp[j] = arr[i];

			return aSelectRand(temp, k-j2);
		}

		return 0;
	}

	
	/**
	 * Method to return a Random number of the range 0 to num.
	 * 
	 * @param 	num		Gives the upper-bound for the random number generation
	 * @return			Returns the random number generated.
	 * 
	 */
	
	private int rand(int num)
	{
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(num);
	}
}