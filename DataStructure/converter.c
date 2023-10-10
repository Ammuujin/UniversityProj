//Program that converts a decimal number to a given base number and vice versa.
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

// Function prototypes
int task1(int number, int base);
int task2(char *number, int base);

// Main function
int main() {
    int task, x; // task: 1 for task 1, 2 for task 2, x: base number, number: decimal number
    char d[100];
    printf("Enter your inputs: \n ");
    scanf("%d", &task);
    scanf("%s %d", d, &x);
    if(atoi(d) >= 0 && x>0) {
        if (task == 1) {
            task1(atoi(d), x);
        } else if (task == 2) {
            task2(d, x);
        }
    }
    else {
        printf("Invalid inputs! check them.\n");
    }
    return 0;
}

// Task 1: Converting decimal number to a given base number.
int task1(int number, int base) {
    int i = 0;
    char result[100]; 
    while (number != 0) {
        int remainder = number % base;
        if (remainder < 10) {
            result[i] = remainder + '0';  
        } else {
            result[i] = remainder - 10 + 'A';  
        }
        number = number / base;
        i++;
    }
    for (int j = i - 1; j >= 0; j--) {
        printf("%c", result[j]);
    }
    printf("\n");
    return 0;
}

// Task 2: Converting a given base number to decimal number.
int task2(char *number, int base) {
    int result = 0;
    int length = strlen(number);
    int power = 0;
    for (int i = length - 1; i >= 0; i--) {
        char digit = number[i];
        int value;
        if (isdigit(digit)) {
            value = digit - '0';
        } else if (isxdigit(digit)) {
            value = toupper(digit) - 'A' + 10;
        } else {
            printf("Check your input again!\n");
            return 1;
        }
        result += value * pow(base, power);
        power++;
    }
    printf("%d\n", result);
    return 0;
}