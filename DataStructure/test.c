#include <stdio.h>

// Simulated CPU Registers
int R0 = 0, R1 = 0;

// Simulated Memory
int memory[4]; // memory[0] for x, memory[1] for y, memory[2] for z, memory[3] for c

// Function to simulate the LOAD operation
void LOAD(int reg, int memAddr) {
    if (reg == 0) R0 = memory[memAddr];
    else if (reg == 1) R1 = memory[memAddr];
}

// Function to simulate the ADD operation
void ADD() {
    R0 = R0 + R1;
}

// Function to simulate the DIV operation
void DIV() {
    R0 = R1 / R0;
}

// Function to simulate the MUL operation
void MUL() {
    R0 = R0 * R1;
}

int main() {
    // Initialize memory
    memory[0] = 5;  // x
    memory[1] = 10; // y
    memory[2] = 300;// z

    // Perform the operations: c = z/(x+y)*y
    // Load x into R0
    LOAD(0, 0);
    // Load y into R1
    LOAD(1, 1);
    // Add R0 and R1, store result in R0
    ADD();
    // Load z into R1
    LOAD(1, 2);
    // Divide R1 by R0, store result in R0
    DIV();
    // Load y into R1
    LOAD(1, 1);
    // Multiply R0 and R1, store result in R0
    MUL();
    
    // Store the result in memory
    memory[3] = R0;
    // Output the result
    printf("Result of c = z/(x+y)*y: %d\n", memory[3]);
    return 0;
}
