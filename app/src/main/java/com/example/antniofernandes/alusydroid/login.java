package com.example.antniofernandes.alusydroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences.Editor editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE).edit();


        Button id = (Button) findViewById(R.id.button14);
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edi = findViewById(R.id.codigo);
                String cod= edi.getText().toString();

                if(cod.equals("1234")){
                editor.putString("comercial", "E06");
                    editor.putBoolean("first_usage", false);
                    editor.commit();
                Intent intent= new Intent(login.this,MainActivity.class);
                login.this.startActivity(intent);
                    finish();
                }
                else if(cod.equals("0123")){
                    editor.putString("comercial", "E09");
                    editor.putBoolean("first_usage", false);
                    editor.commit();
                    Intent intent= new Intent(login.this,MainActivity.class);
                    login.this.startActivity(intent);
                    finish();


                }
                else{
                    Toast.makeText(login.this, "Erro de codigo!", Toast.LENGTH_LONG).show();
                    editor.putBoolean("first_usage", true);
                    editor.commit();
                }
            }
        });
    }
}
