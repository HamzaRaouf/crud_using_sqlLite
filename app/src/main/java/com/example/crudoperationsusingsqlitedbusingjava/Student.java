package com.example.crudoperationsusingsqlitedbusingjava;

public class Student {
    private int id;
    private String student_name;
    private String student_mail;


    public Student(String name,String email)
    {
        this.student_name=name;
        this.student_mail=email;
    }

    public Student(int id, String student_name, String student_mail)
    {
        this.id = id;
        this.student_name = student_name;
        this.student_mail = student_mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_mail() {
        return student_mail;
    }

    public void setStudent_mail(String student_mail) {
        this.student_mail = student_mail;
    }
}

