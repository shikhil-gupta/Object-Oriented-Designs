package booking.movie;

import lombok.Getter;

@Getter
public class Account implements IAccount {

    private String username;
    private String emailid;
    private Integer userid;
    private AccountStatus status;
}
