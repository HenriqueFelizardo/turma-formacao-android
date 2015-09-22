package br.com.cast.turmaformacao.taskmanager.controlles.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controlles.adapters.LabelSpinnerAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class TaskFormActivity extends AppCompatActivity {

    public static final String PARAM_TASK = "PARAM_TASK";
    private EditText editTextNome;
    private EditText editTextDescricao;
    private Task task;
    private Label label;
    private Spinner spinnerLabel;
    private Button addButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();

        bindEditTextNome();
        bindEditTextDescricao();
        bindSpinnerLabel();
        bindButtonAdd();
    }

    private void bindButtonAdd() {
        addButton = (Button) findViewById(R.id.buttonNewLabel);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskFormActivity.this, LabelFormActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void onResume() {
        updateLabelSpinner();
        super.onResume();
    }

    private void updateLabelSpinner() {
        List<Label> values = LabelBusinessService.findAll();
        spinnerLabel.setAdapter(new LabelSpinnerAdapter(this, values));
        LabelSpinnerAdapter adapter = (LabelSpinnerAdapter) spinnerLabel.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void bindSpinnerLabel() {
        spinnerLabel = (Spinner) findViewById(R.id.spinnerLabel);

        registerForContextMenu(spinnerLabel);
        spinnerLabel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LabelSpinnerAdapter adapter = (LabelSpinnerAdapter) spinnerLabel.getAdapter();
                label = adapter.getItem(position);

                return false;
            }
        });
    }

    private void initTask() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.task = extras.getParcelable(PARAM_TASK);
        }
        this.task = task == null ? new Task() : task;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save_task:
                onMenuSaveTaskClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSaveTaskClick() {
        String requiredMessage = TaskFormActivity.this.getString(R.string.msg_required);
        if (!FormHelper.validateRequired(requiredMessage, editTextNome, editTextDescricao)) {
            binTask();
            TaskBusinessService.save(task);
            Toast.makeText(TaskFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
            TaskFormActivity.this.finish();
        }
    }

    private void binTask() {
        task.setName(editTextNome.getText().toString());
        task.setDescription(editTextDescricao.getText().toString());
        task.setLabel((Label) spinnerLabel.getSelectedItem());
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
