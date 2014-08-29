
package lib.metro.enhanced;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.test4android.R;

public class MetroView extends FrameLayout {
    private MetroAdapter mMetroAdapter;

    private ImageView mImageViewBorder;
    private ImageView mImageViewShadow;// TODO shadow and border

    public MetroView(Context context) {
        this(context, null, 0);
    }

    public MetroView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetroView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.lib_metro_enhanced, this, true);
        mImageViewBorder = (ImageView) findViewById(R.id.focus_border);
        mImageViewShadow = (ImageView) findViewById(R.id.focus_shadow);
    }

    public void setAdapter(MetroAdapter adapter) {
        if (adapter != null) {
            mMetroAdapter = adapter;
            updateUI();
        }
    }

    private void updateUI() {
        int totalItems = mMetroAdapter.getCount();
        for (int position = 0; position < totalItems; position++) {
            int width = mMetroAdapter.getWidth(position);
            int height = mMetroAdapter.getHeight(position);
            int x = mMetroAdapter.getX(position);
            int y = mMetroAdapter.getY(position);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
            params.leftMargin = x;
            params.topMargin = y;
            params.rightMargin = 122; // from PPTV
            params.bottomMargin = 122;
            View cellView = mMetroAdapter.getView(position, null, null);
            addView(cellView, params);
        }
    }

    public void applyFocusAnimation(View realFocusView, View realFocusViewContainer,boolean hasFocus) {
        new AnimationProvider().applyAnimation(hasFocus, realFocusView, realFocusViewContainer, mImageViewBorder,
                mImageViewShadow);
    }
}
