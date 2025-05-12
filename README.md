Gemini AI ChatBot 🤖✨

An elegant AI-powered chatbot Android app built with Kotlin and Jetpack Compose. This project integrates the Gemini Pro (gemini-1.5-flash) model from Google AI for real-time, context-aware conversations.

🔗 GitHub Repository: https://github.com/abdellahayoujil/AiChatBoot

✨ Features
🔮 Integrated Gemini Pro AI (gemini-1.5-flash)

💬 Real-time conversational interface

🎨 Jetpack Compose UI

⚡ Smooth message animations and UI transitions

🛠 Tech Stack
Kotlin

Jetpack Compose

Material 3

Google Generative AI SDK (Gemini)

🧠 How It Works
Uses GenerativeModel from Google AI SDK

Maintains conversation history to support context-aware responses

Displays a typing indicator before the AI responds

Handles API errors gracefully

🚀 Getting Started
Clone the repository:

bash
Copier
Modifier
git clone https://github.com/abdellahayoujil/AiChatBoot.git
Open in Android Studio.

Add your Gemini API key in Constant.kt:

kotlin
Copier
Modifier
object Constant {
    const val apikey = "YOUR_API_KEY"
}
Build and run the app on an emulator or device.

📁 Project Structure
MainActivity.kt – Entry point

ChatPage.kt – UI for chat interface

ViewModel.kt – Handles chat logic and state

MessageModel.kt – Data model for messages

ui/theme/ – App themes and color palettes

🙌 Acknowledgements
Built using the official Google AI Gemini SDK

Designed and maintained by @abdellahayoujil
