import java.util.List;


class Voo{
    private String flight_code;
    private Aviao aviao;
    private List<String> reservarions; //reservetions list from txt
    private int reservation_code=1;


    public Aviao getAviao() {
        return aviao;
    }
    public String getFlight_code() {
        return flight_code;
    }
    public List<String> getReservarions() {
        return reservarions;
    }
    
    Voo(String flight_code, String execConf , String TuristcConf){
        this.flight_code=flight_code;
        this.aviao=new Aviao();
        aviao.getPlacesConf(execConf,TuristcConf);
        aviao.fillplaces();
    }
    Voo(String filename){
        this.aviao=new Aviao();
        this.aviao.readData(filename);
        this.reservarions =this.aviao.getListOfReservations();
        this.flight_code=reservarions.get(0);
        System.out.printf("Código de voo %s. Lugares disponíveis: %d lugares em classe Executiva; %d lugares em classe Turística.\n",flight_code,(aviao.getNumFilasExecutiva()*aviao.getNumLugaresPorFilaExecutiva()),(aviao.getNumFilasTuristica()*aviao.getNumLugaresPorFilaTuristica()));

        
        for(int i=1; i<reservarions.size(); i++){
            if(MakeReservation(reservarions.get(i).split(" ")[0], Integer.parseInt((reservarions.get(i).split(" ")[1])),reservation_code)
            ){
               reservation_code++;
            }
        }
    }

    public int getReservation_code() {
        return reservation_code;
    }
    public void setReservation_code(int reservation_code) {
        this.reservation_code = reservation_code;
    }
    public boolean MakeReservation(String classe ,int placesnum,int reservation_code){ //logica errada
    
        switch(classe){

            case "E":
            if(!aviao.getHasExecutivePlaces()){
                System.out.println("Classe executiva não disponível neste voo");
                System.out.println("Nao foi possivel fazer a reserva "+ classe +" "+ placesnum);
                return false;
            }else{ 
                if(placesnum<=(aviao.getNumFilasExecutiva()*aviao.getNumLugaresPorFilaExecutiva())){
                       return fazerReserva(aviao.getPlaces(), "E" , placesnum,reservation_code);
                 }else{
                    System.out.println("Sem lugares para a reserva "+ classe +" "+ placesnum);
                }
            }   
            break;
            case "T":
                if(placesnum<=(aviao.getNumFilasTuristica()*aviao.getNumLugaresPorFilaTuristica())){
                   return fazerReserva(aviao.getPlaces(), "T" , placesnum,reservation_code);
                }else{
                    System.out.println("Sem lugares para a reserva "+ classe +" "+ placesnum);
                    
                }
            break;
        }
        return false;
    }

    public boolean fazerReserva(String [][] matrix, String classe,int placenum,int reservation) {
        int linhas=aviao.getNumLugaresPorFilaExecutiva();
        int colunas= aviao.getNumFilasExecutiva();
        int jstart=0;
        String reservation_code=String.valueOf(reservation);
        if(classe=="E"){
             linhas=aviao.getNumLugaresPorFilaExecutiva();
             colunas= aviao.getNumFilasExecutiva();
        }else if (classe=="T"){
            if(aviao.getHasExecutivePlaces()){
                jstart=colunas;
            }else{
                jstart=0;
            }

            linhas=aviao.getNumLugaresPorFilaTuristica();
            colunas= aviao.getPlaces()[1].length;
        }
        int assentosReservados = 0;       
        int colunasvazias=0;
        int count=0;
        for (int j = jstart; j < colunas; j++) {
            boolean colunaVazia = true;
            for (int i = 0; i < linhas; i++) {
                if (matrix[i][j] != "0") {
                    colunaVazia = false;
                    break;
                }
            }
            if (colunaVazia) {
                colunasvazias++;
            }

            if(colunasvazias>0){

                    for (int i = 0; i < linhas;i++){
                        if (assentosReservados < placenum && matrix[i][j]=="0") {
                            matrix[i][j] =reservation_code;
                            assentosReservados++;
                        }
                    
                    if (assentosReservados == placenum) {
                        return true;
                    }
                }
              
           }

        }
        if(colunasvazias==0 || assentosReservados!=placenum) {
               // Se não houver fila vazia, distribui os lugares vagos sequencialmente ou ...
               rerun:
               for (int i = 0; i <linhas; i++) {
                   for (int j = jstart; j <colunas; j++) {
                       if (matrix[i][j] == "0") {
                           if(count==placenum){
                               break rerun;
                           }
                           count++;
                       }
                      
                   }
               }    
               if(count==placenum-assentosReservados){
                   for (int i = 0; i <linhas; i++) {
                       for (int j = jstart; j <colunas; j++) {
                           if (matrix[i][j] == "0") {
                               matrix[i][j] =reservation_code;
                               assentosReservados++;
                           }
                           if (assentosReservados == placenum) {
                               System.out.println("Assentos reservados com sucesso!");
                               return true;
                           }
                       }
                   }    
               }else{
                    CancelReservarion(reservation_code);
                   System.out.println("Não foi possivel fazer a reserva " + classe+ " " + placenum);

                   return false;
               }
        
       }
    
 
        return false;
    }

    public void CancelReservarion(String reservation){
        System.out.println("->"+reservation);
        String tempPlaces[][]=aviao.getPlaces();
        for(int j=0;j<tempPlaces.length;j++){
            for(int k=0;k<tempPlaces[1].length;k++){
                if(tempPlaces[j][k].equals(reservation)){
                    tempPlaces[j][k]="0";
                }
            }
        }
        aviao.setPlaces(tempPlaces);
    }


}