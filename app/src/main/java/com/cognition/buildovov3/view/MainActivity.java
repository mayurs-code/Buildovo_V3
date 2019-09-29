package com.cognition.buildovov3.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.productEntity.construction.all.AllProducts;
import com.cognition.buildovov3.api.model.productEntity.construction.search.SearchedProduct;
import com.cognition.buildovov3.api.model.userEntity.UserRequest;
import com.cognition.buildovov3.api.model.userEntity.UserResponse;
import com.cognition.buildovov3.service.UserClient;
import com.cognition.buildovov3.values.Constants;
import com.cognition.buildovov3.view.adapters.BoxAllProductAdapter;
import com.cognition.buildovov3.view.adapters.BoxSearchProductAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "Main Activity Tag";
    Context context;
    private ArrayList<AllProducts> constructionAllProducts;
    private ArrayList<SearchedProduct> constructionSearchProducts;
    BoxAllProductAdapter boxAllProductAdapter;
    BoxSearchProductAdapter boxSearchProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        methods();
        context = this;
    }

    private void methods() {
        tialOnclick();
        sendConstructionMaterialRequest();
        searchBarSearchListner();

    }

    private void tialOnclick() {
        UserResponse response;
        final UserRequest request = new UserRequest("mayur25jan@gmail.com", "firebird");
        Button button = (Button) findViewById(R.id.trialOnclick);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    void searchBarSearchListner()
    {
        MaterialSearchBar searchBar=(MaterialSearchBar)findViewById(R.id.materialSearch);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty())
                {
                sendConstructionMaterialSearchRequest(charSequence.toString());
                }
                else {
                    sendConstructionMaterialRequest();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void sendConstructionMaterialRequest() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient client = retrofit.create(UserClient.class);
        Call<ArrayList<AllProducts>> call = client.getAllConstructionMaterials();
        call.enqueue(new Callback<ArrayList<AllProducts>>() {
            @Override
            public void onResponse(Call<ArrayList<AllProducts>> call, Response<ArrayList<AllProducts>> response) {
                constructionAllProducts = response.body();
                fillProductsConstruction(constructionAllProducts);
            }

            @Override
            public void onFailure(Call<ArrayList<AllProducts>> call, Throwable t) {
                Toast.makeText(context, "ERROR OCCURED", Toast.LENGTH_SHORT).show();


            }
        });
    }

    void sendConstructionMaterialSearchRequest(String search) {
        Log.d(TAG, "onTextChanged: "+search.toString());

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient client = retrofit.create(UserClient.class);
        Call<ArrayList<SearchedProduct>> call = client.getSearchedConstructionMaterials(search);
        call.enqueue(new Callback<ArrayList<SearchedProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchedProduct>> call, Response<ArrayList<SearchedProduct>> response) {
                constructionSearchProducts = response.body();
                for (SearchedProduct product:constructionSearchProducts)
                    Log.d(TAG, "onSearchProd: "+product.getProductName());
                fillSearchedProductsConstruction(constructionSearchProducts);
            }

            @Override
            public void onFailure(Call<ArrayList<SearchedProduct>> call, Throwable t) {
                Toast.makeText(context, "ERROR OCCURED", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getMessage());


            }
        });
    }

    void fillProductsConstruction(ArrayList<AllProducts> allProducts) {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerProductsMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        boxAllProductAdapter = new BoxAllProductAdapter(allProducts, context);
        recyclerView.setAdapter(boxAllProductAdapter);

    }
    void fillSearchedProductsConstruction(ArrayList<SearchedProduct> searchedProducts) {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerProductsMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        boxSearchProductAdapter = new BoxSearchProductAdapter(searchedProducts, context);
        recyclerView.setAdapter(boxSearchProductAdapter);

    }


    void sendLoginRequest(UserRequest request) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);
        Call<UserResponse> call = client.accountLogin(request);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(MainActivity.this, response.body().getUser().getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();

            }
        });
    }
}