
package com.test4android;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;
import org.acra.collector.DropBoxCollector;
import org.acra.collector.LogCatCollector;

import test.acra.MyReportSender;
import android.app.Application;
import android.os.DropBoxManager;
import android.util.EventLog;
import android.util.Log;

@ReportsCrashes(
        formKey = "",
        mode = org.acra.ReportingInteractionMode.TOAST,
        resToastText = R.string.hello_world)
public class Test4AndroidApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
        MyReportSender yourSender = new MyReportSender();
        ACRA.getErrorReporter().setReportSender(yourSender);
        String log = LogCatCollector.collectLogCat(null);
        Log.i("test", log);
        log = DropBoxCollector.read(this, null);
        Log.i("test", log);
//        EventLog
//        DropBoxManager dropBoxManager;
//        dropBoxManager.
    }
}
