package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplateAnasayfa;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Paylasim;

import java.util.List;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class AnasayfaAsyncTask extends AsyncTask<String,String,Kullanici>{

    public static final String PROFIL_BULUNAMADI_ERROR = "-1";
    private String sonucKodu;
    private Context context;
    private TaskComplateAnasayfa callBack;
    private ProgressDialog progressDialog;
    Kullanici gelenKullanici = null;
    List<Paylasim> profilPaylasimListesi = null;
    List<Paylasim> paylasimlarListesi = null;
    List<Isletmeci> etkinlikVeMekanlarListesi = null;

    public AnasayfaAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateAnasayfa) context;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected Kullanici doInBackground(String... params) {
        return getAnasayfa(params[0]);
    }

    private Kullanici getAnasayfa(String eposta){
        if(TextUtils.isEmpty(eposta)) {
            sonucKodu = PROFIL_BULUNAMADI_ERROR;
            gelenKullanici = new Kullanici();
            callBack.onTaskComplete(gelenKullanici, profilPaylasimListesi,paylasimlarListesi,etkinlikVeMekanlarListesi);
            return gelenKullanici;
        }
        publishProgress("Profil Sorgulanıyor...");
        gelenKullanici = NetworkManager.profilGetir(eposta);
        profilPaylasimListesi = NetworkManager.profilPaylasimGetir(eposta);
        paylasimlarListesi = NetworkManager.zamanTuneliPaylasimGetir(eposta);
        etkinlikVeMekanlarListesi = NetworkManager.etkinlikVeMekanGetir(eposta);

        callBack.onTaskComplete(gelenKullanici, profilPaylasimListesi,paylasimlarListesi,etkinlikVeMekanlarListesi);
        return gelenKullanici;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(Kullanici kullanici) {
        String sonucMessage = getProfilGetirSonucMessage(sonucKodu);
        if(!TextUtils.isEmpty(sonucMessage)) {
            Toast.makeText(context, sonucMessage, Toast.LENGTH_LONG).show();

            progressDialog.cancel();
            return;
        }

        if(kullanici == null || kullanici.getEposta() == null) {
            Toast.makeText(context, "Profil bulunamadı!!! Lütfen daha sonra tekrar deneyiniz...", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }

        progressDialog.cancel();
    }

    private String getProfilGetirSonucMessage(String sonuc) {

        if(PROFIL_BULUNAMADI_ERROR.equals(sonuc))
            return "Profil bulunamadı!!! Lütfen daha sonra tekrar deneyiniz...";

        return null;

    }

}
