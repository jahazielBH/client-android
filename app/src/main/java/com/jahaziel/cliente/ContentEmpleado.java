package com.jahaziel.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jahaziel.cliente.model.Empleado;
import com.jahaziel.cliente.utils.Api;
import com.jahaziel.cliente.utils.EmpleadoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentEmpleado extends AppCompatActivity {
    EmpleadoService empleadoService;
    List<Empleado> listaEmpleados = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        listView = (ListView) findViewById(R.id.listViewEmp);

        listarEmpleados();

        FloatingActionButton fabE = findViewById(R.id.fabE);
        fabE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContentEmpleado.this,EmpleadoActivity.class);
                intent.putExtra("ID","");
                intent.putExtra("NOMBRE","");
                intent.putExtra("TELEFONO","");
                intent.putExtra("CORREO","");
                intent.putExtra("DIRECCION","");
                startActivity(intent);
            }
        });

    }

    public void listarEmpleados(){
        empleadoService = Api.getEmpleadoService();
        Call<List<Empleado>> call = empleadoService.getEmpleados();
        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(response.isSuccessful()){
                    listaEmpleados = response.body();
                    listView.setAdapter(new EmpleadoAdapter(ContentEmpleado.this,R.layout.content_empleado,listaEmpleados));
                }
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

}
