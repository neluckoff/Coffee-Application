package com.example.coffeapplication.mvvm.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.repositories.MenuFavoriteRepository;
import com.example.coffeapplication.mvvm.repositories.MenusRepository;
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

    @SuppressLint("NotifyDataSetChanged")
    public void update() {
        ArrayList<MenuItem> new_data = new ArrayList<>();
        new_data.addAll(data);
        data.clear();
        data.addAll(new_data);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder holder, int position) {
        final MenuItem temp = data.get(position);

        holder.text.setText(data.get(position).getName());
        holder.cost.setText(data.get(position).getCost());
        holder.img.setImageResource(data.get(position).getImage());

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Cart");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Cart cart = ds.getValue(Cart.class);
                    for (int i=0; i < data.size(); i++) {
                        if (data.get(position).getName() == cart.getName()) {
                            holder.st_menu.setVisibility(View.GONE);
                            holder.ad_menu.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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


        holder.st_btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.st_menu.setVisibility(View.GONE);
                holder.ad_menu.setVisibility(View.VISIBLE);

                String name = data.get(position).getName();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Cart");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean result = false;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Cart cart = ds.getValue(Cart.class);
                            String id = ds.getKey();

                            if (name.equals(cart.getName())) {
                                String count = String.valueOf(Integer.parseInt(cart.getCount()) + 1);
                                Map<String, Object> map = new HashMap<>();
                                map.put("count", count);
                                rootRef.child(id).updateChildren(map);
                                holder.counter.setText(count);
                                result = true;
                            }
                        }
                        if (!result) {
                            String text = data.get(position).getName();
                            String cost = data.get(position).getCost();
                            mDataBase = FirebaseDatabase.getInstance("https://coffe-application-default-rtdb.firebaseio.com/").getReference("Cart");
                            Cart cart2 = new Cart(text, "Без сиропа", cost, "1");
                            mDataBase.push().setValue(cart2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                update();
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
                update();
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
                update();
            }
        });

        Dialog dialog = new Dialog(context);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setContentView(R.layout.item_menu_info);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                ((TextView)dialog.findViewById(R.id.textView2)).setText(holder.text.getText().toString());
                ((ImageView)dialog.findViewById(R.id.imageView4)).setImageResource(data.get(position).getImage());
                ((TextView)dialog.findViewById(R.id.realCost)).setText(holder.cost.getText().toString());

                if (data.get(position).getId() == "bake" || data.get(position).getId() == "season-bake") {
                    ((TextView)dialog.findViewById(R.id.textView4)).setVisibility(View.GONE);
                    ((Spinner)dialog.findViewById(R.id.spinner)).setVisibility(View.GONE);
                } else {
                    ((TextView)dialog.findViewById(R.id.textView4)).setVisibility(View.VISIBLE);
                    ((Spinner)dialog.findViewById(R.id.spinner)).setVisibility(View.VISIBLE);
                }


                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Favorite");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = data.get(position).getName();
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            MenuItem menuItem = ds.getValue(MenuItem.class);
                            if (menuItem.getName().equals(name)) {
                                (dialog.findViewById(R.id.button2)).setVisibility(View.GONE);
                                (dialog.findViewById(R.id.button2del)).setVisibility(View.VISIBLE);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                (dialog.findViewById(R.id.buttonMoreMenu)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        update();
                        dialog.dismiss();
                    }
                });

                (dialog.findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = data.get(position).getName();
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Cart");
                        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    Cart cart = ds.getValue(Cart.class);
                                    String id = ds.getKey();

                                    if (name.equals(cart.getName())) {
                                        (dialog.findViewById(R.id.button3)).setVisibility(View.GONE);
                                        (dialog.findViewById(R.id.button3del)).setVisibility(View.VISIBLE);
                                        String count = String.valueOf(Integer.parseInt(cart.getCount()) + 1);
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("count", count);
                                        rootRef.child(id).updateChildren(map);
                                        Toast.makeText(context.getApplicationContext(), "Теперь в корзине " + count + " элемента", Toast.LENGTH_SHORT).show();
                                    } else {
                                        (dialog.findViewById(R.id.button3)).setVisibility(View.GONE);
                                        (dialog.findViewById(R.id.button3del)).setVisibility(View.VISIBLE);
                                        mDataBase = FirebaseDatabase.getInstance("https://coffe-application-default-rtdb.firebaseio.com/").getReference("Cart");
                                        Cart cart2 = new Cart(((TextView)dialog.findViewById(R.id.textView2)).getText().toString(),
                                                ((Spinner)dialog.findViewById(R.id.spinner)).getSelectedItem().toString(),
                                                ((TextView)dialog.findViewById(R.id.realCost)).getText().toString(), "1");
                                        mDataBase.push().setValue(cart2);
                                        Toast.makeText(context.getApplicationContext(), "Элемент добавлен в корзину", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

                (dialog.findViewById(R.id.button3del)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = data.get(position).getName();
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
                                            (dialog.findViewById(R.id.button3)).setVisibility(View.VISIBLE);
                                            (dialog.findViewById(R.id.button3del)).setVisibility(View.GONE);
                                            Toast.makeText(context.getApplicationContext(), "Теперь в корзине " + count + " элемента", Toast.LENGTH_SHORT).show();
                                        } else {
                                            (dialog.findViewById(R.id.button3)).setVisibility(View.VISIBLE);
                                            (dialog.findViewById(R.id.button3del)).setVisibility(View.GONE);
                                            rootRef.child(id).removeValue();
                                            Toast.makeText(context.getApplicationContext(), "Элемент убран из корзины", Toast.LENGTH_SHORT).show();
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

                (dialog.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)dialog.findViewById(R.id.button2del)).setVisibility(View.VISIBLE);
                        ((Button)dialog.findViewById(R.id.button2)).setVisibility(View.GONE);
                        data.get(position).setFavorite(true);
                        mDataBase = FirebaseDatabase.getInstance("https://coffe-application-default-rtdb.firebaseio.com/").getReference("Favorite");
                        MenuItem menuItem = new MenuItem(data.get(position).getName(), data.get(position).getCost(), data.get(position).getId(),
                                data.get(position).getImage(), data.get(position).isSeason());
                        mDataBase.push().setValue(menuItem);
                        update();
                        Toast.makeText(context.getApplicationContext(), "Добавлено", Toast.LENGTH_SHORT).show();
                    }
                });

                (dialog.findViewById(R.id.button2del)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Favorite");
                        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String name = data.get(position).getName();
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    MenuItem menuItem = ds.getValue(MenuItem.class);
                                    String id = ds.getKey();
                                    if (menuItem.getName().equals(name)) {
                                        (dialog.findViewById(R.id.button2)).setVisibility(View.VISIBLE);
                                        (dialog.findViewById(R.id.button2del)).setVisibility(View.GONE);
                                        rootRef.child(id).removeValue();
                                    }
                                }
                                update();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

                dialog.show();
            }
        });
    }
}


