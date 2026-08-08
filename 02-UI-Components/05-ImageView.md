# ImageView

> `ImageView` is an Android UI component used to display images in an application.

---

# 📖 What is ImageView?

`ImageView` displays images from different sources, such as:

- `drawable`
- `mipmap`
- Resources
- Files
- URLs (usually with an image-loading library)
- Bitmap objects
- Drawable objects

Common uses:

- Profile pictures
- App logos
- Icons
- Product images
- Banners
- Illustrations
- Background-like visual elements
- Gallery images

---

# 🏗️ Basic ImageView

```xml
<ImageView
    android:id="@+id/ivProfile"
    android:layout_width="120dp"
    android:layout_height="120dp"
    android:src="@drawable/profile_image"
    android:contentDescription="@string/profile_image" />
```

---

# 📐 Important Attributes

The most important `ImageView` attributes are:

```xml
android:id
android:layout_width
android:layout_height
android:src
android:scaleType
android:contentDescription
android:adjustViewBounds
android:tint
android:background
```

---

# 🖼️ android:src

`src` specifies the image displayed by the `ImageView`.

Example:

```xml
android:src="@drawable/profile_image"
```

If the image is stored in:

```text
res/drawable/
```

you can reference it using:

```xml
@drawable/image_name
```

Example:

```text
res/drawable/profile_image.png
```

```xml
android:src="@drawable/profile_image"
```

---

# 📁 drawable vs mipmap

### `drawable`

Generally used for images and graphics used inside the application UI.

```text
res/drawable/
```

Examples:

```text
profile.png
banner.png
background.xml
illustration.webp
```

### `mipmap`

Primarily intended for application launcher icons.

```text
res/mipmap/
```

For normal UI images, prefer:

```text
drawable
```

For launcher icons, use:

```text
mipmap
```

---

# 📏 ImageView Size

Example:

```xml
android:layout_width="120dp"
android:layout_height="120dp"
```

Remember:

```text
dp → View dimensions

sp → Text size
```

`ImageView` dimensions normally use `dp`.

---

# 🎯 scaleType

`scaleType` determines how the image is scaled and positioned inside the `ImageView`.

This is one of the most important `ImageView` concepts.

---

# 1. center

The image is centered without scaling.

```xml
android:scaleType="center"
```

If the image is larger than the View, parts of the image may be outside the visible area.

---

# 2. centerCrop

Scales the image so that the entire `ImageView` is filled.

Some parts of the image may be cropped.

```xml
android:scaleType="centerCrop"
```

Very useful for:

- Profile pictures
- Card images
- Thumbnails

Example:

```text
ImageView
┌─────────────────────┐
│      ┌───────┐      │
│      │ Image │      │
│      │       │      │
│      └───────┘      │
└─────────────────────┘

Image fills the View
Some image content may be cropped
```

---

# 3. centerInside

Scales the image down if necessary so the entire image fits inside the View.

The complete image remains visible.

```xml
android:scaleType="centerInside"
```

---

# 4. fitCenter

Scales the image uniformly so the entire image fits inside the View and centers it.

```xml
android:scaleType="fitCenter"
```

This is commonly useful when you want the complete image visible.

---

# 5. fitStart

Fits the complete image inside the View and aligns it toward the start.

```xml
android:scaleType="fitStart"
```

---

# 6. fitEnd

Fits the complete image inside the View and aligns it toward the end.

```xml
android:scaleType="fitEnd"
```

---

# 7. fitXY

Stretches the image to exactly match the ImageView's width and height.

```xml
android:scaleType="fitXY"
```

⚠️ This can distort the image's aspect ratio.

Example:

```text
Original:
┌──────────────┐
│              │
└──────────────┘

fitXY:
┌──────────────┐
│              │
│              │
│              │
└──────────────┘

Image may look stretched.
```

Avoid `fitXY` when maintaining the original proportions is important.

---

# 8. matrix

Uses a transformation matrix to position and scale the image.

```xml
android:scaleType="matrix"
```

This is useful for more advanced image transformations.

---

# ⭐ Most Important scaleTypes

For beginners, focus on:

```text
centerCrop
fitCenter
centerInside
fitXY
```

### Quick comparison

