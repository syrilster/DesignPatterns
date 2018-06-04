package VendingMachineAdvanced;

public enum Coin {
    NO_COIN(0), CENT(1), PENNY(5), NICKLE(10), QUARTER(25);

    private int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}
