package com.example.i_hospital.doctor;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.i_hospital.R;
import com.example.i_hospital.admin.AdminHomeActivity;
import com.example.i_hospital.loginpage.MainActivity;
import com.example.i_hospital.masteradmin.MasterAdminDashBoard;
import com.example.i_hospital.retrofitinstance.RetrofitInstance;
import com.example.i_hospital.retrofitinstance.RetrofitInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorActivity extends AppCompatActivity {
    LinearLayout layout1, layout2, layout3, layout4;
    EditText name,midname,lastname,dob,mob,altmob,gmail,address,qualification,
            specialization,experience,panno,aadharno,username,password,confirmpass;
    Spinner gender,certiticate1,certificate2,certificate3;
    ImageView imgPancard,imgAadharcard,imgPassport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.doctor_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.doctor_name);
        midname = findViewById(R.id.doctor_middle_name);
        lastname = findViewById(R.id.doctor_last_name);
        dob = findViewById(R.id.doctor_birth_date);
        mob = findViewById(R.id.doctor_phone);
        altmob = findViewById(R.id.doctor_alter_number);
        gmail = findViewById(R.id.doctor_email);
        address = findViewById(R.id.doctor_address);
        qualification = findViewById(R.id.doctor_qualification);
        specialization = findViewById(R.id.doctor_specialization);
        experience = findViewById(R.id.doctor_experiance);
        panno = findViewById(R.id.pan_no);
        aadharno = findViewById(R.id.aadhar_number);
        username = findViewById(R.id.doctor_username);
        password = findViewById(R.id.doctor_password);
        confirmpass = findViewById(R.id.doctor_confirm_password);
        // Initialize layouts
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);

        ImageView DoctorBackBtn = findViewById(R.id.DoctorBackBtn);
        DoctorBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to navigate to the next page (similar to toggleLayouts)
    // Method to navigate to the next page
    public void nextPage(View view) {
        // Find the currently visible layout
        LinearLayout currentLayout = layout1;
        if (layout2.getVisibility() == View.VISIBLE) {
            currentLayout = layout2;
        } else if (layout3.getVisibility() == View.VISIBLE) {
            currentLayout = layout3;
        } else if (layout4.getVisibility() == View.VISIBLE) {
            currentLayout = layout4;
        }

        // Determine the next layout
        LinearLayout nextLayout = null;
        if (currentLayout == layout1) {
            nextLayout = layout2;
        } else if (currentLayout == layout2) {
            nextLayout = layout3;
        } else if (currentLayout == layout3) {
            nextLayout = layout4;
        }

        // Show the next layout and hide the current one
        if (nextLayout != null) {
            currentLayout.setVisibility(View.GONE);
            nextLayout.setVisibility(View.VISIBLE);
        } else {
            // Show a toast message when attempting to navigate forward from the last layout
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
            AddDoctorCRUD(DoctorActivity.this);
        }
    }

    public void previousPage(View view) {
        // Find the currently visible layout
        LinearLayout currentLayout = layout1;
        if (layout2.getVisibility() == View.VISIBLE) {
            currentLayout = layout2;
        } else if (layout3.getVisibility() == View.VISIBLE) {
            currentLayout = layout3;
        } else if (layout4.getVisibility() == View.VISIBLE) {
            currentLayout = layout4;
        }

        // Determine the previous layout
        LinearLayout previousLayout = null;
        if (currentLayout == layout2) {
            previousLayout = layout1;
        } else if (currentLayout == layout3) {
            previousLayout = layout2;
        } else if (currentLayout == layout4) {
            previousLayout = layout3;
        }

        if (previousLayout != null) {
            currentLayout.setVisibility(View.GONE);
            previousLayout.setVisibility(View.VISIBLE);
        } else {
            // Show a toast message when attempting to navigate back from the first layout
            Toast.makeText(this, "You are already on the first page", Toast.LENGTH_SHORT).show();

        }
    }

    public void AddDoctorCRUD(Context context) {
        RetrofitInterface retrofitInterface = RetrofitInstance.getSeviceData();

        retrofitInterface.AddDoctorCRUD( "Insert","","","","",name.getText().toString().trim(),
                        midname.getText().toString().trim(),lastname.getText().toString().trim(),dob.getText().toString().trim(),
                        mob.getText().toString().trim(),altmob.getText().toString().trim(),gmail.getText().toString().trim(),
                        address.getText().toString().trim(),qualification.getText().toString().trim(),specialization.getText().toString().trim(),
                        experience.getText().toString().trim(),panno.getText().toString().trim(),aadharno.getText().toString().trim(),username.getText().toString().trim(),
                        password.getText().toString().trim(),confirmpass.getText().toString().trim(),"","","","","","","",""
                        )
                .enqueue(new Callback<Map<String, String>>() {

                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        Log.d("response", response.toString());
                        if (response.isSuccessful()) {
                            if (response.body().get("status").equals("1")) {
                                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Failed to update data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Log.e("YourTag", "API Call Error: " + t.getMessage()); // Log the API call error message
                        Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}