from chatterbot import ChatBot
import nltk

# Set NLTK data path if needed
# nltk.data.path.append('path_to_nltk_data_directory')

# Create a new chatbot instance
chatbot = ChatBot('LoveAdviceBot')

# Predefined love advice responses (extended)
love_advice_responses = {
    "how to impress someone": "Be yourself and show genuine interest in their likes and dislikes.",
    "how to maintain a healthy relationship": "Communication and trust are key. Spend quality time together and resolve conflicts peacefully.",
    "how to ask someone out": "Confidently express your feelings and ask them for a date or a coffee meetup.",
    "how to ask woman's phone": "Just go and ask her phone number. If she likes you, she will give it to you.",
    "how to ask temka out": "Go and ask him out. If he likes you, he will say yes.",
    "how to ask temka's phone": "Just go and ask his phone number. If he likes you, he will give it to you.",
    "how to overcome jealousy": "Jealousy can harm a relationship. Build trust, communicate, and focus on self-improvement.",
    "how to spice up your relationship": "Try new activities, surprises, and open communication to keep the spark alive.",
    "how to deal with a breakup": "Healing takes time. Lean on friends, practice self-care, and focus on personal growth.",
    "how to build confidence in dating": "Work on self-esteem, practice social skills, and remember that rejection is part of dating.",
    "how to deal with long-distance relationship": "In a long-distance relationship, communication is key. Schedule regular visits and set goals.",
    "how to express love and appreciation": "Show love through gestures, compliments, and spending quality time together.",
    "how to handle a partner's jealousy": "Reassure your partner of your commitment and maintain open and honest communication.",
    "how to apologize and make amends": "Acknowledge your mistakes, apologize sincerely, and make changes to avoid repeating them.",
    "how to keep the romance alive": "Plan surprise dates, write love letters, and express affection to keep the romance alive.",
    "how to deal with trust issues": "Rebuild trust through transparency, honesty, and consistent actions over time.",
    "how to navigate cultural differences in a relationship": "Respect and embrace each other's cultures, and communicate openly about differences.",
    "how to balance personal space and time in a relationship": "Maintain individual hobbies and interests while prioritizing quality time together.",
    "how to handle disagreements and conflicts": "Listen actively, avoid blame, and seek compromises to resolve conflicts peacefully.",
    "how to recover from infidelity": "Rebuilding trust is a long process. Consider counseling and open communication.",
    "how to know if it's the right time to say 'I love you'": "When you truly feel it and are ready to express your feelings, it's the right time.",
    "how to cope with unrequited love": "Acknowledge your feelings, seek support from friends, and focus on self-care and growth.",
    "how to navigate a relationship with different future goals": "Communicate about your aspirations, and find compromises that align your goals.",
    "how to build intimacy in a relationship": "Build emotional intimacy through deep conversations and physical intimacy through affection.",
    "how to maintain a healthy sex life in a long-term relationship": "Keep the spark alive with open communication, variety, and spontaneity.",
    "how to handle a partner's family issues": "Support your partner, set boundaries, and address issues calmly and respectfully.",
    "how to handle external relationship pressures": "Prioritize your relationship, communicate, and stand together against external pressures.",
    "how to rebuild a relationship after a major conflict": "Apologize, forgive, and work on rebuilding trust through actions and communication.",
    "how to set relationship goals together": "Discuss and set mutual goals that align with your values and desires.",
    "how to make a relationship stronger over time": "Invest time, effort, and love into your relationship to make it stronger each day."
    # Add more responses as needed
}

# Function to get love advice response
def get_love_advice(input_text, name, age):
    input_text = input_text.lower()
    for question, response in love_advice_responses.items():
        if question in input_text:
            # Use the provided name and age in the response
            response = response.replace("{name}", name).replace("{age}", age)
            return response
    
    # Respond to common questions like "How are you?" and "What is your name?"
    if "how are you" in input_text:
        return "I'm just a chatbot, so I don't have feelings, but I'm here to help you!"
    elif "what is your name" in input_text:
        return "My name is LoveAdviceBot, and I'm here to provide love advice. How can I assist you today?"

    return "I'm here to provide love advice. Ask me any love-related questions!"

# Function to interact with the chatbot
def chat_with_bot():
    print("Love Advice Bot: Hello! I'd like to get to know you better.")
    name = input("What's your name? ")
    age = input("How old are you? ")

    print(f"Love Advice Bot: Nice to meet you, {name}! Let's talk about love advice.")
    while True:
        user_input = input(f"{name}: ")
        if user_input.lower() == "exit":
            print("Love Advice Bot: Goodbye!")
            break

        # Get a response from the predefined responses or handle common questions
        response = get_love_advice(user_input, name, age)
        print("Love Advice Bot:", response)

# Start the chat
chat_with_bot()
