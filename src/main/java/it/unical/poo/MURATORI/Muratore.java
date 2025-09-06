package MURATORI;

public class Muratore extends Thread {
    protected Casa casa;
    protected final int compito;
    protected final String id;
    public Muratore(Casa c, int compito,String id) {
        this.casa = c;
        this.compito = compito;
        this.id = id;
    }

    public void run(){
        try{
            while(casa.inizia(compito)){
                if(compito==0){//CEMENTO
                    System.out.println("Il muratore " + id + "C si prepara per mettere il cemento.");
                    attendi(1700);
                    //System.out.println("Il muratore " + id + " ha messo il cemento.");
                    casa.termina();
                    System.out.println("Messi " + casa.file[compito][casa.indice[compito]] + " cemento in fila " +casa.indice[compito]);
                    attendi(5000);
                }
                else{//MATTONI
                    System.out.println("Il muratore " + id + "M si prepara per mettere il mattone.");
                    attendi(1500);
                    //System.out.println("Il muratore " + id + " ha messo il mattone.");
                    casa.termina();
                    System.out.println("Messi " + casa.file[compito][casa.indice[compito]] + " mattoni in fila " +casa.indice[compito]);
                    attendi(5000);
                }
            }
        }catch(InterruptedException e){
        }
    }

    public void attendi(int x) throws InterruptedException {
        Thread.sleep(x);
    }
}
