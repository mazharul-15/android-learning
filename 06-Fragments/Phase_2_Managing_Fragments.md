# Phase 2: Managing Fragments — Android Development with Kotlin

## 1. What is a Fragment?

A Fragment is a reusable portion of an Android app's UI and behavior. It is hosted by an Activity or another Fragment.

Typical structure:

```text
MainActivity
    |
    +-- FragmentContainerView
            |
            +-- HomeFragment
            +-- ProfileFragment
            +-- SettingsFragment
```

AndroidX import:

```kotlin
import androidx.fragment.app.Fragment
```

---

## 2. Creating a Fragment

Create an XML layout:

`fragment_home.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home Fragment"
        android:textSize="24sp"
        android:layout_gravity="center" />

</FrameLayout>
```

Create the Fragment:

```kotlin
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home)
```

For a simple XML layout, `Fragment(R.layout.fragment_home)` is a convenient modern approach.

---

## 3. FragmentContainerView

`FragmentContainerView` is an AndroidX view designed to host Fragments.

Example:

```xml
<androidx.fragment.app.FragmentContainerView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

A more complete Activity layout using ConstraintLayout:

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragment_container"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 4. Displaying a Fragment

Fragments are commonly displayed through `FragmentManager` and `FragmentTransaction`.

Basic pattern:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        HomeFragment()
    )
    .commit()
```

Conceptually:

```text
Activity
   ↓
FragmentManager
   ↓
beginTransaction()
   ↓
replace()
   ↓
commit()
   ↓
Fragment appears
```

---

## 5. FragmentTransaction

A `FragmentTransaction` represents a set of changes to the FragmentManager.

Common operations:

```kotlin
add()
replace()
remove()
hide()
show()
attach()
detach()
```

Finish a transaction with:

```kotlin
.commit()
```

---

## 6. Add

`add()` adds a Fragment to a container.

```kotlin
supportFragmentManager
    .beginTransaction()
    .add(
        R.id.fragment_container,
        HomeFragment()
    )
    .commit()
```

If another Fragment already occupies the same container, both can remain managed by the FragmentManager and may overlap visually.

---

## 7. Replace

`replace()` replaces the Fragment(s) currently in the specified container with another Fragment.

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        ProfileFragment()
    )
    .commit()
```

Before:

```text
Container
└── HomeFragment
```

After:

```text
Container
└── ProfileFragment
```

For simple screen switching, `replace()` is often easier to understand.

---

## 8. Remove

`remove()` removes a Fragment.

```kotlin
val fragment = HomeFragment()

supportFragmentManager
    .beginTransaction()
    .add(R.id.fragment_container, fragment)
    .commit()
```

Later:

```kotlin
supportFragmentManager
    .beginTransaction()
    .remove(fragment)
    .commit()
```

---

## 9. Back Stack

A transaction is not automatically placed on the Fragment back stack.

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

Then the Back action can reverse that transaction.

Example:

```text
HomeFragment
     ↓
ProfileFragment
     ↓
Back
     ↓
HomeFragment
```

Without `addToBackStack(null)`, that transaction is not added to the Fragment back stack.

---

## 10. Initial Fragment

A common pattern is:

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

The check prevents unnecessarily adding the initial Fragment again when Android restores the Activity and its Fragment state.

---

## 11. Multiple Fragments

Suppose an app has:

```text
HomeFragment
SearchFragment
ProfileFragment
SettingsFragment
```

A simple screen-switching design can use one container:

```text
MainActivity
      |
      +-- FragmentContainerView
              |
              +-- Home
              +-- Search
              +-- Profile
              +-- Settings
```

Switch screens with `replace()`:

```kotlin
fun showHome() {
    supportFragmentManager
        .beginTransaction()
        .replace(
            R.id.fragment_container,
            HomeFragment()
        )
        .commit()
}

fun showProfile() {
    supportFragmentManager
        .beginTransaction()
        .replace(
            R.id.fragment_container,
            ProfileFragment()
        )
        .commit()
}
```

---

## 12. Multiple Fragments at the Same Time

Fragments can also be displayed simultaneously using different containers.

For example:

```text
Activity
|
+-- ListFragment
|
+-- DetailFragment
```

Example:

```kotlin
supportFragmentManager
    .beginTransaction()
    .add(R.id.left_container, ListFragment())
    .add(R.id.right_container, DetailFragment())
    .commit()
```

This type of layout is useful for master-detail or two-pane interfaces.

---

## 13. FragmentManager

`FragmentManager` manages Fragments and Fragment transactions.

From an Activity:

```kotlin
supportFragmentManager
```

From a Fragment, commonly:

```kotlin
parentFragmentManager
```

For child Fragments inside a Fragment:

```kotlin
childFragmentManager
```

Basic relationship:

```text
Activity
   ↓
FragmentManager
   ↓
FragmentTransaction
   ↓
