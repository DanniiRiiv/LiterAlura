package com.LiterAlura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Libro {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Autor> autores;
    @Enumerated (EnumType.STRING)
    private Lenguaje lenguaje;
    private Double descargas;

    // Constructor
    public Libro() {}
    public Libro(DataLibro dataLibro) {
        this.titulo = dataLibro.titulo();
        this.autores = dataLibro.autor().stream()
                .map(Autor::new)
                .collect(Collectors.toList());
        // Asocia cada autor con este libro
        this.autores.forEach(autor -> autor.setLibro(this));
        this.lenguaje = dataLibro.lenguajes().get(0);
        this.descargas = dataLibro.descargas();
    }

    // Para imprimir
    @Override
    public String toString() {
        return  "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Libro -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                "Titulo = " + titulo + "\n" +
                "Autor = " + getFormattedAutores() + "\n" +
                "Lenguaje = " + lenguaje.toString().toLowerCase() + "\n" +
                "Numero de descargas = " + descargas + "\n" +
                "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";
    }
    private String getFormattedAutores() {
        StringBuilder sb = new StringBuilder();
        for (Autor autor : autores) {
            String fechaNacimiento = (autor.getFechaNacimiento() == 0) ? "desconocido" : String.valueOf(autor.getFechaNacimiento());
            String fechaMuerte = (autor.getFechaMuerte() == 0) ? "desconocido" : String.valueOf(autor.getFechaMuerte());

            sb.append(autor.getFormattedNombre())
                    .append(" (")
                    .append(fechaNacimiento)
                    .append("-")
                    .append(fechaMuerte)
                    .append(") ")
                    .append("\n");
        }
        return sb.toString().trim();
    }

    // Getters
    public Long getId() {
        return id;
    }
    public Double getDescargas() {
        return descargas;
    }
    public List<Autor> getAutores() {
        return autores;
    }
    public String getTitulo() {
        return titulo;
    }
}