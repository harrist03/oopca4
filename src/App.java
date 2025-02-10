import DAO.MySqlExpenseDao;
import DTO.ExpenseDTO;
import Exceptions.DaoException;
import java.util.List;
import DAO.ExpenseDAOInterface;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        ExpenseDAOInterface expenseDAO = new MySqlExpenseDao();

        try {
            // List all expenses and calc total spent
            List<ExpenseDTO> expenses = expenseDAO.listAllExpenses();
            System.out.println("All expenses incurred:");
            for (ExpenseDTO expense : expenses) {
                System.out.println(expense.toString());
            }
            System.out.println("Total spent: $" + expenseDAO.totalSpent());
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
