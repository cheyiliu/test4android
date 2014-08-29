package lib.dpad;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test4android.R;

public class RemoteCtlCenterPad extends RelativeLayout {

	private static final long TOUCH_MOVIE_EVENT_DELAY = 150; // ms
	private static final long VIBRATOR_DURATION = 100; // ms
	private Vibrator mVibrator;

	private OnKeyListener mOnKeyListener;

	private ImageView mImageViewBg;
	private ImageView mImageViewCircle;
	private ImageButton mImageButtonOK;

	private long mLastUpdateTime = 0;

	public RemoteCtlCenterPad(Context context) {
		super(context);
		init(context, null, 0);
	}

	public RemoteCtlCenterPad(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public RemoteCtlCenterPad(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	private void init(Context context, AttributeSet attrs, int defStyle) {
		// get the service
		mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

		// load the xml and default res
		LayoutInflater.from(context).inflate(R.layout.lib_dpad_layout, this, true);
		mImageViewBg = (ImageView) findViewById(R.id.lib_imageView_bg);
		mImageViewCircle = (ImageView) findViewById(R.id.lib_imageView_circle);
		mImageViewCircle.setVisibility(View.INVISIBLE);
		mImageButtonOK = (ImageButton) findViewById(R.id.lib_imageButton_ok);

		// apply the custom res if necessary
		TypedArray typedArray = null;
		try {
			typedArray = context.obtainStyledAttributes(attrs, R.styleable.RemoteDPad);
			int bgRes = typedArray.getResourceId(R.styleable.RemoteDPad_bg, 0);
			int circleRes = typedArray.getResourceId(R.styleable.RemoteDPad_circle, 0);
			int okRes = typedArray.getResourceId(R.styleable.RemoteDPad_ok, 0);
			if (bgRes != 0) {
				mImageViewBg.setImageResource(bgRes);
			}
			if (circleRes != 0) {
				mImageViewCircle.setImageResource(circleRes);
			}
			if (okRes != 0) {
				mImageButtonOK.setImageResource(okRes);
			}
		} finally {
			if (typedArray != null) {
				typedArray.recycle();
			}
		}

		// set listener
		OnTouchListener l = new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (v.getId() == R.id.lib_imageView_bg) {

					if (event.getAction() == MotionEvent.ACTION_UP) {
						// when action up, hide the light
						mImageViewCircle.setVisibility(View.INVISIBLE);
					} else if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {

						// if the touch event is not in the light circle, ignore
						if (!isEventInCircle(event)) {
							return true;
						}

						long deltTime = System.currentTimeMillis() - mLastUpdateTime;

						float degree = getDegree(v, event);

						// update the circle
						float deltRotate = degree - mImageViewCircle.getRotation();
						mImageViewCircle.animate().rotationBy(deltRotate).setDuration(0);

						if (deltTime > TOUCH_MOVIE_EVENT_DELAY) {
							mVibrator.vibrate(VIBRATOR_DURATION);
							mLastUpdateTime = System.currentTimeMillis();
							dispatchKeyEvent(degree);
						}
						if (mImageViewCircle.getVisibility() != View.VISIBLE) {
							mImageViewCircle.setVisibility(View.VISIBLE);
						}
					}
				}
				return true;
			}

			/**
			 * Check whether the event is in the Ring(环)
			 */
			private boolean isEventInCircle(MotionEvent event) {
				// the view must be a Square
				double radius_big = mImageViewBg.getHeight() / 2;
				double radius_small = mImageButtonOK.getWidth() / 2;

				double x = event.getX() - radius_big;
				double y = event.getY() - radius_big;

				double hypot = Math.hypot(x, y);

				return radius_big > hypot && hypot > radius_small;
			}

			/**
			 * Get the degree, range is [0,360]
			 */
			private float getDegree(View v, MotionEvent event) {
				float deltX = event.getX() - v.getWidth() / 2;
				float deltY = event.getY() - v.getHeight() / 2;
				// degree is related to res
				float degree = (float) (Math.atan2(deltY, deltX) / 3.14 * 180) + 360 + 270;
				degree %= 360;

				return degree;
			}
		};
		mImageViewBg.setOnTouchListener(l);

		mImageButtonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mVibrator.vibrate(VIBRATOR_DURATION);
				onKey(KeyCode.KeyOk);
			}
		});
	}

	/**
	 * Dispatch the center pad event
	 */
	protected void dispatchKeyEvent(float degree) {
		// degree range is [0, 360]
		if (degree < 45 || degree > 315) {
			onKey(KeyCode.KeyDown);
		} else if (degree > 45 && degree < 135) {
			onKey(KeyCode.KeyLeft);
		} else if (degree > 135 && degree < 225) {
			onKey(KeyCode.KeyUp);
		} else if (degree > 225 && degree < 315) {
			onKey(KeyCode.KeyRight);
		}
	}

	/**
	 * Helper function to check null
	 */
	private void onKey(KeyCode keyCode) {
		if (mOnKeyListener != null) {
			mOnKeyListener.onKey(keyCode);
		}
	}

	/**
	 * Set the center pad OnKeyListener
	 */
	public void setOnKeyListener(OnKeyListener l) {
		mOnKeyListener = l;
	}

	/**
	 * Center pad key enum (KeyUp, KeyDown, KeyLeft, KeyRight, KeyOk)
	 */
	public enum KeyCode {
		KeyUp, KeyDown, KeyLeft, KeyRight, KeyOk
	}

	/**
	 * Center pad event handle interface
	 */
	public interface OnKeyListener {
		public void onKey(KeyCode keyCode);
	}
}
