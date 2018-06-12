package com.berkay22demirel.sosyalkamps;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.KullaniciKaydetAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;

import java.text.DateFormat;
import java.util.Calendar;

public class UyeOlActivity extends AppCompatActivity implements TaskComplate {

    private DateFormat dateFormat = DateFormat.getDateInstance();

    TextView textViewDogumTarihi;
    EditText editTextEposta;
    EditText editTextSifre;
    EditText editTextAd;
    EditText editTextSoyad;
    EditText editTextCepTelefonu;
    RadioButton radioButtonCinsiyetErkek;
    RadioButton radioButtonCinsiyetKadin;
    Button buttonUyeOl;

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
        setContentView(R.layout.activity_uye_ol);

        getSupportActionBar().setTitle("Hesap Oluştur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewDogumTarihi = (TextView) findViewById(R.id.textViewDogumTarihi);
        editTextEposta = (EditText) findViewById(R.id.editTextUyeOlEposta);
        editTextSifre = (EditText) findViewById(R.id.editTextUyeOlSifre);
        editTextAd = (EditText) findViewById(R.id.editTextUyeOlAd);
        editTextSoyad = (EditText) findViewById(R.id.editTextUyeOlSoyad);
        editTextCepTelefonu = (EditText) findViewById(R.id.editTextUyeOlCepTelefonu);
        radioButtonCinsiyetErkek = (RadioButton) findViewById(R.id.radioButtonUyeOlErkek);
        radioButtonCinsiyetKadin = (RadioButton) findViewById(R.id.radioButtonUyeOlKadin);
        buttonUyeOl = (Button) findViewById(R.id.buttonUyeOlUyeOl);

        textViewDogumTarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UyeOlActivity.this, date, myCalendar
                        .get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        buttonUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ekranDegerleriniOku()){
                    KullaniciKaydetAsyncTask task = new KullaniciKaydetAsyncTask(UyeOlActivity.this);
                    task.execute(kullanici);
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
        }else{
            if(radioButtonCinsiyetErkek.isChecked()){
                kullanici = new Kullanici(editTextEposta.getText().toString(),editTextSifre.getText().toString(),
                        editTextAd.getText().toString(),editTextSoyad.getText().toString(),
                        editTextCepTelefonu.getText().toString(),textViewDogumTarihi.getText().toString(),"Erkek");
                return true;
            }
            else{
                kullanici = new Kullanici(editTextEposta.getText().toString(),editTextSifre.getText().toString(),
                        editTextAd.getText().toString(),editTextSoyad.getText().toString(),
                        editTextCepTelefonu.getText().toString(),textViewDogumTarihi.getText().toString(),"Kadın");
                return true;
            }
        }
    }

    @Override
    public void onTaskComplete(Boolean result) {
        if(result){
            Intent intentGirisYap = new Intent(UyeOlActivity.this,AnasayfaActivity.class);
            intentGirisYap.putExtra("eposta",kullanici.getEposta());
            startActivity(intentGirisYap);
        }
    }
}
