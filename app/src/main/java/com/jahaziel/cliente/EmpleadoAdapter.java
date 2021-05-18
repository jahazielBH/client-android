package com.jahaziel.cliente;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jahaziel.cliente.model.Empleado;

import java.util.List;

public class EmpleadoAdapter extends ArrayAdapter<Empleado> {

    private Context context;
    private List<Empleado> empleados;

    public EmpleadoAdapter(@NonNull Context context, int resource, @NonNull List<Empleado> objects) {
        super(context, resource, objects);
        this.context=context;
        this.empleados=objects;
    }

    public EmpleadoAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Empleado> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.empleados=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_empleado,parent,false);

        TextView txtId = (TextView)rowView.findViewById(R.id.ID);
        TextView txtNombre = (TextView)rowView.findViewById(R.id.NOMBRE);
        TextView txtTelefono = (TextView)rowView.findViewById(R.id.TELEFONO);
        TextView txtCorreo = (TextView)rowView.findViewById(R.id.CORREO);
        TextView txtDireccion = (TextView)rowView.findViewById(R.id.DIRECCION);

        txtId.setText(String.format("ID:%s", empleados.get(position).getId()));
        txtNombre.setText(String.format("NOMBRE:%s", empleados.get(position).getNombre()));
        txtTelefono.setText(String.format("TELEFONO:%s", empleados.get(position).getTelefono()));
        txtCorreo.setText(String.format("CORREO:%s", empleados.get(position).getCorreo()));
        txtDireccion.setText(String.format("DIRECCION:%s", empleados.get(position).getDireccion()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EmpleadoActivity.class);
                intent.putExtra("ID",String.valueOf(empleados.get(position).getId()));
                intent.putExtra("NOMBRE",empleados.get(position).getNombre());
                intent.putExtra("TELEFONO",empleados.get(position).getTelefono());
                intent.putExtra("CORREO",empleados.get(position).getCorreo());
                intent.putExtra("DIRECCION",empleados.get(position).getDireccion());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
