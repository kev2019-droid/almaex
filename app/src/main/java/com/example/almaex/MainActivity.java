package com.example.almaex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et_nombre,et_contenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_nombre = findViewById(R.id.txtnombre);
        et_contenido  = findViewById(R.id.txtcontenido);
    }
    public void Guardar(View v){
        String nombre=et_nombre.getText().toString();
        String contenido = et_contenido.getText().toString();

        try{

            //ruta a seguir para guardar la tarjeta sd
            //el tipo file permite guardar de manera temporal la ruta de la tarjeta sd
            //
            File tarjetaSD = new File(getApplicationContext().getExternalFilesDir(null).toString());
            Toast.makeText(this, tarjetaSD.getPath(),Toast.LENGTH_SHORT).show();
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            crearArchivo.write(contenido);
            crearArchivo.flush();
            crearArchivo.close();

            Toast.makeText(this,"Guardado correctamente",Toast.LENGTH_SHORT).show();
            et_nombre.setText("");
            et_contenido.setText("");



        }catch (IOException e){
            Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
        }
    }
    public void Consultar(View v){
        //crear una variable para recuperar el archivo que el usuario quiere consultar
        String nombre = et_nombre.getText().toString();

        try{
            File tarjetaSD = new File(getApplicationContext().getExternalFilesDir(null).toString());
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            InputStreamReader abrirarchivo = new InputStreamReader(openFileInput(nombre));
            BufferedReader leerarchivo = new BufferedReader(abrirarchivo);
            String linea = leerarchivo.readLine();
            String contenidocompleto = "";
            while(linea != null){
                contenidocompleto = contenidocompleto+linea+"\n";
                linea = leerarchivo.readLine();
            }
            leerarchivo.close();
            abrirarchivo.close();
            et_contenido.setText(contenidocompleto);




        }catch (IOException e){
            Toast.makeText(this,"Error al guardar el archivo",Toast.LENGTH_SHORT).show();
        }

    }
}
