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

import com.jahaziel.cliente.model.Departamento;

import java.util.List;

public class DepartamentoAdapter extends ArrayAdapter<Departamento> {

    private Context context;
    private List<Departamento> departamentos;

    public DepartamentoAdapter(@NonNull Context context, int resource, @NonNull List<Departamento> objects) {
        super(context, resource, objects);
        this.context=context;
        this.departamentos =objects;
    }

    public DepartamentoAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Departamento> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.departamentos =objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_departamento,parent,false);

        TextView txtIdD = (TextView)rowView.findViewById(R.id.ID_D);
        TextView txtNombreD = (TextView)rowView.findViewById(R.id.NOMBRE_D);
        TextView txtDescripcionD = (TextView)rowView.findViewById(R.id.DESCRIPCION_D);

        txtIdD.setText(String.format("ID:%s", departamentos.get(position).getId()));
        txtNombreD.setText(String.format("NOMBRE:%s", departamentos.get(position).getNombre()));
        txtDescripcionD.setText(String.format("DESCRIPCION:%s", departamentos.get(position).getDescripcion()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DepartamentoActivity.class);
                intent.putExtra("ID",String.valueOf(departamentos.get(position).getId()));
                intent.putExtra("NOMBRE", departamentos.get(position).getNombre());
                intent.putExtra("DESCRIPCION", departamentos.get(position).getDescripcion());
                context.startActivity(intent);
            }
        });
        return rowView;
    }

}
