#include <stdio.h>
#include <string.h>

void swap(char **a, char **b) {
    char *temp = *a;
    *a = *b;
    *b = temp;
}

void heapify(char *arr[], int n, int i) {
    int largest = i; // Initialize largest as root
    int left = 2*i + 1;
    int right = 2*i + 2;
    // If left child is larger than root
    if (left < n && strcmp(arr[left], arr[largest]) > 0)
        largest = left;
    // If right child is larger than largest so far
    if (right < n && strcmp(arr[right], arr[largest]) > 0)
        largest = right;
    // If largest is not root
    if (largest != i) {
        swap(&arr[i], &arr[largest]); 
        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest);
    }
}
// Function to perform heap sort
void heapSort(char *arr[], int n) {
    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);
    // One by one extract an element from heap
    for (int i=n-1; i>=0; i--) {
        // Move current root to end
        swap(&arr[0], &arr[i]);
        // Call max heapify on the reduced heap
        heapify(arr, i, 0);
    }
}

// Function to display the array
void display(char *arr[], int size) {
    for (int i = 0; i < size; i++)
        printf("%s\n", arr[i]);
}

int main() {
    char *names[] = {"John", "Shiny", "Bob", "Charlie", "Dave", "Alice"};
    int n = sizeof(names)/sizeof(names[0]);
    heapSort(names, n);
    printf("Ordered list:\n");
    display(names, n);
    return 0;
}