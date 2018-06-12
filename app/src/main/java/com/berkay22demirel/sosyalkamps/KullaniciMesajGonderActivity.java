package com.berkay22demirel.sosyalkamps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.MesajGonderAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateMesajGonder;

import org.w3c.dom.Text;

public class KullaniciMesajGonderActivity extends AppCompatActivity implements TaskComplateMesajGonder {

    Button buttonMesajGonder;
    TextView textViewAlici;
    EditText editTextMesaj;

    Intent intent;
    Bundle bundle;

    Mesaj mesaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_mesaj_gonder);

        getSupportActionBar().setTitle("Mesaj Gönder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonMesajGonder = (Button) findViewById(R.id.buttonMesajGonderGonder);
        textViewAlici = (TextView) findViewById(R.id.textViewMesajGonderAlici);
        editTextMesaj = (EditText) findViewById(R.id.editTextMesajGonderIcerik);

        intent = getIntent();
        bundle = intent.getExtras();
        mesaj = (Mesaj) bundle.getSerializable("mesaj");

        textViewAlici.setText("Alici : " + mesaj.getKimeEposta());

        buttonMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextMesaj.getText().toString().equals("")){
                    Toast.makeText(KullaniciMesajGonderActivity.this,"Lütfen bir mesaj giriniz!!!",Toast.LENGTH_LONG).show();
                }else{
                    MesajGonderAsyncTask mesajGonderAsyncTask = new MesajGonderAsyncTask(KullaniciMesajGonderActivity.this);
                    mesaj.setIcerik(editTextMesaj.getText().toString());
                    mesajGonderAsyncTask.execute(mesaj);
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

    @Override
    public void onTaskComplete(Boolean result) {
        if(result == true){
            finish();
        }
    }
}
