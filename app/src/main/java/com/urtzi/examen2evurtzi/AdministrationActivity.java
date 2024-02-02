package com.urtzi.examen2evurtzi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdministrationActivity extends AppCompatActivity {
    DBHandler manager;
    EditText nombre;
    EditText simbolo;
    EditText numero;
    EditText estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        manager = new DBHandler(AdministrationActivity.this);
        nombre = findViewById(R.id.nombre);
        simbolo = findViewById(R.id.simbolo);
        numero = findViewById(R.id.numatomico);
        estado = findViewById(R.id.estado);
        Button insertarButton = findViewById(R.id.anadirelemento);
        insertarButton.setOnClickListener(e -> anadirElemento());
        Button modificarButton = findViewById(R.id.modificarelemento);
        modificarButton.setOnClickListener(e -> modificarElemento());
        Button borrarButton = findViewById(R.id.borrarelemento);
        borrarButton.setOnClickListener(e -> borrarElemento());
        Button volverButton = findViewById(R.id.volver);
        volverButton.setOnClickListener(e -> {
            finish();
        });


    }

    private void anadirElemento() {
        Elemento e = new Elemento(nombre.getText().toString(),simbolo.getText().toString(),numero.getText().toString(),estado.getText().toString());
        if (manager.getElemento(e.getNombre()) != null) {
            MainActivity.ShowDialogWindow(AdministrationActivity.this, "Elemento existente", "Este elemento ya existe");
            return;
        }
        manager.anadirElemento(e);
        MainActivity.ShowDialogWindow(AdministrationActivity.this, "Elemento añadido", "Elemento añadido con éxito.");
    }
    private void modificarElemento() {
        Elemento e = new Elemento(nombre.getText().toString(),simbolo.getText().toString(),numero.getText().toString(),estado.getText().toString());
        if (manager.getElemento(e.getNombre()) == null) {
            MainActivity.ShowDialogWindow(AdministrationActivity.this, "Elemento inexistente", "Este elemento no existe");
            return;
        }
        MainActivity.ShowDialogWindow(AdministrationActivity.this, "Elemento modificado", "Elemento modificado con éxito.");
        manager.modificarElemento(e);
    }
    private void borrarElemento() {
        manager.borrarElemento(nombre.getText().toString());
        MainActivity.ShowDialogWindow(AdministrationActivity.this, "Elemento borrado", "Elemento borrado con éxito.");
    }
}