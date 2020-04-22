package com.examble.android.reoo;

import android.os.AsyncTask;

import com.examble.android.Contact;
import com.examble.android.db.AppDatabase;

import java.util.List;

public class DeleteContactRepo extends AsyncTask<Contact,Void, List<Contact>> {
    private AppDatabase db;
    private  ContactRepo.repo repost;
    public DeleteContactRepo  (AppDatabase database , ContactRepo . repo repos) {
        db = database;
        repost = repos;
    }

    @Override
    protected List<Contact> doInBackground(Contact... contacts) {
        for (Contact c : contacts){
            db.contactDao().deleteContact(c);
        }

        return db.contactDao().getContactsList();

    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);
        repost.getContactList(contacts);

    }
}
