package com.example.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.security.auth.Subject;

import static com.example.realtimedatabase.FBref.refSub;
import static com.example.realtimedatabase.FBref.refUsers;

public class Info extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvName,lvSubName;
    TextView tvName,tvAdd,tvStuNum,tvMName,tvMNum,tvFName,tvFNum,tvHNum,tvGgrade,tvSsub;
    Spinner spQUAR1;

    List<String> subjectList = new ArrayList<String>();
    List<String> studentList = new ArrayList<String>();
    Users user;
    Grades sub;

    String stName,stMname,stDname,stNum,stMnum,stDnum,stHnum,stAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvAdd=(TextView) findViewById(R.id.tvAdd);
        tvFName=(TextView) findViewById(R.id.tvFName);
        tvFNum=(TextView) findViewById(R.id.tvFNum);
        tvHNum=(TextView) findViewById(R.id.tvHNum);
        tvMName=(TextView) findViewById(R.id.tvMName);
        tvMNum=(TextView) findViewById(R.id.tvMNum);
        tvName=(TextView) findViewById(R.id.tvName);
        tvStuNum=(TextView) findViewById(R.id.tvStuNum);
        lvName=(ListView) findViewById(R.id.lvName);
        lvSubName=(ListView) findViewById(R.id.lvSubName);
        tvSsub=(TextView) findViewById(R.id.tvSsub);
        tvGgrade=(TextView) findViewById(R.id.tvGgrade);
        spQUAR1=(Spinner) findViewById(R.id.spQUAR1);



        //spQUAR1.setOnItemSelectedListener(new ItemChooser());
        /*spQUAR1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                int item = spQUAR1.getSelectedItemPosition();
                if(spQUAR1.getAutofillValue())

            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });*/

        lvName.setOnItemClickListener(this);
        lvName.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvSubName.setOnItemClickListener(this);
        lvSubName.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        spQUAR1=(Spinner)findViewById(R.id.spQUAR1);

        String[] arraySpinner = new String[] {
                "Quarter", "1", "2", "3", "4"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQUAR1.setAdapter(adapter);
        spQUAR1.setOnItemClickListener(this);




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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        if (parent==lvName){
            Toast.makeText(this,studentList.get(pos) , Toast.LENGTH_SHORT).show();
            Query querystu =refUsers.orderByChild("name").equalTo(studentList.get(pos));
            querystu.addListenerForSingleValueEvent(VELstudent);
        }
        else{
           if (lvSubName.equals(parent)){
                Query querysub =refUsers.equalTo(subjectList.get(pos));
                //querysub.addListenerForSingleValueEvent(VELsubject);
                tvSsub.setText(subjectList.get(pos));

        }
        }
    }


    ValueEventListener VELstudent =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    user = data.getValue(Users.class);
                    tvAdd.setText(user.getAddress());
                    tvFName.setText(user.getDname());
                    tvFNum.setText(user.getDnum());
                    tvHNum.setText(user.getHnum());
                    tvMName.setText(user.getMname());
                    tvMNum.setText(user.getMnum());
                    tvName.setText(user.getName());
                    tvStuNum.setText(user.getNum());
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
    };

    public void deleteStu(View view) {
        refUsers.child(tvName.getText().toString()).removeValue();
        tvStuNum.setText("");
        tvAdd.setText("");
        tvHNum.setText("");
        tvFName.setText("");
        tvFNum.setText("");
        tvMName.setText("");
        tvMNum.setText("");
        tvName.setText("");
    }


    /*final ValueEventListener VELsubject = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    subjectList()
                    sub = data.getValue(Grades.class);
                    //tvSsub.setText(sub.getSub());
                    //tvGrade.setText(user.getDname());
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };*/

    public void deleteSub(View view) {
        refUsers.child(tvSsub.getText().toString()).removeValue();
        tvSsub.setText("");
        tvGgrade.setText("");
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

}
