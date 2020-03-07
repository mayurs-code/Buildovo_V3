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
import com.cognition.buildovov3.api.model.productEntity.construction.products.Product;
import com.cognition.buildovov3.service.UserClient;
import com.cognition.buildovov3.values.Constants;
import com.cognition.buildovov3.view.adapters.otherAdapters.ImageSliderProductAdapter;
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
    private Product product;
    private String productName;
    private String productMrp;
    private String productDescription;
    private RecyclerView recyclerView_Product;
    ArrayList<String> imageURLs=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
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
