package com.urtzi.examen2evurtzi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView consultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button consultarBoton = findViewById(R.id.consultar);
        consultarBoton.setOnClickListener(e -> cambiarAConsultar());
        Button qumicoBoton = findViewById(R.id.quimico);
        qumicoBoton.setOnClickListener(e -> cambiarAQuimico());
        Button salirBoton = findViewById(R.id.salir);
        salirBoton.setOnClickListener(e -> {
            finish();
            System.exit(0);
        });
        consultas = findViewById(R.id.consultas_totales);
        consultas.setText("0");
    }

    private void cambiarAQuimico() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void cambiarAConsultar() {
        Intent intent = new Intent(this, SearchActivity.class);
        Bundle bun = new Bundle();
        bun.putInt("total", Integer.parseInt(consultas.getText().toString()));
        intent.putExtras(bun);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 2) {
            consultas.setText(String.valueOf(data.getIntExtra("total", 0)));
        }
    }

    public static void ShowDialogWindow(Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }
}