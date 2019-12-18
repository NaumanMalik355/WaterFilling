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

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    private MyListData[] listData;

    public ProductListAdapter(MyListData[] listData) {
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
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        final MyListData data = listData[position];
        holder.textView.setText(listData[position].getDescription());
        holder.prodictId1.setText(listData[position].getSubTitle());
        holder.txtDate.setText(listData[position].getTxtDate());
        holder.imageView.setImageResource(listData[position].getImgId());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + data.getDescription(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView, prodictId1, txtDate;
        public RelativeLayout relativeLayout;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            prodictId1 = itemView.findViewById(R.id.prodictId1);
            txtDate = itemView.findViewById(R.id.txtDate);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutProductList);

        }
    }
}
