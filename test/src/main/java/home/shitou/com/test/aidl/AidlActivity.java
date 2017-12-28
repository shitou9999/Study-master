package home.shitou.com.test.aidl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import home.shitou.com.test.R;

public class AidlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);


        //启动一个服务等待A应用过来连接
        startService(new Intent(this,MessageService.class));


    }





}
