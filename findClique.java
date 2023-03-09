import java.util.ArrayList;

public class findClique {

    public static void main(String[] args) {
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }
        Reducer r = new Reducer();
        FileReader reader = new FileReader(fileName);
        ArrayList<int[][]> graphs = reader.graph();
        
        for( int i = 0;i < graphs.size(); i++){
            //starts timer
            long startTime = System.currentTimeMillis();

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

            //assigns maximum clique
            ArrayList<Integer> points = r.toMaxClique(current);
            long ms = System.currentTimeMillis() - startTime;

            //does final print out
            System.out.print("G" + (i + 1) + " ( " + v + ", " + e + ") ( size=" + points.size() + " ms=" + ms + ") {");
            for (int j = 0; j < points.size(); j++) {
                System.out.print(points.get(j));
                if(j != points.size() -1){
                    System.out.print(",");
                }
            }
            System.out.print("}\n");
        }
        System.out.println("***");
    }
    
}
