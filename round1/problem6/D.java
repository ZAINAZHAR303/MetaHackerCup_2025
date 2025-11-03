import java.io.*;
import java.util.*;

public class D {
    
    static String solve(int N, String S) {
        int alice_turns = 0;
        int bob_turns = 0;
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            // Alice's turn: find first 'A' from left
            boolean found_A = false;
            for (int i = left; i <= right; ++i) {
                if (S.charAt(i) == 'A') {
                    ++alice_turns;
                    left = i + 1;
                    found_A = true;
                    break;
                }
            }
            if (!found_A) {
                break;
            }

            // Bob's turn: find first 'B' from right
            boolean found_B = false;
            for (int i = right; i >= left; --i) {
                if (S.charAt(i) == 'B') {
                    ++bob_turns;
                    right = i - 1;
                    found_B = true;
                    break;
                }
            }
            if (!found_B) {
                break;
            }
        }

        if (alice_turns > bob_turns) {
            return "Alice";
        } else {
            return "Bob";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int cas = 1; cas <= T; ++cas) {
            int N = Integer.parseInt(br.readLine().trim());
            String S = br.readLine().trim();
            String result = solve(N, S);
            out.println("Case #" + cas + ": " + result);
        }
        
        br.close();
        out.close();
    }
}
