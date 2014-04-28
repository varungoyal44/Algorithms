

/*
 * hw7_problem3_Goyal_Panwar.java
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
 * Solution to the problem:
 * Let G = (V,E,w) be an edge-weighted directed graph with all weights 
 * positive, i.e., w : E -> R+. Give an O(n3) algorithm that finds 
 * the minimum distance from u to v for every pair of vertices u, v E V . 
 * Moreover, output the number of distinct paths from u to v of length 
 * equal to the shortest distance.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */

public class hw7_problem3_Goyal_Panwar 
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
		int weight;
		boolean seenEdge = false; 	// IF this edge is seen already...
	}


	//-------------------------------------------------------------------------
	int n, m;
	int graph[][];
	AdjList neigh[];
	int H[];
	int dist[];


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
		new hw7_problem3_Goyal_Panwar();
	}


	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 * @throws IOException	To throw IO Exception if occurred.
	 * 
	 */
	hw7_problem3_Goyal_Panwar() throws IOException
	{
		// To get the values from the user...
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		String str_arr[] = str.split(" ");
		n = Integer.parseInt(str_arr[0]) + 1;
		m = Integer.parseInt(str_arr[1]) + 1;


		// To populate the Graph & To get the weight of each vertex...
		graph = new int[m][3];
		for (int i=1; i<m; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr2[] = str.split(" "); //to split the input...
			graph[i][0] = Integer.parseInt(str_arr2[0]);
			graph[i][1] = Integer.parseInt(str_arr2[1]);
			graph[i][2] = Integer.parseInt(str_arr2[2]);
		}

		// to perform Dijkstra...
		for(int i=1; i<n; i++)
			startDijkstra(i);
	}

	//-------------------------------------------------------------------------
	/**
	 * Method to initiate Dijkstra's Algorithm on a given start vertex.
	 * Thus, print the output.
	 * 
	 * @param	start	specifies the source vertex for 
	 * 					the dijkstra's Algorithm
	 * 
	 */
	void startDijkstra(int start)
	{
		// To initialize...
		H = new int [n];
		dist = new int [n] ;

		// To populate the Adjacency list...
		neigh = new AdjList[n];
		populateNeigh();

		// To find Single source shortest paths from start...
		int cnt[] = Dijkstra(start);


		for(int i=1; i<n; i++)
		{
			if(dist[i] != Integer.MAX_VALUE)
				System.out.print(dist[i] + "/" + cnt[i] + " ");

			else
				System.out.print("inf" + "/" + "0" + " ");	// DO NOT CHANGE...DO NOT CHANGE...DO NOT CHANGE...DO NOT CHANGE...

		}
		System.out.print("\n");
	}


	//-------------------------------------------------------------------------
	/**
	 * To populate the neighboring elements.
	 * 
	 */
	void populateNeigh()
	{
		for(int i=1; i<m; i++)
		{
			// Left to Right.................................
			if(neigh[graph[i][0]] == null)
			{
				neigh[graph[i][0]] = new AdjList();
				neigh[graph[i][0]].vertex = graph[i][1];
				neigh[graph[i][0]].weight = graph[i][2];
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
				temp1.weight = graph[i][2];
				temp.next = temp1;
			}
		}
	}


	//-------------------------------------------------------------------------
	/**
	 * Method to perform Dijkstra's Algorithm on a given start vertex.
	 * 
	 * @param	start	specifies the source vertex for 
	 * 					the dijkstra's Algorithm
	 * 
	 * @return	returns the array which specifies the number of paths of the 
	 * 			shortest length.
	 * 
	 */
	int cnt[];
	int[] Dijkstra(int start)
	{
		cnt = new int[n];
		for(int i=1; i<n; i++)
			cnt[i] = 1;

		// 1. Let H = null
		for(int i=1; i<n; i++)
			H[i] = Integer.MAX_VALUE;

		/*
		 * 2. For every vertex v do
		 * 3. dist[v] = infinite
		 */
		for(int i=1; i<n; i++)
			dist[i] = Integer.MAX_VALUE;

		// 4. dist[s]=0
		dist[start] = 0;

		// 5. Update(s)
		int ret[][] = update(start, 1);
		cnt[ret[0][0]] = ret[0][1];

		/*
		 * 6. For i=1 to n-1 do
		 * 7. 	u = extract vertex from H of the smallest cost. 
		 */

		for(int i=1; i<n-1; i++)
		{
			int small = Integer.MAX_VALUE; 
			int pos = 0;
			for(int j=1; j<n; j++)
			{
				if(H[j] != Integer.MAX_VALUE && dist[H[j]] < small)
				{
					small = dist[H[j]];
					pos = j;
				}
			}
			int u = H[pos];
			H[pos] = Integer.MAX_VALUE;

			if (u != Integer.MAX_VALUE)
			{// 8. Update(u)
				int ret1[][] = update(u, 1);
				cnt[ret1[0][0]] = ret1[0][1];
			}
		}

		return cnt;
	}


	//-------------------------------------------------------------------------
	/**
	 * To update the cost of reaching the vertices neighboring u from
	 * the start vertex.
	 * 
	 * @param	u	defines the vertex whose neighbors are to be updated.
	 * 
	 * @return returns the no of paths to v from u of shortest cost.
	 * 
	 */
	int[][] update (int u, int cnt1)
	{
		int ret[][] = new int [1][2];
		AdjList temp = neigh[u];

		while(temp != null)
		{
			int v = temp.vertex;
			int weightUV = temp.weight;

			if(dist[v] == (dist[u] + weightUV))
			{
				cnt1++;
				ret[0][0] = v;
				ret[0][1] = cnt1;
			}

			if(dist[v] > (dist[u] + weightUV))
			{
				cnt[v] = cnt[u];
				dist[v] = dist[u] + weightUV;

				boolean found = false;
				for(int i=1; i<n; i++)
				{
					if(H[i] == v)
						found = true;
				}

				if(found == false)
				{
					for(int i=1; i<n; i++)
					{
						if(H[i] == Integer.MAX_VALUE)
						{
							H[i] = v;
							break;
						}
					}
				}
			}
			temp = temp.next;
		}
		return ret;
	}
}