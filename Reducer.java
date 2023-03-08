package groupAssignment;

import java.util.*;

public class Reducer {

    public Reducer() {

    }
    static int max;
    static ArrayList<Integer> flist = new ArrayList<>();
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
        max = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        maxClique(graph,list,0,1);
        maximalClique = flist;

        return maximalClique;
    }

    public ArrayList<Integer> maxClique(int[][] graph,ArrayList<Integer> list,int i,int v){
       int n = graph.length;
       int tempmax;
       ArrayList<Integer> test = flist;
        for (int j = i+1; j<n; j++){
            if (list.size()<(v+1))
            {
                list.add(j);
            }
            else
            {
                if (list.size()>v+1)
                {
                    list.remove(v+1);
                }
                list.set(v,j);
            }
            
            if(isclique(list,graph)){
                if (max < v)
                {
                    max = v;
                }
                tempmax = maxClique(graph,list,j,v+1).size();
                if (max < tempmax)
                {
                    max = tempmax;
                    flist = list;
                }
            }
        }
        return list;
    }

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
