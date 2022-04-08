package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Registros extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        lv = (ListView) findViewById(R.id.listview);
        List<Persona> personas = (List<Persona>) getIntent().getSerializableExtra("objlist");
        String[] array = new String[personas.size()];

        for (int i=0; i<personas.size(); i++){
            array[i] = (String) personas.get(i).getNombre()+" "+ personas.get(i).getCurso();

        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        lv.setAdapter(adapter);
    }
}