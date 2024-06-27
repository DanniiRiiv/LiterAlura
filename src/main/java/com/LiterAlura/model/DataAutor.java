package com.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DataAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int fechaNacimiento,
        @JsonAlias("death_year") int fechaMuerte
) { }
