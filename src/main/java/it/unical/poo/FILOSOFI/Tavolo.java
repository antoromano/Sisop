package FILOSOFI;

public abstract class Tavolo {
    protected static final int NF = 5;
    protected int[] forchette = new int[NF];
    public Tavolo(){
        for(int i = 0;i<NF;i++){
            forchette[i]=2;
        }
    }
    public abstract void prendiForchette(int i) throws InterruptedException;
    public abstract void lasciaForchette(int i) throws InterruptedException;
    protected int destra(int i){
        if(i==NF-1){return 0;}
        return i+1;
    }
    protected int sinistra(int i){
        if(i==0){return NF-1;}
        return i-1;
    }

    public void test(){
        for(int i = 0;i<NF;i++){
            Filosofo f = new Filosofo(this, i);
            f.start();
        }
    }
}
