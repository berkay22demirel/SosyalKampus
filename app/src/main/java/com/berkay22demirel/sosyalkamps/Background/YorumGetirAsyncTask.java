package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplateYorum;
import com.berkay22demirel.sosyalkamps.Yorum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class YorumGetirAsyncTask extends AsyncTask<String,String,List<Yorum>> {
    public static final String YORUM_BULUNAMADI_ERROR = "-1";
    private String sonucKodu;
    private Context context;
    private TaskComplateYorum callBack;
    private ProgressDialog progressDialog;
    List<Yorum> gelenYorumlar;

    public YorumGetirAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateYorum) context;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected List<Yorum> doInBackground(String... params) {
        return getYorum(params[0]);
    }

    private List<Yorum> getYorum(String eposta){
        if(TextUtils.isEmpty(eposta)) {
            sonucKodu = YORUM_BULUNAMADI_ERROR;
            gelenYorumlar = new ArrayList<Yorum>();
            callBack.onTaskComplete(gelenYorumlar);
            return new ArrayList<Yorum>();
        }
        publishProgress("Yorumlar Sorgulanıyor...");
        gelenYorumlar = NetworkManager.yorumGetir(eposta);
        callBack.onTaskComplete(gelenYorumlar);
        return gelenYorumlar;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(List<Yorum> yorumlar) {
        String sonucMessage = getYorumGetirSonucMessage(sonucKodu);
        if(!TextUtils.isEmpty(sonucMessage)) {
            Toast.makeText(context, sonucMessage, Toast.LENGTH_LONG).show();

            progressDialog.cancel();
            return;
        }

        if(yorumlar == null || yorumlar.size() == 0) {
            Toast.makeText(context, "Mevcut yorum yok!!!", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
            return;
        }

        progressDialog.cancel();
    }

    private String getYorumGetirSonucMessage(String sonuc) {

        if(YORUM_BULUNAMADI_ERROR.equals(sonuc))
            return "Yorumlar bulunamadı!!!";

        return null;

    }
}
