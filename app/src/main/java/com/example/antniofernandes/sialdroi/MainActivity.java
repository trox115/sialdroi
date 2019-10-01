package com.example.antniofernandes.sialdroi;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pushlink.android.PushLink;
import com.pushlink.android.StrategyEnum;

public class MainActivity extends AppCompatActivity {
    private int STORAGE_CODE =1000; int REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String yourDeviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        PushLink.addMetadata("Brand", Build.BRAND);
        PushLink.addMetadata("Model", Build.MODEL);
        PushLink.setCurrentStrategy(StrategyEnum.STATUS_BAR);
        PushLink.start(this, R.mipmap.ic_launcher, "76mhebgfe6qnmepo", yourDeviceID);
        setContentView(R.layout.activity_main);

        SharedPreferences editor = getSharedPreferences("minhaspreferencias", MODE_PRIVATE);

        boolean isFirstUsage = editor.getBoolean("first_usage", true);

        if(isFirstUsage) {

            Intent intent= new Intent(MainActivity.this, login.class);
            MainActivity.this.startActivity(intent);
            finish();

            // save preferences

        } else {

        }







        Button gerar =(Button) findViewById(R.id.button);

        Button opcoes=(Button) findViewById(R.id.button4);
        Button atualizar=(Button) findViewById(R.id.button2);

        final Button planeador= (Button) findViewById(R.id.button3);

        opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Clientes.class);
                MainActivity.this.startActivity(intent);
            }
        });

        gerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, gerar.class);
                MainActivity.this.startActivity(intent);
            }
        });

        planeador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent (MainActivity.this, planeador.class);
                MainActivity.this.startActivity(inten);
            }
        });
        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String premissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        requestPermissions(new String[]{premissions}, STORAGE_CODE);
                        String[] INITIAL_PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(INITIAL_PERMS, REQUEST);
                    } else {


                        Intent intent = new Intent(MainActivity.this, atualizacao.class);
                        MainActivity.this.startActivity(intent);
                    }
                }
                    else{
                        Intent intent = new Intent(MainActivity.this, atualizacao.class);
                        MainActivity.this.startActivity(intent);

                }
            }
        });

    }




}
