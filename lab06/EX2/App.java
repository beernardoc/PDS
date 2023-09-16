import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App implements ContactsInterface{

    private ContactsStorageInterface interfaceFormat;
    private List<Contact> ListofContacts;

    App(GestordeInterface interfaceFormat){
        this.interfaceFormat=interfaceFormat.getType();
        this.ListofContacts=new ArrayList<Contact>();
    }

    public List<Contact> getListofContacts() {
        return ListofContacts;
    }

    @Override
    public void openAndLoad(ContactsStorageInterface store) {       
        ListofContacts.addAll(interfaceFormat.loadContacts());
        System.out.println("Gravado com sucesso");
    }

    @Override
    public void saveAndClose() {

            Scanner sc=new Scanner( System.in);
            System.out.println("Insira em que extencao deseja salvar:(txt/bin/json) ");
            String extencao=sc.nextLine();
            sc.close();
            ContactsStorageInterface OptionalInterface=null;
            switch (extencao){
                case "txt":
                OptionalInterface =new TxtFormat();
                break;
    
                case "json":
                OptionalInterface=new JsonFormat();
                break;
    
                case "bin":
                OptionalInterface=new BinFormat();
                break;
    
                default:
                System.out.println("Insira uma extencao válida");
                break;
            
    
            }
            OptionalInterface.saveContacts(ListofContacts);
        
  
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(ListofContacts);
        this.interfaceFormat=null;

    }

    @Override
    public boolean exist(Contact contact) {
        
        if(ListofContacts.contains(contact)){
            return true;
        }
        return false;
    }

    @Override
    public Contact getByName(String name) {
    
        for(Contact contact:ListofContacts){
            if(contact.getNome().equals(name)){
                return contact;
            }
        }
        return null;
    }

    @Override
    public boolean add(Contact contact) {      
        if(!exist(contact)){
            ListofContacts.add(contact);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Contact contact) {
        if(exist(contact)){
            ListofContacts.remove(contact);
            return  true;
        }
        return false;

    }
    
}
