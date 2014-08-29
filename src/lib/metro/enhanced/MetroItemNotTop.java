
package lib.metro.enhanced;

import android.content.Context;
import android.util.AttributeSet;

import com.test4android.R;

public class MetroItemNotTop extends MetroItemBase {
    public MetroItemNotTop(Context context) {
        this(context, null, 0);
    }

    public MetroItemNotTop(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetroItemNotTop(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.lib_metro_enhanced_cell_not_top;
    }
}
