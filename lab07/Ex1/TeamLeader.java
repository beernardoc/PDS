package Ex1;

import java.util.Date;

public class TeamLeader extends EmpDecorator{

    TeamLeader(EmployeeInterface emp) {
        super(emp);
    }


    @Override
    public void start(Date date) {
        emp.start(date);
        System.out.println("TeamLeader");
    }


    @Override
    public void terminate(Date date) {
        emp.terminate(date);
        System.out.println("TeamLeader");
    }

    @Override
    public void work() {
        emp.work();
        System.out.println("TeamLeader");
    }

    public void plan(){
        System.out.println("TeamLeader plan");
    }
}
