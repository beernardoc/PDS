import java.io.FileNotFoundException;
import java.util.Scanner;
public class Ex2 {

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean selec = true;
            Commands commands = new Commands();
            if(args.length==1){
               String arq=args[0];
               if(!arq.contains(".txt")){
                arq=arq+".txt";
               }
                try {
                    commands.getCommands(arq);//arquve name
                    System.exit(0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
            while(selec){
                System.out.println("Escolha uma opção (H para ajuda)");
                String op = sc.nextLine();
                String[] command=op.split(" ");
                String option=op.substring(0,1).toUpperCase();
                switch(option){
                    case "H":
                    commands.menu();
                    break;
   
                    case "I":                    
                        String arquivo=op.substring(2);
                        Voo voo =new Voo(arquivo);
                        commands.getList().add(voo);
                    break;
                    case "Q":
                        System.out.println("Saindo...");
                        selec = false;
                    break;
                    case "F": 
                        commands.addVoo(command);
                    break;
                    case "R":
                        commands.optionR(command);
                    break;
                    case "M":            
                        commands.showVooMap(command);
                        
                    break;
                    case "C":
                    commands.optionC(command, sc);
                    break;
                    default:
                        System.out.println("Escolha uma  opção ou prima Q para sair");
                    break;
                }
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

    
}
