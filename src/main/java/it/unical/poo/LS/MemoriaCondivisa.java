package LS;

public abstract class MemoriaCondivisa {
    public  abstract void inizioScrittura()throws InterruptedException;
    public abstract void fineScrittura() throws InterruptedException;
    public  abstract void inizioLettura()throws InterruptedException;
    public abstract void fineLettura() throws InterruptedException;

    protected void test(int numLettori,int numScrittori){
        Lettore[] lettori = new Lettore[numLettori];
        for(int i = 0;i<numLettori;i++){
            lettori[i]= new Lettore(this);
        }
        Scrittore[] scrittori = new Scrittore[numScrittori];
        for(int i = 0;i<numScrittori;i++){
            scrittori[i]= new Scrittore(this);
        }
        for(int i =0;i<numLettori;i++){
            new Thread(lettori[i]).start();
        }
        for(int i =0;i<numScrittori;i++){
            new Thread(scrittori[i]).start();
        }
    }
}

// thread scrittore : può scrivere sulla memoria(buffer) solo quando nessun altro ci sta leggendo o scrivendo sopra
// mentre lo scrittore scrive, nessun altro thread puo scrivere o leggere in memoria(buffer)

//thread lettore : può leggere sulla memoria(buffer) mentre nessun thread scrittore ci sta scrivendo sopra
// più thread lettori possono leggere contemporaneamente, se nessun thread scrittore ci sta scrivendo sopra
