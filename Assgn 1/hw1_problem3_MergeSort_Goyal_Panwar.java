package hw1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hw1_problem3_MergeSort_Goyal_Panwar 
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


		hw1_problem3_MergeSort_Goyal_Panwar obj = new hw1_problem3_MergeSort_Goyal_Panwar();
		X = obj.mergeSort(X ,n);

		for (int i=0; i<n; i++)
		{
			System.out.print(X[i]+" ");
		}

	}

	int[] mergeSort(int X[], int n)
	{
		if(n == 1)
			return X;

		int middle = n/2;

		int A[] = new int [middle];
		for(int i=0; i<middle; i++)
			A[i] = X[i];

		int B[] = new int [n-middle];
		for(int i=middle, j=0; i<n; i++, j++)
			B[j] = X[i];

		int As[] = mergeSort(A, middle);
		int Bs[] = mergeSort(B, n-middle);

		return merge(As, Bs);
	}

	int[] merge (int A[], int B[])
	{
		int i=0, j=0, k=0;

		int m = A.length;
		int n = B.length;

		int C[] = new int [m+n];

		while(k<m+n)
		{
			if(i<m  && j>=n)
			{

				C[k] = A[i];
				i++;
			}

			else if (i>=m && j<n)
			{

				C[k] = B[j];
				j++;
			}

			else if (i<m && j<n)
			{
				if(A[i] < B[j])
				{
					C[k] = A[i];
					i++;
				}

				else
				{
					C[k] = B[j];
					j++;

				}
			}

			k++;
		}

		return C;
	}
}
