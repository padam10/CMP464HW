package com.example.hw5;

import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Manages sports database. uses the db helper class as specified in android docs
 * http://developer.android.com/guide/topics/data/data-storage.html#db
 * @author josh
 *
 */
public class dataManager extends SQLiteOpenHelper{
	//table name
	private static final String TABLE_NAME = "sports_db";
	
	//columns
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String DATE = "date";

	//sql statement to create the sports table
	private static final String CREATE_TABLE = "CREATE TABLE "+
			TABLE_NAME+" ("+
			TITLE+" TEXT, "+
			DESCRIPTION+" TEXT, "+
			DATE+" TEXT)";

	/**
	 * pass in the name of the database and version 1
	 * @param context
	 */
	public dataManager(Context context) {
		super(context, "sports_db", null, 1);
	}
	/**
	 * This is called if getWritable/ReadableDatabase is called and there
	 * is no database already
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public void dropTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
		db.close();
	}

	/**
	 * insert the sports json stuff into the database
	 * 1. First get the 'games' json array
	 * 2. get the writable database from this object
	 * 3. loop through each game in the json array and insert each game
	 *    as a record
	 * @param jsSports
	 * @throws JSONException
	 */
	public void insertSports(JSONObject jsSports) throws JSONException{
		JSONArray games = jsSports.getJSONObject("sports_content").
		getJSONObject("games").getJSONArray("game");

		SQLiteDatabase db = this.getWritableDatabase();
		for(int i = 0; i < games.length(); i++){
			ContentValues cv = new ContentValues();
			JSONObject game = games.getJSONObject(i);
			cv.put(TITLE,game.getString(TITLE));
			cv.put(DESCRIPTION,game.getString(DESCRIPTION));
			cv.put(DATE, game.getString(DATE));
			db.insert(TABLE_NAME, null, cv);
		}
		db.close();
	}
	
	public Date getLatestDate(){
		Date date = null;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME,new String[] {DATE}, null, null,null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		db.close();
		return date;
		
	}

}
		// cursor is an iterater  and it iterates through a database query. It works like temp table in mysql.
		// You need an cursor adapter to display 


