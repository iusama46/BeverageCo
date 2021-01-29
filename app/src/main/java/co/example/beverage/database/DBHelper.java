package co.example.beverage.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import co.example.beverage.models.Item;

/**
 * Created by Ussama Iftikhar on 28-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "beverage";
    private static final String TABLE_NAME = "tea_table";
    private static final String KEY_ID = "itemId";
    private static final String NAME = "itemName";
    private static final String DESCRIPTION = "itemDescription";
    private static final String ITEM_PRICE = "itemPrice";
    private static final String IMAGE = "itemImage";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT,"
                + DESCRIPTION + " TEXT," + ITEM_PRICE + " TEXT," + IMAGE + " INTEGER " + ")";
        db.execSQL(sql);
    }


    public boolean insertItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, item.getName());
        values.put(DESCRIPTION, item.getDescription());
        values.put(ITEM_PRICE, item.getPrice());
        values.put(IMAGE, item.getImage());
        boolean createSuccessful = db.insert(TABLE_NAME, null, values) > 0;
        db.close();
        return createSuccessful;
    }

    public boolean updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, item.getName());
        values.put(DESCRIPTION, item.getDescription());
        values.put(ITEM_PRICE, item.getPrice());

        String where = " " + KEY_ID + " = ?";

        String[] whereArgs = {Integer.toString(item.getId())};

        boolean updateSuccessful = db.update(TABLE_NAME, values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }


    public boolean deleteItem(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete(TABLE_NAME, KEY_ID + " ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<Item>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setDescription(cursor.getString(2));
                item.setPrice(cursor.getString(3));
                item.setImage(cursor.getInt(4));

                itemList.add(item);
            } while (cursor.moveToNext());
        }

        return itemList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
}

