
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    final int ADD_REQUEST_CODE = 1;
    final int  Edit_REQUEST_CODE = 0;
    ArrayList<Contact> contactList = new ArrayList<>();
    AdapterView.OnItem o = new AdapterView.OnItem() {

        @Override
        public void Onclick (Contact contact , int postion ) {

            Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
            intent.putExtra("contact",contact);
            intent.putExtra("o",contact);
            startActivityForResult(intent,Edit_REQUEST_CODE);


        }


        @Override
        public void delete (Contact contact) {
            contactList.remove(contact);
            ArrayList<Contact> List = new ArrayList<>(contactList);
            adapter.submitList(List);
        }
    };
    AdapterView adapter = new AdapterView(o);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        adapter.submitList(contactList);
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
                ArrayList<Contact> List = new ArrayList<>(contactList);
                adapter.submitList(List);
            } else if (requestCode == Edit_REQUEST_CODE && requestCode == Activity.RESULT_OK) {

                contactList.set(data.getIntExtra("position", 0), (Contact) data.getSerializableExtra("contact"));
                ArrayList<Contact> List = new ArrayList<>(contactList);

                adapter.submitList(List);




            }
        }
    }