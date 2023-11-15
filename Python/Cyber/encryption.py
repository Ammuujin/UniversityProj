from cryptography.fernet import Fernet
import base64
import sys

# Function to generate a Fernet key
def generate_key():
    return Fernet.generate_key()

# Function to encrypt a message
def encrypt_message(message, key):
    try:
        f = Fernet(key)
        return f.encrypt(message.encode()), None
    except Exception as e:
        return None, f"Encryption error: {e}"

# Function to decrypt a message
def decrypt_message(encrypted_message, key):
    try:
        f = Fernet(key)
        return f.decrypt(encrypted_message).decode(), None
    except ValueError as e:
        if "Fernet key must be 32 url-safe base64-encoded bytes." in str(e):
            return None, "Decryption error: Invalid key format. Please check your key."
        else:
            return None, f"Decryption error: {e}"
    except Exception as e:
        return None, f"Decryption error: {e}"

# Main function
def main():
    try:
        key = generate_key()
        print(
            f"Generated Encryption Key (save this key to decrypt your message later): {key.decode()}"
        )

        message = input("Enter a message to encrypt: ")
        encrypted_message, error = encrypt_message(message, key)

        if error:
            print(error)
            return

        print(f"Encrypted Message: {encrypted_message.decode()}")

        if input("Do you want to decrypt it? (yes/no): ").lower() == "yes":
            decryption_key = input("Enter the decryption key: ").encode()
            decrypted_message, error = decrypt_message(
                encrypted_message, decryption_key
            )

            if error:
                print(error)
            else:
                print(f"Decrypted Message: {decrypted_message}")
    except KeyboardInterrupt:
        print("\nOperation cancelled by user.")
    except Exception as e:
        print(f"An unexpected error occurred: {e}")

if __name__ == "__main__":
    main()