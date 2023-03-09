

import java.util.ArrayList;

public class findVCover {
    public static void main(String[] args) {
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }
        Reducer r = new Reducer();
        FileReader reader = new FileReader(fileName);
        ArrayList<int[][]> graphs = reader.graph();

        System.out.println("* A Minimum Vertex Cover of every graph in graphs.txt:(reduced to K-Clique) *");
        System.out.println("(|V|,|E|) (size, ms used) Vertex Cover");

        //loop through each graph
        for (int i = 0; i < graphs.size(); i++) {
            int[][] current = graphs.get(i);
           
            int v = current.length;
            int e = 0;
            for (int j = 0; j < v; j++) {
               for (int k = j+1; k < v; k++) {
                   if (current[j][k] == 1) {
                       e++;
                   }
               }
           }

            //starts timer
            long startTime = System.currentTimeMillis();

            //assigns maximum clique
            ArrayList<Integer> points = r.toMaxClique(r.toCliqueProblem(current));
            long ms = System.currentTimeMillis() - startTime;

            //does final print out
            System.out.print("G" + (i + 1) + " ( " + v + ", " + e + ") ( size=" + (v - points.size()) + " ms=" + ms + ") {");
            for (int j = 0; j <= v; j++) {
                if (!points.contains(j)) {
                    System.out.print(j);
                    System.out.print(",");
                }
            }
            System.out.print("}\n");
        }
        System.out.println("***");
    }
}