package LAGHETTO;

public abstract class Laghetto {
    public Laghetto(int P,int N){
        this.nAddetti=N;
        this.nPescatori=P;
    }
    protected final int PESCATORE = 0;
    protected final int ADDETTO = 1;
    protected int nPescatori;
    protected int nAddetti;
    protected final static int minPesci = 50;
    protected final static int maxPesce = 200;
    protected int pPescatori = 0;
    protected int pAddetti = 0;
    protected int nPesci = 200;

    public abstract void inizia(int t) throws InterruptedException;
    public abstract void finisci(int t) throws InterruptedException;

    public void test(){
        Thread[] pescatori = new Thread[nPescatori];
        Thread[] addetti = new Thread[nAddetti];
        for(int i = 0;i<nPescatori;i++){
            pescatori[i] = new Pescatore(this,String.valueOf(i));
            pescatori[i].start();
        }
        for(int i = 0;i<nAddetti;i++){
            addetti[i] = new Addetto(this,String.valueOf(i));
            addetti[i].start();
        }
    }
}
