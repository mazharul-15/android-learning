# Android RecyclerView --- Complete Notes (Beginner to Advanced)

**Stack:** Kotlin + XML + ViewBinding\
**Purpose:** A practical reference for building efficient lists, grids,
feeds, and dynamic collections.

## Table of Contents

1.  What is RecyclerView?
2.  How it works
3.  Core components
4.  Setup and ViewBinding
5.  Basic implementation
6.  Adapter and ViewHolder callbacks
7.  ViewBinding
8.  Click listeners
9.  RecyclerView in a Fragment
10. LayoutManagers
11. Multiple view types
12. DiffUtil
13. ListAdapter
14. Add, update, delete, and selection
15. Decorations and spacing
16. Loading, empty, and error states
17. Pagination
18. Swipe and drag actions
19. Performance
20. Troubleshooting
21. Accessibility and testing
22. Advanced topics and architecture
23. Learning roadmap
24. Quick checklist

------------------------------------------------------------------------

## 1. What is RecyclerView?

`RecyclerView` is an Android UI component for displaying scrollable
collections: students, messages, products, news, reviews, and more.

It is called *RecyclerView* because it reuses item views that scroll
off-screen rather than creating a new view for every item. This reduces
view creation and helps scrolling performance.

  -----------------------------------------------------------------------
  Feature                 ListView                RecyclerView
  ----------------------- ----------------------- -----------------------
  View reuse              ViewHolder often        ViewHolder is central
                          implemented manually    to the API

  Layout choices          Mainly a vertical list  Linear, grid,
                                                  staggered, custom

  Animations              More limited            Flexible item
                                                  animations

  Decorations             Less flexible           `ItemDecoration`

  Modern collection UI    Older API               Generally preferred
  -----------------------------------------------------------------------

## 2. How it works

1.  RecyclerView asks the adapter for a ViewHolder when one is needed.
2.  The adapter inflates the item XML and creates the holder.
3.  RecyclerView asks the adapter to bind data to the holder.
4.  When the item scrolls away, the holder can be reused.
5.  A basic adapter reports item count; `ListAdapter` handles that
    itself.

``` text
Data list -> Adapter -> ViewHolder -> Item XML
                 ^          |
                 |          v
             RecyclerView + LayoutManager
```

## 3. Core components

-   **Data model:** Kotlin object representing an item.
-   **Item layout:** XML for one row/card.
-   **Adapter:** Connects the data to item views.
-   **ViewHolder:** Holds references to the item's views.
-   **LayoutManager:** Arranges items.
-   **ItemDecoration:** Adds dividers, spacing, or drawing.
-   **ItemAnimator:** Animates item changes.

Example model:

``` kotlin
data class Student(
    val id: Int,
    val name: String,
    val department: String
)
```

Use a unique, stable ID. Names may repeat or change, so they are usually
poor identity keys.

## 4. Setup and ViewBinding

Add compatible dependencies in the app module's Gradle file:

``` kotlin
dependencies {
    implementation("androidx.recyclerview:recyclerview:<current-version>")
    implementation("androidx.cardview:cardview:<current-version>") // if using CardView
}
```

Enable ViewBinding in `build.gradle.kts`:

``` kotlin
android {
    buildFeatures {
        viewBinding = true
    }
}
```

Sync Gradle. Replace version placeholders with versions compatible with
your project.

## 5. Basic implementation

### A. Item layout: `res/layout/item_student.xml`

``` xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginHorizontal="12dp"
    android:layout_marginVertical="6dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="2dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/nameTextView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Student name"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:id="@+id/departmentTextView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="4dp"
            android:text="Department" />
    </LinearLayout>
</androidx.cardview.widget.CardView>
```

### B. Screen layout: `res/layout/fragment_student.xml`

``` xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.recyclerview.widget.RecyclerView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/studentRecyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:clipToPadding="false"
    android:paddingVertical="8dp" />
```

### C. ViewHolder

``` kotlin
class StudentViewHolder(
    val binding: ItemStudentBinding
) : RecyclerView.ViewHolder(binding.root)
```

`item_student.xml` generates `ItemStudentBinding` when ViewBinding is
enabled.

### D. Basic adapter

