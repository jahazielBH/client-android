package com.jahaziel.cliente.utils;

import com.jahaziel.cliente.model.Departamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartamentoService {

    @GET("departamento")
    Call<List<Departamento>> getDepartamentos();

    @GET("departamento/{id}")
    Call<Departamento> getDepartamento(@Body Departamento departamento, @Path("id") int id);

    @POST("departamento")
    Call<Departamento> addDepartamento(@Body Departamento departamento);

    @PUT("departamento")
    Call<Departamento> updateDepartamento(@Body Departamento departamento);

    @DELETE("departamento/{id}")
    Call<Departamento> deleteDepartamento(@Path("id")Long id);

}
