package com.cognition.buildovov3.view.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.userEntity.UserRequest;
import com.cognition.buildovov3.api.model.userEntity.UserResponse;
import com.cognition.buildovov3.service.UserClient;
import com.cognition.buildovov3.values.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Login Activity";

    private TextInputEditText emailLogin;
    private TextInputEditText passwordLogin;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        methods();
    }

    private void initialize() {
        emailLogin=findViewById(R.id.emailTextField_Login);
        passwordLogin=findViewById(R.id.passwordTextField_Login);
        buttonLogin=findViewById(R.id.buttonLogin);
    }

    private void methods() {
        skipLogin();
        login();

    }

    private void login() {
        final String email=emailLogin.getText().toString();
        final String password=passwordLogin.getText().toString();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserLoginCredentials(email,password);

            }
        });
    }

    private void sendUserLoginCredentials(String email, String password) {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        UserClient client=retrofit.create(UserClient.class);
        Call<UserResponse> call=client.accountLogin(new UserRequest(email,password));
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d(TAG, "onResponse: "+response.body());
                gotoMainAvtivity(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gotoMainAvtivity(UserResponse body) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    private void skipLogin() {
        TextView skipText=(TextView)findViewById(R.id.skip_Login);
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainAvtivity();
            }
        });
    }

    private void gotoMainAvtivity() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }




}
