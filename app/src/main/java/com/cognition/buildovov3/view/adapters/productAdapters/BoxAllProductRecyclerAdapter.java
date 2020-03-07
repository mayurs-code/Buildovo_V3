package com.cognition.buildovov3.view.adapters.productAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
//import com.cognition.buildovov3.api.model.productEntity.construction.all.AllProducts;
import com.cognition.buildovov3.api.model.productEntity.construction.products.Image;
import com.cognition.buildovov3.api.model.productEntity.construction.products.Product;
import com.cognition.buildovov3.service.UserClient;
import com.cognition.buildovov3.values.Constants;
import com.cognition.buildovov3.view.UI.ProductActivity;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoxAllProductRecyclerAdapter extends RecyclerView.Adapter<BoxAllProductRecyclerAdapter.ViewHolder> {
    private static final String TAG = "Recycler Adapter";


    private ArrayList<Product> allProducts = new ArrayList<>();
    private Context mContext;


    public BoxAllProductRecyclerAdapter(ArrayList<Product> allProducts, Context context) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_box, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.fillData(position);
        holder.imageRequest(position);
    }

    void gotoProductActivity(String id, String brandID, String varientID, String mrpID) {
        Intent i = new Intent(mContext, ProductActivity.class);
        i.putExtra("ID", id);
        i.putExtra("brandID", brandID);
        i.putExtra("varientID", varientID);
        i.putExtra("mrpID", mrpID);

        mContext.startActivity(i);
    }


    @Override
    public int getItemCount() {
        int size = (allProducts != null ? allProducts.size() : 0);
        Log.i(TAG, "getItemCount: " + size);
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView productImage;
        TextView productName;
        TextView productMRP;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_Box_Name);
            productMRP = itemView.findViewById(R.id.product_Box_MRP);
            productImage = itemView.findViewById(R.id.product_Box_image);
            parentLayout = itemView.findViewById(R.id.parent_Box_layout);
        }

        public void fillData(int position) {

            Log.i(TAG, "onBindViewHolder: onResponse" + allProducts.size() + " " + position);
            String imageURL = null;
            Integer price = allProducts.get(position).getMRP();
            try {
                productName.setText(allProducts.get(position).getBrand().getName());
            } catch (Exception e) {
                productName.setText("No Brand Found");

                Log.i(TAG, "fillData: Brand Not Found");
            }
            productMRP.setText("₹" + price + "/-");
        }
        public void imageRequest(final int position){
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            UserClient client = retrofit.create(UserClient.class);
            Call <ArrayList<Image>> call=client.getAllConstructionMaterialImage(""+allProducts.get(position).getId());
            call.enqueue(new Callback<ArrayList<Image>>() {
                @Override
                public void onResponse(Call<ArrayList<Image>> call, Response<ArrayList<Image>> response) {

                    ArrayList<Image> image=response.body();
                    setImage(image.get(0));
                }

                @Override
                public void onFailure(Call<ArrayList<Image>> call, Throwable t) {
                    Log.d(TAG, "setImage: PIZZA IMAGE Failed");

                }
            });
        }

        private void setImage(Image image) {

            byte[] decodedString = Base64.decode(image.getImage().split(",")[1], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            productImage.setImageBitmap(decodedByte);
        }
    }
}
