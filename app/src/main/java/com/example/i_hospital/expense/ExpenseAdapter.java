package com.example.i_hospital.expense;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.i_hospital.R;
import com.example.i_hospital.models.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expense_item, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.bind(expense);

        // Set click listener for PaysButton
        holder.PaysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, e.g., start ViewExpenseNext activity
                Intent intent = new Intent(v.getContext(), ViewExpenseNext.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewBillNo, textViewDate, textViewMedicine,
                textViewSupplierName, textViewStatus, textViewAmount, textViewPaid, textViewBalance;
        private Button PaysButton;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            textViewBillNo = itemView.findViewById(R.id.textViewBillNo);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewMedicine = itemView.findViewById(R.id.Medicine);
            textViewSupplierName = itemView.findViewById(R.id.SuppilerNameText);
            textViewStatus = itemView.findViewById(R.id.Statustittle);
            textViewAmount = itemView.findViewById(R.id.Amounttittle);
            textViewPaid = itemView.findViewById(R.id.Paidtittle);
            textViewBalance = itemView.findViewById(R.id.Balancetittle);
            PaysButton = itemView.findViewById(R.id.PaysButton);
        }

        public void bind(Expense expense) {
            textViewBillNo.setText(expense.getBill_No());
            textViewDate.setText(expense.getBill_Date());
            textViewMedicine.setText(expense.getCategory());
            textViewSupplierName.setText(expense.getSuplier());
            textViewStatus.setText(expense.getStatus());
            textViewAmount.setText(expense.getPament_Amount());
            textViewPaid.setText(expense.getPaid_Amount());
            textViewBalance.setText(expense.getBalance_Amount());
        }
    }
}
