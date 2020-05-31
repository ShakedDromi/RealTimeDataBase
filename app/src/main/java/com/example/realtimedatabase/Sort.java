package com.example.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.realtimedatabase.FBref.refGrades;
import static com.example.realtimedatabase.FBref.refSub;
import static com.example.realtimedatabase.FBref.refUsers;

public class Sort extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spfieldname,spfieldsub;
    ListView lv;

    List<String> arraySpinnerName=new ArrayList<String>();
    List<String> arraySpinnerSub=new ArrayList<String>();
    List<String> list=new ArrayList<String>();

    String name="", subject="";
    int fieldName=0;
    int fieldSub=0;
    Grades grades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        spfieldname=(Spinner) findViewById(R.id.spfieldname);
        spfieldsub=(Spinner) findViewById(R.id.spfieldsub);
        lv=(ListView) findViewById(R.id.lv);

        ArrayAdapter<String> adaptern = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerName);
        adaptern.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spfieldname.setAdapter(adaptern);
        spfieldname.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapters = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerSub);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spfieldsub.setAdapter(adapters);
        spfieldsub.setOnItemSelectedListener(this);

        refSub.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arraySpinnerSub.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String value = data.getValue(String.class);
                    arraySpinnerSub.add(value);
                }
                ArrayAdapter<String> adp = new ArrayAdapter<String>(Sort.this, android.R.layout.simple_spinner_item, arraySpinnerSub);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spfieldsub.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        refUsers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot2) {
                arraySpinnerName.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot datas : dataSnapshot2.getChildren()) {
                    String value2 = datas.getKey();
                    arraySpinnerName.add(value2);
                }
                ArrayAdapter<String> adp2 = new ArrayAdapter<String>(Sort.this, android.R.layout.simple_spinner_item, arraySpinnerName);
                adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spfieldname.setAdapter(adp2);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }



    public void showbtn(View view) {
        if (!name.equals("")){
            refGrades.child(name).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list.clear();
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                           String value=data.getValue().toString();

                            //grades = data.child(Integer.toString(reva)).child().getValue(Grades.class);
                           // String value = grades.getSub() + ", " + grades.getReva() + ": " + grades.getGrade();
                            list.add(value);
                        }
                        ArrayAdapter<String> adplv = new ArrayAdapter<String>(Sort.this, android.R.layout.simple_dropdown_item_1line, list);
                        adplv.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        lv.setAdapter(adplv);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(spfieldsub.equals(parent)){
            subject=arraySpinnerSub.get(position);
            fieldSub=position;
        }
        else{
            if (spfieldname.equals(parent)){
                name=arraySpinnerName.get(position);
                fieldName=position;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
