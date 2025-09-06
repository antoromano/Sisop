package PISCINA;

import java.util.Random;

public abstract class Piscina {
    public Piscina(int n){
        personeCorsie = new int[n];
        numCorsie = n;
        random = new Random();
    }
    protected int numCorsie;
    protected int[] personeCorsie;
    protected Random random;
    protected Nuotatore[] nuo;
    protected int numNuotatori;
    protected Istruttore is;


    public abstract int scegliCorsia() throws InterruptedException;
    public abstract void entraCorsia(int corsia) throws InterruptedException;
    public abstract void lasciaCorsia(int corsia) throws InterruptedException;
    public abstract void ACPiscina(boolean stato) throws InterruptedException;

    public void test(int N)throws InterruptedException{
        is = new Istruttore(this);
        is.start();
        numNuotatori = N;
    }
}
