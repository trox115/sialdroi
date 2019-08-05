package com.example.antniofernandes.alusydroid;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class gerador extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener  {
    private static final int REQUEST = 1337;
    String data1;
    public TextView dta;
    public TextView hs1;
    public TextView liquidou;
    //Calendar c;
    DatePickerDialog dpd;
    String hsa;
    public TextView ob13;
    public TextView ob14;
    String obs13;
    String obs14;
    String liquid;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerador);
        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);
        final String comercial = editor.getString("comercial","No name"); //default value
        this.ob13 = (TextView) findViewById(R.id.editText10);
        this.ob14 = (TextView) findViewById(R.id.editText12);
        this.hs1 = (TextView) findViewById(R.id.editText13);
        this.dta = (TextView) findViewById(R.id.editText14);
        this.liquidou = (TextView) findViewById(R.id.editText11);

        hs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker= new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(),"Escolha a hora");
            }
        });

        dta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Calendar c= Calendar.getInstance();
                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int ano = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(gerador.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dta.setText(dayOfMonth +"/" + (month+1) + "/" + year);
                    }
                }, ano,mes,dia);
                dpd.show();
            }
        });


        Button grava = (Button) findViewById(R.id.button11);

        grava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            gravar();
            }
        });
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditText hora_saida= (EditText) findViewById(R.id.editText13);
        if (minute<10) {
            hora_saida.setText(hourOfDay + ":0" + minute);
        }
        else{
            hora_saida.setText(hourOfDay + ":" + minute);
        }
    }

    public void gravar() {
        try {
            gerador.this.write_file();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public void write_file() throws DocumentException {

        this.obs13 = this.ob13.getText().toString();
        this.obs14 = this.ob14.getText().toString();
        this.hsa = this.hs1.getText().toString();
        this.data1 = this.dta.getText().toString();
        this.liquid=this.liquidou.getText().toString();
        String firm = getIntent().getStringExtra("cliente");
        String localizacao = getIntent().getStringExtra("localizacao");
        String contacto = getIntent().getStringExtra("contacto");
        String nomecontacto = getIntent().getStringExtra("nomecontacto");
        String localidades=getIntent().getStringExtra("locall");

        String spinner = getIntent().getStringExtra("spinner");
        String hora_chegada = getIntent().getStringExtra("hora_chegada");
        String p1 = getIntent().getStringExtra("r1");
        String p2 = getIntent().getStringExtra("r2");
        String p3 = getIntent().getStringExtra("r3");
        String obs = getIntent().getStringExtra("ob1");
        String obs2 = getIntent().getStringExtra("ob2");
        String obs3 = getIntent().getStringExtra("ob3");
        String p4 = getIntent().getStringExtra("r4");
        String p5 = getIntent().getStringExtra("r5");
        String p6 = getIntent().getStringExtra("r6");
        String obs4 = getIntent().getStringExtra("ob4");
        String obs5 = getIntent().getStringExtra("ob5");
        String obs6 = getIntent().getStringExtra("ob6");
        String obs7 = getIntent().getStringExtra("ob7");
        String obs8 = getIntent().getStringExtra("ob8");
        String obs9 = getIntent().getStringExtra("ob9");
        String obs10 = getIntent().getStringExtra("ob10");
        String p7 = getIntent().getStringExtra("r7");
        String p8 = getIntent().getStringExtra("r8");
        String p9 = getIntent().getStringExtra("r9");

        String m1 = getIntent().getStringExtra("marca1");
        String m3 = getIntent().getStringExtra("marca2");
        String m2 = getIntent().getStringExtra("marca3");
        String ace1 = getIntent().getStringExtra("acessorio1");
        String ace2 = getIntent().getStringExtra("acessorio2");
        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);
        final String comercial = editor.getString("comercial","No name");
        String vendedor="";
        if (comercial.equals("E06")){
            vendedor = "Rui Mendes";
        }
        else if(comercial.equals("E09")){
            vendedor = "João César";
        }

        String a1 = getIntent().getStringExtra("r00");
        String percentagem = getIntent().getStringExtra("percentagem");
        String cd = new SimpleDateFormat("yy-MM-dd").format(new Date());
        Toast.makeText(this, "aberto", Toast.LENGTH_LONG).show();
        File folder;
        folder = Environment.getExternalStorageDirectory();
        String filepath = new StringBuilder(String.valueOf(firm)).append(cd).append(" ").append(hora_chegada).append(".pdf").toString();
        Document document = new Document();
        File file = new File(Environment.getExternalStorageDirectory(), filepath);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (DocumentException e12) {
            e12.printStackTrace();
        }
        document.open();

        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
        Font baba = new Font(Font.FontFamily.TIMES_ROMAN, 11.0f);
        Font baba2 = new Font(Font.FontFamily.TIMES_ROMAN, 13.0f);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 11.0f);
        font = new Font(Font.FontFamily.TIMES_ROMAN, 6.0f);
        try {
            /*Image Sialnor = Image.getInstance(folder + "/Sialnor1.png");

            Sialnor.setAbsolutePosition(92.0f, 815.0f);
            Sialnor.scaleAbsoluteHeight(20.0f);
            Sialnor.scaleAbsoluteWidth(100.0f);
            document.add(Sialnor);*/
            InputStream ims = getAssets().open("alusydroid.png");
            Bitmap bmp = BitmapFactory.decodeStream(ims);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.scaleAbsoluteHeight(20.0f);
            image.scaleAbsoluteWidth(100.0f);
            document.add(image);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        BaseColor myColor = WebColors.getRGBColor("#ff8c00");
        BaseColor myColor2 = WebColors.getRGBColor("A0A0A0");
        PdfPTable pdfPTable = new PdfPTable(10);
        pdfPTable.setSpacingAfter(30f);
        pdfPTable.setSpacingBefore(10f);
        PdfPCell pdfPCell = new PdfPCell(new Phrase("Relat\u00f3rio di\u00e1rio de visita a cliente"));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(1);
        pdfPCell.setBackgroundColor(myColor);
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);

        pdfPCell = new PdfPCell(new Phrase("Cliente: " + firm));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        pdfPCell.setColspan(7);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);

        pdfPCell = new PdfPCell(new Phrase("Hora de chegada: " + hora_chegada,baba));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCell.setColspan(3);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);


        pdfPCell = new PdfPCell(new Phrase("Morada:" + localizacao + " - " + localidades));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCell.setColspan(7);
        pdfPTable.addCell(pdfPCell);


        PdfPCell con = new PdfPCell(new Phrase("Hora de sa\u00edda:" + this.hsa,baba));
        con.setVerticalAlignment(5);
        con.setHorizontalAlignment(Element.ALIGN_LEFT);
        con.setColspan(4);
        pdfPTable.addCell(con);


        pdfPCell = new PdfPCell(new Phrase("Nome do contacto: " + nomecontacto ));
       // pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCell.setColspan(7);
        pdfPTable.addCell(pdfPCell);

        pdfPCell = new PdfPCell(new Phrase("Telefone: " + contacto ,baba));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCell.setColspan(3);
        pdfPTable.addCell(pdfPCell);


