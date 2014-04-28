

/*
 * hw3_problem3_KnapsackDynProg_Goyal_Panwar.java
 * 
 * Version:
 * 		0.1
 * 
 * Revision:
 * 		0.1
 */

/**
 * This program implements the The KnapSack Dyn Prog Algorithm 
 * from professor Bezáková's notes.
 * 
 * @author 		Varun Goyal
 * @author 		Anuj Panwar
 */

public class hw3_problem3_KnapsackDynProg_Goyal_Panwar 
{
	int n;
	int c;
	int w;
	int W;
	
	/**
	 * Main method to create the object and call start method.
	 * 
	 * @param 	args		Command line argument (ignored)
	 */

	public static void main(String args[])
	{
		hw3_problem3_KnapsackDynProg_Goyal_Panwar obj1 = 
			new hw3_problem3_KnapsackDynProg_Goyal_Panwar();

		obj1.start(5);
		obj1.start(10);
		obj1.start(20);
		obj1.start(50);
		obj1.start(100);
	}

	
	/**
	 * Start method which call inputs to create input, and then fianlly
	 * calls the KNAPSACK algorithm.
	 * 
	 * @param 	n	number of items which can be stolen.
	 */
	
	private void start(int n)
	{
		inputs(n);
		System.out.println("n: "+n+" c: "+c+" w: "+w+" W: "+W);
		KnapsackDynProg(this.n, c, w, W);
	}


	/**
	 * To create the inputs.
	 * @param 	n	number of items which can be stolen.
	 */
	private void inputs(int n)
	{
		this.n = n;
		c = 1;
		w = 1;
		W = n/2;
	}

	
	/**
	 * The KnapSack Dyn Prog algorithm from professor Bezáková's notes.
	 * and to print the output.
	 * 
	 * @param n		number of items present.
	 * @param c		Cost of individual item.
	 * @param w		weight of individual item.
	 * @param W		total weight that the bag can carry.
	 */
	
	private  void KnapsackDynProg (int n, int c, int w, int W)
	{
		int S[][] = new int [n+1][W+1];

		//	1. initialize S[0][v]=0 for every v=0,…,W
		for(int v=0; v<W; v++)
			S[0][v] = 0;

		//2. initialize S[k][0]=0 for every k=0,…,n
		for(int k=0; k<n; k++)
			S[k][0] = 0;

		for(int v=1; v<=W; v++)
		{
			for(int k=1; k<=n; k++)
			{
				S[k][v] = S[k-1][v];

				if ( (w <= v) && 
						((S[k-1][v-w] + c) > S[k][v]))
				{
					S[k][v] = S[k-1][v-w] + c;
				}
			}
		}
	
		System.out.println("  "+S[n][W]);
	}
		
}