
package lib.metro.sample;

import java.util.ArrayList;

import lib.metro.MetroAdapter;
import lib.metro.MetroView;

import com.test4android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class FragmentMetro2 extends Fragment {
    private static int ROWS = 2;// TODO to test, change this value
    ArrayList<ItemInfo> mItemInfos = new ArrayList<ItemInfo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lib_metro_sample2_fragment2, null);
        generateMetroItems();
        MetroView metroView = (MetroView) root.findViewById(R.id.metroView);
        MetroAdapter metroAdapter = new MetroAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView image = new ImageView(getActivity());

                image.setScaleType(ScaleType.FIT_XY);
                if (position % 2 == 0)
                    image.setImageResource(R.drawable.home_common_itemview_bg_1);
                else
                    image.setImageResource(R.drawable.home_common_itemview_bg_2);
                return image;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return mItemInfos.get(position);
            }

            @Override
            public int getCount() {
                return mItemInfos.size();
            }

            @Override
            public int getWidthRatio(int position) {
                return mItemInfos.get(position).width;
            }

            @Override
            public int getRowCount() {
                return ROWS;
            }

            @Override
            public int getMinWidth() {
                return 200;
            }

            @Override
            public int getMinHeight() {
                return 200;
            }

            @Override
            public int getHeightRatio(int position) {
                return mItemInfos.get(position).height;
            }

            @Override
            public int getMargin() {
                return 5;
            }
        };
        metroView.setAdapter(metroAdapter);
        return root;
    }

    private void generateMetroItems() {
        mItemInfos.clear();

        if (ROWS == 1) {
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(3, 1));
            mItemInfos.add(new ItemInfo(4, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(5, 1));
            mItemInfos.add(new ItemInfo(1, 1));
        } else if (ROWS == 2) {
            mItemInfos.add(new ItemInfo(2, 2));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 2));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 2));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 2));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 2));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 2));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 2));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
        } else if (ROWS == 3) {
            mItemInfos.add(new ItemInfo(3, 3));
            mItemInfos.add(new ItemInfo(2, 2));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 3));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 3));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 3));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 3));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 3));

            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 3));
        } else if (ROWS == 5) {
            mItemInfos.add(new ItemInfo(5, 1));
            mItemInfos.add(new ItemInfo(4, 1));
            mItemInfos.add(new ItemInfo(3, 1));
            mItemInfos.add(new ItemInfo(2, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 2));
            mItemInfos.add(new ItemInfo(1, 3));
            mItemInfos.add(new ItemInfo(1, 4));
            mItemInfos.add(new ItemInfo(1, 5));
        } else {
            // default
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
            mItemInfos.add(new ItemInfo(1, 1));
        }

    }
}
