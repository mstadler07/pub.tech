package com.example.projekt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pubtech_project.NewsViewModel
import androidx.compose.foundation.layout.FlowRow

@Composable
fun TagCloudScreen(viewModel: NewsViewModel = viewModel()) {
    val tagFrequency = viewModel.getTagFrequency()
    val sortedTags = tagFrequency.entries.sortedByDescending { it.value }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Schlagworte",
            fontSize = 20.sp,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))

        FlowRow(
            modifier = Modifier.padding(16.dp)
        ) {
            sortedTags.forEach { (tag, count) ->
                val fontSize = (14 + count * 2).coerceAtMost(32).sp
                val alpha = 0.3f + (count / 10f).coerceAtMost(0.7f)

                Text(
                    text = "#$tag",
                    fontSize = fontSize,
                    color = MaterialTheme.colors.primary.copy(alpha = alpha),
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}