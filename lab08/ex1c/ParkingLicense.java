import java.util.ArrayList;
public class ParkingLicense {
    private static ArrayList<Employee> allows = new ArrayList<>();

    public static boolean allow(Employee e) {
        if (allows.contains(e)) {
            return false;
        }
        else {
            allows.add(e);
            System.out.println(e.getPerson().getName() + " has a parking slot reserved.");
            return true;
        }
    }
    
    public ArrayList<Employee> getPolicies() {
        return allows;
    } 
}