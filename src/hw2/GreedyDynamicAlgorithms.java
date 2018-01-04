package hw2;


import java.util.*;

public class GreedyDynamicAlgorithms {

    /**
     * Goal: find the smallest number of red intervals to select, such that
     * every blue interval overlaps with at least one of the selected red intervals.
     * Output this number
     * 
     * The algorithm works in such a way that it iterates through the blue intervals
     * and checks if the the solution from previous interation includes also this one. 
     * If yes it skeeps the logic if not it will created a list of all of the red 
     * intervals that intersect this (blue) interval and choose the one with the highest
     * finish time. 
     * 
     * @param red - the list of red intervals
     * @param blue - the list blue intervals
     * @return
     */
    public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
	Interval.sortByStartTime(red);
	Interval.sortByStartTime(blue);
	ArrayList<Interval> redIntersectingWithThisBlue = new ArrayList<Interval>();
	ArrayList<Interval> solution = new ArrayList<Interval>();
	for (Interval oneBlue : blue){
	    if(solution.isEmpty() || solution.get(solution.size() - 1).finish < oneBlue.start){
		int i = 0;
		Interval oneRed = red.get(i);
		while(oneRed.start <= oneBlue.finish &&i < red.size()){
		    redIntersectingWithThisBlue.add(oneRed);
		    if(++i < red.size())
			oneRed = red.get(i);
		}
		red.removeAll(redIntersectingWithThisBlue);
		Interval.sortByFinishTime(redIntersectingWithThisBlue);
		solution.add(redIntersectingWithThisBlue.get(redIntersectingWithThisBlue.size() - 1));
	    }
	}
	return solution.size();
    }

    /**
     * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
     * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
     * 
     * The first part of algorithm fills in auxiliary matrix. It calculates optimal path for each cell 
     * diagonally with begining from the start. It takes min value from left or upper cell and ads its value. 
     * So basically we calcualte cost value for each movement optimally based on the result from previous calculactions.
     * Second part generates the list of directions based on that matrix
     * 
     * @param grid - the 2d grid containing the cost of each location in the grid.
     * @return
     */
    public static List<Direction> optimalGridPath(int[][] grid) {
	int[][] T = generateCostMatrix(grid);
	List<Direction> dir = new ArrayList<Direction> ();
	int row = T.length - 1;
	int col = T[0].length - 1;
	while(col > 0 || row > 0){ //or since it should stop only if we reached (0,0)
	    if(col == 0){// only down possible
		    dir.add(Direction.DOWN);
		    row--;
	    }else if(row == 0){ // only right possible
		    dir.add(Direction.RIGHT);
		    col--;
	    }else{
		int right = T[row][col - 1];
		int down = T[row - 1][col];
		if(right < down){ //then right
		    dir.add(Direction.RIGHT);
		    col--;
		}else{ //then down
		    dir.add(Direction.DOWN);
		    row--;
		}
	    }
	}
	reverseListOrder(dir);
	return dir;
    }
    /**
     * this method reverses the order of the given list
     * @param dir
     */
    private static void reverseListOrder(List<Direction> dir) {
	List<Direction> aux = new ArrayList<Direction>();
	for(int i = dir.size() - 1; i >= 0; i--){
	    aux.add(dir.remove(i));
	}
	dir.addAll(aux);
    }

    /**
     * fills in auxiliary matrix. It calculates optimal path for each cell 
     * diagonally with begining from the start. It takes min value from left or upper cell and ads its value. 
     * So basically we calcualte cost value for each movement optimally based on the result from previous calculactions
     * 
     */

    public static int[][] generateCostMatrix(int[][] grid){
	int[][] T = new int [grid.length][grid[0].length];
	ArrayList<Direction> dir = new ArrayList<Direction>();
	//first half
	for(int row = 0; row <= T.length - 1; row ++){
	    int diagRow = row;
	    int diagCol = 0;

	    while(diagRow >= 0 && diagCol <= row && diagCol <= (T[0].length - 1)){
		if(diagRow == 0 && diagCol == 0){
		    // base case - start value is defined
		    T[0][0] = grid [0][0];
		}else if(diagRow == 0){ // only possible to move right
		    T[diagRow][diagCol] = T[diagRow][diagCol - 1] + grid[diagRow][diagCol];
		}else if(diagCol == 0){ // only possible to move down
		    T[diagRow][diagCol] = T[diagRow - 1][diagCol] + grid[diagRow][diagCol];
		}else{ // normal case - possible to move both down and right
		    int down  = T[diagRow - 1][diagCol] + grid[diagRow][diagCol];
		    int right = T[diagRow][diagCol - 1] + grid[diagRow][diagCol];
		    if(right < down){
			T[diagRow][diagCol] = right;

		    }else{
			T[diagRow][diagCol] = down;

		    }

		}
		diagRow--;
		diagCol++;
	    }

	}

	//second half
	for(int col = 1; col <= T[0].length - 1; col ++){
	    int diagRow = T.length - 1;
	    int diagCol = col;

	    while(diagCol <= T[0].length - 1  ){
		if(diagRow == 0 && diagCol == 0){
		    // base case - start value is defined
		    T[0][0] = grid [0][0];
		}else if(diagRow == 0){ // only possible to move right
		    T[diagRow][diagCol] = T[diagRow][diagCol - 1] + grid[diagRow][diagCol];
		}else if(diagCol == 0){ // only possible to move down
		    T[diagRow][diagCol] = T[diagRow - 1][diagCol] + grid[diagRow][diagCol];
		}else{ // normal case - possible to move both down and right
		    int down  = T[diagRow - 1][diagCol] + grid[diagRow][diagCol];
		    int right = T[diagRow][diagCol - 1] + grid[diagRow][diagCol];
		    if(right < down){
			T[diagRow][diagCol] = right;
		    }else{
			T[diagRow][diagCol] = down;
		    }

		}
		diagRow--;
		diagCol++;
	    }

	}
	return T;
    }
    /**
     * A simple Direction enum
     * directions can be either DOWN or RIGHT
     * You will output a list of these in the grid-path problem
     */
    public static enum Direction {
	DOWN, RIGHT
    }

    /**
     * A private Interval class to help with the interval question
     */
    public static class Interval {

	int start;
	int finish;

	public Interval(int start, int finish) {
	    this.start = start;
	    this.finish = finish;
	}

	/**
	 * sorts a list of intervals by start time, you are free to use this on the first question
	 */
	public static void sortByStartTime(ArrayList<Interval> l) {
	    Collections.sort(l, new Comparator<Interval>() {
		public int compare(Interval o1, Interval o2) {
		    Interval i1 = (Interval) o1;
		    Interval i2 = (Interval) o2;
		    return i1.start - i2.start;
		}
	    });
	}

	/**
	 * sorts a list of intervals by finish time, you are free to use this on the first question
	 */
	public static void sortByFinishTime(ArrayList<Interval> l) {
	    Collections.sort(l, new Comparator<Interval>() {
		public int compare(Interval o1, Interval o2) {
		    Interval i1 = (Interval) o1;
		    Interval i2 = (Interval) o2;
		    return i1.finish - i2.finish;
		}
	    });
	}

    }
    public static <T> void printArray(int[][] t){
	for (int i = 0; i < t.length; i ++){
	    for (int j = 0; j < t[0].length; j ++){
		System.out.print(t[i][j] + " ");
	    }
	    System.out.println();
	}

    }
}
