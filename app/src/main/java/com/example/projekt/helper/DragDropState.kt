import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

class DragDropState(
    val listState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    var draggingIndex by mutableStateOf<Int?>(null)
    var dragOffset by mutableStateOf(Offset.Zero)

    fun startDragging(index: Int, offset: Offset) {
        draggingIndex = index
        dragOffset = offset
    }

    fun isDragging(index: Int): Boolean = draggingIndex == index

    fun endDragging() {
        draggingIndex = null
        dragOffset = Offset.Zero
    }

    fun cancelDragging() = endDragging()

    val offset: IntOffset
        get() = IntOffset(0, dragOffset.y.roundToInt())
}

@Composable
fun rememberDragDropState(onMove: (Int, Int) -> Unit): DragDropState {
    val listState = remember { LazyListState() }
    return remember { DragDropState(listState, onMove) }
}
