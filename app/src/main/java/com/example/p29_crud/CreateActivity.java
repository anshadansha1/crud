package com.example.p29_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateActivity extends AppCompatActivity {

    //1.Declare
    private EditText etName, etAge, etCourse;
    private DbHelper dbHelper;
    private Button btnCreateStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        //2.inititalize
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etCourse = findViewById(R.id.etCourse);
        dbHelper = new DbHelper(this);
        btnCreateStudent = findViewById(R.id.btnCreateStudent);

        btnCreateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStudent();
            }
        });

    }

    //3.method createStudent
    public void  createStudent(){
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String course = etCourse.getText().toString();

        if(name.isEmpty() || age == 0 || course.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        StudentModel studentModel = new StudentModel(name,age,course);
        dbHelper.insertStudent(studentModel);
        Toast.makeText(this, "Student Created", Toast.LENGTH_SHORT).show();
        finish();

    }

}