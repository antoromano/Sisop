package AZIENDAAGRICOLA;


public abstract class Azienda{

    protected int nSacchi;
    protected int incasso;

    public abstract void paga(int sacchi)throws InterruptedException;
    public abstract void ritira()throws InterruptedException;
    public abstract void ricarica()throws InterruptedException;

    public Azienda(){
        nSacchi = 200;
        incasso = 0;
    }

    public void test(int clienti){
        Cliente[] c = new Cliente[clienti];
        for(int i = 0;i<clienti;i++){
            c[i] = new Cliente(this, null);
            c[i].start();
        }
        Magazziniere m = new Magazziniere(this);
        m.start();
    }
}