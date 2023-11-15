import nltk
from nltk.stem import WordNetLemmatizer
from nltk.tokenize import word_tokenize
nltk.download('punkt')
nltk.download('wordnet')
nltk.download('averaged_perceptron_tagger')

# Initialize the WordNet lemmatizer
lemmatizer = WordNetLemmatizer()

def get_love_advice(input_text, name, age):
    # Tokenize and lemmatize the input text
    tokens = word_tokenize(input_text.lower())
    lemmatized_input = ' '.join([lemmatizer.lemmatize(token) for token in tokens])

    # Match lemmatized input with predefined responses
    for question, response in love_advice_responses.items():
        if all(word in lemmatized_input for word in question.split()):
            response = response.replace("{name}", name).replace("{age}", age)
            return response

    # Respond to common questions like "How are you?" and "What is your name?"
    if "how are you" in lemmatized_input:
        return "I'm just a chatbot, so I don't have feelings, but I'm here to help you!"
    elif "what is your name" in lemmatized_input:
        return "My name is LoveAdviceBot, and I'm here to provide love advice. How can I assist you today?"

    return "I'm here to provide love advice. Ask me any love-related questions!"
