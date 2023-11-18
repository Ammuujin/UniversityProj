import tkinter as tk
from tkinter import messagebox
import math  # Importing the math module


def register_user():
    # Implement registration logic here
    pass


def verify_login():
    global root
    username = entry_username.get()
    password = entry_password.get()

    if not username or not password:
        messagebox.showerror("Login Failed", "Username or password cannot be empty")
        return

    if username == "admin" and password == "password":
        root.withdraw()  # Hide the main window
        open_calculator()
    else:
        messagebox.showerror("Login Failed", "Incorrect username or password")


def open_calculator():
    global calc  # Declare calc as a global variable
    calc = tk.Toplevel()  # Create a new top-level window
    calc.title("Calculator")

    # Create display
    display = tk.Entry(calc)
    display.grid(row=0, column=0, columnspan=4)

    def on_click(char):
        if char == "=":
            try:
                result = eval(display.get())
                display.delete(0, tk.END)
                display.insert(tk.END, str(result))
            except Exception as e:
                display.delete(0, tk.END)
                display.insert(tk.END, "Error")
        elif char == "C":
            display.delete(0, tk.END)
        elif char == "√":
            try:
                value = float(display.get())
                result = math.sqrt(value)
                display.delete(0, tk.END)
                display.insert(tk.END, str(result))
            except Exception as e:
                display.delete(0, tk.END)
                display.insert(tk.END, "Error")
        else:
            display.insert(tk.END, char)

    # Create buttons
    buttons = [
        "7",
        "8",
        "9",
        "+",
        "4",
        "5",
        "6",
        "-",
        "1",
        "2",
        "3",
        "*",
        "C",
        "0",
        "=",
        "/",
        "√",  # Adding square root button here
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

    # When the calculator is closed, show the main window again
    calc.protocol("WM_DELETE_WINDOW", on_calculator_close)


def on_calculator_close():
    global root, calc
    root.deiconify()  # Show the main window again
    calc.destroy()  # Close the calculator window


# Main window for login
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

# Add a registration button
button_register = tk.Button(frame_login, text="Register", command=register_user)
button_register.pack()

# Login button
button_login = tk.Button(frame_login, text="Login", command=verify_login)
button_login.pack()

root.mainloop()