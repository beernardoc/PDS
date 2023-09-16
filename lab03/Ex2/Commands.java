import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Commands {
    private ArrayList<Voo> List=new ArrayList<Voo>();
    public ArrayList<Voo> getList() {
        return List;
    }
    public void menu(){
        System.out.println("Comandos disponíveis:");
        System.out.println("I filename: Lê um ficheiro com informações de um voo");
        System.out.println("M flight_code: Exibe o mapa de reservas de um voo");
        System.out.println("F flight_code num_seats_executive num_seats_tourist: Adiciona um novo voo");
        System.out.println("R flight_code class number_seats: Efetua uma reserva");
        System.out.println("C reservation_code: Cancela uma reserva");
        System.out.println("Q: Termina o programa");
    }

    public void addVoo(String [] command){
        String num_seats_executive;
        String num_seats_turistic;
        String flightcode;
        if(command.length> 3){ // has exectuve 
            flightcode=command[1];
            num_seats_executive=command[2];
            num_seats_turistic=command[3];
        }else{
            flightcode=command[1];
            num_seats_executive=null;
            num_seats_turistic=command[2];
        }
        List.add( new Voo(flightcode,num_seats_executive,num_seats_turistic)); // adiciona novo voo;
    }

    public void showVooMap(String[] command ){
    

        String flightcode=command[1];
    
        if(List.size()==0){
            System.out.println("Sem voos ");
            return;
        }
        for(int i=0;i<List.size();i++){
            if(List.get(i).getFlight_code().equals(flightcode)){
                List.get(i).getAviao().printPlaces();
                break;
            }
        }
    }
    public void optionR(String[] command){
        if(List.size()==0){
            System.out.println("Adiciona um voo antes");
            return;
        }
        if(command.length<3){
            System.out.println("invalid option format , press H to show help");
        }
        String flightcode=command[1];
        String classe=command[2];
        int number_seats=Integer.parseInt(command[3]);
        for(int i=0;i<List.size();i++){
            if(List.get(i).getFlight_code().equals(flightcode)){
                if(List.get(i).MakeReservation(classe, number_seats, List.get(i).getReservation_code())
                ){
                    int valor =number_seats;
                    char ultimaLetra = (char) ('A' + valor - 1);
                        System.out.print(flightcode+":"+List.get(i).getReservation_code()+" = " );
                        for (char letra = 'A'; letra <= ultimaLetra; letra++) {
                            System.out.print(String.valueOf(valor)+letra + "| ");
                        }
                    
                        int res=List.get(i).getReservation_code();
                        res++;
                        List.get(i).setReservation_code(res);      
                        System.out.println();
                }       
                break;
            }
        }

    }
    public void optionC(String command[], Scanner sc){
        if(List.size()==0){
            System.out.println("Adiciona um voo antes");
            return;
        }
        String reservation_code=command[1];
        if(reservation_code.equals("0")){
            System.out.println("Reserva invalida");
            return;
        }
        System.out.println("Insira o codigo do voo da reserva que quer cancelar");
        String flightcode=sc.nextLine();
        rerun:
        for(int i=0;i<List.size();i++){
            if(List.get(i).getFlight_code().equals(flightcode)){
                List.get(i).CancelReservarion(reservation_code);
                System.out.println("Reserva "+reservation_code + " do voo "+flightcode +" cancelada"  );
                break rerun; 
            }else if(i==List.size()-1){
                System.out.println("Voo nao encontrado");
            }
        }

 
    }
    public void getCommands(String arquivo) throws FileNotFoundException{
        Scanner sc=new Scanner(new File(arquivo));
        
        while(sc.hasNext()){
            String line[]= sc.nextLine().split(" ");
            String option=line[0].toUpperCase();
            switch(option){
                case "I":
                String filename=line[1];
                Voo voo =new Voo(filename);
                List.add(voo);
                break;
                case "C":
                optionC(line, sc);
                break;
                case "H":
                menu();
                break;
                case "F":
                addVoo(line);
                break;
                case "M":
                showVooMap(line);
                break;
                case "R":
                optionR(line);
                break;
                case "Q":
                System.out.println("Saindo");
                System.exit(1);
                break;
                default:
                System.out.println("invalid option");
                break;
            }
        }
        
    }
}