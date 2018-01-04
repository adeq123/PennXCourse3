package hw3;

import java.io.IOException;

public class vertexNo {
    public static void printArray(int[][] array){
	for (int i = 0; i < array.length; i ++){
	    for (int j = 0; j < array[0].length; j ++){
		System.out.print(array[i][j]);
	    }
	    System.out.println();
	}

    }
    public static int[][] initGrid(int n){
	int[][] grid = new int[n][n];

	//identify start and end vertices
	
	for (int row = 0; row < n; row++) {
	    for (int col = 0; col < n; col++) {
		grid[row][col] = row*n + col;

	    }
	}
	return grid;
    }
    public static void main (String[] args) throws IOException{
	int[][] grid = initGrid(7);
	//printArray(grid);
	//Maze maze = new Maze("mediumMaze.txt");
	Maze maze = new Maze("mazeSmall.txt");
	
	for(Maze.Move move : maze.solveMaze()){
	    System.out.println(move);
	}
    }
    


}
