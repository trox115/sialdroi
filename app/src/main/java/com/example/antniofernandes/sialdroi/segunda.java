package com.example.antniofernandes.sialdroi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfObject;

public class segunda extends AppCompatActivity implements janelamarcas.ExampleDialogListener,janelaacessorios.ExampleDialogListener1 {
    public RadioButton nao;
    public RadioButton nao2;
    public RadioButton nao3;
    public RadioButton nao4;
    private TextView ob1;
    private TextView ob2;
    private TextView ob3;
    String marca1;
    String marca2;
    String marca3;
    String acessorio1;
    String acessorio2;
    private TextView textviewmarca;
    private TextView textviewmarca2;
    private TextView textviewmarca3;
    private TextView acessorios;
    private TextView acessorios2;
    private TextView ob4;
    private TextView ob5;
    private TextView ob6;
    private String obs;
    private String obs2;
    private String obs3;
    private String obs4;
    private String obs5;
    private String obs6;
    private String p1 = PdfObject.NOTHING;
    private String p2 = PdfObject.NOTHING;
    private String p3 = PdfObject.NOTHING;
    private String p4 = PdfObject.NOTHING;
    public RadioButton sim;
    public RadioButton sim2;
    public RadioButton sim3;
    public RadioButton sim4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        this.ob1 = (TextView) findViewById(R.id.editText5);
        this.ob2 = (TextView) findViewById(R.id.editText6);
        this.ob3 = (TextView) findViewById(R.id.editText7);
         textviewmarca = (TextView) findViewById(R.id.textView10);
         textviewmarca.setVisibility(View.INVISIBLE);

         textviewmarca2 =(TextView) findViewById(R.id.textView11);
         textviewmarca2.setVisibility(View.INVISIBLE);

        textviewmarca3 =(TextView) findViewById(R.id.textView12);
        textviewmarca3.setVisibility(View.INVISIBLE);

        acessorios=(TextView) findViewById(R.id.textView13);
        acessorios.setVisibility(View.INVISIBLE);

        acessorios2=(TextView) findViewById(R.id.textView14);
        acessorios2.setVisibility(View.INVISIBLE);


        this.ob2.setVisibility(View.INVISIBLE);
        this.ob3.setVisibility(View.INVISIBLE);
        this.ob1.setVisibility(View.INVISIBLE);

