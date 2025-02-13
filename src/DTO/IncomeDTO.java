package DTO;

public class IncomeDTO {
    private int incomeID;
    private String title;
    private double amount;
    private String dateEarned;

    public IncomeDTO (String title, double amount, String dateEarned) {
        // incomeID is not included in the constructor as it is auto-incremented in the database
        this.title = title;
        this.amount = amount;
        this.dateEarned = dateEarned;
    }

    // non argument constructor
    public IncomeDTO() {
    }

    // getters and setters
    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateEarned() {
        return dateEarned;
    }

    public void setDate(String dateEarned) {
        this.dateEarned = dateEarned;
    }

    @Override
    public String toString() {
        return "[" +
                "incomeID: " + incomeID +
                ", title: '" + title + '\'' +
                ", amount: " + amount +
                ", date: '" + dateEarned + '\'' +
                ']';
    }
}
