package com.example.akapil.myapplication.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.akapil.myapplication.R;

/**
 * Created by akapil on 3/16/2017.
 */

public class FourthFragment extends Fragment {

    private WebView webView;

    public FourthFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_four, container, false);
        webView = (WebView) rootView.findViewById(R.id.web_container);
        webView.loadUrl("https://www.google.co.in/");
        return rootView;
    }
}
