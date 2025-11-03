def main():
    T = int(input())
    for case_num in range(1, T + 1):
        N = int(input())
        heights = list(map(int, input().split()))
        max_gap = 0
        for i in range(N - 1):
            gap = abs(heights[i] - heights[i + 1])
            if gap > max_gap:
                max_gap = gap
        print(f"Case #{case_num}: {max_gap}")

if __name__ == "__main__":
    main()

