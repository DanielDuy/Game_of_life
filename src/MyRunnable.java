import javax.swing.*;

public class MyRunnable implements Runnable {
    private final Verden verden;
    MyPanel panel;
    private boolean pauset = false;
    JLabel labelAntLev;

    public MyRunnable(Verden paramVerden, MyPanel paramMyPanel, JLabel paramlabelAntLev) {
        panel = paramMyPanel;
        verden = paramVerden;
        labelAntLev = paramlabelAntLev;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                while (pauset) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            panel.genererGrid();
            verden.oppdatering();
            labelAntLev.setText("Antall levende: "+verden.hentRutenett().globalAntallLevende);
            panel.oppdaterGrid();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public synchronized void pause() {
        pauset = true;
    }

    public synchronized void fortsett() {
        pauset = false;
        notify();
    }
}