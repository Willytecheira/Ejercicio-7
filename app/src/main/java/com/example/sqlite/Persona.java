package com.example.sqlite;

import java.io.Serializable;

public class Persona implements Serializable {
    private int id;
    private String nombre;
    private String curso;

    public Persona(){
        this.id = 0;
        this.nombre = "";
        this.curso = "";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
