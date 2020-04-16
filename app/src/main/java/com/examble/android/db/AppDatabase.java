package com.examble.android.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.examble.android.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class  AppDatabase extends RoomDatabase {
    public static volatile AppDatabase INSTANCE;
    public abstract ContactDao contactDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext() ,AppDatabase.class,"ContactDatabase.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


