package com.jahaziel.cliente;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jahaziel.cliente.model.Empleado;
import com.jahaziel.cliente.utils.Api;
import com.jahaziel.cliente.utils.EmpleadoService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EmpleadoService empleadoService;
    List<Empleado> listaEmpleados = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        listarEmpleados();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EmpleadoActivity.class);
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
                    listView.setAdapter(new EmpleadoAdapter(MainActivity.this,R.layout.content_main,listaEmpleados));
                }
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}