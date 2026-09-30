# Fragment Result API — Sending Data

## Overview

The Fragment Result API is a modern way to send small amounts of data between Fragments using `FragmentManager`.

It supports:

```text
Fragment → Fragment
Fragment → Activity
```

Basic flow:

```text
Sender
   ↓
setFragmentResult()
   ↓
FragmentManager
   ↓
setFragmentResultListener()
   ↓
Receiver
```

The data is normally carried in a `Bundle`.

---

## 1. Important Methods

### Sender

```kotlin
setFragmentResult()
```

Meaning:

> "I am sending a result."

### Receiver

```kotlin
setFragmentResultListener()
```

Meaning:

> "I am waiting for this result."

---

# 2. Fragment → Fragment

Suppose:

```text
HomeFragment
SelectNameFragment
```

The user selects a name in `SelectNameFragment` and sends it to `HomeFragment`.

```text
SelectNameFragment
        ↓
      "Abir"
        ↓
HomeFragment
```

---

## 3. Sender Fragment

```kotlin
class SelectNameFragment : Fragment(R.layout.fragment_select_name) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnSendName)
            .setOnClickListener {

                val bundle = Bundle().apply {
                    putString("name", "Abir Rahman")
                }

                parentFragmentManager.setFragmentResult(
                    "nameRequest",
                    bundle
                )
            }
    }
}
```

Important:

```kotlin
parentFragmentManager.setFragmentResult(
    "nameRequest",
    bundle
)
```

means:

> Send this Bundle as a result using the FragmentManager.

---

## 4. Receiver Fragment

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            "nameRequest",
            viewLifecycleOwner
        ) { _, bundle ->

            val name = bundle.getString("name")

            view.findViewById<TextView>(R.id.tvName)
                .text = name
        }
    }
}
```

The receiver listens for the same request key:

```kotlin
"nameRequest"
```

---

# 5. Request Key

This:

```kotlin
"nameRequest"
```

connects the sender and receiver.

Sender:

```kotlin
setFragmentResult(
    "nameRequest",
    bundle
)
```

Receiver:

```kotlin
setFragmentResultListener(
    "nameRequest",
    viewLifecycleOwner
)
```

They must match.

Wrong:

```kotlin
// Sender
setFragmentResult("nameRequest", bundle)

// Receiver
setFragmentResultListener("studentRequest", ...)
```

---

# 6. Bundle

A `Bundle` is a key-value container.

Example:

```kotlin
val bundle = Bundle().apply {
    putString("name", "Abir")
    putInt("studentId", 1502055)
}
```

Think:

```text
Bundle
 ├── "name"      → "Abir"
 └── "studentId" → 1502055
```

---

# 7. Receiving Bundle Data

String:

```kotlin
val name = bundle.getString("name")
```

Int:

```kotlin
val studentId = bundle.getInt("studentId")
```

Boolean:

```kotlin
val isStudent = bundle.getBoolean("isStudent")
```

Double:

```kotlin
val cgpa = bundle.getDouble("cgpa")
```

---

# 8. Sending Multiple Values

Sender:

```kotlin
val bundle = Bundle().apply {
    putString("name", "Abir Rahman")
    putString("department", "CSE")
    putInt("studentId", 1502055)
}

parentFragmentManager.setFragmentResult(
    "studentRequest",
    bundle
)
```

Receiver:

```kotlin
parentFragmentManager.setFragmentResultListener(
    "studentRequest",
    viewLifecycleOwner
) { _, bundle ->

    val name = bundle.getString("name")
    val department = bundle.getString("department")
    val studentId = bundle.getInt("studentId")
}
```

---

# 9. Use Constants for Keys

Instead of repeatedly writing strings:

```kotlin
"nameRequest"
"name"
```

use constants.

Example:

```kotlin
companion object {

    const val REQUEST_NAME = "request_name"
    const val KEY_NAME = "key_name"
}
```

Sender:

```kotlin
val bundle = Bundle().apply {
    putString(KEY_NAME, "Abir Rahman")
}

