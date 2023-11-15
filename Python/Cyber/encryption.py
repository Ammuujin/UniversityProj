from cryptography.fernet import Fernet
import base64

# Function to generate a Fernet key
def generate_key():
    return Fernet.generate_key()

# Function to encrypt a message
def encrypt_message(message, key):
    try:
        f = Fernet(key)
        return f.encrypt(message.encode()), None
    except Exception as e:
        return None, e

# Function to decrypt a message
def decrypt_message(encrypted_message, key):
    try:
        f = Fernet(key)
        return f.decrypt(encrypted_message).decode(), None
    except ValueError as e:
        if "Fernet key must be 32 url-safe base64-encoded bytes." in str(e):
            return None, "Your input is wrong. Please check your decryption key."
        else:
            return None, e
    except Exception as e:
        return None, e

# Main function
def main():
    key = generate_key()
    print(f"Generated Encryption Key (save this key to decrypt your message later): {key.decode()}")

    message = input("Enter a message to encrypt: ")
    encrypted_message, error = encrypt_message(message, key)

    if error:
        print(f"An error occurred during encryption: {error}")
        return

    print(f"Encrypted Message: {encrypted_message.decode()}")

    if input("Do you want to decrypt it? (yes/no): ").lower() == "yes":
        decryption_key = input("Enter the decryption key: ").encode()
        decrypted_message, error = decrypt_message(encrypted_message, decryption_key)
        
        if error:
            print(f"An error occurred during decryption: {error}")
        else:
            print(f"Decrypted Message: {decrypted_message}")

if __name__ == "__main__":
    main()