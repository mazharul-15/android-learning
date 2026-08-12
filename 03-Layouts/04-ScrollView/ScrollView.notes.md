# ScrollView

> `ScrollView` is an Android ViewGroup that allows the user to scroll vertically when the content is larger than the available screen space.

---

# 📖 What is ScrollView?

`ScrollView` is used when the content of a screen is too large to fit inside the available screen.

It allows the user to move:

```text
Up ↕ Down
```

Common uses:

- Registration forms
- Login forms
- Profile screens
- Settings screens
- Long articles
- Terms and conditions
- Long content pages

---

# 🏗️ Basic ScrollView

```xml
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Content -->

    </LinearLayout>

</ScrollView>
```

---

# ⭐ One Direct Child Rule

A `ScrollView` can have **only one direct child**.

### ❌ Wrong

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</ScrollView>
```

This is wrong because the `ScrollView` has two direct children:

```text
ScrollView
├── TextView ❌
└── Button   ❌
```

### ✅ Correct

Use a container:

```xml
<ScrollView
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

</ScrollView>
```

Structure:

```text
ScrollView
    ↓
LinearLayout
    ├── TextView
    └── Button
```

The direct child can also be another ViewGroup such as:

```text
LinearLayout
ConstraintLayout
FrameLayout
```

---

# ↕️ Vertical Scrolling

`ScrollView` provides **vertical scrolling**.

```text
        ↑
        │
   ┌───────────┐
   │ Content   │
   │           │
   │ Content   │
   │           │
   │ Content   │
   └───────────┘
        │
        ↓
```

Example:

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    ...

</ScrollView>
```

Remember:

```text
ScrollView
→ Vertical scrolling
```

---

# ↔️ HorizontalScrollView

For horizontal scrolling, use:

```xml
<HorizontalScrollView>
```

Example:

```xml
<HorizontalScrollView
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <!-- Content -->

    </LinearLayout>

</HorizontalScrollView>
```

Remember:

```text
ScrollView
→ Vertical

HorizontalScrollView
→ Horizontal
```

---

# 📏 ScrollView Dimensions

Usually:

```xml
android:layout_width="match_parent"
android:layout_height="match_parent"
```

Example:

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">
```

This allows the ScrollView to occupy the available screen.

---

# 📐 Child Dimensions

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

Why `wrap_content`?

Because the child may need to become taller than the screen.

```text
Screen height
      ↓
┌──────────────┐
│              │
│   Content    │
│              │
│              │
└──────────────┘

Child content can continue
below the screen.
      ↓
   Scroll
```

---

# 📜 fillViewport

`fillViewport` makes the ScrollView's child fill the available viewport when the content is smaller than the screen.

```xml
android:fillViewport="true"
```

Example:

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Content -->

    </LinearLayout>

</ScrollView>
```

This is especially useful for:

```text
Login Screen
Registration Screen
Forms
```

---

# 📜 Scrollbar

You can control the scrollbar with:

```xml
android:scrollbars="vertical"
```

Example:

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="vertical">
```

To hide the scrollbar:

```xml
android:scrollbars="none"
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

Controls whether the scrollbar fades away after a short time.

```xml
android:fadeScrollbars="true"
```

Example:

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fadeScrollbars="true">
```

---

# 📍 scrollbarStyle

Controls how the scrollbar is positioned relative to the ScrollView.

Common values:

```text
insideOverlay
insideInset
outsideOverlay
outsideInset
```

Example:

```xml
android:scrollbarStyle="insideOverlay"
```

---

# 🔄 nestedScrollingEnabled

Controls nested scrolling behavior.

```xml
android:nestedScrollingEnabled="true"
```

Example:

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:nestedScrollingEnabled="true">
```

This becomes more relevant when scrolling containers are nested.

---

# 🧩 Complete ScrollView Example

```xml
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"
    android:scrollbars="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/tvTitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Registration Form"
            android:textSize="24sp"
            android:textStyle="bold" />

        <EditText
            android:id="@+id/etName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:hint="Enter your name"
            android:textSize="16sp" />

        <EditText
            android:id="@+id/etEmail"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="12dp"
            android:hint="Enter your email"
            android:inputType="textEmailAddress"
            android:textSize="16sp" />

        <EditText
            android:id="@+id/etPhone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="12dp"
            android:hint="Enter your phone"
            android:inputType="phone"
            android:textSize="16sp" />

        <EditText
            android:id="@+id/etPassword"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="12dp"
            android:hint="Enter your password"
            android:inputType="textPassword"
            android:textSize="16sp" />

        <Button
            android:id="@+id/btnRegister"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            android:text="Register" />

    </LinearLayout>

</ScrollView>
```

