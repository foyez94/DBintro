package com.example.dbintro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHandler db = new DatabaseHandler(this);


        //Insert contacts
        Log.d("Insert: ","Insertin...");
        db.addContact(new Contact("paul", "8766778888"));
        db.addContact(new Contact("John","456677789"));
        db.addContact(new Contact("Rose","4566778888"));
        db.addContact(new Contact("bello","67854366789"));

        //Read this back
        Log.d("Reading; ", "Reading all Contacts....");
        List<Contact> contactList=db.getAllContacts();

        for (Contact c: contactList){
            String log="ID "+c.getId()+", Name:" c.getName()+ ",Phone: "+c.getPhoneNumber()
        }



    }
}