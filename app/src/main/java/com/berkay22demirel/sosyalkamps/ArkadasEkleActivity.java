package com.berkay22demirel.sosyalkamps;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Adapter.ListArkadasListesiAdapter;
import com.berkay22demirel.sosyalkamps.Background.ArkadasEkleAsyncTask;
import com.berkay22demirel.sosyalkamps.Background.KisiListesiGetirAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateKullaniciListesi;

import java.util.List;

public class ArkadasEkleActivity extends AppCompatActivity implements TaskComplateKullaniciListesi {

    Kullanici profil;

    Intent intent;
    Bundle bundle;

    List<Kullanici> kisiListesi;

    ListView listViewKisiListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arkadas_ekle);

        getSupportActionBar().setTitle("Arkadaş Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        bundle = intent.getExtras();
        profil = (Kullanici) bundle.getSerializable("profil");

        listViewKisiListesi = (ListView) findViewById(R.id.listViewArkadasEkle);

        listViewKisiListesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(ArkadasEkleActivity.this).create();
                alertDialog.setTitle("Arkadaş olarak eklemek istiyor musunuz?");
                final Kullanici k = kisiListesi.get(position);
                alertDialog.setMessage(k.getAd() + " " + k.getSoyad());
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Arkadas arkadas = new Arkadas();
                        arkadas.eposta = profil.getEposta();
                        arkadas.arkadasEposta = k.getEposta();
                        ArkadasEkleAsyncTask arkadasEkleAsyncTask = new ArkadasEkleAsyncTask(ArkadasEkleActivity.this);
                        arkadasEkleAsyncTask.execute(arkadas);

                        Intent geriIntent = new Intent();
                        Bundle geriBundle = new Bundle();
                        geriBundle.putSerializable("kullanici",k);
                        geriIntent.putExtras(geriBundle);
                        setResult(RESULT_OK,geriIntent);
                        finish();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        KisiListesiGetirAsyncTask kisiListesiGetirAsyncTask = new KisiListesiGetirAsyncTask(this);
        kisiListesiGetirAsyncTask.execute(profil.getEposta());
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
    public void onTaskComplete(List<Kullanici> kisiListesi) {
        this.kisiListesi = kisiListesi;

        ListArkadasListesiAdapter adapter = new ListArkadasListesiAdapter(this, R.layout.list_item_arkadas_listesi, kisiListesi);
        listViewKisiListesi.setAdapter(adapter);
    }

    @Override
    public void onTaskCompleteSil(Boolean result) {

    }
}
