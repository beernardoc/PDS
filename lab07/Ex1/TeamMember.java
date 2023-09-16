package Ex1;

import java.util.Date;

public class TeamMember extends EmpDecorator {
    TeamMember(EmployeeInterface emp) {
        super(emp);
    }

    @Override
    public void start(Date date) {
        emp.start(date);
        System.out.println("TeamMember");
    }


    @Override
    public void terminate(Date date) {
        emp.terminate(date);
        System.out.println("TeamMember");
    }

    @Override
    public void work() {
        emp.work();
        System.out.println("TeamMember");
    }

    public void colaborate(){
        System.out.println("TeamMember colaborate");
    }
}
