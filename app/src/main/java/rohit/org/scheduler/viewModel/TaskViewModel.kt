package rohit.org.scheduler.viewModel

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rohit.org.scheduler.data.Task
import rohit.org.scheduler.data.TaskDatabase
import rohit.org.scheduler.repositry.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val db = TaskDatabase.getInstance(application)
    private val repository = TaskRepository(db.taskDao())

    val tasks: StateFlow<List<Task>> = repository.tasks.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.addTask(Task(title = title))
        }
    }

    fun removeTask(task: Task) {
        viewModelScope.launch {
            repository.removeTask(task)
        }
    }

    fun clearAllTasks()=viewModelScope.launch {
        repository.clearAllTask()
    }
}