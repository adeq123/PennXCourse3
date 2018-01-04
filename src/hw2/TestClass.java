package hw2;

import java.util.ArrayList;

import hw2.GreedyDynamicAlgorithms.Direction;

public class TestClass {

    public static void main(String [] args){
	int[][] grid = {{5, 1, 1},
			{2, 4, 7},
			{5, 5, 3},
			
			};
	ArrayList<Direction> dir = (ArrayList<Direction>) GreedyDynamicAlgorithms.optimalGridPath(grid);
	for(Direction d : dir)
	    System.out.println(d.toString());
	    
	/*
	 * Huffman hf = new Huffman("aabc");
	String encoding = hf.encode();
	System.out.println(encoding);
	String decoding = hf.decode(encoding);
	System.out.println(decoding);
	*/
    }
}
