

/*
 * hw7_problem2_Goyal_Panwar.java
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
 * This is the solution to the problem:
 * We say that an undirected graph G = (V, E) is positive-vertex-weighted 
 * if we have a positive weight function w on G's vertices, 
 * i.e., w : V -> R+. Give an O(n2) algorithm for the single-source 
 * shortest path problem on positive-vertex-weighted undirected graphs.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */


public class hw7_problem2_Goyal_Panwar 
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
		boolean seenEdge = false; 	// IF this edge is seen already...
	}


	//-------------------------------------------------------------------------
	int n, m;
	int weight[];
	int graph[][];
	int start;
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
		new hw7_problem2_Goyal_Panwar();
	}


	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 * @throws IOException	To throw IO Exception if occurred.
	 * 
	 */
	hw7_problem2_Goyal_Panwar() throws IOException
	{
		// To get the values from the user...
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		String str_arr[] = str.split(" ");
		n = Integer.parseInt(str_arr[0]) + 1;
		m = Integer.parseInt(str_arr[1]) + 1;


		// To get the weight of each vertex...
		str = br.readLine();
		String str_arr1[] = str.split(" ");

		weight = new int[n];
		for(int i=1; i<n; i++)
			weight[i] = Integer.parseInt(str_arr1[i-1]);


		// To populate the Graph...
		graph = new int[m][2];
		for (int i=1; i<m; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr2[] = str.split(" "); //to split the input...
			graph[i][0] = Integer.parseInt(str_arr2[0]);
			graph[i][1] = Integer.parseInt(str_arr2[1]);
		}

		// To initialize...
		H = new int [n];
		dist = new int [n] ;

		// To get the starting vertex...
		start = Integer.parseInt(br.readLine());

		// To populate the Adjacency list...
		neigh = new AdjList[n];
		populateNeigh();

		// To find Single source shortest paths from start...
		Dijkstra();

		for(int i=1; i<n; i++)
			System.out.print(dist[i] + " ");
		
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

			// Right to Left.................................
			if(neigh[graph[i][1]] == null)
			{
				neigh[graph[i][1]] = new AdjList();
				neigh[graph[i][1]].vertex = graph[i][0];
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
			}
		}
	}


	//-------------------------------------------------------------------------
	/**
	 * To calculate the shortest path for the given start vertex
	 * to every other vertex.
	 * 
	 */
	void Dijkstra()
	{
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
		dist[start] = weight[start];

		// 5. Update(s)
		update(start);

		/*
		 * 6. For i=1 to n-1 do
		 * 7. 	u = extract vertex from H of the smallest cost. 
		 */

		for(int i=1; i<n-1; i++) //WHY N-1???????????????????????????????????????????????????
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

			// 8. Update(u)
			update(u);
		}
	}


	//-------------------------------------------------------------------------
	/**
	 * To update the cost of reaching the vertices neighboring u from
	 * the start vertex.
	 * 
	 * @param	u	defines the vertex whose neighbors are to be updated.
	 * 
	 */
	void update (int u)
	{
		AdjList temp = neigh[u];

		while(temp != null)
		{
			int v = temp.vertex;
			if(dist[v] > (dist[u] + weight[v]))
			{
				dist[v] = dist[u] + weight[v];

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
	}
}
