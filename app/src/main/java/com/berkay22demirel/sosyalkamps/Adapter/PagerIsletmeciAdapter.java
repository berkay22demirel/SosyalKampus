package com.berkay22demirel.sosyalkamps.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.berkay22demirel.sosyalkamps.Isletmeci.Fragment.IsletmeciProfil;
import com.berkay22demirel.sosyalkamps.Isletmeci.Fragment.IsletmeciYorumlar;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Yorum;

import java.util.List;

/**
 * Created by BerkayDemirel on 06.06.2018.
 */

public class PagerIsletmeciAdapter extends FragmentStatePagerAdapter {
    int boyut;
    FragmentManager fm;
    Isletmeci isletmeci;
    List<Yorum> yorumlarListesi;
    Isletmeci sonIsletmeci;

    public PagerIsletmeciAdapter(FragmentManager fm, int boyut,Isletmeci isletmeci,List<Yorum> yorumlarListesi,Isletmeci sonIsletmeci) {

        super(fm);
        this.fm = fm;
        this.boyut = boyut;
        this.isletmeci = isletmeci;
        this.yorumlarListesi = yorumlarListesi;
        this.sonIsletmeci = sonIsletmeci;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                IsletmeciProfil tab1 = new IsletmeciProfil();
                tab1.setGelenIsletmeci(sonIsletmeci);
                return tab1;
            case 1:
                IsletmeciYorumlar tab2 = new IsletmeciYorumlar();
                tab2.setYorumlarListesi(yorumlarListesi);
                tab2.setIsletmeci(sonIsletmeci);
                return tab2;
            default:
                return null;
        }
    }




    @Override
    public int getCount() {
        return boyut;
    }
}
