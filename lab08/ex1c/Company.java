
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Company {
    public static User user;
    private List<Employee> emps = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private SocialSecurity socialSecurity = new SocialSecurity();
    private Insurance insurance = new Insurance();
    private ParkingLicense parkingLicense = new ParkingLicense();

    public void admitPerson(String name) {
        Person p = new Person(name);
        persons.add(p);
    }
    public void admitEmployee(Person person, double salary) {
        Employee e = new Employee(person, salary);
        emps.add(e);
        e.addCard(new Card(e,salary));
        socialSecurity.regist(e);
        insurance.regist(e);
        parkingLicense.allow(e);
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getBankAccount();
            System.out.println(e.getSalary());
            ba.deposit(e.getSalary());
        }
    }
    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }

    public SocialSecurity getSocialSecurity() {
        return this.socialSecurity;
    }
    
    public Insurance getInsurance() {
        return this.insurance;
    }

    public ParkingLicense getParkingLicense() {
        return this.parkingLicense;
    }
}
