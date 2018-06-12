package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Background.NetworkManager;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Kullanici;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class GirisYapAsyncTask extends AsyncTask<Kullanici,String,String> {


    private Context context;
    private TaskComplate callBack;
    private ProgressDialog progressDialog;

    public GirisYapAsyncTask(Context context) {
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
        return girisYap(params[0]);
    }

    private String girisYap(Kullanici kullanici){
        publishProgress("Giriş yapılıyor...");
        return NetworkManager.girisYap(kullanici);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String girisYapSonucMessage = getGirisYapSonucMessage(result);
        Toast.makeText(context,girisYapSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }



    private String getGirisYapSonucMessage(String result){
        if(result.equals("1")) {
            callBack.onTaskComplete(true);
            return "Başarıyla giriş yapıldı";
        }
        callBack.onTaskComplete(false);
        return "Giriş yapılamadı!!! Lütfen girdiğiniz bilgileri kontrol ediniz...";
    }

}
