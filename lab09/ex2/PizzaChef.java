class PizzaChef extends Cozinheiro {
    public void cozinha(String food) {
        if (canCookFood(food, "pizza")) {
            food = food.replace("Can I please get a ","");
            String [] foods;
            foods = food.split("\\?");
            System.out.println("PizzaChef: Starting to cook " + foods[0]);
            System.out.println();

        }            
        else {
            System.out.println("Pizza Chef: I can't cook that.");
            super.cozinha(food);
        }
    }
}