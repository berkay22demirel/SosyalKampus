package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Paylasim;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class PaylasimEkleAsyncTask extends AsyncTask<Paylasim,String,String> {
    private Context context;
    private TaskComplate callBack;
    private ProgressDialog progressDialog;

    public PaylasimEkleAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplate) context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Paylasim... params) {
        return kullaniciKaydet(params[0]);
    }

    private String kullaniciKaydet(Paylasim paylasim){
        publishProgress("Profiliniz kaydediliyor...");
        return NetworkManager.paylasimKaydet(paylasim);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String paylasimKaydetSonucMessage = getPaylasimKaydetSonucMessage(result);
        Toast.makeText(context,paylasimKaydetSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getPaylasimKaydetSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            callBack.onTaskComplete(true);
            return "Paylaşım başarıyla oluşturuldu...";
        }
        callBack.onTaskComplete(false);
        return "Paylaşım oluşturulurken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }

}
