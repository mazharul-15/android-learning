# Toast

> **Toast** is a small pop-up message in Android used to display short, non-intrusive feedback to the user. It appears for a short duration and disappears automatically without requiring any user interaction.

---

# 📖 Overview

A **Toast** is one of the simplest ways to communicate with users.

Examples:

- Login Successful
- Data Saved
- File Deleted
- Internet Connected
- Invalid Password

Toast messages are temporary and automatically disappear after a few seconds.

---

# 🎯 Why Use Toast?

Toast is useful for:

- Showing quick feedback
- Confirming an action
- Displaying validation messages
- Informing the user without interrupting the workflow

Unlike a dialog, a Toast does **not** require the user to press **OK**.

---

# 🧠 What is a Toast?

Think of Toast as a **temporary notification**.

Example:

```
+---------------------------+
|  Data Saved Successfully  |
+---------------------------+
```

After a few seconds:

```
(Automatically disappears)
```

---

# 🏗️ Toast Syntax

Basic syntax:

```kotlin
Toast.makeText(
    context,
    "Hello Android!",
    Toast.LENGTH_SHORT
).show()
```

---

# 📝 Understanding the Parameters

```kotlin
Toast.makeText(
    context,
    text,
    duration
).show()
```

| Parameter | Description |
|-----------|-------------|
| `context` | Current Activity or Application context |
| `text` | Message to display |
| `duration` | Display duration (`LENGTH_SHORT` or `LENGTH_LONG`) |

---

# 📦 Example 1: Simple Toast

```kotlin
Toast.makeText(
    this,
    "Welcome!",
    Toast.LENGTH_SHORT
).show()
```

Output:

```
Welcome!
```

---

# 📦 Example 2: Long Toast

```kotlin
Toast.makeText(
    this,
    "Downloading file...",
    Toast.LENGTH_LONG
).show()
```

---

# 📦 Example 3: Toast Using String Resource

Instead of hardcoding text:

```kotlin
Toast.makeText(
    this,
    getString(R.string.login_success),
    Toast.LENGTH_SHORT
).show()
```

`strings.xml`

```xml
<string name="login_success">
    Login Successful
</string>
```

This approach is recommended because it supports localization.

---

# 📦 Example 4: Toast on Button Click

```kotlin
button.setOnClickListener {

    Toast.makeText(
        this,
        "Button Clicked",
        Toast.LENGTH_SHORT
    ).show()

}
```

---

# 📦 Example 5: Toast with ViewBinding

```kotlin
binding.btnLogin.setOnClickListener {

    Toast.makeText(
        this,
        "Login Successful",
        Toast.LENGTH_SHORT
    ).show()

}
```

---

# ⏱️ Toast Duration

Android provides two predefined durations.

## LENGTH_SHORT

```kotlin
Toast.LENGTH_SHORT
```

Displays for approximately **2 seconds**.

---

## LENGTH_LONG

```kotlin
Toast.LENGTH_LONG
```

Displays for approximately **3.5 seconds**.

> **Note:** These durations are predefined by Android. You cannot set an exact custom duration using the standard Toast API.

---

# 📍 Toast Position

By default, a Toast appears near the bottom of the screen.

```
-------------------
|                 |
|                 |
|                 |
|                 |
|                 |
|                 |
|   Hello World   |
-------------------
```

Older Android versions allowed custom positioning with `setGravity()`, but this is generally discouraged in modern Android development because the system manages Toast placement for consistency.

---

# ⚠️ Toast vs Snackbar

| Toast | Snackbar |
|--------|-----------|
| Temporary message | Temporary message |
| Disappears automatically | Can include an action button |
| No user interaction | Supports user interaction |
| Appears in a system-defined position | Usually appears at the bottom of the app UI |
| Good for simple feedback | Better for undo or retry actions |

Example Snackbar:

```kotlin
Snackbar.make(
    binding.root,
    "File Deleted",
    Snackbar.LENGTH_LONG
).show()
```

For Material Design apps, **Snackbar** is often preferred when the user might need to take an action.

---

# 💡 Best Practices

- Keep messages short and meaningful.
- Use `strings.xml` instead of hardcoded text.
- Use Toast only for brief feedback.
- Avoid showing multiple Toasts in rapid succession.
- Consider using Snackbar for important messages or actions.

---

# ⚠️ Common Beginner Mistakes

### ❌ Forgetting `.show()`

Incorrect:

```kotlin
Toast.makeText(
    this,
    "Hello",
    Toast.LENGTH_SHORT
)
```

Nothing will appear.

Correct:

```kotlin
Toast.makeText(
    this,
    "Hello",
    Toast.LENGTH_SHORT
).show()
```

---

### ❌ Using the Wrong Context

Inside an Activity:

```kotlin
this
```

or

```kotlin
applicationContext
```

are common choices depending on the situation.

---

### ❌ Hardcoding Strings

Instead of:

```kotlin
"Login Successful"
```

Use:

```kotlin
getString(R.string.login_success)
```

---

### ❌ Using Toast for Important Messages

Toast disappears automatically.

For errors that require user attention or an action, use:

- Snackbar
- Dialog
- Error Text

---

### ❌ Showing Too Many Toasts

Displaying multiple Toasts one after another can overwhelm users and reduce usability.

---

# 🎤 Interview Questions

### 1. What is a Toast?

A Toast is a small temporary pop-up message used to display brief feedback to the user.

---

### 2. Why do we use Toast?

To provide quick, non-intrusive feedback after an action.

---

### 3. What are the two Toast durations?

- `Toast.LENGTH_SHORT`
- `Toast.LENGTH_LONG`

---

### 4. What happens if `.show()` is omitted?

The Toast is created but never displayed.

---

### 5. What is the difference between Toast and Snackbar?

| Toast | Snackbar |
|--------|-----------|
| No action button | Can include an action button |
| System-managed position | Attached to the app's UI |
| Simple notifications | Better for interactive feedback |

---

### 6. Should user-visible text be hardcoded?

No.

Store it in:

```xml
strings.xml
```

and retrieve it with:

```kotlin
getString(R.string.message)
```

---

### 7. Can you customize the duration of a standard Toast?

No.

The standard Toast API supports only:

- `LENGTH_SHORT`
- `LENGTH_LONG`

---

# 📌 Summary

- Toast displays short, temporary messages to the user.
- It is created using `Toast.makeText(...).show()`.
- Toast is ideal for quick feedback that does not require user interaction.
- Use `strings.xml` for message text.
- For interactive or important feedback, prefer **Snackbar** or a **Dialog**.

---

# 📚 Related Topics

- Activity
- Resources
- ViewBinding
- Snackbar
- Material Design
- Dialog