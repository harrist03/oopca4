package DTO;

public class IncomeDTO {
    private int incomeID;
    private String title;
    private double amount;
    private String date;

    public IncomeDTO(int incomeID, String title, double amount, String date) {
        this.incomeID = incomeID;
        this.title = title;
        this.amount = amount;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "IncomeDTO{" +
                "incomeID=" + incomeID +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
