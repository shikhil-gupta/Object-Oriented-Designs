import lombok.Getter;

@Getter
public class ATM implements Runnable {
    private ICashDispenser cashDispenser;
    private CustomerConsole customerConsole;
    private ICardReader cardReader;
    private ConnectionToBank connectionToBank;
    private String bankName;
    private String place;
    private String bankIPAddress;
    private String atmState;


    @Override
    public void run() {

        Session session = null;

        while (true) {
            switch (atmState) {

                case "OFF_STATE":
                    break;
                case "IDLE_STATE":
                    break;
                case "SERVING_CUSTOMER_STATE":
                    break;
            }

        }

    }
}
