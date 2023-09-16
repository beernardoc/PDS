
import java.util.Vector;



class Database { // Data elements
    private Vector<Employee> employees; // Stores the employees
    public Database() {
    employees = new Vector<>();
    }
    public boolean addEmployee(Employee employee) {
    // Code to add employee
    
    if(employees.contains(employee))
        return false;
    
    employees.add(employee);
    return true;
    
    }
    public void deleteEmployee(long emp_num) {
    // Code to delete employee

    employees.removeIf(n -> (n.getEmpNum() == emp_num));



    }
    public Employee[] getAllEmployees() {
    // Code to retrieve collection

    Employee[] emp_arr = new Employee[employees.size()]; 
    return employees.toArray(emp_arr);


    }
    }