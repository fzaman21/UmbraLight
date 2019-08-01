package com.example.umbralight;
//10116050
//Fikih Zaman
//AKB-2
//07-07-2019
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MenuDictionaryActivity extends Fragment {
    public MenuDictionaryActivity(){}
    RelativeLayout view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.activity_menu_dictionary, container, false);

        getActivity().setTitle("Dictionary");

        return view;
    }
}
