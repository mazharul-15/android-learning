# Activity Notes

## 1. What is an Activity?

An **Activity** represents a single screen or user interaction point in an Android application.

For example:

```text
Login Screen       → LoginActivity
Registration       → RegistrationActivity
Profile Screen     → ProfileActivity
Settings Screen     → SettingsActivity
```

An Activity usually contains a UI layout and Kotlin code that controls that UI.

Example:

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}
```

---

# 2. Activity Structure

A basic Activity normally looks like this:

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}
```

### Important parts

```kotlin
class MainActivity : AppCompatActivity()
```

Creates an Activity class.

```kotlin
override fun onCreate(savedInstanceState: Bundle?)
```

Called when Android creates the Activity.

```kotlin
super.onCreate(savedInstanceState)
```

Calls the parent Activity's implementation.

```kotlin
setContentView(R.layout.activity_main)
```

Connects the XML layout with the Activity.

---

# 3. Activity and XML

An Activity usually displays an XML layout.

### XML

```xml
<TextView
    android:id="@+id/tvWelcome"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Welcome"/>
```

### Kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        tvWelcome.text = "Hello Android"
    }
}
```

Relationship:

```text
activity_main.xml
       ↓
   UI Design
       ↓
 MainActivity
       ↓
Kotlin Logic
```

---

# 4. setContentView()

`setContentView()` tells Android which XML layout should be displayed by the Activity.

```kotlin
setContentView(R.layout.activity_main)
```

Here:

```text
R.layout.activity_main
        │
        └── activity_main.xml
```

Example:

```text
res/
└── layout/
    ├── activity_main.xml
    ├── activity_login.xml
    └── activity_profile.xml
```

An Activity can display one layout at a time using `setContentView()`.

---

# 5. Activity Lifecycle

An Activity has a lifecycle because Android creates, displays, pauses, stops, and destroys Activities depending on what the user does and what the system needs.

Main lifecycle methods:

```text
onCreate()
    ↓
onStart()
    ↓
onResume()
    ↓
[Activity running]
    ↓
onPause()
    ↓
onStop()
    ↓
onDestroy()
```

---

# 6. onCreate()

`onCreate()` is called when Android creates the Activity.

It is commonly used for:

- Setting the layout
- Initializing views
- Setting click listeners
- Initializing data
- Setting up View Binding

Example:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
}
```

---

# 7. onStart()

Called when the Activity becomes visible to the user.

```kotlin
override fun onStart() {
    super.onStart()

    // Activity is becoming visible
}
```

Lifecycle:

```text
onCreate()
    ↓
onStart()
```

---

# 8. onResume()

Called when the Activity comes to the foreground and the user can interact with it.

```kotlin
override fun onResume() {
    super.onResume()

    // Activity is ready for user interaction
}
```

At this point:

```text
Activity
   ↓
Visible
   ↓
Interactive
```

---

# 9. onPause()

Called when the Activity is no longer in the foreground but may still be partially visible.

Example:

```text
Activity A
    ↓
Dialog / another Activity appears
```

```kotlin
override fun onPause() {
    super.onPause()

    // Pause operations that should not continue
}
```

Do not use `onPause()` for heavy operations.

---

# 10. onStop()

Called when the Activity is no longer visible.

```kotlin
override fun onStop() {
    super.onStop()

    // Activity is no longer visible
}
```

Example:

```text
Activity A
     ↓
Activity B completely covers A

A → onStop()
```

---

# 11. onDestroy()

Called when the Activity is being destroyed.

```kotlin
override fun onDestroy() {
    super.onDestroy()

    // Cleanup Activity-specific resources
}
```

Possible reasons:

- User finishes the Activity
- `finish()` is called
- Configuration changes
- Android destroys the Activity

Important:

`onDestroy()` is **not guaranteed to be called in every process-death situation**.

---

# 12. Complete Activity Lifecycle

```text
              onCreate()
                  ↓
              onStart()
                  ↓
              onResume()
                  ↓
        ┌──────────────────┐
        │ Activity Running │
        └──────────────────┘
                  ↓
              onPause()
                  ↓
              onStop()
                  ↓
            onDestroy()
```

---

# 13. Returning to an Activity

Suppose:

```text
Activity A
    ↓
Activity B
```

Activity A:

```text
onCreate()
onStart()
onResume()
```

Then B opens:

```text
Activity A
    ↓
onPause()
    ↓
onStop()
```

When B closes and A becomes visible again:

```text
onRestart()
    ↓
onStart()
    ↓
onResume()
```

---

# 14. onRestart()

