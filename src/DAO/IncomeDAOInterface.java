package DAO;

import DTO.IncomeDTO;
import Exceptions.DaoException;
import java.util.List;

public interface IncomeDAOInterface {
    public List<IncomeDTO> listAllIncome() throws DaoException;
}
