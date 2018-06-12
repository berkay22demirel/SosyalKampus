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

public class ArkadasListesiGetirAsyncTask extends AsyncTask<String,String,List<Kullanici>> {
    public static final String PROFIL_BULUNAMADI_ERROR = "-1";
    private String sonucKodu;
    private Context context;
    private TaskComplateKullaniciListesi callBack;
    private ProgressDialog progressDialog;
    List<Kullanici> gelenKullanici;

    public ArkadasListesiGetirAsyncTask(Context context) {
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
        if(TextUtils.isEmpty(eposta)) {
            sonucKodu = PROFIL_BULUNAMADI_ERROR;
            gelenKullanici = new ArrayList<Kullanici>();
            callBack.onTaskComplete(gelenKullanici);
            return new ArrayList<Kullanici>();
        }
        publishProgress("Arkadaş listesi Sorgulanıyor...");
        gelenKullanici = NetworkManager.arkadasListesiGetir(eposta);
        callBack.onTaskComplete(gelenKullanici);
        return gelenKullanici;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(List<Kullanici> arkadasListesi) {
        String sonucMessage = getArkadasListesiGetirSonucMessage(sonucKodu);
        if(!TextUtils.isEmpty(sonucMessage)) {
            Toast.makeText(context, sonucMessage, Toast.LENGTH_LONG).show();

            progressDialog.cancel();
            return;
        }

        if(arkadasListesi == null || arkadasListesi.size() == 0) {
            Toast.makeText(context, "Mevcut arkadaş yok!!!", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }

        progressDialog.cancel();
    }

    private String getArkadasListesiGetirSonucMessage(String sonuc) {

        if(PROFIL_BULUNAMADI_ERROR.equals(sonuc))
            return "Arkadaş listesi bulunamadı!!!";

        return null;

    }
}
