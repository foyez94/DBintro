package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Utils;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL -Structured Query Language
        String CREATE_CONTACT_TABLE=" CREATE TABLE "+Utils.TABLE_NAME+"("+ Utils.KEY_ID+"INTEGER PRIMARY KEY,"+Utils.KEY_NAME+"TEXT,"
                +Utils.KEY_PHONE_NUMBER+"TEXT" +")";

        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);
        //Dropping is deleting the Table!
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);

        // CREATE TABLE AGAIN
        onCreate(db);
    }
        /**
        *CRUD OPERATIONS- CREATE,READ,UPDATE,DELETE
         **/
        //add contact
        public void addContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues value = new ContentValues();
            value.put(Utils.KEY_NAME, contact.getName());
            value.put(Utils.KEY_PHONE_NUMBER,contact.getPhoneNumber());

            //Insert to row
            db.insert(Utils.TABLE_NAME,null,value);
            db.close();//close db connection;



    }
    //Get a contact

    public Contact getContact(int id){
            SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.query(Utils.TABLE_NAME, new String[]{Utils.KEY_ID, Utils.KEY_NAME, Utils.KEY_PHONE_NUMBER},Utils.KEY_ID+ "=?",
                new String[]{String.valueOf(id)}, null, null, null ,null );
        if (cursor != null)
            cursor.moveToFirst();
                    Contact contact=new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
                    return contact;
    }
    public List<Contact> getAllContacts(){
            SQLiteDatabase db =this.getReadableDatabase();
            List<Contact> contactList = new ArrayList<>();
            //Select all contacts
        String selectAll = " SELECT * FROM " + Utils.TABLE_NAME;
        Cursor cursor =db.rawQuery(selectAll, null);
        //L00p through our contacts
        if (cursor.moveToFirst()){

                do {
                    Contact contact = new Contact();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhoneNumber(cursor.getString(2));
                    //add contact object to our contact list
                    contactList.add(contact);
                } while (cursor.moveToNext());

            }
        return contactList;

    }

}
