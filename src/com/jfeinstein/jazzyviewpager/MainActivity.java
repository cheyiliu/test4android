
package com.jfeinstein.jazzyviewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.jfeinstein.jazzyviewpager.JazzyViewPager.TransitionEffect;
import com.test4android.R;

// from https://github.com/jfeinstein10/JazzyViewPager
public class MainActivity extends Activity implements
        AdapterView.OnItemSelectedListener {

    private JazzyViewPager mJazzy;
    private String[] mStrings = {
            "Accordion",
            "CubeIn",
            "CubeOut",
            "FlipHorizontal",
            "FlipVertical",
            "RotateDown",
            "RotateUp",
            "Stack",
            "Standard",
            "Tablet",
            "ZoomIn",
            "ZoomOut"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_jazzyviewpager_activity_main);
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);

        setupJazziness(TransitionEffect.Accordion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Toggle Fade");
        String[] effects = this.getResources().getStringArray(R.array.jazzy_effects);
        for (String effect : effects)
            menu.add(effect);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().toString().equals("Toggle Fade")) {
            mJazzy.setFadeEnabled(!mJazzy.getFadeEnabled());
        } else {
            TransitionEffect effect = TransitionEffect.valueOf(item.getTitle().toString());
            setupJazziness(effect);
        }
        return true;
    }

    private void setupJazziness(TransitionEffect effect) {
        mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
        mJazzy.setTransitionEffect(effect);
        mJazzy.setAdapter(new MainAdapter());
        mJazzy.setPageMargin(30);
    }

    private class MainAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            TextView text = new TextView(MainActivity.this);
            text.setGravity(Gravity.CENTER);
            text.setTextSize(30);
            text.setTextColor(Color.WHITE);
            text.setText("Page " + position);
            text.setPadding(30, 30, 30, 30);
            int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                    (int) Math.floor(Math.random() * 128) + 64,
                    (int) Math.floor(Math.random() * 128) + 64);
            text.setBackgroundColor(bg);
            container.addView(text, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mJazzy.setObjectForPosition(text, position);
            return text;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(mJazzy.findViewFromObject(position));
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            if (view instanceof OutlineContainer) {
                return ((OutlineContainer) view).getChildAt(0) == obj;
            } else {
                return view == obj;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {

            case 0:
                setupJazziness(TransitionEffect.Accordion);
                break;
            case 1:
                setupJazziness(TransitionEffect.CubeIn);
                break;
            case 2:
                setupJazziness(TransitionEffect.CubeOut);
                break;
            case 3:
                setupJazziness(TransitionEffect.FlipHorizontal);
                break;
            case 4:
                setupJazziness(TransitionEffect.FlipVertical);
                break;
            case 5:
                setupJazziness(TransitionEffect.RotateDown);
                break;
            case 6:
                setupJazziness(TransitionEffect.RotateUp);
                break;
            case 7:
                setupJazziness(TransitionEffect.Stack);
                break;
            case 8:
                setupJazziness(TransitionEffect.Standard);
                break;
            case 9:
                setupJazziness(TransitionEffect.Tablet);
                break;
            case 10:
                setupJazziness(TransitionEffect.ZoomIn);
                break;
            case 11:
                setupJazziness(TransitionEffect.ZoomOut);
                break;
            default:
                setupJazziness(TransitionEffect.Accordion);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }
}
