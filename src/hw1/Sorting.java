package hw1;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
	
	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
	public static void mergeSort(CompareInt[] arr) {
		 mergeSort(arr,0,arr.length - 1);
	}
	/**
	 * This method implements mergeSort algorithm o sorting the array
	 * @param arr, CompareInt[], array to be sorted
	 * @param low
	 * @param high
	 */
	public static void mergeSort(CompareInt[] arr,  int low, int high) {
	    if(high - low <= 0) return;
	    int mid = (high + low) / 2;
	     mergeSort(arr, low, mid);
	     mergeSort(arr, mid + 1, high);
	     CompareInt[] leftPartOfTheArray = Arrays.copyOfRange(arr, low, mid + 1);
	     CompareInt[] rightPartOfTheArray = Arrays.copyOfRange(arr, mid + 1, high + 1);
	     CompareInt[] aux = merge(leftPartOfTheArray, rightPartOfTheArray);
	     copyInto(arr, aux, low);
	}
	/**
	 * This method merges two arrays according to merge algorithm
	 * @param A
	 * @param B
	 * @return
	 */
	private static CompareInt[] merge(CompareInt[] A, CompareInt[] B){
	    CompareInt[] aux = new CompareInt[A.length + B.length];
	    int i = 0;
	    int j = 0;
	    int k = 0;
	    /*
	     * this part of the code compares values shown by pointer i, j
	     * in input matrixes and puts the smaller one in matrix aux
	     * on a place shown by pointer k
	     */
	    while(i < A.length && j < B.length){
		if(A[i].compareTo(B[j]) < 0){
		    aux[k] = A[i];
		    k++;
		    i++;
		}else{
		    aux[k] = B[j];
		    k++;
		    j++;
		}
	    }
	    
	    /*
	     * if any of the matrices is shorter then the other this code will copy the leftovers to the aux matrix
	     */
	    while(i < A.length){
		aux[k++] = A[i++];
	    }
	    
	    while(j < B.length){
		aux[k++] = B[j++];
	    }
	    
	    return aux;
	}
	/**
	 * copies the source array into the destination 
	 * @param destination
	 * @param source
	 */
	public static void copyInto(CompareInt[] destination, CompareInt[] source, int startIndex){
	    int j = 0;
	    for(int i = startIndex; i < startIndex + source.length  ; i ++){
		if(source [j] != null)
		    destination[i] = source [j];
		j++;
	    }
	}
	/**
	* This method implements Quickselect algorithm for selecting kth element
	* When we partition the array, the kth smallest element will only be on one side of this partition
	* no need to recursively sort both sides of the array: only the side contain the element we want
	 */
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
	    k--;
		return quickSelect(arr, 0, arr.length - 1, k);
	}
	
	public static CompareInt quickSelect(CompareInt[] arr, int lo, int hi,int k){
	    if(hi == lo) return arr[lo];
	    int pivot_location = partition (arr, lo, hi);
	    if (pivot_location == k){
		return arr[k];
	    }else if(pivot_location > k){
		return quickSelect (arr, lo, pivot_location - 1, k);
	    }else{
		return quickSelect (arr, pivot_location + 1, hi, k);
	    }
	    
	}
	
	public static int partition(CompareInt[] arr, int lo,int hi){
	    Random rnd = new Random();
	    int pivot_index = rnd.nextInt(hi - lo) + lo;
	    swap(arr, pivot_index, hi);
	    CompareInt pivot = arr[hi];
	    int leftIndex = lo; 
	    int rightIndex = hi;
	    CompareInt[] aux = new CompareInt[arr.length];
	    for(int k = lo; k < hi; k++){
		if(arr[k].compareTo(pivot) < 0){
		    aux[leftIndex++] = arr[k];	
		}else{
		    aux[rightIndex--] = arr[k];
		}
	    }
	    aux[leftIndex] = arr[hi];
	    copyInto(arr, aux, 0);
	    return leftIndex;
	    
	}
	static void swap(CompareInt[] arr, int from, int to) {
	    CompareInt aux = arr[to];
	    arr[to] = arr[from];
	    arr[from] = aux;
	    
	}

}

