package com.example.antniofernandes.alusydroid;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import static android.content.Context.MODE_APPEND;

public class janelanovo extends AppCompatDialogFragment {
 EditText et1,et2,et3,et4;
    private ExampleDialogListener2 listener21;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.novocliente, null);
        builder.setView(view)
                .setTitle("Selecione 2")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String cliente = et1.getText().toString();
                        String telefone = et2.getText().toString();
                        String morada = et3.getText().toString();
                        String nomecontacto = et4.getText().toString();
                        listener21.applyTexts5(cliente,morada,telefone,nomecontacto);
                        escreverclientes123(cliente);
                        escrevermoradas123(morada);
                       escrevercontactos123(telefone);
                       escreverresponsavel123(nomecontacto);
                    }
                });
         et1 = (EditText) view.findViewById(R.id.editText);
         et2= (EditText) view.findViewById(R.id.editText2);
         et3 = (EditText) view.findViewById(R.id.editText3);
         et4= (EditText) view.findViewById(R.id.editText15);
        return builder.create();


    }
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener21 = (ExampleDialogListener2) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    public interface ExampleDialogListener2 {

        void applyTexts5 (String cliente,String morada, String telefone, String nomecontacto);
    }




    public void escreverclientes123(String s1){

        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), "clientes.txt");


        FileWriter writer = null;
        try {
            writer = new FileWriter(textfile,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(s1+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void escrevermoradas123(String s1){

        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), "moradas.txt");


        FileWriter writer = null;
        try {
            writer = new FileWriter(textfile,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(s1+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void escrevercontactos123(String s1){

        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), "contactos.txt");


        FileWriter writer = null;
        try {
            writer = new FileWriter(textfile,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(s1+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void escreverresponsavel123(String s1){

        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), "responsavel.txt");


        FileWriter writer = null;
        try {
            writer = new FileWriter(textfile,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(s1+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}