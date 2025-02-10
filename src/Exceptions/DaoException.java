package Exceptions;

import java.sql.SQLException;

// custom exception class for DAO
public class DaoException extends SQLException {
    public DaoException(String message) {
        super(message);
    }
}
