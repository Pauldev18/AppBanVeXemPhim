package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPhoneNumber, editTextDateOfBirth, editTextAccount, editTextPassword, editTextEmail;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private Button buttonRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Ánh xạ các thành phần từ layout
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        editTextAccount = findViewById(R.id.editTextAccount);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Xử lý sự kiện khi nhấn nút "Register"
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String username = editTextUsername.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String dateOfBirthStr = editTextDateOfBirth.getText().toString();
                String account = editTextAccount.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                int gender = radioButtonMale.isChecked() ? 0 : 1; // 0 cho Nam, 1 cho Nữ
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = null;
                try {
                    dateOfBirth = new Date(dateFormat.parse(dateOfBirthStr).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ApiPhimService.phimService.createAccount(username, phoneNumber, gender,
                        dateOfBirth, account, password, email).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
