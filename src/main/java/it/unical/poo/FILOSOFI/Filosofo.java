package FILOSOFI;

public class Filosofo extends Thread{
    Tavolo tavolo;
    int id;

    public Filosofo(Tavolo t, int id) {
        this.tavolo = t;
        this.id = id;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Il filosofo " + id + " sta pensando");
                tavolo.prendiForchette(id);
                Thread.sleep(800);
                System.out.println("Il filosofo " + id + " ha mangiato");
                tavolo.lasciaForchette(id);
            }
        } catch (InterruptedException e) {
        }
    }
}
