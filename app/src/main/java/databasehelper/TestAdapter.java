package databasehelper;

/**
 * Created by 4li on 1/4/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class TestAdapter
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private Cursor mCursor;
    private DataBaseHelper mDbHelper;

    public TestAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public TestAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public TestAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getCursor(String sql){
        if(mCursor!=null)
            mCursor.close();
        return mCursor=mDb.rawQuery(sql, null);
    }

    public String getTestData(String input)
    {
        String str="";
        input=input.toLowerCase();
        String tableName="";
        if('a'==input.charAt(0)||input.charAt(0)=='b'){
            tableName="e1";
        }
        else if('c'==input.charAt(0)||input.charAt(0)=='d'){
            tableName="e2";
        }
        else if('e'<=input.charAt(0)&&input.charAt(0)<'h'){
            tableName="e3";
        }
        else if('h'<=input.charAt(0)&&input.charAt(0)<'m'){
            tableName="e4";
        }
        else if('m'<=input.charAt(0)&&input.charAt(0)<'q'){
            tableName="e5";
        }
        else if('q'<=input.charAt(0)&&input.charAt(0)<'t'){
            tableName="e6";
        }
        else
            tableName="e7";
        try {
            String sql = "SELECT * FROM "+tableName+" WHERE en=\""+input+"\"";
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur.moveToFirst()) {
                do {
                    str=mCur.getString(2);
                    //Log.w("name:", mCur.getString(0));

                    //Log.w("seq:", mCur.getString(1));
                } while (mCur.moveToNext());


            /*Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToNext();
            }*/

            }
            return str;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
