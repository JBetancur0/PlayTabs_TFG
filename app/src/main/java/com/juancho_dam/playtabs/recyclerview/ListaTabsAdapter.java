package com.juancho_dam.playtabs.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juancho_dam.playtabs.R;
import com.juancho_dam.playtabs.clases.Tab;

import java.util.ArrayList;

public class ListaTabsAdapter extends RecyclerView.Adapter<TabViewHolder> {

    private Context contexto = null;
    private ArrayList<Tab> tabs = null;
    private LayoutInflater inflater = null;

    public ListaTabsAdapter(Context contexto, ArrayList<Tab> tabs) {
        this.contexto = contexto;
        this.tabs = tabs;
        inflater = LayoutInflater.from(this.contexto);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(ArrayList<Tab> tabs) {
        this.tabs = tabs;
        notifyDataSetChanged();
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public TabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.rv_tabs, parent, false);
        TabViewHolder tv = new TabViewHolder(mItemView, this);
        return tv;
    }

    @Override
    public void onBindViewHolder(@NonNull TabViewHolder holder, int position) {

        Tab t = this.getTabs().get(position);

        holder.getTxt_nombreC().setText(t.getNombreCancion());
        holder.getTxt_artista().setText(t.getArtista());
        holder.getRtbar_tab().setRating(t.getCalificacion());
    }

    @Override
    public int getItemCount() {
        return this.tabs.size();
    }
}
