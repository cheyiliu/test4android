
package com.test4android;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;
import test.acra.MyReportSender;
import android.app.Application;

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
    }
}
