package com.examble.android.reoo;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.examble.android.Contact;
import com.examble.android.db.AppDatabase;

import java.util.List;

public class ContactRepo extends AsyncTask  <Void,Void, List<Contact>> {
    List<Contact> list ;
    private AppDatabase db ;
    //علشان احط الداتا ف الاكتفيتي
    repo repos;
   public interface repo{
       void getContactList (List<Contact> contacts);

    }
    public ContactRepo ( @NonNull AppDatabase database,repo repos){
        //انا كدا بقوله خد الداتا بيز اشتغل بيها انته وطالما انا مش عملالها نل يبقا هيا تمام
        db = database;
        this.repos =repos;

    }

    @Override
    protected List<Contact> doInBackground(Void... voids) {
        //هترجعلي الداتا اللي انا عاوزاها

                list = db.contactDao().getContactsList();
        return list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        //هنا هستعمل الداتا  علشان اقدر استعمل list<contact
        super.onPostExecute(contacts);
        repos.getContactList(contacts);
    }
}
//هنسنخدم الانترفيس علشان نبعت الداتا للاكتفيتي