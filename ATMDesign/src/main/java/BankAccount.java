import lombok.Getter;

@Getter
public class BankAccount {
    private String bankAccount;
    private String accountType;
    private double balance;
    private Customer customerProfile;
}
