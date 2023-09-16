class Sweets implements operations{  
    private Database db= new Database();
    private Employee[] employees;
    public void Listemployees() {
        employees = db.getAllEmployees();
        for(int i=0;i<employees.length;i++){
            System.out.println(employees[i].getName() + " " + employees[i].getEmpNum()+ " " + employees[i].getSalary());
        }
        System.out.println();
    }
    @Override
    public void add(Empregado empregado, Employee employee) {
        db.addEmployee(employee);
    }
    @Override
    public void remove(int emp_num) {
        if(isEmpregado(emp_num)){
            db.deleteEmployee(emp_num);
        }else{
            System.out.println("Employee not found");
        }
    }
    @Override
    public boolean isEmpregado(int emp_num) {
        // TODO Auto-generated method stub
        for(int i=0;i<employees.length;i++){
            if(employees[i].getEmpNum()==emp_num){
                return true;
            }
        }
        return false;
    }
}