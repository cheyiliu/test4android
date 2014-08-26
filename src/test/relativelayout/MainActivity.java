
package test.relativelayout;

import java.util.ArrayList;

import com.test4android.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private RelativeLayout mRelatvieLayoutRoot;
    private ArrayList<ItemInfo> mItemInfos = new ArrayList<ItemInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_relatvielayout_activity_main);
        mRelatvieLayoutRoot = (RelativeLayout) findViewById(R.id.relativeLayout_root);
        generateItems();
        for (int i = 0; i < mItemInfos.size(); i++) {
            addItem(mItemInfos.get(i));
        }
        // TextView item1 = new TextView(this);
        // item1.setText("1");
        // item1.setBackgroundResource(R.drawable.home_common_itemview_bg_1);//
        // 设置图片
        // RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
        // 100, 100);
        // // lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);// 与父容器的左侧对齐
        // // lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);// 与父容器的上侧对齐
        // lp.leftMargin = 30;
        // lp.topMargin = 30;
        // item1.setId(1);// 设置这个View 的id
        // item1.setLayoutParams(lp);// 设置布局参数
        // mRelatvieLayoutRoot.addView(item1);// RelativeLayout添加子View
        //
        // // 2
        // TextView item2 = new TextView(this);
        // item2.setText("2");
        // item2.setBackgroundResource(R.drawable.home_common_itemview_bg_2);//
        // 设置图片
        // RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
        // 150, 150);
        // lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        // lp2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        // // lp2.rightMargin = 30;
        // // lp2.topMargin = 30;
        // item2.setId(2);
        // item2.setLayoutParams(lp2);
        // mRelatvieLayoutRoot.addView(item2);
        //
        // // 3
        // TextView item3 = new TextView(this);
        // item3.setText("3");
        // item3.setBackgroundResource(R.drawable.home_common_itemview_bg_1);//
        // 设置图片
        // RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
        // 200, 200);
        // lp3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        // lp3.addRule(RelativeLayout.BELOW, item1.getId());// 设置item3在
        // // //chlidView1的下面
        // // lp3.leftMargin = 30;
        // // lp3.topMargin = 30;
        // item3.setId(3);
        // item3.setLayoutParams(lp3);
        // mRelatvieLayoutRoot.addView(item3);
        //
        // // 4
        // TextView item4 = new TextView(this);
        // item4.setText("4");
        // item4.setBackgroundResource(R.drawable.home_common_itemview_bg_2);//
        // 设置图片
        // RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(
        // 250, 250);
        // lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        // lp4.addRule(RelativeLayout.BELOW, item2.getId());
        // // lp4.rightMargin = 30;
        // // lp4.topMargin = 30;
        // item4.setId(4);
        // item4.setLayoutParams(lp4);
        // mRelatvieLayoutRoot.addView(item4);
        //
        // // 5
        // TextView item5 = new TextView(this);
        // item5.setText("5");
        // item5.setBackgroundResource(R.drawable.home_common_itemview_bg_1);//
        // 设置图片
        // RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(
        // 300, 300);
        // // lp5.addRule(RelativeLayout.RIGHT_OF, item1.getId());
        // // lp5.addRule(RelativeLayout.ALIGN_TOP, item1.getId());
        // lp5.addRule(RelativeLayout.BELOW, item1.getId());
        // lp5.addRule(RelativeLayout.ALIGN_START, item1.getId());
        // // lp5.addRule(RelativeLayout.ALIGN_TOP, item1.getId());
        // // lp5.leftMargin = 30;
        // lp5.topMargin = 30;
        // item5.setId(5);
        // item5.setLayoutParams(lp5);
        // mRelatvieLayoutRoot.addView(item5);
    }

    public void generateItems() {
        mItemInfos.add(new ItemInfo(1, 1, 2, true, -1, -1, -1));
        mItemInfos.add(new ItemInfo(2, 2, 1, true, -1, 1, 1));
        mItemInfos.add(new ItemInfo(3, 1, 1, false, 1, -1, 2));
        mItemInfos.add(new ItemInfo(4, 1, 1, false, -1, 1, 3));

        mItemInfos.add(new ItemInfo(5, 1, 1, true, -1, 1, 2));
        mItemInfos.add(new ItemInfo(6, 1, 1, true, -1, 1, 5));
        mItemInfos.add(new ItemInfo(7, 2, 1, false, 1, -1, 5));

        mItemInfos.add(new ItemInfo(8, 2, 1, true, -1, 1, 6));
        mItemInfos.add(new ItemInfo(9, 1, 1, false, 1, -1, 8));
        mItemInfos.add(new ItemInfo(10, 1, 1, false, -1, 1, 9));

        mItemInfos.add(new ItemInfo(11, 1, 1, true, -1, 1, 8));
        mItemInfos.add(new ItemInfo(12, 1, 1, false, 1, -1, 11));
    }

    @SuppressLint("InlinedApi")
    public void addItem(ItemInfo itemInfo) {
        int minWidth = 50;
        int minHeight = 50;
        int margin = 5;
        TextView tv = new TextView(this);
        if (itemInfo.isTop) {
            tv.setText(itemInfo.id + ", top");
        } else {
            tv.setText(itemInfo.id + ", NOT top");
        }

        tv.setBackgroundResource(R.drawable.home_common_itemview_bg_1);

        int width;
        if (itemInfo.height > 1) {
            width = (int) (minWidth * itemInfo.width * 1.5);
        } else {
            width = minWidth * itemInfo.width;
        }
        width += (margin * (itemInfo.width - 1));

        int height = minHeight * itemInfo.height;
        height += (margin * (itemInfo.height - 1));

        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(
                width, height);

        if (itemInfo.anchorId > 0) {
            if (itemInfo.belowOf > 0) {
                lp5.addRule(RelativeLayout.BELOW, itemInfo.anchorId);
                lp5.addRule(RelativeLayout.ALIGN_START, itemInfo.anchorId);
                lp5.topMargin = margin;
            } else if (itemInfo.rightOf > 0) {
                lp5.addRule(RelativeLayout.RIGHT_OF, itemInfo.anchorId);
                lp5.addRule(RelativeLayout.ALIGN_TOP, itemInfo.anchorId);
                lp5.leftMargin = margin;
            }
        }

        tv.setId(itemInfo.id);
        tv.setLayoutParams(lp5);
        mRelatvieLayoutRoot.addView(tv);
    }
}
