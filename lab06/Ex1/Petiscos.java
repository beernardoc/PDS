import java.util.List;
class Petiscos implements operations{ 
    private Registos db=new Registos();  
    @Override
    public void add(Empregado empregado,Employee employee) {
        if(!db.listaDeEmpregados().contains(empregado)){
            db.insere(empregado);
        }else{
            System.out.println("Empregado já cadastrado");
        }
    }
    @Override
    public void remove(int emp_num) {
        // TODO Auto-generated method stub
        if(isEmpregado(emp_num)){
            db.remove(emp_num);
        }else{
            System.out.println("Empregado não encontrado");
        }
    }
    @Override
    public void Listemployees() {
        List<Empregado> employees = db.listaDeEmpregados();
        for(int i=0;i<employees.size();i++){
            System.out.println(employees.get(i).nome()+" "+employees.get(i).apelido() +" " + employees.get(i).codigo() +" " + employees.get(i).salario());
        }
    }
    @Override
    public boolean isEmpregado(int emp_num) {
        // TODO Auto-generated method stub
        return db.isEmpregado(emp_num);
    }
}