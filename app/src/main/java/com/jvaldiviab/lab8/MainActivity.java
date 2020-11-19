package com.jvaldiviab.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recojo las preferences guardadas
        pref = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_log_out:
                logOut();
                return true;
            case R.id.menu_forget_and_logOut:
                removeSharedPreferences();
                logOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //  ++++    metodos de log out and delete preferences   ++++////
    private void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        //si no se pone no se puede acceder al main principal
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void removeSharedPreferences() {
        //borro las shared preferences
        SharedPreferences.Editor editor = pref.edit();
        editor.clear().apply();
    }
}