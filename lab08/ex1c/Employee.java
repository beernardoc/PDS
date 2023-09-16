
class Employee {
    private double salary;
    private Person person;
    private String bank;
    private double initialDeposit;
    private BankAccountImpl bankAcc;
    private Card card;

    public Employee (Person p, double s){
        this.person = p;
        this.salary = s;
        this.bankAcc = new BankAccountImpl(bank, initialDeposit);
    }
    public double getSalary() {
        return salary;
    }
    public Person getPerson() {
        return person;
    }
    public BankAccount getBankAccount() {
        return bankAcc;
    }
    public void addCard(Card c) {
        this.card = c;
    }
    public String getCard() {
        return this.card.getCard();
    }
}
