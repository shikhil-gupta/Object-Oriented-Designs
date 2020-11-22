package booking.movie;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public abstract class Account
{
    private String username;
    private Integer userId;
    private String emailId;
    private String phoneNo;
    private Timestamp lastlogin;
    private AccountStatus status;
    private String role;
}
