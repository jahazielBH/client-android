package com.jahaziel.cliente.utils;

import com.jahaziel.cliente.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmpleadoService {

    @GET("empleado")
    Call<List<Empleado>> getEmpleados();

    @GET("empleado/{id}")
    Call<Empleado> getEmpleado(@Body Empleado empleado, @Path("id") int id);

    @POST("empleado")
    Call<Empleado> addEmpleado(@Body Empleado empleado);

    @PUT("empleado")
    Call<Empleado> updateEmpleado(@Body Empleado empleado);

    @DELETE("empleado/{id}")
    Call<Empleado> deleteEmpleado(@Path("id")Long id);
}
