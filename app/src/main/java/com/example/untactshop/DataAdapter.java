package com.example.untactshop;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter
{
    protected static final String TAG = "DataAdapter";

    protected static final String TABLE_NAME = "test";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException
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

    public DataAdapter open() throws SQLException
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

    public List getTableData()
    {
        try
        {
            String sql ="SELECT * FROM " + TABLE_NAME;

            List shopList = new ArrayList();

            shopInfo shop = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                while( mCur.moveToNext() ) {


                    shop = new shopInfo();

                    shop.setStation_name(mCur.getString(0));
                    shop.setShop_name(mCur.getString(1));
                    shop.setRoom_name(mCur.getString(2));
                    shop.setCategory(mCur.getString(3));
                    shop.setX1(mCur.getDouble(4));
                    shop.setY1(mCur.getDouble(5));
                    shop.setX2(mCur.getDouble(6));
                    shop.setY2(mCur.getDouble(7));


                    shopList.add(shop);
                }

            }
            return shopList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

}