/*

        pdfPCell = new PdfPCell(new Phrase("\n Forma de visita: " + spinner + "\n", font));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(1);
        pdfPCell.setBackgroundColor(myColor2);
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase(" Este cliente é ativo? " + a1, font));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(1);
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);*/

        document.add(pdfPTable);
        pdfPTable = new PdfPTable(10);
        pdfPTable.setSpacingAfter(30f);
        pdfPTable.setSpacingBefore(10f);
        pdfPCell = new PdfPCell(new Phrase("A-Pontos a verificar"));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(1);
        pdfPCell.setBackgroundColor(myColor);
        pdfPCell.setColspan(10);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("\n 1.O cliente utiliza preferencialmente produts Alusys? \n"));
        pdfPCell.setColspan(9);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p1));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("    1.1. Que marcas? " + m1+", "+m2+", "+m3));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);

        pdfPCell = new PdfPCell(new Phrase("\n 2.O Cliente utiliza os nossos acessórios? \n"));
        pdfPCell.setColspan(9);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p2));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("    2.1. Que marcas de acessórios utiliza ? " + ace1+", "+ace2));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);
       /* pdfPCell = new PdfPCell(new Phrase("    2.2. E Fornencedores? " + obs3, font));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);
       /* pdfPCell = new PdfPCell(new Phrase(obs2));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);*/
        pdfPCell = new PdfPCell(new Phrase("\n 3.O cliente tem expositor com os nossos produtos? \n "));
        pdfPCell.setColspan(9);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p3));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("Quais? " + obs3));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("\n 4.O cliente tem protótipos/amostras dos nossos produtos? \n "));
        pdfPCell.setColspan(9);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p4));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
      /*  pdfPCell = new PdfPCell(new Phrase("    4.1. Que fabricantes/marcas? " + obs4, font));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("    4.2. Que produtos? " + obs5, font));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("    4.3. Factores de diferencia\u00e7\u00e3o em rela\u00e7\u00e3o \u00e0 Sialnor?" + obs6, font));
        pdfPCell.setColspan(10);
        pdfPTable.addCell(pdfPCell);*/
      /*  pdfPCell = new PdfPCell(new Phrase("\n 5. Que marcas estão presentes e em que percentagem?  \n" + percentagem, font));
        pdfPCell.setColspan(10);
        pdfPTable.addCell(pdfPCell);
        /*pdfPCell = new PdfPCell(new Phrase(" \n " + p5));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);*/
       /* pdfPCell = new PdfPCell(new Phrase(" " + percentagem, font));
        pdfPCell.setColspan(10);
        pdfPCell.setRowspan(2);
        pdfPTable.addCell(pdfPCell);*/
        PdfPCell ac = new PdfPCell(new Phrase("\n 5. O cliente tem os nossos catálogos atualizados?  \n"));
        ac.setColspan(9);
        pdfPTable.addCell(ac);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p5));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
       /* pdfPCell = new PdfPCell(new Phrase("    Observa\u00e7\u00f5es: " + obs7, font));
        pdfPCell.setColspan(10);
        pdfPTable.addCell(pdfPCell);*/
        PdfPCell cp = new PdfPCell(new Phrase("\n 6. O cliente precisa de apoio técnico? \n "));
        cp.setColspan(9);
        pdfPTable.addCell(cp);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p6));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
        PdfPCell ap = new PdfPCell(new Phrase("\n 7. Existe alguma sitação pendente de resolução? \n"));
        ap.setColspan(9);
        pdfPTable.addCell(ap);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p7));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase("    Observa\u00e7\u00f5es: " + obs7));
        pdfPCell.setColspan(10);
        pdfPTable.addCell(pdfPCell);
      /*  pdfPCell = new PdfPCell(new Phrase("\n 9. Existe alguma situa\u00e7\u00e3o ainda n\u00e3o tratada?\n ", font));
        pdfPCell.setColspan(9);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase(" \n " + p8));
        pdfPCell.setColspan(1);
        pdfPTable.addCell(pdfPCell);
        pdfPCell = new PdfPCell(new Phrase(" Descrição" + obs9, font));
        pdfPCell.setColspan(10);
        pdfPTable.addCell(pdfPCell);*/
        document.add(pdfPTable);
        pdfPTable = new PdfPTable(8);
        pdfPTable.setSpacingAfter(30f);
        pdfPTable.setSpacingBefore(10f);
        PdfPCell b = new PdfPCell(new Phrase("\n B- Informa\u00e7\u00f5es Complementares relativamente ao Cliente/Mercado e/ou \u00c1reas de Melhoria: \n "));
        b.setColspan(10);
        b.setBackgroundColor(myColor);
        pdfPTable.addCell(b);
        PdfPCell b1 = new PdfPCell(new Phrase("\n Informa\u00e7\u00f5es Financeiras e Comerciais: \n " + this.obs13));
        b1.setColspan(8);
        b1.setRowspan(3);
        pdfPTable.addCell(b1);
        PdfPCell b3 = new PdfPCell(new Phrase("\n Liquidou: \n " + this.liquid + " €"));
        b3.setColspan(8);
        b3.setRowspan(3);
        pdfPTable.addCell(b3);
        PdfPCell b2 = new PdfPCell(new Phrase("\n Informações Comerciais: \n " + this.obs14));
        b2.setColspan(8);
        b2.setRowspan(3);
        pdfPTable.addCell(b2);
        document.add(pdfPTable);
        document.add(new Paragraph("Documento gerado por "+vendedor+" atrav\u00e9s de Alusydroid"));
        document.add(new Paragraph("Data: "+ this.data1));
        document.close();

       /* pdfPCell = new PdfPCell(new Phrase("Data: \n" + this.data1));
        pdfPCell.setVerticalAlignment(5);
        pdfPCell.setHorizontalAlignment(1);
        pdfPCell.setColspan(3);
        pdfPTable.addCell(pdfPCell);*/

        Toast.makeText(getApplicationContext(), "Gravado com sucesso", Toast.LENGTH_LONG).show();

        sendEmail(file,firm,cd);

    }

    protected void sendEmail(File file, String firm, String cd) {
        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);
        final String comercial = editor.getString("comercial","No name");

        Log.i("Send email", "");
        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        String[] TO = {"vitor.vieira@alusys.pt"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Relatório " + firm);
        String vendedor = "";
        if (comercial.equals("E06")){
             vendedor = "Rui Mendes";
        }
        else if(comercial.equals("E09")){
             vendedor = "João César";
        }
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Segue em anexo o relat\u00f3rio do cliente " + firm + ", elaborado no dia" + cd + " correspondente ao dia " + this.data1 + "Elaborado pelo senhor "+ vendedor);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));

            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(gerador.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        String m1 = getIntent().getStringExtra("marca1");
        String m3 = getIntent().getStringExtra("marca2");
        String m2 = getIntent().getStringExtra("marca3");
        String ace1 = getIntent().getStringExtra("acessorio1");
        String ace2 = getIntent().getStringExtra("acessorio2");
        String spinner = getIntent().getStringExtra("spinner");
        String hora_chegada = getIntent().getStringExtra("hora_chegada");
        String p1 = getIntent().getStringExtra("r1");
        String p2 = getIntent().getStringExtra("r2");
        String p3 = getIntent().getStringExtra("r3");
        String obs = getIntent().getStringExtra("ob1");
        String obs2 = getIntent().getStringExtra("ob2");
        String obs3 = getIntent().getStringExtra("ob3");
        String p4 = getIntent().getStringExtra("r4");
        String p5 = getIntent().getStringExtra("r5");
        String p6 = getIntent().getStringExtra("r6");
        String obs4 = getIntent().getStringExtra("ob4");
        String obs5 = getIntent().getStringExtra("ob5");
        String obs6 = getIntent().getStringExtra("ob6");
        String obs7 = getIntent().getStringExtra("ob7");
        String obs8 = getIntent().getStringExtra("ob8");
        String obs9 = getIntent().getStringExtra("ob9");
        String obs10 = getIntent().getStringExtra("ob10");
        String p7 = getIntent().getStringExtra("r7");
        String p8 = getIntent().getStringExtra("r8");
        String p9 = getIntent().getStringExtra("r9");
        String a1 = getIntent().getStringExtra("r00");
        String localizacao = getIntent().getStringExtra("localizacao");
        String contacto = getIntent().getStringExtra("contacto");
        String nomecontacto = getIntent().getStringExtra("nomecontacto");

        background bg = new background(this);
        bg.execute(firm,localizacao,contacto,nomecontacto,spinner,a1,hora_chegada,p1,m1,m2,m3,p2,ace1,ace2,p3,p4,p5,p6,p7,obs7,obs13,liquid,obs14,data1,hsa,comercial);










    }


}
