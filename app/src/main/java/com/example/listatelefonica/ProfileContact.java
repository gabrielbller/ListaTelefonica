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

public class ProfileContact extends AppCompatActivity {

    Button btnDelete, btnEdit;
    TextView txtFirstName, txtLastName, txtMobile, txtPhone;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_contact);

        init();
        setDate();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactHelper helper = new ContactHelper(ProfileContact.this);
                helper.deleteRow(id);

                Log.d("ProfileContact-Delete: ", String.valueOf(id));

                Toast.makeText(ProfileContact.this, "Contato Deletado", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProfileContact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileContact.this, EditContactActivity.class);
                intent.putExtra(KEY_CONTACT_ID_PROFILE,id);

                Log.d("ProfileContact-Edit: ", String.valueOf(id));

                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ProfileContact.this,MainActivity.class);
        startActivity(intent);
    }

    private void init() {
        btnDelete = findViewById(R.id.profileContact_btn_Delete);
        btnEdit = findViewById(R.id.profileContact_btn_Edit);
        txtFirstName = findViewById(R.id.profileContact_tv_firstName);
        txtLastName = findViewById(R.id.profileContact_tv_lastName);
        txtMobile = findViewById(R.id.profileContact_tv_mobile);
        txtPhone = findViewById(R.id.profileContact_tv_Phone);

        id = getIntent().getIntExtra(KEY_CONTACT_ID, 0);
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
