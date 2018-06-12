package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Interface.TaskComplateIsletmeci;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Kullanici;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class IsletmeciKaydetAsyncTask extends AsyncTask<Isletmeci,String,String> {

    private Context context;
    private TaskComplateIsletmeci callBack;
    private ProgressDialog progressDialog;

    public IsletmeciKaydetAsyncTask(Context context) {
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
        return isletmeciKaydet(params[0]);
    }

    private String isletmeciKaydet(Isletmeci isletmeci){
        publishProgress("Profiliniz kaydediliyor...");
        return NetworkManager.isletmeciKaydet(isletmeci);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String isletmeciKaydetSonucMessage = getIsletmeciKaydetSonucMessage(result);
        Toast.makeText(context,isletmeciKaydetSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getIsletmeciKaydetSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            callBack.onTaskCompleteIsletmeci(true);
            return "Profiliniz başarıyla oluşturuldu...";
        }
        callBack.onTaskCompleteIsletmeci(false);
        return "Profiliniz oluşturulurken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }




}
