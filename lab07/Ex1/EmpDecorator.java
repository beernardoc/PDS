package Ex1;

import java.util.Date;

public abstract class EmpDecorator implements EmployeeInterface {

    protected EmployeeInterface emp;
    EmpDecorator(EmployeeInterface emp){
        this.emp = emp;
    }

    @Override
    public void start(Date date) {
        emp.start(date);

    }

    @Override
    public void terminate(Date date) {
        emp.terminate(date);
    }

    @Override
    public void work() {
        emp.work();

    }




}
