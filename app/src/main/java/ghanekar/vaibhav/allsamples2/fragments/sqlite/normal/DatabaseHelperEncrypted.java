package ghanekar.vaibhav.allsamples2.fragments.sqlite.normal;

import android.content.ContentValues;
import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.DatabaseUtils;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ghanekar.vaibhav.allsamples2.utils.Logger;

public class DatabaseHelperEncrypted extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Normalsqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "UserTable";
    private static final String KEY_ID = SqlitePojo.KEY_ID;
    private static final String KEY_NAME = SqlitePojo.KEY_NAME;
    private static final String KEY_ADDRESS = SqlitePojo.KEY_ADDRESS;
    private static final String KEY_SOME_COLUMN = SqlitePojo.KEY_SOME_COLUMN;
    private char[] password = "password".toCharArray();

    public DatabaseHelperEncrypted(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //CREATE TABLE contacts (
    // contact_id INTEGER PRIMARY KEY,
    // first_name TEXT NOT NULL,
    // last_name TEXT NOT NULL,
    // email text NOT NULL UNIQUE,
    // phone text NOT NULL UNIQUE
    //);

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            if (newVersion > oldVersion) {
                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_SOME_COLUMN + " TEXT");
            }
        } else {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    void insertData(SqlitePojo pojo) {
        try {
            SQLiteDatabase database = this.getWritableDatabase(password);
            Logger.LogVerbose("POJO: " + pojo.toString());
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, pojo.getName());
            Logger.LogVerbose("Addr: " + pojo.getAddress());
            values.put(KEY_ADDRESS, pojo.getAddress());
            database.insert(TABLE_NAME, null, values);
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    SqlitePojo getDataByID(int id) {
        try {
            SQLiteDatabase database = this.getReadableDatabase(password);
            Cursor cursor = null;
            SqlitePojo pojo = null;

            cursor = database.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_ADDRESS}, KEY_ID + "=?",
                    new String[]{String.valueOf(id)},
                    null, null, null);
            if (null != cursor) {
                cursor.moveToFirst();
                pojo = new SqlitePojo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    List<SqlitePojo> getAllData() {
        try {
            SQLiteDatabase database = this.getReadableDatabase(password);
            String query = "select * from " + TABLE_NAME;
            Cursor cursor = database.rawQuery(query, null);
            ArrayList<SqlitePojo> pojoArrayList = new ArrayList<>();

            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    do {
                        SqlitePojo pojo = new SqlitePojo();
                        pojo.setId(Integer.parseInt(cursor.getString(0)));
                        pojo.setName(cursor.getString(1));
                        pojo.setAddress(cursor.getString(2));
                        pojoArrayList.add(pojo);
                    } while (cursor.moveToNext());
                }
            }
            return pojoArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    int getDataCount() {
        try {
            SQLiteDatabase database = this.getReadableDatabase(password);
            String query = "Select * from " + TABLE_NAME;
            Cursor cursor = database.rawQuery(query, null);
            int count = cursor.getCount();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

