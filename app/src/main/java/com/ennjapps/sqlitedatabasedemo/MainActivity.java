package com.ennjapps.sqlitedatabasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String[] allFaculties= {"Tom Cruise","Jason Statham","Penelope Cruz","Sunny Leone","Salman Khan","Amitabh Bachchan"};
    private EditText studentName;
    private Spinner faculties;
    private Button addStudent;
    private Button deleteEngineers;
    private ListView list;
    public MyDBAdapter dbAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        studentName = (EditText) findViewById(R.id.student_name);
        faculties = (Spinner) findViewById(R.id.faculties_spinner);
        addStudent = (Button) findViewById(R.id.add_student);
        deleteEngineers = (Button) findViewById(R.id.delete_engineers);
        list = (ListView) findViewById(R.id.student_list);

        MyDBAdapter dbAdapter = new MyDBAdapter(MainActivity.this);
        dbAdapter.open();

        addStudent.setOnClickListener(this);
        deleteEngineers.setOnClickListener(this);

        faculties.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, allFaculties));
        loadList();



    }

    @Override
     public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_student:
                dbAdapter.insertStudent(studentName.getText().toString(), faculties.getSelectedItemPosition() + 1);
                loadList();

                break;
            case R.id.delete_engineers:
                dbAdapter.deleteAllEngineers();
                loadList();



        }
    }

    public void loadList(){
        ArrayList<String>allStudents = new ArrayList<String>();
        allStudents = dbAdapter.selectAllStudents();

        final ArrayAdapter<String> adapter= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,allStudents);
        list.setAdapter(adapter);


    }


}
