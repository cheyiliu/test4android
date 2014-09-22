
package test.db.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

// V1
public class MySQLiteHelperV1 extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    // V1
    public static final String CREATE_NEWS_V1 = "create table news ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "content text, "
            + "publishdate integer,"
            + "commentcount integer)";

    public MySQLiteHelperV1(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS_V1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
