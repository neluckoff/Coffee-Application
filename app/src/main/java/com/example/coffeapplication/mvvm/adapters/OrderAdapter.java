package com.example.coffeapplication.mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    ArrayList<Orders> data;
    Context context;

    public OrderAdapter(ArrayList<Orders> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_history, parent, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderAdapter.OrderViewHolder holder, int position) {
        final Orders temp = data.get(position);

        holder.name.setText(data.get(position).getNames());
        holder.id.setText(data.get(position).getId());
        holder.cost.setText(data.get(position).getCost());
        holder.final_cost.setText(data.get(position).getFinal_cost());
        holder.count.setText(data.get(position).getCount());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView name, cost, final_cost, id, count;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.orderId);
            name = itemView.findViewById(R.id.textView12);
            cost = itemView.findViewById(R.id.textView11);
            final_cost = itemView.findViewById(R.id.finalcost);
            count = itemView.findViewById(R.id.countHistor);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }
}
