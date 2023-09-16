import java.util.ArrayList;

class Ex1{

    public static void main(String[] args) {
        ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
        ArrayList<Empregado> EmpregadoList = new ArrayList<Empregado>();

        EmployeeList.add(new Employee ("Maria Rodrigues", 234, 765.0));
        EmployeeList.add(new Employee ("Valter Rosa", 235, 765.0));
        EmployeeList.add(new Employee ("Teresa Silva", 236, 770.0));

        
        Empregado e1 = new Empregado("João", "Fernandes", 1, 760.0);
        Empregado e2 = new Empregado("Anita", "Rosário", 2, 850.0);
        Empregado e3 = new Empregado("Col", "Needham", 3, 999.0);
        EmpregadoList.add(e3);EmpregadoList.add(e2);EmpregadoList.add(e1);
        
        operations[] empresas= { new Sweets(),new Petiscos()};

        for(int i= 0; i< empresas.length; ++i){
            for(int j=0;j<EmpregadoList.size();j++){
                empresas[i].add(EmpregadoList.get(j),EmployeeList.get(j));
            }           
            empresas[i].Listemployees();;

        }
        System.out.println();
        System.out.println("After removing Valter from Sweets");
        empresas[0].remove(235);
        empresas[0].Listemployees();

        System.out.println("After removing Anita from Petiscos");
        empresas[1].remove(2);
        empresas[1].Listemployees();


        System.out.println();
        System.out.println("Existe empregado com codigo 234 na empresa Sweet(database employeelist)? "+ empresas[0].isEmpregado(234) );
        System.out.println("Existe empregado com codigo 234 na empresa Petiscos(database EmpregadoList)? "+ empresas[1].isEmpregado(234) );

        System.out.println();

        System.out.println("Existe empregado com codigo 1 na empresa Petiscos(database EmpregadoList)? "+ empresas[1].isEmpregado(1) );
        System.out.println("Existe empregado com codigo 1 na empresa Sweet(database employeelist)? "+ empresas[0].isEmpregado(1));

        
    }
}