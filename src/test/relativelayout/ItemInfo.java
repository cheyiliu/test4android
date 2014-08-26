
package test.relativelayout;

public class ItemInfo {
    public int id = -1;
    public int width = -1;
    public int height = -1;
    public boolean isTop = false;// 决定用哪个布局(第一行双图outer突出，第二行outer不突出)
    public int belowOf = -1;// BELOW ALIGN_START
    public int rightOf = -1;// RIGHT_OF ALIGN_TOP
    public int anchorId = -1;// 锚点

    public ItemInfo(int id, int width, int height, boolean isTop, int belowOf, int rightOf,
            int anchorId) {
        super();
        this.id = id;
        this.width = width;
        this.height = height;
        this.isTop = isTop;
        this.belowOf = belowOf;
        this.rightOf = rightOf;
        this.anchorId = anchorId;
    }

}
