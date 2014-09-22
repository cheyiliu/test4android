
package test.db.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

// V2
public class MySQLiteHelperV2 extends SQLiteOpenHelper {
    public static final int VERSION = 2;
    // V1
    public static final String CREATE_NEWS_V1 = "create table news ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "content text, "
            + "publishdate integer,"
            + "commentcount integer)";

    // V2
    public static final String CREATE_COMMENT_V2 = "create table comment ("
            + "id integer primary key autoincrement, "
            + "content text)";

    public MySQLiteHelperV2(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS_V1);
        // V2 add
        db.execSQL(CREATE_COMMENT_V2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1: // V2 add
                db.execSQL(CREATE_COMMENT_V2);
            default:
        }
    }

}
