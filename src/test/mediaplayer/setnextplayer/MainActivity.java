
package test.mediaplayer.setnextplayer;

import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.test4android.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends Activity implements OnClickListener {
    SurfaceView surfaceView;
    Button play, pause, stop;
    MediaPlayer mPlayer, mPlayerNext;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_mediaplayer_setnextmediaplayer);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        mPlayer = new MediaPlayer();
        mPlayerNext = new MediaPlayer();
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
        // // 设置SurfaceView自己不管理的缓冲区
        // surfaceView.getHolder().setType(
        // SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // 设置播放时打开屏幕
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new SurfaceListener());
    }

    @Override
    public void onClick(View source) {
        try {
            switch (source.getId()) {
                case R.id.play:
                    play();
                    break;

                case R.id.pause:
                    if (mPlayer.isPlaying()) {
                        mPlayer.pause();
                    }
                    else {
                        mPlayer.start();
                    }
                    break;
                // 停止按钮被单击
                case R.id.stop:
                    if (mPlayer.isPlaying())
                        mPlayer.stop();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void play() throws IOException {
        mPlayer.reset();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setDataSource("http://192.168.7.75:9090/hlsvod/test1.ts");
        mPlayer.setDisplay(surfaceView.getHolder());
        mPlayer.prepare();
        mPlayer.start();

        // mPlayerNext.reset();
        // mPlayerNext.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayerNext.setDataSource("http://192.168.7.75:9090/hlsvod/test2.ts");
        // mPlayerNext.setDisplay(surfaceView.getHolder());
        mPlayerNext.prepare();

        mPlayer.setNextMediaPlayer(mPlayerNext);
    }

    private class SurfaceListener implements SurfaceHolder.Callback {

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (position > 0) {
                try {
                    play();
                    // 并直接从指定位置开始播放
                    mPlayer.seekTo(position);
                    position = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    }

    @Override
    protected void onPause() {
        if (mPlayer.isPlaying()) {
            // 保存当前的播放位置
            position = mPlayer.getCurrentPosition();
            mPlayer.stop();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // 停止播放
        if (mPlayer.isPlaying())
            mPlayer.stop();
        // 释放资源
        mPlayer.release();
        super.onDestroy();
    }
}
