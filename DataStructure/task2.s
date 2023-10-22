.data
threshold: .float 0.1
video: .space 3000
movement_flag: .word 0  # Indicates if movement is detected
msg_movement: .asciz "Movement detected in the video!\n"
msg_no_movement: .asciz "No movement detected in the video.\n"

.text
main:
    li t0, 0  # time index
    li t1, 0  # x index
    li t2, 0  # y index

time_loop:
    li t1, 0

x_loop:
    li t2, 0

y_loop:
    # Compute address for current time, x, and y indices
    la t5, video
    li t9, 10  
    mul t6, t1, t9  
    add t6, t6, t2  
    li t9, 100  
    mul t7, t0, t9  
    slli t7, t7, 2  
    add t5, t5, t7  
    lflw f0, 0(t5)
    # Increment time and compute address for next frame
    addi t0, t0, 1
    la t6, video
    mul t7, t1, t9
    add t7, t7, t2
    mul t8, t0, t9
    slli t8, t8, 2
    add t6, t6, t8
    lflw f1, 0(t6)
    # Compute the absolute difference between consecutive frames
    fsub.s f2, f1, f0
    flt.s t8, f2, zero
    beqz t8, difference_positive
    fneg.s f2, f2  

difference_positive:
    # Compare the absolute difference with threshold
    la t7, threshold
    lflw f3, 0(t7)
    flt.s t8, f2, f3
    # If movement is detected, set flag and print message
    beqz t8, no_movement
    li t8, 1
    sw t8, movement_flag
    la a0, msg_movement
    li a7, 4
    ecall
    j end  

no_movement:
    addi t2, t2, 1
    blti t2, 10, y_loop
    addi t1, t1, 1
    blti t1, 10, x_loop
    addi t0, t0, 1
    blti t0, 29, time_loop
    lw t9, movement_detected
    beqz t9, no_movement_in_video
    la a0, msg_movement
    li a7, 4
    ecall
    j end

no_movement_in_video:
    # Print no movement detected message
    la a0, msg_no_movement
    li a7, 4
    ecall

end:
    li a7, 10            
    ecall                 
