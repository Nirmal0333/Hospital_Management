package com.example.i_hospital.staff;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.i_hospital.R;
import com.example.i_hospital.admin.AdminHomeActivity;
import com.example.i_hospital.retrofitinstance.RetrofitInstance;
import com.example.i_hospital.retrofitinstance.RetrofitInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffActivity extends AppCompatActivity {
    LinearLayout layout1, layout2, layout3;
    EditText sFirstname,sMidname,sLastname,sMobilenumber,sAltnumber,sDateOfBirth,sAge,sGmail,sAddress,
    sDepartment,sQualification,sPancard,sUsername,sPassword,sConfirmpass;
    RadioButton sGender;
    Spinner sRoll,sPancardphot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_staff);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sFirstname = findViewById(R.id.staffFname);
        sMidname = findViewById(R.id.staffMname);
        sLastname = findViewById(R.id.staffLname);
        sMobilenumber = findViewById(R.id.staffMobnumber);
        sAltnumber = findViewById(R.id.staffMobAlternatenumber);
        sDateOfBirth = findViewById(R.id.staffDOB);
        sAge = findViewById(R.id.staffAge);
        sGmail = findViewById(R.id.staffGmail);
        sAddress = findViewById(R.id.staffAddress);
        sDepartment = findViewById(R.id.staffDepartment);
        sQualification = findViewById(R.id.staffQualification);
        sPancard = findViewById(R.id.staffPanNo);
        sUsername = findViewById(R.id.staffUsername);
        sPassword = findViewById(R.id.staffPassword);
        sConfirmpass = findViewById(R.id.staffConfirmPassword);
        ImageView StaffBackimageView = findViewById(R.id.StaffBackimageView);
        StaffBackimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(StaffActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });
        // Initialize layouts
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
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
        }

        // Determine the next layout
        LinearLayout nextLayout = null;
        if (currentLayout == layout1) {
            nextLayout = layout2;
        } else if (currentLayout == layout2) {
            nextLayout = layout3;
        }

        // Show the next layout and hide the current one
        if (nextLayout != null) {
            currentLayout.setVisibility(View.GONE);
            nextLayout.setVisibility(View.VISIBLE);
        } else {
            // Show a toast message when attempting to navigate forward from the last layout
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
            AddStaffCRUD(StaffActivity.this);
        }
    }


    // Method to navigate to the previous page
    // Method to navigate to the previous page
    public void previousPage(View view) {
        // Find the currently visible layout
        LinearLayout currentLayout = layout1;
        if (layout2.getVisibility() == View.VISIBLE) {
            currentLayout = layout2;
        } else if (layout3.getVisibility() == View.VISIBLE) {
            currentLayout = layout3;
        }

        // Determine the previous layout
        LinearLayout previousLayout = null;
        if (currentLayout == layout2) {
            previousLayout = layout1;
        } else if (currentLayout == layout3) {
            previousLayout = layout2;
        }

        // Show the previous layout and hide the current one
        if (previousLayout != null) {
            currentLayout.setVisibility(View.GONE);
            previousLayout.setVisibility(View.VISIBLE);
        } else {
            // Show a toast message when attempting to navigate back from the first layout
            Toast.makeText(this, "You are already on the first page", Toast.LENGTH_SHORT).show();
        }
    }

    public void AddStaffCRUD(Context context) {
        RetrofitInterface retrofitInterface = RetrofitInstance.getSeviceData();

        retrofitInterface.AddStaffCRUD("Insert","","","",sFirstname.getText().toString().trim(),
                        sMidname.getText().toString().trim(),sLastname.getText().toString().trim(),"",sDateOfBirth.getText().toString().trim(),
                        sMobilenumber.getText().toString().trim(),sAltnumber.getText().toString().trim(),sGmail.getText().toString().trim(),"","","",
                        sDepartment.getText().toString().trim(),sQualification.getText().toString().trim(),sAddress.getText().toString().trim(),sPancard.getText().toString().trim(),"","",
                        sUsername.getText().toString().trim(),sPassword.getText().toString().trim(),"","")
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