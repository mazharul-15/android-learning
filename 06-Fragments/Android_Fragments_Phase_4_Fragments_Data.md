# Phase 4: Data Between Activity and Fragments — Kotlin Android Notes

## Overview

In Android development, Activities and Fragments often need to exchange data.

### Phase 4 Topics

1. Activity → Fragment
2. Fragment → Activity
3. Fragment → Fragment
4. Arguments / Bundle
5. Fragment Result API

The most important idea is:

> Use **Arguments / Bundle** mainly for passing data into a Fragment, and use the **Fragment Result API** for sending results/data between Fragments or from a Fragment to its host Activity.

---

# 1. Activity → Fragment

An Activity can pass data to a Fragment when creating or displaying it.

There are two common approaches:

- Fragment arguments
- Fragment factory / constructor-independent arguments

## 1.1 Using Bundle

### Activity

```kotlin
val fragment = DetailsFragment().apply {
    arguments = Bundle().apply {
        putString("user_name", "Mazharul")
        putInt("user_age", 33)
    }
}

supportFragmentManager.beginTransaction()
    .replace(R.id.fragmentContainer, fragment)
    .commit()
```

### Fragment

```kotlin
class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("user_name")
        val age = arguments?.getInt("user_age")

        println("Name: $name")
        println("Age: $age")
    }
}
```

---

# 2. Why Arguments Instead of Fragment Constructor?

Avoid doing this:

```kotlin
class DetailsFragment(
    private val userName: String
) : Fragment() {
}
```

Android may recreate a Fragment after configuration changes or process recreation. A custom constructor parameter may therefore cause problems.

Prefer:

```kotlin
class DetailsFragment : Fragment(R.layout.fragment_details)
```

and pass data through:

```kotlin
arguments = Bundle().apply {
    putString("user_name", "Mazharul")
}
```

---

# 3. Companion Object Pattern

A common and clean pattern is to create a `newInstance()` function.

```kotlin
class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {

        private const val ARG_USER_NAME = "user_name"

        fun newInstance(userName: String): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_NAME, userName)
                }
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val userName = arguments?.getString(ARG_USER_NAME)

        println(userName)
    }
}
```

### Activity

```kotlin
val fragment = DetailsFragment.newInstance("Mazharul")

supportFragmentManager.beginTransaction()
    .replace(R.id.fragmentContainer, fragment)
    .commit()
```

### Why this pattern is useful

It makes Fragment creation easy:

```kotlin
DetailsFragment.newInstance("Mazharul")
```

instead of:

```kotlin
val fragment = DetailsFragment()
fragment.arguments = Bundle().apply {
    putString("user_name", "Mazharul")
}
```

---

# 4. Passing Different Data Types with Bundle

`Bundle` can contain many Android-supported data types.

```kotlin
val bundle = Bundle().apply {
    putString("name", "Mazharul")
    putInt("age", 33)
    putBoolean("isStudent", true)
    putDouble("cgpa", 3.31)
}
```

Read them:

```kotlin
val name = arguments?.getString("name")
val age = arguments?.getInt("age")
val isStudent = arguments?.getBoolean("isStudent")
val cgpa = arguments?.getDouble("cgpa")
```

Common methods include:

```kotlin
putString()
putInt()
putBoolean()
putDouble()
putFloat()
putLong()
putStringArrayList()
putParcelable()
putParcelableArrayList()
putSerializable()
```

---

# 5. Passing Parcelable Objects

For custom objects, `Parcelable` is commonly used for Android component data transfer.

Example:

```kotlin
@Parcelize
data class User(
    val id: Int,
    val name: String
) : Parcelable
```

Pass it:

```kotlin
val bundle = Bundle().apply {
    putParcelable("user", user)
}

val fragment = DetailsFragment().apply {
    arguments = bundle
}
```

Read it:

```kotlin
val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    arguments?.getParcelable("user", User::class.java)
} else {
    @Suppress("DEPRECATION")
    arguments?.getParcelable("user")
}
```

For modern Android code, be aware that typed `Bundle` APIs were introduced to improve type safety on newer Android versions.

---

