# Fragment → Activity Data Passing

## Overview

A Fragment can send data or events to the Activity that contains it.

There are two important approaches to learn:

1. **Interface Callback**
2. **Fragment Result API**

```text
Fragment
   │
   │ data / event
   ▼
Activity
```

---

# Part 1 — Interface Callback

## 1. What is an Interface Callback?

The Fragment defines an interface containing a function.

The Activity implements that interface.

The Fragment then calls the interface function when it wants to send data.

```text
ProfileFragment
      │
      │ calls callback
      ▼
OnNameSentListener
      │
      ▼
MainActivity
```

---

## 2. Example Goal

The Fragment will send:

```text
name = "Rahim"
```

to the Activity.

---

## 3. Step 1 — Create the Interface

Inside `ProfileFragment`:

```kotlin
interface OnNameSentListener {
    fun onNameSent(name: String)
}
```

This is a contract.

It says:

> Whoever wants to receive the name must implement `onNameSent()`.

---

## 4. Step 2 — Activity Implements the Interface

```kotlin
class MainActivity : AppCompatActivity(),
    ProfileFragment.OnNameSentListener {

    override fun onNameSent(name: String) {

        Toast.makeText(
            this,
            "Received: $name",
            Toast.LENGTH_SHORT
        ).show()
    }
}
```

The Activity is now promising to provide:

```kotlin
override fun onNameSent(name: String)
```

---

## 5. Step 3 — Fragment Keeps a Listener Reference

Inside the Fragment:

```kotlin
private var listener: OnNameSentListener? = null
```

This variable will hold a reference to the Activity's implementation of the interface.

Conceptually:

```text
listener
    │
    ▼
MainActivity
```

---

## 6. Step 4 — Connect Listener in `onAttach()`

```kotlin
override fun onAttach(context: Context) {
    super.onAttach(context)

    listener = context as? OnNameSentListener
}
```

### What is `context`?

When the Fragment is attached to an Activity, the Activity is available through `context`.

```text
ProfileFragment
      │
      │ attached to
      ▼
MainActivity
```

### What does `as?` mean?

```kotlin
context as? OnNameSentListener
```

means:

> Try to treat `context` as an `OnNameSentListener`. If it does not implement the interface, return `null`.

---

## 7. Step 5 — Send Data

For example:

```kotlin
listener?.onNameSent("Rahim")
```

This means:

> Call `onNameSent()` on the object stored in `listener`.

Because `listener` points to the Activity:

```text
ProfileFragment
      │
      │ listener?.onNameSent("Rahim")
      ▼
MainActivity
      │
      ▼
onNameSent("Rahim")
```

---

## 8. Complete Interface Example

### `fragment_profile.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <Button
        android:id="@+id/sendButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Send Name" />

</LinearLayout>
```

### `ProfileFragment.kt`

```kotlin
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    interface OnNameSentListener {
        fun onNameSent(name: String)
    }

    private var listener: OnNameSentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? OnNameSentListener
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val sendButton =
            view.findViewById<Button>(R.id.sendButton)

        sendButton.setOnClickListener {

            listener?.onNameSent("Rahim")
        }
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }
}
```

### `MainActivity.kt`

```kotlin
class MainActivity : AppCompatActivity(),
    ProfileFragment.OnNameSentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onNameSent(name: String) {

        Toast.makeText(
            this,
            "Received: $name",
            Toast.LENGTH_SHORT
        ).show()
    }
}
```

---

# 9. Why `onDetach()`?

We set:

```kotlin
listener = null
```

when the Fragment is detached.

```kotlin
override fun onDetach() {
    listener = null
    super.onDetach()
}
```

This prevents the Fragment from keeping an unnecessary reference to the Activity after they are no longer connected.

---

# 10. Interface Callback Mental Model

Think of the interface as a phone number.

```text
Fragment
   │
   │ listener
   ▼
