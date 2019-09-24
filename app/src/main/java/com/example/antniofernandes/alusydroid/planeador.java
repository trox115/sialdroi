package com.example.antniofernandes.alusydroid;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;


public class planeador extends AppCompatActivity {
    public int contador=0;
    public String [] teste;
    int REQUEST = 1337;
    ArrayAdapter<String> tes;
    InputStream is = null;
    TextView tv = null;
    String line= null;
    String result=null;
    String adress ="";
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planeador);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);
        final String comercial = editor.getString("comercial","No name"); //default value
        if (comercial.equals("E09")){
            adress ="http://firseguranca.com/spinner.php";

        }
        else if (comercial.equals("E06")){
            adress ="http://firseguranca.com/spinner2.php";
        }

       String[] zona = new String[0];
        String[] nome = new String[0];
        String[] morada = new String[0];
        String[] contacto = new String[0];

        try {
            contacto = getcontacto();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            morada = getmorada();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            nome = getnome();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            zona = getzona();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Spinner spinner = (Spinner) findViewById(R.id.spinner3);

       final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, zona);
       spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);



         TableLayout tl = (TableLayout)findViewById(R.id.t1);



        planeador.this.i=0;
        while (planeador.this.i < zona.length){
            TableRow row = new TableRow(this);
            TextView tv = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3 = new TextView(this);
            String a = morada[i];
            String b= nome[i];
            String c= contacto[i];
            tv.setText(b);
            tv2.setText(a);

           tl.addView(row);
           row.addView(tv);
           row.addView(tv2);
            i++;

            }
            contador =zona.length;

        Button planear = (Button) findViewById(R.id.button13);

        final String[] finalZona = zona;
        final String[] finalMorada = morada;
        final String[] finalNome = nome;
        final String[] finalContacto = contacto;
        planear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout tl = (TableLayout)findViewById(R.id.t1);

                tl.removeAllViews();
                TableLayout t2 = (TableLayout)findViewById(R.id.t1);

                i=0;
                String comparador = spinner.getSelectedItem().toString();
                int cenas = 0;

                for(i=0;i<teste.length-1;i++){
                    if (comparador.equals(teste[i])) {
                        int caca=i;
                        TableRow row = new TableRow(planeador.this);
                        TextView tv = new TextView(planeador.this);
                        TextView tv2 = new TextView(planeador.this);
                        TextView tv3 = new TextView(planeador.this);
                        String a = finalMorada[i];
                        String b = finalNome[i];
                        String c = finalContacto[i];
                        tv.setText(b);
                        tv2.setText(a);
                        tv3.setText(c);
                        //tv2.setText(a);
                        tl.addView(row);
                        row.addView(tv);
                        row.addView(tv2);
                        row.addView(tv3);
                        //cenas ++;
                    }

                    }
                //Toast.makeText(planeador.this, finalNome[i], Toast.LENGTH_SHORT).show();



            }
        });


        }


    public String[] getnome() throws IOException {
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

    public String[] getmorada() throws IOException {
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



    public String[] getzona() throws IOException {
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
            contador++;
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
                dados[i] = jo.getString("zona");

            }
            //Toast.makeText(MainActivity.this, dados[1], Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        teste = dados;
        dados = removerduplicados(dados);
        return dados;
    }


    public String[] getcontacto() throws IOException {
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

    public String[] removerduplicados(String[] dados) {
        i=0;
        while(i<dados.length){
            dados[i]=dados[i].toUpperCase();
            i++;
        }
        LinkedHashSet<String> lhSetColors =
                new LinkedHashSet<String>(Arrays.asList(dados));

        //create array from the LinkedHashSet
        String[] newArray = lhSetColors.toArray(new String[ lhSetColors.size() ]);

        return newArray;
    }
}
