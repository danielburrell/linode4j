package uk.co.solong.linode;

public enum PaymentTerm {
    ONE_MONTH(1), TWELVE_MONTHS(12), TWENTYFOUR_MONTHS(24);
    private int paymentTerm;

    PaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

}