Activity
```

The Fragment doesn't need to know exactly which Activity it is talking to.

It only knows:

> "This object can receive `onNameSent()`."

---

# Part 2 — Fragment Result API

The second approach is the **Fragment Result API**.

Instead of maintaining an interface reference, the Fragment sends a result through the FragmentManager.

```text
Fragment
   │
   │ setFragmentResult()
   ▼
FragmentManager
   │
   │ matching key
   ▼
Activity
   │
   │ setFragmentResultListener()
   ▼
receive data
```

---

# 11. Sender — Fragment

```kotlin
val result = Bundle()

result.putString("name", "Rahim")

parentFragmentManager.setFragmentResult(
    "profile_result",
    result
)
```

There are three important parts:

```kotlin
parentFragmentManager
```

The FragmentManager managing this Fragment.

```kotlin
setFragmentResult()
```

The function that sends the result.

```kotlin
"profile_result"
```

The key used to identify this result.

---

# 12. What is `result`?

We create:

```kotlin
val result = Bundle()
```

Then put data inside:

```kotlin
result.putString("name", "Rahim")
```

Conceptually:

```text
Bundle
┌──────────────────┐
│ name → Rahim     │
└──────────────────┘
```

Then:

```kotlin
parentFragmentManager.setFragmentResult(
    "profile_result",
    result
)
```

means:

> Send this Bundle as the result named `profile_result`.

---

# 13. Receiver — Activity

The Activity listens:

```kotlin
supportFragmentManager.setFragmentResultListener(
    "profile_result",
    this
) { _, bundle ->

    val name =
        bundle.getString("name")

}
```

---

# 14. Understand Each Line

### `supportFragmentManager`

```kotlin
supportFragmentManager
```

The FragmentManager associated with the Activity.

You already use it for Fragment transactions:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(...)
    .commit()
```

---

### `setFragmentResultListener()`

```kotlin
supportFragmentManager.setFragmentResultListener(...)
```

Means:

> Listen for a Fragment Result.

---

### `"profile_result"`

```kotlin
"profile_result"
```

This must match the sender's key.

Sender:

```kotlin
setFragmentResult(
    "profile_result",
    result
)
```

Receiver:

```kotlin
setFragmentResultListener(
    "profile_result",
    this
)
```

---

### `this`

Inside `MainActivity`:

```kotlin
this
```

refers to the current `MainActivity`.

It is used as the lifecycle owner for the listener.

---

### `{ _, bundle -> }`

This is a lambda/callback that runs when the result arrives.

The callback receives two values:

```kotlin
{ requestKey, bundle ->
```

`requestKey` is the result key.

We don't need it because we already know the key, so we write:

```kotlin
{ _, bundle ->
```

The `_` means:

> I don't need this value.

`bundle` contains the data sent by the Fragment.

---

# 15. Complete Fragment Result API Example

### `fragment_profile.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <Button
        android:id="@+id/sendButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Send Name" />

</LinearLayout>
```

### `ProfileFragment.kt`

```kotlin
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object {
        const val PROFILE_RESULT = "profile_result"
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val sendButton =
            view.findViewById<Button>(R.id.sendButton)

        sendButton.setOnClickListener {

            val result = Bundle()

            result.putString("name", "Rahim")

            parentFragmentManager.setFragmentResult(
                PROFILE_RESULT,
                result
            )
        }
    }
}
```

### `MainActivity.kt`

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.setFragmentResultListener(
            ProfileFragment.PROFILE_RESULT,
            this
        ) { _, bundle ->

            val name =
                bundle.getString("name")

            Toast.makeText(
                this,
                "Received: $name",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
```

---

# 16. Why Use a Constant for the Key?

Instead of repeatedly typing:

```kotlin
"profile_result"
```

we can define:

```kotlin
const val PROFILE_RESULT = "profile_result"
```

Then:

```kotlin
setFragmentResult(
    PROFILE_RESULT,
    result
)
```

and:

```kotlin
setFragmentResultListener(
    ProfileFragment.PROFILE_RESULT,
    this
)
```

