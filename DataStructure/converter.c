#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h> // Include ctype.h for isdigit function

// Function to convert a number from one base to another
void convertBase(char *number, int base1, int base2) {
    int num = 0;
    int length = strlen(number);
    
    // Convert the number from base1 to base 10
    for (int i = 0; i < length; i++) {
        int digit = (isdigit(number[i])) ? (number[i] - '0') : (number[i] - 'A' + 10);
        num = num * base1 + digit;
    }
    
    // Convert the number from base 10 to base2
    char result[100];
    int index = 0;
    while (num > 0) {
        int digit = num % base2;
        result[index] = (digit < 10) ? ('0' + digit) : ('A' + digit - 10);
        num /= base2;
        index++;
    }
    
    // Print the result in reverse order
    printf("The number in base %d is: ", base2);
    for (int i = index - 1; i >= 0; i--) {
        printf("%c", result[i]);
    }
    printf("\n");
}

int main() {
    int base1, base2;
    char number[100];

    printf("Enter the base of your number: ");
    scanf("%d", &base1);

    printf("Enter the number: ");
    scanf("%s", number);

    printf("Enter the base you want to convert: ");
    scanf("%d", &base2);

    if (base1 < 2 || base1 > 36 || base2 < 2 || base2 > 36) {
        printf("Invalid base. Please enter a base between 2 and 36.\n");
        return 1;
    }

    convertBase(number, base1, base2);

    return 0;
}
