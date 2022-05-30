package com.example.listatelefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    Button btnCancel, btnSave;
    EditText txtFirstName, txtLastName, txtMobile, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        btnCancel = findViewById(R.id.addContact_btn_Cancle);
        btnSave = findViewById(R.id.addContact_btn_Save);
        txtFirstName = findViewById(R.id.addContact_et_firstName);
        txtLastName = findViewById(R.id.addContact_et_lastName);
        txtMobile = findViewById(R.id.addContact_et_Mobile);
        txtPhone = findViewById(R.id.addContact_et_Phone);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ContactHelper helper = new ContactHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.setFirstName(txtFirstName.getText().toString());
                contact.setLastName(txtLastName.getText().toString());
                contact.setMobile(txtMobile.getText().toString());
                contact.setPhone(txtPhone.getText().toString());

                ContactHelper helper = new ContactHelper(AddContactActivity.this);
                helper.insert(contact);

                Toast.makeText(AddContactActivity.this, "Contato adicionado", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(AddContactActivity.this,MainActivity.class);
        startActivity(intent);
    }
}