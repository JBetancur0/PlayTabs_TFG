package com.juancho_dam.playtabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView img_menuBack;
    private ImageView img_btnBack;
    private Button btn_buscarTab;
    private EditText input_nom_tab;
    private ImageView img_user_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        rv_tabsH = (RecyclerView) findViewById(R.id.rv_tabsH);
        img_menuBack = findViewById(R.id.img_menuBack);
        btn_buscarTab = findViewById(R.id.btn_buscar_tab);
        img_btnBack = findViewById(R.id.img_btnBack);
        input_nom_tab = findViewById(R.id.input_nom_tab);
        user_name = findViewById(R.id.txt_user_name);
        img_user_pic = findViewById(R.id.img_user_pic);

        img_menuBack.setAlpha(0.0f);
        img_btnBack.setAlpha(0.0f);
        img_btnBack.setClickable(false);
        user_name.setAlpha(0.0f);
        img_user_pic.setAlpha(0.0f);

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

    public void open_menu(View view) {

        img_btnBack.setClickable(true);
        input_nom_tab.setClickable(false);
        input_nom_tab.setFocusable(false);
        rv_tabsH.setClickable(false);
        rv_tabsH.setFocusable(false);
        input_nom_tab.setFocusableInTouchMode(false);
        img_menuBack.animate().alpha(1.0f).setDuration(300);
        btn_buscarTab.animate().alpha(0.0f).setDuration(300);
        img_btnBack.animate().alpha(1.0f).setDuration(300);
        user_name.animate().alpha(1.0f).setDuration(300);
        img_user_pic.animate().alpha(1.0f).setDuration(300);



    }

    public void hide_menu(View view) {

        img_btnBack.setClickable(false);
        input_nom_tab.setClickable(true);
        input_nom_tab.setFocusable(true);
        rv_tabsH.setClickable(true);
        rv_tabsH.setFocusable(true);
        input_nom_tab.setFocusableInTouchMode(true);
        img_menuBack.animate().alpha(0.0f).setDuration(300);
        img_btnBack.animate().alpha(0.0f).setDuration(300);
        btn_buscarTab.animate().alpha(1.0f).setDuration(300);
        user_name.animate().alpha(0.0f).setDuration(300);
        img_user_pic.animate().alpha(0.0f).setDuration(300);

    }
}