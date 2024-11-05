import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class FormBarang extends JFrame implements ActionListener {
    private JTextField namaField, hargaField, jumlahField, totalField;
    private JButton hitungButton, keluarButton;
    private JLabel totalLabel;

    public FormBarang() {
        setTitle("FORM BARANG");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Menggunakan GridBagLayout untuk pengaturan layout yang fleksibel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul Form
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Mengambil 3 kolom agar tetap di atas elemen lainnya
        JLabel judulLabel = new JLabel("FORM BARANG", JLabel.LEFT);
        judulLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Ukuran font besar seperti H1
        panel.add(judulLabel, gbc);


        // Nama Barang
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset gridwidth agar tidak mengganggu elemen lainnya
        panel.add(new JLabel("Nama Barang"), gbc);
        
        gbc.gridx = 1;
        namaField = new JTextField();
        namaField.setColumns(10);
        panel.add(namaField, gbc);

        // Harga
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Harga"), gbc);

        gbc.gridx = 1;
        hargaField = new JTextField();
        panel.add(hargaField, gbc);

        // Jumlah
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Jumlah"), gbc);

        gbc.gridx = 1;
        jumlahField = new JTextField();
        panel.add(jumlahField, gbc);

        // Total
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Total"), gbc);

        gbc.gridx = 1;
        totalField = new JTextField();
        totalField.setEditable(false);
        panel.add(totalField, gbc);

        // Tombol Hitung
        hitungButton = new JButton("Hitung");
        hitungButton.setBackground(new Color(200, 200, 255));
        hitungButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(hitungButton, gbc);

        // Tombol Keluar
        keluarButton = new JButton("Keluar");
        keluarButton.setBackground(new Color(200, 200, 255));
        keluarButton.addActionListener(this::KeluarActionPerformed); // Menambahkan aksi keluar
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(keluarButton, gbc);

        // Total Label di bagian bawah
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        totalLabel = new JLabel("TOTAL: Rp. 0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(totalLabel, gbc);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Mengambil nilai dari field
            double harga = Double.parseDouble(hargaField.getText());
            int jumlah = Integer.parseInt(jumlahField.getText());
            double total = harga * jumlah;

            // Format hasil dengan NumberFormat menggunakan locale Indonesia
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            String totalFormatted = currencyFormat.format(total);
            totalField.setText(totalFormatted);

            // Update label total
            totalLabel.setText("TOTAL: " + totalFormatted);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid! Masukkan angka untuk harga dan jumlah.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void KeluarActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Terima Kasih Sudah Membeli " + namaField.getText());
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormBarang().setVisible(true);
        });
    }
}
