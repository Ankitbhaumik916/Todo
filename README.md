# 📝 AI-Powered To-Do App

Ankit's modern, smart, and beautiful To-Do app built with **Jetpack Compose**, featuring:
- Dark/Light mode toggle
- Horizontal scrollable calendar
- Date-based task filtering
- Gemini AI-powered task suggestions
- Persistent storage using DataStore
- Smooth animations with Material 3

---

## ✨ Features

- ✅ **Add Tasks** with optional due dates
- 📅 **Filter Tasks** by selecting calendar dates
- 🌗 **Dark/Light Theme Toggle** (persists across launches)
- 🧠 **AI Weekly Plan Suggestions** powered by Gemini API
- 💾 **Persistent Storage** with `DataStore`
- 🗑️ **Swipe/Delete Tasks** with fade animation
- ☑️ **Mark Tasks as Done** with checkbox

---

## 📱 Screenshots

*(Insert screenshots of the app UI here)*

---

## 🛠️ Tech Stack

| Layer        | Tech                            |
|--------------|----------------------------------|
| UI           | Jetpack Compose + Material3     |
| State Mgmt   | Compose States                  |
| AI           | Gemini API                      |
| Persistence  | DataStore Preferences           |
| Theme        | Custom Compose `_1STTheme()`    |

---

## 🧠 AI Feature – Gemini Suggestions

The app can parse your **weekly plan** and provide **task suggestions** using Google's **Gemini API**.  
Just paste your plan and hit **🧠 Suggest Tasks**.

Example input:
Monday: Complete AI assignment
Tuesday: Attend ML class and review notes
Wednesday: Gym and design poster

---

## 🔧 How to Run

1. Clone the repo:
    ```bash
    git clone https://github.com/your-username/Todo-AI-App.git
    ```

2. Open the project in **Android Studio Flamingo or higher**.

3. Make sure to:
    - Set your Gemini API key in `GeminiSuggestions.kt`.
    - Add internet permission in `AndroidManifest.xml` if calling remote APIs.

4. Run the app on emulator or physical device (Android 7+).

---

## 📂 Folder Structure

data/
└── SettingsDataStore.kt
└── TaskDataStore.kt

ai/
└── GeminiSuggestions.kt

ui/
└── MainActivity.kt
└── theme/_1STTheme.kt

model/
└── Task.kt

yaml
Copy
Edit

---

## 🙋‍♂️ Author

**Ankit Bhaumik**  
B.Tech AI Student | Builder of BioLens, DigiTwin & Al-Mirah  
📧 ankitbhaumik916@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/ankit-bhaumik/)  
💻 [Portfolio (soon)](https://yourdomain.com)

---

## 🏁 Upcoming Features

- [ ] 🔔 Task Reminders (via AlarmManager)
- [ ] 🔄 Recurring Tasks
- [ ] 🏷️ Task Labels/Categories
- [ ] 📆 Google Calendar Sync
- [ ] 📊 Weekly Productivity Chart

---

## 📃 License

MIT License © 2025 Ankit Bhaumik