`onRestart()` is called when a stopped Activity is about to start again.

Example:

```kotlin
override fun onRestart() {
    super.onRestart()

    // Activity is restarting after being stopped
}
```

Typical flow:

```text
onStop()
   ↓
onRestart()
   ↓
onStart()
   ↓
onResume()
```

---

# 15. Activity Lifecycle Summary

| Method | Meaning |
|---|---|
| `onCreate()` | Activity is being created |
| `onStart()` | Activity becomes visible |
| `onResume()` | Activity becomes interactive |
| `onPause()` | Activity is losing foreground focus |
| `onStop()` | Activity is no longer visible |
| `onRestart()` | Stopped Activity is starting again |
| `onDestroy()` | Activity is being destroyed |

---

# 16. Why Does Android Need an Activity Lifecycle?

Android controls Activities according to the current situation.

For example:

```text
User opens App
      ↓
onCreate()
      ↓
onStart()
      ↓
onResume()
      ↓
User opens another screen
      ↓
onPause()
      ↓
onStop()
```

This allows Android and developers to manage resources and UI state appropriately.

---

# 17. Multiple Activities

An application can contain multiple Activities.

Example:

```text
MyApp
│
├── MainActivity
├── LoginActivity
├── RegisterActivity
└── ProfileActivity
```

Each Activity can represent a different screen.

Example:

```text
LoginActivity
      ↓
MainActivity
      ↓
ProfileActivity
```

---

# 18. Are Multiple Activities Added to the Manifest?

Yes.

Every Activity that needs to be instantiated by Android should be declared in the application's manifest.

Example:

```xml
<application
    ...>

    <activity android:name=".ProfileActivity" />

    <activity android:name=".RegisterActivity" />

    <activity android:name=".LoginActivity" />

    <activity
        android:name=".MainActivity"
        android:exported="true">

        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>

    </activity>

</application>
```

---

# 19. Launcher Activity

The Activity containing:

```xml
<intent-filter>
    <action android:name="android.intent.action.MAIN" />

    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```

is the entry point of the application when the user launches it from the launcher.

Example:

```xml
<activity
    android:name=".MainActivity"
    android:exported="true">

    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>

</activity>
```

---

# 20. Activity and Intent

An Activity does not normally directly open another Activity using a constructor.

Instead, Android uses an **Intent**.

An Intent is a message that tells Android to perform an action, such as opening another Activity or launching another app or Android component.

Example:

```text
MainActivity
     │
     │ Intent
     ↓
ProfileActivity
```

---

# 21. Explicit Intent

Use an Explicit Intent when you know exactly which Activity should open.

Example:

```kotlin
val intent = Intent(this, ProfileActivity::class.java)

startActivity(intent)
```

Or:

```kotlin
startActivity(
    Intent(this, ProfileActivity::class.java)
)
```

Typical use:

```text
LoginActivity
      ↓
HomeActivity
```

```text
MainActivity
      ↓
ProfileActivity
```

---

# 22. Implicit Intent

Use an Implicit Intent when you want Android to find an appropriate component that can perform an action.

Examples:

- Open website
- Open dialer
- Send email
- Share text
- Open map

### Open Website

```kotlin
val intent = Intent(
    Intent.ACTION_VIEW,
    Uri.parse("https://www.google.com")
)

startActivity(intent)
```

### Open Dialer

```kotlin
val intent = Intent(
    Intent.ACTION_DIAL,
    Uri.parse("tel:01700000000")
)

startActivity(intent)
```

### Share Text

```kotlin
val intent = Intent(Intent.ACTION_SEND).apply {
    type = "text/plain"
    putExtra(Intent.EXTRA_TEXT, "Hello Android")
}

startActivity(
    Intent.createChooser(intent, "Share using")
)
```

---

# 23. Activity vs Intent

These are different things.

### Activity

A component/screen that provides a UI and handles user interaction.

### Intent

A message/request used to ask Android to perform an action or route to another component.

Think:

```text
Activity = Destination / screen

Intent = Message / request
```

Example:

```text
MainActivity
      │
      │ "Open ProfileActivity"
      │
      ↓
    Intent
      │
      ↓
ProfileActivity
```

---

# 24. Bundle

A `Bundle` is a container for key-value data.

Example:

```kotlin
val bundle = Bundle()

bundle.putString("name", "Mazharul")
bundle.putInt("age", 25)
```

Read:

```kotlin
val name = bundle.getString("name")
val age = bundle.getInt("age")
```

A Bundle can be used to pass small amounts of data between Android components.

---

