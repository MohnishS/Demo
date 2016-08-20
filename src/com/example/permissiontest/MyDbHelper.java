package com.example.permissiontest;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper{


private static final String TABLE_NAME = "Products";
private static final String PRODUCT_ID = "Product_Id";
private static final String PRODUCT_NAME = "Product_Name";
private static final String PRODUCT_DESCRIPTION = "Product_Description";
private static final String PRODUCT_PRICE = "Product_Price";
	
public static final int DATABASE_VERSION = 1;
public static final String DATABASE_NAME = "Products.db";
private static final String TEXT_TYPE = " TEXT";
private static final String COMMA_SEP = ",";

private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
												  PRODUCT_ID + " INTEGER PRIMARY KEY," +
												  PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
												  PRODUCT_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
												  PRODUCT_PRICE + " INTEGER" +
												  " )";

private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;


	@SuppressLint("NewApi")
	public MyDbHelper(DemoActivity context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
	}
	
	public boolean insertProduct(int id, String name, String desc, int price)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put(PRODUCT_ID, id);
	      contentValues.put(PRODUCT_NAME, name);
	      contentValues.put(PRODUCT_DESCRIPTION, desc);	
	      contentValues.put(PRODUCT_PRICE, price);
	      db.insert(TABLE_NAME, null, contentValues);
	      return true;
	   }
	
	public ArrayList<String> getAllProducts()
	{
		ArrayList<String> array_list = new ArrayList<String>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
		res.moveToFirst();

		while(res.isAfterLast() == false){
			array_list.add(res.getString(res.getColumnIndex(PRODUCT_NAME)));
			res.moveToNext();
		}
		return array_list;
	}
	
}