# 6. Fragment → Activity

A Fragment often needs to send information back to its host Activity.

There are several approaches.

## 6.1 Interface Callback

An interface can be used for Fragment → Activity communication.

### Fragment

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

    interface OnUserSelectedListener {
        fun onUserSelected(name: String)
    }

    private var listener: OnUserSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? OnUserSelectedListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    private fun selectUser() {
        listener?.onUserSelected("Mazharul")
    }
}
```

### Activity

```kotlin
class MainActivity : AppCompatActivity(),
    HomeFragment.OnUserSelectedListener {

    override fun onUserSelected(name: String) {
        println("Selected user: $name")
    }
}
```

### Important

The Activity must implement the interface.

If it does not, the callback will not work.

---

# 7. Fragment → Activity with Fragment Result API

For many modern Fragment communication cases, the Fragment Result API is simpler and less tightly coupled than an interface.

### Fragment sends result

```kotlin
parentFragmentManager.setFragmentResult(
    "user_result",
    bundleOf("user_name" to "Mazharul")
)
```

### Activity receives result

An Activity can register a FragmentResultListener on its FragmentManager:

```kotlin
supportFragmentManager.setFragmentResultListener(
    "user_result",
    this
) { _, bundle ->

    val userName = bundle.getString("user_name")

    println("User: $userName")
}
```

This is especially useful when the Fragment needs to send a one-time result to its host.

---

# 8. Fragment → Fragment

Directly keeping references to another Fragment is usually not the preferred design.

Avoid:

```kotlin
val fragment = OtherFragment()

fragment.someMethod()
```

This creates strong coupling between the two Fragments.

Better approaches include:

- Fragment Result API
- Shared ViewModel
- Navigation Component / Safe Args when appropriate

For this phase, focus mainly on the Fragment Result API.

---

# 9. Fragment Result API

The Fragment Result API provides a way for Fragments to communicate through a shared `FragmentManager`.

Basic pattern:

### Sender

```kotlin
parentFragmentManager.setFragmentResult(
    "request_key",
    bundleOf("data" to "Hello")
)
```

### Receiver

```kotlin
parentFragmentManager.setFragmentResultListener(
    "request_key",
    viewLifecycleOwner
) { _, bundle ->

    val data = bundle.getString("data")
}
```

---

# 10. Fragment → Fragment Example

Imagine:

```text
HomeFragment
     |
     | open
     ↓
SelectionFragment
     |
     | select item
     ↓
HomeFragment receives result
```

## HomeFragment — Receiver

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            "selection_request",
            viewLifecycleOwner
        ) { _, bundle ->

            val selectedItem =
                bundle.getString("selected_item")

            println("Selected: $selectedItem")
        }
    }
}
```

## SelectionFragment — Sender

```kotlin
class SelectionFragment :
    Fragment(R.layout.fragment_selection) {

    private fun selectItem() {

        parentFragmentManager.setFragmentResult(
            "selection_request",
            bundleOf(
                "selected_item" to "Android"
            )
        )
    }
}
```

Now `HomeFragment` receives:

```text
Android
```

---

# 11. Why Use viewLifecycleOwner?

When registering a Fragment Result listener inside a Fragment, prefer:

```kotlin
viewLifecycleOwner
```

rather than:

```kotlin
this
```

Example:

```kotlin
parentFragmentManager.setFragmentResultListener(
    "selection_request",
    viewLifecycleOwner
) { _, bundle ->

    val item = bundle.getString("selected_item")
}
```

A Fragment's lifecycle can continue after its View has been destroyed.

Using `viewLifecycleOwner` ties the listener to the Fragment's current View lifecycle and helps avoid updating a destroyed View.

---

# 12. Activity → Fragment Using Fragment Result API

The Fragment Result API can also be used when communication is coordinated through a FragmentManager.

However, for initial input to a Fragment, **Fragment arguments** are usually the natural choice.

### Initial data

```text
Activity
   ↓
Fragment arguments
   ↓
Fragment
```

Example:

```kotlin
val fragment = ProfileFragment.newInstance("Mazharul")
```

