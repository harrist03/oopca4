package DTO;

public class ExpenseDTO {
    private int expenseID;
    private String title;
    private String category;
    private double amount;
    private String dateInccured;

    public ExpenseDTO(String title, String category, double amount, String dateInccured) {
        // expenseID is not included in the constructor as it is auto-incremented in the database
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateInccured = dateInccured;
    }

    // non argument constructor
    public ExpenseDTO() {
    }

    // getters and setters
    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return dateInccured;
    }

    public void setDateIncurred(String dateInccured) {
        this.dateInccured = dateInccured;
    }

    @Override
    public String toString() {
        return String.format("%-10d | %-20s | %-20s| %-10.2f | %-10s ", expenseID, category, title, amount, dateInccured);
    }
}
