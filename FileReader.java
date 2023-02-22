package groupAssignment;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileReader {

    private FileInputStream fis;
    private BufferedReader reader;

    public FileReader(String fileName) {
        File file = new File(fileName);
    try {
        fis = new FileInputStream(file);
       reader = new BufferedReader(new InputStreamReader(fis));
   } catch (Exception e) {
       System.out.println("could not open file");
   }
       
    }

    public ArrayList<int[][]> graph() {
        ArrayList<int[][]> graphInfo = new ArrayList<int[][]>();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                int n = Integer.parseInt(line);
                if(n != 0){
                int[][] graph = new int[n][n];
                for (int i = 0; i < n; i++) {
                    line = reader.readLine();
                    StringTokenizer st = new StringTokenizer(line);
                    for (int j = 0; st.hasMoreTokens(); j++) {
                        graph[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                graphInfo.add(graph);}
            }
        } catch (Exception e) {
            System.out.println("could not read file");
        }
        return graphInfo;
    }
    
    public ArrayList<ArrayList<Integer>> cnf() {

        ArrayList<ArrayList<Integer>> info = new ArrayList<ArrayList<Integer>>();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                StringTokenizer st = new StringTokenizer(line);
                while(st.hasMoreTokens()) {
                    row.add(Integer.parseInt(st.nextToken()));
                }
                info.add(row);
            }
            fis.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("could not read file");
        }
        return info;
    }

}
