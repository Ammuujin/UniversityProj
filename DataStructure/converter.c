#include <stdio.h>
#include <string.h>
#include <ctype.h>

void convertBase(char *number, int base1, int base2) {
    int num = 0;
    int length = strlen(number);
    
    // Convert the number from base1 to base 10
    for (int i = 0; i < length; i++) {
        int digit;
        if (isdigit(number[i])) {
            digit = number[i] - '0';
        } else {
            digit = number[i] - 'A' + 10;
        }
        num = num * base1 + digit;
    }
    
    // Convert the number from base 10 to base2
    char result[100];
    int index = 0;
    while (num > 0) {
        int digit = num % base2;
        if (digit < 10) {
            result[index] = '0' + digit;
        } else {
            result[index] = 'A' + digit - 10;
        }
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
    char number[100];
    int base1, base2;
    printf("Enter the number: ");
    scanf("%s", number);
    printf("Enter the base of the number: ");
    scanf("%d", &base1);
    printf("Enter the base to convert to: ");
    scanf("%d", &base2);
    convertBase(number, base1, base2);
    return 0;
}