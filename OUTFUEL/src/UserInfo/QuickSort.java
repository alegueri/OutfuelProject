
package UserInfo;

import java.util.Comparator;
/**
 * Sorts the gas stations based on price or distance. Uses the compareTo method implemented in the GasStationADT to compare. 
 * @author Alessandra Guerinoni 
 *
 */
public class QuickSort {
	/**
	 * helper method that swaps two elements of the array.  
	 *@param GasStationADT[] array
	 *@param the index of the element to be swapped
	 *@param the index of the element to be swapped 
	 *
	 */
	private static void swap(GasStationADT[] a, int i, int j) { GasStationADT t = a[i]; a[i] = a[j]; a[j]= t; } //helper method 

	public static void sort(GasStationADT[] a, String rank)
	{ sort(a, 0, a.length-1, rank); }
	/**
	 * the recursive method of quicksort. Recursively sorts the array. 
	 * @param GasStationADT[] array
	 * @param int lo, the lower index
	 * @param int hi, the higher index
	 * @param String rank, what to sort the given array by. 
	 *
	 */

	private static void sort(GasStationADT[] a, int lo, int hi, String rank)
	{
		if(hi <= lo) return;
		int j = partition(a,lo,hi, rank);
		sort(a, lo, j-1, rank);
		sort(a, j+1, hi, rank);
	}
	/**
	 * the partitioning function of quicksort, which partions the array.
	 * @param GasStationADT[] array 
	 * @param int lo, the lower index
	 * @param int hi, the higher index
	 * @param String rank, what to sort the given array by. 
	 *
	 */
	private static int partition(GasStationADT[] a, int lo, int hi, String rank)
	
	{
		int i = lo, j= hi+1; 
		GasStationADT pe = a[lo]; 
		String p = "price"; 
		String d = "distance"; 
		while(true)
		{
			if(rank.equals(p)) {
			while(a[++i].compareTo(pe,p) == -1 || a[i].compareTo(pe, p) == 0) if (i==hi) break;  //sorts lexographically if prices or distance are the same
			while(pe.compareTo(a[--j],p) == -1 || pe.compareTo(a[j],p) == 0) if (j==lo) break;
			}
			else if (rank.equals(d)) {
				while(a[++i].compareTo(pe,d) == -1 || a[i].compareTo(pe, d) == 0) if (i==hi) break; 
				while(pe.compareTo(a[--j],d) == -1 || pe.compareTo(a[j],d) == 0) if (j==lo) break;
				}
			if (i>= j) break; 
			swap(a, i ,j); 
		}
		swap(a,lo,j); 
		return j; 
	}
	/**
	 * Used within the test junit.  
	 *@param GasStationADT[] array
	 *@param "price" or "distance" to be sorted. 
	 *@return true if the given array is sorted. 
	 *
	 */
	public static boolean isSorted(GasStationADT[] a, String rank)
	
	{  
		if (rank.equals("price")) {
		for(int i = 1; i < a.length; i++) 
			if (a[i-1].compareTo(a[i],"price") == 1) return false;
		}
		if(rank.equals("distance")) {
			for(int i = 1; i < a.length; i++) 
				if (a[i-1].compareTo(a[i],"distance") == 1) return false;
		} 
		return true;  
	 }


}
