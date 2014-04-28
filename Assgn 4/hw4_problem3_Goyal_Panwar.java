

/*
 * hw4_problem3_Goyal_Panwar.java
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

public class hw4_problem3_Goyal_Panwar 
{
	//-------------------------------------------------------------------------
	/**
	 * Main method to create the object and call start method.
	 * 
	 * @param 	args		Command line argument (ignored)
	 */
	public static void main(String args[]) throws IOException
	{
		new hw4_problem3_Goyal_Panwar().start();
	}

	//-------------------------------------------------------------------------
	/**
	 * This method  is used to start the program. get the input and call 
	 * further methods.
	 */
	private void start() throws IOException
	{
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));
		String str = br.readLine();
		int n = Integer.parseInt(str);

		int matrix[][] = new int[n][n];
		for (int i=0; i<n; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr[] = str.split(" "); //to split the input...

			for(int j=0; j<n; j++)
				matrix[i][j] = Integer.parseInt(str_arr[j]);
		}

		// To calculate and print the output...
		System.out.println(longestCommonSubsequence(matrix, n));
	}

	//-------------------------------------------------------------------------
	/**
	 * This method calculates the max size of squares using
	 * Longest Common Subsequence algorithm.
	 * 
	 * @param	matrix[][]		provides the matrix to work on.
	 * @param	n				gives the size of matrix.
	 */
	private int longestCommonSubsequence (int matrix[][], int n)
	{
		// To calculate the max length of matrix square...
		for(int i=1; i<n; i++)
		{
			for(int j=1; j<n; j++)
			{
				int temp = matrix[i][j];
				matrix[i][j] = Math.min(matrix[i-1][j], matrix[i][j-1]);

				if (matrix[i][j] == matrix[i-1][j-1] && matrix[i][j] > 0)
					matrix[i][j] = matrix[i][j]+ 1;

				else
					matrix[i][j] = temp;
			}
		}

		// To GET the max length of matrix square...
		int max=0;
		for(int i=1; i<n; i++)
			for(int j=1; j<n; j++)
				if(max < matrix[i][j])
					max = matrix[i][j];

		return max;
	}
}