# Activity

> **Activity** is one of the four fundamental Android application components. It represents a **single screen with a user interface (UI)** where users can interact with your application.

---

# 📖 Overview

Almost every Android application consists of one or more **Activities**.

Examples:

- Login Screen
- Home Screen
- Settings Screen
- Profile Screen
- About Screen

Each of these screens is typically implemented as a separate Activity.

When a user opens an Android application, Android launches an Activity that acts as the **entry point** of the app.

---

# 🎯 Why is Activity Important?

Activities are responsible for:

- Displaying the user interface
- Handling user interactions
- Managing the screen lifecycle
- Starting other Activities
- Receiving results from other Activities

Without an Activity, users cannot interact with your application.

---

# 🧠 What is an Activity?

An **Activity** is a class that extends `ComponentActivity` or `AppCompatActivity` and represents one screen of an Android application.

Example:

```kotlin
class MainActivity : AppCompatActivity() {

}
```

Think of an Activity as a **page** in a mobile application.

Example:

```
Instagram

Login Screen
      │
      ▼
Home Screen
      │
      ▼
Profile Screen
      │
      ▼
Settings Screen
```

Each screen is usually a separate Activity (although modern apps often use a single Activity with Fragments).

---

# 🏗️ Activity Architecture

A simple Activity consists of:

```
MainActivity.kt
        │
        ▼
activity_main.xml
        │
        ▼
Displayed on Screen
```

- **Kotlin File** → Controls the logic.
- **XML File** → Defines the UI.

---

# 📂 Activity Location

Typical structure:

```text
app/
└── src/
    └── main/
        └── java/
            └── com.example.myapplication/
                └── MainActivity.kt
```

---

# ✨ Creating an Activity

### Method 1

Android Studio

```
Right Click
      ↓
New
      ↓
Activity
      ↓
Empty Views Activity
```

Android Studio automatically creates:

```
MainActivity.kt

activity_main.xml

AndroidManifest.xml entry
```

---

### Method 2

Create manually.

You need to:

- Create Activity class
- Create XML layout
- Register Activity in Manifest

---

# 📝 Basic Activity Example

```kotlin
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

}
```

---

# 🔍 Understanding the Code

## AppCompatActivity

```kotlin
class MainActivity : AppCompatActivity()
```

Provides backward compatibility and modern Android features.

Almost every beginner Android app extends `AppCompatActivity`.

---

## onCreate()

```kotlin
override fun onCreate(savedInstanceState: Bundle?)
```

The first lifecycle callback called when the Activity is created.

Used for:

- Initializing UI
- Loading data
- Setting listeners
- Preparing the screen

---

## super.onCreate()

```kotlin
super.onCreate(savedInstanceState)
```

Calls the parent Activity's implementation.

Always call this first.

---

## setContentView()

```kotlin
setContentView(R.layout.activity_main)
```

Connects the XML layout with the Activity.

Without this line, no UI is displayed.

---

# 📱 Activity and XML Relationship

Example:

```
MainActivity.kt
        │
        │
setContentView(...)
        │
        ▼
activity_main.xml
        │
        ▼
Displayed on Phone
```

---

# 🧠 Activity Lifecycle

Activities move through different states during their lifetime.

Main callbacks:

```
onCreate()

↓

onStart()

↓

onResume()

↓

Running

↓

onPause()

↓

onStop()

↓

onDestroy()
```

We'll study the lifecycle in detail in **Activity-Lifecycle.md**.

---

# 🚀 Starting Another Activity

Activities communicate using **Intents**.

Example:

```kotlin
val intent = Intent(this, SecondActivity::class.java)

startActivity(intent)
```

---

# 📝 Registering an Activity

Every Activity must be declared in the Manifest (unless handled automatically by certain libraries or features).

Example:

```xml
<activity
    android:name=".SecondActivity"/>
```

---

# 📦 Activity Stack

Android maintains Activities inside a **Back Stack**.

Example:

```
MainActivity

↓

LoginActivity

↓

HomeActivity

↓

ProfileActivity
```

Pressing the **Back** button removes the top Activity and returns to the previous one.

---

# 🏷️ Common Activity Methods

| Method | Purpose |
|---------|----------|
| `onCreate()` | Initialize Activity |
| `onStart()` | Activity becomes visible |
| `onResume()` | User can interact |
| `onPause()` | Activity partially hidden |
| `onStop()` | Activity completely hidden |
| `onRestart()` | Restart after Stop |
| `onDestroy()` | Activity destroyed |
| `finish()` | Close current Activity |

---

# 💡 Example

Display a Toast when Activity starts.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    Toast.makeText(
        this,
        "Welcome!",
        Toast.LENGTH_SHORT
    ).show()

}
```

---

# 💡 Best Practices

- Keep Activities focused on UI responsibilities.
- Avoid writing too much business logic inside Activities.
- Use ViewBinding or DataBinding instead of `findViewById()` in modern projects.
- Register Activities correctly in the Manifest.
- Keep Activities lightweight by moving data and business logic to ViewModels or other classes.

---

# ⚠️ Common Beginner Mistakes

### ❌ Forgetting `setContentView()`

Without:

```kotlin
setContentView(...)
```

your UI will not be displayed.

---

### ❌ Forgetting to Register the Activity

An Activity must be declared in the Manifest when required.

---

### ❌ Writing Too Much Code Inside an Activity

Activities should mainly handle UI.

Business logic belongs in other layers (e.g., ViewModel, Repository).

---

### ❌ Not Calling `super.onCreate()`

Always call:

```kotlin
super.onCreate(savedInstanceState)
```

before performing your own initialization.

---

### ❌ Creating Too Many Activities

Modern Android apps often use a **Single-Activity Architecture** with **Fragments** for navigation. However, understanding Activities remains essential because every app starts with at least one Activity.

---

# 🎤 Interview Questions

### 1. What is an Activity?

An Activity is an Android component that represents a single screen with a user interface.

---

### 2. Why do we use Activities?

Activities display the UI, handle user interaction, and manage the lifecycle of a screen.

---

### 3. What is `onCreate()`?

`onCreate()` is the first lifecycle callback invoked when an Activity is created. It is commonly used to initialize the UI and set up the screen.

---

### 4. What is the purpose of `setContentView()`?

It connects an XML layout resource to the Activity so that the UI can be displayed.

---

### 5. What is `AppCompatActivity`?

`AppCompatActivity` is a base Activity class that provides backward compatibility and support for modern Android features.

---

### 6. What is the Activity Back Stack?

The Back Stack is a stack of Activities managed by Android. Pressing the Back button removes the current Activity and returns to the previous one.

---

### 7. How do you start another Activity?

Using an **Intent**.

Example:

```kotlin
val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)
```

---

### 8. Can an Android app have multiple Activities?

Yes. An app can contain multiple Activities, although many modern applications use a single Activity with multiple Fragments.

---

# 📌 Summary

- An **Activity** represents one screen of an Android application.
- It is responsible for displaying the UI and handling user interactions.
- Activities are typically paired with an XML layout using `setContentView()`.
- Android manages Activities through a lifecycle and a Back Stack.
- Understanding Activities is fundamental before learning Fragments, Navigation, and modern Android architecture.

---

# 📚 Related Topics

- Activity Lifecycle
- Intent
- AndroidManifest.xml
- Resources
- ViewBinding
- Fragments
- Navigation Component