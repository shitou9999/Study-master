package chatqq.home.com.app2.插件化.host;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import chatqq.home.com.app2.R;
import chatqq.home.com.app2.插件化.host.plugin.PluginManager;

/**
 * host
 */
public class MainActivity extends AppCompatActivity {

    private String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator
            + "plugin_test.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_host);


    }

    /**
     * 资源 乱掉了，插件换肤  插件之间、与Host之间实现了资源完全隔离，不会出现资源窜用的情况。
     * @param view
     */

    public void test(View view) {
        //启动插件
        Intent intent = new Intent();
        //        intent.setClass(this, TestActivity.class);
        //        intent.setClass(this,Class.forName("home.shitou.com.hostapp.TestActivity"));
//                intent.setClassName(getPackageName(),"home.shitou.com.hostapp.TestActivity");//插件中的class
        intent.setClassName(getPackageName(), "home.shitou.com.yaoyiyao.MainActivity");//插件中的class
        intent.putExtra("user_name", "myname");
        startActivity(intent);

    }

    //安装远程apk
    public void install(View view){
        PluginManager.install(this,apkPath);
    }


}
