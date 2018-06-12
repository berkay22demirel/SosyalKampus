package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Arkadas;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateKullaniciListesi;

/**
 * Created by BerkayDemirel on 10.06.2018.
 */

public class ArkadasSilAsyncTask extends AsyncTask<Arkadas,String,String> {

    private Context context;
    private TaskComplateKullaniciListesi callBack;
    private ProgressDialog progressDialog;

    public ArkadasSilAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateKullaniciListesi) context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Arkadas... params) {
        return kullaniciSil(params[0]);
    }

    private String kullaniciSil(Arkadas arkadas){
        publishProgress("Arkadaş Siliniyor...");
        return NetworkManager.arkadasSil(arkadas);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String arkadasSilSonucMessage = getArkadasSilSonucMessage(result);
        Toast.makeText(context,arkadasSilSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getArkadasSilSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            callBack.onTaskCompleteSil(true);
            return "Arkadaş başarıyla silindi...";
        }
        callBack.onTaskCompleteSil(false);
        return "Arkadaş silinirken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }

}
