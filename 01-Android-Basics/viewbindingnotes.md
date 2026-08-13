# View Binding — Complete Notes

View Binding is an Android feature that generates a binding class for each XML layout file. It lets you access XML views directly from Kotlin without manually using `findViewById()`.

## Enable View Binding

In `app/build.gradle.kts`:

```kotlin
android {
    buildFeatures {
        viewBinding = true
    }
}
```

Then sync Gradle.

## How Binding Classes Are Generated

```text
activity_main.xml
        ↓
ActivityMainBinding

activity_login.xml
        ↓
ActivityLoginBinding

fragment_home.xml
        ↓
FragmentHomeBinding

item_student.xml
        ↓
ItemStudentBinding
```

Android generates these classes automatically.

## XML ID → Binding Property

If XML contains:

```xml
<TextView
    android:id="@+id/tvName"
    ... />

<EditText
    android:id="@+id/etEmail"
    ... />

<Button
    android:id="@+id/btnLogin"
    ... />
```

You can use:

```kotlin
binding.tvName
binding.etEmail
binding.btnLogin
```

The pattern is:

```text
android:id="@+id/btnLogin"
          ↓
binding.btnLogin
```

## Activity View Binding

Suppose `activity_main.xml` contains:

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Mazharul"
        android:textSize="24sp" />

    <EditText
        android:id="@+id/etName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter your name" />

    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Submit" />

</LinearLayout>
```

Then:

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            binding.tvName.text = name
        }
    }
}
```

## Understand the Three Important Lines

### 1. Declare binding

```kotlin
private lateinit var binding: ActivityMainBinding
```

`binding` is the variable holding the generated binding object.

`ActivityMainBinding` comes from `activity_main.xml`.

`lateinit` means the variable will be initialized later.

### 2. Inflate binding

```kotlin
binding = ActivityMainBinding.inflate(layoutInflater)
```

This creates the binding object and inflates the XML layout.

Conceptually:

```text
activity_main.xml
       ↓
inflate()
       ↓
ActivityMainBinding
```

### 3. Set the root view

```kotlin
setContentView(binding.root)
```

`binding.root` represents the root View of the XML layout.

If the XML root is a `LinearLayout`, `binding.root` represents that LinearLayout.

If the XML root is a `ConstraintLayout`, `binding.root` represents that ConstraintLayout.

## Traditional `findViewById()` vs View Binding

Without View Binding:

```kotlin
val email = findViewById<EditText>(R.id.etEmail)
val password = findViewById<EditText>(R.id.etPassword)
val login = findViewById<Button>(R.id.btnLogin)

login.setOnClickListener {
    val emailText = email.text.toString()
}
```

With View Binding:

```kotlin
binding.btnLogin.setOnClickListener {
    val emailText = binding.etEmail.text.toString()
}
```

View Binding removes the need to manually call `findViewById()` for views in the bound layout.

## Type Safety

View Binding knows the type of each view.

For example:

```xml
<Button
    android:id="@+id/btnLogin"
    ... />
```

means:

```kotlin
binding.btnLogin
```

is known as a `Button`.

You do not need:

```kotlin
findViewById<Button>(R.id.btnLogin)
```

## Views Without IDs

A view normally needs an ID to be directly accessed through a binding property.

For example:

```xml
<TextView
    android:id="@+id/tvTitle"
    ... />
```

gives:

```kotlin
binding.tvTitle
```

A view without an ID generally does not provide a named binding property.

## Changing an XML ID

If XML changes from:

```xml
android:id="@+id/tvName"
```

to:

```xml
android:id="@+id/tvUsername"
```

then Kotlin must change from:

```kotlin
binding.tvName
```

to:

```kotlin
binding.tvUsername
```

## View Binding in Fragments

Fragments need special handling because a Fragment can outlive its View.

A common pattern is:

```kotlin
private var _binding: FragmentHomeBinding? = null

private val binding
    get() = _binding!!

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {

    _binding = FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    )

    return binding.root
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
```

The important difference:

```text
Activity
→ binding normally stays with Activity

Fragment
→ binding belongs to the Fragment's View
→ clear it in onDestroyView()
```

Clearing the binding prevents it from holding references to a destroyed Fragment View.

## View Binding in RecyclerView

View Binding is commonly used in RecyclerView ViewHolders.

If the item layout is:

```text
item_student.xml
```

Android generates:

```text
ItemStudentBinding
```

