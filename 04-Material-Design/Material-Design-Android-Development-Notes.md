# Material Design for Android Development

## 1. What is Material Design?

Material Design is Google's design system for creating consistent, attractive, and user-friendly Android applications.

It provides:

- Design principles
- UI components
- Colors and themes
- Typography
- Buttons
- Cards
- Text fields
- Navigation
- Dialogs
- Bottom sheets
- Accessibility guidance

The modern Android design system is commonly called:

```text
Material Design 3 (M3)
Material You
```

---

# 2. Why Material Design is Important

Material Design helps build apps that are:

```text
Consistent
Accessible
Responsive
Modern
Easy to use
```

Instead of designing every component from scratch, Android developers can use Material components.

Examples:

```text
MaterialButton
MaterialCardView
TextInputLayout
TopAppBar
NavigationBar
FloatingActionButton
```

In Jetpack Compose:

```kotlin
Button()
Card()
TextField()
NavigationBar()
TopAppBar()
FloatingActionButton()
```

---

# 3. Material Design 2 vs Material Design 3

## Material Design 2

Older Material system.

## Material Design 3

Modern Material system with:

```text
Material You
Dynamic Color
Modern ColorScheme
Updated Components
Improved Adaptive UI
```

For modern Android development, Material Design 3 is important to learn.

---

# 4. Material Design with XML and Compose

Material Design works with:

```text
XML Views
Jetpack Compose
```

## XML Example

```xml
<com.google.android.material.button.MaterialButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Login" />
```

## Compose Example

```kotlin
Button(
    onClick = { }
) {
    Text("Login")
}
```

---

# 5. Material Components Dependency

For XML/View projects, Material Components are added through Gradle.

Concept:

```kotlin
dependencies {
    implementation(
        "com.google.android.material:material:<version>"
    )
}
```

Use the version appropriate for your project.

---

# 6. Material Theme

A Material theme controls the overall appearance of an app.

Important parts:

```text
ColorScheme
Typography
Shapes
```

Compose concept:

```kotlin
MaterialTheme(
    colorScheme = lightColorScheme(),
    typography = Typography()
) {
    App()
}
```

---

# 7. Color System

Material 3 uses semantic colors.

Important colors:

```text
primary
onPrimary

primaryContainer
onPrimaryContainer

secondary
onSecondary

tertiary
onTertiary

background
onBackground

surface
onSurface

error
onError
```

Example:

```text
primary
    ↓
Button background

onPrimary
    ↓
Text or icon on button
```

---

# 8. ColorScheme Example

```kotlin
private val LightColors =
    lightColorScheme(
        primary = ...,
        secondary = ...,
        background = ...,
        surface = ...
    )
```

Use theme colors:

```kotlin
MaterialTheme
    .colorScheme
    .primary
```

Avoid hardcoding application colors everywhere.

---

# 9. Dynamic Color

Material Design 3 supports dynamic colors on supported Android devices.

Concept:

```text
Device wallpaper
      ↓
System colors
      ↓
Dynamic ColorScheme
      ↓
Application UI
```

This helps applications feel integrated with the Android system.

---

# 10. Typography

Typography controls text appearance.

Important properties:

```text
Font family
Font size
Font weight
Line height
Letter spacing
```

Common Material styles:

```text
displayLarge
headlineLarge
headlineMedium
titleLarge
bodyLarge
bodyMedium
labelLarge
```

Example:

```kotlin
Text(
    text = "Welcome",
    style =
        MaterialTheme
            .typography
            .headlineLarge
)
```

---

# 11. Buttons

Common Material button styles:

```text
Filled Button
Outlined Button
Text Button
Elevated Button
Tonal Button
```

## Filled Button

```kotlin
Button(
    onClick = { }
) {
    Text("Login")
}
```

## Outlined Button

```kotlin
OutlinedButton(
    onClick = { }
) {
    Text("Cancel")
}
```

## Text Button

```kotlin
TextButton(
    onClick = { }
) {
    Text("Learn More")
}
```

Use the primary button for the most important action.

---

# 12. MaterialButton in XML

```xml
<com.google.android.material.button.MaterialButton
    android:id="@+id/loginButton"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Login" />
```

---

# 13. Cards

Cards group related information.

Examples:

```text
Product
User Profile
Article
Settings Section
Dashboard Information
```

Compose:

```kotlin
Card {
    Column {
        Text("Product Name")
        Text("Product Description")
    }
}
```

---

# 14. Text Fields

Material text fields can support:

```text
Labels
Supporting text
Error states
Leading icons
Trailing icons
```

Compose example:

```kotlin
var email by remember {
    mutableStateOf("")
}

OutlinedTextField(
    value = email,
    onValueChange = {
        email = it
    },
    label = {
        Text("Email")
    }
)
```

---

