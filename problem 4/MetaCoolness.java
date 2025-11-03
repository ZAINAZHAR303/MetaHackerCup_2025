import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class MetaCoolness {
    static final int MOD = 1000000007;

    
    static int binomLucas(long n, long k) {
        int res = 1;
        while (n > 0 || k > 0) {
            int ni = (int)(n % MOD);
            int ki = (int)(k % MOD);
            if (ki > ni) return 0;
            res = (int)((long)res * binom(ni, ki) % MOD);
            n /= MOD;
            k /= MOD;
        }
        return res;
    }

    
    static int binom(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k > n - k) k = n - k; 
        
        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) % MOD;
            result = result * modInverse(i + 1, MOD) % MOD;
        }
        return (int) result;
    }
    
    static long modInverse(long a, long m) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(m)).longValue();
    }

    // Pollard's Rho for fast factorization
    static Map<Long, Integer> factorize(long n) {
        Map<Long, Integer> factors = new HashMap<>();
        for (long d = 2; d * d <= n; d++) {
            while (n % d == 0) {
                factors.put(d, factors.getOrDefault(d, 0) + 1);
                n /= d;
            }
        }
        if (n > 1) factors.put(n, factors.getOrDefault(n, 0) + 1);
        return factors;
    }

    static int ways(long x, long N) {
        if (x == 1) return 1;
        Map<Long, Integer> factors = factorize(x);
        int result = 1;
        for (int e : factors.values()) {
            result = (int)((long)result * binomLucas(e + N - 1, N - 1) % MOD);
        }
        return result;
    }

    static List<Long> getDivisors(long n, long upper) {
        List<Long> divs = new ArrayList<>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i <= upper) divs.add(i);
                if (n / i != i && n / i <= upper) divs.add(n / i);
            }
        }
        return divs;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int T = Integer.parseInt(br.readLine().trim());
        for (int caseNum = 1; caseNum <= T; caseNum++) {
            String[] parts = br.readLine().trim().split(" ");
            long N = Long.parseLong(parts[0]);
            long A = Long.parseLong(parts[1]);
            long B = Long.parseLong(parts[2]);
            long answer = 0;
            List<Long> divs = getDivisors(B, A);
            for (long P : divs) {
                int count1 = ways(P, N);
                int count2 = ways(B / P, N);
                answer = (answer + (long)count1 * count2 % MOD) % MOD;
            }
            System.out.println("Case #" + caseNum + ": " + answer);
        }
    }
}
