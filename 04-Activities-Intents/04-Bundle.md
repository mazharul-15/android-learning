# Bundle — Complete Android Notes

## What is a Bundle?

`Bundle` is an Android key-value container used to store and transfer small amounts of data.

```text
Bundle
┌──────────────────────────────┐
│ "name"      → "Abir"         │
│ "age"       → 22             │
│ "isStudent" → true           │
│ "studentId" → 1502055        │
└──────────────────────────────┘
```

The **key** identifies the data and the **value** is the actual data.

---

## 1. Basic Key-Value Concept

```kotlin
val bundle = Bundle()

bundle.putString("name", "Abir")

val name = bundle.getString("name")
```

Here:

```text
"name" → key
"Abir" → value
```

The key used to retrieve data must match the key used to store it.

---

## 2. Common Bundle Data Types

### String

```kotlin
bundle.putString("name", "Abir")
val name = bundle.getString("name")
```

### Int

```kotlin
bundle.putInt("age", 22)
val age = bundle.getInt("age")
```

### Boolean

```kotlin
bundle.putBoolean("isStudent", true)
val isStudent = bundle.getBoolean("isStudent")
```

### Double

```kotlin
bundle.putDouble("cgpa", 3.75)
val cgpa = bundle.getDouble("cgpa")
```

### Float

```kotlin
bundle.putFloat("height", 5.8f)
val height = bundle.getFloat("height")
```

### Long

```kotlin
bundle.putLong("studentId", 1502055L)
val studentId = bundle.getLong("studentId")
```

Other commonly used methods include:

```kotlin
putStringArray()
getStringArray()

putStringArrayList()
getStringArrayList()
```

---

## 3. Bundle with Intent

One of the most common uses of Bundle is passing data between Activities.

```text
MainActivity
     │
     │ Intent + data
     ↓
StudentActivity
```

Example:

```kotlin
val bundle = Bundle()

bundle.putString("name", "Abir")
bundle.putInt("age", 22)
bundle.putBoolean("isStudent", true)

val intent = Intent(this, StudentActivity::class.java)

intent.putExtras(bundle)

startActivity(intent)
```

Receiver:

```kotlin
val bundle = intent.extras

val name = bundle?.getString("name")
val age = bundle?.getInt("age")
val isStudent = bundle?.getBoolean("isStudent")
```

The flow is:

```text
Bundle
   ↓
Intent
   ↓
startActivity()
   ↓
Receiving Activity
   ↓
intent.extras
```

---

## 4. `putExtra()` vs `putExtras()`

### `putExtra()`

Used to put individual values into an Intent:

```kotlin
intent.putExtra("name", "Abir")
intent.putExtra("age", 22)
intent.putExtra("isStudent", true)
```

### `putExtras()`

Used to put a Bundle into an Intent:

```kotlin
val bundle = Bundle()

bundle.putString("name", "Abir")
bundle.putInt("age", 22)

intent.putExtras(bundle)
```

Remember:

```text
putExtra()
→ individual extra/value

putExtras()
→ a Bundle containing extras
```

---

## 5. Bundle Is Not Only for Intents

Common Android uses of Bundle include:

```text
1. Passing data through Intent
2. Saving Activity state
3. Passing Fragment arguments
4. Restoring UI state
```

---

## 6. Bundle and Activity State

You often see:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
}
```

Here:

```kotlin
savedInstanceState
```

is a `Bundle`.

Android can provide this Bundle when an Activity needs to restore previously saved state.

For example, during screen rotation:

```text
Portrait
   ↓
Screen rotation
   ↓
Activity may be recreated
   ↓
Old Activity destroyed
   ↓
New Activity created
```

A Bundle can store small pieces of state that need to be restored.

---

## 7. Saving State with `onSaveInstanceState()`

Suppose you have:

```kotlin
var name = "Abir"
```

Save it:

```kotlin
override fun onSaveInstanceState(outState: Bundle) {

    outState.putString("name", name)

    super.onSaveInstanceState(outState)
}
```

Restore it:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val name = savedInstanceState?.getString("name")
}
```

Flow:

```text
onSaveInstanceState()
        ↓
Bundle
        ↓
Activity recreated
        ↓
onCreate(savedInstanceState)
        ↓
getString()
```

