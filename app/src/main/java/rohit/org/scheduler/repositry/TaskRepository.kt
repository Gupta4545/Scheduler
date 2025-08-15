package rohit.org.scheduler.repositry

import kotlinx.coroutines.flow.Flow
import rohit.org.scheduler.data.Task
import rohit.org.scheduler.data.TaskDao

class TaskRepository(private val dao: TaskDao) {
    val tasks: Flow<List<Task>> = dao.getAllTasks()

    suspend fun addTask(task: Task) = dao.insertTask(task)
    suspend fun removeTask(task: Task) = dao.deleteTask(task)
}