parentFragmentManager.setFragmentResult(
    REQUEST_NAME,
    bundle
)
```

Receiver:

```kotlin
parentFragmentManager.setFragmentResultListener(
    REQUEST_NAME,
    viewLifecycleOwner
) { _, bundle ->

    val name = bundle.getString(KEY_NAME)
}
```

This reduces spelling mistakes.

---

# 10. Why `viewLifecycleOwner`?

You commonly see:

```kotlin
setFragmentResultListener(
    "nameRequest",
    viewLifecycleOwner
) { _, bundle ->
}
```

A Fragment has its own lifecycle, while its View has a separate lifecycle.

The Fragment's View can be destroyed while the Fragment object remains.

Because the listener usually updates UI, using:

```kotlin
viewLifecycleOwner
```

ties the listener to the Fragment's View lifecycle.

This is especially useful when using View Binding.

---

# 11. Fragment → Activity

The Fragment Result API can also send data from a Fragment to its hosting Activity.

Flow:

```text
Fragment
    ↓
setFragmentResult()
    ↓
FragmentManager
    ↓
Activity listener
```

---

## 12. Fragment Sender

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnSend)
            .setOnClickListener {

                val bundle = Bundle().apply {
                    putString("name", "Abir Rahman")
                }

                parentFragmentManager.setFragmentResult(
                    "nameRequest",
                    bundle
                )
            }
    }
}
```

---

## 13. Activity Receiver

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.setFragmentResultListener(
            "nameRequest",
            this
        ) { _, bundle ->

            val name = bundle.getString("name")

            // Use the name here
        }
    }
}
```

Notice:

### Fragment

```kotlin
parentFragmentManager
```

### Activity

```kotlin
supportFragmentManager
```

---

# 14. Fragment → Activity Flow

```text
┌─────────────────────┐
│    HomeFragment     │
│                     │
│  "Abir Rahman"      │
└──────────┬──────────┘
           │
           │ setFragmentResult()
           ↓
┌─────────────────────┐
│   FragmentManager   │
└──────────┬──────────┘
           │
           │ listener
           ↓
┌─────────────────────┐
│    MainActivity     │
│                     │
│ receives "Abir"     │
└─────────────────────┘
```

---

# 15. Fragment → Fragment vs Fragment → Activity

## Fragment → Fragment

Sender:

```kotlin
parentFragmentManager.setFragmentResult(...)
```

Receiver:

```kotlin
parentFragmentManager.setFragmentResultListener(...)
```

Example:

```text
SelectNameFragment
       ↓
    "Abir"
       ↓
HomeFragment
```

---

## Fragment → Activity

Sender:

```kotlin
parentFragmentManager.setFragmentResult(...)
```

Receiver:

```kotlin
supportFragmentManager.setFragmentResultListener(...)
```

Example:

```text
HomeFragment
       ↓
    "Abir"
       ↓
MainActivity
```

---

# 16. Real Student Information Example

Suppose:

```text
StudentFormFragment
```

contains:

```text
Name
Department
Student ID
```

After clicking Submit:

```text
StudentFormFragment
        ↓
       Bundle
        ↓
Fragment Result API
        ↓
StudentInfoFragment
```

Sender:

```kotlin
val bundle = Bundle().apply {
    putString("name", "Abir Rahman")
    putString("department", "CSE")
    putInt("studentId", 1502055)
}

parentFragmentManager.setFragmentResult(
    "studentRequest",
    bundle
)
```

Receiver:

```kotlin
parentFragmentManager.setFragmentResultListener(
    "studentRequest",
    viewLifecycleOwner
) { _, bundle ->

    val name = bundle.getString("name")
    val department = bundle.getString("department")
    val studentId = bundle.getInt("studentId")

    binding.tvName.text = name
    binding.tvDepartment.text = department
    binding.tvStudentId.text = studentId.toString()
}
```

---

# 17. Fragment Result API vs Interface

You previously learned the interface/callback approach:

```kotlin
interface OnSendNameListener {

