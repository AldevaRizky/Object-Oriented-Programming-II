import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniKalkulator implements ActionListener {

    JFrame frame;
    JTextField angka1Field, angka2Field, hasilField;
    JLabel angka1Label, angka2Label, hasilLabel, judulLabel;
    JButton tambahButton, kurangButton, kaliButton, bagiButton, keluarButton;

    MiniKalkulator() {
        // Membuat frame
        frame = new JFrame("Mini Kalkulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);  // Mengubah ukuran frame untuk menyesuaikan judul
        frame.setLayout(null);

        // Membuat label judul
        judulLabel = new JLabel("Mini Kalkulator", SwingConstants.CENTER);
        judulLabel.setFont(new Font("Arial", Font.BOLD, 20));
        judulLabel.setBounds(50, 10, 300, 30);
        frame.add(judulLabel);

        // Membuat label dan field input untuk Angka 1
        angka1Label = new JLabel("Angka 1:");
        angka1Label.setBounds(50, 50, 100, 25);
        frame.add(angka1Label);

        angka1Field = new JTextField();
        angka1Field.setBounds(150, 50, 150, 25);
        frame.add(angka1Field);

        // Membuat label dan field input untuk Angka 2
        angka2Label = new JLabel("Angka 2:");
        angka2Label.setBounds(50, 100, 100, 25);
        frame.add(angka2Label);

        angka2Field = new JTextField();
        angka2Field.setBounds(150, 100, 150, 25);
        frame.add(angka2Field);

        // Membuat label untuk hasil
        hasilLabel = new JLabel("Hasil:");
        hasilLabel.setBounds(50, 150, 100, 25);
        frame.add(hasilLabel);

        hasilField = new JTextField();
        hasilField.setBounds(150, 150, 150, 25);
        hasilField.setEditable(false); // Field hasil tidak bisa diedit
        frame.add(hasilField);

        // Membuat tombol operasi +, -, X, /
        tambahButton = new JButton("+");
        tambahButton.setBounds(50, 200, 50, 30);
        tambahButton.addActionListener(this);
        frame.add(tambahButton);

        kurangButton = new JButton("-");
        kurangButton.setBounds(110, 200, 50, 30);
        kurangButton.addActionListener(this);
        frame.add(kurangButton);

        kaliButton = new JButton("X");
        kaliButton.setBounds(170, 200, 50, 30);
        kaliButton.addActionListener(this);
        frame.add(kaliButton);

        bagiButton = new JButton("/");
        bagiButton.setBounds(230, 200, 50, 30);
        bagiButton.addActionListener(this);
        frame.add(bagiButton);

        // Membuat tombol Keluar
        keluarButton = new JButton("KELUAR");
        keluarButton.setBounds(290, 200, 80, 30);
        keluarButton.addActionListener(this);
        frame.add(keluarButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MiniKalkulator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Cek jika tombol keluar ditekan
        if (e.getSource() == keluarButton) {
            frame.dispose(); // Menutup aplikasi
            return;
        }

        try {
            // Mengambil input dari field
            double angka1 = Double.parseDouble(angka1Field.getText());
            double angka2 = Double.parseDouble(angka2Field.getText());
            double hasil = 0;

            // Cek tombol mana yang ditekan
            if (e.getSource() == tambahButton) {
                hasil = angka1 + angka2;
            } else if (e.getSource() == kurangButton) {
                hasil = angka1 - angka2;
            } else if (e.getSource() == kaliButton) {
                hasil = angka1 * angka2;
            } else if (e.getSource() == bagiButton) {
                if (angka2 != 0) {
                    hasil = angka1 / angka2;
                } else {
                    JOptionPane.showMessageDialog(frame, "Tidak dapat membagi dengan nol!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Tampilkan hasil
            hasilField.setText(String.valueOf(hasil));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
