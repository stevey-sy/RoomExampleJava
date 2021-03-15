package com.example.roomexamplejava;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// @Entity = Room 라이브러리의 entity (하나의 독립체)로 사용하겠다고 선언한다.
@Entity
public class Todo {
    // @PrimaryKey 어노테이션을 사용하면
    // int id 값을 primary key로 사용하겠다는 선언이 된다.
    // autoGenerate 는 auto increment 와 같은 의미이다.
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public Todo(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Todo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
