package aviadapps.getfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "userdb.db"; // Database file name
    public static final String TABLE_USERS = "Users";
    // All of the database keys:
    private static final String KEY_ID = "_id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_USER = "User Name";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_PHONE = "Address";

    private String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate = "CREATE TABLE " + TABLE_USERS;
        strCreate += " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        strCreate += KEY_NAME + " TEXT, ";
        strCreate += KEY_USER + " TEXT, ";
        strCreate += KEY_PHONE + " TEXT, ";
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
        values.put(KEY_USER, user.getUserName());
        values.put(KEY_PHONE, user.getPhone());
        // Insert to database
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // This method requires a user id as parameter and returns the User credentials.
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                KEY_NAME, KEY_USER, KEY_PHONE, KEY_PASSWORD},
                KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        // Calling the user class constructor to create the user, then return it.
        User user = new User(cursor.getString(0),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2),cursor.getString(3));
        return user;
    }

    // This method returns the number of users in the Database.
    public int getUserCount() {
        String countQuery = "SELECT * FROM" + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // This method requires a user as parameter and delete it's entry in the Database.
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?", new String[] {String.valueOf(user.getUserName())});
        db.close();
    }
}
