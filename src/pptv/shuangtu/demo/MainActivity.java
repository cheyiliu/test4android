
package pptv.shuangtu.demo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;

import com.test4android.R;

public class MainActivity extends Activity implements OnFocusChangeListener {

    AnimatorSet mAnimatorSetZoomIn;
    AnimatorSet mAnimatorSetZoomOut;
    ObjectAnimator mObjectAnimatorTransX;
    ObjectAnimator mObjectAnimatorTransX_reset;
    ObjectAnimator mObjectAnimatorTransY;
    ObjectAnimator mObjectAnimatorTransY_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_pptv_shuangtu_demo);

        findViewById(R.id.whole_container).setOnFocusChangeListener(this);
        findViewById(R.id.whole_container1).setOnFocusChangeListener(this);

        mAnimatorSetZoomIn = (AnimatorSet) AnimatorInflater.loadAnimator(
                getApplicationContext(), R.animator.tv_zoom_in);
        mAnimatorSetZoomOut = (AnimatorSet) AnimatorInflater.loadAnimator(
                getApplicationContext(), R.animator.tv_zoom_out);

        mObjectAnimatorTransX = (ObjectAnimator) AnimatorInflater.loadAnimator(
                getApplicationContext(), R.animator.home_translate_horizontal);
        mObjectAnimatorTransX_reset = (ObjectAnimator) AnimatorInflater.loadAnimator(
                getApplicationContext(), R.animator.home_translate_horizontal_reset);
        mObjectAnimatorTransY = (ObjectAnimator) AnimatorInflater.loadAnimator(
                getApplicationContext(), R.animator.home_translate_vertical);
        mObjectAnimatorTransY_reset = (ObjectAnimator) AnimatorInflater.loadAnimator(
                getApplicationContext(), R.animator.home_translate_vertical_reset);
    }

    @Override
    public void onFocusChange(View v, final boolean hasFocus) {
        if (v.getId() == R.id.whole_container || v.getId() == R.id.whole_container1) {
            final View outter = v.findViewById(R.id.outter_image);
            final View inner = v.findViewById(R.id.inner_image);
            final View text = v.findViewById(R.id.inner_txt_container);

            final ObjectAnimator trans_outter_X;
            final ObjectAnimator trans_inner_Y;
            final AnimatorSet animatSet_scale_container;
            if (hasFocus) {
                // outter move to left; inner move to upper
                trans_outter_X = mObjectAnimatorTransX;
                trans_inner_Y = mObjectAnimatorTransY;
                animatSet_scale_container = mAnimatorSetZoomIn;
            } else {
                // restore to origin position(outter move to right; inner move
                // to down)
                trans_outter_X = mObjectAnimatorTransX_reset;
                trans_inner_Y = mObjectAnimatorTransY_reset;
                animatSet_scale_container = mAnimatorSetZoomOut;
            }
            trans_outter_X.setTarget(outter);
            trans_outter_X.start();

            trans_inner_Y.setTarget(inner);
            trans_inner_Y.start();

            animatSet_scale_container.setTarget(v);
            animatSet_scale_container.start();

            if (hasFocus) {
                text.setVisibility(View.VISIBLE);
            } else {
                text.setVisibility(View.INVISIBLE);
            }
        }
    }

}
