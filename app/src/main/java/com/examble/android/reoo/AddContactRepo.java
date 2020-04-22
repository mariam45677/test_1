package com.examble.android.reoo;

import android.os.AsyncTask;

import com.examble.android.Contact;
import com.examble.android.db.AppDatabase;

import java.util.List;

public class AddContactRepo extends AsyncTask <Contact,Void, List<Contact> >{
    private AppDatabase db;
    private  ContactRepo.repo repost;
    public AddContactRepo (AppDatabase database , ContactRepo . repo repos) {
        db = database;
        repost = repos;
    }

    @Override
    protected List<Contact> doInBackground(Contact... contacts) {
        for (Contact c : contacts){
            db.contactDao().InsertContact(c);
        }

        return db.contactDao().getContactsList();
    }
    https://github.com/mariam45677/test_1

    @Override
    protected void onPostExecute(List<Contact> contacts) {

        super.onPostExecute(contacts);
        repost.getContactList(contacts);
    }
}

