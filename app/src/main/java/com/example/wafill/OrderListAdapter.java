package com.example.wafill;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class OrderListAdapter extends BaseAdapter {
    private ArrayList<OrderItem> listData;
    private LayoutInflater layoutInflater;
    public OrderListAdapter(Context aContext, ArrayList<OrderItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.order_list, null);
            holder = new ViewHolder();
            holder.txtSize = (TextView) v.findViewById(R.id.idSize);
            holder.txtPrice = (TextView) v.findViewById(R.id.idPrice);
            holder.txtQuantity = (TextView) v.findViewById(R.id.idQuantity);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.txtSize.setText(Integer.toString(listData.get(position).getmSize()));
        holder.txtPrice.setText(Integer.toString(listData.get(position).getmPrice()));
        holder.txtQuantity.setText(Integer.toString(listData.get(position).getmQuantity()));
        return v;
    }
    static class ViewHolder {
        TextView txtSize;
        TextView txtPrice;
        TextView txtQuantity;

    }
}

//public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {
//    private ArrayList<OrderItem> listData;
//
//    public OrderListAdapter(ArrayList<OrderItem> listData) {
//        this.listData = listData;
//    }
//
//    @NonNull
//    @Override
//    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View productList = layoutInflater.inflate(R.layout.order_list, parent, false);
//        OrderListViewHolder orderListViewHolder = new OrderListViewHolder(productList);
//        return orderListViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final OrderListViewHolder holder, int position) {
//        holder.txtSize.setText(listData.get(position).getmSize());
//        holder.txtPrice.setText("Litre: " + Integer.toString(listData.get(position).getmPrice()));
//        holder.txtQuantity.setText(listData.get(position).getmQuantity());
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size();
//    }
//
//    public class OrderListViewHolder extends RecyclerView.ViewHolder {
//        public TextView txtSize, txtPrice, txtQuantity;
//        public LinearLayout linearLayout;
//
//        public OrderListViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtSize = itemView.findViewById(R.id.idSize);
//            txtPrice = itemView.findViewById(R.id.idPrice);
//            txtQuantity = itemView.findViewById(R.id.idQuantity);
//
//            linearLayout = itemView.findViewById(R.id.orderItemLinearLayout);
//        }
//    }
//}
