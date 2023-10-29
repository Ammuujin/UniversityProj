#include <stdio.h>
#include <stdlib.h>

// Definition of the tree node
typedef struct Node {
    char data;
    struct Node* left;
    struct Node* middle1;
    struct Node* middle2;
    struct Node* right;
} Node;

// Queue for level order traversal
typedef struct Queue {
    Node* node;
    struct Queue* next;
} Queue;

Queue* front = NULL;
Queue* rear = NULL;

// Function to enqueue
void enqueue(Node* n) {
    Queue* tmp = (Queue*)malloc(sizeof(Queue));
    tmp->node = n;
    tmp->next = NULL;
    if (rear == NULL) {
        front = rear = tmp;
        return;
    }
    rear->next = tmp;
    rear = tmp;
}

// Function to dequeue
Node* dequeue() {
    if (front == NULL)
        return NULL;

    Queue* tmp = front;
    Node* n = tmp->node;
    front = front->next;
    if (front == NULL)
        rear = NULL;

    free(tmp);
    return n;
}

// Function to create a new node
Node* createNode(char data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->left = NULL;
    newNode->middle1 = NULL;
    newNode->middle2 = NULL;
    newNode->right = NULL;
    return newNode;
}

void printPreorder(Node* root) {
    if (root == NULL) return;
    printf("%c ", root->data);
    printPreorder(root->left);
    printPreorder(root->middle1);
    printPreorder(root->middle2);
    printPreorder(root->right);
}

void printPostorder(Node* root) {
    if (root == NULL) return;
    printPostorder(root->left);
    printPostorder(root->middle1);
    printPostorder(root->middle2);
    printPostorder(root->right);
    printf("%c ", root->data);
}

void printInorder(Node* root) {
    if (root == NULL) return;
    printInorder(root->left);
    printf("%c ", root->data);
    printInorder(root->middle1);
    printInorder(root->middle2);
    printInorder(root->right);
}

void printLevelOrder(Node* root) {
    if (root == NULL) return;
    enqueue(root);
    while (front != NULL) {
        Node* current = dequeue();
        printf("%c ", current->data);
        if (current->left) enqueue(current->left);
        if (current->middle1) enqueue(current->middle1);
        if (current->middle2) enqueue(current->middle2);
        if (current->right) enqueue(current->right);
    }
}

// Helper function for printPreorderNext
void preorderNextHelper(Node* root, char data, char* found) {
    if (root == NULL || *found == 'Y')
        return;

    if (*found == 'F') {
        printf("Next node after %c in preorder traversal is %c\n", data, root->data);
        *found = 'Y';
        return;
    }

    if (root->data == data)
        *found = 'F';

    preorderNextHelper(root->left, data, found);
    preorderNextHelper(root->middle1, data, found);
    preorderNextHelper(root->middle2, data, found);
    preorderNextHelper(root->right, data, found);
}

void printPreorderNext(Node* root, char data) {
    char found = 'N';
    preorderNextHelper(root, data, &found);
    if (found != 'Y')
        printf("There is no node after %c in preorder traversal.\n", data);
}

int main() {
    Node* root = createNode('A');
    root->left = createNode('B');
    root->middle1 = createNode('B');
    root->middle2 = createNode('C');
    root->right = createNode('D');
    
    root->left->left = createNode('E');
    root->left->right = createNode('F');
    root->middle1->left = createNode('F');
    root->middle2->left = createNode('G');
    root->middle2->middle1 = createNode('H');
    root->middle2->right = createNode('I');

    printf("Preorder traversal: ");
    printPreorder(root);
    printf("\n");

    printf("Postorder traversal: ");
    printPostorder(root);
    printf("\n");

    printf("Inorder traversal: ");
    printInorder(root);
    printf("\n");

    printf("Level order traversal: ");
    printLevelOrder(root);
    printf("\n");

    printPreorderNext(root, 'B');

    free(root);
    return 0;
}
