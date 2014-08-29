
package lib.metro.enhanced;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class AnimationProvider {
    private static String TAG = "AnimationProvider";
    private long DURATION = 100;
    private float SCALE_RATIO = 1.1f;
    private int ShadowBiggerThanRealFocusInPixel = 20;

    private float mPreScaleX = 1f;
    private float mPreScaleY = 1f;

    public void applyAnimation(final boolean hasFocus,
            final View realFocusView,final View realFocusViewContariner,
            final ImageView focusBorder, final ImageView focusShadow) {
        if (hasFocus) {
            focusShadow.setVisibility(View.INVISIBLE);
//            focusCenter.setVisibility(View.INVISIBLE);

            /**
             * 1. move and scale the globalFocusView (visible when animation is
             * done )2. move and scale the globalFloatView (visible during
             * animation ) 3. when 2 is done move and scale the globalShadowView
             * (visible when animation is done) 4. keep the z order
             */
            final ViewGroup realFocusParent = (ViewGroup) realFocusView.getParent();
            int x = (int) (realFocusView.getX()+ getLeft(realFocusParent));
            Log.i(TAG, "----------------------------------------------------x=" + x);
            final int focusCenterTargetX = x;
            final int focusCenterTargetY = realFocusView.getTop() + getTop(realFocusParent);
            final int focusBorderTargetX = (int) (focusCenterTargetX - realFocusView.getWidth()
                    * (SCALE_RATIO - 1) / 2);
            final int focusBorderTargetY = (int) (focusCenterTargetY - realFocusView.getHeight()
                    * (SCALE_RATIO - 1) / 2);
            final int focusShadowTargetX = focusBorderTargetX - ShadowBiggerThanRealFocusInPixel
                    / 2;
            final int focusShadowTargetY = focusBorderTargetY - ShadowBiggerThanRealFocusInPixel
                    / 2;
            Log.i(TAG, "focusCenterTargetX=" + focusCenterTargetX);
            Log.i(TAG, "focusCenterTargetY=" + focusCenterTargetY);
            Log.i(TAG, "focusBorderTargetX=" + focusBorderTargetX);
            Log.i(TAG, "focusBorderTargetY=" + focusBorderTargetY);
            Log.i(TAG, "focusShadowTargetX=" + focusShadowTargetX);
            Log.i(TAG, "focusShadowTargetY=" + focusShadowTargetY);

            /**
             * 1. move and scale the focusCenter (visible when animation is done
             * )
             */
//            RelativeLayout.LayoutParams focusCenterLayout = new RelativeLayout.LayoutParams(
//                    realFocusView.getWidth(), realFocusView.getHeight());
//            focusCenterLayout.leftMargin = focusCenterTargetX;
//            focusCenterLayout.topMargin = focusCenterTargetY;
//
//            focusCenter.setLayoutParams(focusCenterLayout);
//            focusCenter.setVisibility(View.VISIBLE);
//            focusCenter.bringToFront();
//            focusCenter.setImageBitmap(convertViewToBitmap(realFocusView));
//            ObjectAnimator scale_focusCenterView_X = ObjectAnimator.ofFloat(focusCenter,
//                    "scaleX", 1.0f, SCALE_RATIO);
//            ObjectAnimator scale_focusCenterView_Y = ObjectAnimator.ofFloat(focusCenter,
//                    "scaleY", 1.0f, SCALE_RATIO);
//            AnimatorSet scale_focusCenterView = new AnimatorSet();
//            scale_focusCenterView.setInterpolator(new AccelerateInterpolator());
//            scale_focusCenterView.play(scale_focusCenterView_X).with(scale_focusCenterView_Y);
//            scale_focusCenterView.setDuration(DURATION);
//            scale_focusCenterView.start();

            /**
             * 2. move and scale the focusBorder (visible during animation )
             */
            focusBorder.setVisibility(View.VISIBLE);
            focusBorder.bringToFront();
            focusBorder.setPivotX(0);
            focusBorder.setPivotY(0);

            float focusBorderPreX = focusBorder.getX();
            float focusBorderPreY = focusBorder.getY();
            float scale_focusBorderView_X_ratio = realFocusView.getWidth() * SCALE_RATIO
                    / focusBorder.getWidth();
            float scale_focusBorderView_Y_ratio = realFocusView.getHeight() * SCALE_RATIO
                    / focusBorder.getHeight();
            ObjectAnimator trans_focusBorderView_X = ObjectAnimator.ofFloat(focusBorder, "x",
                    focusBorderPreX, focusBorderTargetX);
            ObjectAnimator trans_focusBorderView_Y = ObjectAnimator.ofFloat(focusBorder, "y",
                    focusBorderPreY, focusBorderTargetY);
            ObjectAnimator scale_focusBorderView_X = ObjectAnimator.ofFloat(focusBorder,
                    "scaleX", mPreScaleX, scale_focusBorderView_X_ratio);
            ObjectAnimator scale_focusBorderView_Y = ObjectAnimator.ofFloat(focusBorder,
                    "scaleY", mPreScaleY, scale_focusBorderView_Y_ratio);

            mPreScaleX = scale_focusBorderView_X_ratio;
            mPreScaleY = scale_focusBorderView_Y_ratio;

            AnimatorSet scaleTransFocusBorderView = new AnimatorSet();
            scaleTransFocusBorderView.setInterpolator(new AccelerateInterpolator());
            scaleTransFocusBorderView.play(trans_focusBorderView_X).with(trans_focusBorderView_Y)
                    .with(scale_focusBorderView_X).with(scale_focusBorderView_Y);
            scaleTransFocusBorderView.setDuration(DURATION);
            scaleTransFocusBorderView.start();
            scaleTransFocusBorderView.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    /**
                     * 3. when 2 is done move and scale the globalShadowView
                     * (visible when animation is done)
                     */
                    int shadowWith = (int) (realFocusView.getWidth() * SCALE_RATIO)
                            + ShadowBiggerThanRealFocusInPixel;
                    int shadowHeight = (int) (realFocusView.getHeight() * SCALE_RATIO)
                            + ShadowBiggerThanRealFocusInPixel;

                    FrameLayout.LayoutParams focusShadowayout = new FrameLayout.LayoutParams(
                            shadowWith, shadowHeight);
                    focusShadowayout.leftMargin = focusShadowTargetX;
                    focusShadowayout.topMargin = focusShadowTargetY;

                    focusShadow.setLayoutParams(focusShadowayout);
                    focusShadow.setVisibility(View.VISIBLE);

                    /**
                     * 4. keep the z order
                     */
                    focusShadow.bringToFront();
//                    focusCenter.bringToFront();
                    focusBorder.bringToFront();
                    realFocusViewContariner.bringToFront();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });
        } else {
            // just hide the three
            focusBorder.setVisibility(View.INVISIBLE);
            focusShadow.setVisibility(View.INVISIBLE);
//            focusCenter.setVisibility(View.INVISIBLE);
        }
    }

    private Bitmap convertViewToBitmap(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    private static int getLeft(ViewGroup viewGroup) {
        if (viewGroup == null || !(viewGroup.getParent() instanceof ViewGroup)
                || viewGroup.getParent() instanceof ViewPager) {
            return 0;
        } else {
            return viewGroup.getLeft() + getLeft((ViewGroup) viewGroup.getParent());
        }
    }

    private static int getTop(ViewGroup viewGroup) {
        if (viewGroup == null || !(viewGroup.getParent() instanceof ViewGroup)
                || viewGroup.getParent() instanceof ViewPager) {
            return 0;
        } else {
            return viewGroup.getTop() + getTop((ViewGroup) viewGroup.getParent());
        }
    }

    public void reset() {
        mPreScaleX = 1f;
        mPreScaleY = 1f;
    }
}
