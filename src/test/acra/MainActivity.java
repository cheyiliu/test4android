
package test.acra;

import com.test4android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    static {
        System.loadLibrary("test-acra-native-panic");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_acra);
        OnClickListener l = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "to panic...");
                if (v.getId() == R.id.btn_java_panic) {
                    Object nullObject = null;
                    nullObject.toString();
                } else if (v.getId() == R.id.btn_native_direct_panic) {
                    nativeDirectPanic();
                } else if (v.getId() == R.id.btn_native_throw_exp) {
                    nativeThrowExp();
                }
            }
        };
        findViewById(R.id.btn_java_panic).setOnClickListener(l);
        findViewById(R.id.btn_native_direct_panic).setOnClickListener(l);
        findViewById(R.id.btn_native_throw_exp).setOnClickListener(l);
    }

    public native void nativeThrowExp();

    public native void nativeDirectPanic();
}
