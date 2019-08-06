package booking.movie;

public enum  AccountStatus {
    ACTIVE("active"), BLOCKED("blocked");

    private String accountstatus;

    AccountStatus(String accountstatus) {
        this.accountstatus=accountstatus;
    }

    public String getAccountstatus() {
        return accountstatus;
    }
}
