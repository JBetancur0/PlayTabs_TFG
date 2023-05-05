package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class iniciar_sesion_activity extends AppCompatActivity {

    public static final String EXTRA_CURRENT_USER = "es.juancho.iniciarS_user";
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText input_email;
    private EditText input_pass;

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            currentUser.reload();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        input_email = findViewById(R.id.input_correoIniciar);
        input_pass = findViewById(R.id.input_passIniciar);

        mAuth = FirebaseAuth.getInstance();

    }

    public void iniciar(View view) {

        String email = String.valueOf(input_email.getText());
        String pass = String.valueOf(input_pass.getText());

        if(email.isEmpty()){

            input_email.setError("Escribe un correo electrónico");

        }
        else if(pass.isEmpty()){

            input_pass.setError("Escribe la contraseña");

        }

        else{

            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(iniciar_sesion_activity.this, "Inicio de Sesión correcto", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(iniciar_sesion_activity.this, main_menu_activity.class);
                                i.putExtra(EXTRA_CURRENT_USER, user);
                                startActivity(i);
                                finish();
                                //updateUI(user);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(iniciar_sesion_activity.this, "Error al Iniciar Sesión, comprueba tus credenciales", Toast.LENGTH_SHORT).show();
                                // updateUI(null);
                            }
                        }
                    });



        }

    }
}