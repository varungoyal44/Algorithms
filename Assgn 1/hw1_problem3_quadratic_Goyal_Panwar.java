package hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hw1_problem3_quadratic_Goyal_Panwar 
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader (
				new InputStreamReader (System.in));
		String str = br.readLine();
		int n = Integer.parseInt(str);

		str = br.readLine(); // to get the number array...

		String str_arr[] = str.split(" "); //to split the input...

		int X[] = new int[n];
		for (int i=0; i<n; i++)
		{
			X[i] = Integer.parseInt(str_arr[i]);
		}
		
		
		bubble(X);

	}



	public static void bubble(int x[])
	{
		int n = x.length;
		int temp;
		for (int j=0; j<n-1;j++)
		{
			for(int i=0; i< (n-1)-j;i++)
			{
				if (x[i]>x[i+1]){
					temp = x[i];
					x[i]=x[i+1];
					x[i+1]= temp;
				}

			}
		}
		System.out.println(" ");
		for(int j=0; j<n;j++)
		{
			System.out.print(x[j]+" ");
		}
	}

}