Use arguments when the data is part of the Fragment's initial state.

---

# 13. Arguments vs Fragment Result API

| Feature | Arguments / Bundle | Fragment Result API |
|---|---|---|
| Main purpose | Give Fragment initial data | Send a result/data |
| Direction | Usually Activity → Fragment | Fragment → Fragment / Fragment → host |
| Timing | Fragment creation | During Fragment lifecycle |
| Example | User ID | Selected item |
| Coupling | Low | Low |
| One-time result | Not the main purpose | Yes |
| Survives recreation appropriately | Yes, when used correctly | Designed around Fragment lifecycle |

Think:

```text
Arguments = "Here is your starting data."

Fragment Result = "Here is the result you requested."
```

---

# 14. Fragment Result API Request/Response Pattern

A very useful pattern is:

```text
HomeFragment
     |
     | request/open
     ↓
SelectionFragment
     |
     | result
     ↓
HomeFragment
```

### Step 1 — Register receiver first

```kotlin
parentFragmentManager.setFragmentResultListener(
    "select_language",
    viewLifecycleOwner
) { _, bundle ->

    val language = bundle.getString("language")

    // Update UI
}
```

### Step 2 — Open SelectionFragment

```kotlin
parentFragmentManager.beginTransaction()
    .replace(
        R.id.fragmentContainer,
        SelectionFragment()
    )
    .addToBackStack(null)
    .commit()
```

### Step 3 — Send result

```kotlin
parentFragmentManager.setFragmentResult(
    "select_language",
    bundleOf("language" to "Kotlin")
)
```

### Step 4 — Go back

```kotlin
parentFragmentManager.popBackStack()
```

The previous Fragment can receive the result.

---

# 15. Fragment Result API Key and Bundle Key

There are two different concepts.

### Request key

Identifies the communication channel:

```kotlin
"select_language"
```

### Bundle key

Identifies the actual data:

```kotlin
"language"
```

Example:

```kotlin
parentFragmentManager.setFragmentResult(
    "select_language",
    bundleOf("language" to "Kotlin")
)
```

Here:

```text
Request key = select_language
Bundle key  = language
Value       = Kotlin
```

---

# 16. Multiple Values in Fragment Result

You can send multiple values.

```kotlin
parentFragmentManager.setFragmentResult(
    "user_result",
    bundleOf(
        "id" to 101,
        "name" to "Mazharul",
        "isActive" to true
    )
)
```

Receive:

```kotlin
parentFragmentManager.setFragmentResultListener(
    "user_result",
    viewLifecycleOwner
) { _, bundle ->

    val id = bundle.getInt("id")
    val name = bundle.getString("name")
    val isActive = bundle.getBoolean("isActive")
}
```

---

# 17. FragmentManager Choice

This is important.

## Child Fragment communication

If two Fragments are managed by the same FragmentManager, they can communicate through that FragmentManager.

Commonly:

```kotlin
parentFragmentManager
```

For a Fragment nested inside another Fragment, you may also have:

```kotlin
childFragmentManager
```

Do not randomly choose one.

Use the FragmentManager that owns the Fragments involved in the communication.

---

# 18. parentFragmentManager vs childFragmentManager

### parentFragmentManager

The FragmentManager that manages the current Fragment.

```kotlin
parentFragmentManager
```

Useful when communicating with sibling Fragments or the Activity's FragmentManager.

### childFragmentManager

The FragmentManager owned by the current Fragment.

```kotlin
childFragmentManager
```

Useful when a Fragment contains its own child Fragments.

Example:

```text
MainActivity
   |
   └── ParentFragment
          |
          ├── ChildFragmentA
          └── ChildFragmentB
```

ParentFragment may use:

```kotlin
childFragmentManager
```

to manage ChildFragmentA and ChildFragmentB.

---

# 19. Complete Mini Project

## Project: User Selection App

We have:

```text
MainActivity
      |
      ↓
HomeFragment
      |
      ↓
UserSelectionFragment
      |
      ↓
Selected User
      |
      ↓
HomeFragment
```

---

