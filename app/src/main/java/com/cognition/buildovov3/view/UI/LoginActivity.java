package com.cognition.buildovov3.view.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cognition.buildovov3.R;
import com.cognition.buildovov3.api.model.userEntity.UserRequest;
import com.cognition.buildovov3.api.model.userEntity.UserResponse;
import com.cognition.buildovov3.service.UserClient;
import com.cognition.buildovov3.values.Constants;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Login Activity";
    private EditText ip_address_text;
    private TextInputEditText emailLogin;
    private TextInputEditText passwordLogin;
    private TextView forgotPasswordLogin;
    private TextView newUserRegister;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        methods();
    }

    private void initialize() {
        emailLogin = findViewById(R.id.emailTextField_Login);
        ip_address_text = findViewById(R.id.ip_address_text);
        passwordLogin = findViewById(R.id.passwordTextField_Login);
        newUserRegister = findViewById(R.id.newUserTextView_Login);
        forgotPasswordLogin = findViewById(R.id.forgotPasswordTextField_Login);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    private void methods() {
        skipLogin();
        login();
        forgotPassword();
        signup();


    }

    private void changeIP() {
        Log.d(TAG, "changeIP: PIZZA IP 1" + Constants.IP);


        Constants.IP = ip_address_text.getText().toString();
        System.out.println(Constants.IP);

    }

    private void signup() {
        newUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignupActivity();
            }
        });
    }

    private void gotoSignupActivity() {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

    private void forgotPassword() {
        forgotPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawerForgotPass();
            }
        });
    }

    @SuppressLint("WrongConstant")
    public void openDrawerForgotPass() {
        DrawerLayout drawerLayout = findViewById(R.id.loginDrawerLayout);
        drawerLayout.openDrawer(Gravity.END);
    }

    private void login() {
        final String email = emailLogin.getText().toString();
        final String password = passwordLogin.getText().toString();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserLoginCredentials(email, password);


            }
        });
    }

    private void sendUserLoginCredentials(String email, String password) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient client = retrofit.create(UserClient.class);
        Call<UserResponse> call = client.accountLogin(new UserRequest(email, password));
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                gotoMainAvtivity(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gotoMainAvtivity(UserResponse body) {
        Log.d(TAG, "changeIP: PIZZA IP 4" + Constants.IP);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void skipLogin() {
        TextView skipText = (TextView) findViewById(R.id.skip_Login);
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "changeIP: PIZZA IP 3" + Constants.IP);

                gotoMainAvtivity();
            }
        });
    }

    private void gotoMainAvtivity() {
        Log.d(TAG, "changeIP: PIZZA IP 2" + Constants.IP);

        changeIP();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}
