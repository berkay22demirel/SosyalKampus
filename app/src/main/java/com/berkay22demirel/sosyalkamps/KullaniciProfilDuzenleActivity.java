package com.berkay22demirel.sosyalkamps;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.KullaniciDuzenleAsyncTask;
import com.berkay22demirel.sosyalkamps.Background.KullaniciKaydetAsyncTask;

import java.text.DateFormat;
import java.util.Calendar;

public class KullaniciProfilDuzenleActivity extends AppCompatActivity {

    private DateFormat dateFormat = DateFormat.getDateInstance();

    TextView textViewDogumTarihi;
    EditText editTextEposta;
    EditText editTextSifre;
    EditText editTextBio;
    EditText editTextAd;
    EditText editTextSoyad;
    EditText editTextCepTelefonu;
    RadioButton radioButtonCinsiyetErkek;
    RadioButton radioButtonCinsiyetKadin;
    Button buttonDuzenle;

    Intent intent;
    Bundle bundle;

    Kullanici kullanici;

    private Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            textViewDogumTarihi.setText(dateFormat.format(myCalendar.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_profil_duzenle);

        getSupportActionBar().setTitle("Hesap Düzenle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewDogumTarihi = (TextView) findViewById(R.id.textViewKullaniciDuzenleDogumTarihi);
        editTextEposta = (EditText) findViewById(R.id.editTextKullaniciDuzenleEposta);
        editTextSifre = (EditText) findViewById(R.id.editTextKullaniciDuzenleSifre);
        editTextBio = (EditText) findViewById(R.id.editTextKullaniciDuzenleBio);
        editTextAd = (EditText) findViewById(R.id.editTextKullaniciDuzenleAd);
        editTextSoyad = (EditText) findViewById(R.id.editTextKullaniciDuzenleSoyad);
        editTextCepTelefonu = (EditText) findViewById(R.id.editTextKullaniciDuzenleCepTelefonu);
        radioButtonCinsiyetErkek = (RadioButton) findViewById(R.id.radioButtonKullaniciDuzenleErkek);
        radioButtonCinsiyetKadin = (RadioButton) findViewById(R.id.radioButtonKullaniciDuzenleKadin);
        buttonDuzenle = (Button) findViewById(R.id.buttonKullaniciDuzenleDuzenle);

        intent = getIntent();
        bundle = intent.getExtras();
        kullanici = (Kullanici) bundle.getSerializable("kullanici");
        ekranDegerleriniGir();

        textViewDogumTarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(KullaniciProfilDuzenleActivity.this, date, myCalendar
                        .get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        buttonDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ekranDegerleriniOku()){
                    KullaniciDuzenleAsyncTask kullaniciDuzenleAsyncTask = new KullaniciDuzenleAsyncTask(KullaniciProfilDuzenleActivity.this);
                    kullaniciDuzenleAsyncTask.execute(kullanici);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private boolean ekranDegerleriniOku(){
        if(editTextEposta.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir eposta adresi giriniz!!!",Toast.LENGTH_LONG).show();
            return false;

        }else if(editTextSifre.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir şifre giriniz!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(editTextAd.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir ad giriniz!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(editTextSoyad.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir soyad giriniz!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(editTextCepTelefonu.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir cep telefonu giriniz!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(textViewDogumTarihi.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen doğum günü tarihini seçiniz!!!",Toast.LENGTH_LONG).show();
            return false;
        } else if(editTextBio.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen biyografi giriniz!!!!!!",Toast.LENGTH_LONG).show();
            return false;
        }else{
            if(radioButtonCinsiyetErkek.isChecked()){
                kullanici = new Kullanici(editTextEposta.getText().toString(),editTextSifre.getText().toString(),
                        editTextAd.getText().toString(),editTextSoyad.getText().toString(),
                        editTextCepTelefonu.getText().toString(),textViewDogumTarihi.getText().toString(),"Erkek",editTextBio.getText().toString());
                return true;
            }
            else{
                kullanici = new Kullanici(editTextEposta.getText().toString(),editTextSifre.getText().toString(),
                        editTextAd.getText().toString(),editTextSoyad.getText().toString(),
                        editTextCepTelefonu.getText().toString(),textViewDogumTarihi.getText().toString(),"Kadın",editTextBio.getText().toString());
                return true;
            }
        }
    }

    private void ekranDegerleriniGir(){
        textViewDogumTarihi.setText(kullanici.getDogumtarihi());
        editTextEposta.setText(kullanici.getEposta());
        editTextSifre.setText(kullanici.getSifre());
        editTextAd.setText(kullanici.getAd());
        editTextSoyad.setText(kullanici.getSoyad());
        editTextCepTelefonu.setText(kullanici.getCeptelefonu());
        if(kullanici.getCinsiyet().equals("Erkek")){
            radioButtonCinsiyetErkek.setChecked(true);
        }else{
            radioButtonCinsiyetKadin.setChecked(true);
        }
        if(kullanici.getBio() != null){
            editTextBio.setText(kullanici.getBio());
        }
    }
}
