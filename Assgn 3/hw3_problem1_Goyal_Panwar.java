

/*
 * hw3_problem1_Goyal_Panwar.java
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
import java.util.Random;
import java.util.Vector;


/**
 * This program is the solution for the below problem set:
 * In a little town there are n houses on the main street. This street runs 
 * along the x-axis and the coordinates of the houses are x1, x2,.....xn.
 * You decided to open a candy shop and you want to find the most profitable
 * coordinate xbest. The most profitable coordinate will be one that 
 * minimizes the sum of the distances to every house. Give an O(n) algorithm 
 * that finds xbest such that distbest is as small as possible.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 */

public class hw3_problem1_Goyal_Panwar 
{
	/**
	 * Main method to get the input and print the output.
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

		float xn[] = new float[len];

		for (int i=0; i<len; i++)
		{
			xn[i] = Float.parseFloat(str_arr[i]);
		}

		// For n/2.............................................................
		// To get the median of the array...
		float Xbest = 
			new hw3_problem1_Goyal_Panwar().aSelectRand(xn, (len/2));

		// To calculate the distance of the median from every other house...
		float dist = 0;
		for(int i=0; i<len; i++)
		{
			dist = dist + Math.abs(xn[i] - Xbest);
		}
		System.out.println((int)(dist));

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

	private float aSelectRand(float arr[], int k)
	{
		int n = arr.length;

		// Step 0.............................................................
		if (n<1)
			return 0;

		// Step 1.............................................................
		float x = arr[rand(n)];

		// Step 2.............................................................
		Vector <Float> arrVect = new Vector<Float>();;

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
			float temp[] = new float[j1];

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
			float temp[] = new float[(n-j2)];

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
