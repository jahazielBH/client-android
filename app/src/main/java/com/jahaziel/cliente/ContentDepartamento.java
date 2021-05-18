package com.jahaziel.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jahaziel.cliente.model.Departamento;
import com.jahaziel.cliente.utils.Api;
import com.jahaziel.cliente.utils.DepartamentoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentDepartamento extends AppCompatActivity {

    DepartamentoService departamentoService;
    List<Departamento> listaDepartamentos = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento);

        listView = (ListView) findViewById(R.id.listViewDep);

        listarDepartamentos();

        FloatingActionButton fabD = findViewById(R.id.fabD);
        fabD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContentDepartamento.this,DepartamentoActivity.class);
                intent.putExtra("ID","");
                intent.putExtra("NOMBRE","");
                intent.putExtra("DESCRIPCION","");
                startActivity(intent);
            }
        });

    }

    public void listarDepartamentos(){
        departamentoService = Api.getDepartamentoService();
        Call<List<Departamento>> call = departamentoService.getDepartamentos();
        call.enqueue(new Callback<List<Departamento>>() {
            @Override
            public void onResponse(Call<List<Departamento>> call, Response<List<Departamento>> response) {
                if(response.isSuccessful()){
                    listaDepartamentos = response.body();
                    listView.setAdapter(new DepartamentoAdapter(ContentDepartamento.this,R.layout.content_departamento,listaDepartamentos));
                }
            }

            @Override
            public void onFailure(Call<List<Departamento>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }
}
