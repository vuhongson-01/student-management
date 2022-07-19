package com.example.studentsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Stack<Fragment> stackScreen = new Stack<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openMainScreen();
    }

    public void openMainScreen(){
        stackScreen.push(new MainScreen(this));
        replaceFragment(stackScreen.peek());
    }

    public void openInfoScreen(){

    }
    public void openUpdateScreen(){

    }

    public void openCreateData(){
        stackScreen.push(new CreateScreen(this));
        replaceFragment(stackScreen.peek());
    }
    public void back(){
        stackScreen.pop();
        replaceFragment(stackScreen.peek());
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.screen_frame, fragment);
        transaction.commit();
    }
}