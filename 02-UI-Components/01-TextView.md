# TextView

> `TextView` is an Android UI component used to display text to the user.

---

# 📖 Overview

`TextView` is one of the most commonly used Android UI components.

It can be used for:

- Screen headings
- Page titles
- Section titles
- Labels
- Body text
- Descriptions
- Captions
- Supporting text
- Status messages
- Information text

Example:

```text
┌─────────────────────────────┐
│                             │
│        Student Profile      │ ← Heading
│                             │
│        Personal Information │ ← Section Title
│                             │
│ Name                        │ ← Label
│ Mazharul Islam              │ ← Body / Value
│                             │
│ University                  │ ← Label
│ HSTU                        │ ← Body / Value
│                             │
│ Last updated: Today         │ ← Caption
│                             │
└─────────────────────────────┘
```

---

# 🧠 What is TextView?

`TextView` is a subclass of `View` used to display text.

Basic XML:

```xml
<TextView
    android:id="@+id/tvTitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Student Profile" />
```

---

# 🏗️ Basic TextView

```xml
<TextView
    android:id="@+id/tvName"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Mazharul Islam"
    android:textSize="16sp" />
```

### Important attributes

```text
android:id
android:layout_width
android:layout_height
android:text
android:textSize
android:textColor
android:textStyle
android:gravity
android:padding
android:layout_margin
```

---

# 📏 Standard Text Sizes

Android does not force a single size for "heading", "title", or "body" text.

For your learning projects, use this practical baseline:

| Text Type | Recommended Size |
|---|---:|
| Large Screen Heading | `28sp` |
| Screen Heading | `24sp` |
| Page Title | `20sp` |
| Section Title | `18sp` |
| Body Text | `16sp` |
| Secondary Body Text | `14sp` |
| Label | `14sp` |
| Caption / Supporting Text | `12sp` |

### Quick Reference

```text
28sp → Large Heading
24sp → Screen Heading
20sp → Page Title
18sp → Section Title
16sp → Body
14sp → Label / Secondary
12sp → Caption
```

> These are practical starting values, not strict Android rules. For production apps, Material typography styles are preferable to manually choosing sizes everywhere.

---

# 🏷️ Text Hierarchy

Good UI design uses different text sizes and weights to create a visual hierarchy.

Example:

```text
Student Profile        24sp  Bold
Personal Information   18sp  Bold
Name                   14sp  Medium
Mazharul Islam         16sp  Regular
HSTU                   16sp  Regular
Last updated           12sp  Regular
```

The user should be able to understand the importance of information by looking at the screen.

---

# 1️⃣ Screen Heading

A screen heading identifies the main screen.

Example:

```xml
<TextView
    android:id="@+id/tvScreenHeading"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Student Profile"
    android:textSize="24sp"
    android:textStyle="bold" />
```

Recommended baseline:

```text
Size  → 24sp
Style → bold
```

For a larger hero-style heading:

```text
28sp
```

---

# 2️⃣ Page Title

A page title is slightly smaller than the main screen heading.

```xml
<TextView
    android:id="@+id/tvPageTitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Personal Information"
    android:textSize="20sp"
    android:textStyle="bold" />
```

Recommended baseline:

```text
Size → 20sp
```

---

# 3️⃣ Section Title

Used to divide content into sections.

```xml
<TextView
    android:id="@+id/tvSectionTitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Academic Information"
    android:textSize="18sp"
    android:textStyle="bold" />
```

Recommended baseline:

```text
Size → 18sp
Style → bold
```

---

# 4️⃣ Body Text

Body text is normal readable content.

```xml
<TextView
    android:id="@+id/tvDescription"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="This application helps students manage their academic information."
    android:textSize="16sp" />
```

Recommended baseline:

```text
Size → 16sp
Style → regular
```

---

# 5️⃣ Label

A label identifies the information that follows it.

Example:

```xml
<TextView
    android:id="@+id/tvNameLabel"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Name"
    android:textSize="14sp" />
```

Recommended baseline:

```text
Size → 14sp
```

Example:

```text
Name
Mazharul Islam
```

Here:

```text
Name          → Label
Mazharul      → Value
```

