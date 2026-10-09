package service;

import dao.ExpenseDAO;
import model.Expense;

public class ExpenseService {

    private ExpenseDAO expenseDAO;

    public ExpenseService() {
        expenseDAO = new ExpenseDAO();
    }

    // Add Expense
    public void addExpense(Expense expense) {
        expenseDAO.addExpense(expense);
    }

    // View All Expenses
    public void showAllExpenses() {
        expenseDAO.viewExpenses();
    }

    // Total Spending
    public void showTotalSpending() {
        expenseDAO.totalSpending();
    }

    // Delete Expense
    public boolean deleteExpense(int id) {
        return expenseDAO.deleteExpense(id);
    }

    // Edit Expense
    public boolean editExpense(
            int id,
            double amount,
            String category,
            String date,
            String paymentMethod) {

        return expenseDAO.editExpense(
                id,
                amount,
                category,
                date,
                paymentMethod
        );
    }

    // Monthly Spending
    public void showMonthlySpending(String month, String year) {
        expenseDAO.monthlySpending(month, year);
    }

    // Get Monthly Total
    public double getMonthlyTotal(String month, String year) {
        return expenseDAO.getMonthlyTotal(month, year);
    }

    // Category Summary
    public void showCategorySummary(String month, String year) {
        expenseDAO.categorySummary(month, year);
    }

    // Search Expenses by Category
    public void searchByCategory(String category) {
        expenseDAO.searchByCategory(category);
    }

    // Monthly Expense Report
    public void showMonthlyReport(String month, String year) {
        expenseDAO.monthlyExpenseReport(month, year);
    }
}