package hw4;


import java.util.*;
import java.io.*;

/**
 * Arbitrage class
 *
 */
public class Arbitrage {

    /**
     * Reconstruction algorithm was taken from wikipedia
     * This function reads in a file containing exchange rate information
     * (we've done this for you),
     * and should create a weighted, directed graph
     * 
     * The output should be a list of currencies (i.e. vertices) that you can follow
     * to create an arbitrage opportunity
     * 
     * e.x. if trading from currency 1 to 2 to 3 back to 1 yields an arbitrage
     * opportunity, you should output a list containing {1, 2, 3, 1}
     * 
     * If no arbitrage opportunity is present, you should output an empty list
     */
    public static WeightedDigraph<Double> WD;

    public static List<Integer> arbitrageOpportunity(String filename) throws IOException {
	//parses the input file into a list of exchange rates
	//see the comments above readExchangeRates for details on its output
	double[][] exchangeRates = readExchangeRates(filename);
	List<Integer> arbitrage = new ArrayList <Integer> (exchangeRates.length);
	double[][] Dk = logarithmise(exchangeRates);
	int [][] next = initNext(exchangeRates.length); //array of vertex indices initialized

	//Floyd - Warshal negative cycle detection starts here
	int k;
	for(k = 0; k < exchangeRates.length; k ++){
	    for(int i = 0; i < exchangeRates.length; i ++){
		for(int j = 0;j < exchangeRates[0].length; j++){
		    if(Dk[i][j] > Dk[i][k] + Dk[k][j]){
			Dk[i][j] = Dk[i][k] + Dk[k][j];
			next[i][j] = next[i][k];
		    }
		}
	    }
	    if(isDiagNegative(Dk)){
		break;
	    }
	}

	/*
	 * Creates the list of transaction based on matrix from Floyd - Warshal algorithm
	 * see wikipedia
	 */
	if(isDiagNegative(Dk)){
	    int [] coord = getNegDiagCoordinates(Dk);
	    for(int diag : coord){
		if(diag != 0){

		    int prev = next [--diag] [diag];
		    arbitrage.add(diag);
		    while(prev != diag){

			arbitrage.add(prev);
			prev = next[prev][diag];
		    }
		    arbitrage.add(diag);
		    break;
		}
	    }
	}

	return arbitrage;
    }

    private static void printArray(int[][] next) {
	for (int i = 0; i < next.length; i ++){
	    for (int j = 0; j < next[0].length; j ++){
		System.out.print(next[i][j]+" ");
	    }
	    System.out.println();
	}
	System.out.println();

    }
    /**
     * initializes the array of vertex indices
     * @param length
     * @return
     */
    private static int[][] initNext(int length) {
	int [][] next = new int [length][length];
	for(int i = 0; i < length; i ++){
	    for(int j = 0;j < length; j++){
		next [i] [j] = j;
	    }
	}
	return next;
    }

    /**
     * this method returns coordinates of the negative values on main diagonal.
     * 
     * @param dk1
     * @return
     */
    private static int[] getNegDiagCoordinates(double[][] dk1) {
	int[] arg = new int [dk1.length];
	int j = 0;
	for(int i = 0; i < dk1.length; i ++){
	    if(dk1[i][i] < 0)
		arg [j ++] = i + 1;
	}
	return arg;
    }

    /**
     * The method checks if there is any negative values on the main diagonal of 
     * @param dk1
     * @return
     */
    private static boolean isDiagNegative(double[][] dk1) {
	for(int i = 0; i < dk1.length; i ++){
	    if(dk1[i][i] < 0)
		return true;
	}
	return false;
    }
    /**
     * This method takes  the matrix and calcualtes the -log(i,j) of each value and puts it
     * to new array which at the end is returned. We need it for Floyd Warshal algorith to
     * trasfer the multiplication, associated with exchange rates and transfer, to sum.
     * @param exchangeRates
     * @return
     */
    private static double[][] logarithmise(double[][] exchangeRates) {
	double[][] logExchangeRates = new double [exchangeRates.length][exchangeRates.length];
	for(int i = 0; i < exchangeRates.length; i ++){
	    for(int j = 0;j < exchangeRates[0].length; j++){
		logExchangeRates[i][j] = - Math.log(exchangeRates[i][j]);
	    }
	}
	return logExchangeRates;
    }

    /**
     * Creates graph for given exchange rates
     * @param exchangeRates
     */
    private static void createGraph(double[][] exchangeRates) {
	WD = new WeightedDigraph<Double>(exchangeRates.length);
	for(int i = 0; i < exchangeRates.length; i ++){
	    for(int j = 0;j < exchangeRates[0].length; j++){
		WD.addEdge(i, j, exchangeRates[i][j]);
		WD.addEdge(j, i, exchangeRates[j][i]);
	    }
	}
    }


    /**
     * You don't have to modify this function
     * 
     * Parses a file containing exchange rates between countries into an NxN 2d array
     * 
     * In general, if we have two countries i and j,
     * arr[i][j] gives the exchange rate from country i to j.
     * 
     * I.e. if arr[i][j] = 4.00, then 1 quantity of currency i can be exchanged
     * for 4.00 quantities of currency j
     */
    public static double[][] readExchangeRates(String filename) throws IOException {
	//System.out.println("starting to read exchange rates");
	BufferedReader br = new BufferedReader(new FileReader(filename));

	//first line contains the number of countries
	int n = Integer.parseInt(br.readLine());
	double[][] exchangeRates = new double[n][n];

	//parse the file as a 2d array
	//in general, element j in row i has the exchange rate from country i to country j
	for (int i = 0; i < n; i++) {
	    String[] line = br.readLine().split(" ");
	    for (int j = 0; j < n; j++) {
		double rate = Double.parseDouble(line[j]);
		exchangeRates[i][j] = rate;
	    }
	}
	br.close();
	return exchangeRates;

    }

    public static void printArray(double[][] dk1){
	for (int i = 0; i < dk1.length; i ++){
	    for (int j = 0; j < dk1[0].length; j ++){
		System.out.format("%.2f ", dk1[i][j]);
	    }
	    System.out.println();
	}
	System.out.println();
    }
}

