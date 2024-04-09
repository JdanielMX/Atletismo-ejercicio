/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalTime;


/**
 *
 * @author JDANIEL
 */
public class Atleta {

  
    
    private Long id;
    private int numeroAtleta;
    private String nombre;
    private LocalTime tiempo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public Atleta() {
    }
     
    public Atleta(int numeroAtleta, String nombre, LocalTime tiempo) {
        this.numeroAtleta = numeroAtleta;
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public int getNumeroAtleta() {
        return numeroAtleta;
    }

    public void setNumeroAtleta(int numeroAtleta) {
        this.numeroAtleta = numeroAtleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalTime tiempo) {
        this.tiempo = tiempo;
    }


    
    
    
      @Override
    public String toString() {
        return "Atleta{" + "id=" + id + ", numeroAtleta=" + numeroAtleta + ", nombre=" + nombre + ", tiempo=" + tiempo + '}';
    }
    
}
