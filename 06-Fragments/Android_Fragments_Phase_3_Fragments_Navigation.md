# Phase 3: Navigation — Android Development with Kotlin

## Overview

This phase focuses on moving between Fragments in an Android app using the AndroidX Fragment system.

Topics:

1. Fragment Navigation
2. Back Stack
3. Back Button

A simple navigation flow:

```text
HomeFragment
     ↓
ProfileFragment
     ↓
SettingsFragment
```

The Android Back button can move the user back through the Fragment back stack.

---

# 1. Fragment Navigation

Fragment navigation means moving from one Fragment to another.

For example:

```text
HomeFragment
     ↓
DetailsFragment
```

A simple way to navigate with Fragment transactions is:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        DetailsFragment()
    )
    .addToBackStack(null)
    .commit()
```

Here:

- `supportFragmentManager` manages the Fragments.
- `beginTransaction()` starts a Fragment transaction.
- `replace()` changes the Fragment in the container.
- `addToBackStack(null)` saves the transaction for Back navigation.
- `commit()` executes the transaction.

---

# 2. Basic Navigation Example

Suppose we have:

```text
MainActivity
    |
    +-- FragmentContainerView
             |
             +-- HomeFragment
```

When the user clicks a button:

```text
HomeFragment
     |
     | Details button
     ↓
DetailsFragment
```

In the Activity:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        DetailsFragment()
    )
    .addToBackStack(null)
    .commit()
```

---

# 3. Why addToBackStack() Matters

Consider:

```kotlin
.replace(
    R.id.fragment_container,
    DetailsFragment()
)
.commit()
```

This changes the displayed Fragment.

But if you want the user to return to the previous Fragment using the Back button, add:

```kotlin
.addToBackStack(null)
```

Complete:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        DetailsFragment()
    )
    .addToBackStack(null)
    .commit()
```

Think of it as:

```text
Home
  ↓
[Back Stack: Home → Details transaction]
  ↓
Details
```

Pressing Back reverses the transaction.

---

# 4. Back Stack

The Fragment back stack stores Fragment transactions that can later be reversed.

Example:

```text
Start
  ↓
HomeFragment
```

Navigate to Profile:

```text
HomeFragment
  ↓
ProfileFragment
```

Navigate to Settings:

```text
HomeFragment
  ↓
ProfileFragment
  ↓
SettingsFragment
```

The Back stack contains the previous transactions.

Press Back:

```text
SettingsFragment
  ↓ Back
ProfileFragment
```

Press Back again:

```text
ProfileFragment
  ↓ Back
HomeFragment
```

Press Back again:

```text
HomeFragment
  ↓ Back
Activity may finish
```

---

# 5. Navigation Without Back Stack

Suppose:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        ProfileFragment()
    )
    .commit()
```

There is no:

```kotlin
.addToBackStack(null)
```

The transaction is not added to the Fragment back stack.

Therefore, you should not expect that particular transaction to be reversed by the Fragment back stack when Back is pressed.

---

# 6. Navigation With Back Stack

Use:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        ProfileFragment()
    )
    .addToBackStack(null)
    .commit()
```

Now the transaction is added to the back stack.

Flow:

```text
Home
 ↓
Profile
 ↓
Back
 ↓
Home
```

---

# 7. Practical Example: Home → Details

## HomeFragment

Suppose `fragment_home.xml` contains:

```xml
<Button
    android:id="@+id/btnDetails"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Open Details" />
```

If you use View Binding:

```kotlin
binding.btnDetails.setOnClickListener {
    parentFragmentManager
        .beginTransaction()
        .replace(
            R.id.fragment_container,
            DetailsFragment()
        )
        .addToBackStack(null)
        .commit()
}
```

Because the code is inside a Fragment, `parentFragmentManager` is commonly used to access the FragmentManager that manages that Fragment.

---

# 8. Alternative: Navigate from the Activity

If the Activity handles the click:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        DetailsFragment()
    )
    .addToBackStack(null)
    .commit()
```

Activity:

```kotlin
class MainActivity : AppCompatActivity() {

    fun openDetails() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                DetailsFragment()
            )
            .addToBackStack(null)
            .commit()
    }
}
```

