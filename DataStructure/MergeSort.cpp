#include <iostream>
#include <vector>

// Function to merge two halves of a vector
void merge(std::vector<int>& arr, int left, int middle, int right) {
    int n1 = middle - left + 1; // Size of left subarray
    int n2 = right - middle;    // Size of right subarray

    // Create temporary vectors
    std::vector<int> L(n1), R(n2);

    // Copy data to temporary vectors L[] and R[]
    for (int i = 0; i < n1; i++)
        L[i] = arr[left + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[middle + 1 + j];

    // Merge the temp vectors back into arr[l..r]
    int i = 0; // Initial index of first subarray
    int j = 0; // Initial index of second subarray
    int k = left; // Initial index of merged subarray

    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    // Copy the remaining elements of L[], if there are any
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    // Copy the remaining elements of R[], if there are any
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

// Function to implement merge sort
void mergeSort(std::vector<int>& arr, int left, int right) {
    if (left < right) {
        // Find the middle point
        int middle = left + (right - left) / 2;

        // Sort first and second halves
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        // Merge the sorted halves
        merge(arr, left, middle, right);
    }
}

// Utility function to print the array
void printArray(const std::vector<int>& arr) {
    for (int i = 0; i < arr.size(); i++)
        std::cout << arr[i] << " ";
    std::cout << "\n";
}

// Example usage
int main() {
    std::vector<int> arr = {12, 11, 13, 5, 6, 7};
    std::cout << "Given array is \n";
    printArray(arr);

    mergeSort(arr, 0, arr.size() - 1);

    std::cout << "\nSorted array is \n";
    printArray(arr);
    return 0;
}
