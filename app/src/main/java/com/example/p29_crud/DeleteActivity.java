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

public class DeleteActivity extends AppCompatActivity {

    //1.Declare
    public EditText etId;
    public Button btnDelete;
    public DbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        //2,Initialize
        etId = findViewById(R.id.etId);
        btnDelete = findViewById(R.id.btnDelete);
        dbhelper = new DbHelper(this);

        //3.onclick
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStud();
            }
        });

    }

    public void deleteStud() {
        int id = Integer.parseInt(etId.getText().toString());
        int result = dbhelper.deleteStudent(id);


        if(result > 0){
            Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Student Not Found with ID : "+id , Toast.LENGTH_SHORT).show();
        }

        finish();

    }


}