        this.sim = (RadioButton) findViewById(R.id.s1);
        this.nao = (RadioButton) findViewById(R.id.n1);
        ((RadioGroup)findViewById(R.id.radioGroup4)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(segunda.this.sim.isChecked()){
                    segunda.this.p1="Sim";
                    segunda.this.ob1.setVisibility(View.INVISIBLE);

                }else if(segunda.this.nao.isChecked()){
                    segunda.this.p1="Não";
                    //segunda.this.ob1.setVisibility(View.VISIBLE);
                    openDialog();

                }
            }
        });




        this.sim2 = (RadioButton) findViewById(R.id.si2);
        this.nao2 = (RadioButton) findViewById(R.id.na2);
        ((RadioGroup) findViewById(R.id.radioGroup3)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (segunda.this.sim2.isChecked()) {
                    segunda.this.p2 = "Sim";
                    segunda.this.ob2.setVisibility(View.INVISIBLE);
                } else if (segunda.this.nao2.isChecked()) {
                    segunda.this.p2 = "N\u00e3o";
                   // segunda.this.ob2.setVisibility(View.VISIBLE);
                   // segunda.this.ob3.setVisibility(View.VISIBLE);
                    openDialog2();
                }
            }
        });

        this.sim3 = (RadioButton) findViewById(R.id.si3);
        this.nao3 = (RadioButton) findViewById(R.id.na3);

        ((RadioGroup) findViewById(R.id.RadioGroup1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (segunda.this.sim3.isChecked()) {
                    segunda.this.p3 = "Sim";
                } else if (segunda.this.nao3.isChecked()) {
                    segunda.this.p3 = "N\u00e3o";
                }
            }
        });

        this.sim4 = (RadioButton) findViewById(R.id.si4);
        this.nao4 = (RadioButton) findViewById(R.id.na4);

        ((RadioGroup) findViewById(R.id.RadioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (segunda.this.sim4.isChecked()) {
                    segunda.this.p4 = "Sim";

                } else if (segunda.this.nao4.isChecked()) {
                    segunda.this.p4 = "N\u00e3o";

                }
            }
        });

        Button prosseguir = (Button) findViewById(R.id.button9);

            prosseguir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    segunda.this.obs = segunda.this.ob1.getText().toString();
                    segunda.this.obs2 = segunda.this.ob2.getText().toString();
                    segunda.this.obs3 = segunda.this.ob3.getText().toString();
                    marca1 = textviewmarca.getText().toString();
                    marca2 = textviewmarca2.getText().toString();
                    marca3 = textviewmarca3.getText().toString();
                    acessorio1 = acessorios.getText().toString();
                    acessorio2 = acessorios2.getText().toString();

                    String firm = segunda.this.getIntent().getStringExtra("cliente");
                    String localizacao = segunda.this.getIntent().getStringExtra("localizacao");
                    String contacto = segunda.this.getIntent().getStringExtra("contacto");
                    String nomecontacto = segunda.this.getIntent().getStringExtra("nomecontacto");
                    String spinner = segunda.this.getIntent().getStringExtra("spinner");
                    String hora_chegada = segunda.this.getIntent().getStringExtra("hora_chegada");
                    String a1 = segunda.this.getIntent().getStringExtra("r00");
                    String localidades=segunda.this.getIntent().getStringExtra("locall");
                    if (segunda.this.p1.equals(PdfObject.NOTHING) || segunda.this.p2.equals(PdfObject.NOTHING) || segunda.this.p3.equals(PdfObject.NOTHING) || segunda.this.p4.equals(PdfObject.NOTHING)) {
                        Toast.makeText(segunda.this.getApplicationContext(), "Por favor preencha todos os campos.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent intent = new Intent(segunda.this.getApplicationContext(), terceira.class);
                    intent.putExtra("localizacao", localizacao);

                    intent.putExtra("contacto", contacto);
                    intent.putExtra("nomecontacto", nomecontacto);
                    intent.putExtra("spinner", spinner);
                    intent.putExtra("hora_chegada", hora_chegada);
                    intent.putExtra("cliente", firm);
                    intent.putExtra("locall",localidades);
                    intent.putExtra("r1", segunda.this.p1);
                    intent.putExtra("r2", segunda.this.p2);
                    intent.putExtra("r3", segunda.this.p3);
                    intent.putExtra("r4", segunda.this.p4);
                    intent.putExtra("ob1", segunda.this.obs);
                    intent.putExtra("ob2", segunda.this.obs2);
                    intent.putExtra("ob3", segunda.this.obs3);
                    intent.putExtra("marca1",marca1);
                    intent.putExtra("marca2",marca2);
                    intent.putExtra("marca3",marca3);
                    intent.putExtra("acessorio1",acessorio1);
                    intent.putExtra("acessorio2",acessorio2);


                    intent.putExtra("r00", a1);
                    segunda.this.startActivity(intent);
                }
            });



    }

    public void openDialog(){
        janelamarcas exemplo =new janelamarcas();
        exemplo.show(getSupportFragmentManager(),"Janela de Exemplo");
    }

    public void openDialog2(){
        janelaacessorios exemplo =new janelaacessorios();
        exemplo.show(getSupportFragmentManager(),"Janela de Exemplo");
    }

    public void applyTexts(String username, String password, String spinner) {
        textviewmarca.setText(username);
        textviewmarca2.setText(password);
        textviewmarca3.setText(spinner);


    }

    public void applyTexts2(String username1, String password1) {
        acessorios.setText(username1);
       acessorios2.setText(password1);



    }
}
