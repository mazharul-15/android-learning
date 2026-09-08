# Activity Lifecycle (Deep Dive)

## 1. What is the Activity Lifecycle?

The **Activity Lifecycle** describes the different states an Android Activity goes through during its lifetime.

An Activity can be:

- Created
- Started
- Resumed
- Paused
- Stopped
- Restarted
- Destroyed

Android calls lifecycle callback methods to notify the Activity about these state changes.

### Basic Lifecycle

```text
        onCreate()
             ↓
        onStart()
             ↓
        onResume()
             ↓
     ┌───────────────┐
     │    Running    │
     └───────────────┘
             ↓
        onPause()
             ↓
        onStop()
             ↓
       onDestroy()
```

There is also:

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

# 2. Why Does Android Have a Lifecycle?

Android does not keep every Activity running forever.

For example:

```text
You open LoginActivity
        ↓
LoginActivity is running
        ↓
You open ProfileActivity
        ↓
LoginActivity may become stopped
```

Android needs to tell the Activity:

> "You are becoming visible."

or:

> "You are no longer visible."

or:

> "You are being recreated."

The lifecycle callbacks allow the developer to respond appropriately.

---

# 3. Main Lifecycle Methods

There are seven important lifecycle callbacks:

```text
onCreate()
onStart()
onResume()
onPause()
onStop()
onRestart()
onDestroy()
```

The most important initial sequence is:

```text
onCreate()
    ↓
onStart()
    ↓
onResume()
```

The Activity is then running and interactive.

---

# 4. onCreate()

`onCreate()` is called when Android creates the Activity.

Example:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
}
```

This is normally where you:

- Set the Activity's layout
- Initialize View Binding
- Initialize UI components
- Set click listeners
- Initialize Activity-specific data
- Read Intent data
- Restore saved instance state

Example:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val button = findViewById<Button>(R.id.btnLogin)

    button.setOnClickListener {
        // Handle click
    }
}
```

---

# 5. Why is onCreate() Important?

`onCreate()` is the normal starting point for Activity setup.

Think:

```text
Android creates Activity
        ↓
onCreate()
        ↓
Prepare Activity
        ↓
Set UI
        ↓
Initialize components
```

Important:

`onCreate()` does **not** necessarily mean:

> "This Activity will only ever be created once."

An Activity can be destroyed and recreated.

For example:

```text
Portrait
   ↓
Screen rotation
   ↓
Activity recreated
```

So `onCreate()` can run again.

---

# 6. onStart()

`onStart()` is called when the Activity becomes visible to the user.

```kotlin
override fun onStart() {
    super.onStart()

    // Activity is becoming visible
}
```

Typical flow:

```text
onCreate()
    ↓
onStart()
```

At this point:

```text
Activity
   ↓
Visible
```

But visibility does not necessarily mean the Activity has full user focus yet.

That happens at `onResume()`.

---

# 7. onResume()

`onResume()` is called when the Activity is in the foreground and ready for user interaction.

```kotlin
override fun onResume() {
    super.onResume()

    // Activity is interactive
}
```

Typical flow:

```text
onCreate()
    ↓
onStart()
    ↓
onResume()
    ↓
User can interact
```

Think:

```text
onStart()
    ↓
Visible

onResume()
    ↓
Visible + Interactive
```

---

# 8. Activity Running State

After:

```text
onCreate()
    ↓
onStart()
    ↓
onResume()
```

the Activity is normally in its active state.

```text
┌──────────────────────────────┐
│        Activity Running      │
│                              │
│       User interacts         │
│       with the UI            │
└──────────────────────────────┘
```

The Activity remains in this state until something causes it to lose focus or become invisible.

---

# 9. onPause()

`onPause()` is called when the Activity is losing foreground focus.

```kotlin
override fun onPause() {
    super.onPause()

    // Activity is losing focus
}
```

Example:

```text
Activity A
    ↓
Another UI appears
    ↓
Activity A loses foreground focus
    ↓
onPause()
```

