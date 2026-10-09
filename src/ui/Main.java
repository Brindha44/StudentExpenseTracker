package ui;

import model.Expense;
import model.Budget;

import service.ExpenseService;
import service.BudgetService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExpenseService expenseService = new ExpenseService();
        BudgetService budgetService = new BudgetService();

        System.out.println();
        System.out.println("=================================================");
        System.out.println("           STUDENT EXPENSE TRACKER");
        System.out.println("=================================================");
        System.out.println("        Manage • Track • Control Expenses");
        System.out.println("=================================================");

        while (true) {

            System.out.println();
            System.out.println("--------------- MAIN MENU ----------------");
            System.out.println("1.  Add Expense");
            System.out.println("2.  View Expenses");
            System.out.println("3.  Total Spending");
            System.out.println("4.  Delete Expense");
            System.out.println("5.  Edit Expense");
            System.out.println("6.  Monthly Spending");
            System.out.println("7.  Set Monthly Budget");
            System.out.println("8.  Budget Status");
            System.out.println("9.  Category Summary");
            System.out.println("10. Search Expenses");
            System.out.println("11. Budget Advisor");
            System.out.println("12. Monthly Expense Report");
            System.out.println("13. Exit");
            System.out.println("------------------------------------------");

            System.out.print("Enter your choice: ");

            int choice;

            try {

                choice = Integer.parseInt(sc.nextLine());

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Please enter a number from 1 to 13."
                );

                continue;
            }

            // 1. Add Expense
            if (choice == 1) {

                System.out.println();
                System.out.println("========== ADD EXPENSE ==========");

                System.out.print("Enter amount: ");

                double amount;

                try {

                    amount = Double.parseDouble(sc.nextLine());

                    if (amount <= 0) {

                        System.out.println(
                                "Invalid amount! Amount must be greater than 0."
                        );

                        continue;
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Invalid amount! Please enter a number."
                    );

                    continue;
                }

                System.out.print(
                        "Enter category (Canteen/Education/Food/Travel/Personal/Health/Recharge/Other): "
                );

                String category =
                        sc.nextLine().trim();

                if (category.isEmpty()) {

                    System.out.println(
                            "Category cannot be empty!"
                    );

                    continue;
                }

                System.out.print(
                        "Enter date (DD-MM-YYYY): "
                );

                String date =
                        sc.nextLine().trim();

                if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {

                    System.out.println(
                            "Invalid date! Use DD-MM-YYYY format."
                    );

                    continue;
                }

                System.out.print(
                        "Enter payment method (UPI/Cash/Card): "
                );

                String paymentMethod =
                        sc.nextLine().trim();

                if (!paymentMethod.equalsIgnoreCase("UPI")
                        && !paymentMethod.equalsIgnoreCase("Cash")
                        && !paymentMethod.equalsIgnoreCase("Card")) {

                    System.out.println(
                            "Invalid payment method! Use UPI, Cash, or Card."
                    );

                    continue;
                }

                Expense expense =
                        new Expense();

                expense.amount =
                        amount;

                expense.category =
                        category;

                expense.date =
                        date;

                expense.paymentMethod =
                        paymentMethod;

                expenseService.addExpense(expense);
            }

            // 2. View Expenses
            else if (choice == 2) {

                expenseService.showAllExpenses();
            }

            // 3. Total Spending
            else if (choice == 3) {

                expenseService.showTotalSpending();
            }

            // 4. Delete Expense
            else if (choice == 4) {

                expenseService.showAllExpenses();

                System.out.print(
                        "Enter expense ID to delete: "
                );

                try {

                    int id =
                            Integer.parseInt(
                                    sc.nextLine()
                            );

                    boolean deleted =
                            expenseService.deleteExpense(id);

                    if (deleted) {

                        System.out.println(
                                "Expense deleted successfully!"
                        );

                    } else {

                        System.out.println(
                                "Expense ID not found!"
                        );
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Invalid ID!"
                    );
                }
            }

            // 5. Edit Expense
            else if (choice == 5) {

                expenseService.showAllExpenses();

                System.out.print(
                        "Enter expense ID to edit: "
                );

                int id;

                try {

                    id =
                            Integer.parseInt(
                                    sc.nextLine()
                            );

                } catch (Exception e) {

                    System.out.println(
                            "Invalid ID!"
                    );

                    continue;
                }

                System.out.print(
                        "Enter new amount: "
                );

                double amount;

                try {

                    amount =
                            Double.parseDouble(
                                    sc.nextLine()
                            );

                    if (amount <= 0) {

                        System.out.println(
                                "Amount must be greater than 0."
                        );

                        continue;
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Invalid amount!"
                    );

                    continue;
                }

                System.out.print(
                        "Enter new category: "
                );

                String category =
                        sc.nextLine().trim();

                if (category.isEmpty()) {

                    System.out.println(
                            "Category cannot be empty!"
                    );

                    continue;
                }

                System.out.print(
                        "Enter new date (DD-MM-YYYY): "
                );

                String date =
                        sc.nextLine().trim();

                if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {

                    System.out.println(
                            "Invalid date! Use DD-MM-YYYY format."
                    );

                    continue;
                }

                System.out.print(
                        "Enter new payment method (UPI/Cash/Card): "
                );

                String paymentMethod =
                        sc.nextLine().trim();

                if (!paymentMethod.equalsIgnoreCase("UPI")
                        && !paymentMethod.equalsIgnoreCase("Cash")
                        && !paymentMethod.equalsIgnoreCase("Card")) {

                    System.out.println(
                            "Invalid payment method!"
                    );

                    continue;
                }

                boolean updated =
                        expenseService.editExpense(
                                id,
                                amount,
                                category,
                                date,
                                paymentMethod
                        );

                if (updated) {

                    System.out.println(
                            "Expense updated successfully!"
                    );

                } else {

                    System.out.println(
                            "Expense ID not found!"
                    );
                }
            }

            // 6. Monthly Spending
            else if (choice == 6) {

                System.out.println();
                System.out.println(
                        "========== MONTHLY SPENDING =========="
                );

                System.out.print(
                        "Enter month (MM): "
                );

                String month =
                        sc.nextLine();

                System.out.print(
                        "Enter year (YYYY): "
                );

                String year =
                        sc.nextLine();

                expenseService.showMonthlySpending(
                        month,
                        year
                );
            }

            // 7. Set Monthly Budget
            else if (choice == 7) {

                System.out.println();
                System.out.println(
                        "========== SET MONTHLY BUDGET =========="
                );

                System.out.print(
                        "Enter month (MM): "
                );

                String month =
                        sc.nextLine();

                System.out.print(
                        "Enter year (YYYY): "
                );

                String year =
                        sc.nextLine();

                System.out.print(
                        "Enter monthly budget: "
                );

                double amount;

                try {

                    amount =
                            Double.parseDouble(
                                    sc.nextLine()
                            );

                    if (amount <= 0) {

                        System.out.println(
                                "Budget must be greater than 0."
                        );

                        continue;
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Invalid budget amount!"
                    );

                    continue;
                }

                Budget budget =
                        new Budget();

                budget.month =
                        month;

                budget.year =
                        year;

                budget.amount =
                        amount;

                budgetService.addBudget(budget);
            }

            // 8. Budget Status
            else if (choice == 8) {

                System.out.println();
                System.out.println(
                        "========== BUDGET STATUS =========="
                );

                System.out.print(
                        "Enter month (MM): "
                );

                String month =
                        sc.nextLine();

                System.out.print(
                        "Enter year (YYYY): "
                );

                String year =
                        sc.nextLine();

                double monthlyBudget =
                        budgetService.getBudget(
                                month,
                                year
                        );

                if (monthlyBudget == 0) {

                    System.out.println(
                            "No budget found for this month."
                    );

                } else {

                    double monthlySpending =
                            expenseService.getMonthlyTotal(
                                    month,
                                    year
                            );

                    double remaining =
                            monthlyBudget -
                            monthlySpending;

                    System.out.println();
                    System.out.println(
                            "Monthly Budget   : Rs." +
                            monthlyBudget
                    );

                    System.out.println(
                            "Monthly Spending : Rs." +
                            monthlySpending
                    );

                    System.out.println(
                            "-----------------------------------------"
                    );

                    if (remaining > 0) {

                        System.out.println(
                                "Remaining Budget : Rs." +
                                remaining
                        );

                    } else if (remaining == 0) {

                        System.out.println(
                                "Budget Fully Used!"
                        );

                    } else {

                        System.out.println(
                                "Budget Exceeded By: Rs." +
                                (-remaining)
                        );
                    }
                }
            }

            // 9. Category Summary
            else if (choice == 9) {

                System.out.println();
                System.out.println(
                        "========== CATEGORY SUMMARY =========="
                );

                System.out.print(
                        "Enter month (MM): "
                );

                String month =
                        sc.nextLine();

                System.out.print(
                        "Enter year (YYYY): "
                );

                String year =
                        sc.nextLine();

                expenseService.showCategorySummary(
                        month,
                        year
                );
            }

            // 10. Search Expenses
            else if (choice == 10) {

                System.out.println();
                System.out.println(
                        "========== SEARCH EXPENSES =========="
                );

                System.out.print(
                        "Enter category to search: "
                );

                String category =
                        sc.nextLine();

                expenseService.searchByCategory(
                        category
                );
            }

            // 11. Budget Advisor
            else if (choice == 11) {

                System.out.println();
                System.out.println(
                        "========== BUDGET ADVISOR =========="
                );

                System.out.print(
                        "Enter month (MM): "
                );

                String month =
                        sc.nextLine();

                System.out.print(
                        "Enter year (YYYY): "
                );

                String year =
                        sc.nextLine();

                double monthlyBudget =
                        budgetService.getBudget(
                                month,
                                year
                        );

                if (monthlyBudget == 0) {

                    System.out.println(
                            "No budget found for this month."
                    );

                } else {

                    double monthlySpending =
                            expenseService.getMonthlyTotal(
                                    month,
                                    year
                            );

                    double percentage =
                            (monthlySpending /
                            monthlyBudget) * 100;

                    System.out.println();
                    System.out.println(
                            "Monthly Budget   : Rs." +
                            monthlyBudget
                    );

                    System.out.println(
                            "Monthly Spending : Rs." +
                            monthlySpending
                    );

                    System.out.println(
                            "Budget Used      : " +
                            percentage +
                            "%"
                    );

                    System.out.println(
                            "-----------------------------------------"
                    );

                    if (monthlySpending >
                            monthlyBudget) {

                        System.out.println(
                                "You have exceeded your monthly budget."
                        );

                        System.out.println(
                                "Try to control your spending next month."
                        );

                    } else if (percentage >= 80) {

                        System.out.println(
                                "You have used most of your monthly budget."
                        );

                        System.out.println(
                                "Try to reduce unnecessary spending."
                        );

                    } else {

                        System.out.println(
                                "Good job! You are spending within your budget."
                        );
                    }
                }
            }

            // 12. Monthly Expense Report
            else if (choice == 12) {

                System.out.println();
                System.out.println(
                        "========== MONTHLY EXPENSE REPORT =========="
                );

                System.out.print(
                        "Enter month (MM): "
                );

                String month =
                        sc.nextLine();

                System.out.print(
                        "Enter year (YYYY): "
                );

                String year =
                        sc.nextLine();

                expenseService.showMonthlyReport(
                        month,
                        year
                );
            }

            // 13. Exit
            else if (choice == 13) {

                System.out.println();
                System.out.println(
                        "================================================="
                );
                System.out.println(
                        " Thank you for using Student Expense Tracker!"
                );
                System.out.println(
                        "================================================="
                );

                break;
            }

            // Invalid Choice
            else {

                System.out.println(
                        "Invalid choice! Please enter 1 to 13."
                );
            }
        }

        sc.close();
    }
}