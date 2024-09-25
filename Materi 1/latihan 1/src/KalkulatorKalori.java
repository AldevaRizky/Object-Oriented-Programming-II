import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorKalori implements ActionListener {

    JFrame frame;
    JTextField kaloriField, porsiField, hasilField;
    JLabel kaloriLabel, porsiLabel, hasilLabel;
    JButton hitungButton, tutupButton;

    KalkulatorKalori() {
        // Membuat frame
        frame = new JFrame("Form Hitung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Membuat label dan field input untuk kalori
        kaloriLabel = new JLabel("Jumlah Kalori");
        kaloriLabel.setBounds(50, 50, 100, 25);
        frame.add(kaloriLabel);

        kaloriField = new JTextField();
        kaloriField.setBounds(150, 50, 150, 25);
        frame.add(kaloriField);

        // Membuat label dan field input untuk porsi
        porsiLabel = new JLabel("Jumlah Porsi");
        porsiLabel.setBounds(50, 100, 100, 25);
        frame.add(porsiLabel);

        porsiField = new JTextField();
        porsiField.setBounds(150, 100, 150, 25);
        frame.add(porsiField);

        // Membuat label untuk hasil
        hasilLabel = new JLabel("HASIL REKOMENDASI:");
        hasilLabel.setBounds(50, 150, 300, 25);
        frame.add(hasilLabel);

        // Membuat tombol hitung
        hitungButton = new JButton("HITUNG");
        hitungButton.setBounds(50, 200, 100, 30);
        hitungButton.addActionListener(this);
        frame.add(hitungButton);

        // Membuat tombol tutup
        tutupButton = new JButton("TUTUP");
        tutupButton.setBounds(200, 200, 100, 30);
        tutupButton.addActionListener(this);
        frame.add(tutupButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new KalkulatorKalori();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hitungButton) {
            try {
                // Ambil input kalori dan porsi
                double kalori = Double.parseDouble(kaloriField.getText());
                double porsi = Double.parseDouble(porsiField.getText());

                // Hitung hasil kalori
                double hasil = kalori * porsi;

                // Tampilkan hasil pada label
                hasilLabel.setText("HASIL PERHITUNGAN REKOMENDASI: " + hasil);
            } catch (NumberFormatException ex) {
                // Jika input tidak valid, tampilkan pesan error
                JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == tutupButton) {
            frame.dispose(); // Menutup aplikasi
        }
    }
}
