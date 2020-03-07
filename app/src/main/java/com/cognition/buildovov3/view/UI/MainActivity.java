package com.cognition.buildovov3.view.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.productEntity.construction.products.Product;
import com.cognition.buildovov3.api.model.userEntity.UserRequest;
import com.cognition.buildovov3.api.model.userEntity.UserResponse;
import com.cognition.buildovov3.service.UserClient;
import com.cognition.buildovov3.values.Constants;
import com.cognition.buildovov3.view.adapters.productAdapters.BoxAllProductRecyclerAdapter;

import com.google.android.material.navigation.NavigationView;
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
    ArrayList<Product> allProducts;
    BoxAllProductRecyclerAdapter boxAllProductRecyclerAdapter;
    NavigationView sideNavMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        initiators();
        methods();
    }

    private void initiators() {
        sideNavMain = findViewById(R.id.sideNavMain);
        drawerSideNavInitiator();
    }

    private void drawerSideNavInitiator() {
    }

    void sideNavOpener() {
        sideNavMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile_sidebar: {
                        underConstruction();
                        break;
                    }
                    case R.id.order_sidebar: {
                        underConstruction();

                        break;
                    }
                    case R.id.cart_sidebar: {
                        underConstruction();

                        break;
                    }
                    case R.id.pament_sidebar: {
                        underConstruction();

                        break;
                    }
                    case R.id.logout_sidebar: {
                        onBackPressed();
                        break;

                    }
                }
                return false;
            }
        });
    }

    private void methods() {
        tialOnclick();
        sendConstructionMaterialRequest();
        sideNavOpener();

    }

    @SuppressLint("WrongConstant")
    public void openDrawerSideNav(View view) {
        DrawerLayout drawerLayout = findViewById(R.id.mainDrawerLayout);
        drawerLayout.openDrawer(Gravity.START);
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


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    void sendConstructionMaterialRequest() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient client = retrofit.create(UserClient.class);
        Call<ArrayList<Product>> call = client.getAllConstructionMaterials();
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                allProducts = response.body();
                fillProductsConstruction(allProducts);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: GET " + t.getMessage());
                Toast.makeText(context, "ERROR OCCURED GET" + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    void fillProductsConstruction(ArrayList<Product> Products) {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerProductsMain);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        {
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                private int space = 8;

                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.left = space;
                    outRect.right = space;
                    outRect.bottom = space;
                    outRect.top = space;
                }
            });
        }
        boxAllProductRecyclerAdapter = new BoxAllProductRecyclerAdapter(Products, context);
        recyclerView.setAdapter(boxAllProductRecyclerAdapter);


    }

    public void underConstruction() {
        Intent i = new Intent(this, UnderConstruction.class);
        startActivity(i);

    }


}
