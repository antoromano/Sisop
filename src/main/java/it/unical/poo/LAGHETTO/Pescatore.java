package LAGHETTO;

import java.util.Random;

public class Pescatore extends Thread {
    Laghetto laghetto;
    String id;

    public Pescatore(Laghetto laghetto, String id) {
        this.laghetto = laghetto;
        this.id = id;
    }

    Random random = new Random();

    public void run() {
        while (true) {
            try {
                laghetto.inizia(laghetto.PESCATORE);
                attendi(200, 800); // tempo pesca
                laghetto.finisci(laghetto.PESCATORE);
                System.out.println("Il pescatore " + id + " inizia a pescare");
                Thread.sleep(1000);// allontana
            } catch (InterruptedException e) {
            }
        }
    }

    private void attendi(int min, int max) throws InterruptedException {
        int x = random.nextInt(max-min+1) + min;
        Thread.sleep(x);
    }
}
