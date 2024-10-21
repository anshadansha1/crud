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

public class UpdateActivity extends AppCompatActivity {

    //1.Declare
    public EditText etId, etName, etAge, etCourse;
    public Button btnUpdate;
    public DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        //2.initialize
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etCourse = findViewById(R.id.etCourse);
        btnUpdate = findViewById(R.id.btnUpdate);
        dbHelper = new DbHelper(this);

        //3.Set onClick
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStud();
            }
        });

    }

    public void updateStud(){
        int id = Integer.parseInt(etId.getText().toString());
        String name = etName.getText().toString();
        int age = Integer.parseInt(etId.getText().toString());
        String course = etCourse.getText().toString();

        if(name.isEmpty() || age == 0 || course.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        StudentModel studentModel = new StudentModel(name,age,course);
        studentModel.setId(id);
        int result  = dbHelper.updateStudent(studentModel);

        if(result > 0){
            Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Student Not Found with ID : "+id , Toast.LENGTH_SHORT).show();
        }

        finish();

    }
}