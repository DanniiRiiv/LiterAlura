package com.LiterAlura.repository;

import com.LiterAlura.model.Lenguaje;
import com.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Método para encontrar un libro por su título
    Optional<Libro> findByTitulo(String titulo);

    // Método para mostrar el top 10 de libros por descargas
    List<Libro> findTop10ByOrderByDescargasDesc();

    // Método para buscar por lenguaje
    List<Libro> findByLenguaje(Lenguaje lenguaje);

    @Query("SELECT DISTINCT l.lenguaje FROM Libro l")
    List<Lenguaje> findDistinctLenguajes();
}
