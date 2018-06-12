package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplateKullaniciListesi;
import com.berkay22demirel.sosyalkamps.Kullanici;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class KisiListesiGetirAsyncTask extends AsyncTask<String,String,List<Kullanici>> {
    public static final String PROFIL_BULUNAMADI_ERROR = "-1";
    private String sonucKodu;
    private Context context;
    private TaskComplateKullaniciListesi callBack;
    private ProgressDialog progressDialog;
    List<Kullanici> gelenKullanici;

    public KisiListesiGetirAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateKullaniciListesi) context;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected List<Kullanici> doInBackground(String... params) {
        return getKullanici(params[0]);
    }

    private List<Kullanici> getKullanici(String eposta){
        publishProgress("Kişi listesi Sorgulanıyor...");
        gelenKullanici = NetworkManager.kisiListesiGetir(eposta);
        callBack.onTaskComplete(gelenKullanici);
        return gelenKullanici;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(List<Kullanici> kisiListesi) {
        String sonucMessage = getKisiListesiGetirSonucMessage(sonucKodu);
        if(!TextUtils.isEmpty(sonucMessage)) {
            Toast.makeText(context, sonucMessage, Toast.LENGTH_LONG).show();

            progressDialog.cancel();
            return;
        }

        if(kisiListesi == null || kisiListesi.size() == 0) {
            Toast.makeText(context, "Mevcut kişi yok!!!", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }

        progressDialog.cancel();
    }

    private String getKisiListesiGetirSonucMessage(String sonuc) {

        if(PROFIL_BULUNAMADI_ERROR.equals(sonuc))
            return "Kişi listesi bulunamadı!!!";

        return null;

    }
}
