#include <stdio.h>
#include <string.h>
#include <ctype.h>

// Prototype
void convert(int num, int base, char *result);
void reverseString(char *str);

// Main function
int main() {
    //d is the number in base 10, x is the base to convert to
    int d, x;
    char result[100];
    printf("Enter a number in base 10: ");
    scanf("%d", &d);
    if(d < 0) {
        printf("Invalid input\n");
        return 0;
    }else{
        printf("Enter the base to convert to: ");
        scanf("%d", &x);
        if(x>0){
            convert(d, x, result);
            printf("The number %d in base %d is %s\n", d, x, result);
        }else{
            printf("Invalid input\n");
        }
    }
    return 0;
}

// Function to convert a number from base 10 to a given base
void convert(int num, int base, char *result) {
    int i = 0;
    while (num > 0) {
        int rem = num % base;
        if (rem < 10)
            result[i++] = rem + '0';
        else
            result[i++] = rem - 10 + 'A';
        num /= base;
    }
    result[i] = '\0';
    reverseString(result); // Reverse the result string
}

// Function to reverse a string
void reverseString(char *str) {
    int length = strlen(str);
    int i, j;
    for (i = 0, j = length - 1; i < j; i++, j--) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
