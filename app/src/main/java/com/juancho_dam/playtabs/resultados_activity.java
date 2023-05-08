package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.juancho_dam.playtabs.clases.Tab;
import com.juancho_dam.playtabs.recyclerview.ListaTabsAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class resultados_activity extends AppCompatActivity{

    private Spinner spn_tipoFiltro;
    private Spinner spn_filtro;
    private String nombreTab;
    private ListaTabsAdapter adaptadorTabs;
    private ArrayList<Tab> tabs;
    private ArrayList<String> artistas;
    private ArrayList<String> generos;
    private ArrayList<String> calificaciones;
    private RecyclerView rv_resultados;
    private DatabaseReference refTabs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent i = getIntent();

        if(i != null){

            nombreTab = i.getStringExtra(main_menu_activity.EXTRA_NOMBRE_TAB).toLowerCase(Locale.ROOT);
        }

        spn_tipoFiltro = findViewById(R.id.spn_tipoFiltro);
        spn_filtro = findViewById(R.id.spn_filtro);
        rv_resultados = findViewById(R.id.rv_resultados);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_resultados.setLayoutManager(manager);
        rv_resultados.setHasFixedSize(true);

        tabs = new ArrayList<Tab>();
        artistas = new ArrayList<String>();
        artistas.add("Todo");
        generos = new ArrayList<String>();
        generos.add("Todo");
        calificaciones = new ArrayList<String>();
        calificaciones.add("Todo");
        calificaciones.add("Mejores");
        calificaciones.add("Normales");
        calificaciones.add("Malas");

        refTabs = FirebaseDatabase.getInstance().getReference("TabsHashmap");
        refTabs.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> keys = new ArrayList<String>();

                adaptadorTabs = new ListaTabsAdapter(resultados_activity.this, tabs);
                rv_resultados.setAdapter(adaptadorTabs);

                for(DataSnapshot keynode:snapshot.getChildren()){

                    keys.add(keynode.getKey());
                    Tab t = keynode.getValue(Tab.class);

                    String nombreTLower = t.getNombreCancion().toLowerCase(Locale.ROOT);
                    if(nombreTLower.contains(nombreTab)){

                        tabs.add(keynode.getValue(Tab.class));

                        if(!artistas.contains(t.getArtista())){

                            artistas.add(t.getArtista());
                        }

                        if(!generos.contains(t.getGenero())){

                            generos.add(t.getGenero());
                        }

                    }

                }

                adaptadorTabs.setTabs(tabs);
                adaptadorTabs.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<String> tipoFiltros = new ArrayList<String>();
        tipoFiltros.add("Artista");
        tipoFiltros.add("Género");
        tipoFiltros.add("Calificación");

        ArrayAdapter<String> adTipoFiltros = new ArrayAdapter(this, R.layout.spinner_item, tipoFiltros);
        adTipoFiltros.setDropDownViewResource(R.layout.spinner_item_drop);
        spn_tipoFiltro.setAdapter(adTipoFiltros);

        spn_tipoFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Item1 = tipoFiltros.get(i);

                if(Item1.equalsIgnoreCase("Artista")){

                    ArrayAdapter<String> filtroArtistas = new ArrayAdapter(resultados_activity.this, R.layout.spinner_item, artistas);
                    filtroArtistas.setDropDownViewResource(R.layout.spinner_item_drop);
                    spn_filtro.setAdapter(filtroArtistas);

                    spn_filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String Item1 = artistas.get(i);

                            if(Item1.equalsIgnoreCase("Todo")){

                                adaptadorTabs.setTabs(tabs);
                                adaptadorTabs.notifyDataSetChanged();

                            }

                            else{

                                ArrayList<Tab> tabsFiltradas = new ArrayList<Tab>();

                                for(Tab t: tabs){

                                    if(t.getArtista().equalsIgnoreCase(Item1)){

                                        tabsFiltradas.add(t);

                                    }

                                }

                                adaptadorTabs.setTabs(tabsFiltradas);
                                adaptadorTabs.notifyDataSetChanged();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }

                else if(Item1.equalsIgnoreCase("Género")){

                    ArrayAdapter<String> filtroGeneros = new ArrayAdapter(resultados_activity.this, R.layout.spinner_item, generos);
                    filtroGeneros.setDropDownViewResource(R.layout.spinner_item_drop);
                    spn_filtro.setAdapter(filtroGeneros);

                    spn_filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String Item1 = generos.get(i);

                            if(Item1.equalsIgnoreCase("Todo")){

                                adaptadorTabs.setTabs(tabs);
                                adaptadorTabs.notifyDataSetChanged();

                            }

                            else{

                                ArrayList<Tab> tabsFiltradas = new ArrayList<Tab>();

                                for(Tab t: tabs){

                                    if(t.getGenero().equalsIgnoreCase(Item1)){

                                        tabsFiltradas.add(t);

                                    }

                                }

                                adaptadorTabs.setTabs(tabsFiltradas);
                                adaptadorTabs.notifyDataSetChanged();

                            }



                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }

                else if(Item1.equalsIgnoreCase("Calificación")){

                    ArrayAdapter<String> filtroCalif = new ArrayAdapter(resultados_activity.this, R.layout.spinner_item, calificaciones);
                    filtroCalif .setDropDownViewResource(R.layout.spinner_item_drop);
                    spn_filtro.setAdapter(filtroCalif );

                    spn_filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            String Item1 = calificaciones.get(i);

                            if(Item1.equalsIgnoreCase("Todo")){

                                adaptadorTabs.setTabs(tabs);
                                adaptadorTabs.notifyDataSetChanged();

                            }

                            else{

                                ArrayList<Tab> tabsFiltradas = new ArrayList<Tab>();

                                if(Item1.equalsIgnoreCase("Mejores")){

                                    for(Tab t: tabs){

                                        if(t.getCalificacion()>= 4){

                                            tabsFiltradas.add(t);

                                        }

                                    }

                                    adaptadorTabs.setTabs(tabsFiltradas);
                                    adaptadorTabs.notifyDataSetChanged();
                                }

                                else if(Item1.equalsIgnoreCase("Normales")){

                                    for(Tab t: tabs){

                                        if(t.getCalificacion()>=2 && t.getCalificacion()<=3){

                                            tabsFiltradas.add(t);

                                        }

                                    }

                                    adaptadorTabs.setTabs(tabsFiltradas);
                                    adaptadorTabs.notifyDataSetChanged();

                                }

                                else if(Item1.equalsIgnoreCase("Malas")){

                                    for(Tab t: tabs){

                                        if(t.getCalificacion()<2){

                                            tabsFiltradas.add(t);

                                        }

                                    }

                                    adaptadorTabs.setTabs(tabsFiltradas);
                                    adaptadorTabs.notifyDataSetChanged();

                                }


                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


}