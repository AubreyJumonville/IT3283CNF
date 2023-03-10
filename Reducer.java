
import java.util.*;

public class Reducer {

    public Reducer() {

    }
    static int globalMax;
    static ArrayList<Integer> finalclique = new ArrayList<Integer>();
    static ArrayList<Integer> templist = new ArrayList<Integer>();

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
            if (i >= variables) {
                if ((i - variables) % 3 == 0) {
                    graphMatrix[i][i + 1] = 1;
                    graphMatrix[i][i + 2] = 1;
                } else if ((i - variables) % 3 == 1) {
                    graphMatrix[i][i + 1] = 1;
                    graphMatrix[i][i - 1] = 1;
                } else {
                    graphMatrix[i][i - 1] = 1;
                    graphMatrix[i][i - 2] = 1;
                }
            }
            graphMatrix[i][i] = 1;
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
        //create matrix of same size to store inverted
        int[][] inverseMatrix = new int[n][n];

        //invert matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graphMatrix[i][j] == 0 || i == j) {
                    inverseMatrix[i][j] = 1;
                } else {
                    inverseMatrix[i][j] = 0;
                }
            }
        }

        return inverseMatrix;
    }

    public ArrayList<Integer> toMaxClique(int[][] graph) {

        //initializes global variables
        globalMax = 0;
        finalclique = new ArrayList<Integer>();

        for (int i = 0; i < graph.length - 1; i++) {
            templist = new ArrayList<Integer>();
            templist.add(i);

            maxClique(graph, i, 1);
        }
        return finalclique;
    }

    //Finds every clique in the graph. If the current clique is larger than the minimum, that clique is copied to the final list.
    public void maxClique(int[][] graph,int i,int v){
       int n = graph.length;
       
        for (int j = i+1; j<n; j++){

            //Adds J to the temp list if the current size of the list isn't enough to accomadte the target position of the new vertex
            if (templist.size()<(v+1))
            {
                templist.add(j);
            }
            else
            {
                //Replaces an entry with the current vertex J. If the target index of the vertex is before the end of the list, an entry is removed
                if (templist.size()>v+1)
                {
                    templist.remove(v+1);
                }
                templist.set(v,j);
            }
            
            //Checks if the current list of vertices is a clique
            if(isclique(templist,graph)){
                //if the size of clique is bigger than the global max, the list is copied into the final list and the global max is updated
                if (templist.size()>globalMax)
                {
                    finalclique = new ArrayList<Integer>(templist);
                    globalMax = templist.size();
                }
                
                //runs maxClique recursively with v incremented by one to see if the clique can be bigger
                maxClique(graph,j,v+1);
            }
        }
        
    }

    //Checks to see if every vertex in the the list to see if it connects to every other vertex in the list. 
    //Returns false as soon as one of the vertices is not connected to another.
    static boolean isclique(ArrayList<Integer> list, int[][] graph){
        int size = list.size();
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++) {
                if (i != j){   
                    if (graph[list.get(i)][list.get(j)] == 0)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
