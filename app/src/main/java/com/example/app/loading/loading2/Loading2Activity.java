package com.example.app.loading.loading2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.app.loading.R;

public class Loading2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading2);
        CustomDialog customDialog= new CustomDialog(this,R.style.CustomDialog2);
        customDialog.show();
    }
}
