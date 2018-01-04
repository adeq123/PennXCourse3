package hw2;

public class Diagonals {
    public void diagonal(String[] mat){

    }

    public static String[][]  initMat(){
	int dim = 4;
	char ch = 'A';
	String[][] array = new String[dim +1][];
	for( int i = 0 ; i < dim+1; i++ ) {
	    array[i] = new String[dim];
	    for( int j = 0 ; j < dim; j++, ch++ ) {
		array[i][j] = "" + ch;
	    }
	}

	return array;
    }

    public static void printArray(String[][] array){
	for (int i = 0; i < array.length; i ++){
	    for (int j = 0; j < array[0].length; j ++){
		System.out.print(array[i][j]);
	    }
	    System.out.println();
	}

    }
/** 
 * This method prints all of the diagonals 
 * @param array
 */
    public static void printDiag (String [][] array){
	//first half
	for(int row = 0; row <= array.length - 1; row ++){
	    int diagRow = row;
	    int diagCol = 0;

	    while(diagRow >= 0 && diagCol <= row && diagCol <= (array[0].length - 1)){
		System.out.print(array[diagRow][diagCol]);
		diagRow--;
		diagCol++;
	    }
	    System.out.println();
	}
	
	//second half
	for(int col = 1; col <= array[0].length - 1; col ++){
	    int diagRow = array.length - 1;
	    int diagCol = col;

	    while(diagCol <= array[0].length - 1  ){
		System.out.print(array[diagRow][diagCol]);
		diagRow--;
		diagCol++;
	    }
	    System.out.println();
	}
    }
    public static void main(String[] args) {
	String[][] array = initMat();

	printArray(array);
	System.out.println();
	printDiag(array);
    }

}