---

# 6️⃣ Value / Data Text

The actual information can use normal body size.

```xml
<TextView
    android:id="@+id/tvName"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Mazharul Islam"
    android:textSize="16sp" />
```

Recommended baseline:

```text
Size → 16sp
```

---

# 7️⃣ Caption

Captions provide secondary information.

Example:

```xml
<TextView
    android:id="@+id/tvLastUpdated"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Last updated: Today"
    android:textSize="12sp" />
```

Recommended baseline:

```text
Size → 12sp
```

Use captions for:

- Dates
- Timestamps
- Additional information
- Supporting text

---

# 8️⃣ Secondary Text

Secondary information can use `14sp`.

```xml
<TextView
    android:id="@+id/tvUniversity"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hajee Mohammad Danesh Science and Technology University"
    android:textSize="14sp" />
```

Recommended baseline:

```text
Size → 14sp
```

---

# 📐 dp vs sp

This is extremely important.

### `dp`

Use `dp` for:

- Width
- Height
- Margin
- Padding
- Spacing

Example:

```xml
android:layout_margin="16dp"
```

---

### `sp`

Use `sp` for:

- Text size

Example:

```xml
android:textSize="16sp"
```

### Remember

```text
dp → Layout dimensions

sp → Text size
```

---

# 🎨 Text Color

Use:

```xml
android:textColor="@color/black"
```

Better:

```xml
android:textColor="@color/text_primary"
```

Define it in:

```text
res/values/colors.xml
```

Example:

```xml
<color name="text_primary">#212121</color>
<color name="text_secondary">#757575</color>
```

---

# 📝 Primary vs Secondary Text

A good UI usually has a text hierarchy.

```text
Primary Text
     ↓
Most important information

Secondary Text
     ↓
Supporting information
```

Example:

```text
Mazharul Islam              ← Primary
HSTU • CSE                  ← Secondary
Last updated today          ← Caption
```

---

# ✏️ textStyle

You can change the style of text.

### Normal

```xml
android:textStyle="normal"
```

### Bold

```xml
android:textStyle="bold"
```

### Italic

```xml
android:textStyle="italic"
```

### Bold + Italic

```xml
android:textStyle="bold|italic"
```

---

# 📍 gravity

`gravity` controls the position of the text **inside the TextView**.

Example:

```xml
android:gravity="center"
```

Common values:

```text
center
center_horizontal
center_vertical
start
end
top
bottom
```

Example:

```xml
<TextView
    android:layout_width="200dp"
    android:layout_height="100dp"
    android:gravity="center"
    android:text="Hello" />
```

The text appears in the center of the TextView.

---

# ⚠️ gravity vs layout_gravity

These are different.

### gravity

Controls the content **inside the View**.

```xml
android:gravity="center"
```

### layout_gravity

Controls the View's position **inside its parent**.

```xml
android:layout_gravity="center"
```

Remember:

```text
gravity
    ↓
Content inside View

layout_gravity
    ↓
View inside Parent
```

---

# 📏 Padding

Padding creates space between the TextView's content and its edges.

```xml
android:padding="16dp"
```

You can also use:

```xml
android:paddingStart="16dp"
android:paddingEnd="16dp"
android:paddingTop="8dp"
android:paddingBottom="8dp"
```

---

# ↔️ Margin

Margin creates space outside the TextView.

```xml
android:layout_margin="16dp"
```

Example:

```text
Parent
   │
   │ margin
   ▼
┌──────────────┐
│   padding    │
│    Hello     │
└──────────────┘
```

Remember:

```text
margin  → outside
padding → inside
```

---

# 🔤 Font Family

You can specify a font family.

```xml
android:fontFamily="sans"
```

Common built-in families include:

```text
sans
sans-serif
serif
monospace
```

For production applications, custom fonts or Material typography may be used when appropriate.

---

# 📄 String Resources

Avoid hardcoding user-visible text directly in XML.

### ❌ Not recommended

```xml
android:text="Student Profile"
```

### ✅ Recommended

`strings.xml`

```xml
<string name="student_profile">Student Profile</string>
```

XML:

```xml
android:text="@string/student_profile"
```

Benefits:

- Localization
- Reusability
- Centralized text management
- Easier maintenance

---

# 🌍 String Formatting

You can use placeholders.

`strings.xml`

```xml
<string name="welcome_user">Welcome, %1$s</string>
```

Kotlin:

```kotlin
val message = getString(
    R.string.welcome_user,
    "Mazharul"
)
```

Result:

```text
Welcome, Mazharul
```

---

# 🔢 maxLines

Limits the number of lines.

```xml
android:maxLines="2"
```

Example:

```text
This is a long piece of
text that uses only two
lines...
```

---

# ✂️ ellipsize

Used when text is too long.

Example:

```xml
android:maxLines="1"
android:ellipsize="end"
```

Output:

```text
This is a very long text...
```

Common values:

```text
start
middle
end
marquee
```

---

# ↔️ Text Alignment

`textAlignment` controls alignment of the text content.

Example:

```xml
android:textAlignment="center"
```

Common values:

```text
textStart
textEnd
center
viewStart
viewEnd
```

For many simple cases, `gravity` is the more familiar choice for positioning text inside a TextView.

---

# 📱 Kotlin

## Using findViewById

```kotlin
val tvName = findViewById<TextView>(R.id.tvName)

tvName.text = "Mazharul Islam"
```

---

## Using ViewBinding

```kotlin
binding.tvName.text = "Mazharul Islam"
```

ViewBinding is preferred in modern Android projects over repeated `findViewById()` calls.

---

# 🔄 Change Text

```kotlin
binding.tvTitle.text = "Android Development"
```

---

# 🎨 Change Text Color

```kotlin
binding.tvTitle.setTextColor(
    ContextCompat.getColor(
        this,
        R.color.text_primary
    )
)
```

---

# 📏 Change Text Size

```kotlin
binding.tvTitle.textSize = 24f
```

The value is interpreted as `sp` by `TextView`.

---

# 👁️ Change Visibility

### Visible

```kotlin
binding.tvTitle.visibility = View.VISIBLE
```

### Invisible

```kotlin
binding.tvTitle.visibility = View.INVISIBLE
```

The View is hidden but still occupies its space.

### Gone

```kotlin
binding.tvTitle.visibility = View.GONE
```

The View is hidden and does not occupy layout space.

---

# 🧩 Complete Example

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- Screen Heading -->
    <TextView
        android:id="@+id/tvScreenHeading"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/student_profile"
        android:textSize="24sp"
        android:textStyle="bold" />

    <!-- Section Title -->
    <TextView
        android:id="@+id/tvSectionTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:text="@string/personal_information"
        android:textSize="18sp"
        android:textStyle="bold" />

    <!-- Label -->
    <TextView
        android:id="@+id/tvNameLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="@string/name"
        android:textSize="14sp" />

    <!-- Value -->
    <TextView
        android:id="@+id/tvName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/mazharul_islam"
        android:textSize="16sp" />

    <!-- Secondary Information -->
    <TextView
        android:id="@+id/tvUniversity"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="@string/university"
        android:textSize="14sp" />

    <!-- Caption -->
    <TextView
        android:id="@+id/tvLastUpdated"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="@string/last_updated"
        android:textSize="12sp" />

</LinearLayout>
```

---

# 📊 Text Hierarchy Example

A professional-looking basic hierarchy might be:

```text
Student Profile
24sp / Bold
        ↓
Personal Information
18sp / Bold
        ↓
Name
14sp
        ↓
Mazharul Islam
16sp
        ↓
