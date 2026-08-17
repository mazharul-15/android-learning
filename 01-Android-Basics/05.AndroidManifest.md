# AndroidManifest.xml

> **AndroidManifest.xml** is one of the most important files in every Android application. It provides essential information about your app to the Android operating system before the app is executed.

---

# 📖 Overview

Every Android application **must** contain exactly one `AndroidManifest.xml` file.

It acts as the **configuration file** for the application.

The Android system reads this file **before** launching your app to understand:

- What the application is called
- Which Activities it contains
- Which permissions it needs
- Which Android version it supports
- Which icon and theme it uses
- Which Services, Broadcast Receivers, and Content Providers are included

Without this file, an Android application **cannot run**.

---

# 🎯 Why is AndroidManifest.xml Important?

The manifest tells Android:

- How to launch your app
- Which screen starts first
- What permissions the app requires
- Which application components exist
- Which hardware/software features are needed
- Which app icon and theme to use

Think of it as the **identity card** or **blueprint** of your Android application.

---

# 📂 Location

```text
app/
└── src/
    └── main/
        └── AndroidManifest.xml
```

---

# 🏗️ Basic Structure

```xml
<?xml version="1.0" encoding="utf-8"?>

<manifest
    xmlns:android="http://schemas.android.com/apk/res/android">

    <application>

        <activity
            android:name=".MainActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN"/>

                <category
                    android:name="android.intent.category.LAUNCHER"/>

            </intent-filter>

        </activity>

    </application>

</manifest>
```

---

# 🧠 Main Elements

The most important elements are:

| Element | Purpose |
|----------|---------|
| `<manifest>` | Root element of the manifest |
| `<application>` | Defines the application |
| `<activity>` | Declares an Activity |
| `<service>` | Declares a Service |
| `<receiver>` | Declares a Broadcast Receiver |
| `<provider>` | Declares a Content Provider |
| `<uses-permission>` | Requests permissions |
| `<uses-feature>` | Declares required hardware/software features |
| `<intent-filter>` | Describes how components respond to intents |

---

# 📄 The `<manifest>` Tag

The root element of every manifest.

Example:

```xml
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android">

</manifest>
```

It defines the XML namespace and wraps the entire configuration.

---

# 📄 The `<application>` Tag

Contains application-wide information.

Example:

```xml
<application
    android:label="@string/app_name"
    android:icon="@mipmap/ic_launcher"
    android:theme="@style/Theme.MyApplication">

</application>
```

Common attributes:

| Attribute | Description |
|------------|-------------|
| `android:label` | App name |
| `android:icon` | Launcher icon |
| `android:roundIcon` | Round launcher icon |
| `android:theme` | Application theme |
| `android:allowBackup` | Enables app backup |
| `android:supportsRtl` | Supports right-to-left layouts |

---

# 📄 The `<activity>` Tag

Registers an Activity.

Example:

```xml
<activity
    android:name=".MainActivity"/>
```

Every Activity in your app must be declared in the manifest (unless automatically handled by certain libraries or features).

Example with attributes:

```xml
<activity
    android:name=".LoginActivity"
    android:exported="false"/>
```

---

# 🚀 Launcher Activity

The Activity that opens when the user taps your app icon.

Example:

```xml
<activity
    android:name=".MainActivity"
    android:exported="true">

    <intent-filter>

        <action
            android:name="android.intent.action.MAIN"/>

        <category
            android:name="android.intent.category.LAUNCHER"/>

    </intent-filter>

</activity>
```

### Explanation

- `MAIN` → Entry point of the application.
- `LAUNCHER` → Displays the app icon in the launcher.
- `exported="true"` → Allows the Android system to launch this Activity.

Without this intent filter, your app won't appear in the device launcher.

---

# 🔐 Permissions

Apps often need permission to access sensitive features.

Permissions are declared using:

```xml
<uses-permission
    android:name="android.permission.INTERNET"/>
```

Examples:

```xml
<uses-permission
    android:name="android.permission.CAMERA"/>

<uses-permission
    android:name="android.permission.ACCESS_FINE_LOCATION"/>

<uses-permission
    android:name="android.permission.READ_CONTACTS"/>
```

> **Note:** Declaring a permission in the manifest does **not** automatically grant it. Many permissions (called **runtime permissions**) must also be requested from the user while the app is running.

