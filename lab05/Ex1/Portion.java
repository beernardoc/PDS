package Ex1;
public interface Portion{
    public Temperature getTemperature();
    public State getState();}
    
enum State {
    Solid, Liquid;}
    
enum Temperature {
    WARM, COLD;}


class Milk implements Portion{

    State estado = State.Liquid;
    Temperature temp =  Temperature.WARM;


    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }


    public String toString() {

        return "Milk: Temperature " + temp + ", " + "State " + estado;
        
    }
    
}

class FruitJuice implements Portion{

    State estado = State.Liquid;
    Temperature temp =  Temperature.COLD;
    String others = "Orange";


    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }

    public String toString() {

        return "FruitJuice: " + others + ", Temperature " + temp + ", " + "State " + estado;
        
    }

    
}

class Tuna  implements Portion{

    State estado = State.Solid;
    Temperature temp =  Temperature.COLD;
    String others = "";

    @Override
    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }

    public String toString() {

        return "Tuna: Temperature " + temp + ", " + "State " + estado;
        
    }

    
}

class Pork implements Portion{
    State estado = State.Solid;
    Temperature temp =  Temperature.WARM;
    String others = "";

    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }

    public String toString() {

        return "Pork: Temperature " + temp + ", " + "State " + estado;
        
    }

    
    
}
