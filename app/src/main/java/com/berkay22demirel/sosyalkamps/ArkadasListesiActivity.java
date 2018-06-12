package com.berkay22demirel.sosyalkamps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.berkay22demirel.sosyalkamps.Adapter.ListArkadasListesiAdapter;
import com.berkay22demirel.sosyalkamps.Background.ArkadasListesiGetirAsyncTask;
import com.berkay22demirel.sosyalkamps.Background.ArkadasSilAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateKullaniciListesi;

import java.util.List;

public class ArkadasListesiActivity extends AppCompatActivity implements TaskComplateKullaniciListesi {

    ListArkadasListesiAdapter adapter;

    Kullanici profil;

    Intent intent;
    Bundle bundle;

    List<Kullanici> arkadasListesi;
    Kullanici silinen;

    ListView listViewArkadasListesi;
    Button buttonArkadasEkle;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle geriBundle = data.getExtras();
            Kullanici arkadas = (Kullanici) geriBundle.getSerializable("kullanici");
            for(int i=0;i<arkadasListesi.size();i++){
                if(arkadasListesi.get(i).getEposta().equals(arkadas.eposta)){
                    break;
                }
                if(i == (arkadasListesi.size() - 1)){
                    arkadasListesi.add(arkadas);
                    adapter.setArkadasListesi(arkadasListesi);
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arkadas_listesi);

        getSupportActionBar().setTitle("Arkadaş Listen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        bundle = intent.getExtras();
        profil = (Kullanici) bundle.getSerializable("profil");

        listViewArkadasListesi = (ListView) findViewById(R.id.listViewArkadasListesi);
        buttonArkadasEkle = (Button) findViewById(R.id.buttonArkadasListesi);

        buttonArkadasEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArkadasListesiActivity.this, ArkadasEkleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("profil",profil);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        listViewArkadasListesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentMesaj = new Intent(ArkadasListesiActivity.this, KullaniciMesajGonderActivity.class);
                Bundle bundleMesaj = new Bundle();
                Kullanici k = arkadasListesi.get(position);
                Mesaj mesaj = new Mesaj();
                mesaj.setKimdenEposta(profil.eposta);
                mesaj.setKimeEposta(k.getEposta());
                bundleMesaj.putSerializable("mesaj",mesaj);
                intentMesaj.putExtras(bundleMesaj);
                startActivity(intentMesaj);
            }
        });

        listViewArkadasListesi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(ArkadasListesiActivity.this).create();
                alertDialog.setTitle("Arkadaşı takipten çıkmak istiyor musunuz?");
                final Kullanici k = arkadasListesi.get(position);
                alertDialog.setMessage(k.getAd() + " " + k.getSoyad());
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Arkadas arkadas = new Arkadas();
                        arkadas.setEposta(profil.getEposta());
                        arkadas.setArkadasEposta(k.getEposta());
                        ArkadasSilAsyncTask arkadasSilAsyncTask = new ArkadasSilAsyncTask(ArkadasListesiActivity.this);
                        arkadasSilAsyncTask.execute(arkadas);
                        silinen = k;
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                return false;
            }
        });

        ArkadasListesiGetirAsyncTask arkadasListesiGetirAsyncTask = new ArkadasListesiGetirAsyncTask(ArkadasListesiActivity.this);
        arkadasListesiGetirAsyncTask.execute(profil.getEposta());
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
    public void onTaskComplete(List<Kullanici> arkadasListesi) {
        this.arkadasListesi = arkadasListesi;

        adapter = new ListArkadasListesiAdapter(this, R.layout.list_item_arkadas_listesi, arkadasListesi);
        listViewArkadasListesi.setAdapter(adapter);

    }

    @Override
    public void onTaskCompleteSil(Boolean result) {
        if(result == true){
            for(int i=0;i<arkadasListesi.size();i++){
                if(arkadasListesi.get(i).getEposta().equals(silinen.eposta)){
                    arkadasListesi.remove(i);
                    adapter.setArkadasListesi(arkadasListesi);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
