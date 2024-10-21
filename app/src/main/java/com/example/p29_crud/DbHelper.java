package com.example.p29_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    //1.DataBase and Version
    private static  final String DATABASE_NAME = "student_db";
    private static final int DATABASE_VERSION = 1;

    //2.Table and Column names
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_COURSE = "course";

    //3.Constructor
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //4.Query to Create table
        //CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT,
        // name TEXT,age INTEGER,course TEXT);

        String query = "CREATE TABLE "+TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_NAME +" TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_COURSE + " TEXT)";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //5.Drop table if already Exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //Create Table Again
        onCreate(sqLiteDatabase);
    }

    //6.Method to Insert student
    public void insertStudent(StudentModel studentModel){

        //Open Database for Writing
        SQLiteDatabase db =  this.getWritableDatabase();

        //Store  Column names and Data
        ContentValues  values =  new ContentValues();
        values.put(COLUMN_NAME , studentModel.getName());
        values.put(COLUMN_AGE,studentModel.getAge());
        values.put(COLUMN_COURSE,studentModel.getCourse());

        //Insert row to table
        db.insert(TABLE_NAME ,null,values);
        db.close();
    }

    //7.Method to Update student
    public  int updateStudent(StudentModel studentModel){
        //Open Database for Writing
        SQLiteDatabase db = this.getWritableDatabase();

        //Store Column names and Data
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,studentModel.getName());
        values.put(COLUMN_AGE,studentModel.getAge());
        values.put(COLUMN_COURSE,studentModel.getCourse());

        //Update  the row where Student Id matches
        return db.update(TABLE_NAME,values,COLUMN_ID +" = ?" , new String[]{String.valueOf(studentModel.getId())});
//        db.close();
    }

    //8.Method to Delete student
    public int deleteStudent(int id){
        //Open Database for Writing
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME,COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
//        db.close();
    }

    //9.Method to Read All Students
    public List<StudentModel> readStudents(){
        List<StudentModel> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        //Loop though results
        if(cursor.moveToFirst()){
            do{
                StudentModel studentModel = new StudentModel(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE)));
                studentModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                //Add Students to list
                studentList.add(studentModel);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;

    }

}
