package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PersonaDB extends SQLiteOpenHelper {

    private static final String TAG = "sql";
    private static final String NOMBRE_BANCO = "cadAlumnos.sqLite";
    private static final int VERSION = 1;

    public PersonaDB(Context context) {
        super(context, NOMBRE_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Creando Tabla Persona");
        sqLiteDatabase.execSQL("create table if not exists persona(id integer primary key, nombre text, curso text);");
        Log.d(TAG, "Tabla Creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long save (Persona persona){
        long id = persona.getId();
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("nombre", persona.getNombre());
            values.put("curso",persona.getCurso());
            if(id!=0){
                String id2 = String.valueOf(persona.getId());
                String[] whereArgs = new String[]{id2};
                int count = db.update("persona", values,"id = ?", whereArgs);
                return count;
            }
            else{
                id = db.insert("persona", "",values);
                return id;
            }
        }
        finally {
            db.close();
        }

    }
    public List<Persona> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.query("persona",null,null,null,null,null,null);
            return toList(c);
        }finally {
            db.close();
        }
    }

    @SuppressLint("Range")
    public List<Persona> toList(Cursor c){
        List<Persona> personas = new ArrayList<Persona>();
        if(c.moveToNext()){
            do{
                Persona p = new Persona();
                p.setId(c.getInt(c.getColumnIndex("id")));
                p.setNombre(c.getString(c.getColumnIndex("nombre")));
                p.setCurso(c.getString(c.getColumnIndex("curso")));
                personas.add(p);
            }while (c.moveToNext());
        }
        return personas;
    }
}
