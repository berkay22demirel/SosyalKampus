package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Kullanici;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class KullaniciKaydetAsyncTask extends AsyncTask<Kullanici,String,String> {

    private Context context;
    private TaskComplate callBack;
    private ProgressDialog progressDialog;

    public KullaniciKaydetAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplate) context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Kullanici... params) {
        return kullaniciKaydet(params[0]);
    }

    private String kullaniciKaydet(Kullanici kullanici){
        publishProgress("Profiliniz kaydediliyor...");
        return NetworkManager.kullaniciKaydet(kullanici);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String kullaniciKaydetSonucMessage = getKullaniciKaydetSonucMessage(result);
        Toast.makeText(context,kullaniciKaydetSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getKullaniciKaydetSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            callBack.onTaskComplete(true);
            return "Profiliniz başarıyla oluşturuldu...";
        }
        callBack.onTaskComplete(false);
        return "Profiliniz oluşturulurken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }




}
