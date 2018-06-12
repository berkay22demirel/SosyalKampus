package com.berkay22demirel.sosyalkamps.Interface;

import com.berkay22demirel.sosyalkamps.Kullanici;

import java.util.List;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public interface TaskComplateKullaniciListesi {
    public void onTaskComplete(List<Kullanici> arkadasListesi);
    public void onTaskCompleteSil(Boolean result);
}
