

/*
 * hw5_problem1_Goyal_Panwar.java
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

/**
 * Solution to problem:
 * Given is a convex polygon with n vertices (x1, y1), (x2, y2),......(xn, yn)
 * (the vertices are listed in a clockwise order). Recall that a 
 * triangulation of a convex polygon is a set of n-3 non-intersecting edges, 
 * where each edge connects two non-consecutive vertices (the overall picture
 * consists of n-2 triangles that together form the original polygon). 
 * We will define the length of a triangulation as the sum of the 
 * lengths of these n-3 edges. Give an O(n3) algorithm that finds 
 * the minimum possible length of a triangulation of the given polygon.
 * 
 * 
 * @author Varun Goyal
 * @author Anuj Panwar
 *
 */


public class hw5_problem1_Goyal_Panwar 
{
	int n;
	float poly[][];
	
	//-------------------------------------------------------------------------
	/**
	 * Main method to create the object and call start method.
	 * 
	 * @param 	args		Command line argument (ignored)
	 */
	public static void main(String args[]) throws IOException
	{
		new hw5_problem1_Goyal_Panwar();
	}
	
	//-------------------------------------------------------------------------
	/**
	 * Constructor to construct the variables.
	 */
	hw5_problem1_Goyal_Panwar() throws IOException
	{
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));

		String str = br.readLine();
		n = Integer.parseInt(str);

		poly = new float[n][2];
		str = br.readLine(); // to get the number array...
		String str_arr1[] = str.split(" "); //to split the input...
		
		for (int i=0, j=0; i<n*2; j++, i=i+2)
		{
			poly[j][0] = (float) Double.parseDouble(str_arr1[i]);
			poly[j][1] = (float) Double.parseDouble(str_arr1[i+1]);
		}
		
		minTriangulationLen();
	}
	
	
	//-------------------------------------------------------------------------
	/**
	 * This method performs the triangulation and 
	 * calulates the minimum length.
	 */
	
	void minTriangulationLen()
	{
		float s[][] = new float [n+1][n+1];
		float temp=0;
		
		for(int d=0; d<n-1; d++)
		{
			for(int l=0; l<(n-d-1); l++)
			{
				int r = l+d+1;
				s[l][r] = (float) Double.MAX_VALUE;
				
				if(r == l+1)
					s[l][r] = 0;
				
				for(int k=l+1; k<r; k++)
				{
					temp = s[l][k] + s[k][r] + dist (l ,r);
					
					if(s[l][r] > temp)
						s[l][r] = temp;
				}
			}
		}
		
		temp = dist(0, n-1);
		System.out.println((int) (s[0][n-1] - temp));
		
	}
	
	//-------------------------------------------------------------------------
	/**
	 * To calculate distance between any two points i and j.
	 */
	float dist(int i, int j)
	{
		float x1 = poly[i][0];
		float y1 = poly[i][1];
		
		float x2 = poly[j][0];
		float y2 = poly[j][1];
		
		float dist; 
		dist = (float) (Math.pow((x1-x2), 2) + Math.pow((y2-y1), 2));
		dist = (float) Math.sqrt(dist);
		
		return dist;
	}
}
