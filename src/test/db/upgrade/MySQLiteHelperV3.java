
package test.db.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

// V3
public class MySQLiteHelperV3 extends SQLiteOpenHelper {
    public static final int VERSION = 3;
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

    // V3
    public static final String CREATE_COMMENT_V3 = "create table comment ("
            + "id integer primary key autoincrement, "
            + "content text, "
            + "publishdate integer)";

    public MySQLiteHelperV3(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS_V1);
        // // V2 add
        // db.execSQL(CREATE_COMMENT_V2);
        // V3 add
        db.execSQL(CREATE_COMMENT_V3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1: // V2 add
                db.execSQL(CREATE_COMMENT_V2);
            case 2: // V3 add
                db.execSQL("alter table comment add column publishdate integer");
            default:
        }
    }

}
