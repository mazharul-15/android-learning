# Project Structure

> Every Android project follows a standard directory structure. Understanding each folder and file is essential because you'll interact with them daily while building Android applications.

---

# 📖 Overview

When you create a new Android project in Android Studio, it automatically generates a set of folders and files.

Each folder has a specific purpose, such as storing:

- Source code
- User interface layouts
- Images
- App configuration
- Dependencies
- Build scripts

A good understanding of the project structure helps you quickly find files, debug issues, and maintain large applications.

---

# 🎯 Why is Project Structure Important?

Understanding the project structure helps you:

- Navigate projects efficiently
- Know where to write code
- Organize resources correctly
- Debug build errors
- Manage dependencies
- Collaborate with other developers

---

# 🏗️ Typical Android Project Structure

```text
MyApplication/
│
├── .gradle/
├── .idea/
├── app/
│   ├── build/
│   ├── src/
│   │   ├── androidTest/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   │
│   ├── build.gradle.kts
│   └── proguard-rules.pro
│
├── gradle/
│
├── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
└── local.properties
```

> **Note:** Android Studio's **Android View** groups files to make navigation easier. The actual file structure on disk is closer to the tree shown above.

---

# 📂 Root-Level Files and Folders

## 1. `.gradle/`

Contains Gradle's cached files and build information.

- Automatically generated
- Speeds up future builds
- Do **not** edit manually

---

## 2. `.idea/`

Stores Android Studio project settings.

Examples:

- Editor settings
- Run configurations
- Code style preferences

Usually excluded from Git unless team settings need to be shared.

---

## 3. `app/`

This is the **main module** of your Android application.

Almost everything you'll work on is inside this folder.

It contains:

- Source code
- Resources
- Manifest
- Build configuration

---

## 4. `gradle/`

Contains the Gradle Wrapper files.

Example:

```text
gradle/
└── wrapper/
```

The wrapper ensures every developer uses the same Gradle version.

---

## 5. `build.gradle.kts` (Project Level)

Defines project-wide build configuration.

Examples:

- Plugin versions
- Repository configuration

This file usually changes less often than the module-level Gradle file.

---

## 6. `settings.gradle.kts`

Lists all modules included in the project.

Example:

```kotlin
include(":app")
```

---

## 7. `gradle.properties`

Stores Gradle configuration options.

Examples:

- JVM memory settings
- Build optimization flags

---

## 8. `local.properties`

Contains the local Android SDK path.

Example:

```properties
sdk.dir=C\:\\Users\\Username\\AppData\\Local\\Android\\Sdk
```

⚠️ This file is machine-specific and should **not** be committed to Git.

---

## 9. `gradlew` and `gradlew.bat`

These are the **Gradle Wrapper** scripts.

They allow you to build the project without installing Gradle manually.

Example:

```bash
./gradlew build
```

---

# 📂 The `app` Module

Most of your Android development happens inside the `app` module.

```text
app/
│
├── build/
├── src/
├── build.gradle.kts
└── proguard-rules.pro
```

---

# 📂 `src/`

Contains the application's source code and resources.

```text
src/
│
├── main/
├── test/
└── androidTest/
```

---

# 📂 `main/`

The most important source set.

```text
main/
│
├── java/
├── res/
└── AndroidManifest.xml
```

Everything required for your app is located here.

---

# 📂 `java/` (or `kotlin/`)

Contains your Kotlin (or Java) source code.

Example:

```text
java/
└── com.example.myapplication/
    ├── MainActivity.kt
    ├── LoginActivity.kt
    └── User.kt
```

Here you'll write:

- Activities
- Fragments
- ViewModels
- Adapters
- Data classes
- Business logic

---

# 📂 `res/` (Resources)

