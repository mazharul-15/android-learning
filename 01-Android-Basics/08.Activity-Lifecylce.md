# Activity Lifecycle

> **Activity Lifecycle** is the sequence of states an Activity goes through from the moment it is created until it is destroyed. Understanding the lifecycle is essential for building stable, efficient, and user-friendly Android applications.

---

# 📖 Overview

An Activity does not remain active forever.

Depending on user actions or system events, Android creates, pauses, stops, restarts, and destroys Activities automatically.

Examples:

- Opening an app
- Pressing the Home button
- Rotating the device
- Receiving a phone call
- Opening another Activity
- Closing the application

Android notifies your Activity about these changes through **Lifecycle Callback Methods**.

---

# 🎯 Why is Activity Lifecycle Important?

Understanding the lifecycle helps you:

- Save user data
- Prevent memory leaks
- Pause unnecessary work
- Resume tasks correctly
- Improve performance
- Handle configuration changes
- Build reliable Android applications

Ignoring the lifecycle can cause crashes, data loss, battery drain, and poor user experience.

---

# 🧠 Activity Lifecycle Diagram

```text
                Launch Activity
                      │
                      ▼
                onCreate()
                      │
                      ▼
                 onStart()
                      │
                      ▼
                onResume()
                      │
                      ▼
            Activity Running
                      │
      ┌───────────────┴───────────────┐
      │                               │
      ▼                               ▼
 onPause()                      onDestroy()
      │
      ▼
  onStop()
      │
      ▼
 onRestart()
      │
      ▼
  onStart()
      │
      ▼
 onResume()
```

---

# 🏗️ Lifecycle Callbacks

Android provides six main lifecycle callback methods.

| Callback | Purpose |
|----------|---------|
| `onCreate()` | Initialize the Activity |
| `onStart()` | Activity becomes visible |
| `onResume()` | Activity enters the foreground and is ready for user interaction |
| `onPause()` | Activity loses focus |
| `onStop()` | Activity is no longer visible |
| `onRestart()` | Called before restarting a stopped Activity |
| `onDestroy()` | Activity is destroyed |

---

# 1️⃣ onCreate()

### Called when

The Activity is created for the first time.

### Common Uses

- Inflate the layout
- Initialize UI
- Set listeners
- Initialize ViewModel
- Restore saved state

Example:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    println("onCreate")
}
```

This method is called **only once** during the Activity's initial creation (unless the Activity is recreated, such as after a configuration change).

---

# 2️⃣ onStart()

### Called when

The Activity becomes visible to the user.

The user can **see** the Activity but cannot necessarily interact with it yet.

Example:

```kotlin
override fun onStart() {
    super.onStart()

    println("onStart")
}
```

Common uses:

- Register listeners
- Prepare UI components

---

# 3️⃣ onResume()

### Called when

The Activity moves to the foreground and is ready for user interaction.

At this point:

- Buttons work
- Text fields accept input
- Touch events are received

Example:

```kotlin
override fun onResume() {
    super.onResume()

    println("onResume")
}
```

Common uses:

- Start animations
- Resume camera preview
- Resume video playback
- Resume sensors

---

# Activity Running

The Activity is now fully active.

The user can:

- Click buttons
- Scroll
- Type
- Navigate

This is the normal working state.

---

# 4️⃣ onPause()

### Called when

The Activity loses focus but may still be partially visible.

Examples:

- A dialog appears
- Another Activity partially covers it
- A permission dialog opens

Example:

```kotlin
override fun onPause() {
    super.onPause()

    println("onPause")
}
```

Common uses:

- Pause animations
- Pause media playback
- Save temporary changes
- Release resources that don't need to stay active

Keep this method fast because the next Activity cannot resume until `onPause()` finishes.

---

# 5️⃣ onStop()

### Called when

The Activity is completely hidden.

Examples:

- Another Activity covers the screen
- User presses the Home button

Example:

```kotlin
override fun onStop() {
    super.onStop()

    println("onStop")
}
```

Common uses:

- Stop location updates
- Release camera
- Disconnect listeners
- Stop background work related to the UI

---

# 6️⃣ onRestart()

### Called when

The Activity returns after being stopped.

Example:

```kotlin
override fun onRestart() {
    super.onRestart()

    println("onRestart")
}
```

The sequence is:

```
onRestart()

↓

onStart()

↓

