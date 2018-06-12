package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplateIsletmeciGetir;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Yorum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class IsletmeciAnasayfaAsyncTask extends AsyncTask<String,String,Isletmeci> {

    public static final String PROFIL_BULUNAMADI_ERROR = "-1";
    private String sonucKodu;
    private Context context;
    private TaskComplateIsletmeciGetir callBack;
    private ProgressDialog progressDialog;
    Isletmeci gelenIsletmeci = null;
    List<Yorum> gelenYorumlar = null;

    public IsletmeciAnasayfaAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateIsletmeciGetir) context;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected Isletmeci doInBackground(String... params) {
        return getIsletmeci(params[0]);
    }

    private Isletmeci getIsletmeci(String eposta){
        if(TextUtils.isEmpty(eposta)) {
            sonucKodu = PROFIL_BULUNAMADI_ERROR;
            gelenIsletmeci = new Isletmeci();
            gelenYorumlar = new ArrayList<>();
            callBack.onTaskCompleteIsletmeciGetir(gelenIsletmeci,gelenYorumlar);
            return gelenIsletmeci;
        }
        publishProgress("Profil Sorgulanıyor...");
        gelenIsletmeci = NetworkManager.isletmeciGetir(eposta);
        gelenYorumlar = NetworkManager.yorumGetir(eposta);
        callBack.onTaskCompleteIsletmeciGetir(gelenIsletmeci,gelenYorumlar);
        return gelenIsletmeci;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(Isletmeci isletmeci) {
        String sonucMessage = getIsletmeciGetirSonucMessage(sonucKodu);
        if(!TextUtils.isEmpty(sonucMessage)) {
            Toast.makeText(context, sonucMessage, Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }

        if(isletmeci == null || isletmeci.getEposta() == null) {
            Toast.makeText(context, "Profil bulunamadı!!! Lütfen daha sonra tekrar deneyiniz...", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }
        if(gelenYorumlar == null || gelenYorumlar.size() == 0) {
            Toast.makeText(context, "Mevcut yorum yok...", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }

        progressDialog.cancel();
    }

    private String getIsletmeciGetirSonucMessage(String sonuc) {

        if(PROFIL_BULUNAMADI_ERROR.equals(sonuc))
            return "Profil bulunamadı!!! Lütfen daha sonra tekrar deneyiniz...";

        return null;

    }
}
