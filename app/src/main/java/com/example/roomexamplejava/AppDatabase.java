package com.example.roomexamplejava;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// DB 객체로 사용할 클래스
@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

}
