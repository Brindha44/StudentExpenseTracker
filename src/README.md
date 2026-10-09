# Student Expense Tracker

A Java-based console application designed to help students record, manage, analyze, and control their monthly expenses.

## Features

- Add expenses
- View all expenses
- Edit expenses
- Delete expenses
- Search expenses by category
- Calculate total spending
- View monthly spending
- View category-wise spending
- Set monthly budget
- Check budget status
- Budget advisor
- Monthly expense report
- MySQL database storage
- Input validation

## Technologies Used

- Java
- JDBC
- MySQL
- MySQL Workbench
- VS Code

## Project Structure

```text
StudentExpenseTracker
│
├── src
│   ├── model
│   │   ├── Expense.java
│   │   └── Budget.java
│   │
│   ├── dao
│   │   ├── ExpenseDAO.java
│   │   └── BudgetDAO.java
│   │
│   ├── service
│   │   ├── ExpenseService.java
│   │   └── BudgetService.java
│   │
│   ├── util
│   │   └── DBConnection.java
│   │
│   └── ui
│       └── Main.java
│
├── lib
├── out
└── README.md