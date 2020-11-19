public class CardReaderImpl implements ICardReader {
    @Override
    public boolean validateCard(ATMCard atmCard) {
        return false;
    }

    @Override
    public boolean readCard(ATMCard atmCard) {
        return false;
    }
}
