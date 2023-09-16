import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtFormat implements ContactsStorageInterface {

    private File file;
    private List<Contact> contactsList;
    private String outputFile="TxTwriter.txt";

    TxtFormat(String file){
        try {
            this.file=new File(file);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    TxtFormat(){
        this.outputFile="OptionaTXT.txt";
    }
    @Override
    public List<Contact> loadContacts() {
        Scanner sc=null;
        contactsList=new ArrayList<Contact>();
        try{
 
        sc=new Scanner(file);

        while(sc.hasNextLine()){
            String[] linha=sc.nextLine().split("\\s");

            Contact contact=new Contact(Integer.valueOf(linha[1]),linha[0]);
            
            contactsList.add(contact);
        }
        }catch(Exception e){
            System.out.println(e);
        }

        sc.close();
        return contactsList;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try {
            FileWriter writer = new FileWriter(this.outputFile);
            PrintWriter printWriter = new PrintWriter(writer);
            for(Contact c : list) {
                printWriter.print(c.toString() + " ");
            }
            printWriter.close();
            writer.close();
            return true;
            
        } catch (Exception e) {
            return false;
        }

        
    }
}
