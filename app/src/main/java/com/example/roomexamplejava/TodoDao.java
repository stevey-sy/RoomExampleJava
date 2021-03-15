package com.example.roomexamplejava;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Dao 란 Data Access Object 의 약어.
// DB에 접근하는 객체
// 사용 이유: 효율적인 커넥션 관리와 보안성 유지
@Dao
public interface TodoDao {
    @Query("SELECT * FROM Todo")
    List<Todo> getAll();

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);
}