---

# 9. Multiple Navigation Steps

Suppose:

```text
Home
  ↓
Profile
  ↓
Settings
```

Home → Profile:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        ProfileFragment()
    )
    .addToBackStack(null)
    .commit()
```

Profile → Settings:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        SettingsFragment()
    )
    .addToBackStack(null)
    .commit()
```

Now:

```text
Current:
Settings

Back:
Profile

Back:
Home
```

---

# 10. Android Back Button

The Android system Back action can interact with the Fragment back stack.

Suppose:

```text
Home
 ↓
Profile
 ↓
Settings
```

When the user presses Back:

```text
Settings
 ↓ Back
Profile
```

Again:

```text
Profile
 ↓ Back
Home
```

When there are no more Fragment transactions on the back stack, the Activity's normal Back behavior can occur.

---

# 11. What Happens When Back Is Pressed?

Conceptually:

```text
User presses Back
       ↓
FragmentManager checks back stack
       ↓
Back-stack entry available?
       |
    +-- Yes
    |    ↓
    |  Pop previous transaction
    |    ↓
    |  Previous Fragment appears
    |
    +-- No
         ↓
       Activity's Back behavior
```

The exact behavior can depend on the Activity, FragmentManager, and any Back handling registered by the app.

---

# 12. Manual Back Stack Pop

You can manually pop the latest Fragment transaction:

```kotlin
supportFragmentManager.popBackStack()
```

For example:

```kotlin
button.setOnClickListener {
    supportFragmentManager.popBackStack()
}
```

This is similar to moving back one step in the Fragment transaction history.

---

# 13. Pop the Entire Back Stack

You can also pop multiple entries.

A commonly used API is:

```kotlin
supportFragmentManager.popBackStack(
    null,
    FragmentManager.POP_BACK_STACK_INCLUSIVE
)
```

This can clear/pop the relevant back-stack entries.

Use this carefully because it changes the navigation history.

---

# 14. Back Button vs Up Button

These are not exactly the same concept.

### Back

The system Back action generally means:

```text
Return to the previous state
```

### Up

The Up button in an app's toolbar generally means:

```text
Navigate upward in the app's navigation hierarchy
```

Example:

```text
Home
  ↓
Product List
  ↓
Product Details
```

Back usually returns to the previous screen/state.

Up usually follows the app's defined hierarchy.

---

# 15. Handling Back Manually

Modern Android provides the `OnBackPressedDispatcher` API.

In an Activity:

```kotlin
onBackPressedDispatcher.addCallback(this) {

    // Handle Back
}
```

Example:

```kotlin
onBackPressedDispatcher.addCallback(this) {
    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStack()
    } else {
        finish()
    }
}
```

This gives your Activity control over Back behavior.

However, do not override Back behavior unnecessarily. Let the FragmentManager/system handle normal navigation when possible.

---

# 16. Back Handling Inside a Fragment

A Fragment can also register an `OnBackPressedCallback`.

Example:

```kotlin
requireActivity()
    .onBackPressedDispatcher
    .addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack()
            }
        }
    )
```

Using:

```kotlin
viewLifecycleOwner
```

helps tie the callback to the Fragment's view lifecycle.

Use custom Back handling only when the Fragment has a specific reason to intercept Back.

---

# 17. Back Stack Entry Count

You can check:

```kotlin
supportFragmentManager.backStackEntryCount
```

Example:

```kotlin
if (supportFragmentManager.backStackEntryCount > 0) {
    supportFragmentManager.popBackStack()
}
```

This tells you how many entries are currently in the Fragment back stack.

---

# 18. Named Back Stack Entries

You can give a transaction a name:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        ProfileFragment()
    )
    .addToBackStack("profile")
    .commit()
```

Then you can refer to that entry by name when using relevant FragmentManager APIs.

Example:

```kotlin
supportFragmentManager.popBackStack("profile", 0)
```

The exact pop flags determine whether the named entry itself is included.

---

# 19. Important: Back Stack Stores Transactions

A common beginner misunderstanding is:

> "The back stack stores all Fragment objects."

More accurately, the Fragment back stack records Fragment transactions so they can be reversed.

For example:

```text
Transaction 1:
Home → Profile

