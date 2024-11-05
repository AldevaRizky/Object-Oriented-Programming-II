import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductEntryApp extends JFrame {
    private JTextField tfKodeBarang, tfNamaBarang, tfHargaBeli, tfHargaJual, tfQuantity, tfCari;
    private JButton btnSimpan, btnUbah, btnHapus, btnBatal, btnKeluar, btnCari;
    private JTable table;
    private DefaultTableModel tableModel;

    public ProductEntryApp() {
        setTitle("Entry Data Barang");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title Label
        JLabel lblTitle = new JLabel("Entry Data Barang", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(0, 10, 800, 30);
        add(lblTitle);

        JLabel lblKodeBarang = new JLabel("Kode Barang:");
        lblKodeBarang.setBounds(20, 60, 100, 25);
        add(lblKodeBarang);

        tfKodeBarang = new JTextField();
        tfKodeBarang.setBounds(130, 60, 150, 25);
        add(tfKodeBarang);

        JLabel lblNamaBarang = new JLabel("Nama Barang:");
        lblNamaBarang.setBounds(20, 100, 100, 25);
        add(lblNamaBarang);

        tfNamaBarang = new JTextField();
        tfNamaBarang.setBounds(130, 100, 150, 25);
        add(tfNamaBarang);

        JLabel lblHargaBeli = new JLabel("Harga Beli:");
        lblHargaBeli.setBounds(20, 140, 100, 25);
        add(lblHargaBeli);

        tfHargaBeli = new JTextField();
        tfHargaBeli.setBounds(130, 140, 150, 25);
        add(tfHargaBeli);

        JLabel lblHargaJual = new JLabel("Harga Jual:");
        lblHargaJual.setBounds(350, 60, 100, 25);
        add(lblHargaJual);

        tfHargaJual = new JTextField();
        tfHargaJual.setBounds(460, 60, 150, 25);
        add(tfHargaJual);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(350, 100, 100, 25);
        add(lblQuantity);

        tfQuantity = new JTextField();
        tfQuantity.setBounds(460, 100, 150, 25);
        add(tfQuantity);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(130, 180, 100, 30);
        add(btnSimpan);

        btnUbah = new JButton("Ubah");
        btnUbah.setBounds(240, 180, 100, 30);
        add(btnUbah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(350, 180, 100, 30);
        add(btnHapus);

        btnBatal = new JButton("Batal");
        btnBatal.setBounds(460, 180, 100, 30);
        add(btnBatal);

        btnKeluar = new JButton("Keluar");
        btnKeluar.setBounds(570, 180, 100, 30);
        add(btnKeluar);

        tfCari = new JTextField();
        tfCari.setBounds(20, 230, 150, 25);
        add(tfCari);

        btnCari = new JButton("Cari");
        btnCari.setBounds(180, 230, 90, 25);
        add(btnCari);

        tableModel = new DefaultTableModel(new String[]{"Kode Barang", "Nama Barang", "Harga Beli", "Harga Jual", "Quantity"}, 0);
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 270, 740, 180);
        add(sp);

        loadTableData();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tfKodeBarang.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    tfNamaBarang.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    tfHargaBeli.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    tfHargaJual.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    tfQuantity.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        btnSimpan.addActionListener(e -> simpanData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnCari.addActionListener(e -> cariData());
        btnBatal.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin membatalkan?", "Konfirmasi Batal", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                clearFields();
            }
        });
        
        btnKeluar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
    }

    private void loadTableData() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM product";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            tableModel.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String hargaBeli = String.valueOf(rs.getDouble("harga_beli"));
                String hargaJual = String.valueOf(rs.getDouble("harga_jual"));
                String quantity = String.valueOf(rs.getInt("quantity"));

                tableModel.addRow(new Object[]{kode, nama, hargaBeli, hargaJual, quantity});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }

    private boolean validateFields() {
        if (tfKodeBarang.getText().isEmpty() || tfNamaBarang.getText().isEmpty() || 
            tfHargaBeli.getText().isEmpty() || tfHargaJual.getText().isEmpty() || 
            tfQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!");
            return false;
        }
        return true;
    }

    private void simpanData() {
        if (!validateFields()) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menyimpan data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String kode = tfKodeBarang.getText();
            String nama = tfNamaBarang.getText();
            double hargaBeli = Double.parseDouble(tfHargaBeli.getText());
            double hargaJual = Double.parseDouble(tfHargaJual.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());

            try (Connection conn = DBConnection.getConnection()) {
                String query = "INSERT INTO product VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, kode);
                ps.setString(2, nama);
                ps.setDouble(3, hargaBeli);
                ps.setDouble(4, hargaJual);
                ps.setInt(5, quantity);

                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                    loadTableData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan data.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void ubahData() {
        if (!validateFields()) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin mengubah data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String kode = tfKodeBarang.getText();
            String nama = tfNamaBarang.getText();
            double hargaBeli = Double.parseDouble(tfHargaBeli.getText());
            double hargaJual = Double.parseDouble(tfHargaJual.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());

            try (Connection conn = DBConnection.getConnection()) {
                String query = "UPDATE product SET nama_barang=?, harga_beli=?, harga_jual=?, quantity=? WHERE kode_barang=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nama);
                ps.setDouble(2, hargaBeli);
                ps.setDouble(3, hargaJual);
                ps.setInt(4, quantity);
                ps.setString(5, kode);

                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
                    loadTableData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Kode barang tidak ditemukan!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void hapusData() {
        if (tfKodeBarang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kode barang harus diisi untuk menghapus!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String kode = tfKodeBarang.getText();

            try (Connection conn = DBConnection.getConnection()) {
                String query = "DELETE FROM product WHERE kode_barang=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, kode);

                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                    loadTableData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Kode barang tidak ditemukan!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void cariData() {
        String cari = tfCari.getText();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM product WHERE kode_barang LIKE ? OR nama_barang LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + cari + "%");
            ps.setString(2, "%" + cari + "%");

            ResultSet rs = ps.executeQuery();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String hargaBeli = String.valueOf(rs.getDouble("harga_beli"));
                String hargaJual = String.valueOf(rs.getDouble("harga_jual"));
                String quantity = String.valueOf(rs.getInt("quantity"));

                tableModel.addRow(new Object[]{kode, nama, hargaBeli, hargaJual, quantity});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        tfKodeBarang.setText("");
        tfNamaBarang.setText("");
        tfHargaBeli.setText("");
        tfHargaJual.setText("");
        tfQuantity.setText("");
        tfCari.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductEntryApp().setVisible(true));
    }
}
