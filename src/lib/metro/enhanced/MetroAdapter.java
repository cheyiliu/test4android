
package lib.metro.enhanced;

import android.widget.BaseAdapter;

public abstract class MetroAdapter extends BaseAdapter {
    /**
     * 返回Cell总数
     */
    public abstract int getCount();

    /**
     * 返回该Cell宽度
     */
    public abstract int getWidth(int position);

    /**
     * 返回该Cell高度
     */
    public abstract int getHeight(int position);

    /**
     * 返回该Cell相对屏幕左上方的x坐标
     */
    public abstract int getX(int position);

    /**
     * 返回该Cell相对屏幕左上方的y坐标
     */
    public abstract int getY(int position);

    /**
     * 返回该Cell是否位于第一行[用于区分布局，双图头像在第一行可伸出外面]
     */
    public abstract boolean isTop(int position);

}
