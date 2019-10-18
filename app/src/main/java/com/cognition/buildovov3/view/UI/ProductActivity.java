package com.cognition.buildovov3.view.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.productEntity.construction.product.MRP;
import com.cognition.buildovov3.api.model.productEntity.construction.product.ProductResponse;
import com.cognition.buildovov3.service.UserClient;
import com.cognition.buildovov3.values.Constants;
import com.cognition.buildovov3.view.adapters.otherAdapters.ImageSliderProductAdapter;
import com.cognition.buildovov3.view.adapters.productAdapters.BoxSimilarProductAdapter;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";
    private TextView productDescription_Textview;
    private TextView productMrp_Textview;
    private TextView productName_Textview;
    private SliderView sliderView;
    private String ID;
    private String brandID;
    private String varientID;
    private String mrpID;
    private ImageSliderProductAdapter sliderAdapter;
    private ProductResponse product;
    private String productName;
    private String productMrp;
    private String productDescription;
    private RecyclerView recyclerView_Product;
    private BoxSimilarProductAdapter similarProductAdapter;
    ArrayList<String> imageURLs=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initialize();

        methods();
    }

    private void initialize() {
        getIntentExtras();
        productDescription_Textview =findViewById(R.id.productDescription);
        productMrp_Textview=findViewById(R.id.productPrice_Product);
        productName_Textview=findViewById(R.id.productName_Product);
        recyclerView_Product=findViewById(R.id.recyclerProducts_Product);
        sliderView= findViewById(R.id.imageSlider_Product);

    }

    private void getIntentExtras() {
        ID=getIntent().getStringExtra("ID");
        brandID=getIntent().getStringExtra("brandID");
        varientID=getIntent().getStringExtra("varientID");
        mrpID=getIntent().getStringExtra("mrpID");


    }


    private void methods() {
        initializeImageSlider();
        requestProduct();

    }

    private void onResponseGot( ProductResponse product)
    {

        setSliderImages();
        fillRecyclerView();
    }


    private void fillRecyclerView() {
        Log.i(TAG, "fillRecyclerView: WORKING state 1");
        ArrayList<MRP> productsSimilarproduct=new ArrayList<>();
        productsSimilarproduct.addAll(product.getMRP());
        Log.i(TAG, "fillRecyclerView: WORKING state 2"+ productsSimilarproduct.get(0).toString());
        recyclerView_Product.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_Product.setLayoutManager(new GridLayoutManager(this,3));

        similarProductAdapter =new BoxSimilarProductAdapter(productsSimilarproduct,this);
        recyclerView_Product.setAdapter(similarProductAdapter);
    }

    private void setData() {
        productDescription_Textview.setText(productDescription);
        productMrp_Textview.setText(productMrp);
        productName_Textview.setText(productName);
    }

    private void setSliderImages() {
        try {
            for(MRP mrp:product.getMRP())
            {
                if (mrp.getId().equals(mrpID)){
                    productName =mrp.getBrand().getBrand()+"\n"+mrp.getVarient().getVarient();
                    productMrp="Rs."+mrp.getMRP()+"/-";
                    productDescription=mrp.getBrand().getDescription();
                    imageURLs.addAll(mrp.getBrand().getImages());
                    setData();
                    sliderAdapter.notifyDataSetChanged();

                }
            }
        } catch (Exception e) {
            imageURLs.addAll(product.getImages());
            setData();
            sliderAdapter.notifyDataSetChanged();
        }
    }

    private void requestProduct() {

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        UserClient client = retrofit.create(UserClient.class);

        Call<ProductResponse> call=client.getProductConstructionMaterials(ID);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                product=response.body();
                Log.d(TAG, "onResponse: "+product.toString());
                onResponseGot(product);

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t.getMessage());
                product=null;

            }
        });

    }

    private void initializeImageSlider() {
        sliderAdapter =new ImageSliderProductAdapter(this,imageURLs);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }


    public void enlargeDiscription(View view) {
        if(productDescription_Textview.getMaxLines()==3)
        productDescription_Textview.setMaxLines(1000);
        else
            productDescription_Textview.setMaxLines(3);

    }
}
