public class Card {
    private static int employeeNumber = 0;
    private int cardNumber;
    public Card(Employee e, double salary) {
        this.cardNumber = ++employeeNumber;
    }
    public String getCard() {
        return "Number = " + this.cardNumber;
    }
}
