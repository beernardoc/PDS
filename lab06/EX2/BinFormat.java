import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class BinFormat implements ContactsStorageInterface {

    private FileInputStream file;
    private List<Contact> contactsList;
    private String outputFile="Binwriter.txt";

    BinFormat(String file) {  
        try{
            this.file=new FileInputStream(file);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    BinFormat(){
        this.outputFile="OptionalBinWriter.bin";
    }

    @Override
    public List<Contact> loadContacts() {
        
        contactsList=new ArrayList<Contact>();
        try{
            DataInputStream dataIn = new DataInputStream(file);
            while(dataIn.available() >0){
                String[] line = dataIn.readUTF().split("-");
                Contact contact=new Contact(Integer.valueOf(line[1]),line[0]);
                contactsList.add(contact);

            }
            dataIn.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return contactsList;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
       
        try {
            FileOutputStream fileOut = new FileOutputStream(this.outputFile);
            DataOutputStream dataout=new DataOutputStream(fileOut);
            
            for(Contact c : list) {
                String contact=c.getNome() + "-"+c.getNmec();
                dataout.writeUTF(contact);
            }
            dataout.close();
            fileOut.close();
            return true;
            
        } catch (IOException  e) {
            e.printStackTrace();
            return false;
        }
    
    }


 }