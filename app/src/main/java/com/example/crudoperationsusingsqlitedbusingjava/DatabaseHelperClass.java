package com.example.crudoperationsusingsqlitedbusingjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper
{


    private static final int DATABASE_VERSION=1;

    // DB  name
    private static final String DATABASE_NAME="Student DataBase";
    // Database Table name

    private static final String TABLE_NAME="students";



    // Table Coulmns Name
    private static final String ID="_id";
    private static final String NAME="name";
    private static final String EMAIL="email";
    private SQLiteDatabase sqLiteDataBase;

    //Querry for creating Table
    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+NAME+" TEXT, "+EMAIL+" TEXT )";

    // constructor

    public DatabaseHelperClass(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    // create again after drop of table
    onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student)
    {
        sqLiteDataBase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, student.getStudent_name());
        contentValues.put(DatabaseHelperClass.EMAIL, student.getStudent_mail());
        //Insert INTO TABLE_NAME(student.getStudent_name(),student.getStudent_mail());
        sqLiteDataBase.insert(DatabaseHelperClass.TABLE_NAME,null,contentValues);
        sqLiteDataBase.close();

    }

    public List<Student> getStudentsList()
    {
        String sql="select * from "+TABLE_NAME;
        sqLiteDataBase=this.getReadableDatabase();
        List<Student> studentList=new ArrayList<>();
        Cursor cursor=sqLiteDataBase.rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            do {
                System.out.println("--cursor--"+cursor.toString());
                System.out.println("--cursor0--"+cursor.getInt(0));
                System.out.println("--cursor1--"+cursor.getString(1));
                System.out.println("--cursor2--"+cursor.getString(2));
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                studentList.add(new Student(id,name, email));
            } while (cursor.moveToNext());
        }
        cursor.close();



        return studentList;
    }

    // update students record
    public void UpdateStudents(Student student)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME,student.getStudent_name());
        contentValues.put(DatabaseHelperClass.EMAIL,student.getStudent_mail());
        sqLiteDataBase=this.getWritableDatabase();
        sqLiteDataBase.update(TABLE_NAME,contentValues,ID + " = ?" ,new String[] {String.valueOf(student.getId())});
    }

    // delete a student record

    public void delete_student_record(int  id)
    {
        ContentValues contentValues=new ContentValues();
        sqLiteDataBase=this.getWritableDatabase();

        sqLiteDataBase.delete(TABLE_NAME,ID + " = ?",new String[]{String.valueOf(id)});
    }

    public boolean search_record()
    {

        return false;
    }
}
