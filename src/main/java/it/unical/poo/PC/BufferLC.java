package PC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLC extends Buffer {
    public BufferLC(int dimensione) {
        super(dimensione);
    }

    protected int numElementi = 0;
    protected Lock l = new ReentrantLock();
    protected Condition bufferPieno = l.newCondition();
    protected Condition bufferVuoto = l.newCondition();

    @Override
    public void put(int i) throws InterruptedException {
        l.lock();
        try {
            while (numElementi >= buffer.length) {
                bufferPieno.wait();
            }
            buffer[in] = i;
            in = (in + 1) % buffer.length;
            numElementi++;
            bufferVuoto.signal();
        } finally {
            l.unlock();
        }
    }

    @Override
    public int get() throws InterruptedException {
        int r;
        l.lock();
        try {
            while (numElementi <= 0) {
                bufferVuoto.wait();
            }
            r = buffer[in];
            in = (in + 1) % buffer.length;
            numElementi--;
            bufferPieno.signal();
        } finally {
            l.unlock();
        }
        return r;
    }

}
