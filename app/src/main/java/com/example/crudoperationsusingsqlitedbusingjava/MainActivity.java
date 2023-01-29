package com.example.crudoperationsusingsqlitedbusingjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText st_name,st_email;
    Button add_student,view_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        st_name=findViewById(R.id.edit_text_name);
        st_email=findViewById(R.id.edit_text_mail);
        add_student=findViewById(R.id.add_employ_button);
        view_student=findViewById(R.id.viewEmployButton);

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=st_name.getText().toString();
                String mail=st_email.getText().toString();

                if(name.length() <=0 || mail.length()<=0)
                {
                    Toast.makeText(MainActivity.this, "Field Missing, plz enter vaues in missing fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseHelperClass databaseHelperClass=new DatabaseHelperClass(MainActivity.this);
                    Student student=new Student(name,mail);
                    databaseHelperClass.addStudent(student);
                    Toast.makeText(MainActivity.this, "record added:", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        view_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ViewStudents.class);
                startActivity(intent);
            }
        });
    }
}