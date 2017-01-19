package aviadapps.getfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "userdb.db"; // Database file name

    public static final String TABLE_USERS = "Users";
    public static final String TABLE_COMPANY = "Company";
    private static final String KEY_ID = "_id";
    public static final String USERNAME = "User Name";
    public static final String NAME = "Name";
    public static final String PHONENUMBER = "Phone";
    public static final String PASSWORD = "Password";

    String strCreate, strDelete, strCompanyCreate;

    public HelperDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate = "CREATE TABLE " + TABLE_USERS;
        strCreate += " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        strCreate += NAME + " TEXT, ";
        strCreate += PHONENUMBER + " TEXT, ";
        strCreate += USERNAME + " TEXT, ";
        strCreate += PASSWORD + " TEXT";
        strCreate += ");";

        db.execSQL(strCreate); // Database created.

        strCompanyCreate = "CREATE TABLE " + TABLE_COMPANY;
        strCompanyCreate += " {" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        strCompanyCreate += NAME + " TEXT, ";
        strCompanyCreate += PHONENUMBER + " TEXT, ";
        strCompanyCreate += USERNAME + " TEXT, ";
        strCompanyCreate += PASSWORD + " TEXT";
        strCompanyCreate += ");";

        db.execSQL(strCompanyCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete = "DROP TABLE IF EXISTS " + TABLE_USERS;
        db.execSQL(strDelete); // Database deleted.
    }
}
