class proxy implements BankAccount {
    private BankAccount bankAccount;

    public proxy(String bank,double initial) {
        this.bankAccount = new BankAccountImpl(bank, initial);
    }

    @Override
    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (Company.user == User.OWNER) {
            return bankAccount.withdraw(amount);
        } else {
            System.out.println("operacao nao autorizada (para a empresa)");
            return false;
        }
    }

    @Override
    public double balance() {
        if (Company.user == User.OWNER) {
            return bankAccount.balance();
        } else {
            System.out.println("operacao nao autorizada (para a empresa)");
            return 0.0;
        }
    }
}