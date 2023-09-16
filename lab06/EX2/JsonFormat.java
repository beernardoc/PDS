import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class JsonFormat implements ContactsStorageInterface{
    FileReader reader;
    private String filename="JsonWriter.json";
    private List<Contact> contactsList;
    JsonFormat(String file ){
        try{
            this.reader=new FileReader(file);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    JsonFormat(){
        this.filename="OptionaJsonWriter.json";
    }
    @Override
    public List<Contact> loadContacts() {
        JSONParser jsonParser = new JSONParser();

        try{
            contactsList=new ArrayList<>();
            Object obj=jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;
     
            for (Object iterable_element : employeeList) {
                String JsonTOString[]=parseEmployeeObject( (JSONObject) iterable_element).split("-");

                Contact contact = new Contact(Integer.valueOf(JsonTOString[1]),JsonTOString[0]);
                contactsList.add(contact);
            }

            return contactsList;
        }catch (Exception e){
            System.out.println(e);
        }



        return  contactsList;
    }


	@Override
    public boolean saveContacts(List<Contact> list) {
        JSONArray ContactsList = new JSONArray();
        JSONObject contaObject;
        JSONObject contactDetails;
        for(Contact contact:list){
            contactDetails=new JSONObject();
            contactDetails.put("name", contact.getNome());
            contactDetails.put("nmec", contact.getNmec());
            contaObject=new JSONObject();
            contaObject.put("contacto", contactDetails);
            ContactsList.add(contaObject);

        }
        
        

        try (FileWriter file = new FileWriter(this.filename)) {
            file.write(ContactsList.toJSONString()); 
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return false;
    }

    private String parseEmployeeObject(JSONObject employee) 
    {
        JSONObject employeeObject = (JSONObject) employee.get("contacto");
         
        String firstName = (String) employeeObject.get("name");    
         
        int nmec = Integer.valueOf(employeeObject.get("nmec").toString());   
        

        return firstName+"-"+nmec;
        
    }

}