package com.juancho_dam.playtabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class main_menu_activity extends AppCompatActivity {

    private TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        user_name = findViewById(R.id.txt_nombre_user);

        Intent i = getIntent();
        FirebaseUser user = i.getParcelableExtra(iniciar_sesion_activity.EXTRA_CURRENT_USER);

        String user_nameFirebase = user.getDisplayName();

        user_name.setText(user_nameFirebase);
    }
}