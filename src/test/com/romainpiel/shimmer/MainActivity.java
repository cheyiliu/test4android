
package test.com.romainpiel.shimmer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.test4android.R;

public class MainActivity extends Activity {

    ShimmerTextView tv;
    ShimmerButton btn;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_matrix_shader_shimmer);

        tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        btn = (ShimmerButton) findViewById(R.id.shimmer_btn);
    }

    public void toggleAnimation(View target) {
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(btn);
            shimmer.start(tv);
        }
    }

    public void gotoTest2(View target) {
        // Intent intent = new Intent(this, Test2.class);
        // startActivity(intent);
    }
}
