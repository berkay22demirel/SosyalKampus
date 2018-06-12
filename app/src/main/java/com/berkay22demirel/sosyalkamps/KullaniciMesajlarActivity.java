package com.berkay22demirel.sosyalkamps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.berkay22demirel.sosyalkamps.Adapter.ListMesajAdapter;
import com.berkay22demirel.sosyalkamps.Background.MesajGetirAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateMesajListesi;

import java.util.List;

public class KullaniciMesajlarActivity extends AppCompatActivity implements TaskComplateMesajListesi {

    ListView listViewMesajlar;

    Intent intent;
    Bundle bundle;

    Kullanici profil;

    List<Mesaj> mesajListesi;
    ListMesajAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_mesajlar);

        getSupportActionBar().setTitle("Mesajlar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewMesajlar = (ListView) findViewById(R.id.listViewMesajlar);

        intent = getIntent();
        bundle = intent.getExtras();
        profil = (Kullanici) bundle.getSerializable("kullanici");

        MesajGetirAsyncTask mesajGetirAsyncTask = new MesajGetirAsyncTask(KullaniciMesajlarActivity.this);
        mesajGetirAsyncTask.execute(profil.getEposta());

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
    public void onTaskComplete(List<Mesaj> mesajListesi) {
        this.mesajListesi = mesajListesi;

        adapter = new ListMesajAdapter(KullaniciMesajlarActivity.this,R.layout.list_item_mesaj,mesajListesi);
        listViewMesajlar.setAdapter(adapter);
    }
}
