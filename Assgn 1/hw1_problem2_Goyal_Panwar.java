package hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Convert the number to base n. To do this,
 * divide the number by n remainder is the last digit of new number.
 * repeat above step to get the whole number to base n.
 * 
 * sort using radix sort the array of new numbers.
 * 
 * along-with sort the original numbers. done.
 */

public class hw1_problem2_Goyal_Panwar 
{

	public static void main(String args[]) throws IOException
	{
		new hw1_problem2_Goyal_Panwar().start();
	}

	void start() throws IOException
	{
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));
		String str = br.readLine();
		int n = Integer.parseInt(str);

		str = br.readLine(); // to get the number array...

		String str_arr[] = str.split(" "); //to split the input...

		int X[] = new int[n];
		for (int i=0; i<n; i++)
		{
			X[i] = Integer.parseInt(str_arr[i]);
		}

		int baseNL[] = new int[n];
		int baseNF[] = new int[n];


		for (int i=0; i<n; i++)
		{
			int last = X[i]%n;
			int num = X[i]/n;
			baseNL[i] = last;
			baseNF[i] = num;
		}
	


		// Sorting...

		int temp_baseNL[][] = new int[10][n];
		int temp_baseNF[][] = new int[10][n];
		int temp_X[][] = new int[10][n];


		// intializing temp array...
		for(int i=0; i<10; i++)
			for(int j=0; j<n; j++)
				temp_baseNL[i][j] = -1;

		for(int i=0; i<10; i++)
			for(int j=0; j<n; j++)
				temp_X[i][j] = -1;


		// Last digit... in temp...
		for(int i=0; i<10; i++)
		{
			int k=0;
			for (int j=0; j<n; j++)
			{
				if (baseNL[j] == i)
				{
					temp_baseNL[i][k] = baseNL[j];
					temp_baseNF[i][k] = baseNF[j];
					temp_X[i][k] = X[j];
					k++;
				}
			}
		}

		// Last digit... in main array...
		for(int i=0; i<10; i++)
		{
			int k=0;

			if(temp_baseNL[i][k] != -1)
			{
				baseNL[k] = temp_baseNL[i][k];
				baseNF[k] = temp_baseNF[i][k];
				X[k] = temp_X[i][k];
				k++;
			}

		}


		// Reintializing temp array...
		for(int i=0; i<10; i++)
			for(int j=0; j<n; j++)
				temp_baseNF[i][j] = -1;

		for(int i=0; i<10; i++)
			for(int j=0; j<n; j++)
				temp_X[i][j] = -1;


		// First digit... in temp...
		for(int i=0; i<10; i++)
		{
			for (int j=0; j<n; j++)
			{
				if (baseNF[j]/10 == i)
				{
					temp_baseNF[i][j] = baseNF[j];
					temp_baseNL[i][j] = baseNL[j];
					temp_X[i][j] = X[j];
				}
			}
		}

		// First digit... in main array...
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(temp_baseNF[i][j] != -1)
				{
					baseNF[j] = temp_baseNF[i][j];
					baseNL[j] = temp_baseNL[i][j];
					X[j] = temp_X[i][j];
				}
			}
		}

		for(int i=0; i<n; i++)
			System.out.print(X[i]+" ");

	}
}
