package Ex1;

import java.util.Date;

public class Employee implements EmployeeInterface{

    final String name;

    public Employee(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void start(Date date) {
        System.out.println("start " + name + " " + date);

    }

    @Override
    public void terminate(Date date) {
        System.out.println("terminate " + name + " " + date);
    }

    @Override
    public void work() {
        System.out.println("work " + name);

    }
}
