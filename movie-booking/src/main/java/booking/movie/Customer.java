package booking.movie;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Customer extends Account {

    private String role;
    private String phoneNo;
    private Timestamp lastlogin;

}
