package com.LiterAlura.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaMuerte;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    // Constructor
    public Autor() {}
    public Autor(DataAutor dataAutor) {
        this.nombre = dataAutor.nombre();
        this.fechaNacimiento = dataAutor.fechaNacimiento();
        this.fechaMuerte = dataAutor.fechaMuerte();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Integer getFechaMuerte() {
        return fechaMuerte;
    }
    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public String getFormattedNombre() {
        String[] partes = nombre.split(", ");
        if (partes.length == 2) {
            return partes[1] + " " + partes[0];
        }
        return nombre;
    }

    @Override
    public String toString() {
         Serializable fechaNacimiento2 = (fechaNacimiento == 0) ? "desconocido" : String.valueOf(fechaNacimiento);
         String fechaMuerte2 = (fechaMuerte == 0) ? "desconocido" : String.valueOf(fechaMuerte);

        return  "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Autor -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                "Nombre = " + nombre + "\n" +
                "Año de nacimiento = " + fechaNacimiento2 + "\n" +
                "Año de defunción = " + fechaMuerte2 + "\n" +
                "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";
    }
}