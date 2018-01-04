package hw3;


import java.io.*;
import java.util.*;

public class Maze {

    Graph g; //We will store the maze internally as a graph
    int startVertex; //one of the vertices in the graph will be the start of the maze
    int endVertex; //another will be the end of the maze
    NodeData [] mazeNodesData;
    HashSet<Integer> marked;
    /**
     * We will store an nxn maze in a textfile, and read it in.
     * 
     * A maze is simply represented as a textfile with 4 numbers: 0, 1, 2, 3
     * 
     * 0s represent walls- this is not a valid part of the maze
     * 1s represent valid parts of the maze (i.e. blocks you can move to
     * 2 represents the starting point of the maze
     * 3 represents the end point of the maze.
     * 
     * Our constructor will create the 2d array of integers from the file for you
     * 
     */
    public Maze(String filename) throws IOException{
	//create the 2d grid from the maze textfile
	int[][] grid = createGrid(filename);
	marked = new HashSet<Integer>();
	//identify start and end vertices
	int n = grid.length;
	for (int row = 0; row < n; row++) {
	    for (int col = 0; col < n; col++) {

		if (grid[row][col] == 2) {
		    startVertex = row*n + col;
		}
		if (grid[row][col] == 3) {
		    endVertex = row*n + col;
		}
	    }
	}
	// create the graph
	g = new Graph(n*n);
	mazeNodesData = new NodeData [n*n];
	//add only edges down and right on the grid then we will cover all of them anyway

	for (int row = 0; row < n; row++) {
	    for (int col = 0; col < n; col++) {
		if(grid[row][col] != 0){
		    mazeNodesData[row*n + col] = new NodeData(row, col);
		    if(row < n-1 && grid[row + 1][col] != 0){// add node from below
			g.addEdge(row*n + col, (row + 1)*n + col);
		    }
		    if(col < n-1 && grid[row][col + 1] != 0){//add node from right
			g.addEdge(row*n + col, row*n + col + 1);
		    }

		}
	    }
	}

    }

    /**
     * 
     * This algorithm should solve the maze output a list of "moves", beginning at the start vertex,
     * that can be taken to arrive at the end vertex.  You should solve the maze on the graph,
     * using some sort of graph traversal.
     * 
     * More information on figuring out what "move" to output at each step can be found in the writeup!
     * 
     * @param g - the graph to traverse
     * @param startVertex - the starting vertex
     * @param endVertex - we will stop the traversal and output the list of moves when we hit this vertex
     * 
     */
    public List<Move> solveMaze(){
	LinkedList<Move> movementList = new LinkedList<Move>();
	solveMaze(startVertex, movementList);
	Collections.reverse(movementList);
	 return movementList;
    }

    private LinkedList<Move> solveMaze (int vertex, LinkedList<Move> movementList){

	if(vertex == endVertex){
	    return movementList;
	}
	marked.add(vertex);
	for(int ng : (LinkedList<Integer>) g.neighbors(vertex)){
	    if(!marked.contains(ng) && solveMaze(ng, movementList) != null){ //marked must be included otherwise you go to neighbour and then go back since 
		if(mazeNodesData[vertex].getRow() < mazeNodesData[ng].getRow()){ // the previous is neighbour ...
		    movementList.add(Move.DOWN);
		}else if(mazeNodesData[vertex].getCol() < mazeNodesData[ng].getCol()){
		    movementList.add(Move.RIGHT);
		}else if(mazeNodesData[vertex].getRow() > mazeNodesData[ng].getRow()){
		    movementList.add(Move.UP);
		}else if(mazeNodesData[vertex].getCol() > mazeNodesData[ng].getCol()){
		    movementList.add(Move.LEFT);
		}						
		return movementList;                                
	    }
	}

	return null;
    }

    /**
     * Move is an enum type- when navigating a maze, you can either move
     * UP, DOWN, LEFT, or RIGHT
     */
    public enum Move {
	UP, DOWN, LEFT, RIGHT
    }

    /**
     * Helper function for creating a 2d grid to represent the maze, given a file name
     * 
     * @param filename - the name of the file to be read from, containing the maze data
     */
    public static int[][] createGrid(String filename) throws IOException {
	//create the 2d array from the maze textfile
	BufferedReader br = new BufferedReader(new FileReader(filename));
	String line = br.readLine();
	int n = line.length(); //the grid will always be an nxn square
	int[][] grid = new int[n][n];
	int row = 0;
	while (line != null) {
	    int col = 0;
	    for (char c : line.toCharArray()) {
		int val = Character.getNumericValue(c);
		grid[row][col] = val;
		col++;
	    }
	    line = br.readLine();
	    row++;
	}
	br.close();
	return grid;
    }

    public class NodeData{

	private int row;
	private int col;
	private boolean visited;

	public NodeData(int row, int col){
	    this.row = row;
	    this.col = col;
	    this.visited = false;
	}

	public boolean isVisited() {
	    return visited;
	}

	public void setVisited(boolean visited) {
	    this.visited = visited;
	}

	public int getRow() {
	    return row;
	}

	public int getCol() {
	    return col;
	}
    }
}

