

public class GestordeInterface{

    ContactsStorageInterface interfacetype;
    private String filename;
    GestordeInterface(String file){
        this.filename=file;
    }

    private String getExtension(String file) {
        if(file.contains(".txt"))return "txt";
        if(file.contains(".bin"))return "bin";
        if(file.contains(".json"))return "json";

        return null;
    }

    public ContactsStorageInterface getType(){
        String format=getExtension(filename);

        switch(format){
            case "txt":
            interfacetype=new TxtFormat(filename);
            return interfacetype;

            case "bin":
            interfacetype=new BinFormat(filename);
            return interfacetype;

            case "json":
            interfacetype = new JsonFormat(filename);
            return interfacetype;
        }
      return null;

    }
    
}
