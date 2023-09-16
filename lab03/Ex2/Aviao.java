import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
class Aviao implements Confi{
    private String places[][];
    private boolean hasExecutivePlaces=false;
    private List<String> ListOfReservations=new ArrayList<>();
    private int numFilasExecutiva;
    private int numLugaresPorFilaExecutiva;
    private int numFilasTuristica;
    private int numLugaresPorFilaTuristica;

    public int getNumFilasExecutiva() {
        return numFilasExecutiva;
    }
    public int getNumLugaresPorFilaExecutiva() {
        return numLugaresPorFilaExecutiva;
    }
    public int getNumFilasTuristica() {
        return numFilasTuristica;
    }
    public int getNumLugaresPorFilaTuristica() {
        return numLugaresPorFilaTuristica;
    }
    public boolean getHasExecutivePlaces() { 
        return this.hasExecutivePlaces;
    }
    @Override
    public void readData(String filename) {
        Scanner datareader=null;
        try {
            File f = new File(filename);
            datareader=new Scanner(f);
            while (datareader.hasNext()) {
                String line=datareader.nextLine();
                if(line.startsWith(">")){
                    String flighcode=line.split(" ")[0].replace(">","");
                    ListOfReservations.add(flighcode);
                    if(line.split(" ").length==3){ // existe executiva
                        hasExecutivePlaces=true;
                        getPlacesConf(line.split(" ")[1] , line.split(" ")[2]);                        

                    }else{
                        getPlacesConf(null , line.split(" ")[1]);                        
                    }                

                }else{// reservas
                    ListOfReservations.add(line);
                }
            }
            fillplaces();// all places are available
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            datareader.close();;
        }

    }
    @Override
    public void getPlacesConf(String execConf , String TuristcConf) {
        int line=0;
        int col=0;
        if(execConf!=null)hasExecutivePlaces=true;
        numFilasTuristica=Integer.parseInt(TuristcConf.split("x")[0]);
        numLugaresPorFilaTuristica=Integer.parseInt(TuristcConf.split("x")[1]);
        if(hasExecutivePlaces){
            col= Integer.parseInt(execConf.split("x")[0])+Integer.parseInt(TuristcConf.split("x")[0]);
            numFilasExecutiva= Integer.parseInt(execConf.split("x")[0]);
            numLugaresPorFilaExecutiva=Integer.parseInt(execConf.split("x")[1]);
            if(numLugaresPorFilaTuristica>=numLugaresPorFilaExecutiva){
                line=numLugaresPorFilaTuristica;
            }else{
                line=numLugaresPorFilaExecutiva;
            }
        }else{
            line=numLugaresPorFilaTuristica;
            col=numFilasTuristica;

        } 
        setPlaces( new String[line][col]);
    
    }
    public void setPlaces(String [][] places) {
        this.places = places;
    }
    public String[][] getPlaces() {
        return places;
    }
    public void fillplaces(){
        for(int i=0;i<places.length;i++){
            for(int j=0;j<places[i].length;j++){
                this.places[i][j]="0"; // lall places are available
            }
        }
   
    }
    public List<String> getListOfReservations() {
        return ListOfReservations;
    }
   
    void fixecutiva(){
        for(int i=0;i<numFilasExecutiva;i++){
            places[numLugaresPorFilaExecutiva][i]=" ";
        }
    }
    void fixturistica(){
        for(int i=numFilasTuristica;i<places[1].length;i++){
            places[numLugaresPorFilaTuristica][i]=" ";
        }

    }
    public void printPlaces(){
        String map="   ";
        if(numLugaresPorFilaExecutiva>numLugaresPorFilaTuristica){
             fixturistica();
        }else if (numLugaresPorFilaExecutiva<numLugaresPorFilaTuristica){
             fixecutiva();
        }
    
        for(int i=1; i<=places[1].length; i++){
            map+=String.format("%3d",i);
        }
        map+="\n";
        char letra='A';
        for(int i=0;i<places.length;i++){
            map+=String.format("%3s",letra);
            for(int j=0;j<places[1].length;j++){
                map+=String.format("%3s",places[i][j]);
            }
            map+="\n";
            letra++;
            
        }   
        System.out.println(map);
    }

    
}