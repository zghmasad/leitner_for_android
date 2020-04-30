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

public class TestAdapterIE
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private Cursor mCursor;
    private DataBaseHelperIE mDbHelper;

    public TestAdapterIE(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelperIE(mContext);

    }

    public TestAdapterIE createDatabase() throws SQLException
    {
        //mDbHelper.openDataBase();
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

    public TestAdapterIE open() throws SQLException
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
        //mDb.execSQL("PRAGMA key ='"+DataBaseHelperIE.password+"'");
        return mCursor=mDb.rawQuery(sql, null);
    }

    public String getDefinition(String input)
    {
        String str="";
        try {
            String sql = "SELECT * FROM tbl_idiom WHERE keyword=\""+input+"\"";
            //String sql = "SELECT * FROM tbl_idiom WHERE keyword=\""+input+"\"";
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur.moveToFirst()) {
                do {
                    str=mCur.getString(5);
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
