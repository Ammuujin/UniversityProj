#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct Node {
    char data[100];
    struct Node* prev;
    struct Node* next;
} Node;
typedef Node* NodePtr;
NodePtr createNode(char* data) {
    NodePtr newNode = (NodePtr)malloc(sizeof(Node));
    strcpy(newNode->data, data);
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}
void insertBefore(NodePtr* P, char* data) {
    NodePtr newNode = createNode(data);
    if (*P == NULL) {
        *P = newNode;
        return;
    }
    newNode->next = *P;
    newNode->prev = (*P)->prev;
    if ((*P)->prev != NULL) {
        (*P)->prev->next = newNode;
    }
    (*P)->prev = newNode;
}
void insertAfter(NodePtr* P, char* data) {
    NodePtr newNode = createNode(data);
    if (*P == NULL) {
        *P = newNode;
        return;
    }
    newNode->prev = *P;
    newNode->next = (*P)->next;
    if ((*P)->next != NULL) {
        (*P)->next->prev = newNode;
    }
    (*P)->next = newNode;
}
void delete(NodePtr* P) {
    if (*P == NULL) return;
    NodePtr toDelete = *P;
    if (toDelete->prev != NULL) {
        toDelete->prev->next = toDelete->next;
    }
    if (toDelete->next != NULL) {
        toDelete->next->prev = toDelete->prev;
    }
    free(toDelete);
    *P = NULL;
}
void printReverse(NodePtr head) {
    if (head == NULL) return;
    // Traverse
    while (head->next != NULL) {
        head = head->next;
    }
    // Print in reverse
    while (head != NULL) {
        printf("%s ", head->data);
        head = head->prev;
    }
    printf("\n");
}
int main() {
    NodePtr head = NULL;
    insertAfter(&head, "Hi");
    insertAfter(&head, "Students!");
    insertBefore(&head, "Amuujin");
    delete(&head->next);
    printReverse(head); 
    return 0;
}