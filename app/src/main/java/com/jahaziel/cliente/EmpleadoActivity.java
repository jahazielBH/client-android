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

import com.jahaziel.cliente.model.Empleado;
import com.jahaziel.cliente.utils.Api;
import com.jahaziel.cliente.utils.EmpleadoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoActivity extends AppCompatActivity  {
    EmpleadoService empleadoService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_layout);

        TextView Id = (TextView) findViewById(R.id.Id);
        EditText txtId = (EditText) findViewById(R.id.txtId);

        TextView Nombre = (TextView) findViewById(R.id.Nombre);
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);

        TextView Telefono = (TextView) findViewById(R.id.Telefono);
        EditText txtTelefono = (EditText) findViewById(R.id.txtTelefono);

        TextView Correo = (TextView) findViewById(R.id.Correo);
        EditText txtCorreo = (EditText) findViewById(R.id.txtCorreo);

        TextView Direccion = (TextView) findViewById(R.id.Direccion);
        EditText txtDireccion = (EditText) findViewById(R.id.txtDireccion);

        Button btoGuardar = (Button) findViewById(R.id.btoGuardar);
        Button btoRegresar = (Button) findViewById(R.id.btoRegresar);
        Button btoEliminar=(Button)findViewById(R.id.btoEliminar);

        Bundle bundle=getIntent().getExtras();
        String id = bundle.getString("ID");
        String nombre = bundle.getString("NOMBRE");
        String telefono = bundle.getString("TELEFONO");
        String correo = bundle.getString("CORREO");
        String direccion = bundle.getString("DIRECCION");


        txtId.setText(id);
        txtNombre.setText(nombre);
        txtTelefono.setText(telefono);
        txtCorreo.setText(correo);
        txtDireccion.setText(direccion);

        if(id.trim().length()==0||id.equals("")){
            Id.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }

        btoGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empleado e;
                if(id.trim().length()==0||id.equals("")){
                    e = new Empleado();
                    e.setNombre(txtNombre.getText().toString());
                    e.setTelefono(txtTelefono.getText().toString());
                    e.setCorreo(txtCorreo.getText().toString());
                    e.setDireccion(txtDireccion.getText().toString());
                    addEmpleado(e);
                    Intent intent =new Intent(EmpleadoActivity.this,ContentEmpleado.class);
                    startActivity(intent);
                } else {
                    e = new Empleado();
                    e.setId(Long.valueOf(txtId.getText().toString()));
                    e.setNombre(txtNombre.getText().toString());
                    e.setTelefono(txtTelefono.getText().toString());
                    e.setCorreo(txtCorreo.getText().toString());
                    e.setDireccion(txtDireccion.getText().toString());
                    updateEmpleado(e);
                    Intent intent =new Intent(EmpleadoActivity.this,ContentEmpleado.class);
                    startActivity(intent);
                }

            }
        });

        btoRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EmpleadoActivity.this,ContentEmpleado.class);
                startActivity(intent);
            }
        });

        btoEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmpleado(Long.valueOf(id));
                Intent intent =new Intent(EmpleadoActivity.this,ContentEmpleado.class);
                startActivity(intent);
            }
        });
    }

    public void addEmpleado(Empleado e){
        empleadoService = Api.getEmpleadoService();
        Call<Empleado> Call = empleadoService.addEmpleado(e);
        Call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(retrofit2.Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmpleadoActivity.this,"Empleado guardado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Empleado> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(EmpleadoActivity.this,ContentEmpleado.class);
        startActivity(intent);
    }

    public void updateEmpleado(Empleado e){
        empleadoService = Api.getEmpleadoService();
        Call<Empleado> Call = empleadoService.updateEmpleado(e);
        Call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(retrofit2.Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmpleadoActivity.this,"Empleado actualizado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Empleado> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(EmpleadoActivity.this,ContentEmpleado.class);
        startActivity(intent);
    }

    public void deleteEmpleado(Long id){
        empleadoService = Api.getEmpleadoService();
        Call<Empleado>call=empleadoService.deleteEmpleado(id);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmpleadoActivity.this,"Empleado eliminado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(EmpleadoActivity.this,ContentEmpleado.class);
        startActivity(intent);
    }
}
