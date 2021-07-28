package com.example.notes_krah;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends Fragment {

        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_settings, container, false);

            return view;
        }


    }