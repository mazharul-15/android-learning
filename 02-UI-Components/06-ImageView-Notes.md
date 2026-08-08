# ImageView - Quick Notes

## 📖 What is ImageView?

`ImageView` is an Android UI component used to **display images**.

```text
ImageView → Display image
```

Common uses:

```text
Profile Picture
Logo
Product Image
Banner
Thumbnail
Icon
Illustration
```

---

## 🏗️ Basic XML

```xml
<ImageView
    android:id="@+id/ivProfile"
    android:layout_width="120dp"
    android:layout_height="120dp"
    android:src="@drawable/profile_image"
    android:scaleType="centerCrop"
    android:contentDescription="@string/profile_picture" />
```

---

## 📏 ImageView Size

Use `dp` for ImageView dimensions.

```xml
android:layout_width="120dp"
android:layout_height="120dp"
```

Remember:

```text
dp → View dimensions

sp → Text size
```

---

## 🖼️ android:src

Specifies the image displayed by the ImageView.

```xml
android:src="@drawable/profile_image"
```

Example:

```text
res/
└── drawable/
    └── profile_image.png
```

---

## 📁 drawable vs mipmap

```text
drawable
→ Normal UI images

mipmap
→ Launcher / app icons
```

Normal ImageView images should generally be placed in:

```text
res/drawable/
```

---

# ⭐ scaleType

`scaleType` controls how the image is positioned and scaled inside the ImageView.

Important values:

```text
center
centerCrop
centerInside
fitCenter
fitStart
fitEnd
fitXY
matrix
```

---

## 1. center

Centers the image without scaling it.

```xml
android:scaleType="center"
```

---

## 2. centerCrop ⭐

Fills the entire ImageView.

The image may be cropped.

```xml
android:scaleType="centerCrop"
```

Best for:

```text
Profile pictures
Thumbnails
Card images
```

Remember:

```text
centerCrop
→ Fill View
→ Crop if necessary
```

---

## 3. centerInside

Keeps the complete image inside the ImageView.

```xml
android:scaleType="centerInside"
```

Remember:

```text
centerInside
→ Complete image visible
→ May leave empty space
```

---

## 4. fitCenter

Fits the complete image and centers it.

```xml
android:scaleType="fitCenter"
```

Remember:

```text
fitCenter
→ Complete image
→ Centered
```

---

## 5. fitStart

Fits the complete image and aligns it toward the start.

```xml
android:scaleType="fitStart"
```

---

## 6. fitEnd

Fits the complete image and aligns it toward the end.

```xml
android:scaleType="fitEnd"
```

---

## 7. fitXY ⚠️

Stretches the image to exactly match the ImageView.

```xml
android:scaleType="fitXY"
```

Potential problem:

```text
Image may become distorted
```

Avoid it when maintaining the image's aspect ratio is important.

---

## 8. matrix

Uses a transformation matrix.

```xml
android:scaleType="matrix"
```

Mostly useful for advanced/custom image transformations.

---

# 🎯 scaleType Quick Comparison

| scaleType | Result |
|---|---|
| `center` | Center, no scaling |
| `centerCrop` | Fill + crop |
| `centerInside` | Complete image inside |
| `fitCenter` | Complete image + center |
| `fitStart` | Complete image + start |
| `fitEnd` | Complete image + end |
| `fitXY` | Fill exactly + possible distortion |
| `matrix` | Custom transformation |

### Most important

```text
centerCrop
→ Fill the View

fitCenter
→ Show complete image

centerInside
→ Keep complete image inside

fitXY
→ Stretch to View
```

---

# ♿ contentDescription

Used to describe meaningful images for accessibility.

```xml
android:contentDescription="@string/profile_picture"
```

Example:

```xml
<string name="profile_picture">Profile picture</string>
```

For purely decorative images:

```xml
android:contentDescription="@null"
```

Remember:

```text
Meaningful image
→ Description

Decorative image
→ @null
```

---

# 🆚 src vs background

### `src`

The actual ImageView image:

```xml
android:src="@drawable/profile_image"
```

### `background`

The View's background:

```xml
android:background="@color/black"
```

Remember:

```text
src
→ Image content

background
→ View background
```

---