# 25. Passing Data Between Activities

### Activity A

```kotlin
val intent = Intent(this, ProfileActivity::class.java)

intent.putExtra("name", "Mazharul")
intent.putExtra("email", "mazharul@example.com")

startActivity(intent)
```

### Activity B

```kotlin
val name = intent.getStringExtra("name")
val email = intent.getStringExtra("email")
```

Concept:

```text
MainActivity
     │
     │ Intent
     │
     │ name = "Mazharul"
     │ email = "..."
     ↓
ProfileActivity
```

---

# 26. savedInstanceState

` savedInstanceState` is a `Bundle?` provided to `onCreate()` that can contain previously saved Activity state.

Example:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
}
```

The parameter:

```kotlin
savedInstanceState: Bundle?
```

means:

> Android may provide previously saved state when recreating this Activity.

It can be `null`.

---

# 27. Why Does savedInstanceState Exist?

Consider:

```text
User types:

Name: Mazharul
```

Then the device rotates.

Android may destroy and recreate the Activity:

```text
Old Activity
     ↓
Destroyed
     ↓
New Activity
```

If you have important small UI state that you need to restore, you can save it.

---

# 28. Saving State

Use `onSaveInstanceState()`:

```kotlin
override fun onSaveInstanceState(outState: Bundle) {
    outState.putString("name", "Mazharul")

    super.onSaveInstanceState(outState)
}
```

---

# 29. Restoring State

Retrieve it in `onCreate()`:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val name = savedInstanceState?.getString("name")
}
```

The safe call:

```kotlin
savedInstanceState?.getString("name")
```

is used because `savedInstanceState` can be `null`.

---

# 30. savedInstanceState vs Intent Extras

These are different.

### Intent Extras

Used to pass data when opening another Activity.

```text
Activity A
   ↓
 Intent
   ↓
Activity B
```

Example:

```kotlin
intent.putExtra("name", "Mazharul")
```

### savedInstanceState

Used to restore previously saved Activity state after Activity recreation.

```text
Activity
   ↓
Destroyed / recreated
   ↓
New Activity
```

---

# 31. Activity Recreation

An Activity can be recreated for reasons such as configuration changes.

Common example:

```text
Portrait
   ↓
Screen Rotation
   ↓
Landscape
```

The Activity may go through:

```text
onPause()
onStop()
onDestroy()

        ↓

onCreate()
onStart()
onResume()
```

State restoration is therefore important for appropriate UI state.

---

# 32. finish()

`finish()` tells Android to finish the current Activity.

Example:

```kotlin
finish()
```

Common use:

```text
LoginActivity
      ↓
HomeActivity
      ↓
finish LoginActivity
```

After login, you may not want the user to return to the login screen using the Back button.

Example:

```kotlin
startActivity(
    Intent(this, HomeActivity::class.java)
)

finish()
```

---

# 33. Back Button and Activity Stack

Android maintains a back stack of Activities.

Example:

```text
LoginActivity
     ↓
HomeActivity
     ↓
ProfileActivity
```

Back:

```text
ProfileActivity
     ↓ Back
HomeActivity
     ↓ Back
LoginActivity
```

Conceptually:

```text
Top
│
ProfileActivity
HomeActivity
LoginActivity
│
Bottom
```

When an Activity is finished, it is removed from the stack.

---

# 34. Activity Launch Flow

Example:

```text
User taps Login
       ↓
Button click
       ↓
Intent created
       ↓
startActivity()
       ↓
Android resolves the request
       ↓
LoginActivity starts
       ↓
onCreate()
       ↓
onStart()
       ↓
onResume()
```

---

