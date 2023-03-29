package com.juancho_dam.playtabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inicio_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void iniciar_sesion(View view) {

        Intent i = new Intent(this, iniciar_sesion_activity.class);
        startActivity(i);

    }

    public void resgister(View view) {

        Intent i = new Intent(this, registrarse_activity.class);
        startActivity(i);
    }
}