---

# 📱 Uses Feature

Specifies required device features.

Example:

```xml
<uses-feature
    android:name="android.hardware.camera"
    android:required="true"/>
```

If `required="true"`, devices without that feature cannot install the app.

---

# ⚙️ Services

Declare background services.

Example:

```xml
<service
    android:name=".MusicService"/>
```

---

# 📡 Broadcast Receivers

Register Broadcast Receivers.

Example:

```xml
<receiver
    android:name=".BootReceiver"/>
```

---

# 📂 Content Providers

Declare Content Providers.

Example:

```xml
<provider
    android:name=".UserProvider"
    android:authorities="com.example.provider"/>
```

---

# 🏷️ Application Attributes

Common attributes inside the `<application>` tag:

```xml
<application
    android:label="@string/app_name"
    android:icon="@mipmap/ic_launcher"
    android:theme="@style/Theme.MyApplication"
    android:allowBackup="true"
    android:supportsRtl="true"/>
```

---

# 📜 Intent Filter

Defines which intents a component can respond to.

Example:

```xml
<intent-filter>

    <action
        android:name="android.intent.action.VIEW"/>

    <category
        android:name="android.intent.category.DEFAULT"/>

</intent-filter>
```

Intent filters are commonly used with:

- Activities
- Services
- Broadcast Receivers

---

# ⚙️ Manifest Merging

Modern Android apps often use multiple libraries.

Each library can include its own manifest.

During the build process, **Gradle merges all manifest files into a single final manifest**.

This is called **Manifest Merging**.

If two manifests define conflicting values, Gradle reports a merge error.

---

# 💡 Best Practices

- Declare only the permissions you actually need.
- Remove unused Activities and Services.
- Keep the manifest organized and readable.
- Use string resources for labels instead of hardcoded text.
- Review permissions before publishing your app.
- Understand each attribute before changing it.

---

# ⚠️ Common Beginner Mistakes

### ❌ Forgetting to declare an Activity

If an Activity isn't declared in the manifest (when required), the app may crash when trying to launch it.

---

### ❌ Requesting unnecessary permissions

Only request permissions that your app truly needs. Extra permissions can reduce user trust.

---

### ❌ Forgetting `android:exported`

For apps targeting Android 12 (API 31) and above, components with an `intent-filter` must explicitly specify `android:exported`.

---

### ❌ Hardcoding the app name

Instead of:

```xml
android:label="My App"
```

Use:

```xml
android:label="@string/app_name"
```

---

### ❌ Declaring duplicate components

Avoid registering the same Activity or Service more than once.

---

# 🎤 Interview Questions

### 1. What is AndroidManifest.xml?

It is the configuration file that provides essential information about an Android application to the Android operating system.

---

### 2. Why is the manifest required?

Android uses it to identify the application's components, permissions, entry point, and configuration before launching the app.

---

### 3. What is the purpose of the `<application>` tag?

It defines application-wide settings such as the app name, icon, theme, and registered components.

---

### 4. What is the purpose of the `<activity>` tag?

It registers an Activity so the Android system knows it exists.

---

### 5. What is an intent filter?

An intent filter defines which intents a component (such as an Activity) can respond to.

---

### 6. Why do we use `android.intent.action.MAIN`?

It marks the main entry point of the application.

---

### 7. What is `android.intent.category.LAUNCHER`?

It tells Android to display the application in the device launcher.

---

### 8. What is `android:exported`?

It specifies whether another application or the Android system can launch or access a component.

---

### 9. Why are permissions declared in the manifest?

To inform Android which protected features or data the app intends to access.

---

### 10. What is Manifest Merging?

It is the process where Gradle combines the manifests from the app and all included libraries into one final manifest during the build.

---

# 📌 Summary

- `AndroidManifest.xml` is the central configuration file of every Android application.
- It declares application components, permissions, features, themes, and the launcher Activity.
- Android reads the manifest before starting the app.
- Gradle merges manifests from the app and libraries during the build process.
- A well-maintained manifest is essential for a stable and secure Android application.

---

# 📚 Related Topics

- Project Structure
- Gradle
- Resources
- Activity
- Intent
- Runtime Permissions
- Services
- Broadcast Receivers
- Content Providers