package com.example.coffeapplication.mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.News;

import java.util.ArrayList;

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
        View view=inflater.inflate(R.layout.item_news, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        final Cart temp = data.get(position);

        holder.text.setText(data.get(position).getName());
        holder.cost.setText(data.get(position).getCost());
        holder.syrop.setText(data.get(position).getSyrop());
        holder.count.setText(data.get(position).getCount());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        TextView text, cost, syrop, count;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            text=(TextView)itemView.findViewById(R.id.textView19);
            cost=(TextView) itemView.findViewById(R.id.costCartItem);
            syrop=(TextView)itemView.findViewById(R.id.syropCartItem);
            count=(TextView) itemView.findViewById(R.id.textView20);
        }
    }
}
