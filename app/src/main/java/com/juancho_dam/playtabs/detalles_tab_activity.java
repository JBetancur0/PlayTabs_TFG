package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.ChangeImageTransform;
import androidx.transition.Scene;
import androidx.transition.TransitionManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.juancho_dam.playtabs.clases.Tab;
import com.juancho_dam.playtabs.recyclerview.TabViewHolder;
import com.juancho_dam.playtabs.utilidades.ImagenesFirebase;

import java.util.ArrayList;

public class detalles_tab_activity extends AppCompatActivity {

    private Tab t;
    private ImageView viewTab1;
    private ImageView viewTab2;
    private ImageView viewTab3;
    private ImageView viewTab4;
    private ImageView viewTab5;
    private ImageView viewTab6;
    private ImageView viewTab7;
    private ImageView viewTab8;
    private ImageView viewTab9;
    private ImageView viewTab10;
    private ImageView viewTab11;
    private ImageView viewTab12;
    private ImageView viewTab13;
    private ImageView viewTab14;
    private ImageView viewTab15;
    private FirebaseStorage storage;
    private LinearLayout scrollCont;
    private TextView nombreTab;
    private TextView nombreArtista;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private ImageView btn_addFav;
    private boolean isFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tab);

        viewTab1 = findViewById(R.id.tabView1);
        viewTab2 = findViewById(R.id.tabView2);
        viewTab3 = findViewById(R.id.tabView3);
        viewTab4 = findViewById(R.id.tabView4);
        viewTab5 = findViewById(R.id.tabView5);
        viewTab6 = findViewById(R.id.tabView6);
        viewTab7 = findViewById(R.id.tabView7);
        viewTab8 = findViewById(R.id.tabView8);
        viewTab9 = findViewById(R.id.tabView9);
        viewTab10 = findViewById(R.id.tabView10);
        viewTab11 = findViewById(R.id.tabView11);
        viewTab12 = findViewById(R.id.tabView12);
        viewTab13 = findViewById(R.id.tabView13);
        viewTab14 = findViewById(R.id.tabView14);
        viewTab15 = findViewById(R.id.tabView15);

        btn_addFav = findViewById(R.id.btn_addFav);
        scrollCont = findViewById(R.id.scrollContainer);
        nombreTab = findViewById(R.id.txt_nombreTab);
        nombreArtista = findViewById(R.id.txt_nombreArtista);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance();


        Intent i = getIntent();

        String idTab = "";
        StorageReference listRef = null;
        if (i != null){

            t  = (Tab) i.getSerializableExtra(TabViewHolder.EXTRA_TAB_ITEM);
            nombreTab.setText(t.getNombreCancion());
            nombreArtista.setText(t.getArtista());
            idTab = t.getIdCancion();
            listRef = storage.getReference().child(t.getIdCancion()+"/");
        }

        DatabaseReference refFavs = FirebaseDatabase.getInstance().getReference("favUsers").child(currentUser.getDisplayName());
        ArrayList<String> listFavs = new ArrayList<String>();
        refFavs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String s = (String) dataSnapshot.getValue(String.class);
                    listFavs.add(s);
                }

                if (listFavs.contains(t.getIdCancion())){

                    isFav = true;
                }

                else{

                    isFav = false;

                }

                listFavs.clear();

                if (isFav == true){

                    btn_addFav.setImageResource(R.drawable.fav_heart_f);

                }

                else{

                    btn_addFav.setImageResource(R.drawable.fav_heart);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        String finalIdTab = idTab;
        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        int numFotos = 0;
                        ArrayList<ImageView> imagenesTab = new ArrayList<ImageView>();

                        imagenesTab.add(viewTab1);
                        imagenesTab.add(viewTab2);
                        imagenesTab.add(viewTab3);
                        imagenesTab.add(viewTab4);
                        imagenesTab.add(viewTab5);
                        imagenesTab.add(viewTab6);
                        imagenesTab.add(viewTab7);
                        imagenesTab.add(viewTab8);
                        imagenesTab.add(viewTab9);
                        imagenesTab.add(viewTab10);
                        imagenesTab.add(viewTab11);
                        imagenesTab.add(viewTab12);
                        imagenesTab.add(viewTab13);
                        imagenesTab.add(viewTab14);
                        imagenesTab.add(viewTab15);

                        for (StorageReference item : listResult.getItems()) {
                            numFotos++;
                        }
                        String numF = String.valueOf(numFotos);

                        int cont = 1;
                        for (ImageView i : imagenesTab){

                                if (cont <= numFotos){

                                    ImagenesFirebase.descargarFoto(finalIdTab, String.valueOf(cont), i);
                                    cont++;

                            }
                                else{

                                    scrollCont.removeView(i);

                                }
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override

                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }


    public void volverAtras(View view) {

        finish();
    }

    public void addFav(View view) {

        Intent i = getIntent();
        Tab t  = (Tab) i.getSerializableExtra(TabViewHolder.EXTRA_TAB_ITEM);


        if(isFav == true){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Deseas eliminar esta tablatura de favoritos?")
                    .setTitle("Eliminar de Favoritos")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String userName = currentUser.getDisplayName();

                            DatabaseReference myRef = database.getInstance().getReference("favUsers/"+currentUser.getDisplayName()+"/"+t.getIdCancion());
                            myRef.removeValue();

                            myRef.removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError error, DatabaseReference ref) {
                                    if (error != null) {

                                    } else {
                                        Toast.makeText(detalles_tab_activity.this, "La tablatura se eliminó de favoritos", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(detalles_tab_activity.this, "Operación cancelada", Toast.LENGTH_LONG).show();
                        }
                    });
            builder.show();

        }

        else if(isFav == false){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Deseas añadir esta tablatura a favoritos?")
                    .setTitle("Añadir a Favoritos")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            DatabaseReference myRef = database.getReference();

                            String userName = currentUser.getDisplayName();

                            myRef.child("favUsers").child(userName).child(t.getIdCancion()).setValue(t.getIdCancion());

                            Toast.makeText(detalles_tab_activity.this, "La tablatura se añadió a favoritos", Toast.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(detalles_tab_activity.this, "Operación cancelada", Toast.LENGTH_LONG).show();
                        }
                    });
            builder.show();



        }

    }
}