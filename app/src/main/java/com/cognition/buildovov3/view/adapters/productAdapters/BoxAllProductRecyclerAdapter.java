package com.cognition.buildovov3.view.adapters.productAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.productEntity.construction.all.AllProducts;
import com.cognition.buildovov3.values.Constants;
import com.cognition.buildovov3.view.UI.ProductActivity;

import java.util.ArrayList;

public class BoxAllProductRecyclerAdapter extends RecyclerView.Adapter<BoxAllProductRecyclerAdapter.ViewHolder> {
    private static final String TAG="Recycler Adapter";


    private ArrayList<AllProducts> allProducts =new ArrayList<>();
    private Context mContext;


    public BoxAllProductRecyclerAdapter(ArrayList<AllProducts> allProducts, Context context) {
        this.allProducts = allProducts;
        mContext = context;


    }
/*    int getProductBrandSize(ArrayList<AllProducts> allProducts){
        int datasetSize=0;
        for (AllProducts product:allProducts){
            for(MRP mrp:product.getMRP()){
                datasetSize++;
            }
        }
        return datasetSize;
    }*/


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_box, parent,   false);
        ViewHolder viewHolder=new ViewHolder(view);
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: onResponse"+allProducts.size()+" "+position);

        String imageURL= null;
        Integer price=allProducts.get(position).getMRP();
        try {
            imageURL = Constants.BASE_IMAGE_URL+ allProducts.get(position).getBrand().getImages().get(0);
            holder.productName.setText(allProducts.get(position).getBrand().getBrand());

        } catch (NullPointerException e) {
            imageURL = Constants.BASE_IMAGE_URL+ allProducts.get(position).getProduct().getImages().get(0);
            holder.productName.setText(allProducts.get(position).getProduct().getProductName());


        }
        holder.productMRP.setText("₹"+price+"/-");


        Glide.with(mContext)
                .load(imageURL)
                .into(holder.productImage);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    gotoProductActivity(allProducts.get(position).getProduct().getId(),
                            allProducts.get(position).getBrand().getId(),
                            allProducts.get(position).getVarient().getId(),
                            allProducts.get(position).getId());
                } catch (Exception e) {
                    Toast.makeText(mContext, "No Brand Found", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    void gotoProductActivity(String id,String brandID,String varientID,String mrpID)
    {
        Intent i=new Intent(mContext, ProductActivity.class);
        i.putExtra("ID",id);
        i.putExtra("brandID",brandID);
        i.putExtra("varientID",varientID);
        i.putExtra("mrpID",mrpID);

        mContext.startActivity(i);
    }



    @Override
    public int getItemCount() {
        int size=(allProducts!=null?allProducts.size():0);
        Log.i(TAG, "getItemCount: "+size);
        return size;
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