Transaction 2:
Profile → Settings
```

The back stack records those transactions.

---

# 20. Complete Navigation Example

## MainActivity

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    HomeFragment()
                )
                .commit()
        }
    }
}
```

## HomeFragment

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnDetails)
            .setOnClickListener {

                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        DetailsFragment()
                    )
                    .addToBackStack(null)
                    .commit()
            }
    }
}
```

## DetailsFragment

```kotlin
class DetailsFragment : Fragment(R.layout.fragment_details)
```

Navigation:

```text
App starts
   ↓
HomeFragment
   ↓
Click Details
   ↓
DetailsFragment
   ↓
Press Back
   ↓
HomeFragment
```

---

# 21. Common Mistakes

## Mistake 1: Forgetting addToBackStack()

If you want Back to reverse a Fragment transaction:

```kotlin
.addToBackStack(null)
```

must normally be included.

---

## Mistake 2: Adding the initial Fragment to the back stack unnecessarily

Usually the initial Fragment is added without:

```kotlin
.addToBackStack(null)
```

Example:

```kotlin
if (savedInstanceState == null) {
    supportFragmentManager
        .beginTransaction()
        .replace(
            R.id.fragment_container,
            HomeFragment()
        )
        .commit()
}
```

---

## Mistake 3: Manually finishing the Activity too early

Avoid doing:

```kotlin
requireActivity().finish()
```

every time Back is pressed.

First understand whether the Fragment back stack should handle the navigation.

---

## Mistake 4: Creating unnecessary custom Back logic

Normal Fragment navigation often works with the FragmentManager back stack without custom Back handling.

Only add custom Back behavior when your app actually needs it.

---

# 22. Mini Project

Build:

```text
MainActivity
      |
      +-- FragmentContainerView
              |
              +-- HomeFragment
                      |
                      | Button
                      ↓
                 DetailsFragment
                      |
                      | Button
                      ↓
                 SettingsFragment
```

Requirements:

### Home

Button:

```text
Open Details
```

### Details

Button:

```text
Open Settings
```

### Settings

Press Android Back:

```text
Settings
   ↓
Details
```

Press Back again:

```text
Details
   ↓
Home
```

Expected navigation:

```text
Home
  ↓
Details
  ↓
Settings
  ↓ Back
Details
  ↓ Back
Home
```

---

# 23. Quick Revision

### Navigate to Fragment

```kotlin
parentFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        DetailsFragment()
    )
    .addToBackStack(null)
    .commit()
```

### Add transaction to Back Stack

```kotlin
.addToBackStack(null)
```

### Pop one Back Stack entry

```kotlin
parentFragmentManager.popBackStack()
```

### Check Back Stack

```kotlin
parentFragmentManager.backStackEntryCount
```

### Activity Back Dispatcher

```kotlin
onBackPressedDispatcher.addCallback(this) {
    // Back handling
}
```

---

# 24. Key Concepts to Memorize

```text
Fragment Navigation
       ↓
FragmentManager
       ↓
FragmentTransaction
       ↓
replace()
       ↓
addToBackStack()
       ↓
Android Back
       ↓
popBackStack()
```

Remember:

```text
replace()
    = change the displayed Fragment

addToBackStack()
    = save the transaction for Back navigation

popBackStack()
    = reverse/pop a back-stack transaction
```

---

# 25. What to Learn Next

After Phase 3, a useful sequence is:

```text
Phase 3: Navigation
        ↓
Fragment Lifecycle
        ↓
Fragment ↔ Fragment Communication
        ↓
Navigation Component
        ↓
NavHostFragment
        ↓
NavController
        ↓
Navigation Graph
        ↓
Safe Args / Navigation Arguments
        ↓
ViewModel + Navigation
```

---

## Official Android Documentation

Fragments:
https://developer.android.com/guide/fragments

Fragment transactions:
https://developer.android.com/guide/fragments/transactions

FragmentManager:
https://developer.android.com/reference/androidx/fragment/app/FragmentManager

OnBackPressedDispatcher:
https://developer.android.com/reference/androidx/activity/OnBackPressedDispatcher
