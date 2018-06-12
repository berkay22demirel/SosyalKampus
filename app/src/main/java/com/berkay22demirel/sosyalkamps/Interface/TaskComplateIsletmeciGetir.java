package com.berkay22demirel.sosyalkamps.Interface;

import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Yorum;

import java.util.List;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public interface TaskComplateIsletmeciGetir {
    public void onTaskCompleteIsletmeciGetir(Isletmeci isletmeci,List<Yorum> yorumListesi);
}
