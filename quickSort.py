def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quick_sort(left) + middle + quick_sort(right)
#Test
my_list = [3, 6, 8, 10, 1, 2, 1]
print("Original list is:", my_list)
print("After sorting:")
sorted_list = quick_sort(my_list)
print("Sorted list is:", sorted_list)