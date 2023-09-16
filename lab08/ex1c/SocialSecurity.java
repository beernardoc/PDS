import java.util.ArrayList;
public class SocialSecurity {
    private static ArrayList<Employee> policies = new ArrayList<>();

    public static boolean regist(Employee e) {
        if (policies.contains(e)) {
            return false;
        }
        else {
            policies.add(e);
            System.out.println(e.getPerson().getName() + " has a Social Security policy updated.");
            return true;
        }
    }

    public ArrayList<Employee> getPolicies() {
        return policies;
    } 
}
