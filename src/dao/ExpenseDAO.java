package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Expense;
import util.DBConnection;


public class ExpenseDAO {

    // Add Expense
    public void addExpense(Expense expense) {

        String sql = "INSERT INTO expenses " +
                     "(amount, category, expense_date, payment_method) " +
                     "VALUES (?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(
                            sql,
                            java.sql.Statement.RETURN_GENERATED_KEYS
                    );

            statement.setDouble(1, expense.amount);
            statement.setString(2, expense.category);
            statement.setString(3, expense.date);
            statement.setString(4, expense.paymentMethod);

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                expense.id = keys.getInt(1);
            }

            System.out.println(
                    "Expense saved to database successfully!"
            );

            keys.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println("Failed to save expense!");
            e.printStackTrace();
        }
    }


    // View Expenses
    public void viewExpenses() {

        String sql = "SELECT * FROM expenses";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            System.out.println();
            System.out.println("========== ALL EXPENSES ==========");

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println(
                        "ID: " + result.getInt("id") +
                        " | Amount: Rs." + result.getDouble("amount") +
                        " | Category: " + result.getString("category") +
                        " | Date: " + result.getString("expense_date") +
                        " | Payment: " + result.getString("payment_method")
                );
            }

            if (!found) {
                System.out.println("No expenses found.");
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println("Failed to view expenses!");
            e.printStackTrace();
        }
    }


    // Total Spending
    public void totalSpending() {

        String sql = "SELECT SUM(amount) AS total FROM expenses";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                double total = result.getDouble("total");

                System.out.println(
                        "Total Spending: Rs." + total
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to calculate total spending!"
            );

            e.printStackTrace();
        }
    }


    // Delete Expense
    public boolean deleteExpense(int id) {

        String sql = "DELETE FROM expenses WHERE id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("Failed to delete expense!");
            e.printStackTrace();

            return false;
        }
    }


    // Edit Expense
    public boolean editExpense(
            int id,
            double amount,
            String category,
            String date,
            String paymentMethod) {

        String sql =
                "UPDATE expenses SET amount = ?, category = ?, " +
                "expense_date = ?, payment_method = ? " +
                "WHERE id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setDouble(1, amount);
            statement.setString(2, category);
            statement.setString(3, date);
            statement.setString(4, paymentMethod);
            statement.setInt(5, id);

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("Failed to edit expense!");
            e.printStackTrace();

            return false;
        }
    }


    // Monthly Spending
    public void monthlySpending(
            String month,
            String year) {

        String sql =
                "SELECT category, SUM(amount) AS total " +
                "FROM expenses " +
                "WHERE expense_date LIKE ? " +
                "GROUP BY category";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    "%-" + month + "-" + year
            );

            ResultSet result =
                    statement.executeQuery();

            System.out.println();
            System.out.println(
                    "========== MONTHLY SPENDING =========="
            );

            boolean found = false;
            double grandTotal = 0;

            while (result.next()) {

                found = true;

                String category =
                        result.getString("category");

                double total =
                        result.getDouble("total");

                System.out.println(
                        category + " : Rs." + total
                );

                grandTotal += total;
            }

            if (!found) {

                System.out.println(
                        "No expenses found for this month."
                );

            } else {

                System.out.println("--------------------------------");
                System.out.println(
                        "Total : Rs." + grandTotal
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to calculate monthly spending!"
            );

            e.printStackTrace();
        }
    }


    // Get Monthly Total
    public double getMonthlyTotal(
            String month,
            String year) {

        String sql =
                "SELECT SUM(amount) AS total " +
                "FROM expenses " +
                "WHERE expense_date LIKE ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    "%-" + month + "-" + year
            );

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                double total =
                        result.getDouble("total");

                result.close();
                statement.close();
                connection.close();

                return total;
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to get monthly total!"
            );

            e.printStackTrace();
        }

        return 0;
    }


    // Category Summary
    public void categorySummary(
            String month,
            String year) {

        String sql =
                "SELECT category, SUM(amount) AS total " +
                "FROM expenses " +
                "WHERE expense_date LIKE ? " +
                "GROUP BY category " +
                "ORDER BY total DESC";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    "%-" + month + "-" + year
            );

            ResultSet result =
                    statement.executeQuery();

            System.out.println();
            System.out.println(
                    "========== CATEGORY SUMMARY =========="
            );

            boolean found = false;
            double grandTotal = 0;

            while (result.next()) {

                found = true;

                String category =
                        result.getString("category");

                double total =
                        result.getDouble("total");

                System.out.println(
                        category + " : Rs." + total
                );

                grandTotal += total;
            }

            if (!found) {

                System.out.println(
                        "No expenses found for this month."
                );

            } else {

                System.out.println("--------------------------------");
                System.out.println(
                        "Total : Rs." + grandTotal
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to calculate category summary!"
            );

            e.printStackTrace();
        }
    }


    // Search Expenses by Category
    public void searchByCategory(String category) {

        String sql =
                "SELECT * FROM expenses " +
                "WHERE category = ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, category);

            ResultSet result =
                    statement.executeQuery();

            System.out.println();
            System.out.println(
                    "========== SEARCH RESULTS =========="
            );

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println(
                        "ID: " + result.getInt("id") +
                        " | Amount: Rs." +
                        result.getDouble("amount") +
                        " | Category: " +
                        result.getString("category") +
                        " | Date: " +
                        result.getString("expense_date") +
                        " | Payment: " +
                        result.getString("payment_method")
                );
            }

            if (!found) {

                System.out.println(
                        "No expenses found for this category."
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to search expenses!"
            );

            e.printStackTrace();
        }
    }


    // Monthly Expense Report
    public void monthlyExpenseReport(
            String month,
            String year) {

        String sql =
                "SELECT category, SUM(amount) AS total " +
                "FROM expenses " +
                "WHERE expense_date LIKE ? " +
                "GROUP BY category " +
                "ORDER BY total DESC";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    "%-" + month + "-" + year
            );

            ResultSet result =
                    statement.executeQuery();

            System.out.println();
            System.out.println(
                    "========== MONTHLY EXPENSE REPORT =========="
            );

            boolean found = false;
            double total = 0;
            String highestCategory = "";
            double highestAmount = 0;

            while (result.next()) {

                found = true;

                String category =
                        result.getString("category");

                double amount =
                        result.getDouble("total");

                total += amount;

                if (amount > highestAmount) {

                    highestAmount = amount;
                    highestCategory = category;
                }
            }

            if (!found) {

                System.out.println(
                        "No expenses found for this month."
                );

            } else {

                System.out.println(
                        "Month: " + month + "-" + year
                );

                System.out.println(
                        "Total Spending: Rs." + total
                );

                System.out.println(
                        "Highest Category: " +
                        highestCategory
                );

                System.out.println(
                        "Highest Spending: Rs." +
                        highestAmount
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to generate monthly report!"
            );

            e.printStackTrace();
        }
    }
}