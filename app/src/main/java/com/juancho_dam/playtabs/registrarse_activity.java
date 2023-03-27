package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registrarse_activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText input_email;
    private EditText input_pass;
    private CheckBox check_terms;


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
        setContentView(R.layout.activity_registrarse);

        input_email = findViewById(R.id.input_correoRegis);
        input_pass = findViewById(R.id.input_passRegis);
        check_terms = findViewById(R.id.check_terminosRegis);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registrarse(View view) {

        String email = String.valueOf(input_email.getText());
        String pass = String.valueOf(input_pass.getText());

        if(email.isEmpty()){

            input_email.setError("Escribe un correo");

        }

        else if(pass.isEmpty() || pass.length() <= 6){

            input_pass.setError("Escribe una contraseña con 6 caracteres o más");

        }

        else if(!check_terms.isChecked()){

            check_terms.setError("Tienes que aceptar los términos y condiciones");

        }

        else{

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(registrarse_activity.this, "Usuario registrado correctamente, Por favor, inicia sesión", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(registrarse_activity.this, iniciar_sesion_activity.class);
                                startActivity(i);
                                finish();
//                            // updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(registrarse_activity.this, "Error al registrar el usuario, es posible que ya exista", Toast.LENGTH_SHORT).show();
                                //  updateUI(null);
                            }
                        }
                    });


        }
    }
}