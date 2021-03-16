package com.example.roomexamplejava;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AppDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);

        // DB 객체 생성
        // DB 는 백그라운드에서 작업을 하지 않으면 에러가 발생한다.
        // allowMainThreadQueries() 를 사용하면 메인스레드에서 사용 가능
        db = Room.databaseBuilder(application, AppDatabase.class, "todo-db")
                .allowMainThreadQueries()
                .build();
    }

    public void print() {
        Log.d("Log", "hello world");
    }

    public LiveData<List<Todo>> getAll() {
        return db.todoDao().getAll();
    }

    public void insert(Todo todo) {
        db.todoDao().insert(todo);
//                mResultTextView.setText(db.todoDao().getAll().toString());
    }


}
