package com.juancho_dam.playtabs.clases;

public class Tab {

    private String idCancion;
    private String nombreCancion;
    private String artista;
    private int calificacion;

    public Tab(String idCancion, String nombreCancion, String artista, int calificacion) {
        this.idCancion = idCancion;
        this.nombreCancion = nombreCancion;
        this.artista = artista;
        this.calificacion = calificacion;
    }

    public Tab() {
        this.idCancion = "0";
        this.nombreCancion = "";
        this.artista = "";
        this.calificacion = 0;
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
