package com.example.antniofernandes.sialdroi;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Clientes extends AppCompatActivity implements janelanovo.ExampleDialogListener2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        Button clientes= (Button) findViewById(R.id.button6);

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog7();
            }
        });
    }


    public void openDialog7(){
        janelanovo exemplo =new janelanovo();
        exemplo.show(getSupportFragmentManager(),"Janela de Exemplo");
    }

    public void applyTexts5(String s, String s2, String s3, String s4, String s5) {
        String cliente=s5;
        String morada=s2;
        String nomecontacto=s4;
        String localidade=s;
        String telefone=s3;
        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);
        final String comercial = editor.getString("comercial","No name"); //default value

        background2 bg = new background2(this);
        bg.execute(cliente,morada,nomecontacto,localidade,telefone,comercial);

        Toast.makeText(Clientes.this, s4, Toast.LENGTH_LONG).show();
    }
}
