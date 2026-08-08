# EditText - Quick Notes

## 📖 What is EditText?

`EditText` is an Android UI component that allows the user to **enter and edit text**.

```text
TextView → Display text
EditText → Receive user input
```

---

## 🏗️ Basic XML

```xml
<EditText
    android:id="@+id/etName"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/enter_name"
    android:textSize="16sp" />
```

---

## 📏 Text Size

Recommended practical values:

| Purpose | Size |
|---|---:|
| Compact input | `14sp` |
| Normal input | `16sp` |
| Large input | `18sp` |

### Default recommendation

```text
EditText → 16sp
```

> `16sp` is a practical baseline, not a strict Android requirement.

---

## 📝 hint

Shows placeholder text when the field is empty.

```xml
android:hint="Enter your name"
```

Remember:

```text
hint → Placeholder
text → Actual content
```

---

## 🔤 text

Sets the actual text inside the field.

```xml
android:text="Mazharul Islam"
```

Usually, don't hardcode user-visible text. Prefer `strings.xml`.

---

## ⌨️ inputType

Defines what type of input the user should enter.

### Normal text

```xml
android:inputType="text"
```

### Name

```xml
android:inputType="textPersonName"
```

### Email

```xml
android:inputType="textEmailAddress"
```

### Password

```xml
android:inputType="textPassword"
```

### Phone

```xml
android:inputType="phone"
```

### Integer number

```xml
android:inputType="number"
```

### Decimal number

```xml
android:inputType="numberDecimal"
```

### Multi-line

```xml
android:inputType="textMultiLine"
```

### Quick memory

```text
Name      → textPersonName
Email     → textEmailAddress
Password  → textPassword
Phone     → phone
Number    → number
Decimal   → numberDecimal
Multi-line → textMultiLine
```

---

## 🎨 Text Color

```xml
android:textColor="@color/text_primary"
```

---

## 🎨 Hint Color

```xml
android:textColorHint="@color/text_secondary"
```

---

## 📐 Padding

Space **inside** the EditText.

```xml
android:padding="16dp"
```

```text
padding → inside
```

---

## ↔️ Margin

Space **outside** the EditText.

```xml
android:layout_margin="16dp"
```

```text
margin → outside
```

---

## 📄 maxLength

Limits the number of characters.

```xml
android:maxLength="20"
```

Example:

```text
Maximum 20 characters
```

---

## 📄 maxLines

Limits the number of displayed lines.

```xml
android:maxLines="3"
```

For a normal single-line input:

```xml
android:maxLines="1"
```

---

## 📍 gravity

Controls the position of text **inside EditText**.

```xml
android:gravity="center_vertical"
```

For multi-line input:

```xml
android:gravity="top|start"
```

---

## 💻 Kotlin - ViewBinding

### Get text

```kotlin
val name = binding.etName.text.toString()
```

Important:

```text
EditText.text
→ Editable

.toString()
→ String
```

---

### Set text

```kotlin
binding.etName.setText("Mazharul Islam")
```

---

### Clear text

```kotlin
binding.etName.text.clear()
```

---

## 🔍 Check Input

### Check empty

```kotlin
val name = binding.etName.text.toString()

if (name.isEmpty()) {
    // Empty
}
```

### Check blank

```kotlin
if (name.isBlank()) {
    // Empty or whitespace only
}
```

Remember:

```text
isEmpty() → No characters

isBlank() → Empty or whitespace
```

For form validation, `isBlank()` is often more useful.

---

## 🔐 Password

```xml
<EditText
    android:id="@+id/etPassword"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/password"
    android:inputType="textPassword"
    android:textSize="16sp" />
```

Never log passwords:

```kotlin
// ❌ Don't do this
Log.d("PASSWORD", binding.etPassword.text.toString())
```

---

## 📧 Email

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

## 📱 Phone

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

## 🆔 Naming Convention

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

## 📐 dp vs sp

```text
dp → Dimensions / spacing

sp → Text size
```

Example:

```xml
android:padding="16dp"
android:textSize="16sp"
```

---

## 🆚 TextView vs EditText

| Component | Purpose |
|---|---|
| `TextView` | Display text |
| `EditText` | Receive user input |

---

## ⚠️ Common Mistakes

### ❌ Wrong

```xml
android:textSize="16dp"
```

### ✅ Correct

```xml
android:textSize="16sp"
```

---

### ❌ Wrong input type

```xml
android:inputType="text"
```

for an email field.

### ✅ Better

```xml
android:inputType="textEmailAddress"
```

---

### ❌ Forgetting `.toString()`

```kotlin
val name = binding.etName.text
```

### ✅ When a String is needed

```kotlin
val name = binding.etName.text.toString()
```

---

### ❌ Confusing hint with input

```text
hint → Placeholder
text → Actual user input
```

---

# 🎯 Must Remember

```text
EditText
→ User input

Normal text size
→ 16sp

dp
→ Dimensions

sp
→ Text size

hint
→ Placeholder

inputType
→ Expected input type

maxLength
→ Character limit

maxLines
→ Line limit

padding
→ Inside

margin
→ Outside
```

---

# 💻 Most Important Kotlin Code

```kotlin
// Get input
val name = binding.etName.text.toString()

// Set input
binding.etName.setText("Mazharul Islam")

// Clear input
binding.etName.text.clear()

// Validate
if (name.isBlank()) {
    // Invalid input
}
```

---

# ⭐ Final Checklist

- [ ] EditText receives user input
- [ ] Normal text size is `16sp`
- [ ] Understand `hint`
- [ ] Understand `inputType`
- [ ] Know email input
- [ ] Know password input
- [ ] Know phone input
- [ ] Know number input
- [ ] Know `maxLength`
- [ ] Know `maxLines`
- [ ] Know `gravity`
- [ ] Know `dp` vs `sp`
- [ ] Know how to get text
- [ ] Know how to set text
- [ ] Know how to clear text
- [ ] Know how to validate input
- [ ] Know EditText naming convention