# 35. Common Activity Lifecycle Example

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.d("Lifecycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.d("Lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("Lifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("Lifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("Lifecycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("Lifecycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("Lifecycle", "onDestroy")
    }
}
```

---

# 36. Common Mistakes

### Mistake 1

Thinking an Activity is just an XML file.

Wrong:

```text
Activity = XML
```

Correct:

```text
Activity
   +
XML Layout
   +
Kotlin Code
```

---

### Mistake 2

Thinking `onCreate()` runs only once during the entire app lifetime.

An Activity can be recreated, so `onCreate()` can run again.

---

### Mistake 3

Putting everything in `onDestroy()`.

`onDestroy()` is not guaranteed in every process-death situation.

---

### Mistake 4

Confusing Intent and Activity.

```text
Activity = Android component / screen
Intent = message / request
```

---

### Mistake 5

Thinking `savedInstanceState` is always available.

It is nullable:

```kotlin
Bundle?
```

Therefore:

```kotlin
savedInstanceState?.getString("name")
```

may be appropriate.

---

# 37. Activity Best Practices

- Keep Activity responsible for UI-related work and user interaction.
- Avoid putting all application/business logic directly inside the Activity.
- Use meaningful Activity names.
- Use View Binding rather than repeated `findViewById()` when appropriate.
- Handle lifecycle-dependent work in the appropriate lifecycle methods.
- Save only necessary small UI state with instance-state mechanisms.
- Use Explicit Intents for your own Activities.
- Use Implicit Intents for actions that another app/component should handle.
- Do not assume `onDestroy()` is always called.
- Avoid long-running or blocking work on the main thread.

---

# 38. Important Activity Concepts to Remember

```text
Activity
│
├── onCreate()
├── onStart()
├── onResume()
├── onPause()
├── onStop()
├── onRestart()
└── onDestroy()
```

Supporting concepts:

```text
Activity
│
├── XML Layout
├── Intent
├── Bundle
├── savedInstanceState
├── Manifest
├── Back Stack
└── Activity Lifecycle
```

---

# 39. Interview Questions

## What is an Activity?

An Activity is an Android application component that provides a screen or interaction point for the user.

---

## What is the Activity lifecycle?

The Activity lifecycle describes the different states an Activity goes through as it is created, becomes visible, becomes interactive, loses focus, becomes hidden, and is destroyed.

---

## What is onCreate()?

`onCreate()` is called when Android creates an Activity. It is commonly used to initialize the Activity and set its layout.

---

## What is onResume()?

`onResume()` is called when the Activity comes to the foreground and becomes ready for user interaction.

---

## What is the difference between onPause() and onStop()?

`onPause()` means the Activity is losing foreground focus.

`onStop()` means the Activity is no longer visible.

---

## What is an Intent?

An Intent is a message that tells Android to perform an action, such as opening another Activity or launching another app or Android component.

---

## Explicit vs Implicit Intent?

### Explicit

The target component is explicitly specified.

```kotlin
Intent(this, ProfileActivity::class.java)
```

### Implicit

The desired action is specified, and Android determines an appropriate component.

```kotlin
Intent(
    Intent.ACTION_VIEW,
    Uri.parse("https://www.google.com")
)
```

---

## What is savedInstanceState?

`savedInstanceState` is a nullable `Bundle` that may contain previously saved Activity state when Android recreates the Activity.

---

## What is a Bundle?

A Bundle is a key-value container used by Android to store and transfer small amounts of data.

---

## What is finish()?

`finish()` requests that the current Activity be finished and removed from the Activity stack.

---

# 40. Quick Revision

### Activity

```text
A screen / interaction point
```

### onCreate()

```text
Activity created
```

### onStart()

```text
Activity visible
```

### onResume()

```text
Activity interactive
```

### onPause()

```text
Losing foreground focus
```

### onStop()

```text
No longer visible
```

### onRestart()

```text
Stopped Activity starting again
```

### onDestroy()

```text
Activity being destroyed
```

### Intent

```text
Message/request to Android
```

### Bundle

```text
Key-value data container
```

### savedInstanceState

```text
Previously saved Activity state
```

### finish()

```text
Finish current Activity
```

---

# Final Mental Model

```text
                    Android App
                         │
                         ↓
                      Activity
                         │
              ┌──────────┴──────────┐
              │                     │
             XML                 Kotlin
              │                     │
              └──────────┬──────────┘
                         │
                    Lifecycle
                         │
       ┌─────────────────┼─────────────────┐
       ↓                 ↓                 ↓
   onCreate()        onResume()        onDestroy()
       │
       ↓
     Intent
       │
       ↓
 Another Activity / Android Component / App
```

---

# Key Things to Remember

1. An Activity is an Android component that provides a screen or interaction point.
2. `setContentView()` connects an XML layout to an Activity.
3. Activities have a lifecycle.
4. `onCreate()` is commonly used for initialization.
5. `onResume()` means the Activity is ready for interaction.
6. `onPause()` means the Activity is losing foreground focus.
7. `onStop()` means the Activity is no longer visible.
8. An Activity can be recreated.
9. `savedInstanceState` helps restore previously saved instance state.
10. `Bundle` stores key-value data.
11. Intent is a message/request used to perform actions or navigate between components.
12. Use Explicit Intent for your own Activities.
13. Use Implicit Intent for general actions such as opening a website, dialer, map, email, or sharing.
14. Activities that need to be instantiated by Android should be declared in the manifest.
15. `finish()` finishes the current Activity.