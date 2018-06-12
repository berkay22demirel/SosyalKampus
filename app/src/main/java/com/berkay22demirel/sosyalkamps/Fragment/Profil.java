package com.berkay22demirel.sosyalkamps.Fragment;

import com.berkay22demirel.sosyalkamps.Adapter.ListProfilPaylasimlarAdapter;
import com.berkay22demirel.sosyalkamps.ArkadasListesiActivity;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Paylasim;
import com.berkay22demirel.sosyalkamps.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BerkayDemirel on 01.06.2018.
 */

public class Profil extends Fragment {
    ViewPager viewPager;
    Kullanici profil;

    List<Paylasim> paylasimListesi;

    ListProfilPaylasimlarAdapter adapter;

    TextView textViewProfilAdSoyad;
    TextView textViewProfilBio;
    TextView textViewPaylasimlar;
    ImageView imageView;
    ImageView imageViewArkadasListesi;
    ListView listViewPaylasimlar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil,container,false);

        viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

        textViewProfilAdSoyad = (TextView) view.findViewById(R.id.textViewProfilAdSoyad);
        textViewProfilBio = (TextView) view.findViewById(R.id.textViewProfilBio);
        textViewPaylasimlar = (TextView) view.findViewById(R.id.textViewProfilGonderiler);
        imageView = (ImageView) view.findViewById(R.id.imageViewProfil);
        imageViewArkadasListesi = (ImageView) view.findViewById(R.id.imageViewProfilArkadasListesi);
        listViewPaylasimlar = (ListView) view.findViewById(R.id.listViewProfilPaylasimlar);

        if(profil != null){
            textViewProfilAdSoyad.setText(profil.getAd() + " " + profil.getSoyad());
            textViewProfilBio.setText(profil.getBio());
        }

        imageViewArkadasListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ArkadasListesiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("profil",profil);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter = new ListProfilPaylasimlarAdapter(getContext(),R.layout.list_item_profil_paylasimlar,paylasimListesi);
        listViewPaylasimlar.setAdapter(adapter);

        textViewPaylasimlar.setText(paylasimListesi.size() + " Paylaşım");

        return view;
    }

    public void setProfil(Kullanici profil) {
        this.profil = profil;
    }

    public void setPaylasimListesi(List<Paylasim> paylasimListesi) {
        this.paylasimListesi = paylasimListesi;
    }

}
