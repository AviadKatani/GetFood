/**
 * This class is neccesary for the Database use and contains methods for add / retrieve information from DB.
 */
package aviadapps.getfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HelperDB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "userdba.db"; // Database file name
    public static final String TABLE_USERS = "Users";
    public static final String TABLE_COMPANY = "Company";
    // All of the database keys:
    private static final String KEY_ID = "_id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_ADDRESS = "Address";
    public static final String KEY_USER = "UserName";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_HISTORY = "History";
    // Company keys
    public static final String KEY_MENU = "Menu";
    private String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate = "CREATE TABLE " + TABLE_USERS + " (";
        strCreate += KEY_ID + " INTEGER PRIMARY KEY, ";
        strCreate += KEY_NAME + " TEXT, ";
        strCreate += KEY_ADDRESS + " TEXT, ";
        strCreate += KEY_USER + " TEXT, ";
        strCreate += KEY_EMAIL + " TEXT, ";
        strCreate += KEY_PHONE + " TEXT, ";
        strCreate += KEY_PASSWORD + " TEXT, ";
        strCreate += KEY_HISTORY + " TEXT" + ")";
        db.execSQL(strCreate); // Users database created.

        strCreate = "CREATE TABLE " + TABLE_COMPANY + " (";
        strCreate += KEY_ID + " INTEGER PRIMARY KEY, ";
        strCreate += KEY_NAME + " TEXT, ";
        strCreate += KEY_EMAIL + " TEXT, ";
        strCreate += KEY_PHONE + " TEXT, ";
        strCreate += KEY_MENU + " TEXT" + ")";
        db.execSQL(strCreate); // Company database created
        db.execSQL("INSERT INTO Company (_id,'Name','Email','Phone','Menu') Values (1,'Aroma','aviadkatani@gmail.com', '050-743-2010', 'Rogalah - 2.5₪, Fruit Juice - 2₪, Chocholate - 2₪, Coffe - 2₪, Shoko - 2.5₪, Sandwich - 5₪')");
        db.execSQL("INSERT INTO Company (_id,'Name','Email','Phone','Menu') Values (2,'Pizza Hut','aviadkatani@gmail.com', '050-743-2010', 'Small Pizza - 15₪, Normal Pizza - 20₪, Big Pizza - 30₪, Huge Pizza - 40₪, Salad - 3₪')");
        db.execSQL("INSERT INTO Company (_id,'Name','Email','Phone','Menu') Values (3,'Dominos','aviadkatani@gmail.com', '050-743-2010', 'Small Pizza - 10₪, Normal Pizza - 25₪, Big Pizza - 25₪, Huge Pizza - 30₪, Salad - 4₪')");
        db.execSQL("INSERT INTO Company (_id,'Name','Email','Phone','Menu') Values (4,'McDonalds','aviadkatani@gmail.com', '050-743-2010', 'McRoyal - 5₪, McKebab - 8₪, Coca-Cola - 7₪, Hamburger - 4₪, Small Chips - 4₪, Big Chips - 5₪')");
        db.execSQL("INSERT INTO Company (_id,'Name','Email','Phone','Menu') Values (5,'BBB','aviadkatani@gmail.com', '050-743-2010', 'Small Burger - 3₪, Normal Burger - 5₪, Big Burger - 10₪, Huge Burger - 15₪, Chips - 10₪, Cola - 8₪')");

    }

    // TODO: Create a menu database which contains id, foodName, foodPrice -- should be enough for HashMap.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete = "DROP TABLE IF EXISTS " + TABLE_USERS;
        db.execSQL(strDelete); // Database deleted.
        strDelete = "DROP TABLE IF EXISTS " + TABLE_COMPANY;
        db.execSQL(strDelete);
        onCreate(db);
    }

    public String getHistory(String userName) {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE `UserName` = '" + userName + "'";
        Cursor cursor = db.rawQuery(query, null);
        String b = "Not found";
        if(!cursor.moveToFirst()) cursor.moveToFirst();
        System.out.println(cursor.getCount());
        if(cursor.getCount() == 0) return b;
        else System.out.println(cursor.getString(7) + "" + cursor.getString(6) +  "" + cursor.getString(5) +  "" + cursor.getString(4) +  "" + cursor.getString(3) +  "" + cursor.getString(2) +  "" + cursor.getString(1) +  " THIS IS ALL"); return cursor.getString(7);
    }

    public void addHistory(String historyName, String userName) {
        db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        System.out.print("History to add: " + historyName);
        value.put("History", historyName);
        System.out.print("Values are: " + value.toString());
        db.update(TABLE_USERS, value, KEY_ID + "=" + 0, null);
        //db.insert(TABLE_USERS, null, value);
        db.close();
    }


    public void addUser(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASSWORD, user.getUserPassword());
        values.put(KEY_USER, user.getUserName());
        values.put(KEY_EMAIL, user.getEmailAddress());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_ADDRESS, user.getAddress());
        // Insert to database
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // This method requires a user id as parameter and returns the User credentials.
    public User getUser(int id) {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                KEY_NAME, KEY_USER, KEY_EMAIL, KEY_PHONE, KEY_PASSWORD},
                KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        // Calling the user class constructor to create the user, then return it.
        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6));
        return user;
    }

    // This method returns the number of users in the Database.
    public int getUserCount() {
        String countQuery = "SELECT * FROM" + TABLE_USERS;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // This method requires a user as parameter and delete it's entry in the Database.
    public void deleteUser(User user) {
        db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?", new String[] {String.valueOf(user.getUserName())});
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();

        String query = "SELECT * FROM" + TABLE_USERS;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setAddress(cursor.getString(2));
                user.setUserName(cursor.getString(3));
                user.setEmailAddress(cursor.getString(4));
                user.setPhone(cursor.getString(5));
                user.setUserPassword(cursor.getString(6));
                userList.add(user);
            }
            while (cursor.moveToNext());
        }
        return userList;
    }

    /**
     * Returns the password associated with the specific username. Will be used to check user-login.
     * This method always return something. If user with specific username could not be found, then "Not found" will be returned.
     * @param userName an userName that suppose to be registered.
     * @return The password associated with the username.
     */

    public String searchPass(String userName) {
        db = this.getReadableDatabase();
        String query = "select UserName, Password from " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if(a.equals(userName)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    /**
     * Returns the password associated with the specific emailAddress, which then will be sent to the email address. "Forgot Password" Feature.
     * This method always return something. If user with specific email could not be found, then "Not found" will be returned.
     * @param emailAddress an email address that suppose to be associated with the user.
     * @return The password associated with the email address.
     */

    public String getPassFromEmail(String emailAddress) {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE `Email` = '" + emailAddress + "'";
        Cursor cursor = db.rawQuery(query, null);
        String b = "Not found";
        if(!cursor.moveToFirst()) cursor.moveToFirst();
        if(cursor.getCount() == 0) return b;
        else return cursor.getString(6);
    }

    // Company Database Functions

    public void addCompany(Company company) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, company.getName());
        values.put(KEY_EMAIL, company.getEmailAddress());
        values.put(KEY_PHONE, company.getPhone());
        values.put(KEY_MENU, company.getMenu());
        // Insert to database
        db.insert(TABLE_COMPANY, null, values);
        db.close();
    }

    public int getCompaniesCount() {
        String countQuery = "SELECT * FROM" + TABLE_COMPANY;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public List<Company> getAllCompanies() {
        List<Company> companyList = new ArrayList<Company>();

        String query = "SELECT * FROM " + TABLE_COMPANY;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                Company company = new Company();
                company.setId(Integer.parseInt(cursor.getString(0)));
                company.setName(cursor.getString(1));
                company.setEmailAddress(cursor.getString(2));
                company.setPhone(cursor.getString(3));
                company.setMenu(cursor.getString(4));
                companyList.add(company);
            }
            while (cursor.moveToNext());
        }
        return companyList;
    }
}
