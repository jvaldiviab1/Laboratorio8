package com.jvaldiviab.lab8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //despues de mostrar el splashActivity
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();

        //tomo los valores del sharePreference
        pref=getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        //creo los intents dependiendo de los preferences
        Intent intentLogin=new Intent(this,LoginActivity.class);
        Intent intentMain=new Intent(this,MainActivity.class);

        //verifico el contenido de las preferences y dependiendo lo envio al Main o al log
        if(!TextUtils.isEmpty(Util.getUserEmailPreferences(pref)) && !TextUtils.isEmpty(Util.getUserPasswordPreferences(pref))){
            startActivity(intentMain);
        }else{
            startActivity(intentLogin);
        }

    }

}
