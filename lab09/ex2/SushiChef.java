class SushiChef extends Cozinheiro {
    public void cozinha(String food) {
        if (canCookFood(food, "sushi")) {
            food = food.replace("Can I please get a ","");
            String [] foods;
            foods = food.split("\\?");
            System.out.println("Sushi Chef: Starting to cook " + foods[0]);
            System.out.println();
            
        }            
        else {
            System.out.println("Sushi Chef: I can't cook that.");
            super.cozinha(food);
        }
    }
}
