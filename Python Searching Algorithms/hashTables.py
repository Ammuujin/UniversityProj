# Creating a dictionary in Python
my_dict = {"apple": 5, "banana": 3, "cherry": 8, "date": 1, "elderberry": 2, "fig": 1, "grape": 20}
target_key = "banana"

print(my_dict)
if target_key in my_dict:
    print(f"{target_key} found with value {my_dict[target_key]}")
else:
    print(f"{target_key} not found in the dictionary")
