package pl.oskarpolak.bookshare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OskarPraca on 2017-01-17.
 */

public class SQLiteData extends SQLiteOpenHelper{

    private Context con;

    public SQLiteData(Context context) {

        super(context, "bookshare.db", null, 1);
        con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL("create table users(" +
                   "id integer primary key autoincrement," +
                   "login text," +
                   "password text);"
           );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  void addUser(String login, String password) {
          if(!isUserExist(login)){
               SQLiteDatabase database = getWritableDatabase();
               ContentValues contentValues = new ContentValues();
               contentValues.put("login", login);
               contentValues.put("password", password);
               database.insertOrThrow("users", null, contentValues);
          }else{
            Utils.createSimpleDialog(con, "Błąd", "Login jest już zajęty");
          }
    }

    public boolean checkPassword(String login, String password){
         SQLiteDatabase database = getReadableDatabase();
         Cursor cursor = database.query("users", new String[]{"login, password"}, "login=?", new String[]{login}, null, null, null,null);
         if(cursor.getCount() != 0) {
              if(password.equals(cursor.getString(1))) {
                 return true;
              }else{
                  Utils.createSimpleDialog(con, "Błąd", "Podano błędne dane!");
                  return false;
              }
         }else {
            Utils.createSimpleDialog(con, "Błąd", "Takie konto nie istnieje!");
             return false;
         }
    }

    public boolean isUserExist(String login){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("users", new String[]{"login"}, "login=?",new String[]{login},null,null,null,null);

        return cursor.getCount() == 0 ? true : false;
    }



}
