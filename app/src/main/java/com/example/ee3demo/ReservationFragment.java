package com.example.ee3demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class ReservationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        // 将在后续实现具体的预约页面布局
        return inflater.inflate(R.layout.fragment_reservation, container, false);
    }
} 