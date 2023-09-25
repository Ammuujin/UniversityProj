#include <stdio.h>
#include <stdlib.h>
//program that convert anybase numbers to anybase numbers. Such as, converting 12nd base number to hexadecimal number. 
int main(){
    double num1, num2;
    int base1 = 0, base2 = 0;
    printf("Enter the base of your number: ");
    scanf("%d", &base1);
    printf("Enter the number: ");
    scanf("%lf", &num1);
    printf("Enter the base you want to convert: ");
    scanf("%d", base2);
    num2 = num1;
    int intPart = (int)num2;
    double decPart = num2 - intPart;
    int i = 0;
    int intPart2[100];
    double decPart2[100];
    while(intPart != 0){
        intPart2[i] = intPart % base2;
        intPart /= base2;
        i++;
    }
    int j = 0;
    while(decPart != 0){
        decPart2[j] = decPart * base2;
        decPart = decPart2[j] - (int)decPart2[j];
        j++;
    }
    printf("The number in base %d is: ", base2);
    for(int k = i - 1; k >= 0; k--){
        printf("%d", intPart2[k]);
    }
    printf(".");
    for(int k = 0; k < j; k++){
        printf("%d", (int)decPart2[k]);
    }
    printf("\n");
    return 0;
}