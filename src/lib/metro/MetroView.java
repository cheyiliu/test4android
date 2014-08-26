
package lib.metro;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test4android.R;

public class MetroView extends RelativeLayout {

    private MetroAdapter mAdapter;
    private GridLayout mGridLayout;
    private ImageView mImageViewBorder;
    private ImageView mImageViewShadow;
    private ImageView mImageViewCenter;
    private AnimationProvider mAnimationProvider = new AnimationProvider();

    public MetroView(Context context) {
        this(context, null, 0);
    }

    public MetroView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetroView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.lib_metro, this, true);
        mGridLayout = (GridLayout) findViewById(R.id.grid_layout);
        mImageViewBorder = (ImageView) findViewById(R.id.focus_border);
        mImageViewCenter = (ImageView) findViewById(R.id.focus_center);
        mImageViewShadow = (ImageView) findViewById(R.id.focus_shadow);
    }

    public void setAdapter(MetroAdapter metroAdapter) {
        mAdapter = metroAdapter;
        updateUI();
    }

    private void updateUI() {
        mGridLayout.removeAllViews();
        mGridLayout.setRowCount(mAdapter.getRowCount());
        mGridLayout.setOrientation(GridLayout.VERTICAL);
        int count = mAdapter.getCount();
        int minWidth = mAdapter.getMinWidth();
        int minHeight = mAdapter.getMinHeight();
        int margin = mAdapter.getMargin();
        for (int position = 0; position < count; position++) {
            int widthRatio = mAdapter.getWidthRatio(position);
            int heightRatio = mAdapter.getHeightRatio(position);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = minWidth * widthRatio;
            if (widthRatio > 1) {
                params.width += (margin * (widthRatio - 1) * 2);
            }
            params.height = minHeight * heightRatio;
            if (heightRatio > 1) {
                params.height += (margin * (heightRatio - 1) * 2);
            }
            params.rowSpec = GridLayout.spec(Integer.MIN_VALUE, heightRatio);
            params.columnSpec = GridLayout.spec(Integer.MIN_VALUE, widthRatio);
            params.setMargins(margin, margin, margin, margin);
            View view = mAdapter.getView(position, null, null);
            view.setLayoutParams(params);
            mGridLayout.addView(view);
            view.setFocusable(true);
            view.setOnFocusChangeListener(new OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    mAnimationProvider.applyAnimation(hasFocus, v, mImageViewCenter,
                            mImageViewBorder, mImageViewShadow);
                }
            });
        }
    }
}
