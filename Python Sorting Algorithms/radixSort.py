def counting_sort(arr, exp):
    n = len(arr)
    output = [0] * n
    count = [0] * 10
    
    # Count the occurrences of digits at the current place value
    for i in range(n):
        index = arr[i] // exp
        count[index % 10] += 1
    
    # Calculate cumulative counts
    for i in range(1, 10):
        count[i] += count[i - 1]
    
    # Build the output array in sorted order
    i = n - 1
    while i >= 0:
        index = arr[i] // exp
        output[count[index % 10] - 1] = arr[i]
        count[index % 10] -= 1
        i -= 1
    
    # Copy the sorted elements back to the original array
    for i in range(n):
        arr[i] = output[i]

def radix_sort(arr):
    # Find the maximum number to know the number of digits
    max_num = max(arr)
    
    # Perform counting sort for every digit place value
    exp = 1
    while max_num // exp > 0:
        counting_sort(arr, exp)
        exp *= 10

# Example usage:
arr = [170, 45, 75, 90, 802, 24, 2, 66]
print("Original array:", arr)
radix_sort(arr)
print("Sorted array:", arr)