``` kotlin
class StudentAdapter(
    private val students: List<Student>
) : RecyclerView.Adapter<StudentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        val student = students[position]
        holder.binding.nameTextView.text = student.name
        holder.binding.departmentTextView.text = student.department
    }

    override fun getItemCount(): Int = students.size
}
```

Common imports: `android.view.LayoutInflater`, `android.view.ViewGroup`,
and `androidx.recyclerview.widget.RecyclerView`, plus your model and
generated binding.

### E. Attach adapter

``` kotlin
binding.studentRecyclerView.layoutManager =
    LinearLayoutManager(this)
binding.studentRecyclerView.adapter = StudentAdapter(students)
```

In a Fragment, use `LinearLayoutManager(requireContext())`.

## 6. Adapter and ViewHolder callbacks

### `onCreateViewHolder()`

Creates an item view when RecyclerView needs a new holder.
`parent.context` provides the context; `false` means RecyclerView will
attach the view.

### `onBindViewHolder()`

Binds the item for a position:

``` kotlin
override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]
    holder.binding.nameTextView.text = student.name
}
```

Set **all row-dependent properties** on every bind. Recycled views can
otherwise show old text, visibility, selection, or images.

### `getItemCount()`

For a normal adapter:

``` kotlin
override fun getItemCount(): Int = students.size
```

### Why ViewHolder?

It stores references to the views in one row so they can be reused
without repeatedly looking them up.

## 7. ViewBinding

Use the binding class generated from the item XML:

``` kotlin
val binding = ItemStudentBinding.inflate(
    LayoutInflater.from(parent.context),
    parent,
    false
)
```

Naming: - `item_student.xml` -\> `ItemStudentBinding` -
`fragment_student.xml` -\> `FragmentStudentBinding`

Use `ItemStudentBinding` inside the ViewHolder and adapter; use
`FragmentStudentBinding` for the Fragment's screen.

## 8. Click listeners

Pass clicks to the screen through a lambda instead of hardcoding
navigation or business logic inside the adapter.

``` kotlin
class StudentAdapter(
    private val students: List<Student>,
    private val onStudentClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.binding.nameTextView.text = student.name
        holder.binding.departmentTextView.text = student.department
        holder.binding.root.setOnClickListener {
            onStudentClick(student)
        }
    }

    override fun getItemCount() = students.size
}
```

Usage in an Activity:

``` kotlin
val adapter = StudentAdapter(students) { student ->
    Toast.makeText(this, "Selected: ${student.name}", Toast.LENGTH_SHORT).show()
}
```

In a Fragment use `requireContext()` for the Toast. Passing the model
object is often safer than passing a captured position. If you need a
position at click time, check it:

``` kotlin
val position = holder.bindingAdapterPosition
if (position != RecyclerView.NO_POSITION) {
    // Handle the current position safely.
}
```

## 9. RecyclerView in a Fragment

A Fragment can outlive its view. Clear view binding in
`onDestroyView()`.

``` kotlin
class StudentFragment : Fragment(R.layout.fragment_student) {

    private var _binding: FragmentStudentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: StudentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStudentBinding.bind(view)

        val students = listOf(
            Student(101, "Rahim", "CSE"),
            Student(102, "Karim", "EEE"),
            Student(103, "Sakib", "CSE")
        )

        adapter = StudentAdapter(students) { student ->
            Toast.makeText(
                requireContext(),
                "Clicked: ${student.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.studentRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.studentRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```

This uses the adapter from Section 8. A ListAdapter version is shown
below.

## 10. LayoutManagers

### Vertical list

``` kotlin
recyclerView.layoutManager = LinearLayoutManager(context)
```

### Horizontal list

``` kotlin
recyclerView.layoutManager = LinearLayoutManager(
    context, RecyclerView.HORIZONTAL, false
)
```

### Grid

``` kotlin
recyclerView.layoutManager = GridLayoutManager(context, 2)
```

For a vertical grid, `2` means two columns.

### Staggered grid

``` kotlin
recyclerView.layoutManager = StaggeredGridLayoutManager(
    2, StaggeredGridLayoutManager.VERTICAL
)
```

Use this when cards have variable heights. Use `GridLayoutManager` for
ordinary equal-sized cards.

### Full-span header in a grid