| scaleType | Behavior |
|---|---|
| `center` | Center without scaling |
| `centerCrop` | Fill View, crop image |
| `centerInside` | Entire image fits inside |
| `fitCenter` | Fit entire image and center |
| `fitStart` | Fit and align start |
| `fitEnd` | Fit and align end |
| `fitXY` | Stretch to View |
| `matrix` | Custom transformation |

---

# 🖼️ Profile Image Example

For a profile image:

```xml
<ImageView
    android:id="@+id/ivProfile"
    android:layout_width="120dp"
    android:layout_height="120dp"
    android:src="@drawable/profile_image"
    android:scaleType="centerCrop"
    android:contentDescription="@string/profile_picture" />
```

`centerCrop` is commonly useful when the image should completely fill a fixed-size area.

---

# ♿ contentDescription

`contentDescription` provides a textual description of an image for accessibility services such as screen readers.

Example:

```xml
android:contentDescription="@string/profile_picture"
```

`strings.xml`:

```xml
<string name="profile_picture">Profile picture</string>
```

---

# 🔇 Decorative Images

If an image is purely decorative and does not provide meaningful information, you can mark it as decorative:

```xml
android:contentDescription="@null"
```

Use this only when the image genuinely does not convey useful information.

---

# 🖼️ Image Resources

Images can be stored in:

```text
res/drawable/
```

Example:

```text
res/
└── drawable/
    ├── profile_image.png
    ├── banner.webp
    └── logo.xml
```

Reference:

```xml
android:src="@drawable/profile_image"
```

---

# 🌐 Loading Images from the Internet

For remote images, applications normally use an image-loading library rather than manually downloading and decoding images.

Common libraries include:

- Coil
- Glide
- Picasso

For modern Kotlin Android development, Coil is a common choice.

Conceptually:

```text
Internet URL
     ↓
Image-loading library
     ↓
ImageView
```

Example with Coil:

```kotlin
binding.ivProfile.load(imageUrl)
```

The exact API depends on the Coil version and project configuration.

---

# 💻 ImageView with ViewBinding

Suppose:

```xml
<ImageView
    android:id="@+id/ivProfile"
    android:layout_width="120dp"
    android:layout_height="120dp"
    android:src="@drawable/profile_image"
    android:contentDescription="@string/profile_picture" />
```

Access it using:

```kotlin
binding.ivProfile
```

---

# 🖼️ Change Image Programmatically

Using a drawable resource:

```kotlin
binding.ivProfile.setImageResource(
    R.drawable.profile_image
)
```

---

# 🎨 Change Image Tint

You can apply a tint:

```xml
android:tint="@color/black"
```

Or programmatically:

```kotlin
binding.ivIcon.setColorFilter(
    ContextCompat.getColor(
        this,
        R.color.black
    )
)
```

For modern Android UI, prefer appropriate tint APIs and resource-based styling when possible.

---

# 🔄 Change Scale Type Programmatically

```kotlin
binding.ivProfile.scaleType =
    ImageView.ScaleType.CENTER_CROP
```

Other examples:

```kotlin
ImageView.ScaleType.CENTER
ImageView.ScaleType.CENTER_CROP
ImageView.ScaleType.CENTER_INSIDE
ImageView.ScaleType.FIT_CENTER
ImageView.ScaleType.FIT_XY
```

---

# 📐 adjustViewBounds

`adjustViewBounds` allows the ImageView to adjust its bounds to preserve the image's aspect ratio in certain layouts.

Example:

```xml
<ImageView
    android:id="@+id/ivBanner"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:adjustViewBounds="true"
    android:scaleType="fitCenter"
    android:src="@drawable/banner" />
```

This can be useful for images where the height should adapt to the image's aspect ratio.

---

# 🔲 Background vs src

These are different.

### `src`

Specifies the actual image displayed by the ImageView.

```xml
android:src="@drawable/profile_image"
```

### `background`

Sets the background of the View.

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

# 🆚 ImageView vs Background Image

Use `ImageView` when the image is actual UI content.

Example:

```text
Profile photo
Product photo
Logo
Illustration
```

Use a background when the image is primarily part of the visual background/decorative layout.

---

# 🧩 Complete Example

```xml
<ImageView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/ivProfile"
    android:layout_width="120dp"
    android:layout_height="120dp"
    android:src="@drawable/profile_image"
    android:scaleType="centerCrop"
    android:contentDescription="@string/profile_picture" />
```

