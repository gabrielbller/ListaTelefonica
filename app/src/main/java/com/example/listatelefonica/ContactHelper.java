package com.example.listatelefonica;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {

    public static final int DBVersion = 3;
    public static String DBName = "DB_Contacts";
    public static String tableName = "contacts";
    public static String col_contactId = "contact_id";
    public static String col_firstName = "first_name";
    public static String col_lastName = "last_name";
    public static String col_mobile = "mobile";
    public static String col_phone = "phone";

    SQLiteDatabase database;

    public ContactHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + " ( " + col_contactId + " INTEGER PRIMARY KEY AUTOINCREMENT," + col_firstName + " VARCHAR(30)," + col_lastName + " VARCHAR(30)," + col_mobile + " VARCHAR(14)," + col_phone + " VARCHAR(14));";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName + ";");
        onCreate(sqLiteDatabase);
    }

    public Cursor selectAll() {
        database = getReadableDatabase();
        return database.rawQuery("SELECT * FROM " + tableName, null);
    }

    public void insert(Contact contact) {
        database = getWritableDatabase();
        database.execSQL("INSERT INTO " + tableName + "(" + col_firstName + "," + col_lastName + "," + col_mobile + "," + col_phone + ")" +
                "VALUES('" + contact.getFirstName() + "'  ,'" + contact.getLastName() + "','" + contact.getMobile() + "','" + contact.getPhone() + "');");
    }

    public Cursor selectOne(int id)
    {
        database = getReadableDatabase();
        return database.rawQuery("SELECT * FROM "+tableName+" WHERE "+col_contactId+"="+id+";",null);
    }

    public void deleteRow(int id)
    {
        database = getWritableDatabase();
        database.execSQL("DELETE FROM " +tableName+ " WHERE " +col_contactId+ " = " +id+ ";");
    }

    public void updateRow(int id, String firstName, String lastName, String mobile, String phone)
    {
        database = getWritableDatabase();
        database.execSQL("UPDATE " + tableName + " SET " + col_firstName + " = '" +firstName+ "', " + col_lastName + " = '" +lastName+  "', " + col_mobile + " = '" +mobile+ "', " + col_phone + " = '" +phone+  "' WHERE " +col_contactId+ "= '" +id+ "' ;");
    }
}
