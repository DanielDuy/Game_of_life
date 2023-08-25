import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {

    Verden verden;
    JLabel labelAntLev;

    MyPanel(Verden paramVerden, JLabel paramlabelAntLev) {
        verden = paramVerden;
        labelAntLev = paramlabelAntLev;
    }

    public void nyGrid() {
        for (int r = 0; r < 8; r++) {
            for (int k = 0; k < 12; k++) {
                JButton nyButton = new JButton();
                if (verden.hentRutenett().hentCelle(r, k).erLevende()) {
                    nyButton.setText("*");
                } else {
                    nyButton.setText("");
                }
                nyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (nyButton.getText() == "*") {
                            nyButton.setText("");
                            verden.hentRutenett().trekkFraGlobalAntLev();
                            labelAntLev.setText("Antall levende: "+verden.hentRutenett().hentGlobalAntLev());
                        } else {
                            nyButton.setText("*");
                            verden.hentRutenett().leggTilFraGlobalAntLev();
                            labelAntLev.setText("Antall levende: "+verden.hentRutenett().hentGlobalAntLev());
                        }
                    }
                });
                this.add(nyButton);
            }
        }
    }

    public void oppdaterGrid() {
        int kol = 0;
        int row = 0;

        for (Component c : this.getComponents()) {
            JButton tempButton = (JButton) c;
            if (verden.hentRutenett().hentCelle(row, kol).erLevende()) {
                tempButton.setText("*");
            } else {
                tempButton.setText("");
            }
            kol += 1;
            if (kol == 12) {
                kol = 0;
                row += 1;
            }
        }
    }

    public void genererGrid() {
        int kol = 0;
        int row = 0;

        for (Component c : this.getComponents()) {
            JButton tempButton = (JButton) c;
            if (tempButton.getText() == "*") {
                verden.hentRutenett().hentCelle(row, kol).settLevende();
            } else {
                verden.hentRutenett().hentCelle(row, kol).settDoed();
            }
            kol += 1;
            if (kol == 12) {
                kol = 0;
                row += 1;
            }
        }
    }
}