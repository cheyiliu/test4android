
package test.event.onkey;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;

public class MainActivity extends Activity {
    Timer timer = new Timer();
    TimerTask timerTask;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("test", "onKeyDown, keycode=" + keyCode + ", event=" + event.getAction()
                + ", repeat count="
                + event.getRepeatCount());
        if (timerTask == null) {
            timerTask = new TimerTask() {

                @Override
                public void run() {
                    Log.e("test", "my event");
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 800);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i("test", "onKeyUp, keycode=" + keyCode + ", event=" + event.getAction()
                + ", repeat count="
                + event.getRepeatCount());
        if (timerTask != null) {
            Log.e("test", "my event ends");
            timerTask.cancel();
            timerTask = null;
        }
        return super.onKeyUp(keyCode, event);
    }

}
