package com.berkay22demirel.sosyalkamps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.GirisYapAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Isletmeci.IsletmeciGirisActivity;

public class AnaGirisActivity extends AppCompatActivity implements TaskComplate{
    Button buttonGirisYap;
    Button buttonHesapOlustur;
    TextView textViewIsletmeciGiris;

    EditText editTextEposta;
    EditText editTextSifre;

    Kullanici kullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_giris);
        getSupportActionBar().hide();

        buttonHesapOlustur = (Button) findViewById(R.id.buttonAnaGirisHesapOlustur);
        buttonGirisYap = (Button) findViewById(R.id.buttonAnaGirisGirisYap);
        textViewIsletmeciGiris = (TextView) findViewById(R.id.textViewAnaGirisIsletmeci);

        editTextEposta = (EditText) findViewById(R.id.editTextAnaGirisEposta);
        editTextSifre = (EditText) findViewById(R.id.editTextAnaGirisSifre);;

        buttonGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ekranDegerleriniOku()){
                    GirisYapAsyncTask task = new GirisYapAsyncTask(AnaGirisActivity.this);
                    task.execute(kullanici);
                }

            }
        });

        buttonHesapOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHesapOlustur = new Intent(AnaGirisActivity.this,UyeOlActivity.class);
                startActivity(intentHesapOlustur);
            }
        });

        textViewIsletmeciGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIsletmeci = new Intent(AnaGirisActivity.this,IsletmeciGirisActivity.class);
                startActivity(intentIsletmeci);
            }
        });
    }

    private boolean ekranDegerleriniOku(){
        if(editTextEposta.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir eposta adresi giriniz!!!",Toast.LENGTH_LONG).show();
            return false;

        }else if(editTextSifre.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir şifre giriniz!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            kullanici = new Kullanici(editTextEposta.getText().toString(),editTextSifre.getText().toString());
            return true;

        }
    }

    @Override
    public void onTaskComplete(Boolean result) {
        if(result){
            editTextEposta.setText("");
            editTextSifre.setText("");
            Intent intentGirisYap = new Intent(AnaGirisActivity.this,AnasayfaActivity.class);
            intentGirisYap.putExtra("eposta",kullanici.getEposta());
            startActivity(intentGirisYap);
        }
    }
}
