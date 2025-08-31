package rohit.org.scheduler.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

import rohit.org.scheduler.data.Task


@Composable
fun TaskItem(task: Task, onDelete: (Task) -> Unit) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(2.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(text = task.title, fontFamily = FontFamily.Monospace, color = Color.White)
            }
            Column() {
                IconButton(
                    onClick = { onDelete(task) }) {
                    Text("🗑", color = Color.Red)
                }
            }
        }
    }
}


