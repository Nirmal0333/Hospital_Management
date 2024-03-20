package com.example.i_hospital.retrofitinstance;

import com.example.i_hospital.models.Expense;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("webservice.asmx/CheckLoginUser")
    Call<Map<String, String>> CheckLoginUser(@Query("Username") String Username,
                                             @Query("Password") String Password,
                                             @Query("role") String role);

    @GET("Webservice.asmx/AddDoctorCRUD")
    Call<Map<String, String>> AddDoctorCRUD(@Query("Type") String Type,
                                            @Query("Doctor_Id") String Doctor_Id,
                                            @Query("DID") String DID,
                                            @Query("Master_Id") String Master_Id,
                                            @Query("Staff_Id") String Staff_Id,
                                            @Query("FirstNane") String FirstNane,
                                            @Query("MiddleName") String MiddleName,
                                            @Query("LastName") String LastName,
                                            @Query("Gender") String Gender,
                                            @Query("DOB") String DOB,
                                            @Query("Mobile") String Mobile,
                                            @Query("AlterMobile") String AlterMobile,
                                            @Query("Gmail") String Gmail,
                                            @Query("Address") String Address,
                                            @Query("Qualification") String Qualification,
                                            @Query("Certificate1") String Certificate1,
                                            @Query("Certificate2") String Certificate2,
                                            @Query("Certificate3") String Certificate3,
                                            @Query("Specilization") String Specilization,
                                            @Query("Experiance") String Experiance,
                                            @Query("PanNo") String PanNo,
                                            @Query("PanCard") String PanCard,
                                            @Query("AadharNo") String AadharNo,
                                            @Query("AadharCard") String AadharCard,
                                            @Query("Photo") String Photo,
                                            @Query("Username") String Username,
                                            @Query("Password") String Password,
                                            @Query("AddedById") String AddedById,
                                            @Query("AddedByName") String AddedByName);

    @GET("webservice.asmx/AddPatientCRUD")
    Call<Map<String, String>> AddPatientCRUD(@Query("Type") String Type,
                                             @Query("PaitentId") String PaitentId,
                                             @Query("StaffId") String StaffId,
                                             @Query("FirstNane") String FirstNane,
                                             @Query("MiddleName") String MiddleName,
                                             @Query("LastName") String LastName,
                                             @Query("Gender") String Gender,
                                             @Query("DOB") String DOB,
                                             @Query("Age") String Age,
                                             @Query("Mobile") String Mobile,
                                             @Query("AlternateMobile") String AlternateMobile,
                                             @Query("Gmail") String Gmail,
                                             @Query("Address") String Address,
                                             @Query("Username") String Username,
                                             @Query("EmergencyFirstName") String EmergencyFirstName,
                                             @Query("EmergencyLastName") String EmergencyLastName,
                                             @Query("Relationship") String Relationship,
                                             @Query("EmergencyMobile") String EmergencyMobile,
                                             @Query("FamilyDoctorName") String FamilyDoctorName,
                                             @Query("FamilyDoctorMobile") String FamilyDoctorMobile,
                                             @Query("HealthIssue") String HealthIssue,
                                             @Query("MedicalHistory") String MedicalHistory,
                                             @Query("Prescriptions") String Prescriptions,
                                             @Query("Reports") String Reports,
                                             @Query("CurrentMedicine") String CurrentMedicine,
                                             @Query("ListMedicine") String ListMedicine,
                                             @Query("InsuranceCompany") String InsuranceCompany,
                                             @Query("InsuranceId") String InsuranceId,
                                             @Query("Insurancetype") String Insurancetype,
                                             @Query("InsuranceDetails") String InsuranceDetails,
                                             @Query("VisitDate") String VisitDate,
                                             @Query("AddedById") String AddedById,
                                             @Query("AddedByName") String AddedByName);


    @GET("webservice.asmx/BillEntry")
    Call<Map<String, String>> BillEntry(@Query("BillNo") String BillNo,
                                        @Query("BillDate") String BillDate,
                                        @Query("Title") String Title,
                                        @Query("CategoryId") String CategoryId,
                                        @Query("Category") String Category,
                                        @Query("Supplier_Id") String Supplier_Id,
                                        @Query("Suplier") String Suplier,
                                        @Query("Attachment") String Attachment,
                                        @Query("Description") String Description,
                                        @Query("Status") String Status,
                                        @Query("PamentAmount") String PamentAmount,
                                        @Query("PaidAmount") String PaidAmount,
                                        @Query("BalanceAmount") String BalanceAmount,
                                        @Query("AddedById") String AddedById,
                                        @Query("AddedByName") String AddedByName);

    @GET("webservice.asmx/AddStaffCRUD")
    Call<Map<String, String>> AddStaffCRUD(@Query("Type") String Type,
                                           @Query("Staff_Id") String Staff_Id,
                                           @Query("SID") String SID,
                                           @Query("Master_Id") String Master_Id,
                                           @Query("FirstNane") String FirstNane,
                                           @Query("MiddleName") String MiddleName,
                                           @Query("LastName") String LastName,
                                           @Query("Gender") String Gender,
                                           @Query("DOB") String DOB,
                                           @Query("Mobile") String Mobile,
                                           @Query("AlterMobile") String AlterMobile,
                                           @Query("Gmail") String Gmail,
                                           @Query("RoleId") String RoleId,
                                           @Query("Role") String Role,
                                           @Query("DeptId") String DeptId,
                                           @Query("Department") String Department,
                                           @Query("Qualification") String Qualification,
                                           @Query("Address") String Address,
                                           @Query("PanNo") String PanNo,
                                           @Query("PanCard") String PanCardPhoto,
                                           @Query("Photo") String Photo,
                                           @Query("Username") String Username,
                                           @Query("Password") String Password,
                                           @Query("AddedById") String AddedById,
                                           @Query("AddedByName") String AddedByName);
    @GET("Webservice.asmx/GetBillEntry")
    Call<List<Expense>> GetBillEntry();
}
