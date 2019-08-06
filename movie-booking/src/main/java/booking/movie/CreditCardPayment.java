package booking.movie;

import java.util.Date;

public class CreditCardPayment implements Payment {

    private String creditcardno;
    private String cvv;
    private String name;
    private Date validity;


    public CreditCardPayment(String creditcardno, String cvv, String name, Date validity) {
        this.creditcardno=creditcardno;
        this.cvv=cvv;
        this.name=name;
        this.validity=validity;
    }

    @Override
    public void pay() {
        System.out.println("payment done using Credit card");
    }

}
