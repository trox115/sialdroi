package com.example.antniofernandes.alusydroid;

import android.content.Intent;
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

    public void applyTexts5(String s, String s2, String s3, String s4) {



        Toast.makeText(Clientes.this, s4, Toast.LENGTH_LONG).show();
    }
}
