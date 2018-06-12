package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Kullanici;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class KullaniciDuzenleAsyncTask extends AsyncTask<Kullanici,String,String> {

    private Context context;
    private ProgressDialog progressDialog;

    public KullaniciDuzenleAsyncTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Kullanici... params) {
        return kullaniciDuzenle(params[0]);
    }

    private String kullaniciDuzenle(Kullanici kullanici){
        publishProgress("Profiliniz kaydediliyor...");
        return NetworkManager.kullaniciDuzenle(kullanici);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String kullaniciDuzenleSonucMessage = getKullaniciDuzenleSonucMessage(result);
        Toast.makeText(context,kullaniciDuzenleSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getKullaniciDuzenleSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            return "Profiliniz başarıyla kaydedildi...";
        }
        return "Profiliniz kaydedilirken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }
}
