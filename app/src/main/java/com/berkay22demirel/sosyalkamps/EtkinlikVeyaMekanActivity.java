package com.berkay22demirel.sosyalkamps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Adapter.ListEtkinlikVeyaMekanlarYorumlarAdapter;
import com.berkay22demirel.sosyalkamps.Background.PaylasimEkleAsyncTask;
import com.berkay22demirel.sosyalkamps.Background.YorumGetirAsyncTask;
import com.berkay22demirel.sosyalkamps.Background.YorumYapAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateYorum;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateYorumEkle;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;

import java.util.List;

public class EtkinlikVeyaMekanActivity extends AppCompatActivity implements TaskComplateYorum,TaskComplateYorumEkle,TaskComplate {

    Intent intent;
    Bundle bundle;

    TextView textViewMekanAdi;
    TextView textViewMekanAdresi;
    TextView textViewMekanTelefonu;
    EditText editTextYorumYap;
    Button buttonYorumYap;
    Button buttonYerBildirimiYap;
    ListView listViewYorumlar;

    Isletmeci mekan;
    Yorum yorum;
    List<Yorum> yorumListesi;
    String kullaniciEposta;

    ListEtkinlikVeyaMekanlarYorumlarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik_veya_mekan);

        textViewMekanAdi = (TextView) findViewById(R.id.textViewEtkinlikVeyaMekanlarMekanAdi);
        textViewMekanAdresi = (TextView) findViewById(R.id.textViewEtkinlikVeyaMekanlarMekanAdresi);
        textViewMekanTelefonu = (TextView) findViewById(R.id.textViewEtkinlikVeyaMekanlarMekanTelefonu);
        editTextYorumYap = (EditText) findViewById(R.id.editTextEtkinlikVeyaMekanlarYorumYap);
        buttonYorumYap = (Button) findViewById(R.id.buttonEtkinlikVeyaMekanlarYorumYap);
        buttonYerBildirimiYap = (Button) findViewById(R.id.buttonEtkinlikVeyaMekanlarYerBildirimiYap);
        listViewYorumlar = (ListView) findViewById(R.id.listViewEtkinlikVeyaMekanlarYorumlar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        bundle = intent.getExtras();
        mekan = (Isletmeci) bundle.getSerializable("isletmeci");
        kullaniciEposta = bundle.getString("eposta");

        getSupportActionBar().setTitle(mekan.getMekanAdi());

        textViewMekanAdi.setText(mekan.getMekanAdi());
        textViewMekanAdresi.setText(mekan.getMekanAdres());
        textViewMekanTelefonu.setText(mekan.getMekanTelefon());

        final YorumGetirAsyncTask yorumGetirAsyncTask = new YorumGetirAsyncTask(this);
        yorumGetirAsyncTask.execute(mekan.getEposta());

        buttonYorumYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ekranDegerleriniOku()){
                    YorumYapAsyncTask yorumYapAsyncTask = new YorumYapAsyncTask(EtkinlikVeyaMekanActivity.this);
                    yorumYapAsyncTask.execute(yorum);
                }
            }
        });

        buttonYerBildirimiYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paylasim yerBildirimi = new Paylasim();
                yerBildirimi.setEposta(kullaniciEposta);
                yerBildirimi.setPaylasimZamani("12.06.2018");
                yerBildirimi.setYazi(mekan.getMekanAdi() + " mekanında yer bildirimi yaptı.");
                yerBildirimi.setFotografurl(mekan.getMekanFotografUrl());
                PaylasimEkleAsyncTask paylasimEkleAsyncTask = new PaylasimEkleAsyncTask(EtkinlikVeyaMekanActivity.this);
                paylasimEkleAsyncTask.execute(yerBildirimi);
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

    @Override
    public void onTaskComplete(List<Yorum> yorumListesi) {
        this.yorumListesi = yorumListesi;

        adapter = new ListEtkinlikVeyaMekanlarYorumlarAdapter(this,R.layout.list_item_yorumlar,yorumListesi);
        listViewYorumlar.setAdapter(adapter);
    }

    @Override
    public void onTaskCompleteYorumEkle(Boolean result) {
        if(result){
            yorumListesi.add(yorum);
            adapter.setYorumlarListesi(yorumListesi);
            adapter.notifyDataSetChanged();
        }
    }

    public boolean ekranDegerleriniOku(){
        if(editTextYorumYap.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir yorum giriniz!!!",Toast.LENGTH_LONG).show();
            return false;

        }
        else{
            yorum = new Yorum();
            yorum.setMekanEposta(mekan.getEposta());
            yorum.setYapilanYorum(editTextYorumYap.getText().toString());
            yorum.setPaylasimZamani("12.06.2018");
            yorum.setYorumYapanEposta(kullaniciEposta);
            return true;

        }
    }

    @Override
    public void onTaskComplete(Boolean result) {
        if(result){
            Toast.makeText(EtkinlikVeyaMekanActivity.this,"Yer bildirimi başarıyla yapıldı...",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(EtkinlikVeyaMekanActivity.this,"Yer bildirimi yapılırken bir hata meydana geldi!!!",Toast.LENGTH_LONG).show();
        }
    }
}
