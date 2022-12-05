package controller;

public class PaymentInfo {

    private String cardHolderName;
    private String cardNumber;
    private int cvv;

    public PaymentInfo(String cardHolderName, String cardNumber, int cvv) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }
}
