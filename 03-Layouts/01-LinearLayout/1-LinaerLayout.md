# LinearLayout - Notes

> Quick revision notes for Android `LinearLayout`.

---

# 📖 What is LinearLayout?

`LinearLayout` is a ViewGroup that arranges its child Views in a **single direction**.

The direction can be:

```xml
android:orientation="vertical"
```

or:

```xml
android:orientation="horizontal"
```

---

# 🧠 Basic Structure

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <!-- Child Views -->

</LinearLayout>
```

---

# ↕️ Vertical LinearLayout

Children are arranged from top to bottom.

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Name" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Submit" />

</LinearLayout>
```

Visual structure:

```text
TextView
   ↓
EditText
   ↓
Button
```

---

# ↔️ Horizontal LinearLayout

Children are arranged from left to right.

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Cancel" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Submit" />

</LinearLayout>
```

Visual structure:

```text
[ Cancel ] [ Submit ]
```

---

# 📏 Important Attributes

## 1. orientation

Defines the direction of the child Views.

```xml
android:orientation="vertical"
```

or:

```xml
android:orientation="horizontal"
```

---

## 2. gravity

Controls how the **children are positioned inside the LinearLayout**.

Example:

```xml
android:gravity="center"
```

Other common values:

```text
center
center_horizontal
center_vertical
top
bottom
start
end
```

Example:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

</LinearLayout>
```

---

# ⚠️ gravity vs layout_gravity

This is an important concept.

### `android:gravity`

Controls the position of the **children inside the parent**.

```xml
android:gravity="center"
```

Think:

```text
Parent
┌──────────────────────┐
│                      │
│       Child          │
│                      │
└──────────────────────┘
```

---

### `android:layout_gravity`

Controls the position of the **View itself inside its parent**.

Example:

```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:text="Submit" />
```

Think:

```text
Parent
┌──────────────────────┐
│                      │
│       Button         │
│                      │
└──────────────────────┘
```

### Easy Rule

```text
gravity
    ↓
Children inside parent

layout_gravity
    ↓
View inside parent
```

---

# ⚖️ layout_weight

`layout_weight` allows child Views to share available space.

Example:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <Button
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Button 1" />

    <Button
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Button 2" />

</LinearLayout>
```

Result:

```text
┌───────────────┬───────────────┐
│   Button 1    │   Button 2    │
│      50%      │      50%      │
└───────────────┴───────────────┘
```

Because:

```text
weight = 1 + 1

Total = 2

Button 1 = 1/2
Button 2 = 1/2
```

---

# ⚖️ Different Weights

```xml
<Button
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="1"
    android:text="A" />

<Button
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="2"
    android:text="B" />
```

Total weight:

```text
1 + 2 = 3
```

Space distribution:

```text
A → 1/3

B → 2/3
```

Visual:

```text
┌────────┬────────────────┐
│   A    │       B        │
│  1/3   │      2/3       │
└────────┴────────────────┘
```

---

# 🚨 Important Rule for layout_weight

When using weights in a **horizontal** LinearLayout:

```xml
android:layout_width="0dp"
```

When using weights in a **vertical** LinearLayout:

```xml
android:layout_height="0dp"
```

Example:

```xml
<!-- Horizontal -->
android:layout_width="0dp"
android:layout_weight="1"
```

```xml
<!-- Vertical -->
android:layout_height="0dp"
android:layout_weight="1"
```

This allows the weight to distribute the available space efficiently.

---

# 📦 margin vs padding

### margin

Space **outside** a View.

```xml
android:layout_margin="16dp"
```

```text
Parent
   │
   │ margin
   ▼
 ┌─────────┐
 │  View   │
 └─────────┘
```

---

### padding

Space **inside** a View.

```xml
android:padding="16dp"
```

```text
┌─────────────────┐
│    padding      │
│   ┌─────────┐   │
│   │ content │   │
│   └─────────┘   │
└─────────────────┘
```

---

# 🧩 Nested LinearLayout

A LinearLayout can contain another LinearLayout.

Example:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Edit" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Delete" />

    </LinearLayout>

</LinearLayout>
```

---

# ⚠️ Nested Layout Warning

Too many nested LinearLayouts can create a complicated View hierarchy.

Example:

```text
LinearLayout
    ↓
LinearLayout
    ↓
LinearLayout
    ↓
LinearLayout
    ↓
Button
```

This can make layouts harder to maintain and may affect performance.

For complex responsive layouts, **ConstraintLayout** is often a better choice.

---

# 🆚 LinearLayout vs ConstraintLayout

| LinearLayout | ConstraintLayout |
|---|---|
| Simple layouts | Complex layouts |
| One direction | Flexible positioning |
| Vertical / Horizontal | Multiple relationships |
| Easy to learn | More powerful |
| Supports weights | Uses constraints |
| Can require nesting | Can reduce nesting |

---

# 💡 Best Practices

- Use LinearLayout for simple linear arrangements.
- Use `orientation` explicitly.
- Use `dp` for dimensions.
- Use `sp` for text sizes.
- Use `0dp + layout_weight` when distributing available space.
- Avoid unnecessary nested LinearLayouts.
- Prefer `start` and `end` instead of `left` and `right`.
- Use ConstraintLayout when the UI becomes complex.

---

# ⚠️ Common Mistakes

### ❌ Forgetting orientation

```xml
<LinearLayout>

</LinearLayout>
```

Always specify:

```xml
android:orientation="vertical"
```

or:

```xml
android:orientation="horizontal"
```

---

### ❌ Using match_parent for every child

Example:

```xml
<Button
    android:layout_width="match_parent"
    ... />
```

Multiple children with `match_parent` can produce unexpected layouts.

---

### ❌ Incorrect weight configuration

For horizontal weight distribution:

```xml
android:layout_width="0dp"
android:layout_weight="1"
```

For vertical weight distribution:

```xml
android:layout_height="0dp"
android:layout_weight="1"
```

---

### ❌ Confusing gravity and layout_gravity

Remember:

```text
gravity
→ controls children

layout_gravity
→ controls the View
```

---

# 🎤 Interview Questions

### 1. What is LinearLayout?

A ViewGroup that arranges its child Views in a single horizontal or vertical direction.

---

### 2. What is the difference between horizontal and vertical LinearLayout?

```text
Horizontal → Left → Right

Vertical   → Top → Bottom
```

---

### 3. What is `layout_weight`?

It allows child Views to divide the available space according to their weight values.

---

### 4. What is the difference between `gravity` and `layout_gravity`?

```text
gravity
→ positions children inside the parent

layout_gravity
→ positions the View inside its parent
```

---

### 5. What is the difference between margin and padding?

```text
margin  → outside the View

padding → inside the View
```

---

### 6. When should you use LinearLayout?

Use it when the UI has a simple horizontal or vertical structure.

---

### 7. Why shouldn't we create too many nested LinearLayouts?

Too much nesting makes the View hierarchy complex and can negatively affect maintainability and performance.

---

# 📌 Quick Revision

```text
LinearLayout
     │
     ├── orientation
     │      ├── vertical
     │      └── horizontal
     │
     ├── gravity
     │
     ├── layout_gravity
     │
     ├── layout_weight
     │
     ├── margin
     │
     └── padding
```

### Remember

```text
gravity
→ Children

layout_gravity
→ View

margin
→ Outside

padding
→ Inside

weight
→ Space distribution
```

---

# 📚 Related Topics

- ConstraintLayout
- FrameLayout
- ScrollView
- TextView
- EditText
- Button
- ViewGroup
- XML Layouts