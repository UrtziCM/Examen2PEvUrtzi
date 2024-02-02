package com.urtzi.examen2evurtzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    int consultas;
    EditText buscarText;
    TextView info;
    DBHandler handler;
    final String mensajeError = "No se ha encontrado el elemento especificado, prueba con otro nombre";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        buscarText = findViewById(R.id.buscar_nombre);
        Button volverButton = findViewById(R.id.volver);
        volverButton.setOnClickListener(e -> volverConsulta());
        info = findViewById(R.id.buscar_resultado);

        consultas = this.getIntent().getExtras().getInt("total");

        handler = new DBHandler(SearchActivity.this);
        limpiar();
    }

    private void volverConsulta() {
        Intent intent = new Intent();
        Bundle b = new Bundle();
        intent.putExtra("total",consultas);
        System.out.println(consultas);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void buscarElementoEnBD(String elementName) {
        Elemento e = handler.getElemento(buscarText.getText().toString());
        if (e != null) {
            consultas++;
            info.setText(e.toString());
            Button buscarButton = findViewById(R.id.buscar_elemento);
            buscarButton.setText("Limpiar");
            buscarButton.setOnClickListener(event -> limpiar());
        } else {
            MainActivity.ShowDialogWindow(SearchActivity.this,"Elemento no encontrado", mensajeError);
        }
    }

    private void limpiar() {
        buscarText.setText("");
        info.setText("");
        Button buscarButton = findViewById(R.id.buscar_elemento);
        buscarButton.setText("Buscar");
        buscarButton.setOnClickListener(e -> buscarElementoEnBD(buscarText.getText().toString()));
    }
}