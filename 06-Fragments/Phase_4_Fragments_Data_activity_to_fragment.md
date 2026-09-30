# Activity → Fragment Data Passing

## Goal

Activity → Fragment means the Activity sends data to a Fragment.

```text
MainActivity
     │
     │ data
     ▼
ProfileFragment
```

A common approach for initial Fragment data is:

**Factory Method (`newInstance()`) + `Bundle` arguments**

---

## 1. Example Goal

Send these values from `MainActivity` to `ProfileFragment`:

- Name = `"Rahim"`
- Student ID = `101`
- CGPA = `3.75`

---

## 2. Fragment XML — `fragment_profile.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:id="@+id/nameTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="22sp" />

    <TextView
        android:id="@+id/studentIdTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/cgpaTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:textSize="18sp" />

</LinearLayout>
```

---

## 3. `ProfileFragment.kt`

```kotlin
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val studentId = arguments?.getInt("studentId")
        val cgpa = arguments?.getDouble("cgpa")

        val nameTextView =
            view.findViewById<TextView>(R.id.nameTextView)

        val studentIdTextView =
            view.findViewById<TextView>(R.id.studentIdTextView)

        val cgpaTextView =
            view.findViewById<TextView>(R.id.cgpaTextView)

        nameTextView.text = "Name: $name"
        studentIdTextView.text = "Student ID: $studentId"
        cgpaTextView.text = "CGPA: $cgpa"
    }

    companion object {

        fun newInstance(
            name: String,
            studentId: Int,
            cgpa: Double
        ): ProfileFragment {

            val fragment = ProfileFragment()

            val bundle = Bundle()

            bundle.putString("name", name)
            bundle.putInt("studentId", studentId)
            bundle.putDouble("cgpa", cgpa)

            fragment.arguments = bundle

            return fragment
        }
    }
}
```

---

## 4. What is `newInstance()`?

`newInstance()` is a **factory method**.

Its job is to:

1. Create the Fragment.
2. Create a Bundle.
3. Put data into the Bundle.
4. Attach the Bundle to the Fragment.
5. Return the ready-to-use Fragment.

```text
newInstance()
     │
     ├── Create Fragment
     ├── Create Bundle
     ├── Put data
     ├── Attach Bundle
     └── Return Fragment
```

So:

```kotlin
ProfileFragment.newInstance("Rahim", 101, 3.75)
```

means:

> Create a ProfileFragment prepared with Rahim's data.

---

## 5. Why `companion object`?

We want to call:

```kotlin
ProfileFragment.newInstance(...)
```

without first creating a `ProfileFragment`.

Therefore `newInstance()` is commonly placed inside:

```kotlin
companion object {
    fun newInstance(...) {
        ...
    }
}
```

For now, remember:

> `companion object` lets us call the function using the class name.

---

## 6. `MainActivity.kt` — Sending Data

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val fragment =
            ProfileFragment.newInstance(
                "Rahim",
                101,
                3.75
            )

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                fragment
            )
            .commit()
    }
}
```

The important part is:

```kotlin
val fragment =
    ProfileFragment.newInstance(
        "Rahim",
        101,
        3.75
    )
```

The factory creates a Fragment containing the arguments.

Then:

```kotlin
.replace(
    R.id.fragmentContainer,
    fragment
)
```

displays that Fragment.

---

## 7. Complete Data Flow

```text
MainActivity
     │
     │ ProfileFragment.newInstance(
     │     "Rahim", 101, 3.75
     │ )
     ▼
Factory Method
     │
     ├── Create ProfileFragment
     ├── Create Bundle
     ├── name = Rahim
     ├── studentId = 101
     └── cgpa = 3.75
              │
              ▼
      fragment.arguments
              │
              ▼
       ProfileFragment
              │
              ▼
       Read arguments
```

---

## 8. What is a `Bundle`?

A `Bundle` is a container for key-value data.

```kotlin
val bundle = Bundle()
```

Put data:

```kotlin
bundle.putString("name", "Rahim")
bundle.putInt("studentId", 101)
bundle.putDouble("cgpa", 3.75)
```

Conceptually:

```text
Bundle
┌─────────────────────────┐
│ "name"      → "Rahim"   │
│ "studentId" → 101       │
│ "cgpa"      → 3.75      │
└─────────────────────────┘
```

Read data:

```kotlin
arguments?.getString("name")
arguments?.getInt("studentId")
arguments?.getDouble("cgpa")
```

---

## 9. Keys Must Match

