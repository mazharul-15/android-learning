# Intent Basics

> **Intent** is a messaging object in Android that allows one component to communicate with another. It is primarily used to start Activities, start Services, send Broadcasts, and share data between components.

---

# 📖 Overview

Android applications are made up of multiple components such as:

- Activities
- Services
- Broadcast Receivers
- Content Providers

These components communicate with each other using **Intents**.

For example:

- Open another screen
- Open the phone dialer
- Send an email
- Open Google Maps
- Share a photo
- Open a web browser

All of these actions are performed using Intents.

---

# 🎯 Why are Intents Important?

Intents allow Android applications to:

- Navigate between screens
- Communicate between components
- Share data
- Launch built-in Android apps
- Interact with other applications

Without Intents, Android components would not be able to communicate with each other.

---

# 🧠 What is an Intent?

An **Intent** is an object that describes:

- **What action should be performed**
- **Which component should handle the action**
- **Optional data to pass**

Think of an Intent as a **message**.

```
MainActivity
      │
      │ Intent
      ▼
SecondActivity
```

---

# 🏗️ Types of Intents

Android provides two main types of Intents.

## 1. Explicit Intent

An Explicit Intent specifies **exactly which Activity or component** should be started.

Example:

```
MainActivity

↓

SecondActivity
```

You already know the destination.

---

## 2. Implicit Intent

An Implicit Intent specifies **what action** should be performed.

Android finds the best application that can handle that action.

Examples:

- Open browser
- Dial phone number
- Open camera
- Share text
- Open map

---

# 📦 Explicit Intent

Example:

```kotlin
val intent = Intent(this, SecondActivity::class.java)

startActivity(intent)
```

### Explanation

```kotlin
Intent(
    this,
    SecondActivity::class.java
)
```

- `this` → Current Activity
- `SecondActivity::class.java` → Destination Activity

Then:

```kotlin
startActivity(intent)
```

launches the new Activity.

---

# 📂 Registering Activity

The destination Activity must be declared in the Manifest.

```xml
<activity
    android:name=".SecondActivity"/>
```

Otherwise Android cannot launch it.

---

# 📦 Passing Data

Intents can send data to another Activity.

Example:

```kotlin
val intent = Intent(this, SecondActivity::class.java)

intent.putExtra("name", "Mazharul")

startActivity(intent)
```

---

# 📥 Receiving Data

Inside `SecondActivity`

```kotlin
val name = intent.getStringExtra("name")
```

Result:

```
Mazharul
```

---

# 📦 Multiple Data

```kotlin
intent.putExtra("name", "Mazharul")

intent.putExtra("age", 25)

intent.putExtra("student", true)
```

Receive:

```kotlin
val name = intent.getStringExtra("name")

val age = intent.getIntExtra("age", 0)

val student = intent.getBooleanExtra("student", false)
```

---

# 🌐 Implicit Intent

Example:

```kotlin
val intent = Intent(
    Intent.ACTION_VIEW,
    Uri.parse("https://www.google.com")
)

startActivity(intent)
```

Android opens the browser.

---

# 📞 Open Dialer

```kotlin
val intent = Intent(
    Intent.ACTION_DIAL,
    Uri.parse("tel:01700000000")
)

startActivity(intent)
```

Opens the Phone app with the number filled in.

---

# 📧 Send Email

```kotlin
val intent = Intent(Intent.ACTION_SEND)

intent.type = "text/plain"

intent.putExtra(
    Intent.EXTRA_EMAIL,
    arrayOf("example@gmail.com")
)

intent.putExtra(
    Intent.EXTRA_SUBJECT,
    "Android"
)

intent.putExtra(
    Intent.EXTRA_TEXT,
    "Hello!"
)

startActivity(intent)
```

Android lets the user choose a compatible email app.

---

# 📍 Open Google Maps

```kotlin
val intent = Intent(
    Intent.ACTION_VIEW,
    Uri.parse("geo:23.8103,90.4125")
)

startActivity(intent)
```

---

# 📤 Share Text

```kotlin
val intent = Intent(Intent.ACTION_SEND)

intent.type = "text/plain"

intent.putExtra(
    Intent.EXTRA_TEXT,
    "Hello Android!"
)

startActivity(
    Intent.createChooser(
        intent,
        "Share via"
    )
)
```

