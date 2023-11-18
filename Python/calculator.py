import tkinter as tk
from tkinter import messagebox

def verify_login():
    username = entry_username.get()
    password = entry_password.get()
    
    if username == "admin" and password == "password":
        root.destroy()
        open_calculator()
    else:
        messagebox.showerror("Login Failed", "Incorrect username or password")

def open_calculator():
    def on_click(char):
        if char == '=':
            try:
                result = eval(display.get())
                display.delete(0, tk.END)
                display.insert(tk.END, str(result))
            except Exception as e:
                display.delete(0, tk.END)
                display.insert(tk.END, "Error")
        elif char == 'C':
            display.delete(0, tk.END)
        else:
            display.insert(tk.END, char)

    calc = tk.Tk()
    calc.title("Calculator")

    # Create display
    display = tk.Entry(calc)
    display.grid(row=0, column=0, columnspan=4)

    # Create buttons
    buttons = [
        '7', '8', '9', '+',
        '4', '5', '6', '-',
        '1', '2', '3', '*',
        'C', '0', '=', '/'
    ]

    row = 1
    col = 0
    for char in buttons:
        button = tk.Button(calc, text=char, command=lambda ch=char: on_click(ch))
        button.grid(row=row, column=col, sticky="nsew")
        
        col += 1
        if col > 3:
            col = 0
            row += 1

    # Improve the layout
    for i in range(4):
        calc.grid_columnconfigure(i, weight=1)
    for i in range(1, 5):
        calc.grid_rowconfigure(i, weight=1)

    calc.mainloop()

# Create the main window for login
root = tk.Tk()
root.title("Login Screen")

# Create a frame for the login fields
frame_login = tk.Frame(root)
frame_login.pack(padx=10, pady=10)

# Username field
label_username = tk.Label(frame_login, text="Username:")
label_username.pack()
entry_username = tk.Entry(frame_login)
entry_username.pack()

# Password field
label_password = tk.Label(frame_login, text="Password:")
label_password.pack()
entry_password = tk.Entry(frame_login, show="*")
entry_password.pack()

# Login button
button_login = tk.Button(frame_login, text="Login", command=verify_login)
button_login.pack()

root.mainloop()
