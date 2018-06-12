package com.berkay22demirel.sosyalkamps.Interface;

import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Paylasim;

import java.util.List;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public interface TaskComplateAnasayfa {
    public void onTaskComplete(Kullanici kullanici, List<Paylasim> profilPaylasimListesi, List<Paylasim> paylasimlarListesi, List<Isletmeci> etkinlikVeMekanlarListesi);
}
