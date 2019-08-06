package booking.movie;

import java.util.Date;

public class DebitCardPayment implements Payment {


    private String dreditcardno;
    private String cvv;
    private String name;
    private Date validity;

    public DebitCardPayment(String dreditcardno, String cvv, String name, Date validity) {
        this.dreditcardno=dreditcardno;
        this.cvv=cvv;
        this.name=name;
        this.validity=validity;
    }

    @Override
    public void pay() {

    }
}
