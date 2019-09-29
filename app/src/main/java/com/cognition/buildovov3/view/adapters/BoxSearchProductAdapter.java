package com.cognition.buildovov3.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.productEntity.construction.search.SearchedProduct;
import com.cognition.buildovov3.values.Constants;

import java.util.ArrayList;

public class BoxSearchProductAdapter extends RecyclerView.Adapter<BoxSearchProductAdapter.ViewHolder> {
    private static final String TAG="Recycler Adapter";


    private ArrayList<SearchedProduct> allProducts =new ArrayList<>();
    private Context mContext;

    public BoxSearchProductAdapter(ArrayList<SearchedProduct> searchedProducts, Context context) {
        this.allProducts = searchedProducts;
        mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_box, parent,   false);
        ViewHolder viewHolder=new ViewHolder(view);
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: onResponse");

        String imageURL=Constants.BASE_IMAGE_URL+ allProducts.get(position).getImages().get(0);
        holder.productName.setText(allProducts.get(position).getProductName());
        holder.productMRP.setText("RS.800/-");


        Glide.with(mContext)
                .load(imageURL)
                .into(holder.productImage);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView productImage ;
        TextView productName ;
        TextView productMRP ;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_Box_Name);
            productMRP = itemView.findViewById(R.id.product_Box_MRP);
            productImage = itemView.findViewById(R.id.product_Box_image);
            parentLayout = itemView.findViewById(R.id.parent_Box_layout);
        }
    }
}
