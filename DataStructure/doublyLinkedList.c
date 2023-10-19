#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct Node {
    char data[100];             
    struct Node* prev;
    struct Node* next;
} Node;
Node* createNode(char* data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    strcpy(newNode->data, data);
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}
void insertBefore(Node** P, char* data) {
    Node* newNode = createNode(data);
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
void insertAfter(Node** P, char* data) {
    Node* newNode = createNode(data);
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
void delete(Node** P) {
    if (*P == NULL) return;

    if ((*P)->prev != NULL) {
        (*P)->prev->next = (*P)->next;
    }
    if ((*P)->next != NULL) {
        (*P)->next->prev = (*P)->prev;
    }

    free(*P);
    *P = NULL;
}
void printReverse(Node* tail) {
    while (tail != NULL) {
        printf("%s ", tail->data);
        tail = tail->prev;
    }
    printf("\n");
}
int main() {
    Node* head = NULL;
    insertAfter(&head, "Hi");
    insertAfter(&head, "Amuujin");
    insertBefore(&head, "Good morning!");
    delete(&head->next);  // Deletes "World"
    printReverse(head->next); // Starts from the last node and prints in reverse
    return 0;
}