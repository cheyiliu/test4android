
package test.marqueeText;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CoolMarqueeText extends TextView {
    public CoolMarqueeText(Context con) {
        super(con);
    }

    public CoolMarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoolMarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void startMarquee() {
        setSelected(true);
    }

    public void stopMarquee() {
        setSelected(false);
    }
}