## Step 1 — HomeFragment

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            "user_selected",
            viewLifecycleOwner
        ) { _, bundle ->

            val userName =
                bundle.getString("user_name")

            println("Selected user: $userName")
        }
    }
}
```

---

## Step 2 — UserSelectionFragment

```kotlin
class UserSelectionFragment :
    Fragment(R.layout.fragment_user_selection) {

    private fun selectUser() {

        parentFragmentManager.setFragmentResult(
            "user_selected",
            bundleOf(
                "user_name" to "Mazharul"
            )
        )

        parentFragmentManager.popBackStack()
    }
}
```

---

# 20. Data Flow Diagram

```text
              DATA FLOW

Activity
   |
   | Arguments / Bundle
   ↓
Fragment A
   |
   | Fragment Result API
   ↓
Fragment B
   |
   | Fragment Result API
   ↓
Fragment A
```

Another common flow:

```text
Activity
   |
   ↓
Fragment
   |
   ↓
Fragment Result
   |
   ↓
Activity
```

---

# 21. When to Use Which Method?

## Use Arguments / Bundle when:

You need to give a Fragment initial data.

Example:

```text
Product ID
User ID
Article ID
Category ID
```

Example:

```kotlin
DetailsFragment.newInstance(productId)
```

---

## Use Fragment Result API when:

A Fragment needs to send a result back.

Example:

```text
Selected user
Selected date
Selected category
Selected image
Form result
```

---

## Use Interface Callback when:

You need a strongly defined callback between a Fragment and its host Activity and the architecture fits that pattern.

Example:

```kotlin
interface OnItemClickListener {
    fun onItemClick(id: Int)
}
```

---

## Use Shared ViewModel when:

Multiple Fragments need to share ongoing state.

Example:

```text
CartFragment
     ↕
Shared ViewModel
     ↕
ProductFragment
```

For larger applications, a shared ViewModel can be preferable to sending many individual results.

---

# 22. Common Mistakes

## Mistake 1 — Passing Fragment data through constructor

Avoid:

```kotlin
class DetailsFragment(
    val id: Int
) : Fragment()
```

Prefer:

```kotlin
DetailsFragment.newInstance(id)
```

with `arguments`.

---

## Mistake 2 — Forgetting the same request key

Sender:

```kotlin
setFragmentResult("user_result", ...)
```

Receiver:

```kotlin
setFragmentResultListener("result", ...)
```

This will not match.

Both must use:

```kotlin
"user_result"
```

---

## Mistake 3 — Wrong Bundle key

Sender:

```kotlin
bundleOf("user_name" to "Mazharul")
```

Receiver:

```kotlin
bundle.getString("name")
```

The receiver should use:

```kotlin
bundle.getString("user_name")
```

---

## Mistake 4 — Registering the listener too late

Prefer registering the listener when the receiving Fragment's view is created:

```kotlin
override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
) {
    super.onViewCreated(view, savedInstanceState)

    parentFragmentManager.setFragmentResultListener(
        "user_result",
        viewLifecycleOwner
    ) { _, bundle ->
        // Receive result
    }
}
```

---

## Mistake 5 — Updating a destroyed View

Avoid using a Fragment instance lifecycle when the result is specifically for the Fragment's View.

Prefer:

```kotlin
viewLifecycleOwner
```

---

## Mistake 6 — Overusing direct Fragment references

Avoid tightly connecting:

```text
Fragment A → Fragment B
```

through direct object references.

Prefer communication mechanisms such as:

```text
Fragment Result API
Shared ViewModel
Navigation
```

depending on the requirement.

---

# 23. Quick Revision

### Activity → Fragment

```kotlin
Fragment().apply {
    arguments = Bundle().apply {
        putString("name", "Mazharul")
    }
}
```

---

### Read Fragment arguments

```kotlin
val name = arguments?.getString("name")
```

---

### Fragment → Fragment

```kotlin
parentFragmentManager.setFragmentResult(
    "request_key",
    bundleOf("data" to "Hello")
)
```

---

### Receive Fragment Result

```kotlin
parentFragmentManager.setFragmentResultListener(
    "request_key",
    viewLifecycleOwner
) { _, bundle ->

    val data = bundle.getString("data")
}
```

---

### Go back

```kotlin
parentFragmentManager.popBackStack()
```

---

# 24. Interview Questions

### Q1. How do you pass data from Activity to Fragment?

Use Fragment arguments:

```kotlin
fragment.arguments = Bundle().apply {
    putString("key", "value")
}
```

---

### Q2. Why should you avoid passing data through a Fragment constructor?

Android may recreate the Fragment and does not use arbitrary custom constructor parameters as the standard state restoration mechanism.

---

### Q3. What is Fragment Result API?

It is an AndroidX Fragment API for passing a result between Fragments or between a Fragment and its host using a request key and Bundle.

---

### Q4. What is the difference between Bundle and Fragment Result API?

`Bundle` is a container for key-value data.

Fragment Result API is a communication mechanism that uses a Bundle to send a result.

---

### Q5. Why use viewLifecycleOwner?

Because a Fragment can outlive its View. `viewLifecycleOwner` limits the listener to the View's lifecycle.

---

### Q6. What is parentFragmentManager?

It is the FragmentManager that manages the current Fragment.

---

### Q7. What is childFragmentManager?

It manages child Fragments owned by the current Fragment.

---

### Q8. Can Fragment Result API send custom objects?

Yes, supported objects can be placed in the Bundle, commonly using `Parcelable` for Android data transfer.

---

# 25. Practical Exercise

Build this app:

```text
HomeFragment
     |
     | "Select User"
     ↓
