package com.berkay22demirel.sosyalkamps.Isletmeci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.IsletmeciGirisYapAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateIsletmeci;
import com.berkay22demirel.sosyalkamps.R;

public class IsletmeciGirisActivity extends AppCompatActivity implements TaskComplateIsletmeci {

    Button buttonGirisYap;
    Button buttonHesapOlustur;

    EditText editTextEposta;
    EditText editTextSifre;

    Isletmeci isletmeci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isletmeci_giris);

        getSupportActionBar().setTitle("İşletmeci Giriş");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonHesapOlustur = (Button) findViewById(R.id.buttonIsletmeciGirisHesapOlustur);
        buttonGirisYap = (Button) findViewById(R.id.buttonIsletmeciGirisGirisYap);
        editTextEposta = (EditText) findViewById(R.id.editTextAIsletmeciGirisKullaniciAdi);
        editTextSifre = (EditText) findViewById(R.id.editTextIsletmeciGirisSifre);

        buttonGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ekranDegerleriniOku()){
                    IsletmeciGirisYapAsyncTask task = new IsletmeciGirisYapAsyncTask(IsletmeciGirisActivity.this);
                    task.execute(isletmeci);
                }
            }
        });

        buttonHesapOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHesapOlustur = new Intent(IsletmeciGirisActivity.this,IsletmeciUyeOlActivity.class);
                startActivity(intentHesapOlustur);
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
            isletmeci = new Isletmeci();
            isletmeci.setEposta(editTextEposta.getText().toString());
            isletmeci.setSifre(editTextSifre.getText().toString());

            return true;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskCompleteIsletmeci(Boolean result) {
        if(result){
            editTextEposta.setText("");
            editTextSifre.setText("");
            Intent intentGirisYap = new Intent(IsletmeciGirisActivity.this,IsletmeciAnasayfaActivity.class);
            Bundle bundleGirisYap = new Bundle();
            bundleGirisYap.putSerializable("isletmeci",isletmeci);
            intentGirisYap.putExtras(bundleGirisYap);
            startActivity(intentGirisYap);
        }
    }
}
