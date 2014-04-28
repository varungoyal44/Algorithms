

/*
 * hw5_problem3_Goyal_Panwar.java
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

public class hw5_problem3_Goyal_Panwar 
{
	//-------------------------------------------------------------------------
	int m;
	int n;
	int mat[][];
	
	boolean seen[];
	int dist[];
	int queue;
	int start;
	int end;
	
	//-------------------------------------------------------------------------
	/**
	 * Main method to create the object and call start method.
	 * 
	 * @param 	args		Command line argument (ignored)
	 */
	public static void main(String args[]) throws IOException
	{
		new hw5_problem3_Goyal_Panwar();
	}
	
	//-------------------------------------------------------------------------
	hw5_problem3_Goyal_Panwar() throws IOException
	{
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		String str_arr[] = str.split(" ");
		m = Integer.parseInt(str_arr[0]);
		n = Integer.parseInt(str_arr[1]);
		
		mat = new int[m][n];
		for (int i=0; i<m; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr1[] = str.split(" "); //to split the input...
			
			for(int j=0; j<n; j++)
				mat[i][j] = Integer.parseInt(str_arr1[j]);
		}
		
		int si=0, sj=0;
		for(int i=0; i<m; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(mat[i][j] == 2)
				{
					si = i;
					sj = j;
					break;
				}
			}	
		}
		seen = new boolean [m*n];
		dist = new int [m*n];
		bfs(si, sj);
	}
	
	//-------------------------------------------------------------------------
	void bfs(int si, int sj)
	{
		
	}
	
	
}