    fun onSendName(name: String)
}
```

That approach requires a custom listener.

With Fragment Result API:

```text
Fragment
   ↓
FragmentManager
   ↓
Fragment / Activity
```

For simple Fragment communication, Fragment Result API is often easier.

---

# 18. Fragment Result API vs Intent

### Activity → Activity

Commonly:

```kotlin
Intent
```

Flow:

```text
Activity A
    ↓
Intent + extras
    ↓
Activity B
```

### Fragment → Fragment

Commonly:

```kotlin
Fragment Result API
```

Flow:

```text
Fragment A
    ↓
FragmentManager
    ↓
Fragment B
```

### Fragment → Activity

Can use:

```kotlin
Fragment Result API
```

Flow:

```text
Fragment
    ↓
FragmentManager
    ↓
Activity
```

---

# 19. Common Mistakes

## Mistake 1 — Different request keys

Wrong:

```kotlin
// Sender
setFragmentResult("nameRequest", bundle)

// Receiver
setFragmentResultListener("studentRequest", ...)
```

Use the same request key.

---

## Mistake 2 — Different Bundle keys

Wrong:

```kotlin
putString("studentName", "Abir")
```

then:

```kotlin
getString("name")
```

These are different keys.

---

## Mistake 3 — Wrong FragmentManager

For Fragment-to-Fragment communication in the same FragmentManager:

```kotlin
parentFragmentManager
```

is commonly used.

For an Activity receiving the Fragment result:

```kotlin
supportFragmentManager
```

is used in the Activity.

---

## Mistake 4 — Updating a destroyed View

Use:

```kotlin
viewLifecycleOwner
```

for the listener when the callback updates the Fragment's UI.

---

# 20. Mental Model

Remember this:

```text
SENDER
   ↓
setFragmentResult()
   ↓
FragmentManager
   ↓
setFragmentResultListener()
   ↓
RECEIVER
```

And:

```text
Bundle
↓
data container

Request Key
↓
connects sender and receiver

Bundle Key
↓
identifies the value
```

---

# 21. Cheat Sheet

## Fragment → Fragment

### Sender

```kotlin
val bundle = Bundle().apply {
    putString("name", "Abir")
}

parentFragmentManager.setFragmentResult(
    "nameRequest",
    bundle
)
```

### Receiver

```kotlin
parentFragmentManager.setFragmentResultListener(
    "nameRequest",
    viewLifecycleOwner
) { _, bundle ->

    val name = bundle.getString("name")
}
```

---

## Fragment → Activity

### Sender

```kotlin
val bundle = Bundle().apply {
    putString("name", "Abir")
}

parentFragmentManager.setFragmentResult(
    "nameRequest",
    bundle
)
```

### Activity Receiver

```kotlin
supportFragmentManager.setFragmentResultListener(
    "nameRequest",
    this
) { _, bundle ->

    val name = bundle.getString("name")
}
```

---

# Final Visualization

```text
              FRAGMENT → FRAGMENT

┌──────────────┐
│   Fragment A │
│    Sender    │
└──────┬───────┘
       │
       │ setFragmentResult()
       ↓
┌──────────────────┐
│ FragmentManager  │
└────────┬─────────┘
         │
         │ Listener
         ↓
┌──────────────┐
│   Fragment B │
│   Receiver   │
└──────────────┘


              FRAGMENT → ACTIVITY

┌──────────────┐
│   Fragment   │
│    Sender    │
└──────┬───────┘
       │
       │ setFragmentResult()
       ↓
┌──────────────────┐
│ FragmentManager  │
└────────┬─────────┘
         │
         │ Listener
         ↓
┌──────────────┐
│   Activity   │
│   Receiver   │
└──────────────┘
```

## Key Idea

**Fragment Result API = a communication mechanism for sending a Bundle from one Fragment to another Fragment or to the hosting Activity.**

Official Android documentation:

https://developer.android.com/guide/fragments/communicate
