def heapify(arr, n, i):
    largest = i  # Initialize the largest as the root
    left = 2 * i + 1  # Left child
    right = 2 * i + 2  # Right child

    # Check if the left child exists and is greater than the root
    if left < n and arr[left] > arr[largest]:
        largest = left

    # Check if the right child exists and is greater than the root
    if right < n and arr[right] > arr[largest]:
        largest = right

    # Change the root if needed
    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]  # Swap
        heapify(arr, n, largest)  # Recursively heapify the affected subtree

def heap_sort(arr):
    n = len(arr)

    # Build a max heap
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    # Extract elements one by one
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # Swap the root (maximum element) with the last element
        heapify(arr, i, 0)  # Call max heapify on the reduced heap

# Example usage:
my_list = [38, 27, 43, 3, 9, 82, 10]
print("Original list is:", my_list)
print("After sorting:")
heap_sort(my_list)
print("Sorted list is:", my_list)
