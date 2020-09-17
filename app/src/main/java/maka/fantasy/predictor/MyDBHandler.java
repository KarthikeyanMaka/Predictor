package maka.fantasy.predictor;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PredictDb.db";
    public static final String TABLE_NAME = "Player";
    public static final String COLUMN_ID = "PlayerName";
    public static final String COLUMN_NAME1 = "TeamName";
    public static final String COLUMN_NAME2= "BattingPoints";
    public static final String COLUMN_NAME3 = "BowlPoints";
    public static final String COLUMN_NAME4 = "OtherPoints";
    public static final String COLUMN_NAME5 = "TotalPoints";
    public static final String COLUMN_ID2 = "MatchName";



    //initialize the database
    public MyDBHandler(Context context, String PlayerName,String TeamName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table ="Create Table "+TABLE_NAME+" ("+COLUMN_ID+" Text NOT NULL ,"+COLUMN_NAME1+" Text ,"+COLUMN_NAME2+" Text,"
                +COLUMN_NAME3+" Text, "+COLUMN_NAME4+" Text,"+COLUMN_NAME5+" Text, "+COLUMN_ID2+" Text NOT NULL" +
                ", PRIMARY KEY ("+COLUMN_ID+","+COLUMN_ID2+"))";
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result = "";
        String query = "Select* FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(PlayerModel player) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, player.getID());
        values.put(COLUMN_NAME1, player.TeamName);
        values.put(COLUMN_NAME2, player.BattingPoints);
        values.put(COLUMN_NAME3, player.BowlPoints);
        values.put(COLUMN_NAME4, player.OtherPoints);
        values.put(COLUMN_NAME5, player.TotalPoints);
        values.put(COLUMN_ID2, player.Match);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public PlayerModel findHandler(String PlayerName) {
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_ID + " = " + "'" + PlayerName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PlayerModel player = new PlayerModel();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            player.PlayerName=cursor.getString(0);
            player.TeamName=cursor.getString(1);
            player.BattingPoints=cursor.getString(2);
            player.BowlPoints=cursor.getString(3);
            player.OtherPoints=cursor.getString(4);
            player.TotalPoints=cursor.getString(5);
            player.Match=cursor.getString(6);

            cursor.close();
        } else {
            player = null;
        }
        db.close();
        return player;
    }

    /*public boolean updateHandler(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }*/
}