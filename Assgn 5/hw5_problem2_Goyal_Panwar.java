


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution to problem:
 * Give an algorithm that finds the number of connected components of a given 
 * graph G. (Two vertices are in the same connected component of G if there 
 * exists a path between these vertices.) Your algorithm should run in linear
 * time, i.e., O(n + m), where n is the number of vertices and m is the 
 * number of edges. Describe your algorithm in words and pseudocode 
 * (as well as submit a Java/C/C++ implementation) 
 * and reason its running time.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 */

public class hw5_problem2_Goyal_Panwar 
{
	//-------------------------------------------------------------------------
	/**
	 * Class to keep the Adjacency Lists of the graph
	 */
	class AdjList
	{
		int vertex;
		AdjList next;
	}

	//-------------------------------------------------------------------------
	boolean seen[];
	int n, m;  // n is vertex, m is the edges
	int count;
	AdjList neigh[];
	int graph[][];

	//-------------------------------------------------------------------------
	/**
	 * Main method to create the object and call start method.
	 * 
	 * @param 	args		Command line argument (ignored)
	 */
	public static void main(String args[]) throws IOException
	{
		new hw5_problem2_Goyal_Panwar();
	}

	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 */
	hw5_problem2_Goyal_Panwar() throws IOException
	{
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		String str_arr[] = str.split(" ");
		n = Integer.parseInt(str_arr[0])+1;
		m = Integer.parseInt(str_arr[1])+1;

		graph = new int[m][2];
		for (int i=1; i<m; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr1[] = str.split(" "); //to split the input...
			graph[i][0] = Integer.parseInt(str_arr1[0]);
			graph[i][1] = Integer.parseInt(str_arr1[1]);
		}

		seen = new boolean [n];
		neigh = new AdjList[n+1];

		for(int i=1;i<=n;i++)
			neigh[i] = new AdjList();

		populateNeigh();

		connectedComponent(graph);
	}


	//-------------------------------------------------------------------------
	/**
	 * To populate the neighboring elements.
	 */
	void populateNeigh()
	{
		for(int i=1;i<=n;i++)
		{
			AdjList temp = new AdjList();
			temp.vertex = graph[i][1];
			temp.next = neigh[graph[i][0]].next;

			neigh[graph[i][0]].next=temp;	
		}
	}


	//-------------------------------------------------------------------------
	/**
	 * This method computes the number of connected 
	 * components in the given graph... 
	 */
	void connectedComponent(int graph[][])
	{
		/*
		 * 1. for every vertex n
		 * 2. seen[n]=false
		 */
		for(int i=1; i<n; i++)
			seen[i] = false;

		/*
		 * 3. for every vertex s
		 * 4. if not seen[s] then
		 * 5. DFS(G,s)
		 * 
		 */

		count = 0;
		for(int i=1; i<n; i++)
		{
			if(seen[i] == false)
			{
				dfs(i);
				count++;
			}
		}

		System.out.println(count);
	}


	//-------------------------------------------------------------------------
	/**
	 * This method traverses the graph using Depth First Search...
	 * 
	 * @param	s	denotes the start point from where DFS is to be performed.
	 */
	void dfs(int s)
	{
		seen[s] = true;

		while(neigh[s].next!=null && seen[neigh[s].next.vertex] == false)
		{
			dfs(neigh[s].next.vertex);
			neigh[s].next = neigh[s].next.next;	
		}
	}
}
