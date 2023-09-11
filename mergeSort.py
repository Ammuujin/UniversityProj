def merge_sort(arr):
    if len(arr) <= 1:
        return arr

    # Split the input array into two halves
    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]

    # Recursively sort each half
    left_half = merge_sort(left_half)
    right_half = merge_sort(right_half)

    # Merge the sorted halves
    sorted_arr = merge(left_half, right_half)
    
    return sorted_arr

def merge(left, right):
    result = []
    left_index, right_index = 0, 0

    while left_index < len(left) and right_index < len(right):
        if left[left_index] < right[right_index]:
            result.append(left[left_index])
            left_index += 1
        else:
            result.append(right[right_index])
            right_index += 1

    # Append any remaining elements from both halves (if any)
    result.extend(left[left_index:])
    result.extend(right[right_index:])
    
    return result

# Example usage:
my_list = [3, 6, 8, 10, 1, 2, 1]
print("Original list is:", my_list)
print("After sorting:")
sorted_list = merge_sort(my_list)
print("Sorted list is:", sorted_list)