Fragments
```

---

## 14. Complete Beginner Example

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>

<androidx.fragment.app.FragmentContainerView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### fragment_home.xml

```xml
<?xml version="1.0" encoding="utf-8"?>

<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Welcome to Home"
        android:textSize="24sp"
        android:layout_gravity="center" />

</FrameLayout>
```

### HomeFragment.kt

```kotlin
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home)
```

### MainActivity.kt

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

---

## 15. View Binding in a Fragment

If View Binding is enabled:

```kotlin
private var _binding: FragmentHomeBinding? = null
private val binding get() = _binding!!
```

Then:

```kotlin
override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
) {
    super.onViewCreated(view, savedInstanceState)

    _binding = FragmentHomeBinding.bind(view)

    binding.textView.text = "Hello Fragment"
}
```

Clear the binding when the Fragment's view is destroyed:

```kotlin
override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
```

A Fragment can outlive its view, so this pattern helps avoid keeping a destroyed view around.

---

## 16. Fragment Lifecycle Preview

A simplified lifecycle is:

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

When the Fragment's view goes away:

```text
onPause()
    ↓
onStop()
    ↓
onDestroyView()
```

The complete lifecycle will be studied separately.

---

## 17. `onCreateView()` vs `Fragment(R.layout...)`

Traditional approach:

```kotlin
class HomeFragment : Fragment() {

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
}
```

Simpler approach for a fixed XML layout:

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home)
```

---

## 18. Add vs Replace vs Remove

| Operation | Purpose |
|---|---|
| `add()` | Add a Fragment |
| `replace()` | Replace Fragment(s) in a container |
| `remove()` | Remove a Fragment |
| `hide()` | Hide a Fragment |
| `show()` | Show a hidden Fragment |
| `detach()` | Detach a Fragment's view |
| `attach()` | Re-attach a detached Fragment |

For beginners:

```text
Show a new screen  → replace()
Add another       → add()
Remove            → remove()
Support Back      → addToBackStack()
```

---

## 19. Common Mistakes

### Mistake 1: No Fragment container

You need a container such as:

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/fragment_container"
    ... />
```

### Mistake 2: Mixing old and AndroidX Fragment classes

Prefer:

```kotlin
import androidx.fragment.app.Fragment
```

with AndroidX Fragment APIs.

### Mistake 3: Forgetting `commit()`

Wrong:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        HomeFragment()
    )
```

Correct:

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(
        R.id.fragment_container,
        HomeFragment()
    )
    .commit()
```

### Mistake 4: Using `add()` when you mean screen replacement

For simple screen navigation, `replace()` is usually easier.

### Mistake 5: Re-adding the initial Fragment

Use:

```kotlin
if (savedInstanceState == null) {
    // initial transaction
}
```

when appropriate.

---

## 20. Mini Practice Project

Build:

```text
MainActivity
     |
     +-- FragmentContainerView
             |
             +-- HomeFragment
             +-- ProfileFragment
```

Requirements:

1. Create `HomeFragment`.
2. Create `ProfileFragment`.
3. Add `FragmentContainerView`.
4. Display Home initially.
5. Add a Profile button.
6. Use `replace()` to show Profile.
7. Use `addToBackStack(null)`.
8. Press Back to return to Home.

Expected flow:

```text
App Start
   ↓
HomeFragment
   ↓
Profile Button
   ↓
ProfileFragment
   ↓
Android Back
   ↓
HomeFragment
```

---

## 21. Quick Revision

### Fragment

```kotlin
class HomeFragment : Fragment(R.layout.fragment_home)
```

### FragmentContainerView

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### Add

```kotlin
supportFragmentManager
    .beginTransaction()
    .add(R.id.fragment_container, HomeFragment())
    .commit()
```

### Replace

```kotlin
supportFragmentManager
    .beginTransaction()
    .replace(R.id.fragment_container, ProfileFragment())
    .commit()
```

### Remove

```kotlin
supportFragmentManager
    .beginTransaction()
    .remove(fragment)
    .commit()
```

### Back stack

```kotlin
.addToBackStack(null)
```

### Initial Fragment

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

## 22. Learning Path After Phase 2

```text
Phase 2: Managing Fragments
        ↓
Fragment Lifecycle
        ↓
Fragment ↔ Activity Communication
        ↓
Fragment ↔ Fragment Communication
        ↓
Navigation Component
        ↓
Navigation Arguments / Safe Args
        ↓
ViewModel + Fragments
```

### Key concepts to master

```text
Fragment
FragmentContainerView
FragmentManager
FragmentTransaction
add()
replace()
remove()
addToBackStack()
Multiple Fragments
```

---

## Official Android Documentation

Fragments:
https://developer.android.com/guide/fragments

FragmentContainerView:
https://developer.android.com/reference/androidx/fragment/app/FragmentContainerView

Fragment transactions:
https://developer.android.com/guide/fragments/transactions

AndroidX Fragment:
https://developer.android.com/reference/androidx/fragment/app/Fragment
