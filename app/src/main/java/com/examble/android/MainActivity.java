
package com.examble.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.examble.android.db.AppDatabase;
import com.examble.android.reoo.AddContactRepo;
import com.examble.android.reoo.ContactRepo;
import com.examble.android.reoo.DeleteContactRepo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton fab ;
    AppDatabase db;
    List<Contact> list;
    Intent intent;

    final int ADD_REQUEST_CODE = 1;
    final int  Edit_REQUEST_CODE = 2;
    AdapterView.OnItem o = new AdapterView.OnItem() {

        @Override
        public void Onclick (Contact contact , int position) {

            Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
            intent.putExtra("contact",contact);
            intent.putExtra("postion", position);
            startActivityForResult(intent,Edit_REQUEST_CODE);


        }


        @Override
        public void delete (Contact contact) {
            //contactList.remove(contact);
            //ArrayList<Contact> List = new ArrayList<>(contactList);
           // adapter.submitList(List);
       //     db.contactDao().deleteContact(contact);
         //   list = db.contactDao().getContactsList();
           // adapter.submitList(list);
            DeleteContactRepo delet = new DeleteContactRepo(db,callback);
            delet.execute(contact);



        }
    };
    AdapterView adapter = new AdapterView(o);
    ArrayList<Contact> contactList = new ArrayList<>();
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(MainActivity.this, AddContactActivity.class);

            startActivityForResult(intent, ADD_REQUEST_CODE);


        }
    };
ContactRepo.repo callback = new ContactRepo.repo() {
    @Override
    public void getContactList(List<Contact> contacts) {
        adapter.submitList(contacts);

    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        db = AppDatabase.getInstance(this);
       ContactRepo repo =  new ContactRepo(db, callback);
       AddContactRepo addcontact =new AddContactRepo(db,callback);
        DeleteContactRepo delet = new DeleteContactRepo(db,callback);

       repo.execute();
       //rr.execute();
        list = db.contactDao().getContactsList();
        adapter.submitList(list);
      //  adapter.submitList(contactList);


        rv.setAdapter(adapter);
        fab.setOnClickListener(onClickListener);
    }

        void initViews () {
            fab = findViewById(R.id.fab_add);
            rv = findViewById(R.id.rv);
        }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
              //  contactList.add((Contact) data.getSerializableExtra("contact"));
              //  ArrayList<Contact> List = new ArrayList<>(contactList);
               // db.contactDao().InsertContact((Contact) data.getSerializableExtra("contact"));
                //list = db.contactDao().getContactsList();
                //adapter.submitList(list);
                AddContactRepo addcontact =new AddContactRepo(db,callback);
                    addcontact.execute((Contact) data.getSerializableExtra("contact"));




            } else if (requestCode == Edit_REQUEST_CODE && requestCode == Activity.RESULT_OK) {

             //   contactList.set(data.getIntExtra("position", 0), (Contact) data.getSerializableExtra("contact"));
               // ArrayList<Contact> List = new ArrayList<>(contactList);
                db.contactDao().updateContact((Contact) data.getSerializableExtra("contact"));
                list = db.contactDao().getContactsList();

                adapter.submitList(list);




            }
        }
    }