UserListFragment
     |
     | Click "Mazharul"
     ↓
Fragment Result API
     |
     ↓
HomeFragment
```

Requirements:

1. Create `HomeFragment`.
2. Create `UserListFragment`.
3. Add a button in HomeFragment.
4. Open UserListFragment.
5. Display at least three users.
6. When a user is selected, send the name using Fragment Result API.
7. Return to HomeFragment.
8. Display:

```text
Selected User: Mazharul
```

---

# 26. Recommended Architecture for This Phase

For beginner Android projects:

```text
Initial data
    ↓
Arguments / Bundle
    ↓
Fragment
    ↓
User action
    ↓
Fragment Result API
    ↓
Previous Fragment / Activity
```

As your projects become larger:

```text
UI
 ↓
ViewModel
 ↓
Repository
 ↓
Data source
```

and shared state can be handled with a shared ViewModel when appropriate.

---

# 27. Final Cheat Sheet

```text
┌───────────────────────────────────────────┐
│            PHASE 4 CHEAT SHEET            │
├───────────────────────────────────────────┤
│ Activity → Fragment                       │
│       Arguments / Bundle                  │
│                                           │
│ Fragment → Activity                       │
│       Interface or Fragment Result API    │
│                                           │
│ Fragment → Fragment                       │
│       Fragment Result API                 │
│       Shared ViewModel for shared state   │
│                                           │
│ Initial Fragment data                     │
│       arguments                            │
│                                           │
│ Send result                               │
│       setFragmentResult()                │
│                                           │
│ Receive result                            │
│       setFragmentResultListener()        │
│                                           │
│ Fragment View lifecycle                   │
│       viewLifecycleOwner                 │
└───────────────────────────────────────────┘
```

---

## Official Android Documentation

- Fragment communication:
  https://developer.android.com/guide/fragments/communicate

- Fragment Result API:
  https://developer.android.com/guide/fragments/communicate#fragment-result

- Fragment:
  https://developer.android.com/guide/fragments

- Bundle:
  https://developer.android.com/reference/android/os/Bundle

- FragmentManager:
  https://developer.android.com/reference/androidx/fragment/app/FragmentManager

- Parcelable:
  https://developer.android.com/reference/android/os/Parcelable

---

# End of Phase 4

Next recommended phase:

**Phase 5 — Fragment Lifecycle**

Suggested topics:

```text
├── Fragment Lifecycle
├── onAttach()
├── onCreate()
├── onCreateView()
├── onViewCreated()
├── onStart()
├── onResume()
├── onPause()
├── onStop()
├── onDestroyView()
├── onDestroy()
└── onDetach()
```