---

## 8. Example: Saving EditText Data

With View Binding:

```kotlin
override fun onSaveInstanceState(outState: Bundle) {

    outState.putString(
        "name",
        binding.etName.text.toString()
    )

    super.onSaveInstanceState(outState)
}
```

Restore:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val savedName = savedInstanceState?.getString("name")

    if (savedName != null) {
        binding.etName.setText(savedName)
    }
}
```

Note: standard Android views such as `EditText` can normally save and restore their own view state. You need `Bundle` yourself mainly when you have custom state that Android does not automatically restore.

---

## 9. Bundle with Fragment Arguments

Bundle is commonly used to pass initial arguments to a Fragment.

```kotlin
val bundle = Bundle().apply {
    putString("studentName", "Abir")
    putInt("studentId", 1502055)
}

val fragment = StudentFragment()

fragment.arguments = bundle
```

Inside the Fragment:

```kotlin
val name = arguments?.getString("studentName")
val id = arguments?.getInt("studentId")
```

Flow:

```text
Activity
   ↓
Bundle
   ↓
Fragment arguments
   ↓
Fragment
```

---

## 10. Bundle vs Intent

They are related but not the same.

### Intent

An Intent represents an action or communication request.

```kotlin
val intent = Intent(this, StudentActivity::class.java)
```

It can mean:

```text
"I want to open StudentActivity."
```

### Bundle

A Bundle stores key-value data:

```kotlin
val bundle = Bundle()

bundle.putString("name", "Abir")
```

Simple mental model:

```text
Intent = action/message
Bundle = data container
```

An Intent can carry extras/data.

---

## 11. Intent + Bundle Mental Model

Think of an Intent as an envelope:

```text
┌──────────────────────────────┐
│           INTENT             │
│                              │
│ Destination: StudentActivity │
│                              │
│ Extras:                      │
│   "name" → "Abir"            │
│   "age"  → 22                │
└──────────────────────────────┘
```

The extras can be thought of as key-value data carried with the Intent.

---

## 12. Safe Retrieval

Because a Bundle may be null:

```kotlin
val name = bundle?.getString("name")
```

You can provide defaults for primitive getters:

```kotlin
val age = bundle?.getInt("age", 0)

val isStudent = bundle?.getBoolean("isStudent", false)
```

For Intent extras:

```kotlin
val name = intent.getStringExtra("name")
```

---

## 13. Key Naming

Keys are Strings:

```kotlin
"name"
"age"
"isStudent"
"studentId"
```

For professional code, avoid repeating raw strings throughout the project.

Use constants:

```kotlin
companion object {

    const val EXTRA_NAME = "extra_name"
    const val EXTRA_AGE = "extra_age"
    const val EXTRA_STUDENT_ID = "extra_student_id"
}
```

Then:

```kotlin
intent.putExtra(EXTRA_NAME, "Abir")
```

Receiver:

```kotlin
val name = intent.getStringExtra(EXTRA_NAME)
```

This reduces key-typing mistakes.

---

## 14. Boolean Keys

A Boolean key can have any meaningful String name.

Examples:

```kotlin
"isStudent"
"hasPermission"
"isLoggedIn"
"hasCompletedProfile"
```

There is no special rule requiring `is` or `has`.

For example:

```kotlin
bundle.putBoolean("hasPermission", true)
```

The important thing is that the key matches when retrieving the data.

---

## 15. Do Not Use Bundle for Large Data

Bundle is intended for relatively small amounts of data such as:

```text
Small Activity state
Small Fragment arguments
Small Intent extras
```

Do not use it as a database or large-data storage mechanism.

Avoid putting:

```text
Large images
Huge lists
Large JSON responses
Large files
```

Use appropriate mechanisms instead:

```text
Room
DataStore
Files
Repository
Network/API
ViewModel
```

---

## 16. Student Information Example

Sender:

```kotlin
val bundle = Bundle().apply {
    putString("name", "Abir Rahman")
    putString("department", "Computer Science")
    putInt("studentId", 1502055)
    putBoolean("isActive", true)
}

val intent = Intent(this, StudentInfo::class.java)

intent.putExtras(bundle)

startActivity(intent)
```

Receiver:

```kotlin
val bundle = intent.extras

