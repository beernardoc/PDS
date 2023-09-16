class DessertChef extends Cozinheiro {
    public void cozinha(String food) {
        if (canCookFood(food, "dessert")) {
            food = food.replace("Can I please get a ","");
            String [] foods;
            foods = food.split("\\?");
            System.out.println("Dessert Chef: Starting to cook " + foods[0]);
            System.out.println();
        }            
        else {
            System.out.println("Dessert Chef: I can't cook that.");
            super.cozinha(food);
        }
    }
}