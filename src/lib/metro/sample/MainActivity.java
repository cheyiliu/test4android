
package lib.metro.sample;

import java.util.ArrayList;

import lib.metro.MetroAdapter;
import lib.metro.MetroView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.test4android.R;

public class MainActivity extends Activity {
    private static int ROWS = 3;// TODO to test, change this value
    ArrayList<ItemInfo> mItemInfos = new ArrayList<ItemInfo>();
    MetroView metroView;
    MetroAdapter metroAdapter;

    EditText mEditText;
    Button mButton;
    CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lib_metro_sample);
        mEditText = (EditText) findViewById(R.id.editText1);
        mButton = (Button) findViewById(R.id.button1);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox1);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String strNum = mEditText.getEditableText().toString();
                    ROWS = Integer.parseInt(strNum);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                generateMetroItems(mCheckBox.isChecked());
                metroView.setAdapter(metroAdapter);
            }
        });

        metroView = (MetroView) findViewById(R.id.metroView);
        metroAdapter = new MetroAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView image = new ImageView(getApplicationContext());

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
                return 100;
            }

            @Override
            public int getMinHeight() {
                return 100;
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

    }

    private void generateMetroItems(boolean randomGen) {
        mItemInfos.clear();
        if (randomGen) {
            for (int i = 0; i < 50; i++) {
                int width = (int) (Math.random() * ROWS);
                int height = (int) (Math.random() * ROWS);
                if (width <= 0) {
                    width = 1;
                }
                if (height <= 0) {
                    height = 1;
                }
                mItemInfos.add(new ItemInfo(width, height));
            }
        } else if (ROWS == 1) {
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
