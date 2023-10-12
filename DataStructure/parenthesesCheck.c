#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Structure for the stack
struct Stack {
    char data;
    struct Stack* next;
};

// Function to initialize the stack
void initialize(struct Stack** stack) {
    *stack = NULL; // Initialize the stack as empty
}

// Function to check if the stack is empty
bool isEmpty(struct Stack* stack) {
    return stack == NULL;
}

// Function to push an element onto the stack
void push(struct Stack** stack, char value) {
    struct Stack* newNode = (struct Stack*)malloc(sizeof(struct Stack));
    newNode->data = value;
    newNode->next = *stack;
    *stack = newNode;
}

// Function to pop an element from the stack
char pop(struct Stack** stack) {
    if (isEmpty(*stack)) {
        return '\0'; // Stack is empty
    }
    struct Stack* temp = *stack;
    *stack = temp->next;
    char poppedValue = temp->data;
    free(temp);
    return poppedValue;
}

// Function to check if parentheses in an expression are balanced
bool areParenthesesBalanced(const char* expression) {
    struct Stack* stack;
    initialize(&stack);

    for (int i = 0; expression[i] != '\0'; i++) {
        if (expression[i] == '(' || expression[i] == '[' || expression[i] == '{') {
            push(&stack, expression[i]);
        } else if (expression[i] == ')' || expression[i] == ']' || expression[i] == '}') {
            if (isEmpty(stack)) {
                return false; // Mismatched closing parenthesis
            }

            char popped = pop(&stack);

            if ((expression[i] == ')' && popped != '(') ||
                (expression[i] == ']' && popped != '[') ||
                (expression[i] == '}' && popped != '{')) {
                return false; // Mismatched parentheses
            }
        }
    }

    return isEmpty(stack); // The stack should be empty if parentheses are balanced
}

int main() {
    const char* expression = "{(A + B)} * [C - D]";
    if (areParenthesesBalanced(expression)) {
        printf("The expression is balanced.\n");
    } else {
        printf("The expression is not balanced.\n");
    }

    return 0;
}