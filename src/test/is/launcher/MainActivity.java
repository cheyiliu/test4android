
package test.is.launcher;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    Log.e("test", "current app is home? " + (isHome() ? "yes" : "no"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public boolean isHome() {
        try {
            List<String> homes = getHomes();
            ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
            Log.i("test", "top task package:" + rti.get(0).topActivity.getPackageName());
            return homes.contains(rti.get(0).topActivity.getPackageName());
        } catch (Exception e) {
            Log.e("test", "" + e);
            return false;
        }
    }

    private List<String> getHomes() {
        List<String> packages = new ArrayList<String>();
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo info : resolveInfo) {
            packages.add(info.activityInfo.packageName);
            Log.i("test", "launcher package: " + info.activityInfo.packageName);
            Log.i("test", "launcher package: " + info.toString());
        }
        return packages;
    }
}
