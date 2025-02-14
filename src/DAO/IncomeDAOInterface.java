package DAO;

import DTO.IncomeDTO;
import Exceptions.DaoException;
import java.util.List;

public interface IncomeDAOInterface {
    public List<IncomeDTO> listAllIncome() throws DaoException;
    public double calcTotalIncome() throws DaoException;
    public void addNewIncome(IncomeDTO income) throws DaoException;
    public void deleteIncome(int incomeID) throws DaoException;
    public List<IncomeDTO> listIncomeByMonth(int month) throws DaoException;
    public double calcTotalIncomeByMonth(int month) throws DaoException;
}
