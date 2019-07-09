package com.example.dell.kbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by dell on 5/6/2018.
 */

public class TabThree extends Fragment {

    private Button Pointers;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_three,container,false);

        Pointers = (Button)view.findViewById(R.id.btnQuizPointers);

        Pointers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),QuizActivity.class));
            }
        });

        return view;
    }
}
