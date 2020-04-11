package com.examble.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterView extends RecyclerView.Adapter<AdapterView.AndHolder> {
    public AdapterView(OnItem onItem) {
        this.onItem = onItem;
    }
    private OnItem onItem;

    ArrayList<Contact> contactList = new ArrayList<>();
    interface OnItem {
        void Onclick(Contact contact,int postion);
        //عاوزة اعمل امبلمنتيشن هروح الاكتفيتي اللي عاوزة اعمل فيها
        void delete(Contact contact, int postion);

        }



    @NonNull
    @Override
    public AndHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_items, parent, false);
        return new AndHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AndHolder holder, final int position) {
        holder.onBind(contactList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //انا عاوزة اضغط ع ايتم تعمل اكشن معينc
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class AndHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;


        public AndHolder(@NonNull View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        void onBind(Contact contact) {
            tvName.setText(contact.getName());
            tvNumber.setText(contact.getNumber());
        }

    }
}