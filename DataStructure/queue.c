#include <stdio.h>
#include <stdbool.h>
#define SIZE 100 
typedef struct {
    int items[SIZE];
    int front;
    int rear;
} Queue;
// Initialize the queue
void initializeQueue(Queue* q) {
    q->front = -1;
    q->rear = -1;
}
// Check if the queue is full
bool isFull(Queue* q) {
    return q->rear == SIZE - 1;
}
// Check if the queue is empty
bool isEmpty(Queue* q) {
    return q->front == -1;
}
// Add an element to the queue
void Enqueue(Queue* q, int value) {
    if (isFull(q)) {
        printf("Queue is full!\n");
        return;
    }
    if (isEmpty(q)) {
        q->front = 0;
    }
    q->rear++;
    q->items[q->rear] = value;
}
// Remove an element from the queue
int Dequeue(Queue* q) {
    if (isEmpty(q)) {
        printf("Queue is empty!\n");
        return -1;
    }
    int item = q->items[q->front];
    q->front++;
    if (q->front > q->rear) {
        initializeQueue(q);  // Reset the queue if all elements are removed
    }
    return item;
}
int main() {
    Queue q;
    initializeQueue(&q);
    Enqueue(&q, 10);
    Enqueue(&q, 20);
    Enqueue(&q, 30);
    printf("Dequeued: %d\n", Dequeue(&q));
    printf("Dequeued: %d\n", Dequeue(&q));
    return 0;
}