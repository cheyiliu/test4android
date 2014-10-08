
package test.fileObserver;

import android.app.Activity;
import android.os.Environment;
import android.os.FileObserver;
import android.util.Log;

public class MainActivity extends Activity {
    private MyFileObs mFileObs = new MyFileObs(Environment.getExternalStorageDirectory()
            .getAbsolutePath());

    @Override
    protected void onPause() {
        mFileObs.stopWatching();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFileObs.startWatching();
    }

}

class MyFileObs extends FileObserver {

    private static final String TAG = "test";

    public MyFileObs(String path) {
        super(path);
    }

    @Override
    public void onEvent(int event, String path) {
        switch (event) {
            case FileObserver.ACCESS:
                Log.i(TAG, "ACCESS");
                break;
            case FileObserver.ATTRIB:
                Log.i(TAG, "ATTRIB");
                break;
            case FileObserver.CLOSE_NOWRITE:
                Log.i(TAG, "CLOSE_NOWRITE");
                break;
            case FileObserver.CLOSE_WRITE:
                Log.i(TAG, "CLOSE_WRITE");
                break;
            case FileObserver.CREATE:
                Log.i(TAG, "CREATE");
                break;
            case FileObserver.DELETE:
                Log.i(TAG, "DELETE");
                break;
            case FileObserver.DELETE_SELF:
                Log.i(TAG, "DELETE_SELF");
                break;
            case FileObserver.MODIFY:
                Log.i(TAG, "MODIFY");
                break;
            case FileObserver.MOVE_SELF:
                Log.i(TAG, "MOVE_SELF");
                break;
            case FileObserver.MOVED_FROM:
                Log.i(TAG, "MOVED_FROM");
                break;
            case FileObserver.MOVED_TO:
                Log.i(TAG, "MOVED_TO");
                break;
            case FileObserver.OPEN:
                Log.i(TAG, "OPEN");
                break;
            case FileObserver.ALL_EVENTS:
                Log.i(TAG, "ALL_EVENTS");
                break;
            default:
                Log.i(TAG, "default, event=" + event + ", path=" + path);
                break;
        }

    }
}