---

# 💻 ScrollView with ViewBinding

If the ScrollView has an ID:

```xml
<ScrollView
    android:id="@+id/scrollView"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    ...

</ScrollView>
```

Access it using:

```kotlin
binding.scrollView
```

---

# ⬇️ Scroll to Bottom

You can scroll programmatically to the bottom.

```kotlin
binding.scrollView.post {
    binding.scrollView.fullScroll(View.FOCUS_DOWN)
}
```

`post {}` is useful because the View needs to be laid out before the scroll position can be calculated reliably.

---

# ⬆️ Scroll to Top

```kotlin
binding.scrollView.post {
    binding.scrollView.fullScroll(View.FOCUS_UP)
}
```

---

# 📍 scrollTo()

Scrolls immediately to a specific position.

```kotlin
binding.scrollView.scrollTo(0, 500)
```

Parameters:

```text
x = 0
y = 500
```

For a vertical ScrollView, the `y` value represents the vertical scroll position.

---

# 🐢 smoothScrollTo()

Scrolls smoothly to a specific position.

```kotlin
binding.scrollView.smoothScrollTo(0, 500)
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

`scrollY` returns the current vertical scroll position.

```kotlin
val position = binding.scrollView.scrollY
```

Example:

```kotlin
Log.d(
    "ScrollView",
    "Y = ${binding.scrollView.scrollY}"
)
```

---

# 👂 Detect Scroll Changes

You can listen for scrolling:

```kotlin
binding.scrollView.setOnScrollChangeListener {
    _, scrollX, scrollY, oldScrollX, oldScrollY ->

    Log.d(
        "ScrollView",
        "Current Y = $scrollY"
    )
}
```

Parameters:

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

# 📱 ScrollView with Forms

One of the most common uses is a long form.

Structure:

```text
ScrollView
    ↓
LinearLayout
    ├── TextView
    ├── EditText
    ├── EditText
    ├── EditText
    ├── EditText
    └── Button
```

Example:

```text
Registration Form
       ↓
Name
       ↓
Email
       ↓
Phone
       ↓
Address
       ↓
Password
       ↓
Confirm Password
       ↓
Register
```

If the form is taller than the screen, the user can scroll.

---

# 🆚 ScrollView vs RecyclerView

`ScrollView` is suitable for a relatively small amount of static content.

`RecyclerView` is designed for displaying collections of items efficiently.

| ScrollView | RecyclerView |
|---|---|
| Simple scrolling content | Large/dynamic lists |
| Forms | Contacts |
| Articles | Products |
| Settings pages | Chat messages |
| Small number of Views | Many items |
| Does not recycle children | Recycles item Views |

Examples:

```text
Login Form
→ ScrollView

Registration Form
→ ScrollView

Long Article
→ ScrollView

Contacts
→ RecyclerView

Product List
→ RecyclerView

Chat Messages
→ RecyclerView
```

---

# ⚠️ ScrollView + RecyclerView

Avoid putting a large `RecyclerView` inside a `ScrollView` unless there is a specific design reason.

### ❌ Usually avoid

```text
ScrollView
    ↓
RecyclerView
```

Why?

Both are scrolling containers, and nesting them can cause:

- Scrolling conflicts
- Measurement problems
- Poor performance
- Unnecessary complexity

For large lists, normally use:

```text
RecyclerView
```

---

# ⚠️ Nested ScrollView

Avoid unnecessary nesting:

```text
ScrollView
    ↓
ScrollView
```

Nested scrolling containers can make scrolling behavior difficult to manage.

---

# 🆔 Naming Convention

Use meaningful IDs when you need to access the ScrollView:

```text
scrollView
svForm
svProfile
svContent
svSettings
```

For `HorizontalScrollView`:

```text
horizontalScrollView
hsvImages
hsvCategories
```

---

# 💡 Best Practices

### 1. Use one direct child

```text
ScrollView
    ↓
Container
    ↓
Content
```

---

### 2. Use `wrap_content` for the content container

```xml
android:layout_height="wrap_content"
```

---

### 3. Use `fillViewport` when appropriate

```xml
android:fillViewport="true"
```

Especially useful for forms and screens where the content can sometimes be shorter than the viewport.

---

### 4. Don't use ScrollView for large lists

Use:

```text
RecyclerView
```

for large collections.

---

### 5. Avoid unnecessary nested scrolling containers

Don't create:

```text
ScrollView
    ↓
