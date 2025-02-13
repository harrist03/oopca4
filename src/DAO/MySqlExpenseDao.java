package DAO;

import DTO.ExpenseDTO;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// MySqlDao is a parent class to MySqlExpenseDao
public class MySqlExpenseDao extends MySqlDao implements ExpenseDAOInterface {
    // List all expenses incurred
    @Override
    public List<ExpenseDTO> listAllExpenses() throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExpenseDTO> expenses = new ArrayList<>();

        try {
            conn = this.getConnection();
            String selectQuery = "SELECT * FROM expenses";

            ps = conn.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Create a new ExpenseDTO object for each record
                ExpenseDTO expense = new ExpenseDTO();
                expense.setExpenseID(rs.getInt("expenseID"));
                expense.setTitle(rs.getString("title"));
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setDate(rs.getString("dateIncurred"));
                // Add the ExpenseDTO object to the list
                expenses.add(expense);
            }
        } catch (SQLException e) {
            throw new DaoException("listAllExpenses() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    this.freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("listAllExpenses() " + e.getMessage());
            }
        }
        return expenses;
    }

    // Calculate total amount spent
    public double totalSpent() throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double total = 0;

        try {
            conn = this.getConnection();
            String totalQuery = "SELECT SUM(amount) FROM expenses";

            ps = conn.prepareStatement(totalQuery);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Get the total amount spent from the first column
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DaoException("totalSpent() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    this.freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("totalSpent() " + e.getMessage());
            }
        }
        return total;
    }

    // Add a new expense
    @Override
    public void addExpense(ExpenseDTO expense) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = this.getConnection();
            String insertQuery = "INSERT INTO expenses (title, category, amount, dateIncurred) VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(insertQuery);
            // Set the values of the parameters
            ps.setString(1, expense.getTitle());
            ps.setString(2, expense.getCategory());
            ps.setDouble(3, expense.getAmount());
            ps.setString(4, expense.getDate());
            // Execute the query
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("addExpense() " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    this.freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("addExpense() " + e.getMessage());
            }
        }
    }

    // Delete an expense
    @Override
    public void deleteExpense(int expenseID) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = this.getConnection();
            String deleteQuery = "DELETE FROM expenses WHERE expenseID = ?";

            ps = conn.prepareStatement(deleteQuery);
            ps.setInt(1, expenseID);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deletion is successful!");
            } else {
                System.out.println("Invalid expense ID!");
            }
        } catch (SQLException e) {
            throw new DaoException("deleteExpense() " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    this.freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("addExpense() " + e.getMessage());
            }
        }
    }
    
}