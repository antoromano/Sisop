package PC;

import java.util.LinkedList;

public class BufferLCFIFO extends BufferLC {
    private LinkedList<Thread> codaProduttori = new LinkedList<>();
    private LinkedList<Thread> codaConsumatori = new LinkedList<>();

    public BufferLCFIFO(int dimensione) {
        super(dimensione);
    }

    public void put(int i) throws InterruptedException {
        l.lock();
        try {
            codaProduttori.add(Thread.currentThread());
            while (!possoInserire()) {
                bufferPieno.await();
            }
            codaProduttori.remove();
            buffer[in] = i;
            in = (in + i) % buffer.length;
            numElementi++;
            bufferVuoto.signalAll();
        } finally {
            l.unlock();
        }
    }

    private boolean possoInserire() {
        return numElementi < buffer.length && Thread.currentThread() == codaProduttori.getFirst();
    }

    public int get() throws InterruptedException {
        int r;
        l.lock();
        try {
            codaConsumatori.add(Thread.currentThread());
            while (!possoPrelevare()) {
                bufferVuoto.wait();
            }
            codaConsumatori.remove();
            r = buffer[in];
            in = (in + 1) % buffer.length;
            numElementi--;
            bufferPieno.signal();
        } finally {
            l.unlock();
        }
        return r;
    }

    private boolean possoPrelevare() {
        return numElementi > 0 && Thread.currentThread() == codaConsumatori.getFirst();
    }
}