# 📐 adjustViewBounds

Can help an ImageView adjust its bounds based on the image's aspect ratio.

```xml
android:adjustViewBounds="true"
```

Common example:

```xml
<ImageView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:adjustViewBounds="true"
    android:src="@drawable/banner"
    android:scaleType="fitCenter" />
```

---

# 🎨 Tint

Apply a color tint to an image:

```xml
android:tint="@color/black"
```

Useful for:

```text
Icons
Simple vector graphics
```

---

# 💻 Kotlin - ViewBinding

Access:

```kotlin
binding.ivProfile
```

---

## Change Image

```kotlin
binding.ivProfile.setImageResource(
    R.drawable.profile_image
)
```

---

## Change ScaleType

```kotlin
binding.ivProfile.scaleType =
    ImageView.ScaleType.CENTER_CROP
```

---

# 🌐 Remote Images

For images loaded from URLs, use an image-loading library.

Common choices:

```text
Coil
Glide
Picasso
```

Concept:

```text
URL
 ↓
Image Loading Library
 ↓
ImageView
```

Example with Coil:

```kotlin
binding.ivProfile.load(imageUrl)
```

---

# 🆔 Naming Convention

Use:

```text
ivProfile
ivLogo
ivProduct
ivBanner
ivThumbnail
ivIcon
ivCover
```

Pattern:

```text
iv + MeaningfulName
```

Avoid:

```text
imageView1
imageView2
imageView3
```

---

# ⚠️ Common Mistakes

### ❌ Using `fitXY` everywhere

```xml
android:scaleType="fitXY"
```

Can distort images.

---

### ❌ Forgetting accessibility

For meaningful images:

```xml
android:contentDescription="@string/profile_picture"
```

---

### ❌ Putting normal UI images in mipmap

Prefer:

```text
drawable → UI images

mipmap → Launcher icons
```

---

### ❌ Confusing `src` and `background`

```text
src
→ ImageView image

background
→ View background
```

---

# 🆚 TextView vs EditText vs ImageView

| Component | Purpose |
|---|---|
| `TextView` | Display text |
| `EditText` | Receive text input |
| `ImageView` | Display images |

---

# 🎯 Must Remember

```text
ImageView
→ Display image

Image dimensions
→ dp

Image resource
→ drawable

Launcher icon
→ mipmap

centerCrop
→ Fill + crop

fitCenter
→ Complete image + center

centerInside
→ Complete image inside

fitXY
→ Stretch + possible distortion

contentDescription
→ Accessibility

src
→ Image

background
→ View background
```

---

# 💻 Most Important Code

### XML

```xml
<ImageView
    android:id="@+id/ivProfile"
    android:layout_width="120dp"
    android:layout_height="120dp"
    android:src="@drawable/profile_image"
    android:scaleType="centerCrop"
    android:contentDescription="@string/profile_picture" />
```

### Kotlin

```kotlin
binding.ivProfile.setImageResource(
    R.drawable.profile_image
)
```

---

# 🎤 Interview Essentials

### What is ImageView?

A UI component used to display images.

### What is scaleType?

It controls how an image is scaled and positioned inside the ImageView.

### centerCrop vs fitCenter?

```text
centerCrop
→ Fill View
→ May crop

fitCenter
→ Show complete image
→ May leave empty space
```

### Where are normal UI images stored?

```text
res/drawable/
```

### Where are launcher icons stored?

```text
res/mipmap/
```

### Why use contentDescription?

For accessibility and screen-reader support.

### How do you change an ImageView image?

```kotlin
binding.ivProfile.setImageResource(
    R.drawable.profile_image
)
```

---

# ⭐ Final Checklist

- [ ] Understand ImageView
- [ ] Know `src`
- [ ] Know `scaleType`
- [ ] Understand `centerCrop`
- [ ] Understand `fitCenter`
- [ ] Understand `centerInside`
- [ ] Understand `fitXY`
- [ ] Know `contentDescription`
- [ ] Know `drawable` vs `mipmap`
- [ ] Know `src` vs `background`
- [ ] Know `adjustViewBounds`
- [ ] Know ImageView naming convention
- [ ] Know how to change images with Kotlin
- [ ] Understand remote image loading