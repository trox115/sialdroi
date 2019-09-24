package com.example.antniofernandes.sialdroid;

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

public class terceira extends AppCompatActivity {
    private RadioButton nao;
    private RadioButton nao2;
    private RadioButton nao3;

    private TextView ob7;
    private TextView ob8;
    private TextView ob9;
    public String obs4;
    public String obs5;
    public String obs6;
    public String obs7;
    public String m1;
    public String m2;
    public String m3;
    public String ace1;
    public String ace2;
    public String porcentagem;
    public TextView percentagem;
    public String p5 = PdfObject.NOTHING;
    public String p6 = PdfObject.NOTHING;
    public String p7 = PdfObject.NOTHING;

    private RadioButton sim;
    private RadioButton sim2;
    private RadioButton sim3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceira);
        this.ob7 =(TextView) findViewById(R.id.editText9);
        this.percentagem = (TextView) findViewById(R.id.editText8);
        this.percentagem.setVisibility(View.INVISIBLE);
        this.sim2 = (RadioButton) findViewById(R.id.sim2);
        this.nao2 = (RadioButton) findViewById(R.id.nao2);
        this.sim3 = (RadioButton) findViewById(R.id.sim3);
        this.nao3 = (RadioButton) findViewById(R.id.nao3);
        terceira.this.ob7.setVisibility(View.INVISIBLE);

        this.sim = (RadioButton) findViewById(R.id.sim);
        this.nao = (RadioButton) findViewById(R.id.nao);

        ((RadioGroup) findViewById(R.id.radioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (terceira.this.sim.isChecked()) {
                    terceira.this.p5 = "Sim";
                } else if (terceira.this.nao.isChecked()) {
                    terceira.this.p5 = "N\u00e3o";
                }
            }
        });
        ((RadioGroup) findViewById(R.id.radioGroup2)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (terceira.this.sim2.isChecked()) {
                    terceira.this.p6 = "Sim";

                } else if (terceira.this.nao2.isChecked()) {
                    terceira.this.p6 = "N\u00e3o";

                }
            }
        });
        ((RadioGroup) findViewById(R.id.radio1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (terceira.this.sim3.isChecked()) {
                    terceira.this.p7 = "Sim";
                    terceira.this.ob7.setVisibility(View.VISIBLE);
                } else if (terceira.this.nao3.isChecked()) {
                    terceira.this.p7 = "N\u00e3o";
                    terceira.this.ob7.setVisibility(View.INVISIBLE);
                }
            }
        });


Button save = (Button) findViewById(R.id.button10);

save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        terceira.this.porcentagem = terceira.this.percentagem.getText().toString();

        String firm = terceira.this.getIntent().getStringExtra("cliente");
        String localizacao = terceira.this.getIntent().getStringExtra("localizacao");
        String contacto = terceira.this.getIntent().getStringExtra("contacto");
        String nomecontacto = terceira.this.getIntent().getStringExtra("nomecontacto");
        String spinner = terceira.this.getIntent().getStringExtra("spinner");
        String hora_chegada = terceira.this.getIntent().getStringExtra("hora_chegada");
        String p1 = terceira.this.getIntent().getStringExtra("r1");
        String localidades=terceira.this.getIntent().getStringExtra("locall");
        String p2 = terceira.this.getIntent().getStringExtra("r2");
        String p3 = terceira.this.getIntent().getStringExtra("r3");
        String p4 = terceira.this.getIntent().getStringExtra("r4");
        String obs = terceira.this.getIntent().getStringExtra("ob1");
        String obs2 = terceira.this.getIntent().getStringExtra("ob2");
        String obs3 = terceira.this.getIntent().getStringExtra("ob3");
        String m1 = terceira.this.getIntent().getStringExtra("marca1");
        String m2 = terceira.this.getIntent().getStringExtra("marca2");
        String m3 = terceira.this.getIntent().getStringExtra("marca3");
        String ace1 = terceira.this.getIntent().getStringExtra("acessorio1");
        String ace2 = terceira.this.getIntent().getStringExtra("acessorio2");
        terceira.this.obs7= terceira.this.ob7.getText().toString();

        String activity = terceira.this.getIntent().getStringExtra("activity");
        String a1 = terceira.this.getIntent().getStringExtra("r00");
        if (terceira.this.p5.equals(PdfObject.NOTHING) || terceira.this.p6.equals(PdfObject.NOTHING)) {
            Toast.makeText(terceira.this.getApplicationContext(), "Por favor preencha todos os campos.",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(terceira.this.getApplicationContext(), gerador.class);
        intent.putExtra("cliente", firm);
        intent.putExtra("localizacao", localizacao);
        intent.putExtra("contacto", contacto);
        intent.putExtra("nomecontacto", nomecontacto);
        intent.putExtra("spinner", spinner);
        intent.putExtra("hora_chegada", hora_chegada);
        intent.putExtra("r1", p1);
        intent.putExtra("r2", p2);
        intent.putExtra("r3", p3);
        intent.putExtra("r4", p4);
        intent.putExtra("locall",localidades);
        intent.putExtra("r7", terceira.this.p7);
        intent.putExtra("r5", terceira.this.p5);
        intent.putExtra("r6", terceira.this.p6);
        intent.putExtra("ob1", obs);
        intent.putExtra("ob2", obs2);
        intent.putExtra("ob3", obs3);
        intent.putExtra("ob7", obs7);
        intent.putExtra("marca1",m1);
        intent.putExtra("marca2",m2);
        intent.putExtra("marca3",m3);
        intent.putExtra("acessorio1",ace1);
        intent.putExtra("acessorio2",ace2);

        intent.putExtra("percentagem", terceira.this.porcentagem);

        intent.putExtra("r00", a1);
        intent.putExtra("activty", activity);
        terceira.this.startActivity(intent);
    }
});

    }

}
