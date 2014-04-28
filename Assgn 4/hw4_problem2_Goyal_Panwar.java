

/*
 * hw4_problem2_Goyal_Panwar.java
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
 * Solution for:
 * Consider the following variant of the interval scheduling problem: For a 
 * given set of n intervals (unweighted), we want to compute the total number
 * of subsets of non-overlapping intervals.
 * 
 * For example, for these 4 intervals: A = (2; 4), B = (3; 6), C = (5; 7), 
 * D = (0; 1), we have the following subsets of non-overlapping intervals:
 * {A}; {B}; {C}; {D}; {A,C}; {A,D}; {B,D}; {C,D}; {A,C,D}. 
 * Thus, the total number is 10. Give an O(n2) dynamic programming 
 * algorithm for this problem. Submit the pseudocode, the 
 * "heart of the solution" (see the note below), the running 
 * time estimate, and the implementation.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */

public class hw4_problem2_Goyal_Panwar 
{
	//-------------------------------------------------------------------------
	/**
	 * Main method to create the object and call start method.
	 * 
	 * @param 	args		Command line argument (ignored)
	 */
	public static void main(String args[]) throws IOException
	{
		new hw4_problem2_Goyal_Panwar().start();
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

		float interval[][] = new float[n+1][2];
		for (int i=0; i<n; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr[] = str.split(" "); //to split the input...
			interval[i+1][0] = (float) Double.parseDouble(str_arr[0]); 
			interval[i+1][1] = (float) Double.parseDouble(str_arr[1]);
		}
		
		// To sort by end time...
		interval = mergeSortEndTime(interval, 0, n);
		
		// To get the count of intervals with no overlapping...
		int overlap = overlapping(interval, n);
		
		// To print the result...
		System.out.println(overlap);
	}
	
	//-------------------------------------------------------------------------
	/**
	 * This method is used to sort the intervals from their end time,
	 * using merge-sort.
	 * 
	 * @param	array[][]	gives the array to be sorted.
	 * @param	lo			gives the lover point.
	 * @param	n			gives the size of array to be sorted.
	 */
	private float[][] mergeSortEndTime(float array[][],int lo, int n)
	{
		int low = lo;
		int high = n;
		if (low >= high)
		{
			return array;
		}

		int middle = (low + high) / 2;

		mergeSortEndTime(array, low, middle);
		mergeSortEndTime(array, middle + 1, high);

		int end_low = middle;
		int start_high = middle + 1;

		while ((lo <= end_low) && (start_high <= high)) 
		{
			if (array[low][1] < array[start_high][1]) 
			{
				low++;
			} 
			else 
			{
				float temp[] = array[start_high];
				for (int k = start_high-1; k >= low; k--) 
				{
					array[k+1] = array[k];
				}
				array[low] = temp;
				low++;
				end_low++;
				start_high++;
			}
		}
		return array;
	}
	
	//-------------------------------------------------------------------------
	/**
	 * To check for the overlapping intervals and calculating 
	 * the total number of subsets of non-overlapping intervals.
	 * 
	 * @param	array[][]	gives the array to be calculated upon.
	 * @param	n			size of array.
	 */
	private int overlapping (float interval[][], int n)
	{
		int s[] = new int [n+1];
		s[0] = 1;
		
		for(int j=1; j<=n; j++)
		{
			int k=j;
			
			// while (intervals k and j overlap) do k--
			while(k>0 && interval[k][1] > interval[j][0]) 
				k--;
			
			s[j] = s[j-1] + s[k];
		}
		
		return s[n];
	}
}