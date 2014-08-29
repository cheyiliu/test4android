
package lib.metro.enhanced.sample;

import java.util.ArrayList;

import lib.metro.enhanced.MetroAdapter;
import lib.metro.enhanced.MetroItemNotTop;
import lib.metro.enhanced.MetroItemTop;
import lib.metro.enhanced.MetroView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.test4android.R;

public class MainActivity extends Activity {
    private MetroView mMetroView;
    private ArrayList<ItemInfo> mItemInfos = new ArrayList<ItemInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_metro_enhanced_sample_activity_main);
        mMetroView = (MetroView) findViewById(R.id.metro_view);
        generateItems();

        mMetroView.setAdapter(new MetroAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View cellView;
                if (isTop(position)) {
                    cellView = new MetroItemTop(getApplicationContext());
                } else {
                    cellView = new MetroItemNotTop(getApplicationContext());
                }
                return cellView;
                
//                RelativeLayout cellView = new RelativeLayout(getApplicationContext());
//                ImageView imageView = new ImageView(getApplicationContext());
//                imageView.setBackgroundResource(R.drawable.home_common_itemview_bg_1);
//                cellView.addView(imageView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//                return cellView;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public boolean isTop(int position) {
                return mItemInfos.get(position).isTop;
            }

            @Override
            public int getY(int position) {
                return mItemInfos.get(position).y;
            }

            @Override
            public int getX(int position) {
                return mItemInfos.get(position).x;
            }

            @Override
            public int getWidth(int position) {
                return mItemInfos.get(position).width;
            }

            @Override
            public int getHeight(int position) {
                return mItemInfos.get(position).height;
            }

            @Override
            public int getCount() {
                return mItemInfos.size();
            }
        });
    }

    public void generateItems() {
        // line 1
        mItemInfos.add(new ItemInfo(122, 115, 276, 459, true));
        mItemInfos.add(new ItemInfo(402, 115, 428, 243, true));
        mItemInfos.add(new ItemInfo(834, 115, 212, 243, true));
        mItemInfos.add(new ItemInfo(1050, 115, 212, 243, true));
        mItemInfos.add(new ItemInfo(1266, 115, 428, 243, true));
        mItemInfos.add(new ItemInfo(1698, 115, 212, 243, true));

        // line 2
        mItemInfos.add(new ItemInfo(402, 362, 212, 212, false));
        mItemInfos.add(new ItemInfo(618, 362, 212, 212, false));
        mItemInfos.add(new ItemInfo(834, 362, 428, 212, false));
        mItemInfos.add(new ItemInfo(1266, 362, 212, 212, false));
        mItemInfos.add(new ItemInfo(1482, 362, 212, 212, false));
        mItemInfos.add(new ItemInfo(1698, 362, 212, 212, false));
    }

}
