package br.com.cast.turmaformacao.taskmanager.model.http;


import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

public class TaskService {
    private final static String URL = "http://10.11.21.193:3000/api/v1/task/";

    private TaskService() {
        super();
    }

    public static List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();

        try{
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.e("getTasks", "Codigo de retorno: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class);
                tasks = objectMapper.readValue(inputStream, collectionType);
            }
        }catch (Exception e){
            Log.e(TaskService.class.getName(), e.getMessage());
        }

        return tasks;
    }

}