``` kotlin
val manager = GridLayoutManager(requireContext(), 2)
manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return if (isHeader(position)) 2 else 1
    }
}
recyclerView.layoutManager = manager
```

`isHeader(position)` is illustrative: implement it using the adapter's
actual item type.

## 11. Multiple view types

For a feed containing headers, student rows, and footers, model the row
types explicitly:

``` kotlin
sealed class StudentRow {
    data class Header(val title: String) : StudentRow()
    data class StudentItem(val student: Student) : StudentRow()
    data class Footer(val message: String) : StudentRow()
}
```

An adapter's `getItemViewType()` can return constants based on the row:

``` kotlin
override fun getItemViewType(position: Int): Int =
    when (items[position]) {
        is StudentRow.Header -> TYPE_HEADER
        is StudentRow.StudentItem -> TYPE_STUDENT
        is StudentRow.Footer -> TYPE_FOOTER
    }

companion object {
    private const val TYPE_HEADER = 0
    private const val TYPE_STUDENT = 1
    private const val TYPE_FOOTER = 2
}
```

Inflate the matching layout in `onCreateViewHolder()` and bind the
matching model in `onBindViewHolder()`. With ListAdapter, DiffUtil must
correctly compare identity and content for each row type. For complex
feeds, split binding logic into delegates or smaller classes.

## 12. DiffUtil

`DiffUtil` compares an old and a new list to calculate insertions,
removals, moves, and content changes.

``` kotlin
class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }
}
```

-   `areItemsTheSame()`: Are these the same logical item? Compare a
    unique, stable ID.
-   `areContentsTheSame()`: Is the displayed content the same? Data
    class equality is often suitable.

Example: old `Student(102, "Karim", "EEE")`; new
`Student(102, "Karim Ahmed", "EEE")`. Same ID means same item; different
fields mean the row content changed.

For larger models, compare only fields relevant to the displayed content
if full equality is not suitable.

## 13. ListAdapter

`ListAdapter` uses `DiffUtil` through `AsyncListDiffer` internally.

``` kotlin
class StudentAdapter(
    private val onStudentClick: (Student) -> Unit
) : ListAdapter<Student, StudentViewHolder>(StudentDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)
        holder.binding.nameTextView.text = student.name
        holder.binding.departmentTextView.text = student.department
        holder.binding.root.setOnClickListener { onStudentClick(student) }
    }
}
```

Key differences from a normal adapter: - No `getItemCount()` override is
needed. - Use `getItem(position)` rather than maintaining your own
list. - Call `submitList(newList)`. - Provide a correct
`DiffUtil.ItemCallback`.

### Submit and update

``` kotlin
studentAdapter.submitList(
    listOf(
        Student(101, "Rahim", "CSE"),
        Student(102, "Karim", "EEE")
    )
)

val updated = listOf(
    Student(101, "Rahim", "CSE"),
    Student(102, "Karim Ahmed", "EEE"),
    Student(103, "Sakib", "CSE")
)
studentAdapter.submitList(updated)
```

### Add, remove, update

``` kotlin
// Add
val added = studentAdapter.currentList + Student(104, "Nayeem", "CSE")
studentAdapter.submitList(added)

// Remove
val removed = studentAdapter.currentList.filterNot { it.id == 102 }
studentAdapter.submitList(removed)

// Update immutably
val updatedStudents = studentAdapter.currentList.map { student ->
    if (student.id == 101) student.copy(name = "Rahim Ahmed") else student
}
studentAdapter.submitList(updatedStudents)
```

**Important:** Treat submitted lists as immutable snapshots. Do not
mutate a submitted list and resubmit the same instance. `submitList()`
can diff asynchronously. Its optional commit callback runs when that
submission is committed, but may not run if a later submission
supersedes it.

``` kotlin
studentAdapter.submitList(updated) {
    // This submission has been committed.
}
```

## 14. Add, update, delete, and selection

For a delete button in a row:

``` kotlin
holder.binding.deleteButton.setOnClickListener {
    onDeleteClick(student)
}
```

Let the Fragment/ViewModel update the data source and submit a new list.
Keep business logic out of the adapter.

For selected rows, store selected **IDs**, not only positions, and set
selection state on every bind:

``` kotlin
val selectedIds = setOf(101, 103)
holder.binding.root.isSelected = student.id in selectedIds
```

Ensure the row background responds to selected state. For large
multi-selection features, consider AndroidX selection support or a
ViewModel-managed set of IDs.

## 15. Decorations and spacing

### Divider

``` kotlin
val divider = DividerItemDecoration(
    requireContext(), DividerItemDecoration.VERTICAL
)
recyclerView.addItemDecoration(divider)
```

Use a custom `ItemDecoration` for consistent spacing or drawing. For
grids, calculate offsets using span count and column position. Avoid
unintentionally combining large item margins with equivalent decoration
offsets.

## 16. Loading, empty, and error states

A production list usually needs: - Loading indicator while data is
fetched. - Empty state when a successful response contains no items. -
Error message and retry action. - Content state when items are
available.

``` kotlin
fun renderStudents(students: List<Student>) {
    binding.progressBar.isVisible = false
    binding.errorTextView.isVisible = false

    val empty = students.isEmpty()
    binding.emptyTextView.isVisible = empty
    binding.studentRecyclerView.isVisible = !empty
}
```

This assumes those views exist in the layout and
`androidx.core.view.isVisible` is imported. For real apps, model
`Loading`, `Success(data)`, and `Error(message)` explicitly, often with
a sealed UI-state type in the ViewModel.

## 17. Pagination

Pagination fetches data in pages rather than loading everything at once.

Typical flow: 1. Load the first page. 2. Display its items. 3. Detect
when the user approaches the end. 4. Request the next page only if no
request is running and more pages exist. 5. Append without duplicates.
6. Show loading and error feedback for next-page requests.

Basic scroll-listener concept:

``` kotlin
recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val manager = recyclerView.layoutManager as? LinearLayoutManager ?: return
        val lastVisible = manager.findLastVisibleItemPosition()
        val total = manager.itemCount
        if (lastVisible >= total - 5) {
            // Request next page through ViewModel.
            // Guard duplicate requests and check whether more pages exist.
        }
    }
})
```

This is illustrative, not a complete pagination implementation.
Production pagination must handle rotation, lifecycle changes, duplicate
requests, and retries. For real apps, prefer AndroidX Paging 3 and
`PagingDataAdapter`.

## 18. Swipe and drag actions

`ItemTouchHelper` supports swipe and drag gestures.

``` kotlin
val callback = object : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {
        val position = viewHolder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            // Tell the ViewModel to remove the item safely.
        }
    }
}
ItemTouchHelper(callback).attachToRecyclerView(recyclerView)
```

With ListAdapter, update the real data source and submit a new list. For
drag-and-drop, update the underlying item order as well as the UI.

## 19. Performance and best practices

1.  Keep row layouts lightweight and avoid deeply nested view
    hierarchies.
2.  Use ListAdapter/DiffUtil for changing lists instead of calling
    `notifyDataSetChanged()` for every update.
3.  Do not perform network calls or expensive database work inside
    `onBindViewHolder()`.
4.  Load images asynchronously with a suitable image-loading library.
5.  Reset all row-dependent state on every bind.
6.  Use unique, stable item identity.
7.  Avoid capturing stale positions in click listeners.
8.  Avoid unnecessary nested scrolling lists.
9.  Keep data ownership in a ViewModel/repository rather than in the
    adapter.
10. Use Paging for large remote data sets.
11. Use `setHasFixedSize(true)` only when adapter changes cannot change
    RecyclerView's own size.
12. Use payloads for partial binds only when needed, and ensure full
    binding still works.

`notifyDataSetChanged()` marks the whole data set as changed. DiffUtil
calculates specific changes, allowing more targeted updates and
animations.

## 20. Troubleshooting

### Nothing appears

-   Check RecyclerView dimensions.
-   Assign a LayoutManager and adapter.
-   Verify item count or that `submitList()` is called.
-   Check item visibility and height.

### Index error or wrong click item

-   Avoid using a stale position.
-   Use `getItem(position)` while binding.
-   At click time, validate
    `bindingAdapterPosition != RecyclerView.NO_POSITION`.

### Binding class unresolved

-   Enable ViewBinding and sync Gradle.
-   Check XML file name and generated binding class.
-   Fix XML errors and rebuild.

