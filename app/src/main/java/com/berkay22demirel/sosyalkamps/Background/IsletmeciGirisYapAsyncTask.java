package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateIsletmeci;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class IsletmeciGirisYapAsyncTask extends AsyncTask<Isletmeci,String,String> {


    private Context context;
    private TaskComplateIsletmeci callBack;
    private ProgressDialog progressDialog;

    public IsletmeciGirisYapAsyncTask(Context context) {
        super();
        this.context = context;
        this.callBack = (TaskComplateIsletmeci) context;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Isletmeci... params) {
        return girisYap(params[0]);
    }

    private String girisYap(Isletmeci isletmeci){
        publishProgress("Giriş yapılıyor...");
        return NetworkManager.isletmeciGirisYap(isletmeci);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String isletmeciGirisYapSonucMessage = getIsletmeciGirisYapSonucMessage(result);
        Toast.makeText(context,isletmeciGirisYapSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }



    private String getIsletmeciGirisYapSonucMessage(String result){
        if(result.equals("1")) {
            callBack.onTaskCompleteIsletmeci(true);
            return "Başarıyla giriş yapıldı";
        }
        callBack.onTaskCompleteIsletmeci(false);
        return "Giriş yapılamadı!!! Lütfen girdiğiniz bilgileri kontrol ediniz...";
    }

}
