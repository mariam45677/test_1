package com.examble.android.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.examble.android.Contact;

import java.util.List;



@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getContactsList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void InsertContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);
}
