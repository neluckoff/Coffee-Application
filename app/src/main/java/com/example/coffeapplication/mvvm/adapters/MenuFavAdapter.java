package com.example.coffeapplication.mvvm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.models.Orders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuFavAdapter extends RecyclerView.Adapter<MenuFavAdapter.MenuFavViewHolder>{
    ArrayList<MenuItem> data;
    Context context;

    public MenuFavAdapter(ArrayList<MenuItem> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public MenuFavAdapter.MenuFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_fav_menu, parent, false);
        return new MenuFavAdapter.MenuFavViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(ArrayList<MenuItem> new_data) {
        data.clear();
        data.addAll(new_data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuFavAdapter.MenuFavViewHolder holder, int position) {
        final MenuItem temp = data.get(position);

        holder.name.setText(data.get(position).getName());
        holder.cost.setText(data.get(position).getCost());
        holder.syrop.setText(data.get(position).getSyrop());
        holder.img.setImageResource(data.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MenuFavViewHolder extends RecyclerView.ViewHolder {
        TextView name, cost, syrop, counter;
        ImageView img;
        ImageButton plus, minus;

        public MenuFavViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView27);
            cost = itemView.findViewById(R.id.textView28);
            syrop = itemView.findViewById(R.id.textViewS);
            //counter = itemView.findViewById(R.id.menu_elem_counterS);
            img = itemView.findViewById(R.id.imageView6);
            plus = itemView.findViewById(R.id.imageButtonPlusS);
            //minus = itemView.findViewById(R.id.imageButtonMinusS);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MenuFavAdapter.MenuFavViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = data.get(position).getName();
                String syrop = data.get(position).getSyrop();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Cart");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean result = false;
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            Cart cart = ds.getValue(Cart.class);
                            String id = ds.getKey();

                            if (name.equals(cart.getName())) {
                                Toast.makeText(view.getContext().getApplicationContext(), "Добавлено в корзину", Toast.LENGTH_SHORT).show();
                                String count = String.valueOf(Integer.parseInt(cart.getCount()) + 1);
                                Map<String, Object> map = new HashMap<>();
                                map.put("count", count);
                                rootRef.child(id).updateChildren(map);
                                result = true;
                            }
                        }
                        if (!result) {
                            Toast.makeText(view.getContext().getApplicationContext(), "Добавлено в корзину", Toast.LENGTH_SHORT).show();
                            String text = data.get(position).getName();
                            String cost = data.get(position).getCost();
                            DatabaseReference mDataBase = FirebaseDatabase.getInstance("https://coffe-application-default-rtdb.firebaseio.com/").getReference("Cart");
                            Cart cart2 = new Cart(text, data.get(position).getSyrop(), cost, "1");
                            mDataBase.push().setValue(cart2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
