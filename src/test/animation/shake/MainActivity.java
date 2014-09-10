
package test.animation.shake;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.test4android.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_animation_sharek_activity_main);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_shake);
        TextView editText = (TextView) findViewById(R.id.tv1);
        editText.startAnimation(animation);
    }
}
