package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eNombre;
    private EditText eCurso;
    private Button btnEnviar;
    private Button btnBuscar;
    private PersonaDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre = (EditText) findViewById(R.id.nombre);
        eCurso = (EditText) findViewById(R.id.curso);
        btnBuscar = (Button) findViewById(R.id.buscar);
        btnEnviar = (Button) findViewById(R.id.enviar);

        btnBuscar.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);

        db = new PersonaDB(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.enviar){
            Persona p = new Persona();
            p.setNombre(eNombre.getText().toString().trim());
            p.setCurso(eCurso.getText().toString().trim());
            db.save(p);


        }else if(v.getId() == R.id.buscar){

            List<Persona> personas = db.findAll();
            for (int i = 0; i<personas.size(); i++){
                System.out.println(personas.get(i).getNombre()+" " +personas.get(i).getCurso());
            }
            Intent i = new Intent(this, Registros.class);
            i.putExtra("objlist",(Serializable) personas);
            startActivity(i);
        }
    }
}