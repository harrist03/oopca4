package DAO;

import DTO.ExpenseDTO;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDAOInterface {
    // List all expenses incurred and calc total spent
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
}