package com.example.realtimedatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * a class that contains references to Firebase- Database
 */
public class FBref {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference refUsers = FBDB.getReference("Users");
    public static DatabaseReference refSub = FBDB.getReference("Subject");
    public static DatabaseReference refGrades = FBDB.getReference("Grades");
}