This reduces typing mistakes.

---

# 17. Interface vs Fragment Result API

## Interface Callback

```text
Fragment
   │
   │ listener
   ▼
Activity
```

Fragment:

```kotlin
listener?.onNameSent("Rahim")
```

Activity:

```kotlin
override fun onNameSent(name: String) {
    // receive
}
```

You need:

- Interface
- Listener variable
- `onAttach()`
- `onDetach()`
- Activity implements interface

---

## Fragment Result API

```text
Fragment
   │
   │ result
   ▼
FragmentManager
   │
   ▼
Activity
```

Fragment:

```kotlin
parentFragmentManager.setFragmentResult(
    "profile_result",
    result
)
```

Activity:

```kotlin
supportFragmentManager.setFragmentResultListener(
    "profile_result",
    this
) { _, bundle ->

}
```

You don't need to maintain a listener reference yourself.

---

# 18. When to Use Which?

### Interface Callback

Useful for understanding callback-based communication and for cases where you explicitly want a direct contract between the Fragment and its host.

```text
Fragment → Activity
```

### Fragment Result API

A modern Android mechanism for passing a one-time result/event through a FragmentManager.

```text
Fragment → Activity
```

It is also useful for:

```text
Fragment → Fragment
```

---

# 19. Important Difference

Don't confuse these with Activity → Fragment arguments.

### Activity → Fragment

Use:

```kotlin
newInstance()
```

and:

```kotlin
arguments
```

```text
Activity
   │
   │ initial data
   ▼
Fragment
```

### Fragment → Activity

Use either:

```kotlin
Interface Callback
```

or:

```kotlin
Fragment Result API
```

```text
Fragment
   │
   │ data / result
   ▼
Activity
```

---

# 20. Quick Cheat Sheet

## Interface Callback

### Interface

```kotlin
interface OnNameSentListener {
    fun onNameSent(name: String)
}
```

### Listener variable

```kotlin
private var listener: OnNameSentListener? = null
```

### Attach

```kotlin
override fun onAttach(context: Context) {
    super.onAttach(context)
    listener = context as? OnNameSentListener
}
```

### Send

```kotlin
listener?.onNameSent("Rahim")
```

### Detach

```kotlin
override fun onDetach() {
    listener = null
    super.onDetach()
}
```

### Activity receives

```kotlin
override fun onNameSent(name: String) {
    // use name
}
```

---

## Fragment Result API

### Create result

```kotlin
val result = Bundle()
result.putString("name", "Rahim")
```

### Send

```kotlin
parentFragmentManager.setFragmentResult(
    "profile_result",
    result
)
```

### Listen

```kotlin
supportFragmentManager.setFragmentResultListener(
    "profile_result",
    this
) { _, bundle ->

    val name = bundle.getString("name")
}
```

---

# 21. Mental Model

### Interface

```text
Fragment
   │
   │ "Rahim"
   ▼
listener
   │
   ▼
Activity
```

### Fragment Result API

```text
Fragment
   │
   │ "Rahim"
   ▼
Bundle
   │
   ▼
FragmentManager
   │
   ▼
Activity Listener
```

---

# ⭐ Final Summary

For **Fragment → Activity** communication:

### Method 1 — Interface Callback

```kotlin
listener?.onNameSent("Rahim")
```

The Fragment directly calls a function defined by an interface implemented by the Activity.

### Method 2 — Fragment Result API

```kotlin
parentFragmentManager.setFragmentResult(
    "profile_result",
    result
)
```

The Fragment sends a Bundle through the FragmentManager, and the Activity receives it with:

```kotlin
supportFragmentManager.setFragmentResultListener(
    "profile_result",
    this
) { _, bundle ->
    // receive data
}
```

**Key idea:**

> Interface Callback = Fragment calls the Activity's callback function.

> Fragment Result API = Fragment sends a result through the FragmentManager, and the Activity listens for that result.
