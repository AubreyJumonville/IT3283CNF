import java.util.*;

public class find3SAT {

    public static void main(String[] args) {
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }
        // Initialize reducer
        Reducer r = new Reducer();

        // initialize reader that breaks down given file into the agreed upon type
        FileReader reader = new FileReader(fileName);
        ArrayList<ArrayList<Integer>> cnfs = reader.cnf();

        // print initialization
        System.out.println("* Solve 3CNF in 3cnfs.txt:(reduced to K-Clique) *");
        System.out.println("X means either T or F");

        // loop through each 3cnf
        for (int i = 0; i < cnfs.size(); i++) {
            boolean answer = true;
            // start timer
            long startTime = System.currentTimeMillis();
            ArrayList<Integer> current = cnfs.get(i);
            // reduce to vertex cover
            int variables = Collections.max(current);
            int[][] vertexCover = r.toVertexCover(current, variables * 2);

            for (int j = 0; j < vertexCover.length; j++) {
                 for (int k = 0; k < vertexCover.length; k++) {
                     System.out.print(vertexCover[j][k] + " ");
                 }
                 System.out.print("\n");
            }

            int[][] cliqueProblem = r.toCliqueProblem(vertexCover);
            ArrayList<Integer> maximumClique = r.toMaxClique(cliqueProblem);

            System.out.println(maximumClique.toString());

            // determine if there is an answer based on size of clique in relation to k
            if (((variables * 2 + current.size()) - maximumClique.size()) > (variables + (current.size() / 3 * 2))) {
                answer = false;
            }

            int[] assignments = new int[variables];

            if (answer) {
                for (int j = 0; j < variables * 2; j+=2) {
                    if (!maximumClique.contains(j)) {
                        if (!maximumClique.contains(j + 1)) {
                            assignments[j / 2] = 0;
                        } else if (j % 2 == 0) {
                            assignments[j / 2] = 1;
                        } else {
                            assignments[j / 2] = 2;
                        }
                    } else {
                        assignments[j / 2] = 2;
                    }
                }
            } else {
                Random rd = new Random();
                for (int j = 0; j < assignments.length; j++) {
                    assignments[j] = ((rd.nextInt(2) % 2) + 1);
                }
            }

            // stop timer
            long ms = System.currentTimeMillis() - startTime;

            // print answer
            System.out.print("3CNF No." + (i + 1) + ":[n=" + Collections.max(current) + " k=" + current.size() / 3);
            System.out.print("] (" + ms + "ms) ");
            if (answer) {
                System.out.print("Solution:[");
            } else {
                System.out.print("No solution! Random:[");
            }

            for (int variable = 0; variable < assignments.length; variable++) {
                System.out.print((variable + 1) + ":");
                if (assignments[variable] == 0) {
                    System.out.print("X ");
                } else if (assignments[variable] == 1) {
                    System.out.print("T ");
                } else {
                    System.out.print("F ");
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

            for (int z = 0; z < current.size(); z++) {
                int value = Math.abs(current.get(z)) - 1;
                if (z == 0) {
                    System.out.print("(");
                }
                else if (z % 3 == 0) {
                        System.out.print("∧(");
                }
                
                System.out.print(getSymbol(assignments, value, current.get(z)));

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
    
    private static String getSymbol(int[] assignments, int absValue, int value ) {
        String symbol = "X";
        if (assignments[absValue] == 0) {
           symbol = " X";
        } else if (assignments[absValue] == 1 && value > 0
                || assignments[absValue] == 2 && value < 0) {
            symbol =" T";
        } else {
            symbol =" F";
        }
        return symbol;
    }
}