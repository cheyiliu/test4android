
package test.acra;

import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import android.util.Log;

public class MyReportSender implements ReportSender {

    @Override
    public void send(CrashReportData errorContent) throws ReportSenderException {
        Log.i("test", "send, " + errorContent);
    }

}