### Every row shows the same data

-   Bind the item using the current position.
-   Do not reuse one mutable model for every row.
-   Set every relevant view property in `onBindViewHolder()`.

### Wrong content after scrolling

-   Reset visibility, text, images, enabled state, and selection on
    every bind.

### ListAdapter does not update

-   Submit a new list instance.
-   Do not mutate the old submitted list.
-   Check `areItemsTheSame()` and `areContentsTheSame()`.
-   Ensure displayed fields are included in content comparison.

### Fragment binding crashes

-   Initialize in `onViewCreated()`.
-   Clear `_binding` in `onDestroyView()`.
-   Do not access view binding after the view is destroyed.

### Uneven grid spacing

-   Check the math in `ItemDecoration`.
-   Avoid unintentionally combining item margins and decoration offsets.

## 21. Accessibility and testing

Accessibility: - Use large enough touch targets. - Give icon-only
controls meaningful content descriptions. - Do not rely on color alone
to communicate state. - Maintain readable text sizes and contrast. -
Make row/button behavior clear.

Testing: - Unit-test DiffUtil identity and content comparisons. - Test
add/update/delete logic in the ViewModel. - UI-test clicks, scrolling,
empty/loading/error states, and retry. - Test long text, empty data,
large data, rotation, and slow image loading.

## 22. Advanced topics and architecture

Recommended data flow:

``` text
Fragment / Activity
        |
        v
ViewModel (UI state + actions)
        |
        v
Repository
        |
        v
Network / Database
```

The screen observes UI state and submits the list. The adapter binds
views and reports user actions; it should not own network/database
logic.

Example UI state:

``` kotlin
sealed interface StudentUiState {
    data object Loading : StudentUiState
    data class Success(val students: List<Student>) : StudentUiState
    data class Error(val message: String) : StudentUiState
}
```

Use a Kotlin version that supports `sealed interface` and `data object`,
or adapt to a sealed class.

Advanced topics to learn later: - Paging 3 with `PagingDataAdapter` -
`AsyncListDiffer` - Partial binding with `getChangePayload()` - Stable
IDs and their trade-offs - `ConcatAdapter` - Nested RecyclerViews and
shared `RecycledViewPool` - Custom ItemDecoration and ItemAnimator -
Selection and multi-select - Compose `LazyColumn` and `LazyVerticalGrid`

Learn these gradually; master a normal list, click handling,
ListAdapter, and UI states first.

## 23. Learning roadmap

1.  RecyclerView purpose and recycling
2.  Item XML and data model
3.  ViewHolder
4.  `onCreateViewHolder()`
5.  `onBindViewHolder()`
6.  `getItemCount()`
7.  Complete adapter
8.  ViewBinding
9.  Click listener
10. Fragment integration
11. Multiple view types
12. DiffUtil
13. ListAdapter
14. Add/update/delete with immutable lists
15. GridLayoutManager and horizontal lists
16. ItemDecoration and spacing
17. Loading/empty/error states
18. Swipe actions and selection
19. Paging 3
20. ViewModel, Repository, and testing

## 24. Quick checklist

-   [ ] Data model represents each item.
-   [ ] Item XML contains the needed views.
-   [ ] ViewHolder holds the item binding.
-   [ ] Adapter creates and binds ViewHolders correctly.
-   [ ] LayoutManager and adapter are assigned.
-   [ ] Clicks report the correct item.
-   [ ] Recycled state is reset on every bind.
-   [ ] Fragment binding is cleared in `onDestroyView()`.
-   [ ] Dynamic lists use ListAdapter/DiffUtil where appropriate.
-   [ ] Submitted lists are immutable.
-   [ ] Loading, empty, and error states are handled.
-   [ ] Large data sets use paging when appropriate.
-   [ ] Accessibility and performance have been considered.

------------------------------------------------------------------------

## Final summary

**RecyclerView displays collections efficiently by recycling item
views.** The adapter creates and binds ViewHolders, the LayoutManager
arranges items, and ListAdapter + DiffUtil simplify updates when data
changes.

🧠 **One sentence to remember:** RecyclerView manages the display and
recycling, the adapter connects data to views, and ListAdapter +
DiffUtil help update changing lists efficiently.
