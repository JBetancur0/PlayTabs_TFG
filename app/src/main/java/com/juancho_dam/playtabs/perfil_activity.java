package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.juancho_dam.playtabs.utilidades.ImagenesFirebase;

public class perfil_activity extends AppCompatActivity {

    private TextView txt_userName;
    private ImageView img_userPic;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Uri imageURI;
    private StorageReference storageRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txt_userName = findViewById(R.id.txt_profileUserName);
        img_userPic = findViewById(R.id.img_profileUserPic);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        ImagenesFirebase.descargarFoto(currentUser.getUid(), "profilePic", img_userPic);

        txt_userName.setText(String.valueOf(currentUser.getDisplayName()));
    }

    public void cambiar_foto(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data !=null){

            imageURI = data.getData();
            img_userPic.setImageURI(imageURI);

            storageRef = FirebaseStorage.getInstance().getReference().child(currentUser.getUid()).child("profilePic");
            storageRef.putFile(imageURI);
            
        }
    }

    public void cambiar_userName(View view) {



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nuevo nombre de usuario");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            String nuevoNombre = "";
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nuevoNombre = input.getText().toString();

                txt_userName.setText(nuevoNombre);

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(nuevoNombre)
                        .build();
                currentUser.updateProfile(profileUpdates);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


    }
}