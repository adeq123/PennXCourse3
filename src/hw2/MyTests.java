package hw2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import hw2.GreedyDynamicAlgorithms.Direction;

public class MyTests {
	
	/*TODO: Add your own test cases here!
	 * We've provided a sample test case for each problem below
	 * You can use these as building blocks to write your own test cases
	 */
    int[][] grid = {{5, 1, 1},
		{2, 4, 7},
		{2, 4, 6},
		{5, 5, 3},
		};
	
	@Test
	public void HuffmanSampleTest() {
		String input = "abc";
		Huffman h = new Huffman(input);
		String encoding = h.encode();
		assertEquals(input, h.decode(encoding));
		assertEquals("huffman abc compression", Huffman.compressionRatio(input), 0.20833, 0.01);
	}
	
	@Test
	public void IntervalSampleTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(1, 3);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(0, 4);
		ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
		ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
		reds.add(red);
		blues.add(blue);
		int expectedOptimal = 1;
		int actualOptimal = GreedyDynamicAlgorithms.optimalIntervals(reds, blues);
		assertEquals("interval 1 red 1 blue", expectedOptimal, actualOptimal);
	}
	
	@Test
	public void IntervalNewTest() {
		ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
		ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
		reds.add(new GreedyDynamicAlgorithms.Interval(0, 4));
		reds.add(new GreedyDynamicAlgorithms.Interval(2, 5));
		reds.add(new GreedyDynamicAlgorithms.Interval(4, 7));
		reds.add(new GreedyDynamicAlgorithms.Interval(9, 11));
		reds.add(new GreedyDynamicAlgorithms.Interval(10, 12));
		reds.add(new GreedyDynamicAlgorithms.Interval(11, 12));
		
		blues.add(new GreedyDynamicAlgorithms.Interval(0, 2));
		blues.add(new GreedyDynamicAlgorithms.Interval(5, 5));
		blues.add(new GreedyDynamicAlgorithms.Interval(7, 10));
		blues.add(new GreedyDynamicAlgorithms.Interval(11, 13));
		int expectedOptimal = 2;
		int actualOptimal = GreedyDynamicAlgorithms.optimalIntervals(reds, blues);
		assertEquals("interval 2 red 6 blue", expectedOptimal, actualOptimal);
	}
	@Test
	public void  optimalGridPathTest(){
		ArrayList<Direction> dir = (ArrayList<Direction>) GreedyDynamicAlgorithms.optimalGridPath(grid);
		assertEquals(0, Direction.DOWN.compareTo(dir.get(0)));
		assertEquals(0, Direction.DOWN.compareTo(dir.get(1)));
		assertEquals(0, Direction.RIGHT.compareTo(dir.get(2)));
		assertEquals(0, Direction.DOWN.compareTo(dir.get(3)));
		assertEquals(0, Direction.RIGHT.compareTo(dir.get(4)));
	}

}