Stores all non-code resources.

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
└── anim/
```

---

## `layout/`

Contains XML layout files.

Example:

```text
activity_main.xml
activity_login.xml
fragment_home.xml
```

---

## `drawable/`

Stores drawable resources.

Examples:

- PNG
- JPG
- Vector Drawables
- Shape XML

---

## `mipmap/`

Contains launcher icons.

Example:

```text
ic_launcher.webp
```

---

## `values/`

Stores XML configuration values.

Common files:

```text
strings.xml
colors.xml
themes.xml
dimens.xml
```

---

## `menu/`

Contains menu resource files.

Example:

```text
main_menu.xml
```

---

## `xml/`

Stores miscellaneous XML configuration files.

Examples:

- Backup rules
- Network security configuration
- Preferences

---

## `raw/`

Stores raw files.

Examples:

- MP3
- JSON
- PDF
- Text files

---

## `font/`

Contains custom fonts.

---

## `anim/`

Contains animation resources.

---

# 📂 AndroidManifest.xml

Every Android application has **one manifest file**.

Purpose:

- Declare Activities
- Register Services
- Register Broadcast Receivers
- Define Permissions
- Set Application Icon
- Set Theme

Example:

```xml
<application>

    <activity
        android:name=".MainActivity" />

</application>
```

You'll learn this file in detail in **AndroidManifest.md**.

---

# 📂 `build/`

Generated automatically during compilation.

Contains:

- APK files
- Compiled classes
- Intermediate files

⚠️ Never edit this folder manually.

---

# 📂 `test/`

Contains local unit tests.

Example:

```text
ExampleUnitTest.kt
```

Runs on your computer (JVM), not on an Android device.

---

# 📂 `androidTest/`

Contains instrumentation tests.

These tests run on:

- Real devices
- Android emulators

---

# 📄 `build.gradle.kts` (Module Level)

The most frequently edited Gradle file.

It defines:

- SDK versions
- Application ID
- Dependencies
- Build types
- Version information

Example:

```kotlin
android {
    namespace = "com.example.myapplication"

    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 36
    }
}
```

---

# 📄 `proguard-rules.pro`

Used when shrinking and obfuscating code for release builds.

Benefits:

- Smaller APK
- Improved security
- Code optimization

---

# 🧠 Android View vs Project View

Android Studio offers multiple project views.

## Android View

- Simplified
- Groups related files
- Best for beginners

## Project View

- Shows the actual directory structure
- Useful for advanced users

---

# 💡 Best Practices

- Keep Activities, Fragments, and ViewModels organized in packages.
- Store all user-visible text in `strings.xml`.
- Place images in the appropriate `drawable` folders.
- Never hardcode colors; use `colors.xml`.
- Avoid editing generated files inside `build/`.
- Exclude `local.properties` and generated folders from Git.

---

# ⚠️ Common Beginner Mistakes

### ❌ Putting images in the wrong folder

Use:

```
res/drawable/
```

not the project root.

---

### ❌ Hardcoding strings

Instead of:

```kotlin
textView.text = "Login"
```

Use:

```xml
<!-- strings.xml -->
<string name="login">Login</string>
```

Then reference it in code or XML.

---

### ❌ Editing generated files

Files inside:

```
build/
```

are regenerated automatically.

---

### ❌ Committing `local.properties`

This file contains your local SDK path and should not be shared.

---

# 🎤 Interview Questions

### 1. What is the purpose of the `app` module?

It contains the application's source code, resources, manifest, and module-specific build configuration.

---

### 2. What is stored inside the `res` folder?

Non-code resources such as layouts, images, strings, colors, fonts, menus, and animations.

---

### 3. What is the difference between `drawable` and `mipmap`?

- `drawable/` stores general images and drawable resources.
- `mipmap/` is primarily used for launcher icons.

---

### 4. Why is `local.properties` not committed to Git?

It contains machine-specific information, such as the local Android SDK path.

---

### 5. What is the purpose of `AndroidManifest.xml`?

It declares essential application information, including components, permissions, themes, and metadata.

---

### 6. What is the difference between `test` and `androidTest`?

- `test/` contains local unit tests that run on the JVM.
- `androidTest/` contains instrumentation tests that run on an Android device or emulator.

---

### 7. What is the purpose of the module-level `build.gradle.kts` file?

It configures the Android module, including SDK versions, dependencies, application ID, and build types.

---

# 📌 Summary

- Every Android project follows a standard directory structure.
- The `app` module contains most of your application code.
- The `res` folder stores all non-code resources.
- `AndroidManifest.xml` defines the application's components and configuration.
- Gradle files manage the build process and dependencies.
- Understanding the project structure is essential for efficient Android development.

---

# 📚 Related Topics

- Android Studio
- SDK Manager
- Gradle
- AndroidManifest.xml
- Resources
- Activity