Depending on the situation, Activity A may still be visible.

---

# 10. onPause() Example

Suppose:

```text
Activity A
+-----------------------+
|                       |
|       My App          |
|                       |
+-----------------------+
```

A dialog appears:

```text
Activity A
+-----------------------+
|       My App          |
|                       |
|    +-------------+    |
|    |   Dialog    |    |
|    +-------------+    |
+-----------------------+
```

Activity A may receive:

```text
onPause()
```

because it no longer has full foreground focus.

---

# 11. onStop()

`onStop()` is called when the Activity is no longer visible to the user.

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
     ↓
Activity A is no longer visible
     ↓
onStop()
```

Typical transition:

```text
onResume()
    ↓
onPause()
    ↓
onStop()
```

---

# 12. Difference Between onPause() and onStop()

This is very important.

### onPause()

The Activity is losing foreground focus.

It may still be partially visible.

### onStop()

The Activity is no longer visible.

```text
onResume()
    ↓
Activity active
    ↓
onPause()
    ↓
Losing focus
    ↓
onStop()
    ↓
Not visible
```

Remember:

```text
Pause = losing focus

Stop = no longer visible
```

---

# 13. onRestart()

`onRestart()` is called when a stopped Activity is about to start again.

```kotlin
override fun onRestart() {
    super.onRestart()

    // Activity is restarting
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

Example:

```text
Activity A
    ↓
Activity B opens
    ↓
Activity A → onStop()
    ↓
User closes Activity B
    ↓
Activity A → onRestart()
    ↓
onStart()
    ↓
onResume()
```

---

# 14. onDestroy()

`onDestroy()` is called when the Activity is being destroyed.

```kotlin
override fun onDestroy() {
    super.onDestroy()

    // Activity is being destroyed
}
```

Possible reasons include:

- The Activity is finishing.
- `finish()` was called.
- A configuration change causes recreation.
- Android is destroying the Activity as part of lifecycle management.

Important:

> Do not assume `onDestroy()` is always called before the entire application process is killed.

---

# 15. Complete Lifecycle Diagram

```text
                         ┌─────────────┐
                         │  onCreate() │
                         └──────┬──────┘
                                ↓
                         ┌─────────────┐
                         │  onStart()  │
                         └──────┬──────┘
                                ↓
                         ┌─────────────┐
                         │ onResume()  │
                         └──────┬──────┘
                                ↓
                     ┌────────────────────┐
                     │ Activity Running   │
                     │                    │
                     │ User interacts     │
                     └─────────┬──────────┘
                               ↓
                         ┌─────────────┐
                         │  onPause()  │
                         └──────┬──────┘
                                ↓
                         ┌─────────────┐
                         │  onStop()   │
                         └──────┬──────┘
                                ↓
                         ┌─────────────┐
                         │ onDestroy() │
                         └─────────────┘
```

Restart path:

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

# 16. Example: Opening Another Activity

Suppose we have:

```text
MainActivity
     ↓
ProfileActivity
```

Initially:

```text
MainActivity
    ↓
onCreate()
    ↓
onStart()
    ↓
onResume()
```

Now the user opens ProfileActivity.

MainActivity may go through:

```text
MainActivity
    ↓
onPause()
    ↓
onStop()
```

ProfileActivity:

```text
ProfileActivity
    ↓
onCreate()
    ↓
onStart()
    ↓
onResume()
```

Diagram:

```text
MainActivity
    │
    ├── onCreate()
    ├── onStart()
    ├── onResume()
    │
    │   User opens ProfileActivity
    │
    ├── onPause()
    └── onStop()

             ↓

ProfileActivity
    │
    ├── onCreate()
    ├── onStart()
    └── onResume()
```

---

# 17. Returning to the Previous Activity

Suppose:

```text
MainActivity
      ↓
ProfileActivity
```

The user presses Back.

ProfileActivity:

```text
onPause()
    ↓
onStop()
    ↓
onDestroy()
```

MainActivity:

```text
onRestart()
    ↓
onStart()
    ↓
onResume()
```

Diagram:

```text
ProfileActivity
    ↓
Back
    ↓
onPause()
    ↓
onStop()
    ↓
onDestroy()

        ↓

MainActivity
    ↓
onRestart()
    ↓
onStart()
    ↓
onResume()
```

---

# 18. Activity Lifecycle During Rotation

Screen rotation is an important example.

Suppose:

```text
Portrait
```

The Activity is running:

```text
onCreate()
onStart()
onResume()
```

The device rotates.

Android may recreate the Activity:

```text
onPause()
    ↓
onStop()
    ↓
onDestroy()
```

Then:

```text
onCreate()
    ↓
onStart()
    ↓
onResume()
```

Conceptually:

```text
Old Activity
     │
     ├── onPause()
     ├── onStop()
     └── onDestroy()
             ↓
       Activity recreated
             ↓
       New Activity
             │
             ├── onCreate()
             ├── onStart()
             └── onResume()
```

---

# 19. Why Does Android Recreate the Activity?

A configuration change can change the environment in which the Activity is displayed.

Examples include:

- Screen orientation
- Some screen configuration changes
- Locale changes
- Other configuration changes

For example:

```text
Portrait
   ↓
Landscape
```

Android may destroy the old Activity and create a new one appropriate for the new configuration.

---

# 20. savedInstanceState

When Android recreates an Activity, it may provide previously saved instance state through:

```kotlin
savedInstanceState: Bundle?
```

Example:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
}
```

The `?` means the value can be `null`.

---

# 21. Saving Instance State

Use:

```kotlin
override fun onSaveInstanceState(outState: Bundle) {
    outState.putString("username", "Mazharul")

    super.onSaveInstanceState(outState)
}
```

The data is stored using a key:

```text
"username"
```

and a value:

```text
"Mazharul"
```

---

# 22. Restoring Instance State

In `onCreate()`:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val username =
        savedInstanceState?.getString("username")
}
```

