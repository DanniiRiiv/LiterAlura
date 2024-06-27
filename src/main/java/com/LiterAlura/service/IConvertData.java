package com.LiterAlura.service;

public interface IConvertData {
    <T> T getDates (String json, Class<T> tClass);
}
