package com.example.dell.kbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class TabOne extends Fragment{

    Button OpenPdf;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.tab_one,container,false);

         OpenPdf = (Button)v.findViewById(R.id.btnPDF);

         OpenPdf.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getActivity(),C_ProgramsActivity.class));
             }
         });

        return v;

       // pdfView.fromAsset("Let-Us-C-Yashwant-Kanetkar-1").load();

    }

}
