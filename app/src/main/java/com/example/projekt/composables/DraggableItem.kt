import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset

@Composable
fun DraggableItem(
    dragDropState: DragDropState,
    index: Int,
    modifier: Modifier = Modifier,
    content: @Composable (isDragging: Boolean) -> Unit
) {
    val isDragging = dragDropState.isDragging(index)

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset -> dragDropState.startDragging(index, offset) },
                    onDragEnd = { dragDropState.endDragging() },
                    onDragCancel = { dragDropState.cancelDragging() },
                    onDrag = { _, dragAmount ->
                        dragDropState.dragOffset += dragAmount
                    }
                )
            }
            .offset { if (isDragging) dragDropState.offset else IntOffset.Zero }
    ) {
        content(isDragging)
    }
}
