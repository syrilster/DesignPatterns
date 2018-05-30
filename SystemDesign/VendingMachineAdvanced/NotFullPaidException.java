package VendingMachineAdvanced;

public class NotFullPaidException extends RuntimeException {
    private String message;
    private long differenceAmount;

    public NotFullPaidException(String message, long differenceAmount) {
        this.message = message;
        this.differenceAmount = differenceAmount;
    }

    @Override
    public String getMessage() {
        return message + " Still Missing " + differenceAmount;
    }

    public long getDifferenceAmount() {
        return differenceAmount;
    }
}
