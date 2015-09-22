package br.com.cast.turmaformacao.taskmanager.controlles.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.services.UserBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class NewUserActivity extends AppCompatActivity {
    private User user;
    private EditText editTextUsuario;
    private EditText editTextPassword1;
    private Button buttonRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        initUser();
        bindEditTextUsuario();
        bindEditTextPassword1();
        bindButtonRegister();
    }

    private void initUser() {
        this.user = new User();
    }

    private void bindButtonRegister() {
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requiredMessage = NewUserActivity.this.getString(R.string.msg_required);
                if (!FormHelper.validateRequired(requiredMessage, editTextUsuario, editTextPassword1)) {
                    binUser();
                    UserBusinessService.save(user);
                    Toast.makeText(NewUserActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
                    NewUserActivity.this.finish();
                }
            }
        });
    }

    private void binUser() {
        user.setUsuario(editTextUsuario.getText().toString());
        user.setSenha(editTextPassword1.getText().toString());
    }

    private void bindEditTextPassword1() {
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
    }

    private void bindEditTextUsuario() {
        editTextUsuario = (EditText) findViewById(R.id.editTextUser);
    }
}
