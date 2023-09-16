import java.util.ArrayList;
public class Insurance {
    private static ArrayList<Employee> policies = new ArrayList<>();

    public static boolean regist(Employee e) {
        if (policies.contains(e)) {
            return false;
        }
        else {
            policies.add(e);
            System.out.println(e.getPerson().getName() + " has an Insurance policy updated.");
            return true;
        }
    }
    
    public ArrayList<Employee> getPolicies() {
        return policies;
    } 
}