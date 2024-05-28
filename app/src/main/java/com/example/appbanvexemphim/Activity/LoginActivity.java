package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Model.LoginReponse;
import com.example.appbanvexemphim.Model.LoginRequest;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView username;
    private TextView password;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = findViewById(R.id.txtUserName);
        password = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest loginRequest = new LoginRequest(username.getText().toString(), password.getText().toString());
                ApiPhimService.phimService.login(loginRequest).enqueue(new Callback<LoginReponse>() {
                    @Override
                    public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                        if(response.isSuccessful() && response.body() != null){
                            LoginReponse loginReponse = response.body();
                            int userID = loginReponse.getUserID();
                            String role = loginReponse.getRole();
                            if(role.equals("User")){
                                ChooseSeat.getInstance().setUserID(userID);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginReponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
