import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplikasiPenjualan extends JFrame {

    private JPanel panelUtama;

    public AplikasiPenjualan() {
        // Set judul dan aksi ketika aplikasi ditutup
        setTitle("Sistem Informasi Penjualan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        
        // Membuat bar menu
        JMenuBar menuBar = new JMenuBar();
        
        // Membuat menu "File"
        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);
        
        // Membuat item menu "Barang" di bawah "File"
        JMenuItem menuBarang = new JMenuItem("Barang");
        menuFile.add(menuBarang);

        // Membuat item menu "Satuan" di bawah "File"
        JMenuItem menuSatuan = new JMenuItem("Satuan");
        menuFile.add(menuSatuan);
        
        // Membuat item menu "Keluar" di bawah "File"
        JMenuItem menuKeluar = new JMenuItem("Keluar");
        menuKeluar.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        menuKeluar.addActionListener(e -> System.exit(0));
        menuFile.add(menuKeluar);
        
        // Membuat menu "Laporan" di sebelah "File"
        JMenu menuLaporan = new JMenu("Laporan");
        menuBar.add(menuLaporan);
        
        // Membuat submenu "Penjualan" di bawah "Laporan"
        JMenu submenuPenjualan = new JMenu("Penjualan");
        menuLaporan.add(submenuPenjualan);
        
        // Membuat item "Omset Bulanan" dan "Omset Harian" di bawah "Penjualan"
        JMenuItem menuOmsetBulanan = new JMenuItem("Omset Bulanan");
        submenuPenjualan.add(menuOmsetBulanan);
        
        JMenuItem menuOmsetHarian = new JMenuItem("Omset Harian");
        submenuPenjualan.add(menuOmsetHarian);
        
        // Menambahkan bar menu ke frame
        setJMenuBar(menuBar);
        
        // Membuat panel utama untuk konten dengan layout BorderLayout
        panelUtama = new JPanel(new BorderLayout());
        
        // Membuat label utama dengan teks "SISTEM INFORMASI PENJUALAN TOKO 'ABC'"
        JLabel labelUtama = new JLabel("SISTEM INFORMASI PENJUALAN TOKO 'ABC'", JLabel.CENTER);
        labelUtama.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Menambahkan label utama ke bagian tengah panel
        panelUtama.add(labelUtama, BorderLayout.CENTER);
        
        // Menambahkan panel utama ke frame
        add(panelUtama);
        
        // Menengahkan frame di layar
        setLocationRelativeTo(null);
        
        // Aksi ketika item menu "Barang" diklik untuk membuka form Barang
        menuBarang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormBarang(AplikasiPenjualan.this).tampilkan();
            }
        });
        
        // Aksi ketika item menu "Satuan" diklik untuk membuka form Satuan
        menuSatuan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormSatuan(AplikasiPenjualan.this).tampilkan();
            }
        });
        
        // Aksi ketika item menu "Omset Bulanan" diklik untuk membuka form Omset Bulanan
        menuOmsetBulanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormOmsetBulanan(AplikasiPenjualan.this).tampilkan();
            }
        });
        
        // Aksi ketika item menu "Omset Harian" diklik untuk membuka form Omset Harian
        menuOmsetHarian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormOmsetHarian(AplikasiPenjualan.this).tampilkan();
            }
        });
    }

    public static void main(String[] args) {
        // Menjalankan aplikasi
        SwingUtilities.invokeLater(() -> {
            AplikasiPenjualan app = new AplikasiPenjualan();
            app.setVisible(true);
        });
    }
}

// Kelas untuk menampilkan form barang
class FormBarang {
    private JDialog dialogBarang;
    
    public FormBarang(JFrame parent) {
        dialogBarang = new JDialog(parent, "Form Barang", false); // false membuatnya modelless
        dialogBarang.setSize(300, 150);
        dialogBarang.setLayout(new GridBagLayout());
        
        // Komponen untuk form Barang
        JLabel labelNamaBarang = new JLabel("Nama Barang:");
        JTextField inputNamaBarang = new JTextField(15);
        JButton tombolTutup = new JButton("Tutup");
        
        // Mengatur layout untuk form Barang
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Spasi antar komponen
        
        // Menambahkan label dan input
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialogBarang.add(labelNamaBarang, gbc);
        
        gbc.gridx = 1;
        dialogBarang.add(inputNamaBarang, gbc);
        
        // Menambahkan tombol tutup
        gbc.gridx = 1;
        gbc.gridy = 1;
        dialogBarang.add(tombolTutup, gbc);
        
        // Aksi untuk menutup dialog ketika tombol "Tutup" diklik
        tombolTutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBarang.dispose(); // Menutup dialog
            }
        });
        
        dialogBarang.setLocationRelativeTo(parent);
    }
    
    public void tampilkan() {
        dialogBarang.setVisible(true);
    }
}

