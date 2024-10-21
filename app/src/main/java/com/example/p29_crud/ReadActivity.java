package com.example.p29_crud;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ReadActivity extends AppCompatActivity {

    //1.Declare
    public TextView tvStudents;
    public DbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        //2.Initialize
        tvStudents = findViewById(R.id.tvStudents);
        dbhelper = new DbHelper(this);

        //3.Display List of Students
        displayStuds();
    }

    public void displayStuds(){
        //4.Read Students to list
        List<StudentModel> studentList = dbhelper.readStudents();
        StringBuilder sb = new StringBuilder();

        //5.Loop through
        for(StudentModel studentModel : studentList){
            sb.append("ID : ").append(studentModel.getId())
                    .append(", Name : ").append(studentModel.getName())
                    .append(", Age : ").append(studentModel.getAge())
                    .append(", Course : ").append(studentModel.getCourse())
                    .append("\n\n");
        }

        //6.Set to TextView
        tvStudents.setText(sb.toString());
    }
}