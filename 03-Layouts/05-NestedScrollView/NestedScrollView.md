# NestedScrollView - Quick Notes

## 📖 What is NestedScrollView?

`NestedScrollView` is an Android ViewGroup that provides **vertical scrolling** while supporting **nested scrolling** with other scrolling views.

It is commonly used when a screen contains:

```text
Scrollable content
        +
Another scrolling component
```

For example:

```text
NestedScrollView
    │
    ├── Header
    ├── Text
    ├── Image
    └── RecyclerView
```

It is especially useful when a parent and child both need to participate in scrolling.

---

## 🆚 ScrollView vs NestedScrollView

### ScrollView

```text
Simple vertical scrolling
```

### NestedScrollView

```text
Vertical scrolling
+
Nested scrolling support
```

Remember:

```text
ScrollView
→ Basic scrolling

NestedScrollView
→ Scrolling + nested scrolling
```

---

## 🏗️ Basic XML

```xml
<androidx.core.widget.NestedScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Content -->

    </LinearLayout>

</androidx.core.widget.NestedScrollView>
```

---

# ⭐ One Direct Child Rule

Like `ScrollView`, `NestedScrollView` can have **only one direct child**.

### ❌ Wrong

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</androidx.core.widget.NestedScrollView>
```

There are two direct children:

```text
NestedScrollView
├── TextView ❌
└── Button   ❌
```

### ✅ Correct

Put the Views inside a container:

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </LinearLayout>

</androidx.core.widget.NestedScrollView>
```

Structure:

```text
NestedScrollView
        ↓
LinearLayout
    ├── TextView
    └── Button
```

---

# ↕️ Vertical Scrolling

`NestedScrollView` provides vertical scrolling.

```text
        ↑
        │
┌───────────────┐
│ Header        │
│               │
│ Image         │
│               │
│ Description   │
│               │
│ Products      │
└───────────────┘
        │
        ↓
```

Example:

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">
```

Remember:

```text
NestedScrollView
→ Vertical scrolling
```

---

# 📏 Width and Height

The NestedScrollView normally uses:

```xml
android:layout_width="match_parent"
android:layout_height="match_parent"
```

Example:

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">
```

---

# 📐 Child Height

The direct child commonly uses:

```xml
android:layout_width="match_parent"
android:layout_height="wrap_content"
```

Example:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">
```

Why?

Because the child can become taller than the available screen.

```text
Screen
┌──────────────┐
│              │
│   Content    │
│              │
│              │
└──────────────┘
       ↓
   More content
       ↓
     Scroll
```

---

# 📜 fillViewport

`fillViewport` makes the child fill the available viewport when the content is smaller than the screen.

```xml
android:fillViewport="true"
```

Example:

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Content -->

    </LinearLayout>

</androidx.core.widget.NestedScrollView>
```

Useful for:

```text
Forms
Profile screens
Login screens
Registration screens
Detail screens
```

---

# 📜 Scrollbars

You can control the scrollbar:

```xml
android:scrollbars="vertical"
```

To hide it:

```xml
android:scrollbars="none"
```

Example:

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="none">
```

Remember:

```text
vertical
→ Show vertical scrollbar

none
→ Hide scrollbar
```

---

# 🎨 fadeScrollbars

Controls whether the scrollbar fades away.

```xml
android:fadeScrollbars="true"
```

Example:

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fadeScrollbars="true">
```

---

# 🧩 Nested Scrolling

The main purpose of `NestedScrollView` is to support **nested scrolling**.

Nested scrolling means that a scrolling child can communicate with its scrolling parent.

Example:

```text
NestedScrollView
        ↓
RecyclerView
```

Both components can participate in scrolling.

Concept:

```text
User scrolls
     ↓
Child scrolling View
     ↓
Nested scrolling system
     ↓
Parent can participate
```

---

# 🆚 Why Nested Scrolling Matters?

Consider:

```text
NestedScrollView
    ↓
RecyclerView
```

Both are scrollable.

Without proper nested scrolling behavior, the parent and child can compete over touch events.

`NestedScrollView` provides support for Android's nested scrolling mechanism.

---

# ⚠️ NestedScrollView + RecyclerView

This combination requires care.

Example:

```text
NestedScrollView
    │
    ├── Header
    ├── Image
    ├── Text
    └── RecyclerView
```

It may be useful for certain UI designs, but it is **not automatically the best solution**.

For a large list, generally prefer letting `RecyclerView` handle the scrolling itself.

---

# 🆚 NestedScrollView + RecyclerView

### NestedScrollView

Good for:

```text
Header
+
Details
+
Small list/content
```

### RecyclerView

Good for:

```text
Large list
Dynamic items
Efficient item recycling
```

Remember:

```text
Large collection
→ RecyclerView

Mixed scrolling screen
→ NestedScrollView may be useful
```

---

# 📱 Example: Product Details Screen

A product details screen can contain:

```text
NestedScrollView
    │
    ├── Product Image
    ├── Product Name
    ├── Price
    ├── Description
    ├── Specifications
    └── Reviews
```

Example:

