package com.example.listatelefonica;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.listatelefonica.Key.KEY_CONTACT_ID;
import static com.example.listatelefonica.Key.KEY_CONTACT_ID;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    Context context;
    ArrayList<Contact> contacts;


    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtFirstName,txtLastName, txtMobile;
        int id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtFirstName = itemView.findViewById(R.id.recyclerViewItem_tv_FirstName);
            this.txtLastName = itemView.findViewById(R.id.recyclerViewItem_tv_LastName);
            this.txtMobile = itemView.findViewById(R.id.recyclerViewItem_tv_Mobile);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,ProfileContact.class);
            intent.putExtra(KEY_CONTACT_ID,id);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Contact contact = this.contacts.get(position);

        holder.txtFirstName.setText(contact.getFirstName());
        holder.txtLastName.setText(contact.getLastName());
        holder.txtMobile.setText(contact.getMobile());
        holder.id = contact.getId();

    }


    @Override
    public int getItemCount() {
        return this.contacts.size();
    }
}
