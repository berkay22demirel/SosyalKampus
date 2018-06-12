package com.berkay22demirel.sosyalkamps.Fragment;

import com.berkay22demirel.sosyalkamps.Adapter.ListEtkinlikVeMekanlarAdapter;
import com.berkay22demirel.sosyalkamps.Adapter.ListProfilPaylasimlarAdapter;
import com.berkay22demirel.sosyalkamps.EtkinlikVeyaMekanActivity;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by BerkayDemirel on 01.06.2018.
 */

public class EtkinlikVeMekanlar extends Fragment{

    ViewPager viewPager;
    ListView listViewEtkinlikVeMekanlar;

    List<Isletmeci> etkinlikVeMekanlarListesi;

    ListEtkinlikVeMekanlarAdapter adapter;
    String eposta;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_etkinlikvemekanlar,container,false);

        viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
        listViewEtkinlikVeMekanlar = (ListView) view.findViewById(R.id.listViewEtkinlikVeMekanlar);

        adapter = new ListEtkinlikVeMekanlarAdapter(getContext(),R.layout.list_item_etkinlik_ve_mekanlar,etkinlikVeMekanlarListesi);
        listViewEtkinlikVeMekanlar.setAdapter(adapter);

        listViewEtkinlikVeMekanlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), EtkinlikVeyaMekanActivity.class);
                Bundle bundle = new Bundle();
                Isletmeci m = etkinlikVeMekanlarListesi.get(position);
                bundle.putSerializable("isletmeci",m);
                bundle.putString("eposta",eposta);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    public void setEtkinlikVeMekanlarListesi(List<Isletmeci> etkinlikVeMekanlarListesi) {
        this.etkinlikVeMekanlarListesi = etkinlikVeMekanlarListesi;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }
}
