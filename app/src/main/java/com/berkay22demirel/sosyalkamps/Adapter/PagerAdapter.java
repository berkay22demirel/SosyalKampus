package com.berkay22demirel.sosyalkamps.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.berkay22demirel.sosyalkamps.Fragment.EtkinlikVeMekanlar;
import com.berkay22demirel.sosyalkamps.Fragment.Profil;
import com.berkay22demirel.sosyalkamps.Fragment.ZamanTuneli;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Paylasim;

import java.util.List;

/**
 * Created by BerkayDemirel on 01.06.2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int boyut;
    FragmentManager fm;
    Kullanici gelenKullanici;
    List<Paylasim> gelenProfilPaylasimListesi;
    List<Paylasim> gelenPaylasimlarListesi;
    List<Isletmeci> etkinlikVeMekanlarListesi;
    String eposta;

    public PagerAdapter(FragmentManager fm, int boyut, Kullanici gelenKullanici,List<Paylasim> gelenProfilPaylasimListesi,List<Paylasim> gelenPaylasimlarListesi,List<Isletmeci> etkinlikVeMekanlarListesi,String eposta) {

        super(fm);
        this.fm = fm;
        this.boyut = boyut;
        this.gelenKullanici = gelenKullanici;
        this.gelenProfilPaylasimListesi = gelenProfilPaylasimListesi;
        this.gelenPaylasimlarListesi = gelenPaylasimlarListesi;
        this.etkinlikVeMekanlarListesi = etkinlikVeMekanlarListesi;
        this.eposta = eposta;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ZamanTuneli tab1 = new ZamanTuneli();
                tab1.setPaylasimlarListesi(gelenPaylasimlarListesi);
                return tab1;
            case 1:
                Profil tab2 = new Profil();
                tab2.setProfil(gelenKullanici);
                tab2.setPaylasimListesi(gelenProfilPaylasimListesi);
                return tab2;
            case 2:
                EtkinlikVeMekanlar tab3 = new EtkinlikVeMekanlar();
                tab3.setEtkinlikVeMekanlarListesi(etkinlikVeMekanlarListesi);
                tab3.setEposta(eposta);
                return tab3;
            default:
                return null;
        }
    }




    @Override
    public int getCount() {
        return boyut;
    }

}
