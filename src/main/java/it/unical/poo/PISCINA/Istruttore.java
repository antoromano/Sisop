package PISCINA;

public class Istruttore extends Thread{
    protected Piscina piscina;
    public Istruttore(Piscina p){
        this.piscina = p;
    }
    public void run(){
        while(true){
            try{
                piscina.ACPiscina(true);
                System.out.println("Inizia il turno Mattutino");
                Thread.sleep(4000);
                piscina.ACPiscina(false);
                System.out.println("Finisce il turno Mattutino");

                Thread.sleep(1000);

                piscina.ACPiscina(true);
                System.out.println("Inizia il turno Pomeridiano");
                Thread.sleep(5000);
                piscina.ACPiscina(false);
                System.out.println("Finisce il turno Pomeridiano");

                Thread.sleep(14000);
            }catch(InterruptedException e){}
        }
    }

}
