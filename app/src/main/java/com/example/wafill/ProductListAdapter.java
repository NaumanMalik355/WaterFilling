package com.example.wafill;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    private ArrayList<Products> listData;

    public ProductListAdapter(ArrayList<Products> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View productList = layoutInflater.inflate(R.layout.product_list, parent, false);
        ProductListViewHolder productListViewHolder = new ProductListViewHolder(productList);
        return productListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductListViewHolder holder, int position) {
     //   final MyListData data = listData[position];
        holder.textView.setText(listData.get(position).productName);
        holder.prodictId1.setText(Integer.toString(listData.get(position).bottleSize)+" Litre");
        holder.txtDate.setText(listData.get(position).createdAt);
       // holder.imageView.setImageResource(listData.get(position).imgURL);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Product Name is : " + holder.textView.getText(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView, prodictId1, txtDate;
        public RelativeLayout relativeLayout;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
       //     imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            prodictId1 = itemView.findViewById(R.id.prodictId1);
            txtDate = itemView.findViewById(R.id.txtDate);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutProductList);

        }
    }
}
