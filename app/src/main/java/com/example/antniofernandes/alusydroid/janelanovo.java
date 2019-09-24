package com.example.antniofernandes.alusydroid;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class janelanovo extends AppCompatDialogFragment {
 EditText et1,et2,et3,et4,et5;
    private ExampleDialogListener2 listener21;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.novocliente, null);

        builder.setView(view)
                .setTitle("Insira os dados do cliente")
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
                        String localidade = et5.getText().toString();
                        listener21.applyTexts5(localidade,morada,telefone,nomecontacto,cliente);
                        escreverclientes123(cliente);
                        escrevermoradas123(morada);
                       escrevercontactos123(telefone);
                       escreverresponsavel123(nomecontacto);
                       escreverlocalidades123(localidade);



                    }
                });
         et1 = (EditText) view.findViewById(R.id.editText);
         et2= (EditText) view.findViewById(R.id.editText2);
         et3 = (EditText) view.findViewById(R.id.editText3);
         et4= (EditText) view.findViewById(R.id.editText15);
         et5= (EditText) view.findViewById(R.id.editText17);
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

        void applyTexts5 (String localidade,String morada, String telefone, String nomecontacto, String cliente);

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

    public void escreverlocalidades123(String s1){

        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), "localidades.txt");


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
