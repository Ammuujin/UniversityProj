# Define a TreeNode class to represent nodes in the binary search tree (BST)
class TreeNode:
    def __init__(self, key):
        self.left = None
        self.right = None
        self.value = key

# Insert a new value into the BST while maintaining the BST property
def insert(root, key):
    if root is None:
        return TreeNode(key)
    else:
        if key < root.value:
            root.left = insert(root.left, key)
        else:
            root.right = insert(root.right, key)
    return root

# Perform an in-order traversal of the BST and append values to the result list
def in_order_traversal(root, result):
    if root:
        in_order_traversal(root.left, result)
        result.append(root.value)
        in_order_traversal(root.right, result)

# Tree Sort function
def tree_sort(arr):
    root = None
    # Insert each element from the input array into the BST
    for item in arr:
        root = insert(root, item)

    result = []
    # Perform an in-order traversal to retrieve the sorted values
    in_order_traversal(root, result)
    return result

# Example usage:
my_list = [38, 27, 43, 3, 9, 82, 10]
print("Original list is:", my_list)
print("After sorting:")
sorted_list = tree_sort(my_list)
print("Tree Sort Result:", sorted_list)
