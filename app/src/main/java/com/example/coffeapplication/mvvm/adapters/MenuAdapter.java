package com.example.coffeapplication.mvvm.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.repositories.CartRepository;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    ArrayList<MenuItem> data;
    Context context;

    public MenuAdapter(ArrayList<MenuItem> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder holder, int position) {
        final MenuItem temp = data.get(position);

        holder.text.setText(data.get(position).getName());
        holder.cost.setText(data.get(position).getCost());
        holder.img.setImageResource(data.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text, cost;
        ImageButton st_btn_plus, ad_btn_plus, ad_btn_minus;
        ConstraintLayout st_menu, ad_menu;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView5);
            text=(TextView)itemView.findViewById(R.id.textView14);
            cost=(TextView)itemView.findViewById(R.id.textView15);
            st_btn_plus =(ImageButton)itemView.findViewById(R.id.addToCart);
            st_menu=(ConstraintLayout)itemView.findViewById(R.id.standartItemMenu);
            ad_menu=(ConstraintLayout)itemView.findViewById(R.id.addedItemMenu);
            ad_btn_plus=(ImageButton)itemView.findViewById(R.id.imageButtonPlus);
            ad_btn_minus=(ImageButton)itemView.findViewById(R.id.imageButtonMinus);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        holder.st_btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.st_menu.setVisibility(View.GONE);
                holder.ad_menu.setVisibility(View.VISIBLE);
                String text = data.get(position).getName();
                String cost = data.get(position).getCost();
                new CartRepository().addToHolder(text, "Без сиропа", cost, 1);
            }
        });

        holder.ad_btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.st_menu.setVisibility(View.VISIBLE);
                holder.ad_menu.setVisibility(View.GONE);
            }
        });

    }
}


