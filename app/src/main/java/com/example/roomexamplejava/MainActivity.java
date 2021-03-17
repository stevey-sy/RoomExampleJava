package com.example.roomexamplejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roomexamplejava.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        // Data binding 을 사용해서 UI controller 에서 view 를 분리
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // binding 객체에 해당 activity 의 lifecycle 을 세팅한다.
        // 이렇게 하면 LiveData 가 data 의 변화를 감지해서 binding 객체에 알리고
        // ui 에 새로운 데이터를 표시한다.
        binding.setLifecycleOwner(this);

        // UI controller 에는 ui 관련 코드만 남아있는 상태
        // 이 것들도 data binding 을 사용하면 없앨 수 있다.
//        mTodoEditText = findViewById(R.id.todo_edit);
//        mResultTextView = findViewById(R.id.result_text);

        // View Model 객체 생성
        MainViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);

        // binding 객체에 viewModel 을 세팅한다.
        // 이렇게 되면 binding 객체는 viewModel 을 구독하다가
        // data 에 변화가 생기면 liveData 가 UI 를 갱신하는 구조가 된다.
        binding.setViewModel(viewModel);

        viewModel.print();
        // 이전에는 UI controller 에서 db 객체를 생성해서 data 를 불러와서 사용했지만
        // 지금은 View Model 객체를 생성,
        // View Model 객체 에서 db 객체에 접근해서 데이터를 불러온다.
//        viewModel.getAll().observe(this, todos -> {
//            // data binding 을 사용하면서  UI controller 에서 view 에 직접 접근하지 않는다.
////            mResultTextView.setText(todos.toString());
//            // xml 에서 바로 접근하도록 변경할 수 있다.
////            binding.resultText.setText(todos.toString());
//        });

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
//        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
////                mResultTextView.setText(db.todoDao().getAll().toString());
//                // db 에 데이터 추가하는 부분도
//                // view model 객체를 사용해서 db에 접근, 데이터를 추가한다.
//                // data binding 을 사용하게 되면서 view model 에서 view 로 접근하지 않는다.
////                viewModel.insert(new Todo(mTodoEditText.getText().toString()));
//                // view model 에서 데이터가 바뀌면 view 는 그것을 인지하고 ui 를 바꾼다.
//                viewModel.insert(new Todo(binding.todoEdit.getText().toString()));
//            }
//        });

    }
}