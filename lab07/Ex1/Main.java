package Ex1;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date datestart = new Date();
        Date dateend = new Date(2030,1,31);
        EmployeeInterface emp = new Employee("Joao");
        TeamMember tm = new TeamMember(new Employee("Jose"));
        TeamLeader tl = new TeamLeader(tm);
        Manager man = new Manager(emp);

        EmployeeInterface[] list = {emp,tm,tl,man};

        for( EmployeeInterface x : list){
            x.start(datestart);
            x.terminate(dateend);
            x.work();
        }

        tm.colaborate();
        tl.plan();
        man.manage();





    }
}
