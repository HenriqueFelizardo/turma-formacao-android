package br.com.cast.turmaformacao.taskmanager.controlles.activities;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.services.UserBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class NewUserActivity extends AppCompatActivity {
    private User user;
    private EditText editTextUsuario;
    private EditText editTextPassword1;
    private EditText editTextZipCode;
    private EditText editTextType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextState;
    private Button buttonRegister;
    private Button buttonTest;

    private class GetAddressTask extends AsyncTask<String, Void, Address> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddresByZipCode(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
            editTextCity.setText(address.getCity().toString());
            editTextState.setText(address.getState().toString());
            editTextNeighborhood.setText(address.getNeighborhood().toString());
            editTextStreet.setText(address.getStreet().toString());
            editTextType.setText(address.getType().toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        initUser();
        bindEditTextUsuario();
        bindEditTextPassword1();
        bindEditTextZipCode();
        bindEditTextType();
        bindEditTextStreet();
        bindEditTextNeighborhood();
        bindEditTextCity();
        bindEditTextState();
        bindButtonRegister();
        bindButtonTest();
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

    private void bindButtonTest() {
        buttonTest = (Button) findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new GetAddressTask().execute(editTextZipCode.getText().toString());
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

    private void bindEditTextState() {
        editTextState = (EditText) findViewById(R.id.editTextState);
    }

    private void bindEditTextCity() {
        editTextCity = (EditText) findViewById(R.id.editTextCity);
    }

    private void bindEditTextNeighborhood() {
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
    }

    private void bindEditTextStreet() {
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
    }

    private void bindEditTextType() {
        editTextType = (EditText) findViewById(R.id.editTextType);
    }

    private void bindEditTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
    }

}
