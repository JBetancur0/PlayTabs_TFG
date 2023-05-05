package com.juancho_dam.playtabs.clases;

import java.io.Serializable;

public class Tab implements Serializable {

    private String idCancion;
    private String nombreCancion;
    private String artista;
    private String genero;
    private int calificacion;

    public Tab(String idCancion, String nombreCancion, String artista, String genero,int calificacion) {
        this.idCancion = idCancion;
        this.nombreCancion = nombreCancion;
        this.artista = artista;
        this.genero = genero;
        this.calificacion = calificacion;
    }

    public Tab() {
        this.idCancion = "0";
        this.nombreCancion = "";
        this.artista = "";
        this.genero = "";
        this.calificacion = 0;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(String idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
