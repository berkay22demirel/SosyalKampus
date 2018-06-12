package com.berkay22demirel.sosyalkamps.Fragment;

import com.berkay22demirel.sosyalkamps.Adapter.ListProfilPaylasimlarAdapter;
import com.berkay22demirel.sosyalkamps.Adapter.ListZamanTuneliPaylasimlarAdapter;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Paylasim;
import com.berkay22demirel.sosyalkamps.PaylasimEkleActivity;
import com.berkay22demirel.sosyalkamps.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by BerkayDemirel on 01.06.2018.
 */

public class ZamanTuneli extends Fragment{
    FloatingActionButton floatingActionButton;

    ViewPager viewPager;
    Kullanici profil;
    ListView listViewPaylasimlar;
    List<Paylasim> paylasimlarListesi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zamantuneli,container,false);

        viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_button_yapacagim);
        listViewPaylasimlar = (ListView) view.findViewById(R.id.listViewZamanTuneli);

        ListZamanTuneliPaylasimlarAdapter adapter = new ListZamanTuneliPaylasimlarAdapter(getContext(),R.layout.list_item_zaman_tuneli_paylasimlar,paylasimlarListesi);
        listViewPaylasimlar.setAdapter(adapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PaylasimEkleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("profil",profil);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    public void setPaylasimlarListesi(List<Paylasim> paylasimlarListesi) {
        this.paylasimlarListesi = paylasimlarListesi;
    }

}
