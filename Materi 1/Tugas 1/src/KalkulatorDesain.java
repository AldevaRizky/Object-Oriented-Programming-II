import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorDesain extends JFrame implements ActionListener {
    private JTextField bil1Field, bil2Field, hasilField;
    private JComboBox<String> operasiBox;
    private JButton hitungButton;

    public KalkulatorDesain() {
        setTitle("Kalkulator Sederhana");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama untuk menampilkan kalkulator dengan desain seperti "card"
        JPanel kalkulatorPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Membuat latar belakang persegi panjang untuk "card"
                g2d.setColor(Color.BLACK);
                g2d.fillRoundRect(20, 20, 340, 500, 25, 25);
            }
        };
        
        kalkulatorPanel.setLayout(null);
        kalkulatorPanel.setBackground(Color.LIGHT_GRAY);

        // Judul Kalkulator
        JLabel titleLabel = new JLabel("Kalkulator Sederhana");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(50, 40, 300, 40);
        kalkulatorPanel.add(titleLabel);

        // Label dan input bilangan pertama
        JLabel bil1Label = new JLabel("Bilangan Pertama :");
        bil1Label.setForeground(Color.WHITE);
        bil1Label.setFont(new Font("Arial", Font.BOLD, 14));
        bil1Label.setBounds(40, 100, 150, 25);
        kalkulatorPanel.add(bil1Label);

        bil1Field = new JTextField("0");
        bil1Field.setBounds(40, 130, 300, 30);
        bil1Field.setFont(new Font("Arial", Font.PLAIN, 16));
        kalkulatorPanel.add(bil1Field);

        // Label dan input bilangan kedua
        JLabel bil2Label = new JLabel("Bilangan Kedua :");
        bil2Label.setForeground(Color.WHITE);
        bil2Label.setFont(new Font("Arial", Font.BOLD, 14));
        bil2Label.setBounds(40, 180, 150, 25);
        kalkulatorPanel.add(bil2Label);

        bil2Field = new JTextField("0");
        bil2Field.setBounds(40, 210, 300, 30);
        bil2Field.setFont(new Font("Arial", Font.PLAIN, 16));
        kalkulatorPanel.add(bil2Field);

        // Label dan pilihan operasi
        JLabel operasiLabel = new JLabel("Aksi");
        operasiLabel.setForeground(Color.WHITE);
        operasiLabel.setFont(new Font("Arial", Font.BOLD, 14));
        operasiLabel.setBounds(40, 260, 150, 25);
        kalkulatorPanel.add(operasiLabel);

        String[] operasi = { "Tambah (+)", "Kurang (-)", "Kali (X)", "Bagi (/)" };
        operasiBox = new JComboBox<>(operasi);
        operasiBox.setBounds(40, 290, 300, 30);
        operasiBox.setFont(new Font("Arial", Font.BOLD, 16));
        kalkulatorPanel.add(operasiBox);

        // Tombol hitung
        hitungButton = new JButton("Hitung");
        hitungButton.setBounds(40, 350, 300, 40);
        hitungButton.setFont(new Font("Arial", Font.BOLD, 20));
        hitungButton.setBackground(Color.RED);
        hitungButton.setForeground(Color.WHITE);
        hitungButton.setBorder(null);
        hitungButton.addActionListener(this);
        kalkulatorPanel.add(hitungButton);

        // Label hasil
        hasilField = new JTextField("0");
        hasilField.setBounds(40, 420, 300, 50);
        hasilField.setFont(new Font("Arial", Font.BOLD, 30));
        hasilField.setEditable(false);
        kalkulatorPanel.add(hasilField);

        add(kalkulatorPanel);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    try {
        double bil1 = Double.parseDouble(bil1Field.getText());
        double bil2 = Double.parseDouble(bil2Field.getText());
        String operasi = (String) operasiBox.getSelectedItem();
        double hasil = 0;

        switch (operasi) {
            case "Tambah (+)":
                hasil = bil1 + bil2;
                break;
            case "Kurang (-)":
                hasil = bil1 - bil2;
                break;
            case "Kali (X)":
                hasil = bil1 * bil2;
                break;
            case "Bagi (/)":
                hasil = bil1 / bil2;
                break;
        }

        // Mengecek apakah hasil adalah bilangan bulat
        if (hasil == (int) hasil) {
            hasilField.setText(String.valueOf((int) hasil));  // Tampilkan sebagai integer jika bulat
        } else {
            hasilField.setText(String.valueOf(hasil));  // Tampilkan sebagai double jika desimal
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KalkulatorDesain kalkulator = new KalkulatorDesain();
            kalkulator.setVisible(true);
        });
    }
}
