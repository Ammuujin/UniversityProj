def insertion_sort(arr):
    for i in range(1, len(arr)):
        key = arr[i]  # Current element to be inserted
        j = i - 1  # Index of the previous element

        # Move elements of arr[0..i-1], which are greater than key, one position ahead of their current position
        while j >= 0 and key < arr[j]:
            arr[j + 1] = arr[j]
            j -= 1

        # Place the key in its correct position
        arr[j + 1] = key

# Example usage:
my_list = [38, 27, 43, 3, 9, 82, 10]
print("Original list is:", my_list)
print("After sorting:")
insertion_sort(my_list)
print("Sorted list is:", my_list)
