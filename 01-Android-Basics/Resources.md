# Resources

> **Resources** are external files used by an Android application, such as layouts, strings, colors, images, dimensions, animations, and more. Keeping resources separate from source code makes applications easier to maintain, localize, and scale.

---

# 📖 Overview

In Android, not everything is written in Kotlin.

Things like:

- Text
- Colors
- Images
- Icons
- Layouts
- Animations
- Fonts

are stored inside the **`res/` (resources)** folder.

Instead of hardcoding these values inside your Kotlin code, Android encourages using resource files.

---

# 🎯 Why are Resources Important?

Resources help you:

- Separate UI from business logic
- Support multiple languages
- Easily change colors and themes
- Reuse values across the app
- Improve code readability
- Maintain large projects efficiently

---

# 📂 Resource Folder Location

```text
app/
└── src/
    └── main/
        └── res/
```

Typical structure:

```text
res/
│
├── drawable/
├── layout/
├── mipmap/
├── values/
├── menu/
├── xml/
├── raw/
├── font/
├── anim/
├── animator/
├── color/
└── navigation/
```

---

# 🧠 Resource Types

| Folder | Purpose |
|---------|---------|
| `layout/` | UI layouts |
| `drawable/` | Images, vector drawables, shapes |
| `mipmap/` | Launcher icons |
| `values/` | Strings, colors, themes, dimensions |
| `menu/` | Menu resources |
| `xml/` | Configuration XML files |
| `raw/` | Raw files (MP3, JSON, PDF, etc.) |
| `font/` | Custom fonts |
| `anim/` | View animations |
| `animator/` | Property animations |
| `color/` | Color selector resources |
| `navigation/` | Navigation Graphs |

---

# 📂 layout/

Stores XML files that define the user interface.

Example:

```text
layout/
│
├── activity_main.xml
├── activity_login.xml
├── fragment_home.xml
└── item_user.xml
```

Example:

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello Android"/>
```

Accessed automatically using:

```kotlin
setContentView(R.layout.activity_main)
```

---

# 📂 drawable/

Stores graphical resources.

Examples:

- PNG
- JPG
- WEBP
- Vector Drawables
- Shape XML
- Ripple effects

Example:

```text
drawable/
│
├── logo.png
├── background.xml
├── button_shape.xml
└── ic_person.xml
```

Use in XML:

```xml
android:src="@drawable/logo"
```

Use in Kotlin:

```kotlin
imageView.setImageResource(R.drawable.logo)
```

---

# 📂 mipmap/

Contains launcher icons.

Example:

```text
mipmap/
│
├── ic_launcher.webp
└── ic_launcher_round.webp
```

Launcher icons are placed here instead of `drawable/` because Android can provide optimized versions for different device densities.

---

# 📂 values/

The most frequently used resource folder.

Contains:

```text
values/
│
├── strings.xml
├── colors.xml
├── themes.xml
├── dimens.xml
├── styles.xml
└── arrays.xml
```

---

## strings.xml

Stores all user-visible text.

Example:

```xml
<resources>

    <string name="app_name">My Application</string>

    <string name="login">Login</string>

</resources>
```

Use in XML:

```xml
android:text="@string/login"
```

Use in Kotlin:

```kotlin
textView.text = getString(R.string.login)
```

### Why use strings.xml?

- Easy localization
- Reusable
- Avoid hardcoded text

---

## colors.xml

Stores application colors.

Example:

```xml
<resources>

    <color name="primary">#6200EE</color>

    <color name="white">#FFFFFF</color>

</resources>
```

Use:

```xml
android:textColor="@color/white"
```

---

## themes.xml

Defines the application's theme.

Example:

```xml
<style
    name="Theme.MyApplication"
    parent="Theme.Material3.DayNight.NoActionBar"/>
```

Controls:

- Primary colors
- Typography
- Status bar
- Navigation bar
- Material Design appearance

---

## dimens.xml

Stores reusable dimensions.

Example:

```xml
<resources>

    <dimen name="padding_large">16dp</dimen>

</resources>
```

Use:

```xml
android:padding="@dimen/padding_large"
```

---

## styles.xml

Defines reusable styles.

Example:

```xml
<style name="TitleText">

    <item name="android:textSize">20sp</item>

    <item name="android:textStyle">bold</item>

</style>
```

---

## arrays.xml

Stores arrays.

Example:

```xml
<string-array name="countries">

    <item>Bangladesh</item>

    <item>Germany</item>

