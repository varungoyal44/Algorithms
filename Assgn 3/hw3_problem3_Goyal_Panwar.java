

/*
 * hw3_problem3_Goyal_Panwar.java
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
 * This program is the solution for the problem set:
 * Modify the second KNAPSACK algorithm so that it prints the list of items
 * that achieve the maximum cost, using overall running time O(nW). 
 * Include a pseudocode of your algorithm and a short explanation of 
 * how the algorithm works.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */

public class hw3_problem3_Goyal_Panwar 
{
	/**
	 * Main method here takes the input from the user and calls 
	 * the algorithm.
	 * @param 	args			Command line argument. (ignored)
	 * @throws 	IOException		To handle IO exception.
	 */

	public static void main(String args[]) throws IOException
	{
		// To get the input...
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		// To get the value of n and W...
		String str = br.readLine();
		String temp_arr[] = str.split(" "); //to split the input...
		int n = Integer.parseInt(temp_arr[0]);
		int W = Integer.parseInt(temp_arr[1]);

		// To read the blank line...
		str = br.readLine();

		// To get individual weights...
		int w[] = new int[n+1];
		int c[] = new int[n+1];
		for(int i=1; i<=n; i++)
		{
			str = br.readLine();
			String temp_arr1[] = str.split(" "); //to split the input...
			w[i] = Integer.parseInt(temp_arr1[0]);
			c[i] = Integer.parseInt(temp_arr1[1]);
		}

		new hw3_problem3_Goyal_Panwar().KnapsackDynProgModified(n, c, w, W);
	}


	/**
	 * The KnapSack Dyn Prog algorithm from professor Bezáková's notes.
	 * and to print the output.
	 * 
	 * @param n		number of items present.
	 * @param c		Cost of individual item.
	 * @param w		weight of individual item.
	 * @param W		total weight that the bag can carry.
	 */
	
	private  void KnapsackDynProgModified (int n, int c[], int w[], int W)
	{
		int S[][] = new int [n+1][W+1];

		//	1. initialize S[0][v]=0 for every v=0,…,W
		for(int v=0; v<W; v++)
			S[0][v] = 0;

		//2. initialize S[k][0]=0 for every k=0,…,n
		for(int k=0; k<n; k++)
			S[k][0] = 0;

		for(int v=1; v<=W; v++)
		{
			for(int k=1; k<=n; k++)
			{
				S[k][v] = S[k-1][v];

				if ( w[k] <= v    && 
						(S[k-1][v-w[k]] + c[k]) > S[k][v])
				{
					S[k][v] = S[k-1][v-w[k]] + c[k];
				}
			}
		}


		// To print the maximum cost.....
		System.out.println(S[n][W]);
		
		
		// To get items to be considered...
		int capacityLeft=W;
		int j=W;
		int temp[] = new int [n];
		int z = 0;

		for(int i=n;i>0;i--)
		{
			if(j>0 && S[i][j]!=S[i-1][j])
			{
				temp[z] = i;
				z++;
				j = capacityLeft - w[i];
				capacityLeft = j;	
			}
		}
		
		for(z=(n-1); z>=0; z--)
		{
			if(temp[z] != 0)
				System.out.print(temp[z]+" ");
		}
		
		System.out.println("");
	}
}
