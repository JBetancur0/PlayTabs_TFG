package com.juancho_dam.playtabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

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