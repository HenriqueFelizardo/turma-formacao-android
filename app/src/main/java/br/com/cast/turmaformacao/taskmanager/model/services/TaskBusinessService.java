package br.com.cast.turmaformacao.taskmanager.model.services;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

public final class TaskBusinessService {

    private List<Task> values = new ArrayList<>();
    private long count = 0;

    private static class Singleton {
        public static final TaskBusinessService instance = new TaskBusinessService();
    }

    private TaskBusinessService() {
        super();
    }

    public static TaskBusinessService getInstance() {
        return Singleton.instance;
    }

    public List<Task> findAll() {
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.addAll(values);
        return tasks;
    }

    public void save(Task task) {
        task.setId(task.getId() == null ? ++count : task.getId());
        values.add(task);
    }
}
