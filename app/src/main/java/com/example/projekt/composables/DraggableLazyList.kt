import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.pubtech_project.Article

@Composable
fun DraggableLazyList(
    items: List<Article>,
    onMove: (Int, Int) -> Unit,
    itemContent: @Composable (article: Article, isDragging: Boolean) -> Unit
) {
    val dragDropState = rememberDragDropState(onMove)

    LazyColumn(state = dragDropState.listState) {
        itemsIndexed(items) { index, article ->
            DraggableItem(
                dragDropState = dragDropState,
                index = index
            ) { isDragging ->
                itemContent(article, isDragging)
            }
        }
    }
}
