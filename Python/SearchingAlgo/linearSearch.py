def linear_search(arr, target):
    for i, element in enumerate(arr):
        if element == target:
            return i  # Return the index of the target element
    return -1  # Return -1 if the target element is not found

# Example usage:
my_list = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5]
target_element = 5
print(my_list)
result = linear_search(my_list, target_element)
if result != -1:
    print(f"Element {target_element} found at index {result}")
else:
    print(f"Element {target_element} not found in the list")
