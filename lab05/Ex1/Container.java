package Ex1;
public class Container {

    public static Container create(Portion portion){
        
        State estado = portion.getState();
        Temperature temp = portion.getTemperature();

        if(estado == State.Liquid ){
            if( temp == Temperature.COLD)
                return new PlasticBottle();
            else
                return new TermicBottle(temp);
        }
        else if(temp == Temperature.WARM)
            return new Tupperware(temp);
        
        return new PlasticBag();


            

    }


}


class PlasticBottle extends Container implements Portion{
    State estado = State.Liquid;
    Temperature temp =  Temperature.COLD;


    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }

    @Override
    public String toString() {
        return "PlasticBottle with portion = " + new FruitJuice();
    }

}

class TermicBottle extends Container implements Portion{
    State estado = State.Liquid;
    Temperature temp;

    public TermicBottle(Temperature temp){
        this.temp = temp;

    }


    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }

    @Override
    public String toString() {
        return "TermicBottle with portion = " + new Milk();
    }


}

class Tupperware extends Container implements Portion{
    State estado = State.Solid;
    Temperature temp;
    
    public Tupperware(Temperature temp){
        this.temp = temp;

    }


    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }

    public String toString() {
        return "Tupperware with portion = " + new Pork();
    }
}

class PlasticBag extends Container implements Portion{
    State estado = State.Solid;
    Temperature temp=  Temperature.COLD;


    public Temperature getTemperature() {
        return temp;
    }
   
    public State getState() {
        return estado;
    }

    public String toString() {
        return "PlasticBag with portion = " + new Tuna();
    }
}
