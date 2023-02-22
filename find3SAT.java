package groupAssignment;

import java.util.*;

public class find3SAT{

    public static void main(String[] args) {
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }
        //Initialize reducer
        Reducer r = new Reducer();

        //initialize reader that breaks down given file into the agreed upon type
        FileReader reader = new FileReader(fileName);
        ArrayList<ArrayList<Integer>> cnfs = reader.cnf();

        //print initialization
        System.out.println("* Solve 3CNF in 3cnfs.txt:(reduced to K-Clique) *");
        System.out.println("X means either T or F");

        //loop through each 3cnf
        for (int i = 0; i < cnfs.size(); i++) {
            //start timer
            long startTime = System.currentTimeMillis();
            ArrayList<Integer> current = cnfs.get(0);
            //reduce to vertex cover
            int variables = Collections.max(current);
            int[][] vertexCover = r.toVertexCover(current, variables * 2);
            ArrayList<Integer> maximumClique = r.toMaxClique(r.toCliqueProblem(vertexCover));
        
            int[] assigments = new int[variables];

            for (int j = 0; j < maximumClique.size(); j++) {
                int temp = maximumClique.get(j);
                if (temp < variables * 2) {
                    assigments[temp/2] = (temp%2)+ 1;
                }else{
                    break;
                }
            }
            //stop timer
            long ms = System.currentTimeMillis() - startTime;

            //print answer
            System.out.print("3CNF No." + (i + 1) + ":[n=" + Collections.max(current) +  " k=" + current.size() / 3);
            System.out.print("] (" + ms + "ms) Solution:[");
               
            for (int variable = 0; variable < assigments.length; variable++) {
                System.out.print((variable + 1) + ":");
                if (assigments[variable] == 0) {
                    System.out.print("X ");
                } else if (assigments[variable] == 1) {
                    System.out.print("T ");
                } else {
                   System.out.print("F " );
                }
            }
            System.out.print("]\n");

            /*
             * print ( 2|-1|-1)∧(-3|-2|-4)∧( 4|-3|-1) ==>
             * ( T| T| T)∧( X| F| T)∧( F| X| T)
             */
            System.out.print("( " + current.get(0) + "|");
            for (int z = 1; z < current.size(); z++) {
                if (z % 3 == 0) {
                    System.out.print("∧(");
                }
                if (current.get(z) > 0) {
                    System.out.print(" ");
                }
                System.out.print(current.get(z));
                if (z % 3 == 2) {
                    System.out.print(")");
                } else {
                    System.out.print("|");
                }

            }

            System.out.print(" ==>\n");

            System.out.print("(");
             if (assigments[0] == 0) {
                    System.out.print(" X");
                } else if (assigments[Math.abs(current.get(0))-1] == 1 && current.get(0) > 0 || assigments[Math.abs(current.get(0))-1] == 1 && current.get(0) < 0) {
                    System.out.print(" T");
                } else {
                    System.out.print(" F");
                }
            System.out.print("|");
            for (int z = 1; z < current.size(); z++) {
                int value = Math.abs(current.get(z))-1;
                if (z % 3 == 0) {
                    System.out.print("∧(");
                }
                if (assigments[value] == 0) {
                    System.out.print(" X");
                } else if (assigments[value] == 1 && current.get(z) > 0 || assigments[value] == 1 && current.get(z) < 0) {
                    System.out.print(" T");
                } else {
                    System.out.print(" F");
                }
                if (z % 3 == 2) {
                    System.out.print(")");
                } else {
                    System.out.print("|");
                }

            }
            
            System.out.print("\n\n");

        }  
        return;
    }
}