# Android Fragments — Phase 1: Fundamentals

## Phase 1
- [x] What is Fragment
- [x] Fragment Lifecycle

---

# 1. What is a Fragment?

## Simple Definition

A **Fragment** is a reusable portion of an Android application's UI and behavior that is hosted inside an **Activity**.

> **One sentence to remember:** A Fragment is a reusable part of a screen that lives inside an Activity.

Android's Fragment library is part of AndroidX (`androidx.fragment.app.Fragment`). Fragments help divide an Activity's UI into independent sections/screens and manage those sections separately.

## Fragment is NOT a Small Activity

A Fragment and an Activity are different:

| Activity | Fragment |
|---|---|
| A complete app screen/container | A reusable part of a screen |
| Can exist independently as an app entry point | Normally hosted by an Activity |
| Has its own Activity lifecycle | Has its own Fragment lifecycle |
| Managed by the Android system | Managed by FragmentManager |

### Mental Model

Think of an Activity as a **house** and Fragments as **rooms**.

```text
Activity = House
│
├── HomeFragment
├── NewsFragment
└── ProfileFragment
```

The Activity provides the overall container, while Fragments provide individual UI sections.

---

# 2. Why Do We Use Fragments?

Fragments are useful when an app has multiple screens or sections that share the same Activity.

For example:

```text
MainActivity
│
├── HomeFragment
├── NewsFragment
├── ProfileFragment
└── SettingsFragment
```

Instead of putting all UI and logic into one huge Activity, we can divide it into smaller components.

### Main Benefits

1. **Reusable UI**
   - The same Fragment can be used in different places.

2. **Better organization**
   - UI and logic can be divided into smaller pieces.

3. **Multiple sections in one Activity**
   - One Activity can host multiple Fragments.

4. **Flexible navigation**
   - Fragments can be added, replaced, or removed.

5. **Useful for adaptive layouts**
   - Different Fragment arrangements can be used for different screen sizes.

---

# 3. Activity and Fragment Relationship

A Fragment normally needs an Activity to host it.

```text
                Activity
                   │
        ┌──────────┼──────────┐
        ↓          ↓          ↓
     Home       News       Profile
    Fragment   Fragment    Fragment
```

The Activity is the host.

The Fragment provides a part of the UI and behavior.

AndroidX's `FragmentActivity` and its subclass `AppCompatActivity` support the AndroidX Fragment system.

---

# 4. Basic Fragment Structure

A typical Fragment has two important parts:

```text
HomeFragment.kt
      +
fragment_home.xml
```

### Kotlin class

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

}
```

### XML layout

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home Screen" />

</LinearLayout>
```

The Kotlin class represents the Fragment's behavior.

The XML file represents the Fragment's UI.

---

# 5. Fragment Naming Convention

Use clear names.

### Fragment class

```text
HomeFragment
NewsFragment
ProfileFragment
SettingsFragment
```

### Fragment layout

```text
fragment_home.xml
fragment_news.xml
fragment_profile.xml
fragment_settings.xml
```

### General pattern

```text
Class:   [Name]Fragment
Layout:  fragment_[name].xml
```

---

# 6. Fragment Lifecycle

A Fragment has its own lifecycle.

The lifecycle describes how a Fragment is:

```text
created
   ↓
attached
   ↓
given a View
   ↓
started
   ↓
resumed
   ↓
running
   ↓
paused
   ↓
stopped
   ↓
View destroyed
   ↓
Fragment destroyed
   ↓
detached
```

The main lifecycle callbacks are:

```text
onAttach()
    ↓
onCreate()
    ↓
onCreateView()
    ↓
onViewCreated()
    ↓
onStart()
    ↓
onResume()
    ↓
    RUNNING
    ↓
onPause()
    ↓
onStop()
    ↓
onDestroyView()
    ↓
onDestroy()
    ↓
onDetach()
```

---

# 7. Fragment Lifecycle Callbacks

## 7.1 onAttach()

```kotlin
override fun onAttach(context: Context) {
    super.onAttach(context)
}
```

### What happens?

The Fragment becomes attached to its host Activity.

### Simple meaning

> Fragment gets connected to the Activity.

---

## 7.2 onCreate()

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
}
```

### What happens?

The Fragment itself is being created.

Use this for initialization that does not require accessing the Fragment's Views.

### Example

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize non-UI data
}
```

### Remember

> `onCreate()` = Fragment is being created.

---

# 8. onCreateView()

```kotlin
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    return inflater.inflate(
        R.layout.fragment_home,
        container,
        false
    )
}
```

