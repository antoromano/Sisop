package Utilities;


public abstract class ContoCorrente {
    protected int deposito;
    public ContoCorrente(int D){
        this.deposito=D;
    }

    public abstract void deposita(int importo);
    public abstract void preleva(int importo);

    public int getDeposito(){return deposito;}
}
