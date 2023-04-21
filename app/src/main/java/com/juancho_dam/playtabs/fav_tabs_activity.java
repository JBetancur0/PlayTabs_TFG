package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.juancho_dam.playtabs.clases.Tab;
import com.juancho_dam.playtabs.recyclerview.ListaTabsAdapter;

import java.util.ArrayList;

public class fav_tabs_activity extends AppCompatActivity {

    private RecyclerView rv_favTabs;
    private ListaTabsAdapter adaptadorTabs;
    private ArrayList<Tab> tabs;
    private DatabaseReference myRefTabs = null;
    private DatabaseReference myRefTabsF = null;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();
        adaptadorTabs.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_tabs);
        rv_favTabs = (RecyclerView) findViewById(R.id.rv_favTabs);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_favTabs.setLayoutManager(manager);
        rv_favTabs.setHasFixedSize(true);

        tabs = new ArrayList<Tab>();
        adaptadorTabs = new ListaTabsAdapter(this, tabs);
        rv_favTabs.setAdapter(adaptadorTabs);

        myRefTabs = FirebaseDatabase.getInstance().getReference("TabsHashmap");
        myRefTabsF = FirebaseDatabase.getInstance().getReference("favUsers").child(currentUser.getDisplayName());

        ArrayList<String> favTabs = new ArrayList<String>();

        myRefTabsF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String s = (String) dataSnapshot.getValue(String.class);
                    favTabs.add(s);

                }

                myRefTabs.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        adaptadorTabs.getTabs().clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            Tab t = (Tab) dataSnapshot.getValue(Tab.class);
                            if(favTabs.contains(t.getIdCancion())){
                                tabs.add(t);
                                adaptadorTabs.setTabs(tabs);
                                adaptadorTabs.notifyDataSetChanged();
                            }
                        }
                        favTabs.clear();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}