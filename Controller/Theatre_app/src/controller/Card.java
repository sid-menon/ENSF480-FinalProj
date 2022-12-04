
package controller;


public class Card {
    
    private String cardNumber;
   
    private String exDate;
    
    private int csv;

   
    public Card(String cardNumber, String exDate, int csv) {
        this.cardNumber = cardNumber;
        this.exDate = exDate;
        this.csv = csv;
    }


    public void updateCardInfo(String cardNumber, String exDate, int csv) {
        this.cardNumber = cardNumber;
        this.exDate = exDate;
        this.csv = csv;
    }

   
    public String getCardNumber() {
        return cardNumber;
    }

   
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

 
    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public int getCsv() {
        return csv;
    }

   
    public void setCsv(int csv) {
        this.csv = csv;
    }
}
