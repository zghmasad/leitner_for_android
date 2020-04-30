package litnerdb;

/**
 * Created by 4li on 8/22/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class LinterDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "litnerDatabase";
    private static final String TABLE_NAME = "litnerTable";
    private static final String KEY_ID = "id";
    private static final String KEY_EN = "en";
    private static final String KEY_PERSIAN = "fa";
    private static final String KEY_DAY = "day";
    private static final String KEY_MIN = "min";
    private static final String KEY_BOX = "box";

    public LinterDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EN + " TEXT,"
                + KEY_PERSIAN + " TEXT,"
                + KEY_DAY + " INTEGER,"
                + KEY_MIN + " INTEGER,"
                + KEY_BOX + " INTEGER"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addLitner(LitnerClass litnerObj) {

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE en=\""+litnerObj.English+"\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                return;
                // Adding contact to list
            } while (cursor.moveToNext());
        }

        ContentValues values = new ContentValues();
        values.put(KEY_EN, litnerObj.English);
        values.put(KEY_PERSIAN, litnerObj.Persian);
        values.put(KEY_DAY, litnerObj.Days);
        values.put(KEY_MIN, litnerObj.Minutes);
        values.put(KEY_BOX, litnerObj.Box);



        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        cursor.close();
        db.close(); // Closing database connection
    }
    void editLitner(LitnerClass litnerObj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EN, litnerObj.English);
        values.put(KEY_PERSIAN, litnerObj.Persian);
        values.put(KEY_DAY, litnerObj.Days);
        values.put(KEY_MIN, litnerObj.Minutes);
        values.put(KEY_BOX, litnerObj.Box);


        db.update(TABLE_NAME, values,"id="+litnerObj.Id,null);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    void deleteLitner(LitnerClass litnerObj) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id="+litnerObj.Id,null);
        db.close(); // Closing database connection
    }
    void deleteLitner(int Id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "id="+Id,null);
        db.close(); // Closing database connection
    }

    // code to get the single contact
    /*Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,KEY_NAME, KEY_PH_NO ,KEY_YEAR,KEY_MONTH,KEY_DAY,KEY_HOUR,KEY_MIN}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)));
        // return contact
        return contact;
    }*/
    public String getTranslation(String input){
        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" WHERE EN=\""+input+"\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                return cursor.getString(2);
            } while (cursor.moveToNext());
        }
        db.close();
        return null;
    }
    // code to get all contacts in a list view
    public List<LitnerClass> getAllLitners() {
        List<LitnerClass> contactList = new ArrayList<LitnerClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LitnerClass litnerObj = new LitnerClass();
                litnerObj.Id=Integer.parseInt(cursor.getString(0));
                litnerObj.English=cursor.getString(1);
                litnerObj.Persian=cursor.getString(2);
                litnerObj.Minutes=Integer.parseInt(cursor.getString(3));
                litnerObj.Days=Integer.parseInt(cursor.getString(4));
                litnerObj.Box=Integer.parseInt(cursor.getString(5));
                // Adding contact to list
                contactList.add(litnerObj);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public void deleteLeitner(LitnerClass litnerObject) {
        /*SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(litnerObject.Id) });
        db.close();*/
        deleteLeitner(litnerObject.Id);
    }

    public void deleteLeitner(int litnerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(litnerId) });
        db.close();
    }

    public void deleteLeitner(String english) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_EN + " = ?",
                new String[] { english });
        db.close();
    }

    public List<LitnerClass> getBoxLitners(int box,boolean order) {
        List<LitnerClass> contactList = new ArrayList<LitnerClass>();
        // Select All Query
        String selectQuery;
        if (order)
            selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE box="+box+" order by id DESC";
        else
            selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE box="+box;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LitnerClass litnerObj = new LitnerClass();
                litnerObj.Id=Integer.parseInt(cursor.getString(0));
                litnerObj.English=cursor.getString(1);
                litnerObj.Persian=cursor.getString(2);
                litnerObj.Minutes=Integer.parseInt(cursor.getString(4));
                litnerObj.Days=Integer.parseInt(cursor.getString(3));
                litnerObj.Box=Integer.parseInt(cursor.getString(5));
                // Adding contact to list
                contactList.add(litnerObj);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return contactList;
    }
    // code to update the single contact
    public int updateLeitner(LitnerClass litnerObj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PERSIAN, litnerObj.Persian);
        values.put(KEY_DAY, litnerObj.Days);
        values.put(KEY_MIN, litnerObj.Minutes);
        values.put(KEY_BOX, litnerObj.Box);
        int tempVal=db.update(TABLE_NAME, values, KEY_EN + " = ?",
        new String[] { litnerObj.English });
        db.close();
        // updating row
        return tempVal;
    }

    public int updateTranslation(String english,String persian) {
        SQLiteDatabase db = this.getWritableDatabase();
        //english="h";

        ContentValues values = new ContentValues();
        values.put(KEY_PERSIAN, persian);
        int temp=db.update(TABLE_NAME, values, KEY_EN + " = ?",
                new String[] { english });
        db.close();
        // updating row
        return temp;
    }

    public int updateLeitnerBox(String english){
        //String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE en='"+english+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BOX, 0);//Box number 1
        int tempVal=db.update(TABLE_NAME, values, KEY_EN + " = ?",
                new String[] { english });
        db.close();
        // updating row
        return tempVal;

    }



    // Getting contacts Count
    /*public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }*/

}