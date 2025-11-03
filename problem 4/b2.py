import math

MOD = 10**9 + 7
MAX = 100000  # Increase if needed for large exponents

# Precompute factorials and inverses
fact = [1] * (MAX+1)
invfact = [1] * (MAX+1)
for i in range(1, MAX+1):
    fact[i] = fact[i-1] * i % MOD
invfact[MAX] = pow(fact[MAX], MOD-2, MOD)
for i in range(MAX-1, 0, -1):
    invfact[i] = invfact[i+1] * (i+1) % MOD

def comb(n, k):
    if k < 0 or k > n:
        return 0
    if n < len(fact):
        return fact[n] * invfact[k] % MOD * invfact[n-k] % MOD
    # For very large n, use iterative multiplication
    res = 1
    for i in range(1, k+1):
        res = res * (n - i + 1) % MOD
        res = res * pow(i, MOD-2, MOD) % MOD
    return res

def prime_factors(n):
    factors = {}
    d = 2
    while d * d <= n:
        while n % d == 0:
            factors[d] = factors.get(d, 0) + 1
            n //= d
        d += 1
    if n > 1:
        factors[n] = factors.get(n, 0) + 1
    return factors

def ways(x, N):
    if x == 1:
        return 1
    factors = prime_factors(x)
    result = 1
    for e in factors.values():
        result = result * comb(e + N - 1, N - 1) % MOD
    return result

def get_divisors(n, upper):
    divs = []
    for i in range(1, int(math.isqrt(n)) + 1):
        if n % i == 0:
            if i <= upper:
                divs.append(i)
            if n // i != i and n // i <= upper:
                divs.append(n // i)
    return divs

def solve(N, A, B):
    answer = 0
    divs = get_divisors(B, A)
    for P in divs:
        count1 = ways(P, N)
        count2 = ways(B // P, N)
        answer = (answer + count1 * count2) % MOD
    return answer

def main():
    with open('input.txt', 'r') as file:
        lines = [line.strip() for line in file if line.strip()]
    T = int(lines[0])
    idx = 1
    for case_num in range(1, T + 1):
        N, A, B = map(int, lines[idx].split())
        idx += 1
        print(f"Case #{case_num}: {solve(N, A, B)}")

if __name__ == "__main__":
    main()