# 15. TextInputLayout in XML

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <com.google.android.material.textfield.TextInputEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</com.google.android.material.textfield.TextInputLayout>
```

---

# 16. Text Field Error Example

```kotlin
OutlinedTextField(
    value = email,

    onValueChange = {
        email = it
    },

    isError = email.isEmpty(),

    label = {
        Text("Email")
    },

    supportingText = {

        if (email.isEmpty()) {
            Text("Email is required")
        }
    }
)
```

---

# 17. Top App Bar

A Top App Bar usually contains:

```text
Screen Title
Navigation Icon
Action Icons
```

Example:

```kotlin
TopAppBar(
    title = {
        Text("Home")
    }
)
```

---

# 18. Bottom Navigation

Bottom navigation is useful for major destinations.

Example:

```text
Home
Search
Profile
```

Compose concept:

```kotlin
NavigationBar {

    NavigationBarItem(
        selected = true,
        onClick = { },
        icon = {
            Icon(...)
        },
        label = {
            Text("Home")
        }
    )
}
```

---

# 19. Navigation Rail

Navigation Rail is useful on larger screens.

Examples:

```text
Tablet
Foldable
Large screen
```

---

# 20. Floating Action Button (FAB)

A FAB represents an important primary action.

Examples:

```text
Add
Create
Compose
Camera
```

Compose:

```kotlin
FloatingActionButton(
    onClick = { }
) {
    Icon(
        imageVector =
            Icons.Default.Add,
        contentDescription = "Add"
    )
}
```

---

# 21. Dialog

Dialogs are useful for important decisions.

```kotlin
AlertDialog(

    onDismissRequest = { },

    title = {
        Text("Delete Item?")
    },

    text = {
        Text(
            "This action cannot be undone."
        )
    },

    confirmButton = {

        Button(
            onClick = { }
        ) {
            Text("Delete")
        }
    },

    dismissButton = {

        TextButton(
            onClick = { }
        ) {
            Text("Cancel")
        }
    }
)
```

---

# 22. Snackbar

Snackbars provide temporary feedback.

Examples:

```text
Item deleted
Message sent
Saved successfully
```

Do not use Snackbars for critical information.

---

# 23. Bottom Sheet

Bottom sheets appear from the bottom of the screen.

Common uses:

```text
More options
Share actions
Filters
Item details
```

Compose:

```kotlin
ModalBottomSheet(
    onDismissRequest = { }
) {
    Text("More Options")
}
```

---

# 24. Material Icons

Material provides common icons.

Examples:

```text
Home
Search
Settings
Favorite
Add
Delete
ArrowBack
```

Example:

```kotlin
Icon(
    imageVector =
        Icons.Default.Home,
    contentDescription =
        "Home"
)
```

For meaningful actions, use a meaningful `contentDescription`.

---

# 25. Layout Principles

Good Material layouts focus on:

```text
Clear hierarchy
Consistent spacing
Readable content
Visual grouping
Predictable interactions
```

Do not give every component equal visual importance.

---

# 26. Spacing

Use consistent spacing values.

Common examples:

```text
4dp
8dp
12dp
16dp
24dp
32dp
```

Compose:

```kotlin
Modifier.padding(
    16.dp
)
```

---

# 27. Shapes

Material Design uses consistent shapes.

Examples:

```text
Rounded corners
Small shapes
Medium shapes
Large shapes
```

Avoid random corner radius values throughout the app.

---

# 28. Elevation and Surface

Elevation helps create hierarchy.

Common surfaces:

```text
Background
Surface
Card
Elevated Card
Dialog
Bottom Sheet
```

Use elevation carefully. Too many shadows make the UI cluttered.

---

# 29. Accessibility

Important accessibility areas:

```text
Readable text
Good contrast
Large touch targets
Screen reader labels
Meaningful icons
Do not rely only on color
```

Bad:

```text
Only red color indicates an error.
```

Better:

```text
Error color
+
Error icon
+
Clear message
```

---

# 30. Dark Theme

Apps should consider both:

```text
Light Mode
Dark Mode
```

Example:

```kotlin
val colors =
    if (darkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
```

Then:

```kotlin
MaterialTheme(
    colorScheme = colors
) {
    App()
}
```

---

# 31. Material Design + Jetpack Compose

A common screen structure:

```kotlin
@Composable
fun App() {

    MaterialTheme {

        Scaffold(

            topBar = {

                TopAppBar(
                    title = {
                        Text("My App")
                    }
                )
            },

            floatingActionButton = {

                FloatingActionButton(
                    onClick = { }
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Add,

                        contentDescription =
                            "Add"
                    )
                }
            }

        ) { padding ->

            HomeScreen(
                modifier =
                    Modifier.padding(
                        padding
                    )
            )
        }
    }
}
```

---

# 32. Scaffold

`Scaffold` helps organize common screen areas.

```text
Scaffold
│
├── TopAppBar
├── Main Content
├── NavigationBar
└── FloatingActionButton
```

It can also manage Snackbar areas.

---

# 33. Material Design + Android Architecture

A modern architecture can look like:

```text
Repository
    ↓
