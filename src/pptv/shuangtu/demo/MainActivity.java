
package pptv.shuangtu.demo;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.test4android.R;

public class MainActivity extends Activity implements OnFocusChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_pptv_shuangtu_demo);
        findViewById(R.id.whole_container).setOnFocusChangeListener(this);
        findViewById(R.id.whole_container1).setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, final boolean hasFocus) {
        if (v.getId() == R.id.whole_container || v.getId() == R.id.whole_container1) {
            final View mOutter = v.findViewById(R.id.outter_image);
            final View mInner = v.findViewById(R.id.inner_image);
            final View mTxt = v.findViewById(R.id.inner_txt_container);

            final long duration = 500;

            final float outter_x = mOutter.getX();
            final float delt_x = 30;
            final float inner_y = mInner.getY();
            final float delt_y = 20;

            final float to_x;
            final float to_y;
            final float from_ratio;
            final float to_ratio;
            if (hasFocus) {
                // outter move to left; inner move to upper
                to_x = outter_x - delt_x;
                to_y = inner_y - delt_y;
                from_ratio = 1.0f;
                to_ratio = 1.1f;
            } else {
                // restore to origin position(outter move to right; inner move
                // to down)
                to_x = outter_x + delt_x;
                to_y = inner_y + delt_y;
                from_ratio = 1.1f;
                to_ratio = 1.0f;
            }

            ObjectAnimator trans_outter_X = ObjectAnimator.ofFloat(mOutter, "x", outter_x, to_x);
            trans_outter_X.setDuration(duration);

            ObjectAnimator trans_inner_Y = ObjectAnimator.ofFloat(mInner, "y", inner_y, to_y);
            trans_inner_Y.setDuration(duration);

            ObjectAnimator scale_container_X = ObjectAnimator.ofFloat(v, "scaleX", from_ratio,
                    to_ratio);
            scale_container_X.setDuration(0);
            ObjectAnimator scale_container_Y = ObjectAnimator.ofFloat(v, "scaleY", from_ratio,
                    to_ratio);
            scale_container_Y.setDuration(0);

            AnimatorSet animatSet_move_inner_outter = new AnimatorSet();
            animatSet_move_inner_outter.setInterpolator(new AccelerateDecelerateInterpolator());
            animatSet_move_inner_outter.play(trans_outter_X).with(trans_inner_Y);

            AnimatorSet animatSet_scale_container = new AnimatorSet();
            animatSet_scale_container.setInterpolator(new AccelerateDecelerateInterpolator());
            animatSet_scale_container.play(scale_container_X).with(scale_container_Y);
            animatSet_scale_container.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (hasFocus) {
                        mTxt.setVisibility(View.VISIBLE);
                    } else {
                        mTxt.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });

            animatSet_move_inner_outter.start();
            animatSet_scale_container.start();
        }
    }

}
