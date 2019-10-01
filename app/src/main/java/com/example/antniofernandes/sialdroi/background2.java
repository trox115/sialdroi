package com.example.antniofernandes.sialdroi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class background2 extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;

    public background2 (Context context){
        this.context= context;
    }

    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Login");

    }

    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if(s == null)
        {
            // do what you want to do
        }

        else if(s.contains("Login credentials are good")) // msg you get from success like "Login Success"
        {
            Intent i = new Intent(context,planeador.class);
            context.startActivity(i);

        }


    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String cliente = voids[0];
        String morada = voids[1];
        String nomecontacto = voids[2];
        String localidade = voids[3];
        String telefone = voids[4];
        String vendedor=voids[5];

        String connector = "http://ondalivre-fm.com/prog/add.php";

        String resultado = null;
        try {
            URL url = new URL(connector);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream ops = http.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String post_data = URLEncoder.encode("nome","UTF-8")+"="+URLEncoder.encode(cliente,"UTF-8")+"&"
                    +URLEncoder.encode("morada","UTF-8")+"="+URLEncoder.encode(morada,"UTF-8")+"&"
                    +URLEncoder.encode("telefone","UTF-8")+"="+URLEncoder.encode(telefone,"UTF-8")+"&"
                    +URLEncoder.encode("localidade","UTF-8")+"="+URLEncoder.encode(localidade,"UTF-8")+"&"
                    +URLEncoder.encode("contacto","UTF-8")+"="+URLEncoder.encode(nomecontacto,"UTF-8")+"&"
                    +URLEncoder.encode("zona","UTF-8")+"="+URLEncoder.encode("indefinida","UTF-8")+"&"
                    +URLEncoder.encode("comercial","UTF-8")+"="+URLEncoder.encode(vendedor,"UTF-8");
            writer.write(post_data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips;
            ips = http.getInputStream();
            http.setConnectTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                result += line;

                return result;
            }
            reader.close();
            ips.close();

            http.disconnect();
        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result=e.getMessage();
        }


        return result;
    }
}