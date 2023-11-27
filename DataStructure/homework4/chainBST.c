#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    char data;
    struct Node *left, *right;
    int height;
} Node;

// Function prototypes
Node* newNode(char data);
int height(Node *N);
int max(int a, int b); // Prototype for max function
int getBalance(Node *N);
Node* rightRotate(Node *y);
Node* leftRotate(Node *x);
void Levelorder(Node *root);
Node* balanceTree(Node *root);

// A utility function to create a new Binary Tree Node
Node* newNode(char data) {
    Node* temp = (Node*)malloc(sizeof(Node));
    temp->data = data;
    temp->left = temp->right = NULL;
    temp->height = 1;  // new node is initially added at leaf
    return temp;
}

// A utility function to get the height of the tree
int height(Node *N) {
    if (N == NULL) return 0;
    return N->height;
}

// A utility function to get the balance factor of the tree
int getBalance(Node *N) {
    if (N == NULL) return 0;
    return height(N->left) - height(N->right);
}

// Right rotate utility function
Node* rightRotate(Node* y) {
    Node* x = y->left;
    Node* T2 = x->right;
    x->right = y;
    y->left = T2;
    // Update heights
    y->height = max(height(y->left), height(y->right)) + 1;
    x->height = max(height(x->left), height(x->right)) + 1;
    // Return new root
    return x;
}

// Left rotate utility function
Node* leftRotate(Node* x) {
    Node* y = x->right;
    Node* T2 = y->left;
    y->left = x;
    x->right = T2;
    // Update heights
    x->height = max(height(x->left), height(x->right)) + 1;
    y->height = max(height(y->left), height(y->right)) + 1;
    // Return new root
    return y;
}

// Function to print level order traversal of tree
void Levelorder(Node* root) {
    if (root == NULL) return;
    Node *queue[100];
    int front = 0, rear = 0;
    queue[rear++] = root; // Enqueue root
    while (front < rear) {
        // Print front of queue and remove it from queue
        Node* node = queue[front++];
        printf("%c ", node->data);
        /* Enqueue left child */
        if (node->left != NULL)
            queue[rear++] = node->left;
        /*Enqueue right child */
        if (node->right != NULL)
            queue[rear++] = node->right;
    }
    printf("\n");
}

// This function balances an unbalanced BST into an AVL tree
Node* balanceTree(Node* root) {
    // Get the balance factor to check whether this node became unbalanced
    int balance = getBalance(root);
    // If this node becomes unbalanced, then there are 4 cases
    // 1. Left Left Case
    if (balance > 1 && getBalance(root->left) >= 0)
        return rightRotate(root);
    // 2. Left Right Case
    if (balance > 1 && getBalance(root->left) < 0) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }
    // 3. Right Right Case
    if (balance < -1 && getBalance(root->right) <= 0)
        return leftRotate(root);
    // 4. Right Left Case
    if (balance < -1 && getBalance(root->right) > 0) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }
    return root; // return the unchanged node pointer
}

// A utility function to get maximum of two integers
int max(int a, int b) {
    return (a > b) ? a : b;
}

int main() {
    Node *root = newNode('D');
    root->left = newNode('C');
    root->left->left = newNode('B');
    root->left->left->left = newNode('A');
    printf("Level order traversal before balancing:\n");
    Levelorder(root);
    root = balanceTree(root);
    printf("Level order traversal after balancing:\n");
    Levelorder(root);
    return 0;
}