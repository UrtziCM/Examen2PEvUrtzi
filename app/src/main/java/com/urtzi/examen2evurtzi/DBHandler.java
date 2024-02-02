package com.urtzi.examen2evurtzi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "elementos";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "elemento";

    private static final String ID_COL = "id";

    private static final String NOMBRE_COL = "nombre";

    private static final String SIMBOLO_COL = "simbolo";

    private static final String NUM_COL = "numero";

    private static final String ESTADO_COL = "estado";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOMBRE_COL + " TEXT,"
                + SIMBOLO_COL + " TEXT,"
                + NUM_COL + " TEXT,"
                + ESTADO_COL + " TEXT)";
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void anadirElemento(String nombreElemento, String simboloElemento, String numeroElemento, String estadoElemento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE_COL, nombreElemento);
        values.put(SIMBOLO_COL, simboloElemento);
        values.put(NUM_COL, numeroElemento);
        values.put(ESTADO_COL, estadoElemento);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }
    public void anadirElemento(Elemento elemento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE_COL, elemento.getNombre());
        values.put(SIMBOLO_COL, elemento.getSimbolo());
        values.put(NUM_COL, elemento.getNumero());
        values.put(ESTADO_COL, elemento.getEstado());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void borrarElemento(String nombreElemento) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "nombre = ?",new String[]{nombreElemento});
        db.close();
    }
    public void modificarElemento(Elemento elemento) {
        SQLiteDatabase db = this.getWritableDatabase();
        borrarElemento(elemento.getNombre());
        anadirElemento(elemento);
        db.close();
    }
    public Elemento getElemento(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+ " WHERE nombre = '"+ nombre + "'",null);
        Elemento elemento;
        if (c.moveToFirst()) {
            elemento = new Elemento(c.getString(1),c.getString(2),c.getString(3),c.getString(4));
            c.close();
        } else {
            c.close();
            db.close();
            return null;
        }
        db.close();
        return elemento;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
