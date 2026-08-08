# LinearLayout - Quick Notes

## 📖 What is LinearLayout?

`LinearLayout` is a ViewGroup that arranges child views in a **single direction**.

```text
Horizontal → Left to Right

Vertical → Top to Bottom
```

---

# 🏗️ Basic XML

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
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

</LinearLayout>
```

---

# 🧭 orientation

Defines the direction of child views.

### Vertical

```xml
android:orientation="vertical"
```

```text
View 1
  ↓
View 2
  ↓
View 3
```

### Horizontal

```xml
android:orientation="horizontal"
```

```text
View 1 → View 2 → View 3
```

### Remember

```text
vertical
→ Top to Bottom

horizontal
→ Start to End
```

---

# 📐 layout_width

Defines the width of a View.

Common values:

```xml
android:layout_width="match_parent"
android:layout_width="wrap_content"
```

### match_parent

View tries to match the available width of its parent.

```xml
android:layout_width="match_parent"
```

### wrap_content

View takes enough space for its content.

```xml
android:layout_width="wrap_content"
```

---

# 📏 layout_height

Same concept as `layout_width`, but for height.

```xml
android:layout_height="match_parent"
```

or:

```xml
android:layout_height="wrap_content"
```

---

# ⚖️ layout_weight ⭐

`layout_weight` distributes available space among children.

Example:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal">

    <Button
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="One" />

    <Button
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Two" />

</LinearLayout>
```

Result:

```text
┌───────────────────────┐
│      │       │        │
│ One  │       │  Two   │
│      │       │        │
└───────────────────────┘

Both → 50%
```

### Important rule

For a **horizontal** LinearLayout:

```xml
android:layout_width="0dp"
android:layout_weight="1"
```

For a **vertical** LinearLayout:

```xml
android:layout_width="match_parent"
android:layout_height="0dp"
android:layout_weight="1"
```

Example:

```xml
<Button
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:text="Button" />
```

---

# 🧮 Weight Ratio

Example:

```xml
android:layout_weight="1"
```

and:

```xml
android:layout_weight="2"
```

Total:

```text
1 + 2 = 3
```

Space distribution:

```text
Weight 1 → 1/3

Weight 2 → 2/3
```

```text
┌──────────┬────────────────────┐
│          │                    │
│   1/3    │        2/3         │
│          │                    │
└──────────┴────────────────────┘
```

---

# 🎯 gravity

`android:gravity` controls the **content/children inside the LinearLayout**.

Example:

```xml
android:gravity="center"
```

This can center the children inside the LinearLayout.

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

---

# 📍 layout_gravity

`android:layout_gravity` controls the **individual View inside its parent**.

Example:

```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:text="Login" />
```

Remember:

```text
gravity
→ Content / children inside parent

layout_gravity
→ Individual View inside parent
```

---

# ⚠️ gravity vs layout_gravity

This is a common beginner confusion.

```text
android:gravity
        ↓
Controls content/children inside the ViewGroup

android:layout_gravity
        ↓
Controls the individual View inside its parent
```

---

# ↔️ Padding

Padding creates space **inside** the LinearLayout/View.

```xml
android:padding="16dp"
```

Directional padding:

```xml
android:paddingStart="16dp"
android:paddingEnd="16dp"
android:paddingTop="16dp"
android:paddingBottom="16dp"
```

Remember:

```text
padding → inside
```

---

# ↔️ Margin

Margin creates space **outside** a View.

```xml
android:layout_margin="16dp"
```

Example:

```xml
android:layout_marginTop="12dp"
```

Remember:

```text
margin → outside
```

---

# 📐 dp vs sp

```text
dp → View dimensions / spacing

sp → Text size
```

Example:

```xml
android:layout_width="match_parent"
android:padding="16dp"
android:textSize="16sp"
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

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Name" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Mazharul" />

    </LinearLayout>

</LinearLayout>
```

Concept:

```text
LinearLayout
    │
    └── LinearLayout
          ├── TextView
          └── TextView
```

Avoid excessive nesting when possible.

---

# 🏗️ Common Layout Pattern

For a simple form:

```text
LinearLayout
    │
    ├── TextView
    ├── EditText
    ├── TextView
    ├── EditText
    └── Button
```

Example:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Name"
        android:textSize="14sp" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter your name"
        android:textSize="16sp" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Submit" />

