package com.example.listatelefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.listatelefonica.Key.KEY_CONTACT_ID;
import static com.example.listatelefonica.Key.KEY_CONTACT_ID_EDIT;
import static com.example.listatelefonica.Key.KEY_CONTACT_ID_PROFILE;

public class EditContactActivity extends AppCompatActivity {

    Contact contact;

    Button btnCancel, btnSave;
    EditText txtFirstName, txtLastName, txtMobile, txtPhone;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        init();
        setDate();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditContactActivity.this,ProfileContact.class);
                intent.putExtra(KEY_CONTACT_ID,id);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactHelper helper = new ContactHelper(EditContactActivity.this);
                helper.updateRow(id,txtFirstName.getText().toString(),txtLastName.getText().toString(),txtMobile.getText().toString(),txtPhone.getText().toString());

                Log.d("EditContact-Edit", String.valueOf(txtFirstName));

                Toast.makeText(EditContactActivity.this, "Alteracoes salvas", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditContactActivity.this, ProfileContact.class);
                intent.putExtra(KEY_CONTACT_ID,id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(EditContactActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void init() {
        btnCancel = findViewById(R.id.editContact_btn_Cancle);
        btnSave = findViewById(R.id.editContact_btn_Save);
        txtFirstName = findViewById(R.id.editContact_et_firstName);
        txtLastName = findViewById(R.id.editContact_et_lastName);
        txtMobile = findViewById(R.id.editContact_et_Mobile);
        txtPhone = findViewById(R.id.editContact_et_Phone);


        id = getIntent().getIntExtra(KEY_CONTACT_ID_PROFILE, 0);
    }

    private void setDate()
    {
        if(id != 0)
        {
            Contact contact = new Contact();
            ContactHelper helper = new ContactHelper(this);
            Cursor cursor = helper.selectOne(id);
            cursor.moveToFirst();

            contact.setFirstName(cursor.getString(cursor.getColumnIndex(ContactHelper.col_firstName)));
            contact.setLastName(cursor.getString(cursor.getColumnIndex(ContactHelper.col_lastName)));
            contact.setMobile(cursor.getString(cursor.getColumnIndex(ContactHelper.col_mobile)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContactHelper.col_phone)));


            txtFirstName.setText(contact.getFirstName());
            txtLastName.setText(contact.getLastName());
            txtMobile.setText(contact.getMobile());
            txtPhone.setText(contact.getPhone());

        }
    }
}