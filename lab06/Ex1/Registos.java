import java.util.ArrayList;
import java.util.List;

class Registos {
    // Data elements
    private ArrayList<Empregado> empregados; // Stores the employees
    public Registos() {
    empregados = new ArrayList<>();
    }
    public void insere(Empregado emp) {

        if(!empregados.contains(emp))
            empregados.add(emp);
    }
    public void remove(int codigo) {
    // Code to remove employee
    empregados.removeIf(n -> (n.codigo() == codigo));
    }
    public boolean isEmpregado(int codigo) {
    // Code to find employee

    for(Empregado e : empregados){
        if(e.codigo() == codigo)
            return true;
    }
    return false;
    
    }
    public List<Empregado> listaDeEmpregados() {
    // Code to retrieve collection
        return empregados;
    }
    }