ScrollView
```

without a specific reason.

---

# ⚠️ Common Mistakes

## 1. Multiple direct children

### ❌ Wrong

```xml
<ScrollView>

    <TextView />

    <EditText />

    <Button />

</ScrollView>
```

### ✅ Correct

```xml
<ScrollView>

    <LinearLayout>

        <TextView />

        <EditText />

        <Button />

    </LinearLayout>

</ScrollView>
```

---

## 2. Using ScrollView for large lists

### ❌

```text
ScrollView
→ 1000 items
```

### ✅

```text
RecyclerView
→ 1000 items
```

---

## 3. Using `match_parent` height for content unnecessarily

The ScrollView's child commonly uses:

```xml
android:layout_height="wrap_content"
```

---

## 4. Confusing ScrollView and HorizontalScrollView

```text
ScrollView
→ Vertical

HorizontalScrollView
→ Horizontal
```

---

# 🆚 ScrollView vs HorizontalScrollView

| Component | Direction |
|---|---|
| `ScrollView` | Vertical |
| `HorizontalScrollView` | Horizontal |

---

# 🎤 Interview Questions

### 1. What is ScrollView?

`ScrollView` is a ViewGroup that allows vertical scrolling when its content is larger than the available space.

---

### 2. How many direct children can a ScrollView have?

Only **one direct child**.

That child can be:

```text
LinearLayout
ConstraintLayout
FrameLayout
```

---

### 3. How do you create horizontal scrolling?

Use:

```xml
<HorizontalScrollView>
```

---

### 4. What is `fillViewport`?

It allows the ScrollView's child to fill the available viewport when the content is smaller than the screen.

```xml
android:fillViewport="true"
```

---

### 5. How do you scroll programmatically?

```kotlin
binding.scrollView.smoothScrollTo(0, 500)
```

---

### 6. What does `scrollY` represent?

It represents the current vertical scroll position.

```kotlin
binding.scrollView.scrollY
```

---

### 7. Should you use ScrollView for a large list?

Generally, no.

Use:

```text
RecyclerView
```

---

# 📌 Quick Reference

## Scroll Direction

```text
ScrollView
→ Vertical

HorizontalScrollView
→ Horizontal
```

## Structure

```text
ScrollView
    ↓
ONE direct child
    ↓
Container
    ↓
Multiple Views
```

## Important Attributes

```text
fillViewport
scrollbars
fadeScrollbars
scrollbarStyle
nestedScrollingEnabled
```

## Kotlin

```kotlin
// Access
binding.scrollView

// Current position
binding.scrollView.scrollY

// Immediate
binding.scrollView.scrollTo(0, 500)

// Smooth
binding.scrollView.smoothScrollTo(0, 500)

// Top
binding.scrollView.fullScroll(View.FOCUS_UP)

// Bottom
binding.scrollView.fullScroll(View.FOCUS_DOWN)
```

---

# 🎯 Must Remember

```text
ScrollView
→ Vertical scrolling

HorizontalScrollView
→ Horizontal scrolling

ScrollView
→ Only ONE direct child

Child
→ Usually a ViewGroup

Child height
→ Usually wrap_content

fillViewport
→ Child fills viewport when content is smaller

Large list
→ RecyclerView

scrollTo()
→ Immediate

smoothScrollTo()
→ Smooth scrolling

scrollY
→ Current vertical scroll position
```

---

# ⭐ Final Checklist

- [ ] Understand ScrollView
- [ ] Know vertical scrolling
- [ ] Know HorizontalScrollView
- [ ] Remember the one direct child rule
- [ ] Know why a container is commonly used as the child
- [ ] Understand `fillViewport`
- [ ] Know `scrollbars`
- [ ] Know `fadeScrollbars`
- [ ] Know `scrollbarStyle`
- [ ] Know `nestedScrollingEnabled`
- [ ] Know `scrollTo()`
- [ ] Know `smoothScrollTo()`
- [ ] Know `scrollY`
- [ ] Know how to detect scroll changes
- [ ] Understand ScrollView with forms
- [ ] Understand ScrollView vs RecyclerView
- [ ] Avoid unnecessary nested ScrollViews
- [ ] Know ScrollView naming convention