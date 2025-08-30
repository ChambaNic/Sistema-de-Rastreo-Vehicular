package com.example.prueba.model;



public class Ruta {
    public String fecha, conductor, vehiculo;
    public int numeroPaquetes;

    public Ruta(String fecha, String conductor, String vehiculo, int numeroPaquetes){
        this.fecha = fecha;
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.numeroPaquetes = numeroPaquetes;
    }
}
