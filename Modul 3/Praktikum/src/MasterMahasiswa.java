import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

public class MasterMahasiswa extends JFrame {
    private JTextField txtNIM, txtNama;
    private JRadioButton rbLakiLaki, rbPerempuan;
    private JComboBox<String> cbJurusan;
    private JButton btnSimpan, btnUbah, btnHapus, btnLaporan, btnTutup;
    private JTable table;
    private ButtonGroup genderGroup;
    private DefaultTableModel tableModel;

    public MasterMahasiswa() {
        setTitle("Master Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Title Label
        JLabel lblTitle = new JLabel("Master Mahasiswa", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(18, 0, 5, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Form Panel for Inputs
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // NIM Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("NIM"), gbc);
        gbc.gridx = 1;
        txtNIM = new JTextField(20);
        formPanel.add(txtNIM, gbc);

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Nama"), gbc);
        gbc.gridx = 1;
        txtNama = new JTextField(20);
        formPanel.add(txtNama, gbc);

        // Gender Radio Buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Jenis Kelamin"), gbc);
        gbc.gridx = 1;
        rbLakiLaki = new JRadioButton("Laki-Laki");
        rbPerempuan = new JRadioButton("Perempuan");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbLakiLaki);
        genderGroup.add(rbPerempuan);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(rbLakiLaki);
        genderPanel.add(rbPerempuan);
        formPanel.add(genderPanel, gbc);

        // Jurusan ComboBox
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Jurusan"), gbc);
        gbc.gridx = 1;
        cbJurusan = new JComboBox<>(loadJurusanOptions());
        formPanel.add(cbJurusan, gbc);

        // Buttons for CRUD Operations
        JPanel buttonPanel = new JPanel();
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnLaporan = new JButton("Laporan");
        btnTutup = new JButton("Tutup");

        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnUbah);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnLaporan);
        buttonPanel.add(btnTutup);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"NIM", "Nama", "Jenis Kelamin", "Jurusan"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        add(mainPanel, BorderLayout.CENTER);
        add(tableScroll, BorderLayout.SOUTH);

        // Action listeners for CRUD buttons
        btnSimpan.addActionListener(e -> simpanData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnLaporan.addActionListener(e -> tampilkanLaporan());
        btnTutup.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin keluar?",
                "Konfirmasi Keluar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Menutup jendela jika pengguna memilih "Yes"
            }
        });
        

        // Mouse listener for table row selection
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                isiFormDariTabel();
            }
        });

        loadData();
        formPanel.setPreferredSize(new Dimension(600, 200));
        tableScroll.setPreferredSize(new Dimension(600, 200));

        pack();
        setLocationRelativeTo(null);
    }

    // Method to load jurusan options from the database
    private String[] loadJurusanOptions() {
        ArrayList<String> jurusanList = new ArrayList<>();
        jurusanList.add("- Pilih Jurusan -");
        try (Connection conn = Koneksi.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nama_jurusan FROM jurusan");
            while (rs.next()) {
                jurusanList.add(rs.getString("nama_jurusan"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat daftar jurusan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return jurusanList.toArray(new String[0]);
    }

    // Method to load student data into the table
    private void loadData() {
        try (Connection conn = Koneksi.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mahasiswa");
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("jurusan")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to save data to the database
    private void simpanData() {
        if (txtNIM.getText().isEmpty() || txtNama.getText().isEmpty() || (!rbLakiLaki.isSelected() && !rbPerempuan.isSelected()) || cbJurusan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: Semua data harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Show a confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menyimpan data ini?", "Konfirmasi Simpan", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "INSERT INTO mahasiswa (nim, nama, jenis_kelamin, jurusan) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtNIM.getText());
                stmt.setString(2, txtNama.getText());
                stmt.setString(3, rbLakiLaki.isSelected() ? "Laki-Laki" : "Perempuan");
                stmt.setString(4, cbJurusan.getSelectedItem().toString());
                stmt.executeUpdate();
                loadData();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    // Method to update data in the database
    private void ubahData() {
        if (txtNIM.getText().isEmpty() || txtNama.getText().isEmpty() || (!rbLakiLaki.isSelected() && !rbPerempuan.isSelected()) || cbJurusan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data: Semua data harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try (Connection conn = Koneksi.getConnection()) {
            // Check if NIM exists
            String checkSql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, txtNIM.getText());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                JOptionPane.showMessageDialog(this, "NIM tidak ditemukan! Tidak ada data yang diubah.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Show a confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin mengubah data ini?", "Konfirmasi Ubah", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Proceed with update if confirmed
                String sql = "UPDATE mahasiswa SET nama = ?, jenis_kelamin = ?, jurusan = ? WHERE nim = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtNama.getText());
                stmt.setString(2, rbLakiLaki.isSelected() ? "Laki-Laki" : "Perempuan");
                stmt.setString(3, cbJurusan.getSelectedItem().toString());
                stmt.setString(4, txtNIM.getText());
                stmt.executeUpdate();
                loadData();
                JOptionPane.showMessageDialog(this, "Data berhasil diubah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    // Method to delete data from the database
    private void hapusData() {
        if (txtNIM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: Pilih data yang ingin dihapus", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try (Connection conn = Koneksi.getConnection()) {
            // Check if NIM exists
            String checkSql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, txtNIM.getText());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                JOptionPane.showMessageDialog(this, "NIM tidak ditemukan! Tidak ada data yang dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Proceed with delete if confirmed
                String sql = "DELETE FROM mahasiswa WHERE nim = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtNIM.getText());
                stmt.executeUpdate();
                loadData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    // Method to display report
    private void tampilkanLaporan() {
        try {
            String dest = "D:\\OOPII\\LaporanMahasiswa.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc, PageSize.A4.rotate()); // Rotate to landscape
            
            // Add report headers
            document.add(new Paragraph("Laporan Data Mahasiswa").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Fakultas Teknik Universitas PGRI Madiun").setFontSize(12).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n")); // Add space before the table
            
            // Define column widths
            float[] colWidths = {5, 20, 35, 20, 35};
            Table table = new Table(UnitValue.createPercentArray(colWidths)).useAllAvailableWidth();
            
            // Add table headers
            table.addHeaderCell(new Cell().add(new Paragraph("No").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("NIM").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Nama").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Jenis Kelamin").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Jurusan").setBold()));
            
            // Add table rows
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(i + 1)))); // Add row number
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    String cellValue = tableModel.getValueAt(i, j) != null ? tableModel.getValueAt(i, j).toString() : "";
                    table.addCell(new Cell().add(new Paragraph(cellValue)));
                }
            }

            // Add table to the document
            document.add(table);
            document.close();
            
            // Show success message
            JOptionPane.showMessageDialog(this, "Laporan berhasil dibuat di: " + dest, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Gagal membuat laporan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // Method to clear the form fields
    private void clearForm() {
        txtNIM.setText("");
        txtNama.setText("");
        genderGroup.clearSelection();
        cbJurusan.setSelectedIndex(0);
    }
    
    // Method to populate the form from the selected row in the table
    private void isiFormDariTabel() {
        int selectedRow = table.getSelectedRow(); // Get selected row
        if (selectedRow != -1) {
            // Get data from the selected row
            String nim = tableModel.getValueAt(selectedRow, 0).toString();
            String nama = tableModel.getValueAt(selectedRow, 1).toString();
            String jenisKelamin = tableModel.getValueAt(selectedRow, 2).toString();
            String jurusan = tableModel.getValueAt(selectedRow, 3).toString();
    
            // Populate the form fields with the selected row data
            txtNIM.setText(nim);
            txtNama.setText(nama);
            if ("Laki-Laki".equals(jenisKelamin)) {
                rbLakiLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
            cbJurusan.setSelectedItem(jurusan);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MasterMahasiswa().setVisible(true));
    }
}
