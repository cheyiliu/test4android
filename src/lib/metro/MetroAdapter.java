
package lib.metro;

import android.widget.BaseAdapter;

public abstract class MetroAdapter extends BaseAdapter {
    /**
     * 返回总行数
     */
    public abstract int getRowCount();

    /**
     * 返回Cell Margin
     */
    public abstract int getMargin();

    /**
     * 返回最小Cell宽度
     */
    public abstract int getMinWidth();

    /**
     * 返回最小Cell高度
     */
    public abstract int getMinHeight();

    /**
     * 返回该Cell宽度和最小宽度的比例
     */
    public abstract int getWidthRatio(int position);

    /**
     * 返回该Cell高度和最小高度的比例
     */
    public abstract int getHeightRatio(int position);
}