Because `savedInstanceState` is nullable:

```kotlin
savedInstanceState?.getString("username")
```

uses the safe-call operator.

---

# 23. savedInstanceState Lifecycle Flow

```text
Activity running
       ↓
Configuration change
       ↓
onSaveInstanceState()
       ↓
Old Activity destroyed
       ↓
New Activity created
       ↓
onCreate(savedInstanceState)
       ↓
Restore state
```

---

# 24. What Should Be Saved?

Instance state is suitable for **small amounts of UI state**.

Examples:

```text
Text entered by user
Selected tab
Scroll position
Checkbox state
Selected item
Temporary UI state
```

Do not use instance state as your main database.

For persistent application data, use appropriate storage such as:

- Room
- DataStore
- Files
- Server/database

---

# 25. Important Difference: Intent vs savedInstanceState

These are commonly confused.

## Intent

Used to pass data when starting another Activity.

```kotlin
val intent = Intent(this, ProfileActivity::class.java)

intent.putExtra("username", "Mazharul")

startActivity(intent)
```

Concept:

```text
Activity A
    ↓
Intent + Data
    ↓
Activity B
```

---

## savedInstanceState

Used to restore Activity instance state after recreation.

```text
Activity
    ↓
Recreated
    ↓
savedInstanceState
    ↓
New Activity
```

---

# 26. Activity Lifecycle and View State

Some UI state may be automatically restored by Android when views have appropriate IDs and state-saving behavior.

For example:

```xml
<EditText
    android:id="@+id/etName"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

Android can restore certain view state when the Activity is recreated.

However, developers should still understand instance-state restoration because not all application state is automatically restored.

---

# 27. Lifecycle Logging Example

You can observe the lifecycle using Logcat.

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ActivityLifecycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.d("ActivityLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("ActivityLifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("ActivityLifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("ActivityLifecycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("ActivityLifecycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("ActivityLifecycle", "onDestroy")
    }
}
```

---

# 28. What You May See in Logcat

