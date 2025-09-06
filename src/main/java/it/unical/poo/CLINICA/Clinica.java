package CLINICA;

public abstract class Clinica {
    public abstract void chiamaEIniziaOperazione() throws InterruptedException;

    public abstract void operazioneFinita()throws InterruptedException;

    public abstract void entraPaziente()throws InterruptedException;

    public abstract void esciPaziente()throws InterruptedException;

    public Clinica(){

    }
    public void test(){

    }
}
