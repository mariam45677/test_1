package com.examble.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterView extends ListAdapter <Contact, AdapterView.AndHolder> {

    private OnItem onItem;

    static DiffUtil.ItemCallback<Contact> diffCallback=new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getNumber().equals(newItem.getNumber());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getName().equals(newItem.getName()) && oldItem.getNumber().equals(newItem.getNumber());
        }
    };

    protected AdapterView(OnItem onItem) {
        super( diffCallback);
        this.onItem = onItem;
    }

    interface OnItem {
        void Onclick(Contact contact,int postion);
        //عاوزة اعمل امبلمنتيشن هروح الاكتفيتي اللي عاوزة اعمل فيها
        void delete(Contact contact);

        }



    @NonNull
    @Override
    public AndHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_items, parent, false);
        return new AndHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AndHolder holder, final int position) {
        holder.onBind(getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItem.Onclick(getItem(position),position);
                //انا عاوزة اضغط ع ايتم تعمل اكشن معينc
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onItem .delete(getItem(position)) ;
            }
        });

    }



    public class AndHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView img,delete;



        public AndHolder(@NonNull View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            img = itemView.findViewById(R.id.img);
            delete = itemView.findViewById(R.id.img_delete);

        }

        void onBind(Contact contact) {
            tvName.setText(contact.getName());
            img.setImageResource(contact.getImg());

        }

    }
}