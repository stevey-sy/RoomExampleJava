package com.example.roomexamplejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        // View Model 객체 생성
        MainViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);

        viewModel.print();
        // 이전에는 UI controller 에서 db 객체를 생성해서 data 를 불러와서 사용했지만
        // 지금은 View Model 객체를 생성,
        // View Model 객체 에서 db 객체에 접근해서 데이터를 불러온다.
        viewModel.getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

//        // DB 객체 생성
//        // DB 는 백그라운드에서 작업을 하지 않으면 에러가 발생한다.
//        // allowMainThreadQueries() 를 사용하면 메인스레드에서 사용 가능
//        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
//                .allowMainThreadQueries()
//                .build();

        // live data 를 사용해서 db의 내용을 관찰하고
        // 변화가 있으면 자동으로 UI를 갱신한다.
//        db.todoDao().getAll().observe(this, new Observer<List<Todo>>(){
//            @Override
//            public void onChanged(List<Todo> todos) {
//                mResultTextView.setText(todos.toString());
//            }
//        });

        // 버튼 클릭시 db에 insert 한다.
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
//                mResultTextView.setText(db.todoDao().getAll().toString());
                // db 에 데이터 추가하는 부분도
                // view model 객체를 사용해서 db에 접근, 데이터를 추가한다.
                viewModel.insert(new Todo(mTodoEditText.getText().toString()));
            }
        });

    }
}