package com.example.crudoperationsusingsqlitedbusingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewStudents extends AppCompatActivity {

    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        rcv=findViewById(R.id.students_view);

        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass=new DatabaseHelperClass(this);
        List<Student> studentsList=databaseHelperClass.getStudentsList();
        System.out.println("Size of List is : "+studentsList.size());
        if(studentsList.size()>0)
        {
            StudentAdoptorClass studentAdoptorClass=new StudentAdoptorClass(studentsList,this);
            rcv.setAdapter(studentAdoptorClass);

        }
        else {
            Toast.makeText(this, "There is no record found of students ::", Toast.LENGTH_SHORT).show();
        }


    }
}