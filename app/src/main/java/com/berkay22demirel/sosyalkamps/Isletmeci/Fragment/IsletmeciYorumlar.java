package com.berkay22demirel.sosyalkamps.Isletmeci.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.berkay22demirel.sosyalkamps.Adapter.ListEtkinlikVeyaMekanlarYorumlarAdapter;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateYorum;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.R;
import com.berkay22demirel.sosyalkamps.Yorum;

import java.util.List;

/**
 * Created by BerkayDemirel on 06.06.2018.
 */

public class IsletmeciYorumlar extends Fragment {

    ViewPager viewPager;
    ListView listViewYorumlar;

    List<Yorum> yorumlarListesi;
    Isletmeci isletmeci;

    ListEtkinlikVeyaMekanlarYorumlarAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_isletmeci_yorumlar,container,false);

        viewPager = (ViewPager) getActivity().findViewById(R.id.pager_isletmeci);
        listViewYorumlar = (ListView) view.findViewById(R.id.listViewIsletmeciYorumlar);

        adapter = new ListEtkinlikVeyaMekanlarYorumlarAdapter(getContext(),R.layout.list_item_yorumlar,yorumlarListesi);
        listViewYorumlar.setAdapter(adapter);

        return view;
    }

    public void setYorumlarListesi(List<Yorum> yorumlarListesi) {
        this.yorumlarListesi = yorumlarListesi;
    }

    public void setIsletmeci(Isletmeci isletmeci) {
        this.isletmeci = isletmeci;
    }
}
