


/*
 * hw6_problem2_Goyal_Panwar.java
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
 * Solution to problem:
 * Give an O(n2) algorithm that computes the length of the 
 * longest path in a given directed acyclic graph.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */

public class hw6_problem2_Goyal_Panwar 
{
	//-------------------------------------------------------------------------
	/**
	 * Class to keep the Adjacency Lists of the graph
	 * 
	 */
	class AdjList
	{
		int vertex;
		AdjList next;
	}
	//-------------------------------------------------------------------------
	/**
	 * Class variables....
	 * 
	 */
	boolean seen[];
	AdjList neigh[];	// Adjacency list of all the vertices...
	int n, m;  			// n is vertex, m is the edges...
	int graph[][];
	int topoArr[];
	int topoTime[];
	int time;


	//-------------------------------------------------------------------------
	/**
	 * Main method to create the object and call start method.
	 * 
	 * @param 	args		Command line argument (ignored)
	 * @throws IOException	To throw IO Exception if occurred.
	 *  
	 */
	public static void main(String args[]) throws IOException
	{
		new hw6_problem2_Goyal_Panwar();
	}


	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 * 
	 * @throws IOException	To throw IO Exception if occurred.
	 *  
	 */
	hw6_problem2_Goyal_Panwar() throws IOException
	{
		// To get the values from the user...
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		String str_arr[] = str.split(" ");
		n = Integer.parseInt(str_arr[0]);
		m = Integer.parseInt(str_arr[1]);

		// To populate the Graph...
		graph = new int[m][2];
		for (int i=0; i<m; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr1[] = str.split(" "); //to split the input...
			graph[i][0] = Integer.parseInt(str_arr1[0]);
			graph[i][1] = Integer.parseInt(str_arr1[1]);
		}

		// To initialize the variables.
		seen = new boolean [n];
		neigh = new AdjList[n];
		topoArr = new int[n];
		topoTime = new int[n];
		time = 0;

		// To populate the Adjacency list...
		populateNeigh();

		// To run DFS on the graph...
		for(int i=0; i<n; i++)
		{
			if(seen[i] == false)
			{
				dfs(i);
			}
		}

		// To calculate the max length...
		System.out.println(longestPath());
	}

	//-------------------------------------------------------------------------
	/**
	 * To populate the neighboring elements.
	 * 
	 */
	void populateNeigh()
	{
		for(int i=0; i<m; i++)
		{
			// Left to Right.................................
			if(neigh[graph[i][0]] == null)
			{
				neigh[graph[i][0]] = new AdjList();
				neigh[graph[i][0]].vertex = graph[i][1];
			}

			else
			{
				AdjList temp = neigh[graph[i][0]];
				while(temp.next != null)
				{
					temp = temp.next;
				}
				AdjList temp1 = new AdjList();
				temp1.vertex = graph[i][1];
				temp.next = temp1;
			}
		}
	}


	//-------------------------------------------------------------------------
	/**
	 * This method traverses the graph using Depth First Search...
	 * 
	 * @param	s	denotes the start point from where DFS is to be performed.
	 * 
	 */
	void dfs(int s)
	{
		seen[s] = true;

		AdjList temp = neigh[s];
		while(temp != null)
		{	
			if(seen[temp.vertex] == false)
			{
				dfs(temp.vertex);
			}
			temp = temp.next;	
		}
		topoArr[time] = s;
		topoTime[s] = time;
		time++;
	}



	//-------------------------------------------------------------------------
	/**
	 * Method to return the longest path size in the given graph.
	 * 
	 */
	int longestPath()
	{
		// To calculate S[i]....
		int s[] = new int[n];
		for(int i=0; i<n; i++)
		{
			s[i] = 0;
			AdjList temp = neigh[topoArr[i]];
			while(temp != null)
			{
				if(s[i] <= s[topoTime[temp.vertex]]+1)
					s[i] = s[topoTime[temp.vertex]]+1;

				temp = temp.next;
			}
		}


		// To calculate the max in S[i]....
		int maxCount = 0;
		for(int i=0; i<n; i++)
		{
			if(maxCount < s[i])
				maxCount = s[i];
		}
		
		return maxCount;
	}
}
