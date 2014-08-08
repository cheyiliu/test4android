
package test.log.in.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;

/**
 * Another great log cat open source project https://github.com/cheyiliu/Catlog
 */
public class MainActivity extends Activity {

    private boolean mStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {

            @Override
            public void run() {
                Process mLogcatProc = null;
                BufferedReader reader = null;
                BufferedWriter writer = null;
                try {
                    mLogcatProc = Runtime.getRuntime().exec(new String[] {
                            // "logcat", "WindowManager:I *:S"
                            // "logcat", ""
                            "logcat", "-v", "time", "-d"
                    });
                    reader = new BufferedReader(new InputStreamReader(mLogcatProc.getInputStream()));
                    File tmp = new File(getCacheDir() + File.separator
                            + "tmpLogInApp");
                    if (tmp.exists()) {
                        tmp.delete();
                    }
                    tmp.createNewFile();
                    OutputStream out = new FileOutputStream(tmp);
                    writer = new BufferedWriter(new OutputStreamWriter(out));

                    String line;
                    while (true) {
                        if (mStop) {
                            break;
                        }
                        line = reader.readLine();
                        if (line != null) {
                            writer.newLine();
                            writer.write(line);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (writer != null) {
                            writer.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mStop = true;
    }

}
