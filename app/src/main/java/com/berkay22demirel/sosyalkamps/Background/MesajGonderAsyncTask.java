package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateMesajGonder;
import com.berkay22demirel.sosyalkamps.Mesaj;
import com.berkay22demirel.sosyalkamps.Paylasim;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class MesajGonderAsyncTask extends AsyncTask<Mesaj,String,String> {
    private Context context;
    private TaskComplateMesajGonder callBack;
    private ProgressDialog progressDialog;

    public MesajGonderAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateMesajGonder) context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Mesaj... params) {
        return mesajGonder(params[0]);
    }

    private String mesajGonder(Mesaj mesaj){
        publishProgress("Mesajınız gönderiliyor...");
        return NetworkManager.mesajGonder(mesaj);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String mesajGonderSonucMessage = getMesajGonderSonucMessage(result);
        Toast.makeText(context,mesajGonderSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getMesajGonderSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            callBack.onTaskComplete(true);
            return "Mesaj başarıyla gönderildi...";
        }
        callBack.onTaskComplete(false);
        return "Mesaj gönderilirken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }
}
