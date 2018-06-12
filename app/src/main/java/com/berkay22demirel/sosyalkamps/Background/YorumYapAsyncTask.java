package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateYorumEkle;
import com.berkay22demirel.sosyalkamps.Yorum;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class YorumYapAsyncTask extends AsyncTask<Yorum,String,String> {
    private Context context;
    private TaskComplateYorumEkle callBack;
    private ProgressDialog progressDialog;

    public YorumYapAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateYorumEkle) context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Yorum... params) {
        return yorumKaydet(params[0]);
    }

    private String yorumKaydet(Yorum yorum){
        publishProgress("Yorumunuz kaydediliyor...");
        return NetworkManager.yorumKaydet(yorum);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String yorumKaydetSonucMessage = getYorumKaydetSonucMessage(result);
        Toast.makeText(context,yorumKaydetSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getYorumKaydetSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            callBack.onTaskCompleteYorumEkle(true);
            return "Yorum başarıyla oluşturuldu...";
        }
        callBack.onTaskCompleteYorumEkle(false);
        return "Yorum oluşturulurken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }
}
