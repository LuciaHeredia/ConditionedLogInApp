package com.example.loginapp.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.loginapp.R;

public class Success extends Fragment {

    public Success() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_success, container, false);

        Button bExit = v.findViewById(R.id.button_exit);

        bExit.setOnClickListener(v1 -> {
            requireActivity().finish(); // exit app
        });

        return v;
    }
}