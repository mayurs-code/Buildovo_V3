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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.productEntity.construction.product.MRP;
import com.cognition.buildovov3.values.Constants;
import com.cognition.buildovov3.view.UI.ProductActivity;

import java.util.ArrayList;

public class BoxSimilarProductAdapter extends RecyclerView.Adapter<BoxSimilarProductAdapter.ViewHolder> {
    private static final String TAG="RecyclerSimilarAdapter";


    private ArrayList<MRP> similarProducts = new ArrayList<MRP>();
    private Context mContext;

    public BoxSimilarProductAdapter(ArrayList<MRP> similarProds, Context context) {
        this.similarProducts = similarProds;
        Log.i(TAG, "fillRecyclerView: WORKING state 4 " +similarProds.size());

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i(TAG, "fillRecyclerView: WORKING state 5");


        String imageURL= null;
        try {
            imageURL = Constants.BASE_IMAGE_URL+ similarProducts.get(position).getBrand().getImages().get(0);
            String title=similarProducts.get(position).getBrand().getBrand()+"\n"+similarProducts.get(position).getVarient().getVarient();
            holder.productName.setText(title);
            holder.productMRP.setText("Rs."+similarProducts.get(position).getMRP().toString()+"/-");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Glide.with(mContext)
                .load(imageURL)
                .into(holder.productImage);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoProductActivity(similarProducts.get(position).getProduct(),
                        similarProducts.get(position).getBrand().getId(),
                        similarProducts.get(position).getVarient().getId(),
                        similarProducts.get(position).getId());
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
        ((ProductActivity)mContext).finish();
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "fillRecyclerView: WORKING state 3" + similarProducts.size());

        return similarProducts.size();
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
