

/*
 * hw6_problem3_Goyal_Panwar.java
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
 * Given is a weighted undirected graph G = (V;E) with positive weights 
 * and a subset of its edges F subset of E. An F-containing spanning 
 * tree of G is a spanning tree that contains all edges from F 
 * (there might be other edges as well). Give an O(mn) 
 * algorithm that finds the cost of the minimum-cost F-containing 
 * spanning tree of G.
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */

public class hw6_problem3_Goyal_Panwar 
{
	//-------------------------------------------------------------------------
	/**
	 * Class to keep the Adjacency Lists of the graph
	 */
	class Set
	{
		int vertex;
		Set next;
	}

	
	//-------------------------------------------------------------------------
	/**
	 * Class variables....
	 * 
	 */
	int m, n;
	double graph[][];
	int boss[];
	int size[];
	Set set[];
	
	double T[][];
	int Tcnt;

	
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
		new hw6_problem3_Goyal_Panwar();
	}


	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 * @throws IOException	To throw IO Exception if occurred.
	 * 
	 */
	hw6_problem3_Goyal_Panwar() throws IOException
	{
		// To get the values from the user...
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		String str_arr[] = str.split(" ");
		n = Integer.parseInt(str_arr[0]);
		m = Integer.parseInt(str_arr[1]);

		// To populate the Graph...
		graph = new double[m][4];
		for (int i=0; i<m; i++)
		{
			str = br.readLine(); // to get the number array...
			String str_arr1[] = str.split(" "); //to split the input...
			graph[i][0] = Integer.parseInt(str_arr1[0]);
			graph[i][1] = Integer.parseInt(str_arr1[1]);
			graph[i][2] = Double.parseDouble(str_arr1[2]);
			graph[i][3] = Integer.parseInt(str_arr1[3]);
		}

		// Initializing variables...
		boss = new int[n];
		size= new int[n];
		set = new Set[n];
		T = new double[m][3];
		Tcnt = 0;

		// Sorting the edges according to the weights...
		mergeSort(graph, 0, m-1);

		// To Initialize the union-find data-structure...
		unionFindInit();

		// Calling Kruskal for F=1 edges...
		for (int i=0; i<m; i++)
		{
			if (graph[i][3] == 1)
			{
				kruskal((int)graph[i][0], (int)graph[i][1], graph[i][2], true);
			}
		}

		// Calling Kruskal for F=0 edges...
		for (int i=0; i<m; i++)
		{
			if (graph[i][3] == 0)
			{
				kruskal((int)graph[i][0], (int)graph[i][1], graph[i][2], false);
			}
		}

				
		// To calculate the total of size of min Spanning Tree...
		double totSize=0;
		for(int i=0; i<Tcnt; i++)
		{
			totSize = totSize + T[i][2];
		}
		System.out.println(totSize);
	}


	//-------------------------------------------------------------------------
	/**
	 * To initialize the variables for the Union Find algorithm...
	 * 
	 */
	void unionFindInit()
	{
		for(int i=0; i<n; i++)
		{
			boss[i] = i;
			size[i] = 1;
			set[i] = new Set();
			set[i].vertex = i;
		}
	}


	//-------------------------------------------------------------------------
	/**
	 * Kruskal's Algorithm as described in the notes. 
	 * 
	 * @param	u		the u vertex to be evaluated
	 * @param	v		the v vertex to be evaluated
	 * @param	weight	the weight of the current edge.
	 * @param	f		whether the edge belongs to set F.
	 * 
	 */
	void kruskal(int u, int v, double weight, boolean f)
	{
		if(boss[u] == boss[v] && f == true)
		{
			System.out.println("-1");
			System.exit(1);
		}
		
		// For edge e do...
		if(boss[u] != boss[v])
		{
			T[Tcnt][0] = u;
			T[Tcnt][1] = v;
			T[Tcnt][2] = weight;
			union(u, v);
			
			Tcnt++;
		}
	}


	//-------------------------------------------------------------------------
	/**
	 * To union two forests / trees.
	 * 
	 * @param	u	tree corresponding to vertex u
	 * @param	v	tree corresponding to vertex v
	 * 
	 */
	void union (int u, int v)
	{
		if(size[boss[u]] > size[boss[v]])
		{
			// To union the two sets...
			Set temp = set[boss[u]];
			while(temp.next != null)
				temp = temp.next;
			temp.next = set[boss[v]];

			// To add the size of bosses...
			size[boss[u]] = size[boss[u]] + size[boss[v]];

			// for every z in set[boss[v]] do boss[z]=boss[u]
			temp = set[boss[v]];
			while(temp != null)
			{
				boss[temp.vertex] = boss[u];
				temp = temp.next;
			}
		}

		else
		{
			// To union the two sets...
			Set temp = set[boss[v]];
			while(temp.next != null)
				temp = temp.next;
			temp.next = set[boss[u]];

			// To add the size of bosses...
			size[boss[v]] = size[boss[v]] + size[boss[u]];

			// for every z in set[boss[u]] do boss[z]=boss[v]
			temp = set[boss[u]];
			while(temp != null)
			{
				boss[temp.vertex] = boss[v];
				temp = temp.next;
			}
		}
	}

	
	//-------------------------------------------------------------------------
	/**
	 * To Merge Sort the array in increasing order.
	 * 
	 * @param	array	array to be sorted.
	 * @param	lo		defines the lower set of arrays
	 * @param	n		defines the higher set of arrays
	 * 
	 */
	public static void mergeSort(double array[][],int lo, int n)
	{
		int low = lo;
		int high = n;
		if (low >= high)
		{
			return;
		}

		int middle = (low + high) / 2;

		mergeSort(array, low, middle);
		mergeSort(array, middle + 1, high);

		int end_low = middle;
		int start_high = middle + 1;

		while ((lo <= end_low) && (start_high <= high)) 
		{
			if (array[low][2] < array[start_high][2]) 
			{
				low++;
			} 
			else 
			{
				double Temp[] = array[start_high];
				for (int k = start_high- 1; k >= low; k--) 
				{
					array[k+1] = array[k];
				}
				array[low] = Temp;
				low++;
				end_low++;
				start_high++;
			}
		}
	}
}
