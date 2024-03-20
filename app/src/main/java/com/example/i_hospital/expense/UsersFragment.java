package com.example.i_hospital.expense;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.i_hospital.R;
import com.example.i_hospital.models.Expense;
import com.example.i_hospital.retrofitinstance.RetrofitInstance;
import com.example.i_hospital.retrofitinstance.RetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExpenseAdapter expenseAdapter;

    List<Expense> list;

    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch and display expense data
        fetchExpenseData();

        return view;
    }

    private void fetchExpenseData() {
        list = new ArrayList<>(); // Initialize the list
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofit().create(RetrofitInterface.class);
        retrofitInterface.GetBillEntry().enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
                if (response.isSuccessful()) {
                    List<Expense> expenseList = response.body();
                    // Convert timestamp to human-readable date format
                    for (Expense expense : expenseList) {
                        long timestamp = Long.parseLong(expense.getBill_Date().substring(expense.getBill_Date().indexOf("(") + 1, expense.getBill_Date().indexOf(")")));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String formattedDate = dateFormat.format(new Date(timestamp));
                        expense.setBill_Date(formattedDate);
                    }
                    list.addAll(expenseList); // Add all items from response to the list
                    setExpenseAdapter(list); // Set adapter with fetched data
                } else {
                    Toast.makeText(getContext(), "Invalid response from server", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Invalid response from server");
                }
            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {
                Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setExpenseAdapter(List<Expense> expenseList) {
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(expenseAdapter);
    }
}