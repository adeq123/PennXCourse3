package hw1;



/**
 * A Heap implementation class
 * 
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {
	
	CompareInt[] heap;
	int size;
	int numberOfElem;
	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
		numberOfElem = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
		heap[numberOfElem + 1] = val; //indexing from 1
		numberOfElem ++;
		swim(numberOfElem);
	}
	
	private void swim(int k) {
	    while (k > 1 && heap[k/2].compareTo(heap[k]) > 0){
		Sorting.swap(heap, k, k/2);
		k = k/2;
	    }
		
		
	    
	}

	/**
	 * Extracts the smallest element from the heap
	 * Indexing from 1
	 */
	public CompareInt extractMin() {
	    CompareInt min = heap[1];
	    heap[1] = heap[numberOfElem];
	    sink(1);
	    numberOfElem --;
	    return min;
	}

	private void sink(int k) {
	    while (2*k < numberOfElem){
		int smallest = 2*k;
		if(smallest < numberOfElem && heap[2*k].compareTo(heap[2*k + 1]) > 0){
		    smallest = 2*k + 1;
		}
		if(heap[k].val < heap[smallest].val){
		    break;
		}
		Sorting.swap(heap, k, smallest);
		k = smallest;
	    }
	    
	}
	
	
}
