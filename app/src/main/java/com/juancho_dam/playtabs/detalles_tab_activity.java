package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.juancho_dam.playtabs.clases.Tab;
import com.juancho_dam.playtabs.recyclerview.TabViewHolder;
import com.juancho_dam.playtabs.utilidades.ImagenesFirebase;

import java.util.ArrayList;

public class detalles_tab_activity extends AppCompatActivity {

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
    private ImageView btnAtras;

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
        scrollCont = findViewById(R.id.scrollContainer);
        nombreTab = findViewById(R.id.txt_nombreTab);
        nombreArtista = findViewById(R.id.txt_nombreArtista);
        btnAtras = findViewById(R.id.btn_backDetails);

        storage = FirebaseStorage.getInstance();


        Intent i = getIntent();

        String idTab = "";
        if (i != null){

            Tab t  = (Tab) i.getSerializableExtra(TabViewHolder.EXTRA_TAB_ITEM);
            nombreTab.setText(t.getNombreCancion());
            nombreArtista.setText(t.getArtista());
            idTab = t.getIdCancion();
        }


        StorageReference listRef = storage.getReference().child("1/");

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
                            // All the items under listRef.
                        }
                        String numF = String.valueOf(numFotos);
                        Log.i("numF", numF);

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
                        // Uh-oh, an error occurred!ç
                        Log.i("numF", "Error");
                    }
                });

    }


    public void volverAtras(View view) {

        finish();
    }
}