When sending:

```kotlin
bundle.putString("name", "Rahim")
```

receive using:

```kotlin
arguments?.getString("name")
```

Correct:

```text
putString("name") → getString("name")
```

Wrong:

```kotlin
bundle.putString("name", "Rahim")

arguments?.getString("studentName")
```

because `"name"` and `"studentName"` are different keys.

---

## 10. Why Use Fragment Arguments?

You might be tempted to do:

```kotlin
class ProfileFragment : Fragment() {
    var name = ""
}
```

and:

```kotlin
val fragment = ProfileFragment()
fragment.name = "Rahim"
```

For initial Fragment data, prefer:

```kotlin
ProfileFragment.newInstance("Rahim")
```

with Fragment arguments.

Arguments are designed for the Fragment's initial data and can be retained when Android recreates the Fragment.

---

## 11. Why Use a Factory Method?

Without a factory method:

```kotlin
val fragment = ProfileFragment()

val bundle = Bundle()

bundle.putString("name", "Rahim")
bundle.putInt("studentId", 101)
bundle.putDouble("cgpa", 3.75)

fragment.arguments = bundle
```

With a factory method:

```kotlin
val fragment =
    ProfileFragment.newInstance(
        "Rahim",
        101,
        3.75
    )
```

The second version keeps the Bundle preparation inside the Fragment.

---

## 12. Important Separation

There are two different jobs.

### `newInstance()`

**Creates and prepares the Fragment**

```kotlin
ProfileFragment.newInstance(...)
```

### `FragmentTransaction`

**Displays the Fragment**

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(R.id.fragmentContainer, fragment)
    .commit()
```

Remember:

```text
newInstance()
     ↓
CREATE + PREPARE

FragmentTransaction
     ↓
DISPLAY
```

---

## 13. Simple One-Value Example

For only a name:

```kotlin
companion object {

    fun newInstance(name: String): ProfileFragment {

        val fragment = ProfileFragment()

        val bundle = Bundle()
        bundle.putString("name", name)

        fragment.arguments = bundle

        return fragment
    }
}
```

Activity:

```kotlin
val fragment =
    ProfileFragment.newInstance("Rahim")
```

Fragment:

```kotlin
val name =
    arguments?.getString("name")
```

---

## 14. Common Bundle Types

### String

```kotlin
bundle.putString("name", "Rahim")
```

```kotlin
arguments?.getString("name")
```

### Int

```kotlin
bundle.putInt("age", 25)
```

```kotlin
arguments?.getInt("age")
```

### Boolean

```kotlin
bundle.putBoolean("isStudent", true)
```

```kotlin
arguments?.getBoolean("isStudent")
```

### Double

```kotlin
bundle.putDouble("cgpa", 3.75)
```

```kotlin
arguments?.getDouble("cgpa")
```

For custom objects, Android commonly uses `Parcelable`.

---

## 15. Mental Model

Think of the Bundle as a small box:

```text
Activity
   │
   │ puts data into box
   ▼
┌──────────────┐
│   Bundle     │
│              │
│ name = Rahim │
│ id = 101     │
└──────┬───────┘
       │
       ▼
   Fragment
       │
       │ opens box
       ▼
   reads data
```

---

## 16. Activity → Fragment vs Fragment → Activity

### Activity → Fragment

Use:

```kotlin
newInstance()
```

with:

```kotlin
Bundle arguments
```

```text
Activity
   │
   │ initial data
   ▼
Fragment
```

### Fragment → Activity

One modern option is:

```kotlin
setFragmentResult()
```

with:

```kotlin
setFragmentResultListener()
```

```text
Fragment
   │
   │ result/data
   ▼
Activity
```

---

## 17. Quick Cheat Sheet

### Create Fragment

```kotlin
val fragment = ProfileFragment()
```

### Create Bundle

```kotlin
val bundle = Bundle()
```

### Put data

```kotlin
bundle.putString("name", "Rahim")
```

### Attach Bundle

```kotlin
fragment.arguments = bundle
```

### Factory method

```kotlin
ProfileFragment.newInstance("Rahim")
```

### Read data

```kotlin
val name = arguments?.getString("name")
```

### Display Fragment

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(R.id.fragmentContainer, fragment)
    .commit()
```

---

## ⭐ One-Sentence Summary

> **For Activity → Fragment communication, use a Fragment factory method (`newInstance()`) to create the Fragment and put initial data into its `arguments` Bundle, then use a FragmentTransaction to display it.**
