package br.com.cast.turmaformacao.taskmanager.controlles.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.persistence.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonNew;
    public User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
        bindButtonNew();
    }

    public boolean loginCheck() {
        User usuario = new User();

        usuario.setUsuario(editTextLogin.getText().toString());
        usuario.setSenha(editTextPassword.getText().toString());

        user = UserRepository.login(usuario);

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (loginCheck()) {
                    Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                    startActivity(redirectToTaskList);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, R.string.msg_failure, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void bindButtonNew() {
        buttonNew = (Button) findViewById(R.id.buttonNew);
        buttonNew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent redirectToNewUser = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(redirectToNewUser);
            }
        });
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
    }
}
