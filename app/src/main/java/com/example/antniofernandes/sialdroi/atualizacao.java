package com.example.antniofernandes.sialdroi;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class atualizacao extends AppCompatActivity {
    private int STORAGE_CODE =1000; int REQUEST = 1337;
    private static final String FILE_NAME ="clientes.txt" ;
    private static final String FILE_NAME2 ="contactos.txt" ;
    private static final String FILE_NAME3 ="moradas.txt" ;
    private static final String FILE_NAME4="responsavel.txt";
    private static final String FILE_NAME5="interna.txt";
    private static final String FILE_NAME6="localidades.txt";


    ArrayAdapter<String> tes;
    InputStream is = null;
    String line= null;
    String result=null;
    String adress =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizacao);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        Button atualizar = (Button) findViewById(R.id.button12);
        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);
        final String comercial = editor.getString("comercial","No name"); //default value



        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = spinner.getSelectedItem().toString();
                if (comercial.equals(s) && comercial.equals("E06")){
                     adress ="http://ondalivre-fm.com/spinner.php";

                    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
                    try {
                        final String [] clientes=getclientes();
                        final String [] contactos=getcontactos();
                        final String [] moradas=getmoradas();
                        final String [] responsavel=getresponsavel();
                        final String [] localidades=getlocalidades();

                        escreverclientes(clientes);
                        escrevercontactos(contactos);
                        escrevermoradas(moradas);
                        escreverresponsavel(responsavel);
                        escreverclientesinterno(clientes);
                        escreverlocalidades(localidades);
                        Toast.makeText(atualizacao.this, "Atualização Completa", Toast.LENGTH_LONG).show();
                        finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else  if (comercial.equals(s) && comercial.equals("E09")){
                    adress ="http://ondalivre-fm.com/spinner2.php";
                    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
                    try {
                        final String [] clientes=getclientes();
                        final String [] contactos=getcontactos();
                        final String [] moradas=getmoradas();
                        final String [] responsavel=getresponsavel();
                        final String [] localidades=getlocalidades();

                        escreverclientes(clientes);
                        escrevercontactos(contactos);
                        escrevermoradas(moradas);
                        escreverresponsavel(responsavel);
                        escreverclientesinterno(clientes);
                        escreverlocalidades(localidades);
                        Toast.makeText(atualizacao.this, "Atualização Completa", Toast.LENGTH_LONG).show();
                        finish();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{

                    Toast.makeText(atualizacao.this, "O comercial não é válido", Toast.LENGTH_SHORT).show();
                }

               // Toast.makeText(atualizacao.this, adress, Toast.LENGTH_SHORT).show();


            }
        });
    }

    public String[] getlocalidades() throws IOException {
        try {
            URL url = new URL(adress);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LER IS E TRANSFORMAR EM STRING

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        while((line=br.readLine())!=null){
            sb.append(line +"\n");

        }
        is.close();
        result=sb.toString();
        String[] dados = new String[0];

        //PARSE jSON DATA
        try {
            JSONArray ja= new JSONArray(result);
            JSONObject jo = null;
            dados= new String[ja.length()];
            //  Toast.makeText(MainActivity.this, "teste", Toast.LENGTH_SHORT).show();
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                dados[i] = jo.getString("localidade");

            }
            //Toast.makeText(MainActivity.this, dados[1], Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return dados;
    }

    public void escreverlocalidades(String [] teste){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String premissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                requestPermissions(new String[]{premissions}, STORAGE_CODE);
                String[] INITIAL_PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(INITIAL_PERMS, REQUEST);
            } else {

                File textfile;
                textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME6);
                try {
                    FileOutputStream fos = new FileOutputStream(textfile);
                    String cli;
                    int i = 0;

                    while (teste.length > i) {
                        cli = teste[i] + "\n";
                        try {
                            fos.write(cli.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }

                } catch (IOException e) {

                }
            }


        } else {


            File textfile;
            textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME6);
            try {
                FileOutputStream fos = new FileOutputStream(textfile);
                String cli;
                int i = 0;

                while (teste.length > i) {
                    cli = teste[i] + "\n";
                    try {
                        fos.write(cli.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            } catch (IOException e) {

            }

        }
    }










    public String[] getclientes() throws IOException {
        try {
            URL url = new URL(adress);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LER IS E TRANSFORMAR EM STRING

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        while((line=br.readLine())!=null){
            sb.append(line +"\n");

        }
        is.close();
        result=sb.toString();
        String[] dados = new String[0];

        //PARSE jSON DATA
        try {
            JSONArray ja= new JSONArray(result);
            JSONObject jo = null;
            dados= new String[ja.length()];
            //  Toast.makeText(MainActivity.this, "teste", Toast.LENGTH_SHORT).show();
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                dados[i] = jo.getString("nome");

            }
            //Toast.makeText(MainActivity.this, dados[1], Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return dados;
    }

    public void escreverclientes(String [] teste){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String premissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                requestPermissions(new String[]{premissions}, STORAGE_CODE);
                String[] INITIAL_PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(INITIAL_PERMS, REQUEST);
            } else {

                File textfile;
                textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
                try {
                    FileOutputStream fos = new FileOutputStream(textfile);
                    String cli;
                    int i = 0;

                    while (teste.length > i) {
                        cli = teste[i] + "\n";
                        try {
                            fos.write(cli.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }

                } catch (IOException e) {

                }
            }


        } else {


            File textfile;
            textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
            try {
                FileOutputStream fos = new FileOutputStream(textfile);
                String cli;
                int i = 0;

                while (teste.length > i) {
                    cli = teste[i] + "\n";
                    try {
                        fos.write(cli.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            } catch (IOException e) {

            }

        }
    }

    public void escreverclientesinterno(String [] teste) {
        FileOutputStream fos = null;


       // File textfile;
       // textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        try {
            //FileOutputStream fos = null;
            String cli;
            int i = 0;

            while (teste.length > i) {
                cli = teste[i] + "\n";
                try {
                    fos = openFileOutput(FILE_NAME5, MODE_PRIVATE);
                    fos.write(cli.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                i++;
            }


        }finally {
            if (fos !=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    public String[] getmoradas() throws IOException {
        try {
            URL url = new URL(adress);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LER IS E TRANSFORMAR EM STRING

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        while((line=br.readLine())!=null){
            sb.append(line +"\n");

        }
        is.close();
        result=sb.toString();
        String[] dados = new String[0];

        //PARSE jSON DATA
        try {
            JSONArray ja= new JSONArray(result);
            JSONObject jo = null;
            dados= new String[ja.length()];
            //  Toast.makeText(MainActivity.this, "teste", Toast.LENGTH_SHORT).show();
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                dados[i] = jo.getString("morada");

            }
            //Toast.makeText(MainActivity.this, dados[1], Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return dados;
    }


    public void escrevermoradas(String [] teste){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String premissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                requestPermissions(new String[]{premissions}, STORAGE_CODE);
                String[] INITIAL_PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(INITIAL_PERMS, REQUEST);
            } else {

                File textfile;
                textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME3);
                try {
                    FileOutputStream fos = new FileOutputStream(textfile);
                    String cli;
                    int i = 0;

                    while (teste.length > i) {
                        cli = teste[i] + "\n";
                        try {
                            fos.write(cli.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }

                } catch (IOException e) {

                }
            }


        } else {


            File textfile;
            textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME3);
            try {
                FileOutputStream fos = new FileOutputStream(textfile);
                String cli;
                int i = 0;

                while (teste.length > i) {
                    cli = teste[i] + "\n";
                    try {
                        fos.write(cli.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            } catch (IOException e) {

            }

        }
    }


    public String[] getcontactos() throws IOException {
        try {
            URL url = new URL(adress);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LER IS E TRANSFORMAR EM STRING

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        while((line=br.readLine())!=null){
            sb.append(line +"\n");

        }
        is.close();
        result=sb.toString();
        String[] dados = new String[0];

        //PARSE jSON DATA
        try {
            JSONArray ja= new JSONArray(result);
            JSONObject jo = null;
            dados= new String[ja.length()];
            //  Toast.makeText(MainActivity.this, "teste", Toast.LENGTH_SHORT).show();
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                dados[i] = jo.getString("telefone");

            }
            //Toast.makeText(MainActivity.this, dados[1], Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return dados;
    }


    public void escrevercontactos(String [] teste){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String premissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                requestPermissions(new String[]{premissions}, STORAGE_CODE);
                String[] INITIAL_PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(INITIAL_PERMS, REQUEST);
            } else {

                File textfile;
                textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME2);
                try {
                    FileOutputStream fos = new FileOutputStream(textfile);
                    String cli;
                    int i = 0;

                    while (teste.length > i) {
                        cli = teste[i] + "\n";
                        try {
                            fos.write(cli.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }

                } catch (IOException e) {

                }
            }


        } else {


            File textfile;
            textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME2);
            try {
                FileOutputStream fos = new FileOutputStream(textfile);
                String cli;
                int i = 0;

                while (teste.length > i) {
                    cli = teste[i] + "\n";
                    try {
                        fos.write(cli.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            } catch (IOException e) {

            }

        }
    }

    public String[] getresponsavel() throws IOException {
        try {
            URL url = new URL(adress);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LER IS E TRANSFORMAR EM STRING

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        while((line=br.readLine())!=null){
            sb.append(line +"\n");

        }
        is.close();
        result=sb.toString();
        String[] dados = new String[0];

        //PARSE jSON DATA
        try {
            JSONArray ja= new JSONArray(result);
            JSONObject jo = null;
            dados= new String[ja.length()];
            //  Toast.makeText(MainActivity.this, "teste", Toast.LENGTH_SHORT).show();
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                dados[i] = jo.getString("contacto");

            }
            //Toast.makeText(MainActivity.this, dados[1], Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return dados;
    }

    public void escreverresponsavel(String [] teste){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String premissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                requestPermissions(new String[]{premissions}, STORAGE_CODE);
                String[] INITIAL_PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(INITIAL_PERMS, REQUEST);
            } else {

                File textfile;
                textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME4);
                try {
                    FileOutputStream fos = new FileOutputStream(textfile);
                    String cli;
                    int i = 0;

                    while (teste.length > i) {
                        cli = teste[i] + "\n";
                        try {
                            fos.write(cli.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }

                } catch (IOException e) {

                }
            }


        } else {


            File textfile;
            textfile = new File(Environment.getExternalStorageDirectory(), FILE_NAME4);
            try {
                FileOutputStream fos = new FileOutputStream(textfile);
                String cli;
                int i = 0;

                while (teste.length > i) {
                    cli = teste[i] + "\n";
                    try {
                        fos.write(cli.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            } catch (IOException e) {

            }

        }
    }

}
