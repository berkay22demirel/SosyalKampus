package com.berkay22demirel.sosyalkamps.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Arkadas;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplate;
import com.berkay22demirel.sosyalkamps.Kullanici;

/**
 * Created by BerkayDemirel on 10.06.2018.
 */

public class ArkadasEkleAsyncTask extends AsyncTask<Arkadas,String,String> {

    private Context context;
    private ProgressDialog progressDialog;

    public ArkadasEkleAsyncTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Lütfen bekleyin...","İşlem Yürütülüyor...",true,true);
    }

    @Override
    protected String doInBackground(Arkadas... params) {
        return kullaniciKaydet(params[0]);
    }

    private String kullaniciKaydet(Arkadas arkadas){
        publishProgress("Arkadaş Ekleniyor...");
        return NetworkManager.arkadasKaydet(arkadas);
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        progressDialog.setMessage(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        String arkadasKaydetSonucMessage = getArkadasKaydetSonucMessage(result);
        Toast.makeText(context,arkadasKaydetSonucMessage,Toast.LENGTH_LONG).show();
        progressDialog.cancel();
    }

    private String getArkadasKaydetSonucMessage(String sonuc){
        if(sonuc.equals("1")) {
            return "Arkadaş başarıyla eklendi...";
        }
        return "Arkadaş eklenirken bir hata meydana geldi!!! Lütfen tekrar deneyiniz...";
    }

}
