package com.example.crudoperationsusingsqlitedbusingjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdoptorClass extends RecyclerView.Adapter<StudentAdoptorClass.ViewHolde> {
    ViewStudents context;
    List<Student> studentsList;
    DatabaseHelperClass databaseHelperClass;
    public StudentAdoptorClass(List<Student> studentsList, ViewStudents viewStudents)
    {
        this.studentsList=studentsList;
        this.context=viewStudents;
        databaseHelperClass=new DatabaseHelperClass(viewStudents);
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.student_item_list,parent,false);
        ViewHolde viewHolde=new ViewHolde(view);
        return viewHolde;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position)
    {
        int p=position;
        holder.name.setText(studentsList.get(position).getStudent_name());
        holder.mail.setText(studentsList.get(position).getStudent_mail());
        holder.db_id.setText(Integer.toString(studentsList.get(position).getId()));
        System.out.println("----ID-----in Adoptor:"+studentsList.get(position).getId());

        holder.edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String nameString=holder.name.getText().toString();
                String mailString=holder.mail.getText().toString();
                databaseHelperClass.UpdateStudents(new Student(studentsList.get(p).getId(),nameString,mailString));
                studentsList.get(p).setStudent_mail(nameString);
                studentsList.get(p).setStudent_mail(mailString);
                notifyDataSetChanged();

                context.finish();
                context.startActivity(context.getIntent());

            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                databaseHelperClass.delete_student_record(studentsList.get(p).getId());
                studentsList.remove(p);
                notifyDataSetChanged();
                context.finish();
                context.startActivity(context.getIntent());
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder{

        TextView db_id;
        EditText name,mail;
        Button edit,del;

        public ViewHolde(@NonNull View itemView) {
            super(itemView);
            db_id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.st_name);
            mail=itemView.findViewById(R.id.st_eml);
            edit=itemView.findViewById(R.id.edit_button);
            del=itemView.findViewById(R.id.delete_button);

        }
    }
}
