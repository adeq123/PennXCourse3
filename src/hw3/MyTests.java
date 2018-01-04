package hw3;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import java.util.*;

public class MyTests {
	
	@Test
	public void mazeSampleTest() throws IOException {
		Maze m = new Maze("mazeSmall.txt");
		List<Maze.Move> moves = m.solveMaze();
		assertEquals(2, moves.size());
		assertEquals(Maze.Move.DOWN, moves.get(0));
		assertEquals(Maze.Move.RIGHT, moves.get(1));
	}
	


	@Test
	public void testLarger() throws IOException {
		Graph g = new Graph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 6);
        g.addEdge(4, 9);
        g.addEdge(5, 6);
        g.addEdge(5, 7);

        g.addEdge(6, 8);
        g.addEdge(7, 9);
        g.addEdge(8, 10);
        g.addEdge(9, 12);
        g.addEdge(9, 11);
        g.addEdge(10, 12);
        g.addEdge(11, 12);
        
		assertEquals(2, g.numShortestPaths(0, 12));
		assertEquals(2, g.numShortestPaths(0, 9));
		assertEquals(2, g.numShortestPaths(0, 3));
		assertEquals(4, g.numShortestPaths(6, 0));
	}
	
	@Test
	public void testLarger2() throws IOException {
		Graph g = new Graph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        g.addEdge(4, 7);
        g.addEdge(5, 7);
        g.addEdge(6, 7);

        
		assertEquals(6, g.numShortestPaths(0, 7));
		assertEquals(2, g.numShortestPaths(0, 3));
		assertEquals(2, g.numShortestPaths(6, 0));
	}
	
	@Test
	public void testSmall() throws IOException {
		Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
		//assertEquals(2, g.numShortestPaths(0, 3));
		assertEquals(2, g.numShortestPaths(1, 2));
		assertEquals(1, g.numShortestPaths(0, 1));
		assertEquals(1, g.numShortestPaths(2, 3));
	}
	
}

