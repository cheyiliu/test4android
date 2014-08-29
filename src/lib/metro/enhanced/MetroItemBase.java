
package lib.metro.enhanced;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test4android.R;

public abstract class MetroItemBase extends RelativeLayout {
    private ImageView mImageViewOutter;
    private ImageView mImageViewInner;
    private ImageView mImageViewBg;
    private View mContainerWhole;
    private View mContainerBg;
    private View mContainerOutter;
    private View mContainerInner;
    private View mContainerTitle;
    private TextView mTextViewTitle;

    AnimatorSet mAnimatorSetZoomIn;// TODO too many objects, each cell has one
    AnimatorSet mAnimatorSetZoomOut;
    ObjectAnimator mObjectAnimatorTransX;
    ObjectAnimator mObjectAnimatorTransX_reset;
    ObjectAnimator mObjectAnimatorTransY;
    ObjectAnimator mObjectAnimatorTransY_reset;

    public MetroItemBase(Context context) {
        this(context, null, 0);
    }

    public MetroItemBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetroItemBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
        setFocusable(true);
        setId(R.id.whole_container);
        setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("test", "" + hasFocus + ", v=" + v);

                View parentView = (View) v.getParent();// 解决遮盖问题
//                parentView.requestLayout();
//                parentView.invalidate();
//                v.bringToFront();

                MetroView metroView = (MetroView) parentView;

                final ObjectAnimator trans_outter_X;
                final ObjectAnimator trans_inner_Y;
                final AnimatorSet animatSet_scale_container;
                if (hasFocus) {
                    trans_outter_X = mObjectAnimatorTransX;
                    trans_inner_Y = mObjectAnimatorTransY;
                    animatSet_scale_container = mAnimatorSetZoomIn;
                } else {
                    trans_outter_X = mObjectAnimatorTransX_reset;
                    trans_inner_Y = mObjectAnimatorTransY_reset;
                    animatSet_scale_container = mAnimatorSetZoomOut;
                }
                trans_outter_X.setTarget(mImageViewOutter);
                trans_outter_X.start();

                trans_inner_Y.setTarget(mImageViewInner);
                trans_inner_Y.start();

                animatSet_scale_container.setTarget(v);
                animatSet_scale_container.start();

                if (hasFocus) {
                    mContainerTitle.setVisibility(View.VISIBLE);
                } else {
                    mContainerTitle.setVisibility(View.INVISIBLE);
                }

                if (metroView != null) {
                    metroView.applyFocusAnimation(mContainerBg,mContainerWhole, hasFocus);
                }
            }
        });
        mImageViewBg = (ImageView) findViewById(R.id.bg_image);
        mImageViewInner = (ImageView) findViewById(R.id.inner_image);
        mImageViewOutter = (ImageView) findViewById(R.id.outter_image);
        mContainerWhole = findViewById(R.id.whole_container);
        mContainerBg = findViewById(R.id.bg_container);
        mContainerOutter = findViewById(R.id.outter_container);
        mContainerInner = findViewById(R.id.inner_container);
        mContainerTitle = findViewById(R.id.inner_txt_container);
        mTextViewTitle = (TextView) findViewById(R.id.inner_txt);

        mAnimatorSetZoomIn = (AnimatorSet) AnimatorInflater.loadAnimator(
                context, R.animator.tv_zoom_in);
        mAnimatorSetZoomOut = (AnimatorSet) AnimatorInflater.loadAnimator(
                context, R.animator.tv_zoom_out);

        mObjectAnimatorTransX = (ObjectAnimator) AnimatorInflater.loadAnimator(
                context, R.animator.home_translate_horizontal);
        mObjectAnimatorTransX_reset = (ObjectAnimator) AnimatorInflater.loadAnimator(
                context, R.animator.home_translate_horizontal_reset);
        mObjectAnimatorTransY = (ObjectAnimator) AnimatorInflater.loadAnimator(
                context, R.animator.home_translate_vertical);
        mObjectAnimatorTransY_reset = (ObjectAnimator) AnimatorInflater.loadAnimator(
                context, R.animator.home_translate_vertical_reset);
    }

    protected abstract int getLayoutId();

}
