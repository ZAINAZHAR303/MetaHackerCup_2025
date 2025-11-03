import java.io.*;
import java.util.*;

public class FeatherFlattening {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int T = Integer.parseInt(br.readLine().trim());
        for (int caseNum = 1; caseNum <= T; caseNum++) {
            int N = Integer.parseInt(br.readLine().trim());
            String[] parts = br.readLine().trim().split(" ");
            int[] A = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(parts[i - 1]);
            }
            
            // Compute prefix XOR
            int[] S = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                S[i] = S[i - 1] ^ A[i];
            }
            
            // Count occurrences of each prefix XOR value
            Map<Integer, Integer> mp = new HashMap<>();
            for (int i = 0; i <= N; i++) {
                mp.put(S[i], mp.getOrDefault(S[i], 0) + 1);
            }
            
            // Calculate sub (sum of lengths of subarrays with XOR = 0)
            long sub = 0;
            for (int count : mp.values()) {
                long m = count;
                sub += m * (m * m - 1) / 6;
            }
            
            // Total sum of lengths of all subarrays
            long total = (long) N * (N + 1) * (N + 2) / 6;
            
            // Answer is total - sub
            long ans = total - sub;
            System.out.println("Case #" + caseNum + ": " + ans);
        }
    }
}
