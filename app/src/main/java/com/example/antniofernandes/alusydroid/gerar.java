package com.example.antniofernandes.alusydroid;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class gerar extends AppCompatActivity implements janelanovo.ExampleDialogListener2, TimePickerDialog.OnTimeSetListener {
    public static String File_Name ="clientes.txt";
    public static String File_Name2 ="contactos.txt";
    public static String File_Name3 ="moradas.txt";
    public static String File_Name4 ="responsavel.txt";
    public static String File_Name5 = "localidades.txt";
    public ArrayList<String> cl = new ArrayList<>();
    public String firm;
    public String contacto;
    public String nomecontacto;
    public String localidade;

    private TextView contacto1;
    private TextView nomecontacto1;

    public String localizacao;
    private TextView localizacao1;
    private EditText localidade1;
    public String spinner;
    private Spinner spinner1;
    public RadioButton sim;
    public RadioButton nao;
    public AutoCompleteTextView cliente;
    public String horadechegada;
    public int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar);
        Button prosseguir =(Button) findViewById(R.id.button8);
        localizacao1=(TextView) findViewById(R.id.local);
        contacto1 = (EditText) findViewById(R.id.contact);
        nomecontacto1=(TextView) findViewById(R.id.responsavel) ;
        localidade1=(EditText) findViewById(R.id.editText16);

        final AutoCompleteTextView cliente = (AutoCompleteTextView) findViewById(R.id.empresa);
        final String[] listaclientes=lerclientes();
        final String[] listacontactos=lercontactos();
        final String[] listamorada=lermoradas();
        final String[] listanomecontacto=lernomecontacto();
        final String[] listalocalidades = lerlocalidades();
        final EditText hora_chegada= (EditText) findViewById(R.id.editText4);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaclientes);
        cliente.setThreshold(3);
        cliente.setAdapter(adapter);
        //Toast.makeText(this, listamorada[0], Toast.LENGTH_SHORT).show();
        hora_chegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker= new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(),"time picker");

            }
        });


        Button adicionar = (Button) findViewById(R.id.button7);
        adicionar.setVisibility(View.INVISIBLE);

        this.firm = PdfObject.NOTHING;
        this.contacto = PdfObject.NOTHING;
        this.nomecontacto = PdfObject.NOTHING;
        this.nomecontacto1=(TextView) findViewById(R.id.responsavel) ;
        this.contacto1 = (TextView) findViewById(R.id.contact);
        this.localizacao = PdfObject.NOTHING;
        this.localizacao1=(TextView) findViewById(R.id.local);
        this.spinner = PdfObject.NOTHING;
        this.spinner1 = (Spinner) findViewById(R.id.visit);

        prosseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerar.this.sim = (RadioButton) gerar.this.findViewById(R.id.radioButton2);
                gerar.this.nao = (RadioButton) gerar.this.findViewById(R.id.radioButton);
                gerar.this.spinner= spinner1.getSelectedItem().toString();
                gerar.this.firm = cliente.getText().toString();
                gerar.this.contacto = contacto1.getText().toString();
                gerar.this.nomecontacto = nomecontacto1.getText().toString();
                gerar.this.localidade = localidade1.getText().toString();

                gerar.this.localizacao=localizacao1.getText().toString();
                gerar.this.horadechegada=hora_chegada.getText().toString();


                if(gerar.this.firm.equals(PdfObject.NOTHING) || gerar.this.contacto.equals(PdfObject.NOTHING) ||  gerar.this.nomecontacto.equals(PdfObject.NOTHING) || gerar.this.localizacao.equals(PdfObject.NOTHING) || hora_chegada.equals(PdfObject.NOTHING)){
                    i=0;
                    while (gerar.this.i < listaclientes.length){
                        if (gerar.this.firm.equals(listaclientes[gerar.this.i])) {
                            gerar.this.contacto1.setText(listacontactos[gerar.this.i]);
                            gerar.this.localizacao1.setText(listamorada[gerar.this.i]);
                            gerar.this.nomecontacto1.setText(listanomecontacto[gerar.this.i]);
                            gerar.this.localidade1.setText(listalocalidades[gerar.this.i]);

                            break;
                        }

                         gerar mainActivity = gerar.this;
                        i++;
                    }
                      if(i==listaclientes.length){
                        openDialog7();
                    }
                }

                else if (gerar.this.sim.isChecked()) {
                    Intent intent = new Intent(gerar.this.getApplicationContext(), segunda.class);
                    intent.putExtra("cliente", gerar.this.firm);
                    intent.putExtra("contacto", gerar.this.contacto);
                    intent.putExtra("nomecontacto", gerar.this.nomecontacto);
                    intent.putExtra("localizacao", gerar.this.localizacao);
                    intent.putExtra("hora_chegada", horadechegada);
                    intent.putExtra("spinner", gerar.this.spinner);
                    intent.putExtra("r00", "Sim");
                    intent.putExtra("locall",gerar.this.localidade);
                    gerar.this.startActivity(intent);
                }

                else if (gerar.this.nao.isChecked()) {
                    Intent intent = new Intent(gerar.this.getApplicationContext(), segunda.class);
                    intent.putExtra("cliente", gerar.this.firm);
                    intent.putExtra("contacto", gerar.this.contacto);
                    intent.putExtra("nomecontacto", gerar.this.nomecontacto);
                    intent.putExtra("localizacao", gerar.this.localizacao);
                    intent.putExtra("hora_chegada", horadechegada);
                    intent.putExtra("spinner", gerar.this.spinner);
                    intent.putExtra("r00", "N\u00e3o");
                    intent.putExtra("locall",gerar.this.localidade);
                    gerar.this.startActivity(intent);
                } else {
                    Toast.makeText(gerar.this.getApplicationContext(), "Por favor preencha todos os campos.", Toast.LENGTH_LONG).show();
                }



            }
        });



    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditText hora_chegada= (EditText) findViewById(R.id.editText4);
        String hora;
        if (minute<10) {
            hora_chegada.setText(hourOfDay + ":0" + minute);
        }
        else{
            hora_chegada.setText(hourOfDay+ ":"+minute);
        }
    }


    public String[] lernomecontacto(){

        int i = 0;
        //Toast.makeText(relatorio.this, "Estas auqi", Toast.LENGTH_LONG).show();

        StringBuilder sb = new StringBuilder();
        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), File_Name4);

        String[] array4 = new String[0];
        try {
            FileInputStream fis = new FileInputStream(textfile);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while ((line = buff.readLine()) != null) {

                    sb.append(line);
                    cl.add(sb.toString());
                    i++;
                    sb = new StringBuilder();

                }
                array4 = cl.toArray(new String[cl.size()]);

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(gerar.this, array4[0], Toast.LENGTH_LONG).show();
        return array4;
    }














    public String[] lerclientes(){

        int i = 0;
        //Toast.makeText(relatorio.this, "Estas auqi", Toast.LENGTH_LONG).show();

        StringBuilder sb = new StringBuilder();
        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), File_Name);

        String[] array = new String[0];
        try {
            FileInputStream fis = new FileInputStream(textfile);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while ((line = buff.readLine()) != null) {

                    sb.append(line);
                    cl.add(sb.toString());
                    i++;
                    sb = new StringBuilder();

                }
                array = cl.toArray(new String[cl.size()]);

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(gerar.this, array[0], Toast.LENGTH_LONG).show();
        return array;
    }

    public String[] lercontactos(){
        ArrayList<String> cl = new ArrayList<>();
        int i = 0;
        //Toast.makeText(relatorio.this, "Estas auqi", Toast.LENGTH_LONG).show();

        StringBuilder sb1 = new StringBuilder();
        File textfile1;
        textfile1 = new File(Environment.getExternalStorageDirectory(), File_Name2);

        String[] array2 = new String[0];
        try {
            FileInputStream fis = new FileInputStream(textfile1);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while ((line = buff.readLine()) != null) {

                    sb1.append(line);
                    cl.add(sb1.toString());
                    i++;
                    sb1 = new StringBuilder();

                }
                array2 = cl.toArray(new String[cl.size()]);

            } else {


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array2;
    }

    public String[] lermoradas(){
        ArrayList<String> cl = new ArrayList<>();
        int i = 0;
        //Toast.makeText(relatorio.this, "Estas auqi", Toast.LENGTH_LONG).show();

        StringBuilder sb = new StringBuilder();
        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), File_Name3);

        String[] array3 = new String[0];
        try {
            FileInputStream fis = new FileInputStream(textfile);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while ((line = buff.readLine()) != null) {

                    sb.append(line);
                    cl.add(sb.toString());
                    i++;
                    sb = new StringBuilder();

                }
                array3 = cl.toArray(new String[cl.size()]);

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(relatorio.this, array3[0], Toast.LENGTH_LONG).show();
        return array3;
    }

    public String[] lerlocalidades(){
        ArrayList<String> cl = new ArrayList<>();
        int i = 0;
        //Toast.makeText(relatorio.this, "Estas auqi", Toast.LENGTH_LONG).show();

        StringBuilder sb = new StringBuilder();
        File textfile;
        textfile = new File(Environment.getExternalStorageDirectory(), File_Name5);

        String[] array4 = new String[0];
        try {
            FileInputStream fis = new FileInputStream(textfile);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while ((line = buff.readLine()) != null) {

                    sb.append(line);
                    cl.add(sb.toString());
                    i++;
                    sb = new StringBuilder();

                }
                array4 = cl.toArray(new String[cl.size()]);

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(relatorio.this, array3[0], Toast.LENGTH_LONG).show();
        return array4;
    }






    public void openDialog7(){
        janelanovo exemplo =new janelanovo();
        exemplo.show(getSupportFragmentManager(),"Janela de Exemplo");
    }

    public void applyTexts5(String s, String s2, String s3, String s4, String s5) {

            gerar.this.contacto1.setText(s3);
            gerar.this.localizacao1.setText(s2);
            gerar.this.nomecontacto1.setText(s4);
            gerar.this.localidade1.setText(s);

        String cliente=s5;
        String morada=s2;
        String nomecontacto=s4;
        String localidade=s;
        String telefone=s3;
        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);
        final String comercial = editor.getString("comercial","No name"); //default value

        background2 bg = new background2(this);
        bg.execute(cliente,morada,nomecontacto,localidade,telefone,comercial);

        Toast.makeText(gerar.this, s4, Toast.LENGTH_LONG).show();
}
}
