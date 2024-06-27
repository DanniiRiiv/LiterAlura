package com.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Lenguaje {
    ALEMAN("de"),
    ARMENIO("hy"),
    BIELORRUSO("be"),
    CHINO("zh"),
    COREANO("ko"),
    CASTELLANO("es"),
    FRANCES("fr"),
    GAELICO("gd"),
    GALES("cy"),
    GRIEGO("e"),
    GROENLANDES("k"),
    INGLES("en"),
    IRLANDES("ga"),
    ITALIANO("it"),
    JAPONES("ja"),
    LATIN("la"),
    MONGOL("mn"),
    NAVAJO("nv"),
    NEERLANDES("nl"),
    NORUEGO("no"),
    POLACO("pl"),
    PORTUGUES("pt"),
    RUMANO("ro"),
    RUSO("ru"),
    SUECO("sv");

    private final String codigo;

    Lenguaje(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

}