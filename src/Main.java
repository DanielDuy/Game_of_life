import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Verden verden1 = new Verden(8, 12);

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 505, 30);

        JLabel label1 = new JLabel();
        panel1.add(label1);

        MyPanel panel2 = new MyPanel(verden1, label1);
        panel2.setLayout(new GridLayout(8, 12, 0, 0));
        panel2.setBounds(0, 30, 505, 235);
        panel2.nyGrid();

        MyRunnable r1 = new MyRunnable(verden1, panel2, label1);
        Thread t1 = new Thread(r1);
        t1.start();
        label1.setText("Antall levende: "+verden1.hentRutenett().hentGlobalAntLev());
        r1.pause();

        JButton button1 = new JButton("Start");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button1.getText() == "Start") {
                    button1.setText("Stopp");
                    r1.fortsett();
                } else {
                    button1.setText("Start");
                    r1.pause();
                }
            }
        });
        panel1.add(button1);

        JButton button2 = new JButton("Avslutt");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel1.add(button2);

        JFrame frame1 = new JFrame();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setTitle("Game Of Life");
        frame1.setSize(510, 300);
        frame1.setLayout(null);
        frame1.add(panel1);
        frame1.add(panel2);
        frame1.setVisible(true);
    }
}