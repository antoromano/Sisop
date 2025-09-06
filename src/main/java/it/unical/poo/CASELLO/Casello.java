package CASELLO;


public abstract class Casello{
    protected int incasso;
    protected int N;
    protected final int T;
    protected int[] casello;

    public Casello(int N,int T){
        this.N=N;
        casello = new int[N];
        for(int i = 0;i<N;i++){casello[i]=0;}
        this.T=T;
    }
    public int getIncasso(){return incasso;}
    public int getN(){return N;}
    public int getT(){return T;}

    public abstract void accedi(int porta)throws InterruptedException;
    public abstract void pagaEAbbandona(int porta,int km)throws InterruptedException;
    
    public void esegui(int V) throws InterruptedException{
        Veicolo[] veicoli = new Veicolo[V];
        for(int i = 0;i<V;i++){
            veicoli[i] = new Veicolo(this);
        }
        for(int i = 0;i<V;i++){
            veicoli[i].start();
        }
        for(int i = 0;i<V;i++){
            veicoli[i].join();
        }
    }
}