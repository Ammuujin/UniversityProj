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
    print("Enter the base you want to convert: ");
    scanf("%d", base2);
    num2 = num1;
    int intPart = (int)num2;
    double decPart = num2 - intPart;
    int i = 0;
    int intPart2[100];
    while(intPart != 0){
        intPart2[i]=intPart % base2;
        intPart = intPart / base2;
        i++;
    }
    int j = 0;
}