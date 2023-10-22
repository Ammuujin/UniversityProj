.data
task: .word 0
x: .word 0
d: .space 100
result: .space 100
.stack 4096
stack_top:

    .text
    .globl main

main:
    la sp, stack_top
    la a0, task_prompt   ; Prompt for task number
    li a7, 4
    ecall
    li a7, 5
    ecall
    sw a0, task
    la a0, number_prompt ; Prompt for number
    li a7, 4
    ecall
    li a7, 5
    ecall
    sw a0, d
    la a0, base_prompt   ; Prompt for base
    li a7, 4
    ecall
    li a7, 5
    ecall
    sw a0, x
    lw a0, task
    li a1, 1
    beq a0, a1, convert_decimal_to_base
    li a1, 2
    beq a0, a1, convert_base_to_decimal
    la a0, invalid_task_message ; Feedback for invalid task number
    li a7, 4
    ecall
    li a7, 10  ; Exit
    ecall

convert_decimal_to_base:
    lw a0, d
    li t6, 0                    
    bltz a0, set_negative_flag    
    lw a1, x
    la t0, result
    addi t1, t0, 99
    j convert_loop

set_negative_flag:
    li t6, 1                     
    neg a0, a0                   
    lw a1, x
    la t0, result
    addi t1, t0, 99

convert_loop:
    beqz a0, print_result
    rem a2, a0, a1
    blt a2, 10, store_digit
    addi a2, a2, 55
    j store_result

store_digit:
    addi a2, a2, '0' 

store_result:
    sb a2, 0(t1)
    addi t1, t1, -1
    div a0, a0, a1
    mflo a0
    j convert_loop

print_result:
    beqz t6, no_neg_sign     
    li a0, '-'
    li a7, 11
    ecall

no_neg_sign:
    addi t1, t1, 1


print_loop:
    lbu a0, 0(t1)
    beqz a0, end_print
    li a7, 11
    ecall
    addi t1, t1, 1
    j print_loop

end_print:
    li a7, 10
    ecall

convert_base_to_decimal:
    lw a1, x
    la t0, d
    li t2, 0
    li t3, 0
    li t6, 0                     
    lb a0, 0(t0)
    li a2, '-'
    beq a0, a2, set_negative_base  
    j convert_base_loop

set_negative_base:
    li t6, 1                    
    addi t0, t0, 1              

convert_base_loop:
    lb a0, 0(t0)
    beqz a0, end_convert
    li a2, '9'
    blt a0, a2, is_digit
    li a2, 'A'
    sub a0, a0, a2
    addi a0, a0, 10
    j process_conversion

is_digit:
    li a2, '0'
    sub a0, a0, a2

process_conversion:
    li t4, 1
    move t5, t3

power_loop:
    beqz t5, end_power_loop
    mul t4, t4, a1
    addi t5, t5, -1
    j power_loop

end_power_loop:
    mul a0, a0, t4
    add t2, t2, a0
    addi t0, t0, 1
    addi t3, t3, 1
    j convert_base_loop

end_convert:
    beqz t6, no_neg_decimal      
    neg a0, a0                  

no_neg_decimal:
    move a0, t2
    li a7, 1
    ecall

end_program:
    la a0, program_complete_message ; Feedback for program completion
    li a7, 4
    ecall
    li a7, 10  ; Exit
    ecall

task_prompt:   .asciz "Select a task (1: Decimal to Base, 2: Base to Decimal): "
number_prompt: .asciz "Enter the number: "
base_prompt:   .asciz "Enter the base (between 2 and 36 inclusive): "
invalid_task_message: .asciz "Invalid task number selected.\n"
program_complete_message: .asciz "Program completed successfully!\n"