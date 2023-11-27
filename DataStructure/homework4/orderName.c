#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    char data[101]; // Assuming names are no longer than 100 characters
    struct Node *left, *right;
} Node;
Node* createNode(const char* name) {
    Node* node = (Node*)malloc(sizeof(Node));
    strcpy(node->data, name);
    node->left = node->right = NULL;
    return node;
}
void swap(Node** a, Node** b) {
    Node* temp = *a;
    *a = *b;
    *b = temp;
}
void heapify(Node* arr[], int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    if (left < n && strcmp(arr[left]->data, arr[largest]->data) > 0)
        largest = left;
    if (right < n && strcmp(arr[right]->data, arr[largest]->data) > 0)
        largest = right;
    if (largest != i) {
        swap(&arr[i], &arr[largest]);
        heapify(arr, n, largest);
    }
}
Node** OrderListByCreatingHeap(char names[][101], int n) {
    Node** heap = (Node**)malloc(n * sizeof(Node*));
    for (int i = 0; i < n; ++i) {
        heap[i] = createNode(names[i]);
    }
    for (int i = n / 2 - 1; i >= 0; --i)
        heapify(heap, n, i);
    for (int i = n - 1; i >= 0; --i) {
        swap(&heap[0], &heap[i]);
        heapify(heap, i, 0);
    }
    return heap;
}
void display(Node* rootOfHeap[], int n) {
    for (int i = 0; i < n; ++i) {
        printf("%s\n", rootOfHeap[i]->data);
    }
}
int main() {
    char names[6][101] = {"John", "Shiny", "Bob", "Charlie", "Dave", "Alice"};
    int n = sizeof(names) / sizeof(names[0]);
    Node** rootOfHeap = OrderListByCreatingHeap(names, n);
    printf("Ordered list:\n");
    display(rootOfHeap, n);
    // Free the allocated memory
    for (int i = 0; i < n; ++i) {
        free(rootOfHeap[i]);
    }
    free(rootOfHeap);
    return 0;
}