
package test.marqueeText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.test4android.R;

public class MainActivity extends Activity {
    CoolMarqueeText mCoolMarqueeText;
    Button mButton;
    boolean mIsMarquee = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_marqueetext_activity_main);
        mCoolMarqueeText = (CoolMarqueeText) findViewById(R.id.textView1);
        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mIsMarquee) {
                    mCoolMarqueeText.startMarquee();
                } else {
                    mCoolMarqueeText.stopMarquee();
                }

                mIsMarquee = !mIsMarquee;
            }
        });
    }

}
