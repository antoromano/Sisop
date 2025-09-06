package VISUALIZZATORE;

public class Visualizzatore extends Thread {
    protected Gestore gestore;

    public Visualizzatore(Gestore g) {
        this.gestore = g;
    }

    public void run() {
        try {
            String x = gestore.preleva();
            Thread.sleep(x.hashCode());
            System.out.println(x);
        } catch (InterruptedException e) {
        }
    }
}
