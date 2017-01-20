package aviadapps.getfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "userdb.db"; // Database file name

    public static final String TABLE_USERS = "Users";
    public static final String TABLE_COMPANY = "Company";
    private static final String KEY_ID = "_id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_USER = "User Name";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_ADDRESS = "Address";

    String strCreate, strDelete, strCompanyCreate;

    public HelperDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate = "CREATE TABLE " + TABLE_USERS;
        strCreate += " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        strCreate += KEY_NAME + " TEXT, ";
        strCreate += KEY_USER + " TEXT, ";
        strCreate += KEY_PASSWORD + " TEXT";
        strCreate += ");";

        db.execSQL(strCreate); // Database created.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete = "DROP TABLE IF EXISTS " + TABLE_USERS;
        db.execSQL(strDelete); // Database deleted.
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_NAME, user.getUserName());
        values.put(KEY_NAME, user.getAddress());
    }
}
