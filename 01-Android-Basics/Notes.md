# Android Basics Notes

> Personal notes and quick revision points for **01-Android-Basics**.

---

# 📚 Topics Covered

- ✅ Android Studio
- ✅ SDK Manager
- ✅ Project Structure
- ✅ Gradle
- ✅ AndroidManifest.xml
- ✅ Resources
- ✅ Activity
- ✅ Activity Lifecycle
- ✅ Intent Basics
- ✅ Toast
- ✅ Logcat

---

# 📝 Android Studio

- Official IDE for Android Development.
- Based on IntelliJ IDEA.
- Uses Gradle as the build system.
- Supports Kotlin and Java.
- Provides Emulator, Logcat, Layout Editor, Device Manager, and Profiler.
- Recommended IDE by Google.

**Shortcut**

```
Run App → Shift + F10
```

---

# 📝 SDK Manager

- Downloads Android SDKs.
- Installs Build Tools.
- Manages Emulator Images.
- Updates SDK Components.
- Supports multiple Android versions.

Remember:

```
Compile SDK
        ≠
Minimum SDK
        ≠
Target SDK
```

---

# 📝 Project Structure

Important folders:

```
app/
src/
main/
java/
res/
AndroidManifest.xml
```

Important Gradle files:

```
build.gradle.kts
settings.gradle.kts
gradle.properties
```

---

# 📝 Gradle

Gradle is Android's build automation system.

Responsibilities:

- Compile code
- Download libraries
- Process resources
- Generate APK/AAB
- Run tests

Important values:

```
compileSdk

minSdk

targetSdk

applicationId

versionCode

versionName
```

---

# 📝 AndroidManifest.xml

Stores application configuration.

Common tags:

```
<manifest>

<application>

<activity>

<service>

<receiver>

<provider>

<uses-permission>
```

Launcher Activity:

```
MAIN

+

LAUNCHER
```

---

# 📝 Resources

Resources are stored inside:

```
res/
```

Common folders:

```
layout/

drawable/

mipmap/

values/

menu/

xml/

raw/

font/
```

Frequently used files:

```
strings.xml

colors.xml

themes.xml

dimens.xml
```

---

# 📝 Activity

Activity = One screen of an application (beginner mental model).

Example:

```
Login

↓

Home

↓

Profile

↓

Settings
```

Basic structure:

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }

}
```

---

# 📝 Activity Lifecycle

Order:

```
onCreate()

↓

onStart()

↓

onResume()

↓

Running

↓

onPause()

↓

onStop()

↓

onDestroy()
```

Back Button:

```
Pause

↓

Stop

↓

Destroy
```

Home Button:

```
Pause

↓

Stop
```

---

# 📝 Intent

Intent = Communication between Android components.

Types:

```
Explicit Intent

Implicit Intent
```

Example:

```kotlin
val intent = Intent(
    this,
    SecondActivity::class.java
)

startActivity(intent)
```

Passing data:

```kotlin
intent.putExtra(
    "name",
    "Mazharul"
)
```

Receiving:

```kotlin
intent.getStringExtra("name")
```

---

# 📝 Toast

Quick user feedback.

Example:

```kotlin
Toast.makeText(
    this,
    "Hello",
    Toast.LENGTH_SHORT
).show()
```

Durations:

```
LENGTH_SHORT

LENGTH_LONG
```

---

# 📝 Logcat

Used for debugging.

Most common log methods:

```kotlin
Log.d()

Log.i()

Log.w()

Log.e()
```

Example:

```kotlin
Log.d(
    "MainActivity",
    "App Started"
)
```

---

# 🚀 Frequently Used Classes

| Class | Purpose |
|--------|---------|
| `Activity` | Screen |
| `Intent` | Communication |
| `Toast` | Short message |
| `Log` | Debugging |
| `Bundle` | Store data |
| `R` | Resource access |

---

# 📌 Important Android Terms

| Term | Meaning |
|------|----------|
| APK | Android Package |
| AAB | Android App Bundle |
| SDK | Software Development Kit |
| IDE | Integrated Development Environment |
| XML | User Interface Design |
| Gradle | Build System |
| Manifest | App Configuration |
| Activity | Screen |
| Intent | Messaging Object |
| Resource | External Files |

---

# 🎯 Interview Revision

### Android Studio

- Why do we use Android Studio?
- What tools does it provide?

---

### SDK Manager

- Difference between Compile SDK, Min SDK, and Target SDK?
- Why do we need SDK Manager?

---

### Project Structure

- Purpose of `app/`, `java/`, and `res/` folders.
- Difference between project-level and module-level Gradle files.

---

### Gradle

- What is Gradle?
- What is the purpose of `applicationId`?
- Difference between `versionCode` and `versionName`.

---

### AndroidManifest.xml

- What is AndroidManifest.xml?
- Why is `android:exported` important?
- What is the Launcher Activity?

---

### Resources

- Why use `strings.xml` instead of hardcoded text?
- Difference between `drawable/` and `mipmap/`.
- What is the `R` class?

---

### Activity

- What is an Activity?
- What does `setContentView()` do?
- What is the Activity Back Stack?

---

### Activity Lifecycle

- Explain the lifecycle methods in order.
- Difference between `onPause()` and `onStop()`.
- What happens when the Home button or Back button is pressed?

---

### Intent

- Difference between Explicit and Implicit Intents.
- How do you pass data using Intent Extras?
- Difference between `ACTION_DIAL` and `ACTION_CALL`.

---

### Toast

- What is Toast?
- Difference between Toast and Snackbar.
- Why should user-visible text be stored in `strings.xml`?

---

### Logcat

- What is Logcat?
- What is a TAG?
- Difference between `Log.d()` and `Log.e()`.

---

# 💡 Best Practices

- Use meaningful resource names.
- Store user-visible text in `strings.xml`.
- Keep Activities lightweight.
- Understand the Activity Lifecycle.
- Use Explicit Intents for navigation within your app.
- Use Logcat to debug instead of guessing.
- Keep Gradle files organized.
- Request only the permissions your app needs.
- Follow Material Design guidelines.

---

# ⭐ Week 1 Checklist

- [x] Android Studio
- [x] SDK Manager
- [x] Project Structure
- [x] Gradle
- [x] AndroidManifest.xml
- [x] Resources
- [x] Activity
- [x] Activity Lifecycle
- [x] Intent Basics
- [x] Toast
- [x] Logcat

---

# 📚 Next Topics

- UI Components (TextView, EditText, Button, ImageView)
- Layouts (LinearLayout, ConstraintLayout, FrameLayout)
- ViewBinding
- RecyclerView
- Fragments
- Navigation Component

---

# 🎓 Final Revision (1-Minute Recap)

- **Android Studio** → Official Android IDE.
- **SDK Manager** → Manages Android SDKs and Emulator images.
- **Project Structure** → Organizes source code, resources, and configuration.
- **Gradle** → Builds the app and manages dependencies.
- **AndroidManifest.xml** → Defines app components, permissions, and configuration.
- **Resources** → External files like layouts, strings, colors, and images.
- **Activity** → Represents a screen in the app.
- **Activity Lifecycle** → Describes how an Activity is created, paused, resumed, stopped, and destroyed.
- **Intent** → Enables communication between Android components.
- **Toast** → Displays brief feedback messages.
- **Logcat** → Helps debug apps by showing logs and crash information.