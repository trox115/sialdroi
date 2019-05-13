package com.example.antniofernandes.alusydroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class background extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;
    public background (Context context){
        this.context= context;
    }

    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Login");
    }

    protected void onPostExecute(String s) {



    }


    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String cliente = voids[0];
        String localizacao = voids[1];
        String contacto = voids[2];
        String nomecontacto = voids[3];
        String spinner = voids[4];
        String a1 = voids[5];
        //String acessoriosm2 = voids[6];
        String hora_chegada = voids[6];
        String p1 = voids[7];
        String m1 = voids[8];
        String m2 = voids[9];
        String m3 = voids[10];
        String p2 = voids[11];
        String ace1 = voids[12];
        String ace2 = voids[13];
        String p3 = voids[14];
        String p4 = voids[15];
        String p5 = voids[16];
        String p6 = voids[17];
        String p7 = voids[18];
        String obs7 = voids[19];
        String obs13 = voids[20];
        String liquid = voids[21];
        String obs14 = voids[22];
        String hsa = voids[24];
        String data1 = voids[23];
        String comercial =voids[25];

        String connector = "http://firseguranca.com/prog/relatorio.php";

        String resultado = null;
        try {
            URL url = new URL(connector);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String post_data = URLEncoder.encode("cliente","UTF-8")+"="+URLEncoder.encode(cliente,"UTF-8")+"&"
                    +URLEncoder.encode("morada","UTF-8")+"="+URLEncoder.encode(localizacao,"UTF-8")+"&"
                    +URLEncoder.encode("telefone","UTF-8")+"="+URLEncoder.encode(contacto,"UTF-8")+"&"
                    +URLEncoder.encode("contacto","UTF-8")+"="+URLEncoder.encode(nomecontacto,"UTF-8")+"&"
                    +URLEncoder.encode("visita","UTF-8")+"="+URLEncoder.encode(spinner,"UTF-8")+"&"
                    +URLEncoder.encode("ativo","UTF-8")+"="+URLEncoder.encode(a1,"UTF-8")+"&"
                    +URLEncoder.encode("horachegada","UTF-8")+"="+URLEncoder.encode(hora_chegada,"UTF-8")+"&"
                    +URLEncoder.encode("produtos","UTF-8")+"="+URLEncoder.encode(p1,"UTF-8")+"&"
                    +URLEncoder.encode("p1","UTF-8")+"="+URLEncoder.encode(m1,"UTF-8")+"&"
                    +URLEncoder.encode("p2","UTF-8")+"="+URLEncoder.encode(m2,"UTF-8")+"&"
                    +URLEncoder.encode("p3","UTF-8")+"="+URLEncoder.encode(m3,"UTF-8")+"&"
                    +URLEncoder.encode("acessorios","UTF-8")+"="+URLEncoder.encode(p2,"UTF-8")+"&"
                    +URLEncoder.encode("a1","UTF-8")+"="+URLEncoder.encode(ace1,"UTF-8")+"&"
                    +URLEncoder.encode("a2","UTF-8")+"="+URLEncoder.encode(ace2,"UTF-8")+"&"
                    +URLEncoder.encode("expositor","UTF-8")+"="+URLEncoder.encode(p3,"UTF-8")+"&"
                    +URLEncoder.encode("prototipo","UTF-8")+"="+URLEncoder.encode(p4,"UTF-8")+"&"
                    +URLEncoder.encode("catalogos","UTF-8")+"="+URLEncoder.encode(p5,"UTF-8")+"&"
                    +URLEncoder.encode("apoio","UTF-8")+"="+URLEncoder.encode(p6,"UTF-8")+"&"
                    +URLEncoder.encode("situacao","UTF-8")+"="+URLEncoder.encode(p7,"UTF-8")+"&"
                    +URLEncoder.encode("s1","UTF-8")+"="+URLEncoder.encode(obs7,"UTF-8")+"&"
                    +URLEncoder.encode("financeira","UTF-8")+"="+URLEncoder.encode(obs13,"UTF-8")+"&"
                    +URLEncoder.encode("liquidou","UTF-8")+"="+URLEncoder.encode(liquid,"UTF-8")+"&"
                    +URLEncoder.encode("scomercial","UTF-8")+"="+URLEncoder.encode(obs14,"UTF-8")+"&"
                    +URLEncoder.encode("datav","UTF-8")+"="+URLEncoder.encode(data1,"UTF-8")+"&"
                    +URLEncoder.encode("comercial","UTF-8")+"="+URLEncoder.encode(comercial,"UTF-8")+"&"
                    //+URLEncoder.encode("acessoriosm2","UTF-8")+"="+URLEncoder.encode(acessoriosm2,"UTF-8")+"&"
                    +URLEncoder.encode("horasaida","UTF-8")+"="+URLEncoder.encode(hsa,"UTF-8");
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
       // insere2(cliente2,localizacao,contacto,nomecontacto,spinner,a1,hora_chegada,p1,m1,m2,m3,p2,ace1,ace2,p3,p4,p5,p6,p7,obs7,obs13,liquid,obs14,hsa,data1);

        return result;
    }

    /*protected String insere2(String... voids){
        String cliente2 = voids[0];
        String localizacao = voids[1];
        String contacto = voids[2];
        String nomecontacto = voids[3];
        String spinner = voids[4];
        String a1 = voids[5];
        //String acessoriosm2 = voids[6];
        String hora_chegada = voids[6];
        String p1 = voids[7];
        String m1 = voids[8];
        String m2 = voids[9];
        String m3 = voids[10];
        String p2 = voids[11];
        String ace1 = voids[12];
        String ace2 = voids[13];
        String p3 = voids[14];
        String p4 = voids[15];
        String p5 = voids[16];
        String p6 = voids[17];
        String p7 = voids[18];
        String obs7 = voids[19];
        String obs13 = voids[20];
        String liquid = voids[21];
        String obs14 = voids[22];
        String hsa = voids[23];
        String data1 = voids[24];


        String connector = "http://firseguranca.com/prog/relatorio.php";

        String resultado = null;
        String result ="";
        try {
            URL url = new URL(connector);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream ops = http.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String post_data = URLEncoder.encode("cliente","UTF-8")+"="+URLEncoder.encode(cliente2,"UTF-8")+"&"
                    +URLEncoder.encode("morada","UTF-8")+"="+URLEncoder.encode(localizacao,"UTF-8")+"&"
                    +URLEncoder.encode("telefone","UTF-8")+"="+URLEncoder.encode(contacto,"UTF-8")+"&"
                    +URLEncoder.encode("contacto","UTF-8")+"="+URLEncoder.encode(nomecontacto,"UTF-8")+"&"
                    +URLEncoder.encode("visita","UTF-8")+"="+URLEncoder.encode(spinner,"UTF-8")+"&"
                    +URLEncoder.encode("ativo","UTF-8")+"="+URLEncoder.encode(a1,"UTF-8")+"&"
                    +URLEncoder.encode("horachegada","UTF-8")+"="+URLEncoder.encode(hora_chegada,"UTF-8")+"&"
                    +URLEncoder.encode("produtos","UTF-8")+"="+URLEncoder.encode(p1,"UTF-8")+"&"
                    +URLEncoder.encode("p1","UTF-8")+"="+URLEncoder.encode(m1,"UTF-8")+"&"
                    +URLEncoder.encode("p2","UTF-8")+"="+URLEncoder.encode(m2,"UTF-8")+"&"
                    +URLEncoder.encode("p3","UTF-8")+"="+URLEncoder.encode(m3,"UTF-8")+"&"
                    +URLEncoder.encode("acessorios","UTF-8")+"="+URLEncoder.encode(p2,"UTF-8")+"&"
                    +URLEncoder.encode("a1","UTF-8")+"="+URLEncoder.encode(ace1,"UTF-8")+"&"
                    +URLEncoder.encode("a2","UTF-8")+"="+URLEncoder.encode(ace2,"UTF-8")+"&"
                    +URLEncoder.encode("expositor","UTF-8")+"="+URLEncoder.encode(p3,"UTF-8")+"&"
                    +URLEncoder.encode("prototipo","UTF-8")+"="+URLEncoder.encode(p4,"UTF-8")+"&"
                    +URLEncoder.encode("catalogos","UTF-8")+"="+URLEncoder.encode(p5,"UTF-8")+"&"
                    +URLEncoder.encode("apoio","UTF-8")+"="+URLEncoder.encode(p6,"UTF-8")+"&"
                    +URLEncoder.encode("situacao","UTF-8")+"="+URLEncoder.encode(p7,"UTF-8")+"&"
                    +URLEncoder.encode("s1","UTF-8")+"="+URLEncoder.encode(obs7,"UTF-8")+"&"
                    +URLEncoder.encode("financeira","UTF-8")+"="+URLEncoder.encode(obs13,"UTF-8")+"&"
                    +URLEncoder.encode("liquidou","UTF-8")+"="+URLEncoder.encode(liquid,"UTF-8")+"&"
                    +URLEncoder.encode("scomercial","UTF-8")+"="+URLEncoder.encode(obs14,"UTF-8")+"&"
                    +URLEncoder.encode("datav","UTF-8")+"="+URLEncoder.encode(data1,"UTF-8")+"&"
                    //+URLEncoder.encode("acessoriosm2","UTF-8")+"="+URLEncoder.encode(acessoriosm2,"UTF-8")+"&"
                    +URLEncoder.encode("horasaida","UTF-8")+"="+URLEncoder.encode(hsa,"UTF-8");
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
    }*/
}
