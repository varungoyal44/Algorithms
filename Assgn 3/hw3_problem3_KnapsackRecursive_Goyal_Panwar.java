


/**
 * This program implements the KnapSack Recursive Algorithm 
 * from professor Bezáková's notes.
 * 
 * @author 		Varun Goyal
 * @author 		Anuj Panwar
 *
 */
public class hw3_problem3_KnapsackRecursive_Goyal_Panwar 
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
		hw3_problem3_KnapsackRecursive_Goyal_Panwar obj1 = 
			new hw3_problem3_KnapsackRecursive_Goyal_Panwar();
		
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
		int num = KnapIndRec(this.n, c, w, W);
		System.out.println(num);
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
	 * The KnapSack Recursive algorithm from professor Bezáková's notes.
	 * and to print the output.
	 * 
	 * @param n		number of items present.
	 * @param c		Cost of individual item.
	 * @param w		weight of individual item.
	 * @param W		total weight that the bag can carry.
	 */
	private int KnapIndRec(int n, int c, int w, int W)
	{
		int withLastItem, withoutLastItem;
		
		if (n <= 0 || W < 0)
			return 0;

		if (W < w)
			withLastItem = -1; // undefined
		else
			withLastItem = c + KnapIndRec(n-1, c, w, W-w);

		withoutLastItem = KnapIndRec(n-1, c, w, W);
		
		return (Math.max(withLastItem,withoutLastItem));
	}
}
