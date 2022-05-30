package com.example.listatelefonica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    TextView txtNoContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setDate();

    }
       private void init()
       {
           floatingActionButton = findViewById(R.id.floatingActionButton);
           recyclerView = findViewById(R.id.contacts);

           floatingActionButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
                   startActivity(intent);
               }
           });
       }

       private void setDate()
       {
           ContactHelper helper = new ContactHelper(this);

           Cursor cursor = helper.selectAll();
           ArrayList<Contact> contacts = new ArrayList<>();
           cursor.moveToFirst();

           for (int i = 1 ; i<= cursor.getCount() ;i++)
           {
               Contact contact = new Contact();
               contact.setId(cursor.getInt(cursor.getColumnIndex(ContactHelper.col_contactId)));
               contact.setFirstName(cursor.getString(cursor.getColumnIndex(ContactHelper.col_firstName)));
               contact.setLastName(cursor.getString(cursor.getColumnIndex(ContactHelper.col_lastName)));
               contact.setMobile(cursor.getString(cursor.getColumnIndex(ContactHelper.col_mobile)));

               contacts.add(contact);
               cursor.moveToNext();
           }


           ContactAdapter adapter = new ContactAdapter(this,contacts);
           recyclerView.setAdapter(adapter);
           recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false));
       }

    @Override
    public void onBackPressed()
    {
        finish();
    }

}