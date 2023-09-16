package Ex1;
public class PortionFactory {


    public static Portion create(String name, Temperature temp){


        if(name == "Meat"){
            if(temp == Temperature.COLD)
                return new Tuna();
            
            else
                return new Pork();
        }
        else
            if(temp == Temperature.COLD)
                return new FruitJuice();
    
            else
                return new Milk();

        
    }

    




    
    




}
