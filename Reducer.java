package groupAssignment;

import java.util.*;

public class Reducer {

    public Reducer() {

    }

    public int[][] toVertexCover(ArrayList<Integer> cnf, int variables) {
        int n = (variables) + cnf.size();
        //initialize matrix that represents graph
        int[][] graphMatrix = new int[n][n];
        int value = 0;
        for (int i = 0; i < n; i++) {
            //value of row
            value = getvalue(i, variables, cnf);
            for (int j = 0; j < n; j++) {
                // value of col
                int current = getvalue(j, variables, cnf);
                //if row is representing a base variable ie a or -a
                if (i < variables) {
                    //connect to itself and the -variable of itself
                    if (value == current || (j < variables && Math.abs(value) == Math.abs(current))) {
                        graphMatrix[i][j] = 1;
                    } else {
                        graphMatrix[i][j] = 0;
                    }
                } else if (j < variables && value == current) { //non variables should only connect to themselves
                    graphMatrix[i][j] = 1;
                } else {
                    graphMatrix[i][j] = 0;
                }
            }
        }
        return graphMatrix;
    }

    private int getvalue(int i, int variables, ArrayList<Integer> cnf) {
        //value is based on the first set of vertices representing the variables
        int value = 0;
        if (i < variables) {
            value = (i / 2) + 1;
            if (i % 2 != 0) {
                value = value * -1;
            }
        } else {
            value = cnf.get((i - variables));
        }
        return value;
    }

    public int[][] toCliqueProblem(int[][] graphMatrix) {

        int n = graphMatrix.length;
        //creat matrix of same size to store inverted
        int[][] inverseMatrix = new int[n][n];

        //invert matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graphMatrix[i][j] == 0) {
                    inverseMatrix[i][j] = 1;
                } else {
                    inverseMatrix[i][j] = 0;
                }
            }
        }

        return inverseMatrix;
    }

    public ArrayList<Integer> toMaxClique(int[][] graph) {
        ArrayList<Integer> maximalClique = new ArrayList<>();

        /*
         * 
         * IMPLEMENT FORMULA HERE
         * 
         */


        return maximalClique;
    }

}
