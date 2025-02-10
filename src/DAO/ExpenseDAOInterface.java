package DAO;

import DTO.ExpenseDTO;
import Exceptions.DaoException;
import java.util.List;

public interface ExpenseDAOInterface {
    public List<ExpenseDTO> listAllExpenses() throws DaoException;
    public double totalSpent() throws DaoException;
}
