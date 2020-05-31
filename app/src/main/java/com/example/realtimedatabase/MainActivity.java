package com.example.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.realtimedatabase.FBref.refGrades;
import static com.example.realtimedatabase.FBref.refSub;
import static com.example.realtimedatabase.FBref.refUsers;

public class MainActivity extends AppCompatActivity {
    EditText etNAME,etNUM,etMNAME,etDNAME,etHNUM,etMNUM,etDNUM,etADD,etGRADE,etNAME1;
    Spinner spQUAR,spSUB;
    String name,Mname,Dname,num,Mnum,Dnum,Hnum,address,namestu,reva,sub,grade;

    // ArrayList<String> stlst=new <String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNAME=(EditText)findViewById(R.id.etNAME);
        etNUM=(EditText)findViewById(R.id.etNUM);
        etMNUM=(EditText)findViewById(R.id.etMNUM);
        etDNUM=(EditText)findViewById(R.id.etDNUM);
        etMNAME=(EditText)findViewById(R.id.etMNAME);
        etDNAME=(EditText)findViewById(R.id.etDNAME);
        etHNUM=(EditText)findViewById(R.id.etHNUM);
        etADD=(EditText)findViewById(R.id.etADD);

        etGRADE=(EditText)findViewById(R.id.etGRADE);
        etNAME1=(EditText)findViewById(R.id.etNAME1);
        spQUAR=(Spinner)findViewById(R.id.spQUAR);
        spSUB=(Spinner)findViewById(R.id.spSUB);

        String[] arraySpinner = new String[] {
               "Quarter", "1", "2", "3", "4"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQUAR.setAdapter(adapter);



        // Read from the database
        refSub.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                final List<String> subjectList = new ArrayList<String>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String value = data.getValue(String.class);
                    subjectList.add(value);
                }
                ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, subjectList);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSUB.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void fstsub(View view) {
        name=etNAME.getText().toString();
        Mname=etMNAME.getText().toString();
        Dname=etDNAME.getText().toString();
        num=etNUM.getText().toString();
        Mnum=etMNUM.getText().toString();
        Dnum=etDNUM.getText().toString();
        Hnum=etHNUM.getText().toString();
        address=etADD.getText().toString();
        Users user=new Users(name,Mname,Dname,num,Mnum,Dnum,Hnum,address);
        refUsers.child(name).setValue(user);
        Toast.makeText(this,"Student Submited",Toast.LENGTH_LONG).show();
    }

    public void sndsub(View view) {
        namestu=etNAME1.getText().toString();
        reva=spQUAR.getSelectedItem().toString();
        sub=spSUB.getSelectedItem().toString();
        grade=etGRADE.getText().toString();
        Grades grades=new Grades(namestu,reva,sub,grade);
        refGrades.child(namestu).child(reva).child(sub).setValue(grades);
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
