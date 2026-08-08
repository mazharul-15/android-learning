# EditText

> `EditText` is an Android UI component that allows the user to enter and edit text.

---

# 📖 What is EditText?

`EditText` is used whenever the application needs input from the user.

Common examples:

- Name
- Email
- Password
- Phone number
- Search
- Address
- Username
- Comments
- Messages

Example:

```text
Name
┌──────────────────────────────┐
│ Enter your name              │
└──────────────────────────────┘
```

---

# 🏗️ Basic EditText

```xml
<EditText
    android:id="@+id/etName"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/enter_name"
    android:textSize="16sp" />
```

---

# 📏 Standard Text Size

For `EditText`, the **actual entered text and hint text** are commonly kept around:

```text
16sp
```

A practical scale:

| Purpose | Text Size |
|---|---:|
| Large input / special field | `18sp` |
| Normal input | `16sp` |
| Compact input | `14sp` |

### Recommended default

```xml
android:textSize="16sp"
```

For most forms:

```text
EditText → 16sp
```

> These are practical baseline values, not strict Android requirements. In production applications, use the typography/style system of your design system or Material components.

---

# 🧠 Important Attributes

```xml
android:id
android:layout_width
android:layout_height
android:hint
android:text
android:textSize
android:textColor
android:textColorHint
android:inputType
android:maxLength
android:maxLines
android:singleLine
android:gravity
android:padding
android:layout_margin
```

---

# 📝 hint

`hint` displays placeholder text when the `EditText` is empty.

```xml
android:hint="Enter your name"
```

Example:

```text
┌──────────────────────────────┐
│ Enter your name              │
└──────────────────────────────┘
```

When the user types:

```text
┌──────────────────────────────┐
│ Mazharul Islam               │
└──────────────────────────────┘
```

The hint disappears.

---

# 🔤 hint vs text

This is important.

### `hint`

```xml
android:hint="Enter your name"
```

Used as a placeholder.

### `text`

```xml
android:text="Mazharul Islam"
```

Sets actual text in the field.

Remember:

```text
hint
→ Placeholder

text
→ Actual content
```

---

# 🔐 inputType

`inputType` tells Android what kind of input the field expects.

---

## Normal Text

```xml
android:inputType="text"
```

---

## Name / Person Name

```xml
android:inputType="textPersonName"
```

Useful for:

```text
First name
Last name
Full name
```

---

## Email

```xml
android:inputType="textEmailAddress"
```

Android can provide an email-friendly keyboard.

---

## Password

```xml
android:inputType="textPassword"
```

Example:

```xml
<EditText
    android:id="@+id/etPassword"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/password"
    android:inputType="textPassword"
    android:textSize="16sp" />
```

---

## Phone Number

```xml
android:inputType="phone"
```

---

## Integer Number

```xml
android:inputType="number"
```

---

## Decimal Number

```xml
android:inputType="numberDecimal"
```

---

## Multi-line Text

```xml
android:inputType="textMultiLine"
```

Useful for:

```text
Comments
Description
Address
Message
```

---

# 📐 inputType vs keyboard

`inputType` affects the expected input and helps Android determine an appropriate keyboard/input behavior.

For example:

```xml
android:inputType="textEmailAddress"
```

is more appropriate for an email field than:

```xml
android:inputType="text"
```

---

# 📏 maxLength

Limits the number of characters.

```xml
android:maxLength="20"
```

For example:

```text
Maximum 20 characters
```

---

# 📄 maxLines

Controls the maximum number of displayed lines.

```xml
android:maxLines="3"
```

For a normal single-line input:

```xml
android:maxLines="1"
```

---

# ↔️ singleLine

Makes the input a single line.

```xml
android:singleLine="true"
```

For modern Android development, it is often better to configure the desired input behavior through `inputType` and related attributes rather than relying on `singleLine` alone.

---

# 📍 gravity

Controls the position of text **inside the EditText**.

Example:

```xml
android:gravity="center_vertical"
```

For a multi-line field:

```xml
android:gravity="top|start"
```

---

# 🎨 Text Color

```xml
android:textColor="@color/text_primary"
```

Example:

```xml
<color name="text_primary">#212121</color>
```

---

# 🎨 Hint Color

You can separately control the hint color:

```xml
android:textColorHint="@color/text_secondary"
```

Example:

```xml
<color name="text_secondary">#757575</color>
```

---

# 📐 Padding

Padding creates space inside the EditText.

```xml
android:padding="16dp"
```

You can also control each direction:

```xml
android:paddingStart="16dp"
android:paddingEnd="16dp"
android:paddingTop="12dp"
android:paddingBottom="12dp"
```

---

# ↔️ Margin

Margin creates space outside the EditText.

```xml
android:layout_marginTop="16dp"
```

Remember:

```text
padding
→ inside

margin
→ outside
```

---

# 🌍 String Resources

Avoid hardcoding user-visible text.

### ❌ Avoid

```xml
android:hint="Enter your name"
```

### ✅ Prefer

`res/values/strings.xml`

```xml
<string name="enter_name">Enter your name</string>
```

Then:

```xml
android:hint="@string/enter_name"
```

---

# 💻 Getting User Input

Using ViewBinding:

```kotlin
val name = binding.etName.text.toString()
```

`EditText.text` returns an `Editable`, so use:

```kotlin
.toString()
```

when you need a `String`.

---

# 🧹 Clearing Input

```kotlin
binding.etName.text.clear()
```

---

# ✏️ Setting Input Programmatically

```kotlin
binding.etName.setText("Mazharul Islam")
```

---

# 🔎 Checking Empty Input

```kotlin
val name = binding.etName.text.toString()

if (name.isEmpty()) {
    // Empty
}
```

For form validation, `isBlank()` is often more useful:

```kotlin
if (name.isBlank()) {
    // Empty or only whitespace
}
```

Remember:

```text
isEmpty()
→ no characters

isBlank()
→ no meaningful characters / whitespace only
```

---

# 🔐 Password Input

Example:

```xml
<EditText
    android:id="@+id/etPassword"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/password"
    android:inputType="textPassword"
    android:textSize="16sp" />
```

Important:

- Don't log passwords.
- Don't store passwords unnecessarily.
- Use secure authentication mechanisms for real applications.

---

# 📱 Email Input

```xml
<EditText
    android:id="@+id/etEmail"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/email"
    android:inputType="textEmailAddress"
    android:textSize="16sp" />
```

---

# 📞 Phone Input

```xml
<EditText
    android:id="@+id/etPhone"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/phone"
    android:inputType="phone"
    android:textSize="16sp" />
```

---

# 📝 Multi-line Input

Example for comments:

```xml
<EditText
    android:id="@+id/etComment"
    android:layout_width="match_parent"
    android:layout_height="120dp"
    android:hint="@string/write_comment"
    android:inputType="textMultiLine"
    android:gravity="top|start"
    android:textSize="16sp" />
```

---

# 🧩 Complete Form Example

```xml
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
        android:text="@string/student_registration"
        android:textSize="24sp"
        android:textStyle="bold" />

    <EditText
        android:id="@+id/etName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:hint="@string/enter_name"
        android:inputType="textPersonName"
        android:textSize="16sp" />

    <EditText
        android:id="@+id/etEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:hint="@string/enter_email"
        android:inputType="textEmailAddress"
        android:textSize="16sp" />

    <EditText
        android:id="@+id/etPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:hint="@string/enter_password"
        android:inputType="textPassword"
        android:textSize="16sp" />

</LinearLayout>
```

---

# 🆔 Naming Convention

Use:

```text
etName
etEmail
etPassword
etPhone
etAddress
etComment
etSearch
```

Pattern:

```text
et + MeaningfulName
```

Avoid:

```text
editText1
editText2
editText3
```

---

# 💡 Best Practices

### 1. Use appropriate `inputType`

```text
Email     → textEmailAddress
Password  → textPassword
Phone     → phone
Number    → number
Decimal   → numberDecimal
```

---

### 2. Use `16sp` as the normal baseline

```xml
android:textSize="16sp"
```

Use `14sp` or `18sp` when the design specifically requires it.

---

### 3. Use meaningful hints

Good:

```text
Enter your email
Enter your phone number
Enter your password
```

Avoid vague hints:

```text
Enter something
Input
Type here
```

---

### 4. Validate user input

Never assume user input is valid.

```kotlin
val email = binding.etEmail.text.toString()

if (email.isBlank()) {
    // Show validation message
}
```

---

### 5. Don't log sensitive input

Never do this:

```kotlin
Log.d("PASSWORD", binding.etPassword.text.toString())
```

Especially for passwords, authentication tokens, and other sensitive information.

---

# ⚠️ Common Mistakes

## ❌ Using `dp` for text size

```xml
android:textSize="16dp"
```

### ✅ Correct

```xml
android:textSize="16sp"
```

---

## ❌ Using generic input type for everything

```xml
android:inputType="text"
```

for an email field is less appropriate.

### ✅ Better

```xml
android:inputType="textEmailAddress"
```

---

## ❌ Forgetting `.toString()`

```kotlin
val name = binding.etName.text
```

When a `String` is required:

```kotlin
val name = binding.etName.text.toString()
```

---

## ❌ Treating hint as actual data

```text
hint
→ placeholder

text
→ actual input
```

---

# 🎤 Interview Questions

### 1. What is EditText?

`EditText` is an Android UI component that allows users to enter and edit text.

---

### 2. What is the difference between TextView and EditText?

```text
TextView
→ Displays text

EditText
→ Accepts user input
```

---

### 3. What is `inputType`?

It specifies the expected type of user input and influences input behavior and keyboard configuration.

---

### 4. How do you get text from EditText?

```kotlin
val value = binding.etName.text.toString()
```

---

### 5. What is the difference between `hint` and `text`?

```text
hint
→ Placeholder

text
→ Actual content
```

---

### 6. How do you clear EditText?

```kotlin
binding.etName.text.clear()
```

---

### 7. How do you limit input length?

```xml
android:maxLength="20"
```

---

### 8. What text size should normally be used for EditText?

A practical baseline is:

```text
16sp
```

But the final value should follow the app's typography/design system.

---

# 📌 Quick Reference

```text
EditText
    ↓
Accept user input

Normal text
    ↓
16sp

Compact
    ↓
14sp

Large
    ↓
18sp
```

### Important attributes

```text
hint
text
textSize
textColor
textColorHint
inputType
maxLength
maxLines
gravity
padding
layout_margin
```

### Common input types

```text
text
textPersonName
textEmailAddress
textPassword
phone
number
numberDecimal
textMultiLine
```

### Kotlin

```kotlin
// Get
val value = binding.etName.text.toString()

// Set
binding.etName.setText("Mazharul")

// Clear
binding.etName.text.clear()
```

---

# ⭐ Final Checklist

- [ ] Understand EditText
- [ ] Know `hint`
- [ ] Know `text`
- [ ] Know `textSize`
- [ ] Use `16sp` as the normal baseline
- [ ] Understand `inputType`
- [ ] Know email input
- [ ] Know password input
- [ ] Know phone input
- [ ] Know number input
- [ ] Know multi-line input
- [ ] Know `maxLength`
- [ ] Know `maxLines`
- [ ] Know `gravity`
- [ ] Know how to read input
- [ ] Know how to clear input
- [ ] Know how to validate input
- [ ] Know EditText naming conventions