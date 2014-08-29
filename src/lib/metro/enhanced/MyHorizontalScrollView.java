
package lib.metro.enhanced;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class MyHorizontalScrollView extends HorizontalScrollView {
    public MyHorizontalScrollView(Context paramContext) {
        this(paramContext, null, 0);
    }

    public MyHorizontalScrollView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public MyHorizontalScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect paramRect) {
        if (getChildCount() == 0)
            return 0;
        int i = getWidth();
        int j = getScrollX();
        int k = getHorizontalFadingEdgeLength();
        int m = j + 200;
        int n = -200 + (i + j);
        if (paramRect.left > 0)
            m += k;
        if (paramRect.right < getChildAt(0).getWidth())
            n -= k;
        if ((paramRect.left < m) && (paramRect.right < n))
            return Math.max(paramRect.left - m, -j);
        if ((paramRect.left > m) && (paramRect.right > n))
            return Math.min(paramRect.left - m, paramRect.right - n);
        return super.computeScrollDeltaToGetChildRectOnScreen(paramRect);
    }
}
