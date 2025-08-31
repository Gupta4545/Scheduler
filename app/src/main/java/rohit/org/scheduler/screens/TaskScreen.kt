import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import rohit.org.scheduler.screens.TaskItem
import rohit.org.scheduler.viewModel.TaskViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.appendPlaceholders

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()

    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(10.dp)) {
        Row {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .weight(0.8f)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(3.dp))
                    .padding(8.dp)

            )
            Spacer(Modifier.width(8.dp))
            IconButton(
                modifier = Modifier
                    .background(Color.White)
                    .border(width = 2.dp, color = Color.LightGray, RoundedCornerShape(10.dp)),
                onClick = {
                    if (text.isNotBlank()) {
                        viewModel.addTask(text)
                        text = ""
                    }
                }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        tasks.forEach { task ->
            TaskItem(task = task, onDelete = { viewModel.removeTask(it) })
        }
        Spacer(modifier = Modifier.height(16.dp))
        clearAll(viewModel = viewModel)
    }
}

@Composable
fun clearAll(viewModel: TaskViewModel) {
    IconButton(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Transparent)
        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
        .padding(8.dp),
        onClick = { viewModel.clearAllTasks() }) {
        Text(
            text = "Clear All",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle(3),
            color = Color.Black
        )
    }
}
