def solve_case(N, A, B):
    # First N multipliers: all 1 (product = 1, always ≤ A)
    prefix = [1] * N
    # Next N multipliers: all 1 except last, which is B
    suffix = [1] * (N - 1) + [B]
    return prefix + suffix

def main():
    with open('input.txt', 'r') as file:
        lines = [line.strip() for line in file if line.strip()]
    T = int(lines[0])
    idx = 1
    for case_num in range(1, T + 1):
        N, A, B = map(int, lines[idx].split())
        idx += 1
        out_seq = solve_case(N, A, B)
        print(f"Case #{case_num}: {' '.join(str(x) for x in out_seq)}")

if __name__ == "__main__":
    main()
