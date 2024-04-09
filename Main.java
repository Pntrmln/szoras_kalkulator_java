import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

class Frame extends JFrame implements ActionListener {
    JTextField textField;
    JButton gomb;
    int darab;
    JLabel szoveg2;
    int gombnyomas;
    public Frame() {
        JPanel panel = new JPanel(new GridBagLayout());

        JLabel szoveg = new JLabel("Adj meg számokat!");
        szoveg.setFont(new Font("Arial", Font.PLAIN, 24));
        textField = new JTextField(25);
        gomb = new JButton("Kész!");
        gomb.addActionListener(this);
        szoveg2 = new JLabel("");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        panel.add(szoveg, c);
        c.gridy = 1;
        panel.add(textField, c);
        c.gridy = 2;
        panel.add(gomb, c);
        c.gridy = 3;
        panel.add(szoveg2, c);
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setSize(new Dimension(400,400));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gombnyomas++;
        String szSzamok = textField.getText();
        String[] szSzamArray = szSzamok.split(", ");
        try {
            int[] num = new int[69];
            for (int i = 0; i < szSzamArray.length; i++) {
                int y = Integer.parseInt(szSzamArray[i]);
                num[i] = y;
            }
            darab = 0;
            for (String s : szSzamArray) {
                if (s != null) {
                    darab++;
                }
            }
            System.out.println("darab:" + darab);
            int osszeg = IntStream.of(num).sum();
            System.out.println("osszeg:" + osszeg);
            float atlag = (float) osszeg / darab;
            System.out.println("atlag:" + atlag);
            double[] negyzetArray = new double[darab];
            for (int i = 0; i < darab; i++) {
                float kivont = num[i] - atlag;
                double negyzetre_emelt = Math.pow(kivont, 2);
                negyzetArray[i] = negyzetre_emelt;
            }
            double negyzetOsszeg = DoubleStream.of(negyzetArray).sum();
            System.out.println(negyzetOsszeg);
            double szoras = Math.sqrt(negyzetOsszeg / darab);
            szoveg2.setText("Szórás: " + szoras);
        } catch (NumberFormatException e1) {
            szoveg2.setText("Nem megfelelő a formátum!");
        }
    }
}
