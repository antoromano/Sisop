package LAGHETTO;

import java.util.Random;

public class Addetto extends Thread{
    Laghetto laghetto;
    String id;
    public Addetto(Laghetto laghetto,String id){
        this.laghetto = laghetto;
        this.id = id;
    }
    Random random = new Random();
    public void run() {
        while (true) {
            try {
                laghetto.inizia(laghetto.ADDETTO);
                attendi(300, 600); // tempo rilascio
                laghetto.finisci(laghetto.ADDETTO);
                System.out.println("L'addetto " + id + " inizia a liberare i pesci");
                Thread.sleep(3000);// allontana
            } catch (InterruptedException e) {
            }
        }
    }

    private void attendi(int min, int max) throws InterruptedException {
        int x = random.nextInt(max-min+1) + min;
        Thread.sleep(x);
    }
}