val name = bundle?.getString("name")
val department = bundle?.getString("department")
val studentId = bundle?.getInt("studentId")
val isActive = bundle?.getBoolean("isActive")
```

With View Binding:

```kotlin
binding.tvStudentName.text = name
binding.tvDepartmentName.text = department
binding.tvIdNo.text = studentId?.toString()
```

---

## 17. Bundle Flow in Android

### Passing data to another Activity

```text
MainActivity
     │
     │ create Bundle
     ↓
Bundle
     │
     │ putExtras()
     ↓
Intent
     │
     │ startActivity()
     ↓
StudentInfo
     │
     │ intent.extras
     ↓
Bundle
     │
     │ getString(), getInt(), ...
     ↓
UI
```

### Saving Activity state

```text
Activity
   │
   │ onSaveInstanceState()
   ↓
Bundle
   │
   │ Activity recreated
   ↓
onCreate(savedInstanceState)
   │
   ↓
Restore data
```

---

## 18. Common Mistakes

### Mistake 1: Different Keys

Wrong:

```kotlin
bundle.putString("name", "Abir")

bundle.getString("studentName")
```

Correct:

```kotlin
bundle.putString("name", "Abir")

bundle.getString("name")
```

### Mistake 2: Wrong Data Type

Store:

```kotlin
bundle.putInt("age", 22)
```

Retrieve as Int:

```kotlin
bundle.getInt("age")
```

Do not retrieve it as a String.

### Mistake 3: Forgetting Nullability

Potentially unsafe:

```kotlin
val name = savedInstanceState.getString("name")
```

Safer:

```kotlin
val name = savedInstanceState?.getString("name")
```

### Mistake 4: Using Bundle for Large Data

Do not use Bundle as a replacement for a:

```text
Database
File storage
Repository
Network storage
```

---

## 19. Bundle vs Other Android Components

| Tool | Main purpose |
|---|---|
| Bundle | Small temporary data, state, and arguments |
| Intent extras | Passing small data with an Intent |
| DataStore | Persistent preferences/settings |
| Room | Structured persistent local database |
| Files | File-based storage |
| ViewModel | UI-related state across configuration changes |

Examples:

```text
Open StudentActivity
        ↓
Intent + Bundle

Save theme preference
        ↓
DataStore

Save thousands of students
        ↓
Room

Save an image file
        ↓
File storage
```

---

## 20. Interview Questions

### What is a Bundle?

A Bundle is an Android key-value container used to store and transfer small amounts of data. It is commonly used with Intent extras, Fragment arguments, and saving/restoring Activity state.

### Why is `savedInstanceState` a Bundle?

Because Android can store small pieces of Activity state as key-value pairs in a Bundle and provide that state when the Activity is recreated.

### What is the difference between Intent and Bundle?

An Intent represents an action or communication request, while a Bundle is a key-value container for small amounts of data. An Intent can carry extras/data.

---

## 21. Quick Cheat Sheet

```kotlin
// Create
val bundle = Bundle()

// String
bundle.putString("name", "Abir")
val name = bundle.getString("name")

// Int
bundle.putInt("age", 22)
val age = bundle.getInt("age")

// Boolean
bundle.putBoolean("isStudent", true)
val isStudent = bundle.getBoolean("isStudent")

// Double
bundle.putDouble("cgpa", 3.75)
val cgpa = bundle.getDouble("cgpa")

// Put Bundle into Intent
intent.putExtras(bundle)

// Get Bundle from Intent
val bundle = intent.extras

// Save Activity state
override fun onSaveInstanceState(outState: Bundle) {
    outState.putString("name", "Abir")
    super.onSaveInstanceState(outState)
}

// Restore Activity state
val name = savedInstanceState?.getString("name")
```

---

## Final Mental Model

Remember these three concepts:

```text
INTENT
"What action/destination?"

BUNDLE
"What small data should I carry?"

SAVED INSTANCE STATE
"What small UI/state data should I restore after recreation?"
```

Example:

```text
MainActivity
     │
     │ Intent
     │
     ├── destination → StudentInfo
     │
     └── extras
          │
          └── Bundle
               ├── "name" → "Abir"
               ├── "studentId" → 1502055
               └── "isActive" → true
```

**Bundle = a key-value container for small amounts of Android data.**
