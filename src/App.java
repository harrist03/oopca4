import DAO.MySqlExpenseDao;
import DTO.ExpenseDTO;
import Exceptions.DaoException;
import java.util.List;
import java.util.Scanner;

import DAO.ExpenseDAOInterface;


public class App {
    public static void main(String[] args) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        app.start(sc);
        sc.close();
    }

    public void start(Scanner sc) {
        ExpenseDAOInterface expenseDAO = new MySqlExpenseDao();

        // 1. List all expenses and calc total spent
        try {
            List<ExpenseDTO> expenses = expenseDAO.listAllExpenses();
            System.out.println("All expenses incurred:");
            for (ExpenseDTO expense : expenses) {
                System.out.println(expense.toString());
            }
            System.out.println("Total spent: $" + expenseDAO.totalSpent());
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 2. Add a new expense
        try {
            // Prompt user for new expense details
            System.out.println("Enter title of new expense: ");
            String title = sc.nextLine();
            System.out.println("Enter category of new expense: ");
            String category = sc.nextLine();
            System.out.println("Enter amount of new expense ($): ");
            double amount = sc.nextDouble();
            System.out.println("Enter date of new expense (yyyy-mm-dd): ");
            String date = sc.next();
            
            ExpenseDTO newExpense = new ExpenseDTO(title, category, amount, date);
            expenseDAO.addExpense(newExpense);
            System.out.println("New expense added successfully.");
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
