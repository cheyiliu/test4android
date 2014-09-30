
package test.android_secret_code;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SecretReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "hi, I got the secret code, " + intent, Toast.LENGTH_LONG).show();
    }

}
