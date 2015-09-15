package br.com.cast.turmaformacao.taskmanager.controlles.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class TaskFormActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextDescricao;
    private Button buttonIncluir;
    private Task task;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();

        bindEditTextNome();
        bindButtonIncluir();
        bindEditTextDescricao();
    }

    private void initTask() {
        this.task = new Task();
    }

    public void bindButtonIncluir() {
        buttonIncluir = (Button) findViewById(R.id.buttonInclude);
        buttonIncluir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String requiredMessage = TaskFormActivity.this.getString(R.string.msg_required);
                if(!FormHelper.validateRequired(requiredMessage, editTextNome, editTextDescricao)){
                    binTask();
                    TaskBusinessService.getInstance().save(task);
                    Toast.makeText(TaskFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
                    TaskFormActivity.this.finish();
                }
            }
        });
    }

    private void binTask(){
        task.setName(editTextNome.getText().toString());
        task.setDescription(editTextDescricao.getText().toString());
    }

    public void bindEditTextNome() {
        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextNome.setText(task.getName() == null ? "" : task.getName());
    }

    public void bindEditTextDescricao() {
        editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);
        editTextDescricao.setText(task.getDescription() == null ? "" : task.getDescription());
    }
}