Example:

```kotlin
class StudentViewHolder(
    private val binding: ItemStudentBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(student: Student) {
        binding.tvName.text = student.name
        binding.tvDepartment.text = student.department
    }
}
```

Inflating the item:

```kotlin
override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
): StudentViewHolder {

    val binding = ItemStudentBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    return StudentViewHolder(binding)
}
```

## View Binding vs Data Binding

Do not confuse View Binding with Data Binding.

### View Binding

Main purpose:

```kotlin
binding.tvName.text = "Mazharul"
```

It provides safe access to XML views.

### Data Binding

Data Binding can connect UI directly with data and expressions:

```xml
android:text="@{user.name}"
```

For normal XML Android development, View Binding is simpler and is a good default.

## View Binding vs Kotlin Synthetic Views

Older projects sometimes used:

```kotlin
import kotlinx.android.synthetic.main.activity_main.*
```

and then:

```kotlin
tvName.text = "Mazharul"
```

Kotlin Android Extensions synthetic view access is deprecated.

For modern projects, use:

```kotlin
binding.tvName.text = "Mazharul"
```

## Complete Login Example

### `activity_login.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Login"
        android:textSize="32sp"
        android:textStyle="bold" />

    <EditText
        android:id="@+id/etEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:inputType="textEmailAddress" />

    <EditText
        android:id="@+id/etPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Password"
        android:inputType="textPassword" />

    <Button
        android:id="@+id/btnLogin"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Login" />

</LinearLayout>
```

### `LoginActivity.kt`

```kotlin
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            Log.d("LOGIN", "Email: $email")
        }
    }
}
```

## Multiple XML Layouts

For example:

```text
res/layout/

activity_main.xml
activity_login.xml
activity_register.xml
fragment_home.xml
item_student.xml
```

Generated binding classes:

```text
ActivityMainBinding
ActivityLoginBinding
ActivityRegisterBinding
FragmentHomeBinding
ItemStudentBinding
```

## Good XML Naming

Good layout filenames produce understandable binding class names.

```text
activity_main.xml
→ ActivityMainBinding

activity_student_details.xml
→ ActivityStudentDetailsBinding

fragment_home.xml
→ FragmentHomeBinding

item_student.xml
→ ItemStudentBinding
```

Use clear XML filenames.

## Common Activity Pattern

Memorize this:

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvName.text = "Mazharul"

        binding.btnLogin.setOnClickListener {
            // ...
        }
    }
}
```

The most important lines are:

```kotlin
private lateinit var binding: ActivityMainBinding
```

```kotlin
binding = ActivityMainBinding.inflate(layoutInflater)
```

```kotlin
setContentView(binding.root)
```

Then:

```kotlin
binding.viewId
```

## Simple Mental Model

Think of View Binding as a bridge between XML and Kotlin:

```text
activity_main.xml
       ↓
Android generates
       ↓
ActivityMainBinding
       ↓
MainActivity.kt
       ↓
binding.tvName
binding.etEmail
binding.btnLogin
```

Without View Binding:

```text
XML
 ↓
findViewById()
 ↓
Kotlin
```

With View Binding:

```text
XML
 ↓
Generated Binding Class
 ↓
Kotlin
```

## Important Things to Remember

1. Enable View Binding in `build.gradle.kts`.
2. Android generates a Binding class for each XML layout.
3. `activity_main.xml` generates `ActivityMainBinding`.
4. Inflate the binding.
5. Use `setContentView(binding.root)` in an Activity.
6. Access views using `binding.viewId`.
7. View Binding reduces the need for `findViewById()`.
8. View Binding is type-safe.
9. Activity binding is straightforward.
10. Fragment binding should be cleared in `onDestroyView()`.
11. RecyclerView ViewHolders commonly use View Binding.
12. View Binding does not replace XML.

## Final Example

XML:

```xml
<TextView
    android:id="@+id/tvWelcome"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Welcome" />

<Button
    android:id="@+id/btnLogin"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Login" />
```

Kotlin:

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvWelcome.text = "Welcome Mazharul"

        binding.btnLogin.setOnClickListener {
            Log.d("MainActivity", "Login clicked")
        }
    }
}
```

The core concept is:

```text
android:id="@+id/btnLogin"
          ↓
binding.btnLogin
```

**View Binding = a generated, type-safe bridge that lets Kotlin access views from an XML layout without manually using `findViewById()`.**
