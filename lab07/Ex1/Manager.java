package Ex1;

import java.util.Date;

public class Manager extends EmpDecorator{

    Manager(EmployeeInterface emp) {
        super(emp);
    }

    @Override
    public void start(Date date) {
        emp.start(date);
        System.out.println("Manager");
    }


    @Override
    public void terminate(Date date) {
        emp.terminate(date);
        System.out.println("Manager");
    }

    @Override
    public void work() {
        emp.work();
        System.out.println("Manager");
    }

    public void manage(){
        System.out.println("Manager manage");
    }
}
