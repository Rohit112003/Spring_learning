package com.app.todo.service;

import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {

        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {

        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task =taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Taks id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);


    }
}
