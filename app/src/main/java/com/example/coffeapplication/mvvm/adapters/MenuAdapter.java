package com.example.coffeapplication.mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.models.News;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {
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
}

    class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text, cost;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView5);
            text=(TextView)itemView.findViewById(R.id.textView14);
            cost=(TextView)itemView.findViewById(R.id.textView15);
        }
    }