The chooser lets the user select an app to share with.

---

# 🎯 Intent Actions

Common actions:

| Action | Purpose |
|----------|---------|
| `ACTION_VIEW` | View data (web page, map, etc.) |
| `ACTION_SEND` | Share data |
| `ACTION_DIAL` | Open phone dialer |
| `ACTION_CALL` | Make a phone call (requires permission) |
| `ACTION_EDIT` | Edit data |
| `ACTION_PICK` | Select an item |
| `ACTION_MAIN` | Main entry point |
| `ACTION_SENDTO` | Send to a specific recipient (e.g., email, SMS) |

---

# 📎 Intent Extras

Extras are key-value pairs used to pass data.

Example:

```kotlin
intent.putExtra("username", "Mazharul")
```

Retrieve:

```kotlin
intent.getStringExtra("username")
```

Common types:

- String
- Int
- Boolean
- Double
- Parcelable
- Serializable

---

# 🔙 Returning Data

In modern Android development, returning data from another Activity is commonly done using the **Activity Result API**.

Example concept:

```
Activity A

↓

Open Activity B

↓

User selects data

↓

Return result to Activity A
```

> Older code often uses `startActivityForResult()`, but this API is deprecated. Prefer the **Activity Result API** in new projects.

---

# 💡 Best Practices

- Use **Explicit Intents** for navigation within your app.
- Use **Implicit Intents** to interact with other apps.
- Check that an app can handle an Implicit Intent before launching it when appropriate.
- Use meaningful keys for Intent extras.
- Keep data passed through Intents small. For large objects, consider using a database or shared repository.

---

# ⚠️ Common Beginner Mistakes

### ❌ Forgetting to Register Activities

Activities should be declared in the Manifest when required.

---

### ❌ Misspelling Extra Keys

Incorrect:

```kotlin
putExtra("nmae", "Mazharul")
```

Correct:

```kotlin
putExtra("name", "Mazharul")
```

---

### ❌ Forgetting to Read Extras

Sending:

```kotlin
putExtra("name", "Mazharul")
```

but never reading:

```kotlin
intent.getStringExtra("name")
```

---

### ❌ Using `ACTION_CALL` Without Permission

`ACTION_CALL` directly places a phone call and requires the `CALL_PHONE` permission.

If you simply want to open the dialer, use:

```kotlin
Intent.ACTION_DIAL
```

---

### ❌ Passing Large Objects

Avoid passing large data through Intents. It can lead to performance issues or transaction size limits.

---

# 🎤 Interview Questions

### 1. What is an Intent?

An Intent is a messaging object used to communicate between Android components.

---

### 2. What are the two types of Intents?

- Explicit Intent
- Implicit Intent

---

### 3. What is an Explicit Intent?

An Explicit Intent specifies the exact component (such as an Activity) that should handle the request.

---

### 4. What is an Implicit Intent?

An Implicit Intent specifies an action to perform, allowing Android to choose a suitable application to handle it.

---

### 5. What is the difference between Explicit and Implicit Intents?

| Explicit Intent | Implicit Intent |
|-----------------|-----------------|
| Specifies the target component | Specifies an action |
| Used within the same app | Can launch other apps |
| Example: Open `SecondActivity` | Example: Open browser |

---

### 6. What are Intent Extras?

Extras are key-value pairs used to send data between components.

---

### 7. Which Intent is used to open a web page?

```kotlin
Intent.ACTION_VIEW
```

with a web URL.

---

### 8. Which Intent is safer for opening the phone app?

```kotlin
Intent.ACTION_DIAL
```

because it does not require the `CALL_PHONE` permission.

---

### 9. How do you pass data to another Activity?

Using:

```kotlin
intent.putExtra(...)
```

and retrieve it with:

```kotlin
intent.get...Extra(...)
```

---

# 📌 Summary

- Intents enable communication between Android components.
- **Explicit Intents** are used to navigate within your app.
- **Implicit Intents** allow Android to launch suitable external apps.
- Intent Extras provide a simple way to pass small amounts of data.
- Intents are fundamental for navigation and interaction in Android applications.

---

# 📚 Related Topics

- Activity
- Activity Lifecycle
- AndroidManifest.xml
- Broadcast Receivers
- Services
- Activity Result API
- Navigation Component