```xml
<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <ImageView
            android:layout_width="match_parent"
            android:layout_height="250dp"
            android:scaleType="centerCrop"
            android:src="@drawable/product" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:text="Product Name"
            android:textSize="24sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:text="Product description goes here."
            android:textSize="16sp" />

    </LinearLayout>

</androidx.core.widget.NestedScrollView>
```

---

# 💻 ViewBinding

If the NestedScrollView has an ID:

```xml
android:id="@+id/nestedScrollView"
```

Access it using:

```kotlin
binding.nestedScrollView
```

---

# 📍 scrollTo()

Scroll immediately to a specific position.

```kotlin
binding.nestedScrollView.scrollTo(0, 500)
```

Parameters:

```text
x = 0
y = 500
```

For vertical scrolling:

```text
y
→ Vertical position
```

---

# 🐢 smoothScrollTo()

Scroll smoothly to a specific position.

```kotlin
binding.nestedScrollView.smoothScrollTo(0, 500)
```

Difference:

```text
scrollTo()
→ Immediate

smoothScrollTo()
→ Smooth animation
```

---

# 🔍 scrollY

Returns the current vertical scroll position.

```kotlin
val position = binding.nestedScrollView.scrollY
```

Example:

```kotlin
Log.d(
    "NestedScrollView",
    "Y = ${binding.nestedScrollView.scrollY}"
)
```

---

# ⬆️ Scroll to Top

```kotlin
binding.nestedScrollView.post {
    binding.nestedScrollView.fullScroll(View.FOCUS_UP)
}
```

---

# ⬇️ Scroll to Bottom

```kotlin
binding.nestedScrollView.post {
    binding.nestedScrollView.fullScroll(View.FOCUS_DOWN)
}
```

---

# 👂 Detect Scroll Changes

You can listen for scrolling:

```kotlin
binding.nestedScrollView.setOnScrollChangeListener {
    _, scrollX, scrollY, oldScrollX, oldScrollY ->

    Log.d(
        "NestedScrollView",
        "Current Y = $scrollY"
    )
}
```

Important values:

```text
scrollX
→ Current horizontal position

scrollY
→ Current vertical position

oldScrollX
→ Previous horizontal position

oldScrollY
→ Previous vertical position
```

---

# 🎯 Detect When User Reaches Bottom

You can check whether the user has reached the bottom:

```kotlin
binding.nestedScrollView.setOnScrollChangeListener {
    view, _, scrollY, _, _ ->

    val child = view.getChildAt(0)

    if (scrollY >= child.measuredHeight - view.measuredHeight) {
        Log.d("NestedScrollView", "Reached bottom")
    }
}
```

Concept:

```text
Current scroll position
        ↓
Content height
        ↓
Viewport height
        ↓
Reached bottom?
```

---

# 🔄 NestedScrollView with AppBarLayout

A common Material Design pattern is:

```text
CoordinatorLayout
      ↓
AppBarLayout
      ↓
NestedScrollView
```

Example structure:

```text
CoordinatorLayout
│
├── AppBarLayout
│     └── Toolbar
│
└── NestedScrollView
      └── Content
```

`NestedScrollView` is commonly used with `AppBarLayout` because they support nested scrolling behavior.

---

# 🏗️ AppBarLayout Example

```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.google.android.material.appbar.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <com.google.android.material.appbar.MaterialToolbar
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize" />

    </com.google.android.material.appbar.AppBarLayout>

    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <!-- Content -->

        </LinearLayout>

    </androidx.core.widget.NestedScrollView>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

The exact scrolling behavior depends on the Material/CoordinatorLayout configuration.

---

# 🆚 ScrollView vs NestedScrollView

| Feature | ScrollView | NestedScrollView |
|---|---|---|
| Vertical scrolling | ✅ | ✅ |
| One direct child | ✅ | ✅ |
| `fillViewport` | ✅ | ✅ |
| Nested scrolling support | Basic | ✅ |
| Works with nested scrolling children | Limited use cases | Better suited |
| Package | Android framework | AndroidX |
| Common use | Simple screens | Complex/nested scrolling screens |

Remember:

```text
Simple screen
→ ScrollView

Nested scrolling
→ NestedScrollView
```

---

# 🆚 NestedScrollView vs RecyclerView

| NestedScrollView | RecyclerView |
|---|---|
| Scrollable screen content | Scrollable collection |
| Forms | Lists |
| Detail pages | Contacts |
| Profile pages | Products |
| Articles | Messages |
| Small/medium content | Large/dynamic collections |

---

# ⚠️ Common Mistakes

## 1. Multiple direct children

### ❌ Wrong

```xml
<NestedScrollView>

    <TextView />

    <Button />

</NestedScrollView>
```

### ✅ Correct

```xml
<NestedScrollView>

    <LinearLayout>

        <TextView />

        <Button />

    </LinearLayout>

</NestedScrollView>
```

---

## 2. Using it for every list

Don't automatically use:

```text
NestedScrollView
    ↓
Large RecyclerView
```

For large collections, let `RecyclerView` handle the list efficiently.

---

## 3. Nested scrolling containers everywhere

Avoid unnecessary structures such as:

```text
NestedScrollView
    ↓
