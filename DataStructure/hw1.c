#include <stdio.h>
#include <stdlib.h>

//Structures:
// Define the structure for complex numbers
typedef struct {
    double real;
    double imaginary;
} ComplexNumber;

// Structure for a list of complex numbers
typedef struct {
    ComplexNumber *numbers;
    int size;
} ComplexNumberList;

//Function Prototype
void setRealPart(ComplexNumber *num, double real);
void setImaginaryPart(ComplexNumber *num, double imaginary);
void printComplexNumber(ComplexNumber num);
ComplexNumber add(ComplexNumber num1, ComplexNumber num2);
ComplexNumber sub(ComplexNumber num1, ComplexNumber num2);
ComplexNumber multiply(ComplexNumber num1, ComplexNumber num2);
ComplexNumber divide(ComplexNumber num1, ComplexNumber num2);
void insert(ComplexNumberList *list, ComplexNumber num, int index);
void delete(ComplexNumberList *list, int index);

//Functions:
//Setter methods:
// Function to set the real part of a complex number
void setRealPart(ComplexNumber *num, double real) {
    num->real = real;
}

// Function to set the imaginary part of a complex number
void setImaginaryPart(ComplexNumber *num, double imaginary) {
    num->imaginary = imaginary;
}

//Getter method:
// Function to print a complex number
void printComplexNumber(ComplexNumber num) {
    if (num.imaginary >= 0)
        printf("%.2lf + %.2lfi\n", num.real, num.imaginary);
    else
        printf("%.2lf - %.2lfi\n", num.real, -num.imaginary);
}

//Mathematical Operations:
// Function to add two complex numbers
ComplexNumber add(ComplexNumber num1, ComplexNumber num2) {
    ComplexNumber result;
    result.real = num1.real + num2.real;
    result.imaginary = num1.imaginary + num2.imaginary;
    return result;
}

// Function to subtract two complex numbers
ComplexNumber sub(ComplexNumber num1, ComplexNumber num2) {
    ComplexNumber result;
    result.real = num1.real - num2.real;
    result.imaginary = num1.imaginary - num2.imaginary;
    return result;
}

// Function to multiply two complex numbers
ComplexNumber multiply(ComplexNumber num1, ComplexNumber num2) {
    ComplexNumber result;
    result.real = num1.real * num2.real - num1.imaginary * num2.imaginary;
    result.imaginary = num1.real * num2.imaginary + num1.imaginary * num2.real;
    return result;
}

// Function to divide two complex numbers
ComplexNumber divide(ComplexNumber num1, ComplexNumber num2) {
    ComplexNumber result;
    double denominator = num2.real * num2.real + num2.imaginary * num2.imaginary;
    result.real = (num1.real * num2.real + num1.imaginary * num2.imaginary) / denominator;
    result.imaginary = (num1.imaginary * num2.real - num1.real * num2.imaginary) / denominator;
    return result;
}

//List Functions:
// Function to insert a new complex number at a given location
void insert(ComplexNumberList *list, ComplexNumber num, int index) {
    if (index < 0 || index > list->size) {
        printf("Invalid index for insertion.\n");
        return;
    }
    
    // Increase the size of the list
    list->size++;
    list->numbers = (ComplexNumber *)realloc(list->numbers, list->size * sizeof(ComplexNumber));
    
    // Shift elements to the right to make space for the new number
    for (int i = list->size - 1; i > index; i--) {
        list->numbers[i] = list->numbers[i - 1];
    }
    
    // Insert the new number at the specified index
    list->numbers[index] = num;
}

// Function to delete a complex number from a given location
void delete(ComplexNumberList *list, int index) {
    if (index < 0 || index >= list->size) {
        printf("Invalid index for deletion.\n");
        return;
    }
    
    // Shift elements to the left to fill the gap
    for (int i = index; i < list->size - 1; i++) {
        list->numbers[i] = list->numbers[i + 1];
    }
    
    // Decrease the size of the list
    list->size--;
    list->numbers = (ComplexNumber *)realloc(list->numbers, list->size * sizeof(ComplexNumber));
}

//Main Function:
int main() {
    // Create a list of complex numbers
    ComplexNumberList list;
    list.size = 0;
    list.numbers = NULL;
    
    //Sample data:
    // Insert some complex numbers into the list
    ComplexNumber num1 = {5.0, 1.0};
    ComplexNumber num2 = {2.0, -6.0};
    insert(&list, num1, 0);
    insert(&list, num2, 1);
    
    // Print the list
    printf("Complex Numbers:\n");
    for (int i = 0; i < list.size; i++) {
        printf("Element %d: ", i + 1);
        printComplexNumber(list.numbers[i]);
    }
    
    // Perform mathematical operations on the list
    ComplexNumber sum = {0.0, 0.0};
    ComplexNumber difference = {0.0, 0.0};
    ComplexNumber product = {1.0, 0.0};
    ComplexNumber quotient = {1.0, 0.0};
    
    for (int i = 0; i < list.size; i++) {
        sum = add(sum, list.numbers[i]);
        difference = sub(difference, list.numbers[i]);
        product = multiply(product, list.numbers[i]);
        if (i > 0) {
            quotient = divide(quotient, list.numbers[i]);
        } else {
            quotient = list.numbers[i];
        }
    }
    
    // Print the results of mathematical operations
    printf("Mathematical Operations:\n");
    printf("Sum: ");
    printComplexNumber(sum);
    printf("Difference: ");
    printComplexNumber(difference);
    printf("Product: ");
    printComplexNumber(product);
    printf("Quotient: ");
    printComplexNumber(quotient);
    
    // Clean up the memory
    free(list.numbers);
    
    return 0;
}
