package com.LiterAlura.repository;

import com.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :year AND (a.fechaMuerte >= :year OR a.fechaMuerte IS NULL)")
    List<Autor> findAuthorsAliveDuringYear(@Param("year") Integer year);
}
