package DAO;

import DTO.ExpenseDTO;
import Exceptions.DaoException;
import java.util.List;

public interface ExpenseDAOInterface {
    public List<ExpenseDTO> listAllExpenses() throws DaoException;
    public double calcTotalExpenses() throws DaoException;
    public void addExpense(ExpenseDTO expense) throws DaoException;
    public void deleteExpense(int expenseID) throws DaoException;
    public List<ExpenseDTO> listExpenseByMonth(int month) throws DaoException;
    public double calcTotalExpensesByMonth(int month) throws DaoException;
}
