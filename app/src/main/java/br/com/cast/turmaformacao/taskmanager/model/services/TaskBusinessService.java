package br.com.cast.turmaformacao.taskmanager.model.services;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelRepository;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskRepository;

public final class TaskBusinessService {


    private TaskBusinessService() {
        super();
    }

    public static List<Task> findAll() {
        List<Task> tasks = TaskRepository.getAll();

        for (Task t : tasks) {
            t.setLabel(LabelRepository.getId(t.getLabel().getId()));
        }
        return tasks;
    }

    public static void save(Task task) {
        TaskRepository.save(task);
    }

    public static void delete(Task selectedTask) {
        TaskRepository.delete(selectedTask.getId());
    }
}
