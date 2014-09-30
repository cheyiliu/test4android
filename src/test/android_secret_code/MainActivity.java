
package test.android_secret_code;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
// from http://blog.csdn.net/andevil/article/details/39500609
// 这个类仅仅用于提示用户有这个功能

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("请在拨号键盘输入*#*#0123#*#*, 你会有发现哦～");
        tv.setTextSize(30);
        setContentView(tv);
    }

}
