package com.example.i_hospital.patients;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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

public class PatientsActivity extends AppCompatActivity {
    LinearLayout layout1, layout2, layout3, layout4;
    EditText pFirstname,pMiddlename,pLastname,pMobileNumber,pAlternateNumber,pDob,pAge,pGmail,pAddress,
    peFirstname,peLastname,peMobilenumber,peRelation,peDoctorname,peDoctorphone,pHealthissue,pMedicalhistory,
    pInsurenceCompany,pInsurenceid,pInsurenceDetails;
    RadioGroup radioGroupSex,currentMedication;

    Spinner pPrescription, pReport,pInsurenceType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patients);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.patient_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize layouts
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);

        pFirstname = findViewById(R.id.patient_name);
        pMiddlename =findViewById(R.id.patient_middle_name);
        pLastname = findViewById(R.id.patient_last_name);
        pMobileNumber = findViewById(R.id.patient_phone);
        pAlternateNumber = findViewById(R.id.patient_alternate_number);
        pDob = findViewById(R.id.patient_DOB);
        pAge = findViewById(R.id.patient_age);
        pGmail = findViewById(R.id.patient_gmail);
        pAddress = findViewById(R.id.patient_address);
        peFirstname = findViewById(R.id.emergency_first_name);
        peLastname = findViewById(R.id.emergency_last_name);
        peMobilenumber = findViewById(R.id.emergency_number);
        peRelation = findViewById(R.id.emergency_relation);
        peDoctorname = findViewById(R.id.familyDrName);
        peDoctorphone = findViewById(R.id.familyDrPhone);
        pHealthissue = findViewById(R.id.health_issue);
        pMedicalhistory = findViewById(R.id.medical_history);
        pInsurenceCompany = findViewById(R.id.insurence_company);
        pInsurenceid = findViewById(R.id.insurenceid);
        pInsurenceDetails = findViewById(R.id.insurencedetails);

        ImageView DoctorBackBtn = findViewById(R.id.DoctorBackBtn);
        DoctorBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientsActivity.this, AdminHomeActivity.class);
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
            AddPatientCRUD(PatientsActivity.this);
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

        // Show the previous layout and hide the current one
        if (previousLayout != null) {
            currentLayout.setVisibility(View.GONE);
            previousLayout.setVisibility(View.VISIBLE);
        } else {
            // Show a toast message when attempting to navigate back from the first layout
            Toast.makeText(this, "You are already on the first page", Toast.LENGTH_SHORT).show();
        }
    }

    public void AddPatientCRUD(Context context) {
        RetrofitInterface retrofitInterface = RetrofitInstance.getSeviceData();

        retrofitInterface.AddPatientCRUD("","","",pFirstname.getText().toString().trim(),pMiddlename.getText().toString().trim(),pLastname.getText().toString().trim(),"",
                pDob.getText().toString().trim(),pAge.getText().toString().trim(),pMobileNumber.getText().toString().trim(),pAlternateNumber.getText().toString().trim(),pGmail.getText().toString().trim(),
                pAddress.getText().toString().trim(),"",peFirstname.getText().toString().trim(),peLastname.getText().toString().trim(),peRelation.getText().toString().trim(),
                        peMobilenumber.getText().toString().trim(),peDoctorname.getText().toString().trim()
                        ,peDoctorname.getText().toString().trim(),pHealthissue.getText().toString().trim(),pMedicalhistory.getText().toString().trim(),"",
                "","","",pInsurenceCompany.getText().toString().trim(),pInsurenceid.getText().toString().trim(),"",pInsurenceDetails.getText().toString().trim(),
                        "","",""
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