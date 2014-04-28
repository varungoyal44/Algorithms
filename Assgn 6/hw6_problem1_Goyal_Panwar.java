


/*
 * hw6_problem1_Goyal_Panwar.java
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
 * Give a linear-time algorithm (i.e., O(n+m)) that decides whether 
 * a given undirected graph is cyclic.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */


public class hw6_problem1_Goyal_Panwar 
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
		AdjList revEdge; 			// Location of reverse of the same edge...
		boolean seenEdge = false; 	// IF this edge is seen already...
	}
	
	
	//-------------------------------------------------------------------------
	/**
	 * Class variables....
	 * 
	 */
	boolean seenVertex[];
	AdjList neigh[];	// Adjacency list of all the vertices...
	int n, m;  			// n is vertex, m is the edges...
	int graph[][];
	
	
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
		new hw6_problem1_Goyal_Panwar();
	}
	
	
	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 * 
	 * @throws IOException	To throw IO Exception if occurred.
	 * 
	 */
	hw6_problem1_Goyal_Panwar() throws IOException
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
		seenVertex = new boolean [n];
		neigh = new AdjList[n]; 
		
		// To populate the Adjacency list...
		populateNeigh();
		
		// To run DFS on the graph...
		for(int i=0; i<n; i++)
		{
			if(seenVertex[i] == false)
			{
				dfs(i);
			}
		}
		
		// If no loops are found then print NO...
		System.out.println("NO");
		
	}
	
	
	//-------------------------------------------------------------------------
	/**
	 * To populate the neighboring elements.
	 * 
	 */
	void populateNeigh()
	{
		AdjList tempRev;
		for(int i=0; i<m; i++)
		{
			// Left to Right.................................
			if(neigh[graph[i][0]] == null)
			{
				neigh[graph[i][0]] = new AdjList();
				neigh[graph[i][0]].vertex = graph[i][1];
				
				tempRev = neigh[graph[i][0]];
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
				
				tempRev = temp.next;
			}
			
			// Right to Left.................................
			if(neigh[graph[i][1]] == null)
			{
				neigh[graph[i][1]] = new AdjList();
				neigh[graph[i][1]].vertex = graph[i][0];
				
				// to populate reverse edge...
				tempRev.revEdge = neigh[graph[i][1]];
				neigh[graph[i][1]].revEdge = tempRev;
			}
			
			else
			{
				AdjList temp = neigh[graph[i][1]];
				while(temp.next != null)
				{
					temp = temp.next;
				}
				AdjList temp1 = new AdjList();
				temp1.vertex = graph[i][0];
				temp.next = temp1;
				
				// to populate reverse edge...
				tempRev.revEdge = temp.next;
				temp.next.revEdge = tempRev;
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
		seenVertex[s] = true;
		
		AdjList temp = neigh[s];
		while(temp != null)
		{	
			if(seenVertex[temp.vertex] == true && temp.seenEdge == false)
			{
				System.out.println("YES");
				System.exit(0);
			}
			if(seenVertex[temp.vertex] == false)
			{
				temp.seenEdge = true;
				temp.revEdge.seenEdge = true;
				dfs(temp.vertex);
			}
			
			temp = temp.next;	
		}
	}
}