import bcrypt
import hashlib
import time

print("Name = Shravani Sachin Patil ")

password = input("Enter password: ")

hashed1 = bcrypt.hashpw(password.encode(), bcrypt.gensalt())
hashed2 = bcrypt.hashpw(password.encode(), bcrypt.gensalt())

print("\nPassword with salts.")
print("\nHashed Password 1:", hashed1)
print("Hashed Password 2:", hashed2)
print("\n The passwords are same, both hashes differ due to random salts.")

sha_hash1 = hashlib.sha512(password.encode()).hexdigest()
sha_hash2 = hashlib.sha512(password.encode()).hexdigest()

print("\nPassword without salts.")
print("Hash 1:", sha_hash1)
print("Hash 2:", sha_hash2)
print("Identical hashes for same passwords !")


