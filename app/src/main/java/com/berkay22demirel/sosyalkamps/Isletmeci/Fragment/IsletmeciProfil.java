package com.berkay22demirel.sosyalkamps.Isletmeci.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.R;

/**
 * Created by BerkayDemirel on 06.06.2018.
 */

public class IsletmeciProfil extends Fragment {
    ViewPager viewPager;
    TextView textViewMekanAdi;
    TextView textViewMekanAdresi;
    TextView textViewMekanTelefonu;

    Isletmeci gelenIsletmeci;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_isletmeci_profil,container,false);

        viewPager = (ViewPager) getActivity().findViewById(R.id.pager_isletmeci);
        textViewMekanAdi = (TextView) view.findViewById(R.id.textViewIsletmeciMekanAdi);
        textViewMekanAdresi = (TextView) view.findViewById(R.id.textViewIsletmeciMekanAdresi);
        textViewMekanTelefonu = (TextView) view.findViewById(R.id.textViewIsletmeciMekanTelefonu);

        textViewMekanAdi.setText(gelenIsletmeci.getMekanAdi());
        textViewMekanAdresi.setText(gelenIsletmeci.getMekanAdres());
        textViewMekanTelefonu.setText(gelenIsletmeci.getMekanTelefon());


        return view;
    }

    public void setGelenIsletmeci(Isletmeci gelenIsletmeci) {
        this.gelenIsletmeci = gelenIsletmeci;
    }
}