// Kelas untuk menampilkan form satuan
class FormSatuan {
    private JDialog dialogSatuan;
    
    public FormSatuan(JFrame parent) {
        dialogSatuan = new JDialog(parent, "Form Satuan", false); // false membuatnya modelless
        dialogSatuan.setSize(300, 150);
        dialogSatuan.setLayout(new GridBagLayout());
        
        // Komponen untuk form Satuan
        JLabel labelNamaSatuan = new JLabel("Nama Satuan:");
        JTextField inputNamaSatuan = new JTextField(15);
        JButton tombolTutup = new JButton("Tutup");
        
        // Mengatur layout untuk form Satuan
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Spasi antar komponen
        
        // Menambahkan label dan input
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialogSatuan.add(labelNamaSatuan, gbc);
        
        gbc.gridx = 1;
        dialogSatuan.add(inputNamaSatuan, gbc);
        
        // Menambahkan tombol tutup
        gbc.gridx = 1;
        gbc.gridy = 1;
        dialogSatuan.add(tombolTutup, gbc);
        
        // Aksi untuk menutup dialog ketika tombol "Tutup" diklik
        tombolTutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogSatuan.dispose(); // Menutup dialog
            }
        });
        
        dialogSatuan.setLocationRelativeTo(parent);
    }
    
    public void tampilkan() {
        dialogSatuan.setVisible(true);
    }
}

// Kelas untuk menampilkan form omset bulanan
class FormOmsetBulanan {
    private JDialog dialogOmsetBulanan;
    
    public FormOmsetBulanan(JFrame parent) {
        dialogOmsetBulanan = new JDialog(parent, "Omset Bulanan", false); // false membuatnya modelless
        dialogOmsetBulanan.setSize(300, 150);
        dialogOmsetBulanan.setLayout(new GridBagLayout());
        
        // Komponen untuk form Omset Bulanan
        JLabel labelOmsetBulanan = new JLabel("Masukkan Omset Bulanan:");
        JTextField inputOmsetBulanan = new JTextField(15);
        JButton tombolTutup = new JButton("Tutup");
        
        // Mengatur layout untuk form Omset Bulanan
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Spasi antar komponen
        
        // Menambahkan label dan input
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialogOmsetBulanan.add(labelOmsetBulanan, gbc);
        
        gbc.gridx = 1;
        dialogOmsetBulanan.add(inputOmsetBulanan, gbc);
        
        // Menambahkan tombol tutup
        gbc.gridx = 1;
        gbc.gridy = 1;
        dialogOmsetBulanan.add(tombolTutup, gbc);
        
        // Aksi untuk menutup dialog ketika tombol "Tutup" diklik
        tombolTutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogOmsetBulanan.dispose(); // Menutup dialog
            }
        });
        
        dialogOmsetBulanan.setLocationRelativeTo(parent);
    }
    
    public void tampilkan() {
        dialogOmsetBulanan.setVisible(true);
    }
}

// Kelas untuk menampilkan form omset harian
class FormOmsetHarian {
    private JDialog dialogOmsetHarian;
    
    public FormOmsetHarian(JFrame parent) {
        dialogOmsetHarian = new JDialog(parent, "Omset Harian", false); // false membuatnya modelless
        dialogOmsetHarian.setSize(300, 150);
        dialogOmsetHarian.setLayout(new GridBagLayout());
        
        // Komponen untuk form Omset Harian
        JLabel labelOmsetHarian = new JLabel("Masukkan Omset Harian:");
        JTextField inputOmsetHarian = new JTextField(15);
        JButton tombolTutup = new JButton("Tutup");
        
        // Mengatur layout untuk form Omset Harian
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Spasi antar komponen
        
        // Menambahkan label dan input
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialogOmsetHarian.add(labelOmsetHarian, gbc);
        
        gbc.gridx = 1;
        dialogOmsetHarian.add(inputOmsetHarian, gbc);
        
        // Menambahkan tombol tutup
        gbc.gridx = 1;
        gbc.gridy = 1;
        dialogOmsetHarian.add(tombolTutup, gbc);
        
        // Aksi untuk menutup dialog ketika tombol "Tutup" diklik
        tombolTutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogOmsetHarian.dispose(); // Menutup dialog
            }
        });
        
        dialogOmsetHarian.setLocationRelativeTo(parent);
    }
    
    public void tampilkan() {
        dialogOmsetHarian.setVisible(true);
    }
}
