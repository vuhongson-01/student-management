package com.example.studentsmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class CreateScreen extends Fragment {
    View viewscreen;
    ImageButton backBtn;
    MainActivity main;
    Button createBtn;
    DateFormat dateFormat;
    DBHelper db;
    public CreateScreen(MainActivity main){
        super();
        this.main = main;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         dateFormat = dateFormat = new SimpleDateFormat("yyyy/MM/dd");
         viewscreen = inflater.inflate(R.layout.add_new_data, container, false);
         backBtn = viewscreen.findViewById(R.id.back_btn);
         backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.back();
            }
         });

         createBtn = viewscreen.findViewById(R.id.create_btn);
         createBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 db = new DBHelper(getContext());

                 String mssv = ((EditText)viewscreen.findViewById(R.id.add_mssv_field)).getText().toString();
                 String ho_ten = ((EditText)viewscreen.findViewById(R.id.add_ho_ten_field)).getText().toString();
                 String email = ((EditText)viewscreen.findViewById(R.id.add_email_field)).getText().toString();
                 String ngay_sinh = dateFormat.format(((CalendarView)viewscreen.findViewById(R.id.add_ngay_sinh_field)).getDate());

                 if (!check(mssv) || !check(ngay_sinh) || !check(ho_ten) || !check(email)){
                     Toast toast = Toast.makeText(getContext(), "all of attribute must be filled", Toast.LENGTH_SHORT);
                     toast.show();
                 }
                 else {
                     insertDB(db, mssv, ho_ten, email, ngay_sinh);
                 }

             }
         });
        return viewscreen;
    }

    public boolean check(String data){
        if (data.length() == 0) return false;
        return true;
    }

    private void insertDB(DBHelper db, String id, String ho_ten, String email, String ngay_sinh){
        boolean check = db.insertData( id, ho_ten, email, ngay_sinh);
        if (check) {
            Toast toast = Toast.makeText(getContext(), "create done", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getContext(), "create fail", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
