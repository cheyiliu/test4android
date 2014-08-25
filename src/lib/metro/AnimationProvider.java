
package lib.metro;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AnimationProvider {
    private static String TAG = "AnimationProvider";
    private static long DURATION = 10000;
    private static float SCALE_RATIO = 1.1f;
    private static int ShadowBiggerThanRealFocusInPixel = 60;

    private static float mPreScaleX = 1f;
    private static float mPreScaleY = 1f;

    public static void applyAnimation(final boolean hasFocus,
            final View realFocusView, final ImageView globalFocusView,
            final ImageView globalFloatView, final ImageView globalShadowView) {
        if (hasFocus) {
            globalShadowView.setVisibility(View.INVISIBLE);
            globalFocusView.setVisibility(View.INVISIBLE);// TODO to test, do
                                                          // NOT invisible
                                                          // will cause
                                                          // problem, but WHY?

            /**
             * 1. move and scale the globalFocusView (visible when animation is
             * done )2. move and scale the globalFloatView (visible during
             * animation ) 3. when 2 is done move and scale the globalShadowView
             * (visible when animation is done) 4. keep the z order
             */
            final ViewGroup realFocusParent = (ViewGroup) realFocusView.getParent();
            int x = (realFocusView.getLeft() + getLeft(realFocusParent));

            final int globalFocusTargetX = x;
            final int globalFocusTargetY = realFocusView.getTop() + getTop(realFocusParent);
            final int globalFloatTargetX = (int) (globalFocusTargetX - realFocusView.getWidth()
                    * (SCALE_RATIO - 1) / 2);
            final int globalFloatTargetY = (int) (globalFocusTargetY - realFocusView.getHeight()
                    * (SCALE_RATIO - 1) / 2);
            final int globalShadowTargetX = globalFloatTargetX - ShadowBiggerThanRealFocusInPixel
                    / 2;
            final int globalShadowTargetY = globalFloatTargetY - ShadowBiggerThanRealFocusInPixel
                    / 2;
            Log.i(TAG, "globalFocusTargetX=" + globalFocusTargetX);
            Log.i(TAG, "globalFocusTargetY=" + globalFocusTargetY);
            Log.i(TAG, "globalFloatTargetX=" + globalFloatTargetX);
            Log.i(TAG, "globalFloatTargetY=" + globalFloatTargetY);
            Log.i(TAG, "globalShadowTargetX=" + globalShadowTargetX);
            Log.i(TAG, "globalShadowTargetY=" + globalShadowTargetY);

            /**
             * 1. move and scale the globalFocusView (visible when animation is
             * done )
             */
            RelativeLayout.LayoutParams globalFocusLayout = new RelativeLayout.LayoutParams(
                    realFocusView.getWidth(), realFocusView.getHeight());
            globalFocusLayout.leftMargin = globalFocusTargetX;
            globalFocusLayout.topMargin = globalFocusTargetY;

            globalFocusView.setLayoutParams(globalFocusLayout);
            globalFocusView.setVisibility(View.VISIBLE);
            globalFocusView.bringToFront();
            globalFocusView.setImageBitmap(convertViewToBitmap(realFocusView));
            ObjectAnimator scale_globalFocusView_X = ObjectAnimator.ofFloat(globalFocusView,
                    "scaleX", 1.0f, SCALE_RATIO);
            ObjectAnimator scale_globalFocusView_Y = ObjectAnimator.ofFloat(globalFocusView,
                    "scaleY", 1.0f, SCALE_RATIO);
            AnimatorSet scale_globalFocusView = new AnimatorSet();
            scale_globalFocusView.setInterpolator(new AccelerateInterpolator());
            scale_globalFocusView.play(scale_globalFocusView_X).with(scale_globalFocusView_Y);
            scale_globalFocusView.setDuration(DURATION);
            scale_globalFocusView.start();

            /**
             * 2. move and scale the globalFloatView (visible during animation )
             */
            globalFloatView.setVisibility(View.VISIBLE);
            globalFloatView.bringToFront();
            globalFloatView.setPivotX(0);
            globalFloatView.setPivotY(0);

            float globalFloatPreX = globalFloatView.getX();
            float globalFloatPreY = globalFloatView.getY();
            float scale_globalFloatView_X_ratio = realFocusView.getWidth() * SCALE_RATIO
                    / globalFloatView.getWidth();
            float scale_globalFloatView_Y_ratio = realFocusView.getHeight() * SCALE_RATIO
                    / globalFloatView.getHeight();
            ObjectAnimator trans_globalFloatView_X = ObjectAnimator.ofFloat(globalFloatView, "x",
                    globalFloatPreX, globalFloatTargetX);
            ObjectAnimator trans_globalFloatView_Y = ObjectAnimator.ofFloat(globalFloatView, "y",
                    globalFloatPreY, globalFloatTargetY);
            ObjectAnimator scale_globalFloatView_X = ObjectAnimator.ofFloat(globalFloatView,
                    "scaleX", mPreScaleX, scale_globalFloatView_X_ratio);
            ObjectAnimator scale_globalFloatView_Y = ObjectAnimator.ofFloat(globalFloatView,
                    "scaleY", mPreScaleY, scale_globalFloatView_Y_ratio);

            mPreScaleX = scale_globalFloatView_X_ratio;
            mPreScaleY = scale_globalFloatView_Y_ratio;

            AnimatorSet scaleTransGLobalFloatView = new AnimatorSet();
            scaleTransGLobalFloatView.setInterpolator(new AccelerateInterpolator());
            scaleTransGLobalFloatView.play(trans_globalFloatView_X).with(trans_globalFloatView_Y)
                    .with(scale_globalFloatView_X).with(scale_globalFloatView_Y);
            scaleTransGLobalFloatView.setDuration(DURATION);
            scaleTransGLobalFloatView.start();
            scaleTransGLobalFloatView.addListener(new AnimatorListener() {

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

                    RelativeLayout.LayoutParams globalShadowLayout = new RelativeLayout.LayoutParams(
                            shadowWith, shadowHeight);
                    globalShadowLayout.leftMargin = globalShadowTargetX;
                    globalShadowLayout.topMargin = globalShadowTargetY;

                    globalShadowView.setLayoutParams(globalShadowLayout);
                    globalShadowView.setVisibility(View.VISIBLE);

                    /**
                     * 4. keep the z order
                     */
                    globalShadowView.bringToFront();
                    globalFocusView.bringToFront();
                    globalFloatView.bringToFront();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });
        } else {
            // just hide the three
            globalFloatView.setVisibility(View.INVISIBLE);
            globalShadowView.setVisibility(View.INVISIBLE);
            globalFocusView.setVisibility(View.INVISIBLE);
        }
    }

    private static Bitmap convertViewToBitmap(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    private static int getLeft(ViewGroup viewGroup) {
        return 0;
    }

    private static int getTop(ViewGroup viewGroup) {
        return 0;
    }

    public static void reset() {
        mPreScaleX = 1f;
        mPreScaleY = 1f;
    }
}
