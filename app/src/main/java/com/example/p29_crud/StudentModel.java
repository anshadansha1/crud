package com.example.p29_crud;

public class StudentModel {

    //Fields
    private int id;
    private String name;
    private int age;
    private String course;

    //Constructor
    public StudentModel(String name,int age , String course){
        this.name = name;
        this.age = age;
        this.course = course;
    }

    //Getter and Setter
     public int getId(){
        return id;
     }
     public void setId(int id){
        this.id = id;
     }

     public String getName(){
        return name;
     }
     public void setName(String name){
        this.name = name;
     }

     public int getAge(){
        return age;
     }
     public void setAge(int age){
        this.age = age;
     }

     public String getCourse(){
        return course;
     }
     public void setCourse(String course){
        this.course = course;
     }



}
