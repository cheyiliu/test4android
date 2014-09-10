
package test.localsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import android.app.Activity;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.test4android.R;

/**
 * https://github.com/gaojiexxx/note/blob/master/
 * android_Ethernet_pluggedin_or_unplugged_event.txt
 * http://www.cnblogs.com/bastard/archive/2012/10/09/2717052.html
 */
public class MainActivity extends Activity {

    private static final String TAG = "test";

    public static String SOCKET_ADDRESS = "your.local.socket.address";//

    private boolean mStopped = false;
    private final Handler mHandler = new Handler();
    private final NotificationRunnable mNotificationRunnable = new NotificationRunnable();

    public class NotificationRunnable implements Runnable {
        private String mMessage = null;

        public void run() {
            if (!TextUtils.isEmpty(mMessage)) {
                Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
            }
        }

        public void setMessage(String message) {
            this.mMessage = message;
        }
    }

    class SocketListener extends Thread {

        public SocketListener() {
        }

        @Override
        public void run() {
            Log.i(TAG, "Server socket run . . . start");
            showMessage("DEMO: SocketListener started!");
            LocalServerSocket server = null;
            try {
                server = new LocalServerSocket(SOCKET_ADDRESS);
                while (!mStopped) {
                    LocalSocket receiver = server.accept();// TODO may not stop
                                                           // as this block call
                                                           // fixed, when change
                                                           // mStopped to true,
                                                           // send a
                                                           // bye bye msg
                    if (receiver != null) {
                        InputStream inputStream = receiver.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String inputLine = null;
                        StringBuilder sb = new StringBuilder();
                        while (((inputLine = bufferedReader.readLine()) != null)) {
                            sb.append(inputLine);
                        }
                        showMessage(sb.toString());
                        bufferedReader.close();
                        receiver.close();
                        sb = null;
                    }
                }
            } catch (IOException e) {
                Log.e(getClass().getName(), e.getMessage());
                showMessage("exception: " + e);
            } finally {
                if (server != null) {
                    try {
                        server.close();
                    } catch (IOException e) {
                        Log.e(getClass().getName(), e.getMessage());
                        showMessage("exception: " + e);
                    }
                }
            }

            showMessage("DEMO: SocketListener stopped!");
            Log.i(TAG, "Server socket run . . . end");
        }
    }

    private void showMessage(String message) {
        Log.i(TAG, "showMessage, " + message);
        mNotificationRunnable.setMessage(message);
        mHandler.post(mNotificationRunnable);
    }

    public void writeSocket(String message) throws IOException {
        Log.i(TAG, "writeSocket, " + message);
        LocalSocket sender = new LocalSocket();
        sender.connect(new LocalSocketAddress(SOCKET_ADDRESS));
        sender.getOutputStream().write(message.getBytes());
        sender.getOutputStream().close();
        sender.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.test_local_socket_activity_main);

        Button send1 = (Button) findViewById(R.id.send_msg);
        send1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Date date = new Date(System.currentTimeMillis());
                    writeSocket("hello world, now time is, " + date.toString());
                } catch (IOException e) {
                    Log.e(getClass().getName(), e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume, to start socket");
        super.onResume();
        mStopped = false;
        new SocketListener().start();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause, to stop socket");
        mStopped = true;
        try {
            writeSocket("bye bye~~~");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

}
