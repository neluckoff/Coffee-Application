package com.example.coffeapplication.mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    ArrayList<MenuItem> data;
    Context context;
    private DatabaseReference mDataBase;

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
        TextView text, cost, counter;
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
            counter=(TextView)itemView.findViewById(R.id.menu_elem_counter);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        ArrayList<Cart> cartArrayList = new ArrayList<>();

        holder.st_btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.st_menu.setVisibility(View.GONE);
                holder.ad_menu.setVisibility(View.VISIBLE);
                String text = data.get(position).getName();
                String cost = data.get(position).getCost();
                mDataBase = FirebaseDatabase.getInstance("https://coffe-application-default-rtdb.firebaseio.com/").getReference("Cart");
                Cart cart = new Cart(text, "Без сиропа", cost, "1");
                mDataBase.push().setValue(cart);
            }
        });

        holder.ad_btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = data.get(position).getName();
                String syrop = data.get(position).getSyrop();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Cart");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            Cart cart = ds.getValue(Cart.class);
                            String id = ds.getKey();

                            if (name.equals(cart.getName())) {
                                if (Integer.parseInt(cart.getCount()) > 1) {
                                    String count = String.valueOf(Integer.parseInt(cart.getCount()) - 1);
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("count", count);
                                    rootRef.child(id).updateChildren(map);
                                    holder.counter.setText(count);
                                } else {
                                    holder.st_menu.setVisibility(View.VISIBLE);
                                    holder.ad_menu.setVisibility(View.GONE);
                                    rootRef.child(id).removeValue();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        holder.ad_btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = data.get(position).getName();
                String syrop = data.get(position).getSyrop();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Cart");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            Cart cart = ds.getValue(Cart.class);
                            String id = ds.getKey();

                            if (name.equals(cart.getName())) {
                                String count = String.valueOf(Integer.parseInt(cart.getCount()) + 1);
                                Map<String, Object> map = new HashMap<>();
                                map.put("count", count);
                                rootRef.child(id).updateChildren(map);
                                holder.counter.setText(count);
                            }
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


