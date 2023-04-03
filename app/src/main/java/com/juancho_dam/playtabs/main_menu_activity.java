package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.juancho_dam.playtabs.clases.Tab;
import com.juancho_dam.playtabs.recyclerview.ListaTabsAdapter;

import java.util.ArrayList;

public class main_menu_activity extends AppCompatActivity {

    private TextView user_name;
    private RecyclerView rv_tabsH = null;
    private ListaTabsAdapter adaptadorTabs;
    private ArrayList<Tab> tabs;
    private DatabaseReference myRefTabs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        user_name = findViewById(R.id.txt_nombre_user);
        rv_tabsH = (RecyclerView) findViewById(R.id.rv_tabsH);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_tabsH.setLayoutManager(manager);
        rv_tabsH.setHasFixedSize(true);

        tabs = new ArrayList<Tab>();
        adaptadorTabs = new ListaTabsAdapter(this, tabs);
        rv_tabsH.setAdapter(adaptadorTabs);


        myRefTabs = FirebaseDatabase.getInstance().getReference("TabsHashmap");
        myRefTabs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adaptadorTabs.getTabs().clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Tab t = (Tab) dataSnapshot.getValue(Tab.class);
                    if(t.getCalificacion() >=4){
                        tabs.add(t);
                        adaptadorTabs.setTabs(tabs);
                        adaptadorTabs.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        Intent i = getIntent();
        FirebaseUser user = i.getParcelableExtra(iniciar_sesion_activity.EXTRA_CURRENT_USER);

        String user_nameFirebase = user.getDisplayName();

        user_name.setText(user_nameFirebase);

    }
}