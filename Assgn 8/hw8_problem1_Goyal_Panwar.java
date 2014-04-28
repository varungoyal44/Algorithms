package hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * hw8_problem1_Goyal_Panwar.java
 *
 * Version:
 *     0.1
 *
 * Revisions:
 *     0.1
 */ 


public class hw8_problem1_Goyal_Panwar 
{
	//-------------------------------------------------------------------------
	int n, m;
	int chess[][];
	int no0, no1;

	class Element{
		int val, i, j;
		boolean color = false;	//true => white  false => black.
	}

	class Edge{
		Element A;
		Element B;
		boolean seen = false;
	}

	Element ele[];
	ArrayList <Edge> graph;
	ArrayList <Element> neigh[];


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
		new hw8_problem1_Goyal_Panwar();
	}


	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 * @throws IOException	To throw IO Exception if occurred.
	 * 
	 */
	@SuppressWarnings("unchecked")
	hw8_problem1_Goyal_Panwar() throws IOException
	{
		// To get the values from the user...
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		String str_arr[] = str.split(" ");
		n = Integer.parseInt(str_arr[0]);
		m = Integer.parseInt(str_arr[1]);


		// To populate the Chessboard...
		chess = new int[n][m];
		for (int i=1; i<n; i++)
		{
			str = br.readLine();
			String str_arr1[] = str.split(" ");

			for(int j=0; j<m; j++)
				chess[i][j] = Integer.parseInt(str_arr1[j]);
		}


		// To get no of 0's and 1's;
		no1 = 0;
		no0 = 0;
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				if(chess[i][j] == 0)
					no0++;

				if(chess[i][j] == 1)
					no1++;
			}
		}

		// if odd no of 0s...
		if(no0 % 2 != 0)
		{
			System.out.println("NO");
			System.exit(0);
		}
		else
		{
			System.out.println("YES");
			System.exit(0);
		}	


		// initializing...
		ele = new Element[n*m];
		graph = new ArrayList <Edge>();


		// To populate the graph...
		populate();


		// To populate the Adjacency list...
		//neigh = new AdjList[no0];
		populateNeigh();


		// To print the solution...
		/*for(int i=0; i<n; i++)
		{
			System.out.println(" ");
			for(int j=0; j<m; j++)
			{
				System.out.print(" "+chess[i][j]);
			}
		}*/
	}


	//-------------------------------------------------------------------------
	void populate()
	{
		// to populate elements...
		int k=0;
		boolean col = false;
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{

				ele[k] = new Element();
				ele[k].i = i;
				ele[k].j = j;
				ele[k].val = chess[i][j];

				if(col == false)
				{
					col = true;
					ele[k].color = col;
				}
				else
				{
					col = false;
					ele[k].color = col;
				}
				k++;
			}
		}


		for(int i=0; i<ele.length; i++)
		{
			if(ele[i].color == true) // white
			{
				int j = 0;

				// left black...
				j = i-1;
				if (j >= 0 && (j%(m-1)) != 0)
					populateGraph(i, j);	

				// right black...
				j = i+1;
				if (j%m != 0)
					populateGraph(i ,i+1);

				// top black...
				j = i - m;
				if(j >= 0)
					populateGraph(i, j);

				// bottom black...
				j = i + m;
				if(j <= (n*m))
					populateGraph(i, j);
			}
		}


		for(int i=0; i<graph.size(); i++)
		{
			if(graph.get(i).A.val == 1)
			{
				graph.remove(i);
				i--;
			}
		}


		for(int i=0; i<graph.size(); i++)
		{
			if(graph.get(i).B.val == 1)
			{
				graph.remove(i);
				i--;
			}
		}

		/*for(int i=0; i<graph.size(); i++)
		{
			if (graph.get(i).A == graph.get(i+1).A)
				System.out.println("\n");

			System.out.println(graph.get(i).A.i + "," + graph.get(i).A.j + "   " +
					graph.get(i).B.i+ "," + graph.get(i).B.j);
		}*/


	}

	//-------------------------------------------------------------------------
	void populateGraph(int Apos,int Bpos)
	{
		Edge temp = new Edge();
		temp.A = ele[Apos];
		temp.B = ele[Bpos];
		graph.add(temp);
	}

	//-------------------------------------------------------------------------
	/**
	 * To populate the neighboring elements.
	 * 
	 */
	void populateNeigh()
	{
		//neigh[][]
	}


	/*
		for(int i=1; i<no0; i++)
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
		}*/
}

