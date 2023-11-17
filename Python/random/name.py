import random
import json

# Sample data structure for names and their associated traits
names_data = {
    "Michael": {"sex": "male", "traits": ["adventurous", "leader"]},
    "Ethan": {"sex": "male", "traits": ["intelligent", "creative"]},
    "James": {"sex": "male", "traits": ["kind", "thoughtful"]},
    "Emma": {"sex": "female", "traits": ["intelligent", "ambitious"]},
    "Olivia": {"sex": "female", "traits": ["kind", "artistic"]},
    "Sophia": {"sex": "female", "traits": ["adventurous", "independent"]},
    "William": {"sex": "male", "traits": ["friendly", "athletic"]},
    "Liam": {"sex": "male", "traits": ["creative", "musical"]},
    "Benjamin": {"sex": "male", "traits": ["intelligent", "ambitious"]},
    "Ava": {"sex": "female", "traits": ["creative", "artistic"]},
    "Mia": {"sex": "female", "traits": ["kind", "thoughtful"]},
    "Abigail": {"sex": "female", "traits": ["intelligent", "leader"]},
    "Daniel": {"sex": "male", "traits": ["analytical", "innovative"]},
    "Alexander": {"sex": "male", "traits": ["confident", "charismatic"]},
    "Matthew": {"sex": "male", "traits": ["compassionate", "insightful"]},
    "Emily": {"sex": "female", "traits": ["creative", "empathetic"]},
    "Grace": {"sex": "female", "traits": ["gentle", "compassionate"]},
    "Chloe": {"sex": "female", "traits": ["adventurous", "curious"]},
}


def generate_name(sex, aspirations):
    # Filter names by sex and calculate score based on matching aspirations
    scored_names = {}
    for name, details in names_data.items():
        if details["sex"] == sex:
            score = sum(1 for aspiration in aspirations if aspiration in details["traits"])
            if score > 0:
                scored_names[name] = score

    # Normalize scores to create a probability distribution
    total_score = sum(scored_names.values())
    if total_score == 0:
        return "No matching name found"
    for name in scored_names:
        scored_names[name] /= total_score

    # Random selection based on weighted probabilities
    return random.choices(list(scored_names.keys()), weights=scored_names.values(), k=1)[0]

# Example usage
sex = input("Enter the child's sex (male/female): ").lower()
aspirations_input = input("Enter the parent's aspirations for the child (comma-separated, e.g., intelligent,kind): ")
aspirations = [aspiration.strip().lower() for aspiration in aspirations_input.split(',')]

print("Suggested Name:", generate_name(sex, aspirations))
