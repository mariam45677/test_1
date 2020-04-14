package com.examble.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AddContactActivity extends AppCompatActivity {
    private EditText etName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initView();
//بتجبلي ال انتنت اللي ف المين بتندهالي لوجاي اكستر كونتنت هينفذ ده
        if (getIntent().hasExtra("contact")){
            Contact contact = (Contact) getIntent().getSerializableExtra("contact");
            etName.setText(contact.getName());


        }
    }

    void initView() {
        etName = findViewById(R.id.et_name);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = createContact(etName.getText().toString());
                if (contact != null) {
                    Intent intent = new Intent();
                    intent.putExtra("contact", contact);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private Contact createContact(String name) {
        boolean invalid = false;
        if (name == null || name.isEmpty()) {
            invalid = true;
            etName.setError("لا يمكن إضافة مستحدم بدون اسم");
        }
       // if (number == null || number.isEmpty()) {
         //   invalid = true;
           // etNumber.setError("لا يمكن إضافة مستخدم بدون رقم ");

        if (invalid) return null;
        else return new Contact(name);
    }
}
