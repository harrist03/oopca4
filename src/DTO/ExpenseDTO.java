package DTO;

public class ExpenseDTO {
    private int expenseID;
    private String title;
    private String category;
    private double amount;
    private String dateInccured;

    public ExpenseDTO(int expenseID, String title, String category, double amount, String dateInccured) {
        this.expenseID = expenseID;
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

    public void setDate(String dateInccured) {
        this.dateInccured = dateInccured;
    }

    @Override
    public String toString() {
        return "ExpenseDTO{" +
                "expenseID: " + expenseID +
                ", title: '" + title + '\'' +
                ", category: '" + category + '\'' +
                ", amount: " + amount +
                ", date: '" + dateInccured + '\'' +
                '}';
    }
}