### What happens?

The Fragment's UI/View hierarchy is created.

For example:

```text
fragment_home.xml
       ↓
   inflate()
       ↓
 Fragment View
```

### Remember

> `onCreateView()` = Create the Fragment's View.

---

# 9. onViewCreated()

```kotlin
override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
) {
    super.onViewCreated(view, savedInstanceState)
}
```

### What happens?

The Fragment's View has already been created.

This is a common place to:

- Find/access Views
- Set click listeners
- Set adapters
- Initialize UI
- Observe UI-related data

Example:

```kotlin
override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
) {
    super.onViewCreated(view, savedInstanceState)

    val titleTextView = view.findViewById<TextView>(R.id.titleTextView)

    titleTextView.text = "Welcome"
}
```

### Remember

> `onCreateView()` creates the View; `onViewCreated()` works with the View.

---

# 10. onStart()

```kotlin
override fun onStart() {
    super.onStart()
}
```

The Fragment becomes visible to the user.

### Remember

> `onStart()` = Fragment becomes visible.

---

# 11. onResume()

```kotlin
override fun onResume() {
    super.onResume()
}
```

The Fragment is now in the foreground and ready for user interaction.

### Remember

> `onResume()` = Fragment is active and ready for interaction.

---

# 12. RUNNING State

After `onResume()`:

```text
Fragment
   ↓
RUNNING
```

The user can normally interact with the Fragment's UI.

Example:

```text
User
 ↓
Button click
 ↓
Fragment handles click
```

---

# 13. onPause()

```kotlin
override fun onPause() {
    super.onPause()
}
```

The Fragment is losing the foreground position.

It may still be visible, depending on the situation, but it is no longer the active foreground component.

### Remember

> `onPause()` = Fragment is losing active interaction.

---

# 14. onStop()

```kotlin
override fun onStop() {
    super.onStop()
}
```

The Fragment is no longer visible to the user.

### Remember

> `onStop()` = Fragment is no longer visible.

---

# 15. onDestroyView()

```kotlin
override fun onDestroyView() {
    super.onDestroyView()
}
```

The Fragment's **View is destroyed**.

This is one of the most important concepts in Fragment development.

The Fragment object itself may still exist.

```text
Fragment
   │
   ├── Fragment object → may still exist
   │
   └── View → destroyed
```

Later, the same Fragment can receive a new View.

### Remember

> `onDestroyView()` = Destroy the Fragment's UI/View, not necessarily the Fragment itself.

This distinction becomes especially important when using **ViewBinding**.

---

# 16. onDestroy()

```kotlin
override fun onDestroy() {
    super.onDestroy()
}
```

The Fragment itself is being destroyed.

### Remember

> `onDestroy()` = Fragment object is being destroyed.

---

# 17. onDetach()

```kotlin
override fun onDetach() {
    super.onDetach()
}
```

The Fragment is no longer attached to its Activity/host.

### Remember

> `onDetach()` = Fragment is disconnected from its host.

---

# 18. Complete Lifecycle Diagram

```text
                 Fragment Created
                       │
                       ↓
                  onAttach()
                       │
                       ↓
                   onCreate()
                       │
                       ↓
                onCreateView()
                       │
                       ↓
                onViewCreated()
                       │
                       ↓
                    onStart()
                       │
                       ↓
                   onResume()
                       │
                       ↓
                    RUNNING
                       │
                       ↓
                   onPause()
                       │
                       ↓
                    onStop()
                       │
                       ↓
               onDestroyView()
                       │
                       ↓
                  onDestroy()
                       │
                       ↓
                  onDetach()
                       │
                       ↓
                 Fragment Gone
```

---

# 19. The Most Important Concept: Fragment vs Fragment View

This is the concept you should understand before moving to ViewBinding.

A Fragment has:

```text
Fragment Object
       │
       └── Fragment View
```

But their lifetimes are not always identical.

### Fragment lifecycle

```text
onAttach()
    ↓
onCreate()
    ↓
       Fragment exists
    ↓
onDestroy()
    ↓
onDetach()
```

### Fragment View lifecycle

```text
onCreateView()
    ↓
onViewCreated()
    ↓
       View exists
    ↓
onDestroyView()
```

So:

```text
Fragment lifetime
┌─────────────────────────────────────────────┐
│ onAttach → onCreate → ... → onDestroy      │
│                                             │
│     View lifetime                           │
│     ┌───────────────────────────────┐       │
│     │ onCreateView → ... →          │       │
│     │ onDestroyView                 │       │
│     └───────────────────────────────┘       │
└─────────────────────────────────────────────┘
```

