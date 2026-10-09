package dao;

import model.Budget;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BudgetDAO {

    // Add Monthly Budget
    public void addBudget(Budget budget) {

        String sql = "INSERT INTO monthly_budget " +
                     "(month, year, budget_amount) " +
                     "VALUES (?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, budget.month);
            statement.setString(2, budget.year);
            statement.setDouble(3, budget.amount);

            statement.executeUpdate();

            System.out.println(
                    "Monthly budget saved successfully!"
            );

            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to save monthly budget!"
            );

            e.printStackTrace();
        }
    }


    // Get Monthly Budget
    public double getBudget(String month, String year) {

        String sql =
                "SELECT budget_amount FROM monthly_budget " +
                "WHERE month = ? AND year = ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, month);
            statement.setString(2, year);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                double budget =
                        result.getDouble("budget_amount");

                result.close();
                statement.close();
                connection.close();

                return budget;
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to get monthly budget!"
            );

            e.printStackTrace();
        }

        return 0;
    }
}