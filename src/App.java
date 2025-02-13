import DAO.MySqlExpenseDao;
import DAO.MySqlIncomeDao;
import DTO.ExpenseDTO;
import DTO.IncomeDTO;
import Exceptions.DaoException;
import java.util.List;
import java.util.Scanner;

import DAO.ExpenseDAOInterface;
import DAO.IncomeDAOInterface;

public class App {
    // Create DAO objects
    public static ExpenseDAOInterface expenseDAO = new MySqlExpenseDao();
    public static IncomeDAOInterface incomeDAO = new MySqlIncomeDao();

    public static void main(String[] args) throws DaoException {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n1. List all expenses");
            System.out.println("2. Total spent in expenses");
            System.out.println("3. Add a new expense");
            System.out.println("4. Delete an expense");
            System.out.println("5. List all income");
            System.out.println("6. Total earned in income");
            System.out.println("7. Add a new income");
            System.out.println("8. Delete an income");
            System.out.println("10. Exit");
            choice = sc.nextInt();

            if (choice == 1) {
                listAllExpenses();
            } else if (choice == 2) {
                calcTotalExpenses();
            } else if (choice == 3) {
                addNewExpense(sc);
            } else if (choice == 4) {
                deleteExpense(sc);
            } else if (choice == 5) {
                listAllIncome();
            } else if (choice == 6) {
                calcTotalIncome();
            } else if (choice == 7) {
                addNewIncome(sc);
            } else if (choice == 8) {
                deleteIncome(sc);
            } else if (choice == 10) {
                System.out.println("Goodbye!");
            }
            if (choice <= 0 || choice > 10) {
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
        System.out.println("Total spent: $" + expenseDAO.calcTotalExpenses());
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

    // 5. List all income
    public static void listAllIncome() throws DaoException {
        List<IncomeDTO> incomeList = incomeDAO.listAllIncome();
        System.out.println("All income earned:");
        for (IncomeDTO income : incomeList) {
            System.out.println(income.toString());
        }
    }

    // 6. Calculate total income
    public static void calcTotalIncome() throws DaoException {
        System.out.println("Total earned: $" + incomeDAO.calcTotalIncome());
    }

    // 7. Add a new income
    public static void addNewIncome(Scanner sc) throws DaoException {
        sc.nextLine();
        // Prompt user for new income details
        System.out.println("Enter title of new income: ");
        String title = sc.nextLine();
        System.out.println("Enter amount of new income ($): ");
        double amount = sc.nextDouble();
        System.out.println("Enter date of new income (yyyy-mm-dd): ");
        String date = sc.next();

        IncomeDTO newIncome = new IncomeDTO(title, amount, date);
        incomeDAO.addNewIncome(newIncome);
        System.out.println("New income added successfully.");
    }

    // 8. Delete an income
    public static void deleteIncome(Scanner sc) throws DaoException {
        listAllIncome();
        System.out.println("Enter Income ID to be deleted: ");
        int incomeID = sc.nextInt();
        incomeDAO.deleteIncome(incomeID);
    }
}
