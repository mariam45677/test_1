
package com.examble.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    final int ADD_REQUEST_CODE = 1;
    final int  Edit_REQUEST_CODE =1;
    ArrayList<Contact> contactList = new ArrayList<>();
    AdapterView.OnItem o = new AdapterView.OnItem() {

        @Override
        public void Onclick (Contact contact , int postion ) {

            Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
            intent.putExtra("contact",contact);
            intent.putExtra("o",contact);
            startActivityForResult(intent,Edit_REQUEST_CODE);
            // contactList.remove(contact);

        }


        @Override
        public void delete (Contact contact, int position) {
            contactList.remove(contact);

        }
    };
    AdapterView adapter = new AdapterView(o);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        adapter.contactList= contactList;
    }

        void initViews () {
            rv = findViewById(R.id.rv);
            rv.setAdapter(adapter);
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                    startActivityForResult(intent, ADD_REQUEST_CODE);
                }
            });
        }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                contactList.add((Contact) data.getSerializableExtra("contact"));
                adapter.notifyDataSetChanged();
            } else if (requestCode == Edit_REQUEST_CODE && requestCode == Activity.RESULT_OK) {

                contactList.set(data.getIntExtra("position", 0), (Contact) data.getSerializableExtra("contact"));
                adapter.notifyDataSetChanged();

            }
        }
    }