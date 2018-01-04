package hw1;

import static org.junit.Assert.assertEquals;

public class Test {

    public static void main(String[] args) {
	
	CompareInt[] arr1 = MyTests.convert(new int[]{4,7 , 3, 9, 8, 6, 1, 55, 66, 21, 12, 32,43, 43, 99, 98, 97, 90});
	int kth = KthSmallest.heapImpl(arr1.length, arr1);
	System.out.println(kth);
	System.out.println();
	for(CompareInt c : arr1)
	    System.out.println(c.val);

    }

}
