package com.berkay22demirel.sosyalkamps.Isletmeci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.IsletmeciKaydetAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateIsletmeci;
import com.berkay22demirel.sosyalkamps.R;

public class IsletmeciUyeOlActivity extends AppCompatActivity implements TaskComplateIsletmeci {

    EditText editTextEposta;
    EditText editTextSifre;
    EditText editTextMekanAdi;
    EditText editTextMekanAdresi;
    EditText editTextMekanTelefonu;
    Button buttonUyeOl;

    Isletmeci isletmeci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isletmeci_uye_ol);

        getSupportActionBar().setTitle("İşletmeci Hesabı Oluştur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextEposta = (EditText) findViewById(R.id.editTextIsletmeciUyeOlEmail);
        editTextSifre = (EditText) findViewById(R.id.editTextIsletmeciUyeOlSifre);
        editTextMekanAdi = (EditText) findViewById(R.id.editTextIsletmeciUyeOlMekanAdi);
        editTextMekanAdresi = (EditText) findViewById(R.id.editTextIsletmeciUyeOlMekanAdresi);
        editTextMekanTelefonu = (EditText) findViewById(R.id.editTextIsletmeciUyeOlCepTelefonu);
        buttonUyeOl = (Button) findViewById(R.id.buttonIsletmeciUyeOlUyeOl);

        buttonUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ekranDegerleriniOku()) {
                    IsletmeciKaydetAsyncTask task = new IsletmeciKaydetAsyncTask(IsletmeciUyeOlActivity.this);
                    task.execute(isletmeci);
                }
            }
        });

    }

    private boolean ekranDegerleriniOku() {
        if (editTextEposta.getText().toString().equals("")) {
            Toast.makeText(this, "Lütfen bir eposta adresi giriniz!!!", Toast.LENGTH_LONG).show();
            return false;

        } else if (editTextSifre.getText().toString().equals("")) {
            Toast.makeText(this, "Lütfen bir şifre giriniz!!!", Toast.LENGTH_LONG).show();
            return false;
        } else if (editTextMekanAdi.getText().toString().equals("")) {
            Toast.makeText(this, "Lütfen bir ad giriniz!!!", Toast.LENGTH_LONG).show();
            return false;
        } else if (editTextMekanAdresi.getText().toString().equals("")) {
            Toast.makeText(this, "Lütfen bir adres giriniz!!!", Toast.LENGTH_LONG).show();
            return false;
        } else if (editTextMekanTelefonu.getText().toString().equals("")) {
            Toast.makeText(this, "Lütfen bir telefon giriniz!!!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            isletmeci = new Isletmeci();
            isletmeci.setEposta(editTextEposta.getText().toString());
            isletmeci.setSifre(editTextSifre.getText().toString());
            isletmeci.setMekanAdi(editTextMekanAdi.getText().toString());
            isletmeci.setMekanAdres(editTextMekanAdresi.getText().toString());
            isletmeci.setMekanTelefon(editTextMekanTelefonu.getText().toString());
            isletmeci.setMekanFotografUrl("Fotograf URL");
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
            Intent intentGirisYap = new Intent(IsletmeciUyeOlActivity.this,IsletmeciAnasayfaActivity.class);
            Bundle bundleGirisYap = new Bundle();
            bundleGirisYap.putSerializable("isletmeci",isletmeci);
            intentGirisYap.putExtras(bundleGirisYap);
            startActivity(intentGirisYap);
        }
    }
}
