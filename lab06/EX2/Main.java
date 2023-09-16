
class Main{
    public static void main(String args[]){
        String TXTfile = "contactos.txt";
        String BINfile = "contactos.bin";
        String JsonFile = "contactos.json";

        //muda o ficheiro para testar outros arquivos 
        
        GestordeInterface  Interface=new GestordeInterface(JsonFile);
        
        App app=new App(Interface);
        app.openAndLoad(Interface.interfacetype);
        System.out.println(app.getListofContacts());



        app.saveAndClose(Interface.interfacetype);              
        //app.saveAndClose(); // grava os dados no ficheiro na extencao pretendida

        Contact FindTester=app.getByName("APG");
        Contact AddTester=new Contact(20210, "Adder");
        
        app.add(AddTester);
        System.out.println(FindTester);
        app.remove(FindTester);
        System.out.println(app.getListofContacts());



    
       
    }
}