---

# 🧩 Profile Card Example

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <ImageView
        android:id="@+id/ivProfile"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_gravity="center_horizontal"
        android:src="@drawable/profile_image"
        android:scaleType="centerCrop"
        android:contentDescription="@string/profile_picture" />

    <TextView
        android:id="@+id/tvName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="12dp"
        android:text="@string/mazharul_islam"
        android:textSize="18sp"
        android:textStyle="bold" />

</LinearLayout>
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

# 💡 Best Practices

### 1. Use meaningful IDs

```xml
android:id="@+id/ivProfile"
```

instead of:

```xml
android:id="@+id/imageView1"
```

---

### 2. Use `drawable` for normal UI images

```text
res/drawable/
```

Use `mipmap` primarily for launcher icons.

---

### 3. Use `contentDescription`

For meaningful images:

```xml
android:contentDescription="@string/profile_picture"
```

For purely decorative images:

```xml
android:contentDescription="@null"
```

---

### 4. Choose the correct `scaleType`

Common choices:

```text
Profile / Card
→ centerCrop

Complete image
→ fitCenter

Keep image inside bounds
→ centerInside
```

---

### 5. Avoid unnecessary image sizes

Very large images can increase memory usage.

Use appropriately sized assets for the UI.

---

### 6. Use an image-loading library for remote images

For images from URLs, use a library such as Coil rather than implementing image downloading yourself.

---

# ⚠️ Common Mistakes

## ❌ Using `fitXY` without considering distortion

```xml
android:scaleType="fitXY"
```

This can stretch the image.

Use:

```xml
android:scaleType="centerCrop"
```

or:

```xml
android:scaleType="fitCenter"
```

depending on the design.

---

## ❌ Forgetting content description

```xml
<ImageView
    ...
    android:src="@drawable/profile_image" />
```

For a meaningful image, add:

```xml
android:contentDescription="@string/profile_picture"
```

---

## ❌ Using wrong resource type

Don't place normal UI images in `mipmap` just because it is an image.

Prefer:

```text
drawable → UI images
mipmap   → launcher icons
```

---

## ❌ Confusing `src` and `background`

```text
src
→ ImageView image

background
→ View background
```

---

# 🎤 Interview Questions

### 1. What is ImageView?

`ImageView` is an Android UI component used to display images.

---

### 2. What is `scaleType`?

`scaleType` determines how an image is scaled and positioned inside the ImageView.

---

### 3. What is the difference between `centerCrop` and `fitCenter`?

```text
centerCrop
→ Fills the View
→ May crop part of the image

fitCenter
→ Shows the complete image
→ May leave empty space
```

---

### 4. What is `fitXY`?

It scales the image independently in the horizontal and vertical directions to fill the ImageView, which can distort the image.

---

### 5. Why is `contentDescription` important?

It provides a description of meaningful images for accessibility services such as screen readers.

---

### 6. Where should normal UI images usually be stored?

```text
res/drawable/
```

---

### 7. Where should launcher icons usually be stored?

```text
res/mipmap/
```

---

### 8. How do you change an ImageView image using Kotlin?

```kotlin
binding.ivProfile.setImageResource(
    R.drawable.profile_image
)
```

---

### 9. How do you access ImageView using ViewBinding?

```kotlin
binding.ivProfile
```

---

# 📌 Quick Reference

## Important Attributes

```text
src
scaleType
contentDescription
adjustViewBounds
tint
background
```

## Common scaleTypes

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

## Most useful

```text
centerCrop
→ Fill + crop

fitCenter
→ Complete image + center

centerInside
→ Complete image inside View

fitXY
→ Fill exactly + possible distortion
```

## Resource folders

```text
drawable
→ Normal UI images

mipmap
→ Launcher icons
```

## Kotlin

```kotlin
binding.ivProfile.setImageResource(
    R.drawable.profile_image
)
```

---

# ⭐ Final Checklist

- [ ] Understand what ImageView is
- [ ] Know `android:src`
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
- [ ] Know how to change an image using Kotlin
- [ ] Know how to use ViewBinding
- [ ] Understand basic accessibility
- [ ] Know when to use an image-loading library