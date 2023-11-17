import random

# Example names pools
male_names = ["Michael", "Ethan", "James"]
female_names = ["Emma", "Olivia", "Sophia"]

# Aspirations and associated names
aspirations = {
    "intelligent": ["Ethan", "Emma"],
    "kind": ["Olivia", "James"],
    "adventurous": ["Michael", "Sophia"]
}

def generate_name(sex, aspiration):
    # Filter names by aspiration
    filtered_names = [name for name in aspirations[aspiration] if name in (male_names if sex == "male" else female_names)]
    
    # Select a random name from the filtered list
    return random.choice(filtered_names)

# Example usage
sex = input("Enter the child's sex (male/female): ").lower()
aspiration = input("Enter the parent's aspiration for the child (intelligent/kind/adventurous): ").lower()

print("Suggested Name:", generate_name(sex, aspiration))
