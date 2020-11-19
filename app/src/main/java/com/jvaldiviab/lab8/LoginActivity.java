package com.jvaldiviab.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //elementos de la UI
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button btnLogin;

    //elemento para las preferencias
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ubico los elementos por id
        elementsUi();


        //instancia de las pref (donde se guardan)
        pref = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        //voy al metodo que establece en el login las credenciales que han sido guardadas
        setCredentialsIfExist();

        //cuando clickean el btn
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if (login(email, password)) {
                    goMain();
                    safePreferences(email, password);
                }
            }
        });

    }

    private void elementsUi() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        switchRemember = findViewById(R.id.switchRemember);
        btnLogin = findViewById(R.id.buttonLog);
    }

    private boolean login(String email, String password) {
        if (!checkEmail(email)) {
            Toast.makeText(this, "the email is not correct", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (!checkPassword(password)) {
                Toast.makeText(this, "the password need 4 or more characters", Toast.LENGTH_LONG).show();
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean checkEmail(String email) {
        //el TextUtils es lo mismo que email.lenght
        //Patterns.EMAIL_ADDRESS.matcher(email).matches() sirve para revisar que cumpla con lo necesario de un correo
        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPassword(String password) {
        if (password.length() >= 4) {
            return true;
        } else {
            return false;
        }
    }

    private void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        //Para evitar volver al login cuando se le de atrás
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void safePreferences(String email, String password) {
        if (switchRemember.isChecked()) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("email", email);
            editor.putString("pass", password);
            editor.apply();
        }
    }

    private void setCredentialsIfExist() {
        //accedo a lo que se guardo en las preferencias
        String email = Util.getUserEmailPreferences(pref);
        String password = Util.getUserPasswordPreferences(pref);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            editTextEmail.setText(email);
            editTextPassword.setText(password);
            //activo el switch por default
            switchRemember.setChecked(true);
        }
    }
}