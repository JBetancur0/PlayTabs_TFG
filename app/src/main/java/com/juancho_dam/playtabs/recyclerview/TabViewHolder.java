package com.juancho_dam.playtabs.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juancho_dam.playtabs.R;
import com.juancho_dam.playtabs.clases.Tab;

public class TabViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView txt_nombreC;
    private TextView txt_artista;
    private RatingBar rtbar_tab;

    private ListaTabsAdapter lta;
    private Context contexto;


    public TextView getTxt_nombreC() {
        return txt_nombreC;
    }

    public void setTxt_nombreC(TextView txt_nombreC) {
        this.txt_nombreC = txt_nombreC;
    }

    public TextView getTxt_artista() {
        return txt_artista;
    }

    public void setTxt_artista(TextView txt_artista) {
        this.txt_artista = txt_artista;
    }

    public RatingBar getRtbar_tab() {
        return rtbar_tab;
    }

    public void setRtbar_tab(RatingBar rtbar_tab) {
        this.rtbar_tab = rtbar_tab;
    }

    public ListaTabsAdapter getLta() {
        return lta;
    }

    public void setLta(ListaTabsAdapter lta) {
        this.lta = lta;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public TabViewHolder(@NonNull View itemView, ListaTabsAdapter listaTabs) {
        super(itemView);

        txt_nombreC = (TextView) itemView.findViewById(R.id.rv_tabsh_txtNombreC);
        txt_artista = (TextView) itemView.findViewById(R.id.rv_tabsh_txtArtistaC);
        rtbar_tab = (RatingBar) itemView.findViewById(R.id.rtbar_tabsh);
        lta = listaTabs;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


    }
}
