import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsabilityDemo {
    public static void main(String[] args) {
        List <String> orders = new ArrayList<>();
        orders.add("Can I please get a veggie burger?");
        orders.add("Can I please get a Pasta Carbonara?");
        orders.add("Can I please get a PLAIN pizza, no toppings!?");
        orders.add("Can I please get a sushi nigiri and sashimi?");
        orders.add("Can I please get a salad with tuna?");
        orders.add("Can I please get a strawberry ice cream and waffles dessert?");

        Cozinheiro ordersCooker = new SushiChef().setSucessor(
                                    new PastaChef().setSucessor(
                                        new BurgerChef().setSucessor(
                                            new PizzaChef().setSucessor(
                                                new DessertChef()))));

        for (String order : orders) {
            System.out.println(order);
            ordersCooker.cozinha(order);
        }                         
    }
}
