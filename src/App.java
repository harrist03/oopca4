import DAO.MySqlExpenseDao;
import DTO.ExpenseDTO;
import Exceptions.DaoException;
import java.util.List;
import java.util.Scanner;

import DAO.ExpenseDAOInterface;

public class App {
    public static ExpenseDAOInterface expenseDAO = new MySqlExpenseDao();

    public static void main(String[] args) throws DaoException {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n1. List all expenses");
            System.out.println("2. Total spent in expenses");
            System.out.println("3. Add a new expense");
            System.out.println("4. Delete an expense");
            System.out.println("8. Exit");
            choice = sc.nextInt();

            if (choice == 1) {
                listAllExpenses();
            } else if (choice == 2) {
                calcTotalExpenses();
            } else if (choice == 3) {
                addNewExpense(sc);
            } else if (choice == 4) {
                deleteExpense(sc);
            } else if (choice == 8) {
                System.out.println("Goodbye!");
            }
            if (choice <= 0 || choice > 8) {
                System.out.println("Invalid input! Try again!");
            }
        } while (choice != 8);

        sc.close();
    }

    // 1. List all expenses
    public static void listAllExpenses() throws DaoException {
        List<ExpenseDTO> expenses = expenseDAO.listAllExpenses();
        System.out.println("All expenses incurred:");
        for (ExpenseDTO expense : expenses) {
            System.out.println(expense.toString());
        }
    }

    // 2. Calculate total expenses
    public static void calcTotalExpenses() throws DaoException {
        System.out.println("Total spent: $" + expenseDAO.totalSpent());
    }

    // 3. Add a new expense
    public static void addNewExpense(Scanner sc) throws DaoException {
        sc.nextLine();
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
    }

    // 4. Delete an expense
    public static void deleteExpense(Scanner sc) throws DaoException {
        listAllExpenses();
        System.out.println("Enter expense ID to be deleted: ");
        int expenseID = sc.nextInt();
        expenseDAO.deleteExpense(expenseID);
    }
}