ScrollView
    ↓
RecyclerView
```

This can create complicated scrolling behavior.

---

## 4. Forgetting `wrap_content`

The content container commonly uses:

```xml
android:layout_height="wrap_content"
```

---

## 5. Confusing ScrollView with NestedScrollView

```text
ScrollView
→ Basic vertical scrolling

NestedScrollView
→ Vertical scrolling + nested scrolling support
```

---

# 💡 Best Practices

### Use NestedScrollView when:

```text
✓ The screen has vertically scrollable content
✓ Another scrolling component participates in scrolling
✓ Using CoordinatorLayout/AppBarLayout patterns
✓ Building complex detail screens
```

### Prefer RecyclerView when:

```text
✓ You have a large list
✓ Items are dynamic
✓ Items need recycling
✓ Performance for many items matters
```

---

# 🆔 Naming Convention

Use meaningful IDs:

```text
nestedScrollView
nsvContent
nsvProfile
nsvDetails
nsvProduct
```

Pattern:

```text
nsv + MeaningfulName
```

Avoid:

```text
nestedScrollView1
nestedScrollView2
```

when a meaningful name is possible.

---

# 🎤 Interview Essentials

### What is NestedScrollView?

`NestedScrollView` is an AndroidX ViewGroup that provides vertical scrolling and supports nested scrolling with other scrolling Views.

---

### How many direct children can NestedScrollView have?

Only **one direct child**.

Usually:

```text
NestedScrollView
    ↓
LinearLayout / ConstraintLayout
```

---

### What is the difference between ScrollView and NestedScrollView?

```text
ScrollView
→ Basic vertical scrolling

NestedScrollView
→ Vertical scrolling
→ Nested scrolling support
```

---

### When would you use NestedScrollView?

For screens where multiple scrolling components need to cooperate, or where nested scrolling behavior is required.

Common examples:

```text
AppBarLayout + content
Complex detail screen
Header + scrolling content
```

---

### Should you use NestedScrollView for a large list?

Generally, no.

Use:

```text
RecyclerView
```

for large collections.

---

### How do you scroll programmatically?

```kotlin
binding.nestedScrollView.smoothScrollTo(0, 500)
```

---

### How do you get the current scroll position?

```kotlin
binding.nestedScrollView.scrollY
```

---

# 📌 Quick Reference

## Component

```text
NestedScrollView
→ Vertical scrolling
→ Nested scrolling support
```

## Structure

```text
NestedScrollView
        ↓
ONE direct child
        ↓
Container
        ↓
Multiple Views
```

## Common Child

```text
LinearLayout
ConstraintLayout
FrameLayout
```

## Important XML

```xml
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fillViewport="true"
android:scrollbars="vertical"
```

## Kotlin

```kotlin
// Access
binding.nestedScrollView

// Current position
binding.nestedScrollView.scrollY

// Immediate scroll
binding.nestedScrollView.scrollTo(0, 500)

// Smooth scroll
binding.nestedScrollView.smoothScrollTo(0, 500)

// Top
binding.nestedScrollView.fullScroll(View.FOCUS_UP)

// Bottom
binding.nestedScrollView.fullScroll(View.FOCUS_DOWN)
```

---

# 🎯 Must Remember

```text
NestedScrollView
→ AndroidX vertical scrolling ViewGroup

Only ONE direct child

Usually:
NestedScrollView
    ↓
Container
    ↓
Content

fillViewport
→ Child fills viewport when content is smaller

Nested scrolling
→ Allows scrolling parents and children to cooperate

ScrollView
→ Basic scrolling

NestedScrollView
→ Scrolling + nested scrolling

Large list
→ RecyclerView

scrollTo()
→ Immediate

smoothScrollTo()
→ Smooth

scrollY
→ Current vertical position
```

---

# 💻 Most Important Code

### XML

```xml
<androidx.core.widget.NestedScrollView
    android:id="@+id/nestedScrollView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Content -->

    </LinearLayout>

</androidx.core.widget.NestedScrollView>
```

### Kotlin

```kotlin
binding.nestedScrollView.smoothScrollTo(0, 500)
```

---

# ⭐ Final Checklist

- [ ] Understand NestedScrollView
- [ ] Know vertical scrolling
- [ ] Understand nested scrolling
- [ ] Remember the one direct child rule
- [ ] Know why a container is commonly used
- [ ] Understand `fillViewport`
- [ ] Know `scrollbars`
- [ ] Know `fadeScrollbars`
- [ ] Know `scrollTo()`
- [ ] Know `smoothScrollTo()`
- [ ] Know `scrollY`
- [ ] Know how to detect scroll changes
- [ ] Know how to detect the bottom
- [ ] Understand NestedScrollView + RecyclerView
- [ ] Understand NestedScrollView + AppBarLayout
- [ ] Understand ScrollView vs NestedScrollView
- [ ] Understand NestedScrollView vs RecyclerView
- [ ] Know common mistakes
- [ ] Know NestedScrollView naming convention