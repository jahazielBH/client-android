package com.jahaziel.cliente.utils;

public class Api {
    //Cambiar IP de tu computadora y cambiar el Puerto donde esta executando la api
    public static final String URL_API="http://10.0.0.15:8080/api/v1/crud/";

    public static EmpleadoService getEmpleadoService(){
        return  Cliente.getClient(URL_API).create(EmpleadoService.class);
    }

    public static DepartamentoService getDepartamentoService(){
        return  Cliente.getClient(URL_API).create(DepartamentoService.class);
    }
}
