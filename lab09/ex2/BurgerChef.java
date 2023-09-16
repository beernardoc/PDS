class BurgerChef extends Cozinheiro {
    public void cozinha(String food) {
        if (canCookFood(food, "burger")) {
            food = food.replace("Can I please get a ","");
            String [] foods;
            foods = food.split("\\?");
            System.out.println("Burger Chef: Starting to cook " + foods[0]);
            System.out.println();

        }            
        else {
            System.out.println("Burger Chef: I can't cook that.");
            super.cozinha(food);
        }
    }
}