Last updated: Today
12sp
```

Remember that typography should create a **clear hierarchy**, not just use different sizes randomly.

---

# 🆔 Naming Convention

Use meaningful IDs.

Recommended:

```text
tvTitle
tvScreenHeading
tvSectionTitle
tvName
tvEmail
tvDescription
tvStatus
tvLastUpdated
```

Avoid:

```text
textView1
textView2
textView3
```

Good naming makes your Kotlin code easier to understand.

---

# 💡 Best Practices

### 1. Use `sp` for text size

```xml
android:textSize="16sp"
```

### 2. Use `dp` for spacing

```xml
android:padding="16dp"
android:layout_margin="8dp"
```

### 3. Use String Resources

```xml
android:text="@string/title"
```

### 4. Use meaningful IDs

```text
tvTitle
tvDescription
tvUsername
```

### 5. Create a clear hierarchy

Example:

```text
Heading → 24sp
Title   → 20sp
Section → 18sp
Body    → 16sp
Label   → 14sp
Caption → 12sp
```

### 6. Avoid excessive text styles

Do not make every TextView bold, huge, or colorful.

Typography should communicate importance.

### 7. Consider accessibility

Text should be readable and have sufficient contrast.

Do not rely only on color to communicate important information.

---

# ⚠️ Common Beginner Mistakes

## ❌ Using dp for text

```xml
android:textSize="16dp"
```

### ✅ Correct

```xml
android:textSize="16sp"
```

---

## ❌ Hardcoding strings

```xml
android:text="Student Profile"
```

### ✅ Better

```xml
android:text="@string/student_profile"
```

---

## ❌ Using meaningless IDs

```xml
android:id="@+id/textView1"
```

### ✅ Better

```xml
android:id="@+id/tvTitle"
```

---

## ❌ Confusing gravity

```text
gravity
→ content inside View

layout_gravity
→ View inside parent
```

---

## ❌ Using too many text sizes

Avoid:

```text
13sp
15sp
17sp
19sp
21sp
23sp
```

without a clear reason.

Use a small, consistent typography scale.

---

# 🎤 Interview Questions

### 1. What is TextView?

`TextView` is an Android UI component used to display text to the user.

---

### 2. Which unit should be used for TextView text size?

```text
sp
```

---

### 3. What is the difference between dp and sp?

```text
dp → Layout dimensions

sp → Text size
```

---

### 4. What is the difference between gravity and layout_gravity?

```text
gravity
→ positions content inside the View

layout_gravity
→ positions the View inside its parent
```

---

### 5. What is `maxLines`?

It limits the maximum number of lines a TextView can display.

---

### 6. What does `ellipsize` do?

It controls how text is truncated when it does not fit.

Example:

```xml
android:maxLines="1"
android:ellipsize="end"
```

---

### 7. Why should we use `strings.xml`?

For:

- Localization
- Reusability
- Maintainability
- Centralized text management

---

### 8. How do you change TextView text using Kotlin?

Using ViewBinding:

```kotlin
binding.tvTitle.text = "Hello Android"
```

---

### 9. What is the difference between `INVISIBLE` and `GONE`?

```text
INVISIBLE
→ Hidden but occupies space

GONE
→ Hidden and does not occupy space
```

---

# 📌 Quick Reference

## Text Sizes

```text
28sp → Large Heading
24sp → Screen Heading
20sp → Page Title
18sp → Section Title
16sp → Body / Value
14sp → Label / Secondary
12sp → Caption
```

## Units

```text
dp → dimensions / spacing

sp → text
```

## Important Attributes

```text
text
textSize
textColor
textStyle
gravity
padding
layout_margin
maxLines
ellipsize
fontFamily
```

## Kotlin

```kotlin
binding.tvTitle.text = "Hello"

binding.tvTitle.textSize = 24f
```

## Visibility

```text
VISIBLE
INVISIBLE
GONE
```

---

# ⭐ Final Checklist

- [ ] Understand what TextView is
- [ ] Know basic TextView XML
- [ ] Know `text`
- [ ] Know `textSize`
- [ ] Know `textColor`
- [ ] Know `textStyle`
- [ ] Understand `gravity`
- [ ] Understand `layout_gravity`
- [ ] Understand `dp` vs `sp`
- [ ] Understand `maxLines`
- [ ] Understand `ellipsize`
- [ ] Know how to use String Resources
- [ ] Know how to update TextView from Kotlin
- [ ] Know ViewBinding syntax
- [ ] Understand TextView naming conventions
- [ ] Understand basic typography hierarchy

---

# 📚 Related Topics

- EditText
- Button
- ImageView
- ImageButton
- LinearLayout
- ConstraintLayout
- ViewBinding
- Resources
- Material Design Typography