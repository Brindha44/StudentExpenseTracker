package service;

import dao.BudgetDAO;
import model.Budget;

public class BudgetService {

    private BudgetDAO budgetDAO;

    public BudgetService() {
        budgetDAO = new BudgetDAO();
    }

    public void addBudget(Budget budget) {
        budgetDAO.addBudget(budget);
    }

    public double getBudget(String month, String year) {
        return budgetDAO.getBudget(month, year);
    }
}