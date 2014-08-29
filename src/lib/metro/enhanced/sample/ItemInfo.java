
package lib.metro.enhanced.sample;

public class ItemInfo {

    public int x = -1;
    public int y = -1;
    public int width = -1;
    public int height = -1;
    public boolean isTop = false;// 决定用哪个布局(第一行双图outer突出，第二行outer不突出)

    public ItemInfo(int x, int y, int width, int height, boolean isTop) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isTop = isTop;
    }

}
