package com.berkay22demirel.sosyalkamps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.PaylasimEkleAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;

public class PaylasimEkleActivity extends AppCompatActivity implements TaskComplate {

    Kullanici profil;
    Paylasim paylasim;

    EditText editTextYazi;
    Button buttonPaylas;

    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paylasim_ekle);

        getSupportActionBar().setTitle("Paylaş");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        bundle = intent.getExtras();
        profil = (Kullanici) bundle.getSerializable("profil");


        editTextYazi = (EditText) findViewById(R.id.editTextPaylasimEkleYazi);
        buttonPaylas = (Button) findViewById(R.id.buttonPaylasimEklePaylas);

        buttonPaylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ekranDegerleriniOku()){
                    PaylasimEkleAsyncTask paylasimEkleAsyncTask = new PaylasimEkleAsyncTask(PaylasimEkleActivity.this);
                    paylasimEkleAsyncTask.execute(paylasim);
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
        if(editTextYazi.getText().toString().equals("")){
            Toast.makeText(this,"Lütfen bir şeyler yazın!!!",Toast.LENGTH_LONG).show();
            return false;

        }else{
            paylasim = new Paylasim(editTextYazi.getText().toString(),profil.getEposta(),"Fotograf Url","10 06 2018");
            return true;
        }
        }

    @Override
    public void onTaskComplete(Boolean result) {
        if(result){
            finish();
        }
    }
}
