package com.example.i_hospital.models;

public class Expense {
    private String Bill_No;
    private String Bill_Date;
    private String Category;
    private String Suplier;
    private String Status;
    private String Pament_Amount;
    private String Paid_Amount;
    private String Balance_Amount;


    public String getBill_No() {
        return Bill_No;
    }

    public void setBill_No(String bill_No) {
        Bill_No = bill_No;
    }

    public String getBill_Date() {
        return Bill_Date;
    }

    public void setBill_Date(String bill_Date) {
        Bill_Date = bill_Date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSuplier() {
        return Suplier;
    }

    public void setSuplier(String suplier) {
        Suplier = suplier;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPament_Amount() {
        return Pament_Amount;
    }

    public void setPament_Amount(String pament_Amount) {
        Pament_Amount = pament_Amount;
    }

    public String getPaid_Amount() {
        return Paid_Amount;
    }

    public void setPaid_Amount(String paid_Amount) {
        Paid_Amount = paid_Amount;
    }

    public String getBalance_Amount() {
        return Balance_Amount;
    }

    public void setBalance_Amount(String balance_Amount) {
        Balance_Amount = balance_Amount;
    }

}