</string-array>
```

---

# 📂 menu/

Contains menu XML files.

Example:

```text
menu/
└── main_menu.xml
```

Used for:

- Toolbar menus
- Popup menus
- Navigation menus

---

# 📂 xml/

Contains miscellaneous configuration files.

Examples:

- Backup rules
- Network Security Configuration
- File Provider
- Preferences

---

# 📂 raw/

Stores raw files without Android processing.

Examples:

```text
raw/
│
├── music.mp3
├── data.json
├── sample.pdf
└── intro.mp4
```

Access example:

```kotlin
resources.openRawResource(R.raw.data)
```

---

# 📂 font/

Stores custom fonts.

Example:

```text
font/
└── roboto_bold.ttf
```

Use in XML:

```xml
android:fontFamily="@font/roboto_bold"
```

---

# 📂 anim/

Stores traditional view animations.

Examples:

```text
fade_in.xml
slide_up.xml
rotate.xml
```

---

# 📂 animator/

Stores property animations.

Used with:

- ObjectAnimator
- AnimatorSet

---

# 📂 color/

Stores color state lists.

Example:

```xml
<selector>

    <item
        android:color="#FF0000"
        android:state_pressed="true"/>

</selector>
```

Useful for buttons, text, and icons with different states.

---

# 📂 navigation/

Contains Navigation Graph files used by Jetpack Navigation.

Example:

```text
navigation/
└── nav_graph.xml
```

---

# 🏷️ The R Class

Whenever resources are added, Android automatically generates an `R` class.

Example:

```kotlin
R.layout.activity_main

R.string.app_name

R.color.primary

R.drawable.logo
```

The `R` class provides unique IDs for all resources.

> **Note:** The `R` class is generated automatically. Never edit it manually.

---

# 📏 Resource Qualifiers

Android supports different resources for different devices.

Examples:

```text
layout-land/
```

Landscape layouts.

```text
values-fr/
```

French strings.

```text
drawable-hdpi/
```

High-density images.

```text
values-night/
```

Dark mode colors.

This allows Android to automatically choose the most appropriate resource.

---

# 💡 Best Practices

- Store all user-visible text in `strings.xml`.
- Never hardcode colors.
- Use vector drawables whenever possible.
- Reuse dimensions using `dimens.xml`.
- Organize resources with meaningful names.
- Remove unused resources to reduce app size.
- Use resource qualifiers for localization and different screen sizes.

---

# ⚠️ Common Beginner Mistakes

### ❌ Hardcoding Strings

Instead of:

```kotlin
textView.text = "Login"
```

Use:

```kotlin
textView.text = getString(R.string.login)
```

---

### ❌ Hardcoding Colors

Instead of:

```xml
android:textColor="#FF0000"
```

Use:

```xml
android:textColor="@color/primary"
```

---

### ❌ Placing Launcher Icons in drawable/

Launcher icons belong in:

```text
mipmap/
```

---

### ❌ Editing the R Class

The `R` class is generated automatically.

Never modify it manually.

---

### ❌ Using Spaces or Capital Letters in Resource Names

Incorrect:

```text
Login Button.png
```

Correct:

```text
login_button.png
```

Resource names should:

- Use lowercase letters
- Use numbers if needed
- Separate words with underscores (`_`)

---

# 🎤 Interview Questions

### 1. What are Android Resources?

Resources are external files such as layouts, strings, colors, images, and animations used by an Android application.

---

### 2. Why do we use `strings.xml`?

To store user-visible text separately from code, making localization and maintenance easier.

---

### 3. What is the difference between `drawable` and `mipmap`?

- `drawable/` stores general graphical resources.
- `mipmap/` stores launcher icons.

---

### 4. What is the `R` class?

The `R` class is an automatically generated class that provides references (IDs) to all application resources.

---

### 5. Why shouldn't we hardcode strings?

Hardcoding makes localization difficult and reduces maintainability. Using `strings.xml` allows reuse and translation.

---

### 6. What are Resource Qualifiers?

Resource qualifiers allow Android to select different resources based on device configuration, such as language, screen size, orientation, or theme.

---

### 7. What is stored inside the `raw` folder?

Files such as MP3, JSON, PDF, and other raw assets that Android does not process.

---

# 📌 Summary

- The `res/` folder contains all non-code resources.
- Resources are accessed using the automatically generated `R` class.
- `strings.xml`, `colors.xml`, and `themes.xml` are among the most commonly used resource files.
- Resource qualifiers enable support for multiple languages, screen sizes, and device configurations.
- Organizing resources properly improves maintainability, readability, and scalability.

---

# 📚 Related Topics

- Project Structure
- AndroidManifest.xml
- Activity
- Layouts (XML)
- Themes & Material Design
- Drawable Resources
- Localization