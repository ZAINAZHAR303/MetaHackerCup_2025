package problem1;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));
        
        int T = Integer.parseInt(reader.readLine());
        
        for (int caseNum = 1; caseNum <= T; caseNum++) {
            int N = Integer.parseInt(reader.readLine());
            String[] heightStrs = reader.readLine().split(" ");
            int[] heights = new int[N];
            
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(heightStrs[i]);
            }
            
            int maxGap = 0;
            for (int i = 0; i < N - 1; i++) {
                int gap = Math.abs(heights[i] - heights[i + 1]);
                if (gap > maxGap) {
                    maxGap = gap;
                }
            }
            
            writer.println("Case #" + caseNum + ": " + maxGap);
        }
        
        writer.close();
        reader.close();
    }
}

