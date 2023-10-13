def interpolation_search(arr, target):
    low = 0
    high = len(arr) - 1
    
    while low <= high and arr[low] <= target <= arr[high]:
        # Estimate the position of the target element
        pos = low + ((target - arr[low]) * (high - low)) // (arr[high] - arr[low])
        
        if arr[pos] == target:
            return pos  # Element found
        elif arr[pos] < target:
            low = pos + 1  # Search in the right subarray
        else:
            high = pos - 1  # Search in the left subarray
    
    return -1  # Element not found

# Example usage:
sorted_list = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
target_element = 7
print(sorted_list)
result = interpolation_search(sorted_list, target_element)

if result != -1:
    print(f"Element {target_element} found at index {result}")
else:
    print(f"Element {target_element} not found in the list")
