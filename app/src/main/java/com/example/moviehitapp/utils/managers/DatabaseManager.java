package com.example.moviehitapp.utils.managers;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public final class DatabaseManager {

    private static RoomDatabase roomDataBase;

    public static synchronized void open(Context context , Class<? extends RoomDatabase> roomDatabaseClass , String db_name) {
        roomDataBase = Room.databaseBuilder(context , roomDatabaseClass , db_name).build();
    }

    public static synchronized void close() {
        if (isOpen()) roomDataBase.close();
    }

    public static synchronized boolean isOpen() {
        return roomDataBase.isOpen();
    }

    public static RoomDatabase getRoomDataBase() {
        return roomDataBase;
    }
}