onResume()
```

---

# 7️⃣ onDestroy()

### Called when

The Activity is destroyed.

Reasons include:

- User finishes the Activity
- System destroys it to reclaim memory
- Activity is recreated after a configuration change (the old instance is destroyed)

Example:

```kotlin
override fun onDestroy() {
    super.onDestroy()

    println("onDestroy")
}
```

Common uses:

- Final cleanup
- Release remaining resources

> **Important:** Do not rely on `onDestroy()` to save important data because the system may terminate your process without calling it in some situations.

---

# 📋 Complete Example

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate")
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }
}
```

---

# 🔄 Common Lifecycle Scenarios

## Scenario 1: Launching the App

```text
onCreate()

↓

onStart()

↓

onResume()
```

---

## Scenario 2: Pressing the Home Button

```text
onPause()

↓

onStop()
```

The Activity remains in memory and may be resumed later.

---

## Scenario 3: Returning to the App

```text
onRestart()

↓

onStart()

↓

onResume()
```

---

## Scenario 4: Pressing the Back Button

```text
onPause()

↓

onStop()

↓

onDestroy()
```

The Activity finishes and is removed from the Back Stack.

---

## Scenario 5: Opening Another Activity

```text
Current Activity

↓

onPause()

↓

New Activity:
onCreate()
onStart()
onResume()

↓

Current Activity:
onStop()
```

---

# 💾 Saving UI State

When the system recreates an Activity (for example, after device rotation), you can save temporary UI state.

Use:

```kotlin
override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)

    outState.putString("name", "Mazharul")
}
```

Restore it in:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val name = savedInstanceState?.getString("name")
}
```

> This is useful for preserving temporary UI data like text entered into a form.

---

# 💡 Best Practices

- Keep `onCreate()` focused on initialization.
- Start UI-related work in `onStart()` or `onResume()` when appropriate.
- Pause or stop unnecessary work in `onPause()` and `onStop()`.
- Release expensive resources (camera, sensors, location updates) when they are no longer needed.
- Save temporary UI state using `onSaveInstanceState()`.
- Avoid performing heavy operations on the main thread inside lifecycle callbacks.

---

# ⚠️ Common Beginner Mistakes

### ❌ Putting Everything Inside `onCreate()`

Only initialize what is necessary.

Move long-running tasks elsewhere.

---

### ❌ Ignoring `onPause()`

Always pause work such as animations, media playback, or sensors when the Activity loses focus.

---

### ❌ Depending on `onDestroy()`

Do not assume it will always be called before your process is terminated.

---

### ❌ Forgetting `super`

Always call the superclass implementation:

```kotlin
super.onResume()
```

and similarly for the other lifecycle methods.

---

### ❌ Performing Heavy Work on the Main Thread

Long-running operations inside lifecycle methods can freeze the UI and trigger an "Application Not Responding" (ANR) error.

---

# 🎤 Interview Questions

### 1. What is the Activity Lifecycle?

The Activity Lifecycle is the sequence of callback methods that Android invokes as an Activity is created, becomes visible, interacts with the user, and is eventually stopped or destroyed.

---

### 2. Which lifecycle method is called first?

`onCreate()`

---

### 3. Which lifecycle method is called when the Activity becomes interactive?

`onResume()`

---

### 4. What is the difference between `onPause()` and `onStop()`?

- `onPause()` is called when the Activity loses focus but may still be partially visible.
- `onStop()` is called when the Activity is completely hidden.

---

### 5. Which callback is called when returning to a stopped Activity?

`onRestart()`

---

### 6. What happens when the Home button is pressed?

Typical sequence:

```text
onPause()

↓

onStop()
```

---

### 7. What happens when the Back button is pressed?

Typical sequence:

```text
onPause()

↓

onStop()

↓

onDestroy()
```

---

### 8. What is `onSaveInstanceState()` used for?

It saves temporary UI state so it can be restored if the Activity is recreated, such as after a screen rotation.

---

### 9. Is `onDestroy()` guaranteed to be called?

No. The Android system may terminate the process without calling `onDestroy()` in certain situations.

---

# 📌 Summary

- Every Activity follows a defined lifecycle managed by Android.
- The main lifecycle callbacks are `onCreate()`, `onStart()`, `onResume()`, `onPause()`, `onStop()`, `onRestart()`, and `onDestroy()`.
- Use each callback for the appropriate work to improve performance and reliability.
- Save temporary UI state with `onSaveInstanceState()`.
- A solid understanding of the Activity Lifecycle is essential for Android development and is a common interview topic.

---

# 📚 Related Topics

- Activity
- Intent
- AndroidManifest.xml
- Fragments
- ViewModel
- SavedStateHandle
- Process Lifecycle