When opening the Activity:

```text
onCreate
onStart
onResume
```

When another Activity completely covers it:

```text
onPause
onStop
```

When returning:

```text
onRestart
onStart
onResume
```

When the Activity finishes:

```text
onPause
onStop
onDestroy
```

The exact callbacks can vary depending on the situation.

---

# 29. finish() and Lifecycle

When you call:

```kotlin
finish()
```

the current Activity is requested to finish.

Typical flow:

```text
onPause()
    ↓
onStop()
    ↓
onDestroy()
```

Example:

```kotlin
startActivity(
    Intent(this, HomeActivity::class.java)
)

finish()
```

This is common after login:

```text
LoginActivity
     ↓
Successful login
     ↓
HomeActivity
     ↓
finish LoginActivity
```

Now pressing Back from HomeActivity won't return to the finished LoginActivity.

---

# 30. Activity Back Stack

Android maintains a task/back stack of Activities.

Example:

```text
LoginActivity
      ↓
HomeActivity
      ↓
ProfileActivity
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

Press Back:

```text
ProfileActivity
      ↓
HomeActivity
```

Press Back again:

```text
HomeActivity
      ↓
LoginActivity
```

---

# 31. Lifecycle + Back Stack

Suppose:

```text
MainActivity
    ↓
ProfileActivity
```

When ProfileActivity is opened:

```text
MainActivity
    ↓
onPause()
    ↓
onStop()

ProfileActivity
    ↓
onCreate()
    ↓
onStart()
    ↓
onResume()
```

When Back is pressed:

```text
ProfileActivity
    ↓
onPause()
    ↓
onStop()
    ↓
onDestroy()

MainActivity
    ↓
onRestart()
    ↓
onStart()
    ↓
onResume()
```

---

# 32. Common Lifecycle Mistakes

## Mistake 1: Treating onCreate() as "runs only once"

Incorrect:

```text
onCreate() = once forever
```

Correct:

```text
An Activity instance has one onCreate(),
but Android may create a new Activity instance later.
```

---

## Mistake 2: Assuming onDestroy() is guaranteed

Do not rely on:

```kotlin
override fun onDestroy() {
    // critical data saving
}
```

for critical persistence.

Critical data should be saved through appropriate persistent storage.

---

## Mistake 3: Doing heavy work in onPause()

`onPause()` should return quickly.

Avoid long-running operations there.

---

## Mistake 4: Saving everything in savedInstanceState

`Bundle` is intended for small amounts of state, not large application data.

---

## Mistake 5: Confusing onStart() and onResume()

Remember:

```text
onStart()
    ↓
Visible

onResume()
    ↓
Visible + interactive
```

---

# 33. Lifecycle Method Comparison

| Method | Activity State | Common Purpose |
|---|---|---|
| `onCreate()` | Being created | Initialize Activity |
| `onStart()` | Visible | Prepare for visibility |
| `onResume()` | Foreground/interactive | Resume interactive work |
| `onPause()` | Losing focus | Pause foreground work |
| `onStop()` | Not visible | Stop visibility-related work |
| `onRestart()` | Starting after stop | Prepare to become visible again |
| `onDestroy()` | Being destroyed | Cleanup Activity instance |

---

# 34. Simple Mental Model

Think of an Activity like a person entering and leaving a room.

```text
onCreate()
↓
Person enters the room

onStart()
↓
Person is visible

onResume()
↓
Person is actively interacting

onPause()
↓
Person's attention is interrupted

onStop()
↓
Person leaves the room

onRestart()
↓
Person comes back

onDestroy()
↓
The room/person instance is being removed
```

---

# 35. Most Important Lifecycle Flows

## First launch

```text
onCreate()
    ↓
onStart()
    ↓
onResume()
```

---

## Another Activity covers it

```text
onPause()
    ↓
onStop()
```

---

## Return to stopped Activity

```text
onRestart()
    ↓
onStart()
    ↓
onResume()
```

---

## Activity finishes

```text
onPause()
    ↓
