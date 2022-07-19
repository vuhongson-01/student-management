package com.example.studentsmanagement;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MainScreen extends Fragment {

    MainActivity main;
    View view;
    ImageButton addButton;
    DBHelper db;
    public MainScreen(MainActivity mainActivity){
        super();
        this.main = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        db = new DBHelper(getContext());
        addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.openCreateData();
            }
        });

        displayData();
        return view;
    }

    public void displayData(){
        LinearLayout mssvCtn = view.findViewById(R.id.mssv_container);
        LinearLayout ho_tenCtn = view.findViewById(R.id.ho_ten_container);
        LinearLayout emailCtn = view.findViewById(R.id.email_container);
        LinearLayout ngay_sinhCtn = view.findViewById(R.id.ngay_sinh_container);
        mssvCtn.removeAllViews();
        ho_tenCtn.removeAllViews();
        emailCtn.removeAllViews();
        ngay_sinhCtn.removeAllViews();

        Cursor res = db.getData();

    }
}