ViewModel
    ↓
StateFlow
    ↓
Composable
    ↓
Material UI Components
```

Example:

```kotlin
when (state) {

    UiState.Loading -> {
        CircularProgressIndicator()
    }

    is UiState.Success -> {

        Card {
            Text(
                state.data.name
            )
        }
    }

    is UiState.Error -> {

        Text(
            state.message
        )
    }
}
```

Material components display the current UI state.

---

# 34. Material Login Screen Example

A login screen may contain:

```text
Title
Email Text Field
Password Text Field
Login Button
Forgot Password Button
Snackbar
```

Compose:

```kotlin
@Composable
fun LoginScreen() {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "Login",

            style =
                MaterialTheme
                    .typography
                    .headlineLarge
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        OutlinedTextField(

            value = email,

            onValueChange = {
                email = it
            },

            label = {
                Text("Email")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(

            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        Button(

            onClick = {
                // Login action
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text("Login")
        }
    }
}
```

---

# 35. XML vs Jetpack Compose

| Area | XML | Jetpack Compose |
|---|---|---|
| Button | MaterialButton | Button |
| Card | MaterialCardView | Card |
| Text Field | TextInputLayout | TextField |
| Theme | XML Theme | MaterialTheme |
| UI | XML Layout | Composable |
| State | LiveData/Observers | State/StateFlow |

Both are useful for Android development.

---

# 36. Common Material Design Mistakes

## Mistake 1: Hardcoding Colors

Avoid:

```kotlin
Color.Red
```

for general theme colors.

Prefer:

```kotlin
MaterialTheme
    .colorScheme
    .primary
```

## Mistake 2: Too Many Colors

Use a consistent ColorScheme.

## Mistake 3: Too Many Shadows

Use elevation only when it improves hierarchy.

## Mistake 4: Ignoring Dark Theme

Test your UI in both light and dark modes.

## Mistake 5: Small Touch Targets

Buttons and icons should be easy to tap.

## Mistake 6: Color as the Only Signal

Combine color with text or icons.

---

# 37. Material Design Learning Roadmap

Recommended order:

```text
1. Material Design Basics
        ↓
2. MaterialTheme
        ↓
3. ColorScheme
        ↓
4. Typography
        ↓
5. Buttons
        ↓
6. Cards
        ↓
7. Text Fields
        ↓
8. TopAppBar
        ↓
9. NavigationBar
        ↓
10. FAB
        ↓
11. Dialog
        ↓
12. Snackbar
        ↓
13. Bottom Sheet
        ↓
14. Dark Theme
        ↓
15. Dynamic Color
        ↓
16. Accessibility
        ↓
17. Adaptive UI
```

---

# 38. Practice Project

Build a Task Manager application.

Use:

```text
MaterialTheme
Scaffold
TopAppBar
NavigationBar
Card
FloatingActionButton
OutlinedTextField
Snackbar
Dialog
Bottom Sheet
```

Suggested screens:

```text
Task List
Add Task
Task Details
Settings
```

---

# 39. Interview Questions

## Q1. What is Material Design?

A design system created by Google for building consistent and user-friendly interfaces.

## Q2. What is Material Design 3?

The modern version of Material Design, including Material You and dynamic color concepts.

## Q3. What is ColorScheme?

A semantic collection of application colors such as primary, secondary, surface, background, and error.

## Q4. What is Scaffold?

A Compose layout structure for organizing common UI areas such as top bars, bottom bars, FABs, snackbars, and content.

## Q5. What is Dynamic Color?

A system that can generate application colors based on device system colors on supported Android versions.

## Q6. Why use MaterialTheme?

It provides consistent colors, typography, and component styling throughout an app.

---

# 40. Quick Revision

```text
Material Design
=
Android UI design system

Material 3
=
Modern Material system

MaterialTheme
=
Application theme

ColorScheme
=
Application colors

Typography
=
Text styles

Scaffold
=
Screen structure

TopAppBar
=
Top screen area

NavigationBar
=
Bottom navigation

FAB
=
Important primary action

Card
=
Groups related content

Snackbar
=
Temporary feedback

Dialog
=
Focused decision

Bottom Sheet
=
Additional actions/content
```

---

# Final Takeaway

Material Design is not only about attractive UI components.

It is about creating a consistent user experience.

```text
Good Material UI
=
Consistency
+
Clear hierarchy
+
Accessibility
+
Responsive design
+
Correct component usage
```

For modern Android development, focus especially on:

```text
Material Design 3
+
Jetpack Compose
+
MaterialTheme
+
ColorScheme
+
Typography
+
Scaffold
+
Adaptive UI
+
Accessibility
```