onStop()
    ↓
onDestroy()
```

---

## Configuration change / recreation

Conceptually:

```text
onPause()
    ↓
onStop()
    ↓
onDestroy()
    ↓
onCreate()
    ↓
onStart()
    ↓
onResume()
```

The exact lifecycle sequence can vary depending on the specific situation.

---

# 36. Professional Best Practices

### Use `onCreate()` for

- Initial Activity setup
- `setContentView()`
- View Binding initialization
- Click listeners
- Reading initial Intent data
- Restoring instance state

### Use `onStart()` for

Work that should happen when the Activity becomes visible.

### Use `onResume()` for

Work that should happen when the Activity is in the foreground and interactive.

### Use `onPause()` for

Quickly pausing foreground-related work.

### Use `onStop()` for

Work that should stop when the Activity is no longer visible.

### Use `onDestroy()` carefully

Do not use it as your only mechanism for saving critical data.

---

# 37. Interview Questions

## What is Activity Lifecycle?

The Activity Lifecycle is the sequence of states and callbacks an Activity goes through during creation, visibility, interaction, interruption, stopping, restarting, and destruction.

---

## What is the first lifecycle method?

Normally:

```text
onCreate()
```

---

## What comes after onCreate()?

```text
onStart()
```

Then:

```text
onResume()
```

---

## Difference between onStart() and onResume()?

`onStart()` means the Activity is becoming visible.

`onResume()` means the Activity is in the foreground and ready for user interaction.

---

## Difference between onPause() and onStop()?

`onPause()` means the Activity is losing foreground focus.

`onStop()` means the Activity is no longer visible.

---

## What is onRestart()?

`onRestart()` is called when a stopped Activity is starting again.

---

## When is onDestroy() called?

It is called when the Activity instance is being destroyed, such as when it finishes or is recreated for a configuration change.

---

## Can onCreate() be called multiple times?

Yes.

An Activity can be destroyed and a new Activity instance can be created.

---

## What is savedInstanceState?

It is a nullable `Bundle` that may contain previously saved instance state when an Activity is recreated.

---

# 38. Final Revision

Remember this:

```text
CREATE
  ↓
START
  ↓
RESUME
  ↓
RUNNING
  ↓
PAUSE
  ↓
STOP
  ↓
DESTROY
```

Returning from STOP:

```text
STOP
 ↓
RESTART
 ↓
START
 ↓
RESUME
```

### One-line meanings

```text
onCreate()  → Activity is created
onStart()   → Activity becomes visible
onResume()  → Activity becomes interactive
onPause()   → Activity loses foreground focus
onStop()    → Activity is no longer visible
onRestart() → Stopped Activity is starting again
onDestroy() → Activity instance is being destroyed
```

### Core mental model

```text
          Activity
             │
       ┌─────┴─────┐
       │           │
    Lifecycle    UI State
       │           │
       ↓           ↓
 onCreate()    savedInstanceState
 onStart()
 onResume()
 onPause()
 onStop()
 onRestart()
 onDestroy()
```

---

# 39. Key Takeaways

1. An Activity has a lifecycle managed by Android.
2. `onCreate()` is used for initial Activity setup.
3. `onStart()` means the Activity is becoming visible.
4. `onResume()` means the Activity is in the foreground and interactive.
5. `onPause()` means the Activity is losing foreground focus.
6. `onStop()` means the Activity is no longer visible.
7. `onRestart()` occurs when a stopped Activity is starting again.
8. `onDestroy()` occurs when an Activity instance is being destroyed.
9. An Activity can be recreated.
10. Screen rotation can cause Activity recreation.
11. `savedInstanceState` can help restore small amounts of Activity instance state.
12. `Bundle` stores key-value data.
13. `Intent` and `savedInstanceState` serve different purposes.
14. `finish()` finishes the current Activity.
15. Do not rely on `onDestroy()` for critical data persistence.
16. Do not put heavy work in lifecycle callbacks that must return quickly.