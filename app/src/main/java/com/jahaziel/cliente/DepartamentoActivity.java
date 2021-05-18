package com.jahaziel.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jahaziel.cliente.model.Departamento;
import com.jahaziel.cliente.utils.Api;
import com.jahaziel.cliente.utils.DepartamentoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartamentoActivity extends AppCompatActivity {

    DepartamentoService departamentoService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.departamento_layout);

        TextView IdD = (TextView) findViewById(R.id.IdD);
        EditText txtIdD = (EditText) findViewById(R.id.txtIdD);

        TextView NombreD = (TextView) findViewById(R.id.NombreD);
        EditText txtNombreD = (EditText) findViewById(R.id.txtNombreD);

        TextView DescripcionD = (TextView) findViewById(R.id.DescripcionD);
        EditText txtDescripcionD = (EditText) findViewById(R.id.txtDescripcionD);

        Button btoGuardar = (Button) findViewById(R.id.btoGuardarD);
        Button btoRegresar = (Button) findViewById(R.id.btoRegresarD);
        Button btoEliminar=(Button)findViewById(R.id.btoEliminarD);

        Bundle bundle=getIntent().getExtras();
        String id = bundle.getString("ID");
        String nombre = bundle.getString("NOMBRE");
        String descripcion = bundle.getString("DESCRIPCION");

        txtIdD.setText(id);
        txtNombreD.setText(nombre);
        txtDescripcionD.setText(descripcion);

        if(id.trim().length()==0||id.equals("")){
            IdD.setVisibility(View.INVISIBLE);
            txtIdD.setVisibility(View.INVISIBLE);
        }

        btoGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Departamento d;
                if(id.trim().length()==0||id.equals("")){
                    d = new Departamento();
                    d.setNombre(txtNombreD.getText().toString());
                    d.setDescripcion(txtDescripcionD.getText().toString());
                    addDepartamento(d);
                    Intent intent =new Intent(DepartamentoActivity.this,ContentDepartamento.class);
                    startActivity(intent);
                } else {
                    d = new Departamento();
                    d.setId(Long.valueOf(txtIdD.getText().toString()));
                    d.setNombre(txtNombreD.getText().toString());
                    d.setDescripcion(txtDescripcionD.getText().toString());
                    updateDepartamento(d);
                    Intent intent =new Intent(DepartamentoActivity.this,ContentDepartamento.class);
                    startActivity(intent);
                }

            }
        });

        btoRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DepartamentoActivity.this,ContentDepartamento.class);
                startActivity(intent);
            }
        });

        btoEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDepartamento(Long.valueOf(id));
                Intent intent =new Intent(DepartamentoActivity.this,ContentDepartamento.class);
                startActivity(intent);
            }
        });
    }

    public void addDepartamento(Departamento d){
        departamentoService = Api.getDepartamentoService();
        Call<Departamento> Call = departamentoService.addDepartamento(d);
        Call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(retrofit2.Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DepartamentoActivity.this,"Departamento guardado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Departamento> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(DepartamentoActivity.this,ContentDepartamento.class);
        startActivity(intent);
    }

    public void updateDepartamento(Departamento d){
        departamentoService = Api.getDepartamentoService();
        Call<Departamento> Call = departamentoService.updateDepartamento(d);
        Call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(retrofit2.Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DepartamentoActivity.this,"Departamento actualizado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Departamento> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(DepartamentoActivity.this,ContentDepartamento.class);
        startActivity(intent);
    }

    public void deleteDepartamento(Long id){
        departamentoService = Api.getDepartamentoService();
        Call<Departamento>call= departamentoService.deleteDepartamento(id);
        call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DepartamentoActivity.this,"Departamento eliminado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Departamento> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(DepartamentoActivity.this,ContentDepartamento.class);
        startActivity(intent);
    }
}
