package com.example.coffeapplication.mvvm.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.views.CartFragment;
import com.example.coffeapplication.mvvm.views.MenuFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    ArrayList<Cart> data;
    Context context;

    public CartAdapter(ArrayList<Cart> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        final Cart temp = data.get(position);

        holder.name.setText(data.get(position).getName());
        holder.counter.setText(data.get(position).getCount());
        holder.cost.setText(data.get(position).getCost());
        holder.syrop.setText(data.get(position).getSyrop());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(ArrayList<Cart> new_data) {
        data.clear();
        data.addAll(new_data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, counter, cost, syrop;
        ImageButton plus, minus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView19);
            cost = itemView.findViewById(R.id.costCartItem);
            syrop = itemView.findViewById(R.id.syropCartItem);
            counter = itemView.findViewById(R.id.textView20);
            plus = itemView.findViewById(R.id.cartPlus);
            minus = itemView.findViewById(R.id.cartMinus);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull List<Object> payloads) {
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
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            Cart cart = ds.getValue(Cart.class);
                            String id = ds.getKey();

                            if (name.equals(cart.getName())) {
                                String count = String.valueOf(Integer.parseInt(cart.getCount()) + 1);
                                Map<String, Object> map = new HashMap<>();
                                map.put("count", count);
                                rootRef.child(id).updateChildren(map);
                                holder.counter.setText(count);
                                update(data);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
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
                                    update(data);
                                } else {
                                    rootRef.child(id).removeValue();
                                    update(data);
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
    }
}