</LinearLayout>
```

---

# ⚖️ Weight + Orientation

## Horizontal

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <View
        android:layout_width="0dp"
        android:layout_height="50dp"
        android:layout_weight="1" />

    <View
        android:layout_width="0dp"
        android:layout_height="50dp"
        android:layout_weight="2" />

</LinearLayout>
```

Result:

```text
1 : 2
```

---

## Vertical

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <View
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <View
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="2" />

</LinearLayout>
```

Result:

```text
1 : 2
```

---

# 🆚 LinearLayout vs ConstraintLayout

### LinearLayout

Best for:

```text
Simple linear arrangements
Forms
Rows
Columns
Small layouts
```

### ConstraintLayout

Best for:

```text
Complex responsive layouts
Multiple relationships
Precise positioning
Reducing unnecessary nesting
```

Remember:

```text
Simple → LinearLayout

Complex → ConstraintLayout
```

---

# 🆔 Naming Convention

For a LinearLayout, use meaningful IDs when you need to reference it:

```text
llContainer
llHeader
llForm
llProfile
llButtons
```

Pattern:

```text
ll + MeaningfulName
```

Example:

```xml
android:id="@+id/llButtons"
```

---

# ⚠️ Common Mistakes

## 1. Forgetting orientation

A LinearLayout needs an orientation.

```xml
android:orientation="vertical"
```

or:

```xml
android:orientation="horizontal"
```

---

## 2. Wrong weight dimensions

For horizontal weight:

```xml
android:layout_width="0dp"
android:layout_weight="1"
```

For vertical weight:

```xml
android:layout_height="0dp"
android:layout_weight="1"
```

---

## 3. Confusing gravity

```text
gravity
→ Content / children inside the ViewGroup

layout_gravity
→ View inside parent
```

---

## 4. Excessive nesting

Avoid:

```text
LinearLayout
 └── LinearLayout
      └── LinearLayout
           └── LinearLayout
                └── TextView
```

Too much nesting can make layouts harder to maintain.

Use `ConstraintLayout` when the layout becomes complex.

---

## 5. Using `sp` for dimensions

### ❌ Wrong

```xml
android:layout_width="100sp"
```

### ✅ Correct

```xml
android:layout_width="100dp"
```

---

# 🎤 Interview Essentials

### What is LinearLayout?

A ViewGroup that arranges its children in a single direction: horizontal or vertical.

---

### What is `orientation`?

Defines the direction of child views.

```text
horizontal
vertical
```

---

### What is `layout_weight`?

It distributes available space among child views according to their weight values.

---

### What is the common weight pattern for horizontal layouts?

```xml
android:layout_width="0dp"
android:layout_weight="1"
```

---

### What is the common weight pattern for vertical layouts?

```xml
android:layout_height="0dp"
android:layout_weight="1"
```

---

### gravity vs layout_gravity?

```text
gravity
→ Content / children inside ViewGroup

layout_gravity
→ View inside parent
```

---

# 📌 Quick Reference

## Main Attributes

```text
orientation
gravity
layout_weight
padding
layout_margin
layout_width
layout_height
```

## Orientation

```text
vertical
→ Top → Bottom

horizontal
→ Start → End
```

## Dimensions

```text
dp → Dimensions

sp → Text
```

## Weight

```text
Horizontal
→ width = 0dp
→ weight = value

Vertical
→ height = 0dp
→ weight = value
```

## Spacing

```text
padding → Inside

margin → Outside
```

---

# 🎯 Must Remember

```text
LinearLayout
→ Arrange children in one direction

orientation
→ horizontal / vertical

gravity
→ Content / children inside

layout_gravity
→ Individual View inside parent

layout_weight
→ Distribute available space

dp
→ Dimensions

sp
→ Text

padding
→ Inside

margin
→ Outside
```

---

# ⭐ Final Checklist

- [ ] Understand LinearLayout
- [ ] Know `orientation`
- [ ] Know horizontal layout
- [ ] Know vertical layout
- [ ] Understand `match_parent`
- [ ] Understand `wrap_content`
- [ ] Understand `layout_weight`
- [ ] Know weight ratios
- [ ] Know gravity
- [ ] Know layout_gravity
- [ ] Understand padding
- [ ] Understand margin
- [ ] Understand dp vs sp
- [ ] Understand nested LinearLayouts
- [ ] Know when to use LinearLayout
- [ ] Know when ConstraintLayout is better
- [ ] Know LinearLayout naming convention