### One sentence to remember

> **A Fragment can survive while its View is destroyed and recreated.**

This is why Fragment View lifecycle matters.

---

# 20. Practical Lifecycle Example

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("HomeFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HomeFragment", "onCreate")
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeFragment", "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("HomeFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HomeFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HomeFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("HomeFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("HomeFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("HomeFragment", "onDetach")
    }
}
```

You can observe these messages in **Logcat** while navigating between screens or changing the Fragment's state.

---

# 21. Common Lifecycle Flow

When a Fragment is first displayed:

```text
onAttach()
↓
onCreate()
↓
onCreateView()
↓
onViewCreated()
↓
onStart()
↓
onResume()
```

When it becomes inactive:

```text
onPause()
↓
onStop()
```

When its View is removed:

```text
onDestroyView()
```

When the Fragment itself is finally destroyed:

```text
onDestroy()
↓
onDetach()
```

---

# 22. Common Mistakes

## Mistake 1: Thinking Fragment = Activity

Wrong:

```text
Fragment = small Activity
```

Better:

```text
Activity = host/container
Fragment = reusable UI + behavior inside the host
```

---

## Mistake 2: Confusing onCreateView() and onViewCreated()

```text
onCreateView()
    ↓
Creates/returns the View

onViewCreated()
    ↓
Works with the already-created View
```

---

## Mistake 3: Thinking onDestroyView() destroys the Fragment

Wrong:

```text
onDestroyView()
    ↓
Fragment destroyed
```

Correct:

```text
onDestroyView()
    ↓
Only Fragment View is destroyed
    ↓
Fragment may still exist
```

---

## Mistake 4: Using Views after onDestroyView()

After:

```kotlin
onDestroyView()
```

the old View hierarchy should no longer be used.

This is especially important with ViewBinding.

---

# 23. Quick Comparison Table

| Callback | Simple Meaning |
|---|---|
| `onAttach()` | Connected to Activity/host |
| `onCreate()` | Fragment object created |
| `onCreateView()` | Fragment UI/View created |
| `onViewCreated()` | UI is ready to use |
| `onStart()` | Fragment becomes visible |
| `onResume()` | Fragment becomes active |
| `onPause()` | Losing active foreground |
| `onStop()` | No longer visible |
| `onDestroyView()` | Fragment View destroyed |
| `onDestroy()` | Fragment object destroyed |
| `onDetach()` | Disconnected from host |

---

# 24. Most Important Methods to Remember

You do NOT need to memorize every callback immediately.

For practical Android development, first understand these:

```text
onCreate()
onCreateView()
onViewCreated()
onStart()
onResume()
onPause()
onStop()
onDestroyView()
onDestroy()
```

Especially remember:

```text
onCreateView()
        ↓
Creates View

onViewCreated()
        ↓
Use/View setup

onDestroyView()
        ↓
View is destroyed
```

---

# 25. Phase 1 Final Mental Model

```text
                 ACTIVITY
                    │
                    │ hosts
                    ↓
               ┌─────────┐
               │FRAGMENT │
               └────┬────┘
                    │
                    │ creates
                    ↓
               ┌─────────┐
               │   VIEW  │
               │ XML UI  │
               └─────────┘
```

Lifecycle:

```text
Fragment
│
├── onAttach()
├── onCreate()
│
├── View
│   ├── onCreateView()
│   ├── onViewCreated()
│   ├── onStart()
│   ├── onResume()
│   ├── onPause()
│   ├── onStop()
│   └── onDestroyView()
│
├── onDestroy()
└── onDetach()
```

## Final One-Sentence Summary

> **A Fragment is a reusable part of an Activity's UI, and its lifecycle controls both the Fragment itself and the separate lifecycle of its View.**

---

# Phase 1 Checklist

- [x] What is Fragment
- [x] Fragment vs Activity
- [x] Why Fragments are used
- [x] Activity–Fragment relationship
- [x] Fragment class + XML layout
- [x] Fragment naming convention
- [x] Fragment lifecycle
- [x] Lifecycle callbacks
- [x] Fragment View lifecycle
- [x] Fragment vs View lifetime
- [x] Common lifecycle mistakes
- [x] Practical Logcat lifecycle example

## Next Phase

**Phase 2 — Managing Fragments**

1. Creating & Displaying Fragment
2. FragmentContainerView
3. FragmentTransaction
4. Add / Replace / Remove
5. Multiple Fragments
