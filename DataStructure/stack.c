#include <stdio.h>
#include <stdlib.h>
// Node definition for our stack
typedef struct Node {
    int data;
    struct Node* next;
} Node;
// Stack definition
typedef struct Stack {
    Node* top;
    int length;
} Stack;
// Function to create a new node
Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    if (!newNode) {
        printf("Memory allocation failed. Exiting.\n");
        exit(1);
    }
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}
// Initialize the stack
void initializeStack(Stack* stack) {
    stack->top = NULL;
    stack->length = 0;
}
// Push an element onto the stack
void Push(Stack* stack, int data) {
    Node* newNode = createNode(data);
    newNode->next = stack->top;
    stack->top = newNode;
    stack->length++;
}
// Pop an element from the stack
int Pop(Stack* stack) {
    if (stack->top == NULL) {
        printf("Stack is empty.\n");
        return -1;
    }
    Node* temp = stack->top;
    stack->top = stack->top->next;
    int poppedData = temp->data;
    free(temp);
    stack->length--;
    return poppedData;
}
// Get the top element of the stack
int Top(Stack* stack) {
    if (stack->top == NULL) {
        printf("Stack is empty.\n");
        return -1;
    }
    return stack->top->data;
}
// Print the length of the stack
void printLength(Stack* stack) {
    printf("Length of the stack: %d\n", stack->length);
}
int main() {
    Stack stack;
    initializeStack(&stack);
    Push(&stack, 10);
    Push(&stack, 20);
    Push(&stack, 30);
    Push(&stack, 40);
    Push(&stack, 50);
    printf("Top element: %d\n", Top(&stack));
    printLength(&stack);
    printf("Popped element: %d\n", Pop(&stack));
    printf("Top element after pop: %d\n", Top(&stack));
    printLength(&stack);
    return 0;
}