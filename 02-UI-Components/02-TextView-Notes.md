# TextView - Quick Revision

## 1. What is TextView?

`TextView` is a UI component used to **display text** to the user.

```text
TextView → Display text
```

---

## 2. Basic XML

```xml
<TextView
    android:id="@+id/tvTitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/title"
    android:textSize="20sp" />
```

---

## 3. Most Important Attributes

```xml
android:id
android:layout_width
android:layout_height
android:text
android:textSize
android:textColor
android:textStyle
android:gravity
android:maxLines
android:ellipsize
android:padding
android:layout_margin
```

---

## 4. Text Size

Practical values:

| Purpose | Size |
|---|---:|
| Large Heading | `28sp` |
| Screen Heading | `24sp` |
| Page Title | `20sp` |
| Section Title | `18sp` |
| Body / Value | `16sp` |
| Label / Secondary | `14sp` |
| Caption | `12sp` |

Remember:

```text
28sp → Large Heading
24sp → Heading
20sp → Title
18sp → Section
16sp → Body
14sp → Label
12sp → Caption
```

> These are practical baseline values, not strict Android rules.

---

## 5. dp vs sp

```text
dp → View dimensions and spacing

sp → Text size
```

Example:

```xml
android:layout_width="200dp"
android:padding="16dp"
android:textSize="16sp"
```

---

## 6. text

Displays the actual text.

```xml
android:text="Hello Android"
```

Prefer:

```xml
android:text="@string/hello_android"
```

---

## 7. textSize

Controls text size.

```xml
android:textSize="16sp"
```

Use:

```text
sp
```

not:

```text
dp
```

---

## 8. textColor

```xml
android:textColor="@color/black"
```

Prefer color resources:

```xml
@color/text_primary
```

---

## 9. textStyle

Normal:

```xml
android:textStyle="normal"
```

Bold:

```xml
android:textStyle="bold"
```

Italic:

```xml
android:textStyle="italic"
```

Bold + Italic:

```xml
android:textStyle="bold|italic"
```

---

## 10. gravity

Controls the **content inside the TextView**.

```xml
android:gravity="center"
```

Example:

```text
┌─────────────────────┐
│                     │
│        Hello        │
│                     │
└─────────────────────┘
```

Remember:

```text
gravity
→ content inside View
```

---

## 11. layout_gravity

Controls the **TextView inside its parent**.

```xml
android:layout_gravity="center"
```

Remember:

```text
gravity
→ content inside View

layout_gravity
→ View inside Parent
```

---

## 12. padding vs margin

```text
padding
→ Space inside the View

margin
→ Space outside the View
```

Example:

```xml
android:padding="16dp"
android:layout_margin="8dp"
```

---

## 13. maxLines

Limits the number of lines.

```xml
android:maxLines="2"
```

---

## 14. ellipsize

Controls how overflowing text is truncated.

Common value:

```xml
android:maxLines="1"
android:ellipsize="end"
```

Result:

```text
This is a very long...
```

Common values:

```text
start
middle
end
marquee
```

---

## 15. String Resources

### ❌ Avoid

```xml
android:text="Student Profile"
```

### ✅ Prefer

`res/values/strings.xml`

```xml
<string name="student_profile">
    Student Profile
</string>
```

Then:

```xml
android:text="@string/student_profile"
```

Why?

```text
Localization
Reusability
Maintainability
```

---

## 16. Kotlin - ViewBinding

Change text:

```kotlin
binding.tvTitle.text = "Android Development"
```

Get text:

```kotlin
val title = binding.tvTitle.text.toString()
```

Change text size:

```kotlin
binding.tvTitle.textSize = 24f
```

---

## 17. Visibility

Show:

```kotlin
binding.tvTitle.visibility = View.VISIBLE
```

Hide but keep space:

```kotlin
binding.tvTitle.visibility = View.INVISIBLE
```

Hide and remove space:

```kotlin
binding.tvTitle.visibility = View.GONE
```

Remember:

```text
VISIBLE
→ Show

INVISIBLE
→ Hide + keep space

GONE
→ Hide + remove space
```

---

## 18. Naming Convention

Use:

```text
tvTitle
tvHeading
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

Pattern:

```text
tv + MeaningfulName
```

---

## 19. Typical Text Hierarchy

Example:

```text
Student Profile
24sp / Bold

Personal Information
18sp / Bold

Name
14sp

Mazharul Islam
16sp

Last updated: Today
12sp
```

The purpose is to create **visual hierarchy**.

---

## 20. Common Mistakes

### Wrong

```xml
android:textSize="16dp"
```

### Correct

```xml
android:textSize="16sp"
```

---

### Wrong

```xml
android:text="Student Profile"
```

### Better

```xml
android:text="@string/student_profile"
```

---

### Remember

```text
TextView
→ Display text

dp
→ Dimensions

sp
→ Text

gravity
→ Content inside View

layout_gravity
→ View inside Parent

padding
→ Inside

margin
→ Outside

maxLines
→ Limit lines

ellipsize
→ Handle overflowing text
```

---

# 🎯 Must Remember

```text
TextView = Display text

textSize = sp

dimensions = dp

gravity ≠ layout_gravity

padding ≠ margin

strings.xml = User-visible text

ViewBinding:
binding.tvTitle.text = "Hello"
```

---

# 🔥 Interview Essentials

**Q: What is TextView?**

```text
A UI component used to display text.
```

**Q: dp vs sp?**

```text
dp → dimensions
sp → text size
```

**Q: gravity vs layout_gravity?**

```text
gravity → content inside View
layout_gravity → View inside Parent
```

**Q: What does ellipsize do?**

```text
Controls how overflowing text is truncated.
```

**Q: Why use strings.xml?**

```text
Localization + Reusability + Maintainability
```