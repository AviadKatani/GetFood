package aviadapps.getfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "userdb.db";
    public static final String TABLE_USERS = "Users";
    private static final String KEY_ID = "_id";
    public static final String NAME = "Name";
    public static final String PASSWORD = "Password";
    public static final String AGE = "Age";

    String strcreate, strdelete;

    public HelperDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strcreate = "CREATE TABLE " + TABLE_USERS;
        strcreate += " "
        strcreate = "CREATE TABLE " + TABLE_USERS;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
