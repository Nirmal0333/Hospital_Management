package com.example.i_hospital.loginpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.i_hospital.R;
import com.example.i_hospital.admin.AdminHomeActivity;
import com.example.i_hospital.masteradmin.MasterAdminDashBoard;
import com.example.i_hospital.retrofitinstance.RetrofitInstance;
import com.example.i_hospital.retrofitinstance.RetrofitInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Spinner userTypeSpinner;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        userTypeSpinner = findViewById(R.id.userTypeSpinner);
        loginButton = findViewById(R.id.LoginButton);

        String[] userTypes = {"Admin", "Master Admin", "Staff"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userTypes);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        userTypeSpinner.setAdapter(adapter);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLoginUser(MainActivity.this);
            }
        });

    }
    public void CheckLoginUser(Context context) {
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofit().create(RetrofitInterface.class);
        String selectedRole = userTypeSpinner.getSelectedItem().toString();
        retrofitInterface.CheckLoginUser(usernameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim(), selectedRole)
                .enqueue(new Callback<Map<String, String>>() {

                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        if (response.isSuccessful()) {
                            Map<String, String> responseBody = response.body();
                            if (responseBody != null && responseBody.containsKey("status")) {
                                String status = responseBody.get("status");
                                if (status.equals("1")) {
                                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                                    // Check the role and navigate accordingly
                                    if (selectedRole.equals("Master Admin")) {
                                        Intent intent = new Intent(MainActivity.this, MasterAdminDashBoard.class);
                                        startActivity(intent);
                                    } else if (selectedRole.equals("Admin")) {
                                        Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                                        startActivity(intent);
                                    }
                                    finish();
                                } else if (status.equals("2")) {
                                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                                    // Check the role and navigate accordingly
                                    if (selectedRole.equals("Master Admin")) {
                                        Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class); // Open AdminHomeActivity for Admin
                                        startActivity(intent);
                                    } else if (selectedRole.equals("Admin")) {
                                        Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class); // Open AdminHomeActivity for Admin
                                        startActivity(intent);
                                    }
                                    finish();
                                } else {
                                    Toast.makeText(context, "Invalid status received from server", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, "Invalid response from server", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Failed to connect to server", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Log.e("Apierror", "API Call Error: " + t.getMessage(), t);
                        Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }



}