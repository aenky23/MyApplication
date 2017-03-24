package com.example.akapil.myapplication.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.akapil.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by akapil on 3/16/2017.
 */

public class ThreeFragment extends Fragment implements View.OnClickListener {

    TextView clock;
    Button startBtn, pauseBtn, resetBtn, saveLapBtn;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds;

    ArrayAdapter<String> adapter;
    ListView lvLaps;
    List<String> ListElementsArrayList;
    String[] ListElements = new String[]{};

    Handler handler;

    public ThreeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_three, container, false);

        handler = new Handler();
        startBtn = (Button) rootView.findViewById(R.id.btn_start);
        pauseBtn = (Button) rootView.findViewById(R.id.btn_pause);
        resetBtn = (Button) rootView.findViewById(R.id.btn_reset);
        saveLapBtn = (Button) rootView.findViewById(R.id.btn_save_lap);
        clock = (TextView) rootView.findViewById(R.id.tv_clock);
        lvLaps = (ListView) rootView.findViewById(R.id.lv_laps);

        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, ListElementsArrayList);


        startBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        saveLapBtn.setOnClickListener(this);

        lvLaps.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                resetBtn.setEnabled(false);
                break;
            case R.id.btn_pause:

                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                resetBtn.setEnabled(true);

                break;
            case R.id.btn_reset:
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                MilliSeconds = 0;

                ListElementsArrayList.clear();
                adapter.notifyDataSetChanged();
                clock.setText("00:00:00");


                break;
            case R.id.btn_save_lap:

                ListElementsArrayList.add(clock.getText().toString());

                adapter.notifyDataSetChanged();

                break;
        }
    }


    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            clock.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }
    };
}