package DAO;

import DTO.IncomeDTO;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// MySqlDao is a parent class to MySqlIncomeDao
public class MySqlIncomeDao extends MySqlDao implements IncomeDAOInterface {
    // List all income
    @Override
    public List<IncomeDTO> listAllIncome() throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<IncomeDTO> incomeList = new ArrayList<>();

        try {
            conn = this.getConnection();
            String selectQuery = "SELECT * FROM income";

            ps = conn.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Create a new IncomeDTO object for each record
                IncomeDTO income = new IncomeDTO();
                income.setIncomeID(rs.getInt("incomeID"));
                income.setTitle(rs.getString("title"));
                income.setAmount(rs.getDouble("amount"));
                income.setDate(rs.getString("dateEarned"));
                // Add the IncomeDTO object to the list
                incomeList.add(income);
            }
        } catch (SQLException e) {
            throw new DaoException("listAllIncome() " + e.getMessage());
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
                throw new DaoException("listAllIncome() " + e.getMessage());
            }
        }
        return incomeList;
    }

    // Calculate total amount earned
    @Override
    public double calcTotalIncome() throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double totalIncome = 0;

        try {
            conn = this.getConnection();
            String selectQuery = "SELECT SUM(amount) FROM income";

            ps = conn.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalIncome = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DaoException("calcTotalIncome() " + e.getMessage());
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
                throw new DaoException("calcTotalIncome() " + e.getMessage());
            }
        }
        return totalIncome;
    }

    // Add a new income
    @Override
    public void addNewIncome(IncomeDTO income) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = this.getConnection();
            String insertQuery = "INSERT INTO income (title, amount, dateEarned) VALUES (?, ?, ?)";

            ps = conn.prepareStatement(insertQuery);
            ps.setString(1, income.getTitle());
            ps.setDouble(2, income.getAmount());
            ps.setString(3, income.getDateEarned());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("addNewIncome() " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    this.freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("addNewIncome() " + e.getMessage());
            }
        }
    }
    
}
