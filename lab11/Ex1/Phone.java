public class Phone {

    private String processador;
    private double price;
    private int memory;
    private int camera;


     public Phone(String processador,double price,int memory,int camera){
         this.camera = camera;
         this.processador = processador;
         this.price = price;
         this.memory = memory;

     }

    public double getPrice() {
        return price;
    }

    public int getCamera() {
        return camera;
    }

    public int getMemory() {
        return memory;
    }

    public String getProcessador() {
        return processador;
    }
}
