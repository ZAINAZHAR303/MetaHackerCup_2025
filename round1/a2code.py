from collections import deque

def can_reach_all(heights, ladder_length):
    n = len(heights)
    visited = [False] * n
    queue = deque()
    for i in range(n):
        if heights[i] <= ladder_length:
            queue.append(i)
            visited[i] = True
    while queue:
        current = queue.popleft()
        for neighbor in [current - 1, current + 1]:
            if 0 <= neighbor < n and not visited[neighbor]:
                if abs(heights[current] - heights[neighbor]) <= ladder_length:
                    visited[neighbor] = True
                    queue.append(neighbor)
    return all(visited)

def shortest_ladder_length(heights):
    left, right = 0, max(heights)
    while left < right:
        mid = (left + right) // 2
        if can_reach_all(heights, mid):
            right = mid
        else:
            left = mid + 1
    return left

def main():
    with open('input.txt', 'r') as file:
        data = file.read().strip().split()
    T = int(data[0])
    index = 1
    for case_num in range(1, T + 1):
        N = int(data[index])
        index += 1
        heights = list(map(int, data[index:index+N]))
        index += N
        result = shortest_ladder_length(heights)
        print(f"Case #{case_num}: {result}")

if __name__ == "__main__":
    main()
