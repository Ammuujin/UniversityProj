class TreeNode:
    def __init__(self, key):
        self.left = None
        self.right = None
        self.val = key

class BinarySearchTree:
    def __init__(self):
        self.root = None

    def insert(self, key):
        if self.root is None:
            self.root = TreeNode(key)
        else:
            self._insert(self.root, key)

    def _insert(self, curr_node, key):
        if key < curr_node.val:
            if curr_node.left is None:
                curr_node.left = TreeNode(key)
            else:
                self._insert(curr_node.left, key)
        elif key > curr_node.val:
            if curr_node.right is None:
                curr_node.right = TreeNode(key)
            else:
                self._insert(curr_node.right, key)
        else:
            print("Value already in tree!")

    def inorder(self, root):
        if root:
            self.inorder(root.left)
            print(root.val, end=' ')
            self.inorder(root.right)

# Example usage:
bst = BinarySearchTree()
bst.insert(8)
bst.insert(3)
bst.insert(10)
bst.insert(1)
bst.insert(6)
bst.insert(14)
bst.insert(4)
bst.insert(7)

bst.inorder(bst.root)  # Will print the numbers in sorted order
print(bst)
