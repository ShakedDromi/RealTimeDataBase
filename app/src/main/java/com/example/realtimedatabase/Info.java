package com.example.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Info extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvName,lvSubName;
    TextView tvName,tvAdd,tvStuNum,tvMName,tvMNum,tvFName,tvFNum,tvHNum,tvGrades;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refSub = database.getReference("Subject");
    DatabaseReference refUsers = database.getReference("Users");

    List<String> subjectList = new ArrayList<String>();
    List<String> studentList = new ArrayList<String>();

    String stName,stMname,stDname,stNum,stMnum,stDnum,stHnum,stAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvAdd=(TextView) findViewById(R.id.tv2);
        tvFName=(TextView) findViewById(R.id.tv6);
        tvFNum=(TextView) findViewById(R.id.tv7);
        tvHNum=(TextView) findViewById(R.id.tv8);
        tvMName=(TextView) findViewById(R.id.tv4);
        tvMNum=(TextView) findViewById(R.id.tv5);
        tvName=(TextView) findViewById(R.id.tv);
        tvStuNum=(TextView) findViewById(R.id.tv3);
        tvGrades=(TextView) findViewById(R.id.tvGrades);
        lvName=(ListView) findViewById(R.id.lvName);
        lvSubName=(ListView) findViewById(R.id.lvSubName);

        ArrayAdapter<String> adapterSubjects = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, subjectList);
        adapterSubjects.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lvSubName.setAdapter(adapterSubjects);
        // Read from the database
        refSub.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                subjectList.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String value = data.getValue(String.class);
                    subjectList.add(value);
                }
                ArrayAdapter<String> adp = new ArrayAdapter<String>(Info.this, android.R.layout.simple_dropdown_item_1line, subjectList);
                adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                lvSubName.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        ArrayAdapter<String> adapterStudents = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, subjectList);
        adapterStudents.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lvName.setAdapter(adapterStudents);
        // Read from the database
        refUsers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String value = data.getKey();
                    studentList.add(value);
                }
                ArrayAdapter<String> adp = new ArrayAdapter<String>(Info.this, android.R.layout.simple_dropdown_item_1line, studentList);
                adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                lvName.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void deleteStu(View view) {
        //refUsers.child().removeValue();
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();
        if (st.equals("Enter Data")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (st.equals("Info")){
            Intent si = new Intent(this, Info.class);
            startActivity(si);
        }
        if (st.equals("Sort")){
            Intent si = new Intent(this, Sort.class);
            startActivity(si);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent==lvName.getParent()){

        }
        else{

        }
    }
}
