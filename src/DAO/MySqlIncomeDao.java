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
                income.setDateEarned(rs.getString("dateEarned"));
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

    // calc total amount earned
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

    // Delete an income
    @Override
    public void deleteIncome(int incomeID) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = this.getConnection();
            String deleteQuery = "DELETE FROM income WHERE incomeID = ?";

            ps = conn.prepareStatement(deleteQuery);
            ps.setInt(1, incomeID);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deletion is successful!");
            } else {
                System.out.println("Invalid Income ID!");
            }
        } catch (SQLException e) {
            throw new DaoException("deleteIncome()" + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    this.freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteIncome() " + e.getMessage());
            }
        }
    }

    // list income by month
    public List<IncomeDTO> listIncomeByMonth(int month) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<IncomeDTO> incomeList = new ArrayList<>();

        try {
            conn = this.getConnection();
            String selectQuery = "SELECT * FROM income WHERE MONTH(dateEarned) = ?";
            ps = conn.prepareStatement(selectQuery);
            ps.setInt(1, month);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Create a new IncomeDTO object for each record
                IncomeDTO income = new IncomeDTO();
                income.setIncomeID(rs.getInt("incomeID"));
                income.setTitle(rs.getString("title"));
                income.setAmount(rs.getDouble("amount"));
                income.setDateEarned(rs.getString("dateEarned"));
                // Add the incomeDTO object to the list
                incomeList.add(income);
            }
        } catch (SQLException e) {
            throw new DaoException("findIncomeByMonth()" + e.getMessage());
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
                throw new DaoException("findIncomeByMonth() " + e.getMessage());
            }
        }

        return incomeList;
    }

    // calc total income by month
    public double calcTotalIncomeByMonth(int month) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double totalIncome = 0;

        try {
            conn = this.getConnection();
            String sumQuery = "SELECT SUM(amount) FROM income WHERE MONTH(dateEarned) = ?";
            ps = conn.prepareStatement(sumQuery);
            ps.setInt(1, month);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalIncome = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DaoException("calcTotalIncomeByMonth() " + e.getMessage());
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
                throw new DaoException("calcTotalIncomeByMonth() " + e.getMessage());
            }
        }

        return totalIncome;
    }
}
