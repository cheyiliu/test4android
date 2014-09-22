
package test.db.upgrade;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * adb shell 
         * cd data/data/com.test4android/databases 
         * sqlite3 demo.db
         * .tables 
         * .dump news 
         * .dump comment 
         * .exit
         */
        // test v1->v2->v3
        {
            MySQLiteHelperV1 mySQLiteHelperV1 = new MySQLiteHelperV1(this, "demo.db", null, 0);
            mySQLiteHelperV1.getWritableDatabase();

            MySQLiteHelperV2 mySQLiteHelperV2 = new MySQLiteHelperV2(this, "demo.db", null, 0);
            mySQLiteHelperV2.getWritableDatabase();

            MySQLiteHelperV3 mySQLiteHelperV3 = new MySQLiteHelperV3(this, "demo.db", null, 0);
            mySQLiteHelperV3.getWritableDatabase();
        }

        /**
         * need do adb uninstall com.test4android
         */
//        // test v1->v3
//        {
//            MySQLiteHelperV1 mySQLiteHelperV1 = new MySQLiteHelperV1(this,
//                    "demo.db", null, 0);
//            mySQLiteHelperV1.getWritableDatabase();
//
//            MySQLiteHelperV3 mySQLiteHelperV3 = new MySQLiteHelperV3(this,
//                    "demo.db", null, 0);
//            mySQLiteHelperV3.getWritableDatabase();
//        }
    }

}
