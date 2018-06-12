package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplateKullaniciListesi;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateMesajListesi;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Mesaj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class MesajGetirAsyncTask extends AsyncTask<String,String,List<Mesaj>> {
    public static final String MESAJ_BULUNAMADI_ERROR = "-1";
    private String sonucKodu;
    private Context context;
    private TaskComplateMesajListesi callBack;
    private ProgressDialog progressDialog;
    List<Mesaj> gelenMesajlar;

    public MesajGetirAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateMesajListesi) context;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected List<Mesaj> doInBackground(String... params) {
        return getMesaj(params[0]);
    }

    private List<Mesaj> getMesaj(String eposta){
        if(TextUtils.isEmpty(eposta)) {
            sonucKodu = MESAJ_BULUNAMADI_ERROR;
            gelenMesajlar = new ArrayList<Mesaj>();
            callBack.onTaskComplete(gelenMesajlar);
            return new ArrayList<Mesaj>();
        }
        publishProgress("Mesajlar Sorgulanıyor...");
        gelenMesajlar = NetworkManager.mesajGetir(eposta);
        callBack.onTaskComplete(gelenMesajlar);
        return gelenMesajlar;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(List<Mesaj> mesajlar) {
        String sonucMessage = getMesajGetirSonucMessage(sonucKodu);
        if(!TextUtils.isEmpty(sonucMessage)) {
            Toast.makeText(context, sonucMessage, Toast.LENGTH_LONG).show();

            progressDialog.cancel();
            return;
        }

        if(mesajlar == null || mesajlar.size() == 0) {
            Toast.makeText(context, "Mevcut mesaj yok!!!", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }

        progressDialog.cancel();
    }

    private String getMesajGetirSonucMessage(String sonuc) {

        if(MESAJ_BULUNAMADI_ERROR.equals(sonuc))
            return "Mesajlar bulunamadı!!!";

        return null;

    }
}
