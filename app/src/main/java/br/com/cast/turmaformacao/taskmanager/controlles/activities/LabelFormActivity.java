package br.com.cast.turmaformacao.taskmanager.controlles.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controlles.adapters.ColorListAdapter;
import br.com.cast.turmaformacao.taskmanager.controlles.adapters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class LabelFormActivity extends AppCompatActivity {

    public static final String PARAM_LABEL = "PARAM_LABEL";
    private Label label;
    private EditText editTextName;
    private EditText editTextDescription;
    private Spinner spinnerColor;
    private Color colorSelect;
    private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);

        initLabel();
        bindEditTextName();
        bindEditTextDescription();
        bindSpinnerColor();
        bindList();
        bindListColorView();
    }

    public void bindList() {
        list = (ListView) findViewById(R.id.listViewId);
        registerForContextMenu(list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LabelListAdapter adapter = (LabelListAdapter) list.getAdapter();
                label = adapter.getItem(position);
                return false;
            }
        });
    }

    private void bindListColorView() {
        list = (ListView) findViewById(R.id.listViewId);
    }

    protected void onResume() {
        updateLabelList();
        super.onResume();
    }

    private void updateLabelList() {
        List<Label> values = LabelBusinessService.findAll();
        list.setAdapter(new LabelListAdapter(this, values));
        LabelListAdapter adapter = (LabelListAdapter) list.getAdapter();
        adapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_label, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save_label:
                onMenuSaveClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindSpinnerColor() {
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        Color[] list = Color.values();

        ColorListAdapter colorAdapter = new ColorListAdapter(LabelFormActivity.this, list);

        spinnerColor.setAdapter(colorAdapter);
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(label.getDescription() == null ? "" : label.getDescription());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(label.getName() == null ? "" : label.getName());
    }

    private void onMenuSaveClick() {
        String requiredMessage = LabelFormActivity.this.getString(R.string.msg_required);
        if (!FormHelper.validateRequired(requiredMessage, editTextName, editTextDescription)) {
            binLabel();
            LabelBusinessService.save(label);
            Toast.makeText(LabelFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
            updateLabelList();
            finish();
        }
    }

    private void binLabel() {
        label.setName(editTextName.getText().toString());
        label.setDescription(editTextDescription.getText().toString());
        colorSelect = (Color) spinnerColor.getSelectedItem();
        label.setColor(colorSelect);
    }

    private void initLabel() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.label = (Label) extras.getParcelable(PARAM_LABEL);
        }
        this.label = label == null ? new Label() : label;
    }
}


